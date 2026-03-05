package org.example.apidiogo.Service;
import jakarta.validation.Valid;
import org.example.apidiogo.Dto.AlunoResponseDto;
import org.example.apidiogo.Dto.ProfessorRequestDto;
import org.example.apidiogo.Dto.ProfessorResponseDto;
import org.example.apidiogo.Exception.AlunoNotFoundException;
import org.example.apidiogo.Exception.ProfessorNotFoundException;
import org.example.apidiogo.Model.Aluno;
import org.example.apidiogo.Model.Professor;
import org.example.apidiogo.Repository.ProfessorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfessorService(
            ProfessorRepository professorRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.professorRepository = professorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private Professor fromRequestDTO(ProfessorRequestDto dto) {
        Professor professor = new Professor();
        professor.setNome(dto.getNome());
        professor.setUsuario(dto.getUsuario());
        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        professor.setSenha(senhaCriptografada);
        return professor;
    }

    private ProfessorResponseDto toResponseDto(Professor professor) {
        return new ProfessorResponseDto(
                professor.getId(),
                professor.getNome(),
                professor.getUsuario()
        );
    }

    public List<ProfessorResponseDto> listAll() {
        return professorRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<ProfessorResponseDto> listById(Long id) {
        Optional<Professor> professor = professorRepository.findById(id);
        return professor.stream()
                .map(this::toResponseDto)
                .toList();
    }

    public void deleteProfessor(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() ->
                        new ProfessorNotFoundException("Professor não encontrado")
                );
        professorRepository.delete(professor);
    }

    public ProfessorResponseDto createProfessor(ProfessorRequestDto dto) {
         Professor professor = fromRequestDTO(dto);
         Professor salvo = professorRepository.save(professor);
         return toResponseDto(salvo);
    }

    public ProfessorResponseDto updateProfessor(@Valid ProfessorRequestDto professorAtualizado, Long id) {
        Professor existente = professorRepository.findProfessorById(id)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor com o id " + id + " não encontrado"));
        existente.setNome(professorAtualizado.getNome());
        Professor atualizado = professorRepository.save(existente);
        return toResponseDto(atualizado);
    }

    public ProfessorResponseDto updatePatchProfessor(Map<String, Object> updates, Long id) {
        Professor existente = professorRepository.findProfessorById(id)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor com o id " + id+ " não foi encontrado"));

        if(updates.containsKey("usuario")){
            existente.setUsuario(updates.get("usuario").toString());
        }
        if (updates.containsKey("nome")) {
            existente.setNome(updates.get("nome").toString());
        }
        if (updates.containsKey("senha")) {
            existente.setSenha(updates.get("senha").toString());
        }

        Professor atualizado = professorRepository.save(existente);
        return toResponseDto(atualizado);
    }
}

