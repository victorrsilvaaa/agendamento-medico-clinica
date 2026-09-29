package com.joao_agenda.agendamento_medico_clinica.dto;

import com.joao_agenda.agendamento_medico_clinica.model.Profissional;

public record ProfissionalResponseDTO(
        Long id,
        String nome,
        String cpf,
        String telefone,
        Long especialidadeId,
        String especialidadeNome
) {
    public static ProfissionalResponseDTO fromEntity(Profissional profissional) {
        return new ProfissionalResponseDTO(
                profissional.getId(),
                profissional.getNome(),
                profissional.getCpf(),
                profissional.getTelefone(),
                profissional.getEspecialidade().getId(),
                profissional.getEspecialidade().getNome()
        );
    }
}