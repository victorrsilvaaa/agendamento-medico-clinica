package com.joao_agenda.agendamento_medico_clinica.dto;

import com.joao_agenda.agendamento_medico_clinica.model.Especialidade;

public record EspecialidadeResponseDTO(

        Long id,
        String nome
) {
    public static EspecialidadeResponseDTO fromEntity(Especialidade especialidade) {
        return new EspecialidadeResponseDTO(especialidade.getId(), especialidade.getNome());
    }
}