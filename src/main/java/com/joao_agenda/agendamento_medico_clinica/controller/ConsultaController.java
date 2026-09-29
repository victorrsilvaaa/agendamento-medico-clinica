package com.joao_agenda.agendamento_medico_clinica.controller;

import com.joao_agenda.agendamento_medico_clinica.dto.ConsultaRequestDTO;
import com.joao_agenda.agendamento_medico_clinica.dto.ConsultaResponseDTO;
import com.joao_agenda.agendamento_medico_clinica.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> salvar(@RequestBody @Valid ConsultaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ConsultaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(consultaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ConsultaRequestDTO dto) {
        return ResponseEntity.ok(consultaService.atualizar(id, dto));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ConsultaResponseDTO> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.cancelar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        consultaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}