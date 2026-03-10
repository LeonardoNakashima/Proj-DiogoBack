package org.example.apidiogo.Dto;

import jakarta.validation.Valid;

public class BoletimRequestDto {

    @Valid
    private Long id_disciplina;

    @Valid
    private Long id_aluno;

    @Valid
    private Double n1;

    @Valid
    private Double n2;

    @Valid
    private Double media;

    public @Valid Long getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(@Valid Long id_disciplina) {
        this.id_disciplina = id_disciplina;
    }

    public @Valid Long getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(@Valid Long id_aluno) {
        this.id_aluno = id_aluno;
    }

    public @Valid Double getN1() {
        return n1;
    }

    public void setN1(@Valid Double n1) {
        this.n1 = n1;
    }

    public @Valid Double getN2() {
        return n2;
    }

    public void setN2(@Valid Double n2) {
        this.n2 = n2;
    }

    public @Valid Double getMedia() {
        return media;
    }

    public void setMedia(@Valid Double media) {
        this.media = n2 + n1 / 2;
    }
}