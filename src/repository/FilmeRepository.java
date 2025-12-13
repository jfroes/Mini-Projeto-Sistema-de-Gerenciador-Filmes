package repository;

import entities.Filme;
import exceptions.ResourceNotFoundExeception;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class FilmeRepository {

    private static final String ARQUIVO = "db.txt";

    public String save(Filme filme){
        String id = String.valueOf(gerarNovoId());
        String titulo = filme.getTitulo();
        String genero = filme.getGenero();
        String diretor = filme.getDiretor();
        String ano = filme.getAno().toString();
        String nota = filme.getNota().toString();

        String newFilme = "\n"+ id + ";" + titulo + ";" + genero + ";" + diretor + ";" + ano + ";" + nota;

        try{
            Files.writeString(Paths.get(ARQUIVO), newFilme, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return newFilme;
    }

    public Optional<Filme> findById(Integer id){
        return findAll().stream().filter(filme -> filme.getId().equals(id)).findFirst();
    }

    public List<Filme> findAll(){
        List<Filme> filmes = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get(ARQUIVO));
            for(String line : lines){

                String[] partes = line.split(";");
                Integer id = Integer.parseInt(partes[0]);
                String titulo = partes[1];
                String genero = partes[2];
                String diretor = partes[3];
                Integer ano = Integer.parseInt(partes[4]);
                Double nota = Double.parseDouble(partes[5]);

                filmes.add(new Filme(id, titulo, genero, diretor, ano, nota));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filmes;
    }

    public List<Filme> findByGenero(String genero){
        List<Filme> filmes = findAll();
        return  filmes.stream().filter(f -> f.getGenero().equalsIgnoreCase(genero)).toList();
    }

    public List<Filme> findByTitulo(String titulo){
            return findAll().stream().filter(filme -> filme.getTitulo().toLowerCase().contains(titulo.toLowerCase())).toList();
    }

    public List<Filme> findByDiretor(String diretor){
        return findAll().stream().filter(filme -> filme.getDiretor().toLowerCase().contains(diretor.toLowerCase())).toList();
    }

    public List<Filme> findByAno(int ano){
        return findAll().stream().filter(filme -> filme.getAno() == ano).toList();
    }

    public List<Filme> getByNotaMin(){
        List<Filme> filmes = findAll();
        return filmes.stream().sorted(Comparator.comparingDouble(Filme::getNota)).toList();
    }

    public List<Filme> getByNotaMax(){
        List<Filme> filmes = findAll();
        return filmes.stream().sorted(Comparator.comparingDouble(Filme::getNota).reversed()).toList();
    }
    public Filme update(Filme filme){
        Filme existente = findById(filme.getId()).get();

        existente.setTitulo(filme.getTitulo());
        existente.setGenero(filme.getGenero());
        existente.setDiretor(filme.getDiretor());
        existente.setAno(filme.getAno());
        existente.setNota(filme.getNota());

        try{
            List<String> lines = Files.readAllLines(Paths.get(ARQUIVO));
            int index = existente.getId() - 1;

            if (index < 0 || index >= lines.size()){
                throw new ResourceNotFoundExeception("RECURSO NÃO ENCONTRADO");
            }

            lines.set(index, existente.getId() + ";" + existente.getTitulo() + ";" + existente.getGenero() + ";" + existente.getDiretor() + ";" + existente.getAno() + ";" + existente.getNota());

            Files.write(Paths.get(ARQUIVO), lines);

        }catch (IOException | IndexOutOfBoundsException e) {
            throw new ResourceNotFoundExeception("RECURSO NÃO ENCONTRADO");
        }
        return existente;
    }

    public void delete(Integer id){
        try {
            List<String> lines = Files.readAllLines(Paths.get(ARQUIVO));
            String fime = lines.remove(id - 1);
            Files.write(Paths.get(ARQUIVO), lines);
        } catch (IOException | IndexOutOfBoundsException e) {
            throw new ResourceNotFoundExeception("RECURSO NÃO ENCONTRADO");
        }
    }

    private int gerarNovoId(){
        List<Filme> filmes = findAll();
        return filmes.stream().mapToInt(Filme::getId).max().orElse( 0) + 1;
    }

}
