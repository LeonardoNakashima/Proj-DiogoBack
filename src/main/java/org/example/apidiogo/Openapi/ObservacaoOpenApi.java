package org.example.apidiogo.Openapi;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.apidiogo.Dto.DisciplinaRequestDto;
import org.example.apidiogo.Dto.DisciplinaResponseDto;
import org.example.apidiogo.Dto.ObservacaoRequestDto;
import org.example.apidiogo.Dto.ObservacaoResponseDto;
import org.example.apidiogo.Model.Disciplina;
import org.example.apidiogo.Model.Observacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Observação", description = "Endpoints para gerenciamento das Observação")
public interface ObservacaoOpenApi {
    @Operation(
            summary = "Lista todas as Observações",
            description = "Retorna todas as observações cadastradas no sistema."
    )
    ResponseEntity<List<ObservacaoResponseDto>> listObservacoes();


    @Operation(
            summary = "Lista Observação por id",
            description = "Retorna a observação com o id."
    )
    ResponseEntity<List<ObservacaoResponseDto>> findObservacao(Long id);


    @Operation(
            summary = "Inserir Observação",
            description = "Inserindo observação no sistema"
    )
    public ResponseEntity<ObservacaoResponseDto> createObservacao(@RequestBody @Valid ObservacaoRequestDto dto);

    @Operation(
            summary = "Deletar observação",
            description = "excluindo observação do sistema"
    )
    public ResponseEntity<Observacao> deleteObservacao(Long id);


    @Operation(
            summary = "Atualizar observação",
            description = "atualizando observação do sistema"
    )
    public ResponseEntity<ObservacaoResponseDto> updateObservacao(@PathVariable Long id, @RequestBody @Valid ObservacaoRequestDto dto);
}