package org.example.apidiogo.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta da autenticação bem-sucedida.")
public class LoginAdminResponse {

    @Schema(description = "Token JWT para autorização de requisições futuras.", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY...")
    private String token;

    @Schema(description = "Usuario do admin autenticado.", example = "Julia")
    private String usuario;

    public LoginAdminResponse(String token, String usuario) {
        this.token = token;
        this.usuario = usuario;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
