package com.joao_agenda.agendamento_medico_clinica.repository;

import com.joao_agenda.agendamento_medico_clinica.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
