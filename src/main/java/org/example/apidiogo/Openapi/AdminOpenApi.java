package org.example.apidiogo.Openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.apidiogo.Dto.AdminRequestDto;
import org.example.apidiogo.Dto.AdminResponseDto;
import org.example.apidiogo.Model.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Admin", description = "Endpoints para gerenciamento dos Admins")
public interface AdminOpenApi {

    @Operation(
            summary = "Lista todos os Admins",
            description = "Retorna todos os Admins cadastrados no sistema."
    )
    ResponseEntity<List<AdminResponseDto>> listAdmins();

    @Operation(
            summary = "Lista admin por id",
            description = "Retorna o Admin com o id."
    )
    ResponseEntity<List<AdminResponseDto>> findAdmin(Long id);


    @Operation(
            summary = "Inserir admin",
            description = "Inserindo admin no sistema"
    )
    public ResponseEntity<AdminResponseDto> createAdmin(@RequestBody @Valid AdminRequestDto dto);


    @Operation(
            summary = "Deletar admin",
            description = "excluindo admin do sistema"
    )
    public ResponseEntity<Admin> deleteAdmin(Long id);


    @Operation(summary = "Atualiza um administrador", description = "Atualiza todos os dados de um administrador existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdminResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Administrador não encontrado", content = @Content)
    })
    ResponseEntity<AdminResponseDto> updateAdmin(
            @Parameter(description = "Id do administrador para ser atualizado", required = true, example = "123 ") Long id,
            @Parameter(description = "Novos dados da empresa") AdminRequestDto dto);

}
