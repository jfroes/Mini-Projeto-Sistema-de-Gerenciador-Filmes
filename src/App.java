import dto.FilmeDTO;
import entities.Filme;
import repository.FilmeRepository;
import services.FilmeService;

import java.util.Comparator;
import java.util.List;

public class App {
    public static void main(String[] args) {
        FilmeService service = new FilmeService();
        FilmeRepository repository = new FilmeRepository();

        List<FilmeDTO> filmes = service.findByNotaMax();
        filmes.forEach(System.out::println);
    }
}
