package com.joao_agenda.agendamento_medico_clinica.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaRequestDTO(
        @NotNull
        Long pacienteId,

        @NotNull
        Long profissionalId,

        @NotNull
        LocalDateTime dataHora
) {}