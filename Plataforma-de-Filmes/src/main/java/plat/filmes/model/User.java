package plat.filmes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@EqualsAndHashCode(of = "id")
@Entity(name = "tb_users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private Perfil perfil;
    private int points;

     public User (String login, String password,Perfil perfil){
        this.login = login;
        this.password = password;
        this.perfil = perfil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public User() {
    }

    public User(Long id, String login, String password, Perfil perfil, int points) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.perfil = perfil;
        this.points = points;

    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.perfil == Perfil.MODERADOR){
            return List.of(new SimpleGrantedAuthority("ROLE_MODERADOR"));
        } if(this.perfil == Perfil.AVANCADO) {
            return List.of(new SimpleGrantedAuthority("ROLE_AVANCADO"));
        } if (this.perfil == Perfil.BASICO) {
            return List.of(new SimpleGrantedAuthority("ROLE_BASICO"));
        } if (this.perfil == Perfil.LEITOR) {
            return List.of(new SimpleGrantedAuthority("ROLE_LEITOR"));
        }
        return List.of();
    }

}