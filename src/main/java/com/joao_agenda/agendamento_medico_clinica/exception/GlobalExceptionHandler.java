package com.joao_agenda.agendamento_medico_clinica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponseDTO> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        ErroResponseDTO erro = new ErroResponseDTO();
        erro.setTimestamp(LocalDateTime.now());
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        erro.setErro(HttpStatus.NOT_FOUND.getReasonPhrase());
        erro.setMensagem(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(ConflitoException.class)
    public ResponseEntity<ErroResponseDTO> tratarConflito(ConflitoException ex) {
        ErroResponseDTO erro = new ErroResponseDTO();
        erro.setTimestamp(LocalDateTime.now());
        erro.setStatus(HttpStatus.CONFLICT.value());
        erro.setErro(HttpStatus.CONFLICT.getReasonPhrase());
        erro.setMensagem(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponseDTO> tratarErroDeValidacao(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .findFirst()
                .orElse("Dados inválidos");

        ErroResponseDTO erro = new ErroResponseDTO();
        erro.setTimestamp(LocalDateTime.now());
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setErro(HttpStatus.BAD_REQUEST.getReasonPhrase());
        erro.setMensagem(mensagem);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}