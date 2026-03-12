package org.example.apidiogo.Service;
import jakarta.validation.Valid;
import org.example.apidiogo.Dto.*;
import org.example.apidiogo.Exception.BoletimNotFoundException;
import org.example.apidiogo.Exception.DisciplinaNotFoundException;
import org.example.apidiogo.Exception.ObservacaoNotFoundException;
import org.example.apidiogo.Model.Boletim;
import org.example.apidiogo.Model.Disciplina;
import org.example.apidiogo.Model.Observacao;
import org.example.apidiogo.Repository.ObservacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ObservacaoService {

    private final ObservacaoRepository observacaoRepository;
    public ObservacaoService(ObservacaoRepository observacaoRepository) {
        this.observacaoRepository = observacaoRepository;
    }

    private Observacao fromRequestDTO(ObservacaoRequestDto dto) {
        Observacao observacao = new Observacao();
        observacao.setDescricao(dto.getDescricao());
        observacao.setIdProfessor(dto.getId_professor());
        observacao.setIdAluno(dto.getId_aluno());
        return observacao;
    }

    private ObservacaoResponseDto toResponseDto(Observacao observacao) {
        return new ObservacaoResponseDto(
                observacao.getId(),
                observacao.getDescricao(),
                observacao.getIdProfessor(),
                observacao.getIdAluno()
        );
    }

    public List<ObservacaoResponseDto> listAll() {
        return observacaoRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<ObservacaoResponseDto> listById(Long id) {
        Optional<Observacao> observacao = observacaoRepository.findById(id);
        return observacao.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<ObservacaoResponseDto> listByIdAluno(Long idAluno) {
        Optional<Observacao> observacao = observacaoRepository.findObservacaoByIdAluno(idAluno);
        return observacao.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public ObservacaoResponseDto createObservacao(ObservacaoRequestDto dto) {
        Observacao observacao = fromRequestDTO(dto);
        Observacao salvo = observacaoRepository.save(observacao);
        return toResponseDto(salvo);
    }

    public void deleteObservacao(Long id) {
        Observacao observacao = observacaoRepository.findById(id)
                .orElseThrow(() -> new ObservacaoNotFoundException("Observacao com id " + id +" não foi encontrado: 404"));
        observacaoRepository.delete(observacao);
    }

    public ObservacaoResponseDto updateObservacao(@Valid ObservacaoRequestDto observacaoAtualizado, Long id) {
        Observacao existente = observacaoRepository.findObservacaoById(id)
                .orElseThrow(() -> new ObservacaoNotFoundException("Observacao com o id " + id + " não encontrado"));
        existente.setDescricao(observacaoAtualizado.getDescricao());
        existente.setIdProfessor(observacaoAtualizado.getId_professor());
        existente.setIdAluno(observacaoAtualizado.getId_aluno());
        Observacao atualizado = observacaoRepository.save(existente);
        return toResponseDto(atualizado);
    }

    public ObservacaoResponseDto updatePatchObservacao(Map<String, Object> updates, Long id) {
        Observacao existente = observacaoRepository.findById(id)
                .orElseThrow(() -> new ObservacaoNotFoundException("observação com o id " + id+ " não foi encontrado"));

        if(updates.containsKey("id_aluno")){
            existente.setIdAluno(Long.valueOf(updates.get("idAluno").toString()));
        }
        if (updates.containsKey("id_professor")) {
            existente.setIdProfessor(Long.valueOf(updates.get("idProfessor").toString()));
        }
        if (updates.containsKey("descricao")) {
            existente.setDescricao(updates.get("descricao").toString());
        }
        Observacao atualizado = observacaoRepository.save(existente);
        return toResponseDto(atualizado);
    }
}
