package org.example.apidiogo.Openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.apidiogo.Dto.DisciplinaRequestDto;
import org.example.apidiogo.Dto.DisciplinaResponseDto;
import org.example.apidiogo.Model.Disciplina;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Disciplina", description = "Endpoints para gerenciamento das Disciplinas")
public interface DisciplinaOpenApi {

    @Operation(
            summary = "Lista todas as Disciplinas",
            description = "Retorna todas as disciplinas cadastradas no sistema."
    )
    ResponseEntity<List<DisciplinaResponseDto>> listDisciplinas();

    @Operation(
            summary = "Lista Disciplina por id",
            description = "Retorna a Disciplina com o id."
    )
    ResponseEntity<List<DisciplinaResponseDto>> findDisciplina(Long id);


    @Operation(
            summary = "Inserir Disciplina",
            description = "Inserindo disciplina no sistema"
    )
    public ResponseEntity<DisciplinaResponseDto> createDisciplina(@RequestBody @Valid DisciplinaRequestDto dto);


    @Operation(
            summary = "Deletar disciplina",
            description = "excluindo disciplina do sistema"
    )
    public ResponseEntity<Disciplina> deleteDisciplina(Long id);

    @Operation(
            summary = "Atualizar disciplina",
            description = "atualizando disciplina do sistema"
    )
    public ResponseEntity<DisciplinaResponseDto> updateDisciplina(@PathVariable Long id, @RequestBody @Valid DisciplinaRequestDto dto);
}
