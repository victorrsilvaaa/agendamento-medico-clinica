package com.joao_agenda.agendamento_medico_clinica.service;

import com.joao_agenda.agendamento_medico_clinica.dto.EspecialidadeRequestDTO;
import com.joao_agenda.agendamento_medico_clinica.dto.EspecialidadeResponseDTO;
import com.joao_agenda.agendamento_medico_clinica.model.Especialidade;
import com.joao_agenda.agendamento_medico_clinica.repository.EspecialidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadeService {

    private final EspecialidadeRepository especialidadeRepository;

    public EspecialidadeService(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = especialidadeRepository;
    }

    public EspecialidadeResponseDTO salvar(EspecialidadeRequestDTO dto) {
        Especialidade especialidade = especialidadeRepository.save(dto.toEntity());
        return EspecialidadeResponseDTO.fromEntity(especialidade);
    }

    public List<EspecialidadeResponseDTO> listarTodos() {
        return especialidadeRepository.findAll()
                .stream()
                .map(EspecialidadeResponseDTO::fromEntity)
                .toList();
    }

    public EspecialidadeResponseDTO buscarPorId(Long id) {
        Especialidade especialidade = especialidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));
        return EspecialidadeResponseDTO.fromEntity(especialidade);
    }

    public EspecialidadeResponseDTO atualizar(Long id, EspecialidadeRequestDTO dto) {
        Especialidade especialidade = especialidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));

        especialidade.setNome(dto.nome());

        Especialidade atualizada = especialidadeRepository.save(especialidade);
        return EspecialidadeResponseDTO.fromEntity(atualizada);
    }

    public void deletar(Long id) {
        if (!especialidadeRepository.existsById(id)) {
            throw new RuntimeException("Especialidade não encontrada");
        }
        especialidadeRepository.deleteById(id);
    }
}