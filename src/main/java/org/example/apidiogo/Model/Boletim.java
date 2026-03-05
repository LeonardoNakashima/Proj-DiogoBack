package org.example.apidiogo.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Boletim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_disciplina;

    private Long id_aluno;

    private Double n1;

    private Double n2;

    private Double media;


    public Boletim() {
    }

    public Boletim(Long id, Long id_disciplina, Long id_aluno, Double n1, Double n2, Double media) {
        this.id = id;
        this.id_disciplina = id_disciplina;
        this.id_aluno = id_aluno;
        this.n1 = n1;
        this.n2 = n2;
        this.media = media;
    }

    public Long getId() {
        return id;
    }

    public Long getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(Long id_disciplina) {
        this.id_disciplina = id_disciplina;
    }

    public Long getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(Long id_aluno) {
        this.id_aluno = id_aluno;
    }

    public Double getN1() {
        return n1;
    }

    public void setN1(Double n1) {
        this.n1 = n1;
    }

    public Double getN2() {
        return n2;
    }

    public void setN2(Double n2) {
        this.n2 = n2;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public Double calcularMedia() {
        return this.media = (this.n1 + this.n2) / 2;
    }

}
