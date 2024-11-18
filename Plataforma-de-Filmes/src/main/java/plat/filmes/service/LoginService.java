package plat.filmes.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import plat.filmes.model.DTO.AuthDTO;
import plat.filmes.model.User;
import plat.filmes.repository.UserRepository;
import plat.filmes.security.TokenService;

import java.time.LocalDateTime;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String login (AuthDTO data) {
        User user = (User) userRepository.findByLogin(data.login());
        if(user != null) {
            try {
                    var usernamePassord = new UsernamePasswordAuthenticationToken(data.login(), data.password());
                    var auth = this.authenticationManager.authenticate(usernamePassord);
                    var token = tokenService.generateToken((User) auth.getPrincipal());
                    resetFailedLoginAttempts(user);
                    return token;
            } catch (Exception e){
                loginFailed(user);
                throw new RuntimeException("Invalid password, try again.");
            }
        } else {
            throw new RuntimeException("Login not found");
        }
    }

    @Transactional
    public void loginFailed(User user) {
        if (user!=null){
            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
            user.setLastFailedLogin(LocalDateTime.now());
            userRepository.save(user);
            } if (user.getFailedLoginAttempts() >= 3) {
                user.setAccountNonLocked(false);
                userRepository.save(user);
                throw new RuntimeException("Account locked. Try again in 30 minutes.");
            }

    }

    @Transactional
    public void resetFailedLoginAttempts(User user) {
        if(user.isAccountNonLocked()){
                user.setFailedLoginAttempts(0);
                user.setAccountNonLocked(true);
                userRepository.save(user);
            }
    }

}
