package com.joao_agenda.agendamento_medico_clinica.repository;

import com.joao_agenda.agendamento_medico_clinica.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("SELECT c FROM Consulta c " +
            "WHERE c.profissional.id = :profissionalId " +
            "AND c.status <> 'CANCELADA' " +
            "AND c.dataHora > :limiteInferior " +
            "AND c.dataHora < :limiteSuperior")

    List<Consulta> buscarConflitantes(
            @Param("profissionalId") Long profissionalId,
            @Param("limiteInferior") LocalDateTime limiteInferior,
            @Param("limiteSuperior") LocalDateTime limiteSuperior
    );

    @Query("SELECT c FROM Consulta c " +
            "WHERE c.profissional.id = :profissionalId " +
            "AND c.status <> 'CANCELADA' " +
            "AND c.dataHora > :limiteInferior " +
            "AND c.dataHora < :limiteSuperior " +
            "AND c.id <> :consultaId")
    List<Consulta> buscarConflitantesParaAtualizacao(
            @Param("profissionalId") Long profissionalId,
            @Param("limiteInferior") LocalDateTime limiteInferior,
            @Param("limiteSuperior") LocalDateTime limiteSuperior,
            @Param("consultaId") Long consultaId
    );
}
