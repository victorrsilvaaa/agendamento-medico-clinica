package com.joao_agenda.agendamento_medico_clinica.repository;

import com.joao_agenda.agendamento_medico_clinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository <Paciente, Long>  {


}
