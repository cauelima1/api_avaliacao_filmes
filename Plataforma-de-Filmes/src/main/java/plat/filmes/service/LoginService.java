package plat.filmes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import plat.filmes.model.DTO.AuthDTO;
import plat.filmes.model.User;
import plat.filmes.security.TokenService;

@Service
public class LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    public String login (AuthDTO data){
        var usernamePassord = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassord);
        var token = tokenService.generateToken((User) auth.getPrincipal());
      return token;
    }
}
