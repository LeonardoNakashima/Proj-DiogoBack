package org.example.apidiogo.Dto;

import java.util.List;

public class BoletimAlunoDto {

    private Long matricula;
    private String nome;
    private List<DisciplinaNotaDto> disciplinas;

    public BoletimAlunoDto(Long matricula, String nome, List<DisciplinaNotaDto> disciplinas) {
        this.matricula = matricula;
        this.nome = nome;
        this.disciplinas = disciplinas;
    }

    public Long getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public List<DisciplinaNotaDto> getDisciplinas() { return disciplinas; }
}