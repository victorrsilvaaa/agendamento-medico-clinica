package com.joao_agenda.agendamento_medico_clinica.dto;

import com.joao_agenda.agendamento_medico_clinica.model.Especialidade;
import jakarta.validation.constraints.NotBlank;

public record EspecialidadeRequestDTO(

        @NotBlank
        String nome
) {
    public Especialidade toEntity() {
        Especialidade especialidade = new Especialidade();
        especialidade.setNome(this.nome);
        return especialidade;
    }
}