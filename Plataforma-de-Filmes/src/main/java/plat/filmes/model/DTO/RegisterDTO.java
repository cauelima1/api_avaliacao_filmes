package plat.filmes.model.DTO;

import plat.filmes.model.Perfil;

public record RegisterDTO (String login, String password, Perfil role) {
}
