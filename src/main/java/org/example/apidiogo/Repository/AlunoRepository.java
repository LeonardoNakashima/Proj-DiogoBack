package org.example.apidiogo.Repository;

import org.example.apidiogo.Model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findAlunoByMatricula(Long Matricula);

    Optional<Aluno> findAlunoByEmail(String Email);
}
