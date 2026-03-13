package org.example.apidiogo.Service;

import ch.qos.logback.core.pattern.util.AlmostAsIsEscapeUtil;
import jakarta.validation.Valid;
import org.example.apidiogo.Dto.*;
import org.example.apidiogo.Exception.AdminNotFoundException;
import org.example.apidiogo.Exception.AlunoNotFoundException;
import org.example.apidiogo.Model.Admin;
import org.example.apidiogo.Model.Aluno;
import org.example.apidiogo.Repository.AlunoRepository;
import org.example.apidiogo.Repository.BoletimRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final BoletimRepository boletimRepository;
    private final PasswordEncoder passwordEncoder;

    public AlunoService(AlunoRepository alunoRepository, PasswordEncoder passwordEncoder, BoletimRepository boletimRepository) {
        this.alunoRepository = alunoRepository;
        this.passwordEncoder = passwordEncoder;
        this.boletimRepository = boletimRepository;
    }


    private Aluno fromRequestDTO(AlunoRequestDto dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        aluno.setSenha(senhaCriptografada);
        return aluno;
    }

    private AlunoResponseDto toResponseDto(Aluno aluno) {
        return new AlunoResponseDto(
                aluno.getMatricula(),
                aluno.getNome(),
                aluno.getEmail()
        );
    }



    public List<AlunoResponseDto> listAll() {
        return alunoRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }


    public List<AlunoResponseDto> listByMatricula(Long matricula) {
        Optional<Aluno> aluno = alunoRepository.findAlunoByMatricula(matricula);
        return aluno.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());    }

    public List<AlunoResponseDto> listByEmail(String email) {
        Optional<Aluno> aluno = alunoRepository.findAlunoByEmail(email);
        return aluno.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());    }

    public BoletimAlunoDto listarBoletimAluno(Long matricula) {
        List<Object[]> resultado = boletimRepository.listarAlunosComNotas(matricula);
        if (resultado.isEmpty()) {
            throw new AlunoNotFoundException(
                    "Nenhuma nota encontrada para o aluno de matrícula " + matricula
            );
        }
        String nomeAluno = (String) resultado.get(0)[1];
        List<DisciplinaNotaDto> disciplinas = resultado.stream()
                .map(r -> new DisciplinaNotaDto(
                        (String) r[2],
                        ((Number) r[3]).doubleValue(),
                        ((Number) r[4]).doubleValue(),
                        ((Number) r[5]).doubleValue()
                ))
                .toList();
        return new BoletimAlunoDto(matricula, nomeAluno, disciplinas);
    }

    public AlunoResponseDto createAluno(AlunoRequestDto dto) {
        Aluno aluno = fromRequestDTO(dto);
        Aluno salvo = alunoRepository.save(aluno);
        return toResponseDto(salvo);
    }


    public void deleteAluno(Long matricula) {
        Aluno aluno = alunoRepository.findAlunoByMatricula(matricula)
                .orElseThrow(() -> new AlunoNotFoundException("Aluno com matricula" + matricula +" não foi encontrado: 404"));
        alunoRepository.delete(aluno);
    }

    public AlunoResponseDto updateAluno(@Valid AlunoRequestDto alunoAtualizado, Long matricula) {
        Aluno existente = alunoRepository.findAlunoByMatricula(matricula)
                .orElseThrow(() -> new AlunoNotFoundException("Aluno com a matricula " + matricula + " não encontrado"));
        existente.setEmail(alunoAtualizado.getEmail());
        existente.setEmail(alunoAtualizado.getNome());
        existente.setSenha(alunoAtualizado.getSenha());

        Aluno atualizado = alunoRepository.save(existente);
        return toResponseDto(atualizado);

    }

    public AlunoResponseDto updatePatchAluno(Map<String, Object> updates, Long matricula) {
        Aluno existente = alunoRepository.findAlunoByMatricula(matricula)
                .orElseThrow(() -> new AlunoNotFoundException("Aluno com a matricula " + matricula+ " não foi encontrado"));

        if(updates.containsKey("email")){
            existente.setEmail(updates.get("email").toString());
        }
        if (updates.containsKey("nome")) {
            existente.setNome(updates.get("nome").toString());
        }
        if (updates.containsKey("senha")) {
            existente.setSenha(updates.get("senha").toString());
        }

        Aluno atualizado = alunoRepository.save(existente);
        return toResponseDto(atualizado);
    }

}
