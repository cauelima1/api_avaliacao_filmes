package plat.filmes.service;

import plat.filmes.controller.MovieController;
import plat.filmes.model.Perfil;
import plat.filmes.repository.UserRepository;

public class PointsSystem {

    private int movieRating;
    private boolean likeMovie;

    private final MovieController movieController;
    private final UserRepository userRepository;

    public PointsSystem(MovieController movieController, UserRepository userRepository) {
        this.movieController = movieController;
        this.userRepository = userRepository;
    }

    void points (){
        if (likeMovie==true){
            movieRating++;
        }
    }

    void verifyPointsToBasic (Long id) {
        if (movieRating==20){
            userRepository.getReferenceById(id).setPerfil(Perfil.BASICO);
            //configuracao para sair
        }
    }

    void verifyPointsToAdvance (Long id) {
        if (movieRating==180){
            userRepository.getReferenceById(id).setPerfil(Perfil.AVANCADO);
            //configuracao para sair
        }
    }

    void verifyPointsToModerate (Long id) {
        if (movieRating==1000){
            userRepository.getReferenceById(id).setPerfil(Perfil.MODERADOR);
            //configuracao para sair
        }
    }


}
