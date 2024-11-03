package plat.filmes.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plat.filmes.model.DTO.UserDTO;
import plat.filmes.model.User;

import plat.filmes.service.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {


    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create (@RequestBody UserDTO userDTO){
        User usuarioCadastrado = userService.create(userDTO);
        return ResponseEntity.ok(usuarioCadastrado);
    }


}
