package controllers;

import dto.FilmeDTO;
import entities.Filme;
import exceptions.DuplicatedResourceException;
import exceptions.ResourceNotFoundExeception;
import resources.Mensagens;
import services.FilmeService;

import java.util.Comparator;
import java.util.List;

public class FilmeController {

    FilmeService service = new FilmeService();

    public String addFilme(String titulo, String genero, String diretor, int ano, double nota){
        try{
            FilmeDTO dto = new FilmeDTO(0, titulo, genero, diretor, ano, nota);
            return  service.insert(dto);
        }catch (DuplicatedResourceException e){
            return Mensagens.erro(Mensagens.erro(e.getMessage()));
        }
    }

    public void deleteFilme(int id){
        try{
            service.deleteFilme(id);
            System.out.println(Mensagens.sucesso("FILME DELETADO COM SUCESSO."));
        }catch (ResourceNotFoundExeception e){
            System.out.println(Mensagens.erro(Mensagens.erro(e.getMessage())));
        }
    }

    public void findAll(){
        List<FilmeDTO> filmes= service.findAll();
        filmes.forEach(System.out::println);
    }

    public void findByTitulo(String titulo){
        try{
            List<FilmeDTO> filmes = service.findByTitulo(titulo).stream().sorted(Comparator.comparing(FilmeDTO::getTitulo)).toList();
            filmes.forEach(System.out::println);
        } catch (ResourceNotFoundExeception e) {
            System.out.println(Mensagens.erro(e.getMessage()));
        }
    }

    public void findByGenero(String genero){
        try {
            List<FilmeDTO> filmes = service.findByGenero(genero).stream().sorted(Comparator.comparing(FilmeDTO::getGenero)).toList();
            filmes.forEach(System.out::println);
        }catch (ResourceNotFoundExeception e){
            System.out.println(Mensagens.erro(e.getMessage()));
        }
    }

    public void findByDiretor(String diretor){
        try{
            List<FilmeDTO> filmes = service.findByDiretor(diretor).stream().sorted(Comparator.comparing(FilmeDTO::getTitulo)).toList();
            filmes.forEach(System.out::println);
        }catch (ResourceNotFoundExeception e ){
            System.out.println(Mensagens.erro(e.getMessage()));
        }
    }

    public void findByAno(int ano){
        try{
            List<FilmeDTO> filmes = service.findByAno(ano).stream().sorted(Comparator.comparing(FilmeDTO::getAno)).toList();
            filmes.forEach(System.out::println);
        }catch (ResourceNotFoundExeception e ){
            System.out.println(Mensagens.erro(e.getMessage()));
        }
    }
}
