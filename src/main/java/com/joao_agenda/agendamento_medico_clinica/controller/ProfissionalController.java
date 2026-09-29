package com.joao_agenda.agendamento_medico_clinica.controller;

import com.joao_agenda.agendamento_medico_clinica.dto.ProfissionalRequestDTO;
import com.joao_agenda.agendamento_medico_clinica.dto.ProfissionalResponseDTO;
import com.joao_agenda.agendamento_medico_clinica.service.ProfissionalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @PostMapping
    public ResponseEntity<ProfissionalResponseDTO> salvar(@RequestBody @Valid ProfissionalRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profissionalService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProfissionalResponseDTO>> listarTodos() {
        return ResponseEntity.ok(profissionalService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(profissionalService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ProfissionalRequestDTO dto) {
        return ResponseEntity.ok(profissionalService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        profissionalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}