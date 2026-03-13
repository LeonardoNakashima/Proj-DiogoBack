package org.example.apidiogo.Dto;

public class AlunoNotaDto {

    private Long matricula;
    private String nome;
    private String disciplina;
    private Double n1;
    private Double n2;
    private Double media;

    public AlunoNotaDto(Long matricula, String nome, String disciplina,Double n1, Double n2, Double media) {
        this.matricula = matricula;
        this.nome = nome;
        this.disciplina = disciplina;
        this.n1 = n1;
        this.n2 = n2;
        this.media = media;
    }

    public Long getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public String getDisciplina() {return disciplina;}
    public Double getN1() { return n1; }
    public Double getN2() { return n2; }
    public Double getMedia() { return media; }
}