package plat.filmes;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import plat.filmes.model.Movie;
import plat.filmes.service.OMDBService;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;


@EnableFeignClients
@SpringBootApplication
public class PlataformaDeFilmesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PlataformaDeFilmesApplication.class, args);
	}

	@Autowired
	private OMDBService omdbService;


	@Override
	public void run(String... args) throws Exception {
		Scanner leitura = new Scanner(System.in);
		System.out.println("Digite o título do filme para busca: ");
		var busca = leitura.nextLine();

		String tituloCodificado = URLEncoder.encode(busca, "UTF-8");
		String endereco = "https://www.omdbapi.com/?t=" + tituloCodificado + "&apikey=479a1bfc";

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(endereco))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());


	}
}

