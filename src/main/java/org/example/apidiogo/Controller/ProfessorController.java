package org.example.apidiogo.Controller;

import jakarta.validation.Valid;
import org.example.apidiogo.Dto.*;
import org.example.apidiogo.Exception.DisciplinaNotFoundException;
import org.example.apidiogo.Exception.ProfessorNotFoundException;
import org.example.apidiogo.Model.Disciplina;
import org.example.apidiogo.Model.Professor;
import org.example.apidiogo.Openapi.ProfessorOpenApi;
import org.example.apidiogo.Repository.ProfessorRepository;
import org.example.apidiogo.Service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/professor")
public class ProfessorController implements ProfessorOpenApi {

    private final ProfessorService service;
    private final ProfessorRepository professorRepository;

    public ProfessorController(ProfessorService service, ProfessorRepository professorRepository) {
        this.service = service;
        this.professorRepository = professorRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProfessorResponseDto>> listProfessores() {
        List<ProfessorResponseDto> professores = service.listProfessorWithDisciplina();
        return ResponseEntity.ok(professores);
    }



    @GetMapping("/findProfessor/{id}")
    public ResponseEntity<List<ProfessorResponseDto>> findProfessor(@PathVariable Long id) {
        return ResponseEntity.ok(service.listById(id));
    }


    @PostMapping("/create")
    public ResponseEntity<ProfessorResponseDto> createProfessor(@RequestBody @Valid ProfessorRequestDto dto) {
       ProfessorResponseDto response = service.createProfessor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Professor> deleteProfessor(@PathVariable Long id) {
        service.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProfessorResponseDto> updateProfessor(@PathVariable Long id, @RequestBody @Valid ProfessorRequestDto dto) {
        ProfessorResponseDto response = service.updateProfessor(dto, id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ProfessorResponseDto> patchProfessor(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        ProfessorResponseDto response = service.updatePatchProfessor(updates, id);
        return ResponseEntity.ok(response);
    }
}
