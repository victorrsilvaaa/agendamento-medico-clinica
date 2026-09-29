package com.joao_agenda.agendamento_medico_clinica.dto;

import com.joao_agenda.agendamento_medico_clinica.model.Paciente;

import java.time.LocalDate;

public record PacienteRequestDTO(

        String nome,
        String cpf,
        String telefone,
        String email,
        LocalDate dataNascimento
) {
    public Paciente toEntity() {
        Paciente paciente = new Paciente();
        paciente.setNome(this.nome);
        paciente.setCpf(this.cpf);
        paciente.setTelefone(this.telefone);
        paciente.setEmail(this.email);
        paciente.setDataNascimento(this.dataNascimento);
        return paciente;
    }
}
