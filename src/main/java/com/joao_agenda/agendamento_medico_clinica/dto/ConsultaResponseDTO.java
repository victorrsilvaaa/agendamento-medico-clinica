package com.joao_agenda.agendamento_medico_clinica.dto;

import com.joao_agenda.agendamento_medico_clinica.model.Consulta;

import java.time.LocalDateTime;

public record ConsultaResponseDTO(
        Long id,
        Long pacienteId,
        String pacienteNome,
        Long profissionalId,
        String profissionalNome,
        LocalDateTime dataHora,
        String status
) {
    public static ConsultaResponseDTO fromEntity(Consulta consulta) {
        return new ConsultaResponseDTO(
                consulta.getId(),
                consulta.getPaciente().getId(),
                consulta.getPaciente().getNome(),
                consulta.getProfissional().getId(),
                consulta.getProfissional().getNome(),
                consulta.getDataHora(),
                consulta.getStatus().name()
        );
    }
}