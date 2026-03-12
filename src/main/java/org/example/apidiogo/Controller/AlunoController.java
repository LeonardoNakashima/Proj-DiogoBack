package org.example.apidiogo.Controller;

import jakarta.validation.Valid;
import org.example.apidiogo.Dto.*;
import org.example.apidiogo.Exception.AdminNotFoundException;
import org.example.apidiogo.Exception.AlunoNotFoundException;
import org.example.apidiogo.Model.Admin;
import org.example.apidiogo.Model.Aluno;
import org.example.apidiogo.Openapi.AlunoOpenApi;
import org.example.apidiogo.Repository.AlunoRepository;
import org.example.apidiogo.Service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/aluno")
public class AlunoController implements AlunoOpenApi {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<AlunoResponseDto>> listAlunos() {
        List<AlunoResponseDto> alunos = service.listAll();
        return ResponseEntity.ok(alunos);
    }
    @GetMapping("/notas")
    public ResponseEntity<List<AlunoNotaDto>> listarAlunosComNotas(){
        return ResponseEntity.ok(service.listarAlunosComNotas());
    }


    @GetMapping("/findAluno/{matricula}")
    public ResponseEntity<List<AlunoResponseDto>> findAluno(@PathVariable Long matricula) {
        return ResponseEntity.ok(service.listByMatricula(matricula));
    }

    @GetMapping("/findAlunoEmail/{email}")
    public ResponseEntity<List<AlunoResponseDto>> findAlunoEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.listByEmail(email));
    }

    @PostMapping("/create")
    public ResponseEntity<AlunoResponseDto> createAluno(@RequestBody @Valid AlunoRequestDto dto) {
        AlunoResponseDto response = service.createAluno(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/delete/{matricula}")
    public ResponseEntity<Aluno> deleteAluno(@PathVariable Long matricula) {
        service.deleteAluno(matricula);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{matricula}")
    public ResponseEntity<AlunoResponseDto> updateAluno(@PathVariable Long matricula, @RequestBody @Valid AlunoRequestDto dto) {
        AlunoResponseDto response = service.updateAluno(dto, matricula);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/{matricula}")
    public ResponseEntity<AlunoResponseDto> patchAluno(@PathVariable Long matricula, @RequestBody Map<String, Object> updates) {
        AlunoResponseDto response = service.updatePatchAluno(updates, matricula);
        return ResponseEntity.ok(response);
    }
}
