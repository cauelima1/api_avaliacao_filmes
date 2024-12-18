package plat.filmes.service;

import plat.filmes.model.DTO.UserDTO;
import plat.filmes.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

    Optional<User> findById(Long id);

    List<User> findAll();

    void delete (Long id);

    User create(UserDTO userDTO);




}
