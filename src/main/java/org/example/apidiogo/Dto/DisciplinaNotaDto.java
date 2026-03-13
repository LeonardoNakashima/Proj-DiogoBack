package org.example.apidiogo.Dto;

public class DisciplinaNotaDto {

    private String disciplina;
    private Double n1;
    private Double n2;
    private Double media;

    public DisciplinaNotaDto(String disciplina, Double n1, Double n2, Double media) {
        this.disciplina = disciplina;
        this.n1 = n1;
        this.n2 = n2;
        this.media = media;
    }

    public String getDisciplina() { return disciplina; }
    public Double getN1() { return n1; }
    public Double getN2() { return n2; }
    public Double getMedia() { return media; }
}