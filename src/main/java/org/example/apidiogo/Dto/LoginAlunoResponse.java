package org.example.apidiogo.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta da autenticação bem-sucedida de um aluno")
public class LoginAlunoResponse {

    @Schema(description = "Token JWT para autorização de requisições futuras.", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;

    @Schema(description = "Nome do aluno autenticado.", example = "Maria Silva")
    private String nome;

    @Schema(description = "Número da matricula do aluno autenticado.", example = "12345")
    private Long matricula;

    public LoginAlunoResponse(String token, String nome, Long matricula) {
        this.token = token;
        this.nome = nome;
        this.matricula = matricula;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Long getMatricula() {
        return matricula;
    }
    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }
}
