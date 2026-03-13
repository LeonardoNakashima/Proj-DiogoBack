package org.example.apidiogo.Repository;

import org.example.apidiogo.Model.Boletim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoletimRepository extends JpaRepository<Boletim, Long> {

    @Query(value = """
SELECT a.matricula,
       a.nome,
       d.nome,
       b.n1,
       b.n2,
       b.media
FROM aluno a
JOIN boletim b ON a.matricula = b.id_aluno
JOIN disciplina d ON d.id = b.id_disciplina
WHERE a.matricula = :matricula
""", nativeQuery = true)

    List<Object[]> listarAlunosComNotas(@Param("matricula") Long matricula);
}
