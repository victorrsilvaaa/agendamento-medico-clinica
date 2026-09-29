package com.joao_agenda.agendamento_medico_clinica.controller;

import com.joao_agenda.agendamento_medico_clinica.dto.EspecialidadeRequestDTO;
import com.joao_agenda.agendamento_medico_clinica.dto.EspecialidadeResponseDTO;
import com.joao_agenda.agendamento_medico_clinica.service.EspecialidadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

    private final EspecialidadeService especialidadeService;

    public EspecialidadeController(EspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }

    @PostMapping
    public ResponseEntity<EspecialidadeResponseDTO> salvar(@RequestBody @Valid EspecialidadeRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(especialidadeService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<EspecialidadeResponseDTO>> listarTodos() {
        return ResponseEntity.ok(especialidadeService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadeResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(especialidadeService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadeResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid EspecialidadeRequestDTO dto) {
        return ResponseEntity.ok(especialidadeService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        especialidadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}