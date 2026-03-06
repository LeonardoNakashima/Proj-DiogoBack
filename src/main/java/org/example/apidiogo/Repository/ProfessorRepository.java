package org.example.apidiogo.Repository;

import org.example.apidiogo.Dto.ProfessorResponseDto;
import org.example.apidiogo.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findProfessorByUsuario(String usuario);

    Optional<Professor> findProfessorById(Long id);

    @Query("""
       SELECT p
       FROM Professor p
       JOIN FETCH p.disciplina
       """)
    List<Professor> listProfessorWithDisciplina();

    @Query("""
       SELECT p
       FROM Professor p
       JOIN FETCH p.disciplina
       WHERE p.id = :id
       """)
    Optional<Professor> listProfessorWithDisciplina(@Param("id") Long id);

}

