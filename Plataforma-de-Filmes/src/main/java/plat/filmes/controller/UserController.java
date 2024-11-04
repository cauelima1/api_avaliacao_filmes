package plat.filmes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plat.filmes.model.DTO.UserDTO;
import plat.filmes.model.User;
import plat.filmes.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> showUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> showAllUsers () {
        return ResponseEntity.ok(userService.findAll())  ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<User>> deleteUserById(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<User> create (@RequestBody UserDTO userDTO){
        User usuarioCadastrado = userService.create(userDTO);
        return ResponseEntity.ok(usuarioCadastrado);
    }


}
