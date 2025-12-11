package services;

import dto.FilmeDTO;
import entities.Filme;
import exceptions.DuplicatedResourceException;
import exceptions.ResourceNotFoundExeception;
import repository.FilmeRepository;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;

public class FilmeService {

    public FilmeDTO insert (FilmeDTO dto){
        Filme filme = new Filme(dto.getTitulo(), dto.getGenero(), dto.getDiretor(), dto.getAno(), dto.getNota());

        if (repository.findAll().stream().anyMatch(f -> f.getTitulo().equalsIgnoreCase(dto.getTitulo()))){
            throw new DuplicatedResourceException("Já existe recurso com esse nome");
        }else{
            repository.save(filme);
            return new FilmeDTO(filme);
        }
    }

    private FilmeRepository repository = new FilmeRepository();

    public List<FilmeDTO> findAll(){
        List<Filme> filmes = repository.findAll();
        return filmes.stream().map(filme -> new FilmeDTO(filme)).toList();
    }

    public FilmeDTO findByid(Integer id){
        try{
            Filme filme = repository.findById(id).get();
            return new FilmeDTO(filme);
        }catch (ResourceNotFoundExeception | NoSuchElementException e){
            throw new ResourceNotFoundExeception("Recurso não encontrado.");
        }
    }

    public List<FilmeDTO> findByGenero(String gender){
        try{
            List<Filme> filmes = repository.findByGenero(gender);
            return filmes.stream().map(filme -> new FilmeDTO(filme)).toList();

        }catch (ResourceNotFoundExeception | NoSuchElementException e){
            throw new ResourceNotFoundExeception("Recurso não encontrado.");
        }
    }

    public FilmeDTO update (FilmeDTO dto){
        try{
            if (repository.findAll().stream().anyMatch(f -> f.getTitulo().equalsIgnoreCase(dto.getTitulo())) &&
            repository.findAll().stream().noneMatch(f -> f.getId().equals(dto.getId()))){
                throw new DuplicatedResourceException("Já existe um recurso com este nome");
            }

            Filme filme = new Filme(dto.getId(), dto.getTitulo(), dto.getGenero(), dto.getDiretor(), dto.getAno(), dto.getNota());

            filme = repository.update(filme);

            return new FilmeDTO(filme);
        }catch (IndexOutOfBoundsException e){
            throw new ResourceNotFoundExeception("Recurso não encontrado.");
        }
    }
}
