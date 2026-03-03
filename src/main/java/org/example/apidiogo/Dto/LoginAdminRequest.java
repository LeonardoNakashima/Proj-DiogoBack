package org.example.apidiogo.Dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Objeto de requisição para login de administrador")
public class LoginAdminRequest {


    @NotBlank(message = "O usuario não pode estar em branco")
    @Schema(description = "Usuario de login do administrador", example = "admin123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String usuario;

    @NotBlank(message = "A senha не pode estar em branco")
    @Schema(description = "Senha de acesso do administrador", example = "senhaForte123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
