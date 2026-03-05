package org.example.apidiogo.Dto;

import org.example.apidiogo.Model.Observacao;

public class ObservacaoResponseDto {

    private Long id;
    private String descricao;
    private Long id_professor;
    private Long id_aluno;

    public ObservacaoResponseDto() {}

    public ObservacaoResponseDto(Long id, String descricao, Long id_professor, Long id_aluno) {
        this.id = id;
        this.descricao = descricao;
        this.id_professor = id_professor;
        this.id_aluno = id_aluno;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getId_professor() {
        return id_professor;
    }

    public Long getId_aluno() {
        return id_aluno;
    }
}
