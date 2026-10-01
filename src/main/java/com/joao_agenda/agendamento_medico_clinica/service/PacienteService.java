package com.joao_agenda.agendamento_medico_clinica.service;

import com.joao_agenda.agendamento_medico_clinica.dto.PacienteRequestDTO;
import com.joao_agenda.agendamento_medico_clinica.dto.PacienteResponseDTO;
import com.joao_agenda.agendamento_medico_clinica.exception.RecursoNaoEncontradoException;
import com.joao_agenda.agendamento_medico_clinica.model.Paciente;
import com.joao_agenda.agendamento_medico_clinica.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteResponseDTO salvar(PacienteRequestDTO dto) {

        Paciente paciente = pacienteRepository.save(dto.toEntity());
        return PacienteResponseDTO.fromEntity(paciente);
    }

    public List<PacienteResponseDTO> listarTodos() {

        return pacienteRepository.findAll()
                .stream()
                .map(PacienteResponseDTO::fromEntity)
                .toList();

    }

    public PacienteResponseDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Paciente não encontrado"));

        return PacienteResponseDTO.fromEntity(paciente);
    }

    public PacienteResponseDTO atualizar(Long id, PacienteRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Paciente não encontrado"));

        paciente.setNome(dto.nome());
        paciente.setCpf(dto.cpf());
        paciente.setTelefone(dto.telefone());
        paciente.setEmail(dto.email());
        paciente.setDataNascimento(dto.dataNascimento());

        Paciente pacienteAtualizado = pacienteRepository.save(paciente);
        return PacienteResponseDTO.fromEntity(pacienteAtualizado);
    }

    public void deletar(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Paciente não encontrado");
        }
        pacienteRepository.deleteById(id);
    }
}