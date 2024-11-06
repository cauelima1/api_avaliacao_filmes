package plat.filmes.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import plat.filmes.model.DTO.OmdbDTO;
import plat.filmes.model.Movie;

import java.util.Map;
import java.util.Objects;

@FeignClient(name = "OMDB", url = "http://www.omdbapi.com/")
public interface OMDBService {


    @GetMapping("/?apikey=62d3e3f1&t={nome}")
    OmdbDTO consultMovie(@PathVariable("nome") String nome);
}
