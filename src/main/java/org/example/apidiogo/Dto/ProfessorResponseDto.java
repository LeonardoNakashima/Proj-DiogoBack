package org.example.apidiogo.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.apidiogo.Model.Disciplina;

import java.util.List;

@Schema(description = "Objeto de resposta que representa os dados de um professor")
public class ProfessorResponseDto {

    @Schema(description = "Id do professor", example = "123")
    private Long id;
    @Schema(description = "nome do professor", example = "Carlos Alberto")
    private String nome;
    @Schema(description = "Usuario do Professor", example = "Carlos.Alberto")
    private String usuario;
    private List<Disciplina> disciplina;

    public ProfessorResponseDto() {}

    public ProfessorResponseDto(Long id, String nome, String usuario, List<Disciplina> disciplina) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.disciplina = disciplina;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public List<Disciplina> getDisciplina() {
        return disciplina;
    }
}
