package repository;

import entities.Filme;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FilmeRepository {

    private static final String ARQUIVO = "db.txt";
//    private static Integer GENERATED_ID = getTotalLines();



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

    public void save(Filme filme){
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

    }

    private int gerarNovoId(){
        List<Filme> filmes = findAll();
        return filmes.stream().mapToInt(Filme::getId).max().orElse( 0) + 1;
    }
}
