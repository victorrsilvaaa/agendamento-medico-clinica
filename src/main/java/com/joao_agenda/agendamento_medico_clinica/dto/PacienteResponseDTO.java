package com.joao_agenda.agendamento_medico_clinica.dto;

import com.joao_agenda.agendamento_medico_clinica.model.Paciente;

import java.time.LocalDate;

public record PacienteResponseDTO(

        Long id,
        String nome,
        String cpf,
        String telefone,
        String email,
        LocalDate dataNascimento
) {
    public static PacienteResponseDTO fromEntity(Paciente paciente) {

        return new PacienteResponseDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEmail(),
                paciente.getDataNascimento()
        );
    }
}
