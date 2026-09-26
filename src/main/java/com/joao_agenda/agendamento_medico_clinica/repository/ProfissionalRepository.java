package com.joao_agenda.agendamento_medico_clinica.repository;

import com.joao_agenda.agendamento_medico_clinica.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}
