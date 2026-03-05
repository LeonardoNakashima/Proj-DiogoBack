package org.example.apidiogo.Dto;

import jakarta.validation.Valid;

public class ObservacaoRequestDto {

    @Valid
    private String descricao;
    @Valid
    private Long id_professor;
    @Valid
    private Long id_aluno;

    public @Valid String getDescricao() {
        return descricao;
    }

    public void setDescricao(@Valid String descricao) {
        this.descricao = descricao;
    }

    public @Valid Long getId_professor() {
        return id_professor;
    }

    public void setId_professor(@Valid Long id_professor) {
        this.id_professor = id_professor;
    }

    public @Valid Long getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(@Valid Long id_aluno) {
        this.id_aluno = id_aluno;
    }
}
