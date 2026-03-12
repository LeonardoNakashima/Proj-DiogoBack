package org.example.apidiogo.Controller;

import jakarta.validation.Valid;
import org.example.apidiogo.Dto.*;
import org.example.apidiogo.Model.Disciplina;
import org.example.apidiogo.Model.Observacao;
import org.example.apidiogo.Openapi.ObservacaoOpenApi;
import org.example.apidiogo.Service.ObservacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/findObservacaoAluno/{idAluno}")
    public ResponseEntity<List<ObservacaoResponseDto>> findObservacaoAluno(@PathVariable Long idAluno) {
        return ResponseEntity.ok(service.listByIdAluno(idAluno));
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

    @PatchMapping("/update/{id}")
    public ResponseEntity<ObservacaoResponseDto> patchObservacao(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        ObservacaoResponseDto response = service.updatePatchObservacao(updates, id);
        return ResponseEntity.ok(response);
    }

}
