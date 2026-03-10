package org.example.apidiogo.Controller;

import jakarta.validation.Valid;
import org.example.apidiogo.Dto.*;
import org.example.apidiogo.Model.Admin;
import org.example.apidiogo.Model.Disciplina;
import org.example.apidiogo.Openapi.DisciplinaOpenApi;
import org.example.apidiogo.Service.AdminService;
import org.example.apidiogo.Service.DisciplinaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/disciplina")
public class DisciplinaController implements DisciplinaOpenApi {

    private final DisciplinaService service;

    public DisciplinaController(DisciplinaService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<DisciplinaResponseDto>> listDisciplinas() {
        List<DisciplinaResponseDto> disciplinas = service.listAll();
        return ResponseEntity.ok(disciplinas);
    }


    @GetMapping("/findDisciplina/{id}")
    public ResponseEntity<List<DisciplinaResponseDto>> findDisciplina(@PathVariable Long id) {
        return ResponseEntity.ok(service.listById(id));
    }


    @PostMapping("/create")
    public ResponseEntity<DisciplinaResponseDto> createDisciplina(@RequestBody @Valid DisciplinaRequestDto dto) {
        DisciplinaResponseDto response = service.createDisciplina(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Disciplina> deleteDisciplina(@PathVariable Long id) {
        service.deleteDisciplina(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DisciplinaResponseDto> updateDisciplina(@PathVariable Long id, @RequestBody @Valid DisciplinaRequestDto dto) {
        DisciplinaResponseDto response = service.updateDisciplina(dto, id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<DisciplinaResponseDto> patchDisciplina(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        DisciplinaResponseDto response = service.updatePatchDisciplina(updates, id);
        return ResponseEntity.ok(response);
    }
}
