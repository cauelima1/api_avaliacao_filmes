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

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany
    private List<Movie> movieList = new ArrayList<>();

    public User (String login, String password,Perfil perfil){
        this.login = login;
        this.password = password;
        this.perfil = perfil;
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
            return List.of(new SimpleGrantedAuthority("moderador"));
        } if(this.perfil == Perfil.AVANCADO) {
            return List.of(new SimpleGrantedAuthority("avancado"));
        } if (this.perfil == Perfil.BASICO) {
            return List.of(new SimpleGrantedAuthority("basico"));
        } if (this.perfil == Perfil.LEITOR) {
            return List.of(new SimpleGrantedAuthority("leitor"));
        }
        return List.of();
    }



}
