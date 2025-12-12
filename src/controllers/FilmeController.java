package controllers;

import dto.FilmeDTO;
import entities.Filme;
import exceptions.ResourceNotFoundExeception;
import services.FilmeService;

import java.util.Comparator;
import java.util.List;

public class FilmeController {

    FilmeService service = new FilmeService();

    public void findAll(){
        List<FilmeDTO> filmes= service.findAll();
        filmes.forEach(System.out::println);
    }

    public void findByTitulo(String titulo){
        try{
            List<FilmeDTO> filmes = service.findByTitulo(titulo).stream().sorted(Comparator.comparing(FilmeDTO::getTitulo)).toList();
            filmes.forEach(System.out::println);
        } catch (ResourceNotFoundExeception e) {
            System.out.println(e.getMessage());
        }
    }

    public void findByGenero(String genero){
        try {
            List<FilmeDTO> filmes = service.findByGenero(genero).stream().sorted(Comparator.comparing(FilmeDTO::getGenero)).toList();
            filmes.forEach(System.out::println);
        }catch (ResourceNotFoundExeception e){
            System.out.println(e.getMessage());
        }
    }

    public void findByDiretor(String diretor){
        try{
            List<FilmeDTO> filmes = service.findByDiretor(diretor).stream().sorted(Comparator.comparing(FilmeDTO::getTitulo)).toList();
            filmes.forEach(System.out::println);
        }catch (ResourceNotFoundExeception e ){
            System.out.println(e.getMessage());
        }
    }

    public void findByAno(int ano){
        try{
            List<FilmeDTO> filmes = service.findByAno(ano).stream().sorted(Comparator.comparing(FilmeDTO::getAno)).toList();
            filmes.forEach(System.out::println);
        }catch (ResourceNotFoundExeception e ){
            System.out.println(e.getMessage());
        }
    }
}
