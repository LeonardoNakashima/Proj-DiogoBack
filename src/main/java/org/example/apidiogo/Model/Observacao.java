package org.example.apidiogo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Observacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Long id_professor;
    private Long id_aluno;

    public Observacao() {}

    public Observacao(Long id,String descricao, Long id_professor, Long id_aluno) {
        this.id = id;
        this.descricao = descricao;
        this.id_professor = id_professor;
        this.id_aluno = id_aluno;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId_professor() {
        return id_professor;
    }

    public void setId_professor(Long id_professor) {
        this.id_professor = id_professor;
    }

    public Long getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(Long id_aluno) {
        this.id_aluno = id_aluno;
    }
}
