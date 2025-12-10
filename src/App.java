import dto.FilmeDTO;
import entities.Filme;
import repository.FilmeRepository;
import services.FilmeService;

import java.util.Comparator;
import java.util.List;

public class App {
    public static void main(String[] args) {
        FilmeService service = new FilmeService();

        Filme filme = new Filme(0,"As tranças do rei creca", "imaginação", "Biru Leiby", 1974, 8.7);
        FilmeDTO dto = new FilmeDTO(filme);
        service.insert(dto);

        List<FilmeDTO> filmes = service.findAll();

        filmes.forEach(System.out::println);
    }
}
