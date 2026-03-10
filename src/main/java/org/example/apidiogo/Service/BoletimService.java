package org.example.apidiogo.Service;

import jakarta.validation.Valid;
import org.example.apidiogo.Dto.*;
import org.example.apidiogo.Exception.AdminNotFoundException;
import org.example.apidiogo.Exception.AlunoNotFoundException;
import org.example.apidiogo.Exception.BoletimNotFoundException;
import org.example.apidiogo.Exception.ProfessorNotFoundException;
import org.example.apidiogo.Model.*;
import org.example.apidiogo.Repository.BoletimRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoletimService {

    private final BoletimRepository boletimRepository;

    public BoletimService(BoletimRepository boletimRepository) {
        this.boletimRepository = boletimRepository;
    }

    private Boletim fromRequestDTO(BoletimRequestDto dto) {
        Boletim boletim = new Boletim();
        boletim.setId_aluno(dto.getId_aluno());
        boletim.setN1(dto.getN1());
        boletim.setN2(dto.getN2());
        boletim.setId_disciplina(dto.getId_disciplina());
        boletim.setMedia(boletim.calcularMedia());
        return boletim;
    }

    private BoletimResponseDto toResponseDto(Boletim boletim) {
        return new BoletimResponseDto(
                boletim.getId(),
                boletim.getId_disciplina(),
                boletim.getId_aluno(),
                boletim.getN1(),
                boletim.getN2(),
                boletim.getMedia()
        );
    }


    public List<BoletimResponseDto> listAll() {
        return boletimRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<BoletimResponseDto> listById(Long id) {
        Optional<Boletim> boletim = boletimRepository.findById(id);
        return boletim.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public BoletimResponseDto createBoletim(BoletimRequestDto dto) {
        Boletim boletim = fromRequestDTO(dto);
        Boletim salvo = boletimRepository.save(boletim);
        return toResponseDto(salvo);
    }

    public void deleteBoletim(Long id) {
        Boletim boletim = boletimRepository.findById(id)
                .orElseThrow(() -> new BoletimNotFoundException("Boletim com id " + id +" não foi encontrado: 404"));
        boletimRepository.delete(boletim);
    }

    public BoletimResponseDto updateBoletim(@Valid BoletimRequestDto boletimAtualizado, Long id) {
        Boletim existente = boletimRepository.findById(id)
                .orElseThrow(() -> new BoletimNotFoundException("Boletim com o id " + id + " não encontrado"));
        existente.setId_disciplina(boletimAtualizado.getId_disciplina());
        existente.setMedia(boletimAtualizado.getMedia());
        existente.setN1(boletimAtualizado.getN1());
        existente.setN2(boletimAtualizado.getN2());
        existente.setId_aluno(boletimAtualizado.getId_aluno());

        Boletim atualizado = boletimRepository.save(existente);
        return toResponseDto(atualizado);
    }

    public BoletimResponseDto updatePatchBoletim(Map<String, Object> updates, Long id) {
        Boletim existente = boletimRepository.findById(id)
                .orElseThrow(() -> new BoletimNotFoundException("Boletim com o id " + id+ " não foi encontrado"));

        if(updates.containsKey("id_aluno")){
            existente.setId_aluno(Long.valueOf(updates.get("id_aluno").toString()));
        }
        if (updates.containsKey("n2")) {
            existente.setN2(Double.valueOf(updates.get("n2").toString()));
        }
        if (updates.containsKey("n1")) {
            existente.setN1(Double.valueOf(updates.get("n1").toString()));
        }
        if (updates.containsKey("media")) {
            existente.setMedia(Double.valueOf(updates.get("media").toString()));
        }
        Boletim atualizado = boletimRepository.save(existente);
        return toResponseDto(atualizado);
    }
}
