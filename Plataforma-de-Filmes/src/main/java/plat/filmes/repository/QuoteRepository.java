package plat.filmes.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import plat.filmes.model.submodel.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
