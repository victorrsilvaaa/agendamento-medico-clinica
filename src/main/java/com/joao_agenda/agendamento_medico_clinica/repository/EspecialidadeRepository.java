package com.joao_agenda.agendamento_medico_clinica.repository;

import com.joao_agenda.agendamento_medico_clinica.model.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
}
