package plat.filmes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import plat.filmes.model.DTO.RegisterDTO;
import plat.filmes.model.User;
import plat.filmes.repository.UserRepository;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity register (RegisterDTO data){
        if(this.userRepository.findByLogin(data.login()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());
        this.userRepository.save(newUser);
        return  ResponseEntity.ok().build();
    }
}
