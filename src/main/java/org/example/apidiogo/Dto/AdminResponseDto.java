package org.example.apidiogo.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de resposta que representa os dados de um admin")
public class AdminResponseDto {

    @Schema(description = "Id do administrador", example = "123")
    private Long id;
    @Schema(description = "usuario do administrador", example = "admin123")
    private String usuario;

    public AdminResponseDto() {}

    public AdminResponseDto(Long id, String usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }
    public String getUsuario() {
        return usuario;
    }

}
