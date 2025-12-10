package services;

import dto.FilmeDTO;
import entities.Filme;
import exceptions.DuplicatedResourceException;
import repository.FilmeRepository;

import java.util.List;

public class FilmeService {

    private FilmeRepository repository = new FilmeRepository();

    public List<FilmeDTO> findAll(){
        List<Filme> filmes = repository.findAll();
        return filmes.stream().map(filme -> new FilmeDTO(filme)).toList();
    }

    public FilmeDTO insert (FilmeDTO dto){
        Filme filme = new Filme(dto.getTitulo(), dto.getGenero(), dto.getDiretor(), dto.getAno(), dto.getNota());

        if (repository.findAll().stream().anyMatch(f -> f.getTitulo().equalsIgnoreCase(dto.getTitulo()))){
            throw new DuplicatedResourceException("Já existe recurso com esse nome");
        }else{
            repository.save(filme);
            return new FilmeDTO(filme);
        }
    }
}
