package dto;

import entities.Filme;

public class FilmeDTO {
    private Integer id;
    private String titulo;
    private String genero;
    private String diretor;
    private Integer ano;
    private Double nota;

    public FilmeDTO() {
    }

    public FilmeDTO(Integer id, String titulo, String genero, String diretor, Integer ano, Double nota) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.diretor = diretor;
        this.ano = ano;
        this.nota = nota;
    }

    public FilmeDTO(Filme filme){
        id = filme.getId();
        titulo = filme.getTitulo();
        genero = filme.getGenero();
        diretor = filme.getDiretor();
        ano = filme.getAno();
        nota = filme.getNota();
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public Integer getAno() {
        return ano;
    }

    public Double getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", diretor='" + diretor + '\'' +
                ", ano=" + ano +
                ", nota=" + nota +
                '}';
    }
}
