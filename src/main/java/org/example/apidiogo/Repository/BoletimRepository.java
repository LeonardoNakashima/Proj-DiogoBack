package org.example.apidiogo.Repository;

import org.example.apidiogo.Model.Boletim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoletimRepository extends JpaRepository<Boletim, Long> {

    @Query(value = """
    SELECT a.matricula, a.nome, b.n1, b.n2, b.media
    FROM aluno a
    JOIN boletim b ON a.matricula = b.id_aluno
    """, nativeQuery = true)
    List<Object[]> listarAlunosComNotas();
}
