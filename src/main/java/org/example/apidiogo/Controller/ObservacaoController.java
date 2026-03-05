package org.example.apidiogo.Controller;

import jakarta.validation.Valid;
import org.example.apidiogo.Dto.DisciplinaRequestDto;
import org.example.apidiogo.Dto.DisciplinaResponseDto;
import org.example.apidiogo.Dto.ObservacaoRequestDto;
import org.example.apidiogo.Dto.ObservacaoResponseDto;
import org.example.apidiogo.Model.Disciplina;
import org.example.apidiogo.Model.Observacao;
import org.example.apidiogo.Openapi.ObservacaoOpenApi;
import org.example.apidiogo.Service.ObservacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/observacao")
public class ObservacaoController implements ObservacaoOpenApi {
    private final ObservacaoService service;
    public ObservacaoController(ObservacaoService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ObservacaoResponseDto>> listObservacoes() {
        List<ObservacaoResponseDto> observacoes = service.listAll();
        return ResponseEntity.ok(observacoes);
    }

    @GetMapping("/findObservacao/{id}")
    public ResponseEntity<List<ObservacaoResponseDto>> findObservacao(@PathVariable Long id) {
        return ResponseEntity.ok(service.listById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ObservacaoResponseDto> createObservacao(@RequestBody @Valid ObservacaoRequestDto dto) {
        ObservacaoResponseDto response = service.createObservacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Observacao> deleteObservacao(@PathVariable Long id) {
        service.deleteObservacao(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ObservacaoResponseDto> updateObservacao(@PathVariable Long id, @RequestBody @Valid ObservacaoRequestDto dto) {
        ObservacaoResponseDto response = service.updateObservacao(dto, id);
        return ResponseEntity.ok(response);
    }



}
