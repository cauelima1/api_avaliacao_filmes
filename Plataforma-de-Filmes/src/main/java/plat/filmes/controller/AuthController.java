package plat.filmes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plat.filmes.model.DTO.AuthDTO;
import plat.filmes.model.DTO.LoginResponseDTO;
import plat.filmes.model.DTO.RegisterDTO;
import plat.filmes.model.User;
import plat.filmes.repository.UserRepository;
import plat.filmes.service.LoginService;
import plat.filmes.service.RegisterService;
import plat.filmes.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Validated AuthDTO data){
        var token = loginService.login(data);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
        return (registerService.register(data));
    }

    @PutMapping("/change/{login}")
    public ResponseEntity<User> changePerfil(@PathVariable("login") String login){
        User user = userService.changePerfil(login);
        return ResponseEntity.ok(user);
    }
}
