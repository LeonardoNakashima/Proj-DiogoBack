package org.example.apidiogo.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Observacoes")
public class Observacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @Column(name = "id_professor")
    private Long idProfessor;
    @Column(name = "id_aluno")
    private Long idAluno;

    public Observacao() {}

    public Observacao(Long id,String descricao, Long idProfessor, Long idAluno) {
        this.id = id;
        this.descricao = descricao;
        this.idProfessor = idProfessor;
        this.idAluno = idAluno;
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

    public Long getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Long idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }
}
