package org.example.apidiogo.Repository;

import org.example.apidiogo.Model.Observacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ObservacaoRepository extends JpaRepository<Observacao, Long> {

    Optional<Observacao>findObservacaoById(Long id);
}
