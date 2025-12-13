package services;

import dto.FilmeDTO;
import entities.Filme;
import exceptions.DuplicatedResourceException;
import exceptions.ResourceNotFoundExeception;
import repository.FilmeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FilmeService {
    private FilmeRepository repository = new FilmeRepository();

    public String insert (FilmeDTO dto){
        Filme filme = new Filme(dto.getTitulo(), dto.getGenero(), dto.getDiretor(), dto.getAno(), dto.getNota());

        if (repository.findAll().stream().anyMatch(f -> f.getTitulo().equalsIgnoreCase(dto.getTitulo()))){
            throw new DuplicatedResourceException("Já existe recurso com esse nome");
        }else{
            return repository.save(filme);
        }
    }

    public void deleteFilme(int id){
            repository.delete(id);
    }

    public List<FilmeDTO> findAll(){
        List<Filme> filmes = repository.findAll();
        return filmes.stream().map(filme -> new FilmeDTO(filme)).toList();
    }

    public FilmeDTO findByid(Integer id){
        try{
            Filme filme = repository.findById(id).get();
            return new FilmeDTO(filme);
        }catch (ResourceNotFoundExeception | NoSuchElementException e){
            throw new ResourceNotFoundExeception("RECURSO NÃO ENCONTRADO");
        }
    }

    public List<FilmeDTO> findByGenero(String gender){
        try{
            List<Filme> filmes = repository.findByGenero(gender);
            return filmes.stream().map(filme -> new FilmeDTO(filme)).toList();

        }catch (ResourceNotFoundExeception | NoSuchElementException e){
            throw new ResourceNotFoundExeception("RECURSO NÃO ENCONTRADO.");
        }
    }

    public List<FilmeDTO> findByDiretor(String diretor){
        try{
            return repository.findByDiretor(diretor).stream().map(FilmeDTO::new).toList();
        }catch (ResourceNotFoundExeception | NoSuchElementException e){
            throw new ResourceNotFoundExeception("RECURSO NÃO ENCONTRADO.");

        }
    }

    public List<FilmeDTO> findByAno(int ano){
        try{
            return repository.findByAno(ano).stream().map(FilmeDTO::new).toList();
        }catch (ResourceNotFoundExeception e ){
            throw new ResourceNotFoundExeception("RECURSO NÃO ENCONTRADO.");
        }
    }

    public List<FilmeDTO> findByTitulo(String titulo){
            List<FilmeDTO> filmes = new ArrayList<>();
             filmes = repository.findByTitulo(titulo).stream().map(FilmeDTO::new).toList();

             if (filmes.isEmpty()){
                 throw new ResourceNotFoundExeception("RECURSO NÃO ENCONTRADO.");
             }
            return filmes;
    }

    public List<FilmeDTO> getByNotaMin(){
        try{
            List<Filme> filmes = repository.getByNotaMin();
            return filmes.stream().map(filme -> new FilmeDTO(filme)).toList();

        }catch (ResourceNotFoundExeception | NoSuchElementException e){
            throw new ResourceNotFoundExeception("RECURSO NÃO ENCONTRADO.");
        }
    }

    public List<FilmeDTO> getByNotaMax(){
        try{
            List<Filme> filmes = repository.getByNotaMax();
            return filmes.stream().map(filme -> new FilmeDTO(filme)).toList();

        }catch (ResourceNotFoundExeception | NoSuchElementException e){
            throw new ResourceNotFoundExeception("RECURSO NÃO ENCONTRADO.");
        }
    }

    public FilmeDTO update (FilmeDTO dto){
        try{
            if (repository.findAll().stream().anyMatch(f -> f.getTitulo().equalsIgnoreCase(dto.getTitulo())) &&
            repository.findAll().stream().noneMatch(f -> f.getId().equals(dto.getId()))){
                throw new DuplicatedResourceException("JÁ EXISTE UM RECURSO COM ESTE NOME");
            }

            Filme filme = new Filme(dto.getId(), dto.getTitulo(), dto.getGenero(), dto.getDiretor(), dto.getAno(), dto.getNota());

            filme = repository.update(filme);

            return new FilmeDTO(filme);
        }catch (IndexOutOfBoundsException e){
            throw new ResourceNotFoundExeception("RECURSO NÃO ENCONTRADO.");
        }
    }
}
