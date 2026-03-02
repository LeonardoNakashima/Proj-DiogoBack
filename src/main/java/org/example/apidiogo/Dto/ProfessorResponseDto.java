package org.example.apidiogo.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de resposta que representa os dados de um professor")
public class ProfessorResponseDto {

    @Schema(description = "Id do professor", example = "123")
    private Long id;
    @Schema(description = "nome do professor", example = "Carlos Alberto")
    private String nome;
    @Schema(description = "Usuario do Professor", example = "Carlos.Alberto")
    private String usuario;

    public ProfessorResponseDto() {}

    public ProfessorResponseDto(Long id, String nome, String usuario) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
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
}
