package com.joao_agenda.agendamento_medico_clinica.dto;

import com.joao_agenda.agendamento_medico_clinica.model.Paciente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PacienteRequestDTO(

        @NotBlank
        String nome,

        @NotBlank
        String cpf,

        @NotBlank
        String telefone,

        @Email
        @NotBlank
        String email,

        @NotNull
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
