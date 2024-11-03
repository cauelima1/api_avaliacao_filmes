package plat.filmes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import plat.filmes.model.DTO.AuthenticationDTO;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public AcessDTO login(AuthenticationDTO authDTO){

        UsernamePasswordAuthenticationToken userAuth = new
                UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword());

        Authentication authentication = authenticationManager.authenticate(userAuth);

        UserDetailsImpl userAuthenticate = (UserDetailsImpl) authentication.getPrincipal();
        return null;
    }

}
