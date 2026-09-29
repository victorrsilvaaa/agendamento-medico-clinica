package com.joao_agenda.agendamento_medico_clinica.service;

import com.joao_agenda.agendamento_medico_clinica.dto.ConsultaRequestDTO;
import com.joao_agenda.agendamento_medico_clinica.dto.ConsultaResponseDTO;
import com.joao_agenda.agendamento_medico_clinica.model.Consulta;
import com.joao_agenda.agendamento_medico_clinica.model.Paciente;
import com.joao_agenda.agendamento_medico_clinica.model.Profissional;
import com.joao_agenda.agendamento_medico_clinica.model.StatusConsulta;
import com.joao_agenda.agendamento_medico_clinica.repository.ConsultaRepository;
import com.joao_agenda.agendamento_medico_clinica.repository.PacienteRepository;
import com.joao_agenda.agendamento_medico_clinica.repository.ProfissionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalRepository profissionalRepository;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, ProfissionalRepository profissionalRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.profissionalRepository = profissionalRepository;
    }

    public ConsultaResponseDTO salvar(ConsultaRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Profissional profissional = profissionalRepository.findById(dto.profissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setProfissional(profissional);
        consulta.setDataHora(dto.dataHora());
        consulta.setStatus(StatusConsulta.AGENDADA);

        Consulta salva = consultaRepository.save(consulta);
        return ConsultaResponseDTO.fromEntity(salva);
    }

    public List<ConsultaResponseDTO> listarTodos() {
        return consultaRepository.findAll()
                .stream()
                .map(ConsultaResponseDTO::fromEntity)
                .toList();
    }

    public ConsultaResponseDTO buscarPorId(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        return ConsultaResponseDTO.fromEntity(consulta);
    }

    public ConsultaResponseDTO atualizar(Long id, ConsultaRequestDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Profissional profissional = profissionalRepository.findById(dto.profissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        consulta.setPaciente(paciente);
        consulta.setProfissional(profissional);
        consulta.setDataHora(dto.dataHora());

        Consulta atualizada = consultaRepository.save(consulta);
        return ConsultaResponseDTO.fromEntity(atualizada);
    }

    public ConsultaResponseDTO cancelar(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        consulta.setStatus(StatusConsulta.CANCELADA);

        Consulta atualizada = consultaRepository.save(consulta);
        return ConsultaResponseDTO.fromEntity(atualizada);
    }

    public void deletar(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new RuntimeException("Consulta não encontrada");
        }
        consultaRepository.deleteById(id);
    }
}