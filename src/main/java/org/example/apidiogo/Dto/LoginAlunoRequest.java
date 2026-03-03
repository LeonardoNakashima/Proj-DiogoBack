package org.example.apidiogo.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Objeto de requisição para login de aluno")
public class LoginAlunoRequest {
    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O usuario não pode estar em branco")
    @Schema(description = "email de login do aluno", example = "aluno@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "A senha не pode estar em branco")
    @Schema(description = "Senha de acesso do administrador", example = "senhaForte123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
