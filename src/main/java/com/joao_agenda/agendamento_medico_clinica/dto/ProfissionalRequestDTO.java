package com.joao_agenda.agendamento_medico_clinica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfissionalRequestDTO(
        @NotBlank
        String nome,

        @NotBlank
        String cpf,

        @NotBlank
        String telefone,

        @NotNull
        Long especialidadeId
) {}