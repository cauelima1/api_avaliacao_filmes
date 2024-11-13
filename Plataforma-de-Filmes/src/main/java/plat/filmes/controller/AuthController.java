package plat.filmes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plat.filmes.model.DTO.AuthDTO;
import plat.filmes.model.DTO.LoginResponseDTO;
import plat.filmes.model.DTO.RegisterDTO;
import plat.filmes.service.LoginService;
import plat.filmes.service.RegisterService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Validated AuthDTO data){
        var token = loginService.login(data);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
        return (registerService.register(data));
    } 
}
