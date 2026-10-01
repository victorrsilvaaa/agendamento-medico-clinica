package com.joao_agenda.agendamento_medico_clinica.service;

import com.joao_agenda.agendamento_medico_clinica.dto.ProfissionalRequestDTO;
import com.joao_agenda.agendamento_medico_clinica.dto.ProfissionalResponseDTO;
import com.joao_agenda.agendamento_medico_clinica.exception.RecursoNaoEncontradoException;
import com.joao_agenda.agendamento_medico_clinica.model.Especialidade;
import com.joao_agenda.agendamento_medico_clinica.model.Profissional;
import com.joao_agenda.agendamento_medico_clinica.repository.EspecialidadeRepository;
import com.joao_agenda.agendamento_medico_clinica.repository.ProfissionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;
    private final EspecialidadeRepository especialidadeRepository;

    public ProfissionalService(ProfissionalRepository profissionalRepository, EspecialidadeRepository especialidadeRepository) {
        this.profissionalRepository = profissionalRepository;
        this.especialidadeRepository = especialidadeRepository;
    }

    public ProfissionalResponseDTO salvar(ProfissionalRequestDTO dto) {
        Especialidade especialidade = especialidadeRepository.findById(dto.especialidadeId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Especialidade não encontrada"));

        Profissional profissional = new Profissional();
        profissional.setNome(dto.nome());
        profissional.setCpf(dto.cpf());
        profissional.setTelefone(dto.telefone());
        profissional.setEspecialidade(especialidade);

        Profissional salvo = profissionalRepository.save(profissional);
        return ProfissionalResponseDTO.fromEntity(salvo);
    }

    public List<ProfissionalResponseDTO> listarTodos() {
        return profissionalRepository.findAll()
                .stream()
                .map(ProfissionalResponseDTO::fromEntity)
                .toList();
    }

    public ProfissionalResponseDTO buscarPorId(Long id) {
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Profissional não encontrado"));
        return ProfissionalResponseDTO.fromEntity(profissional);
    }

    public ProfissionalResponseDTO atualizar(Long id, ProfissionalRequestDTO dto) {
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Profissional não encontrado"));

        Especialidade especialidade = especialidadeRepository.findById(dto.especialidadeId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Especialidade não encontrada"));

        profissional.setNome(dto.nome());
        profissional.setCpf(dto.cpf());
        profissional.setTelefone(dto.telefone());
        profissional.setEspecialidade(especialidade);

        Profissional atualizado = profissionalRepository.save(profissional);
        return ProfissionalResponseDTO.fromEntity(atualizado);
    }

    public void deletar(Long id) {
        if (!profissionalRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Profissional não encontrado");
        }
        profissionalRepository.deleteById(id);
    }
}