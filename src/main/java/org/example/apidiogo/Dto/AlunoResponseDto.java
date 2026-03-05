package org.example.apidiogo.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de resposta que representa os dados de um aluno")
public class AlunoResponseDto {
    @Schema(description = "Matricula do aluno", example = "123")
    private Long matricula;
    @Schema(description = "nome do aluno", example = "Maria Eduarda")
    private String nome;
    @Schema(description = "email do aluno", example = "maria.eduarda@gmail.com")
    private String email;

    public AlunoResponseDto() {}

    public AlunoResponseDto(Long matricula, String nome, String email) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
    }

    public Long getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }



}
