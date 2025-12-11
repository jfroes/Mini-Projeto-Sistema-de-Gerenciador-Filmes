import dto.FilmeDTO;
import repository.FilmeRepository;
import services.FilmeService;

import java.util.List;

public class App {
    public static void main(String[] args) {
        FilmeService service = new FilmeService();
        FilmeRepository repository = new FilmeRepository();



       List<FilmeDTO> filmesTitulo = service.findByDiretor("la");

       filmesTitulo.forEach(System.out::println);
    }
}
