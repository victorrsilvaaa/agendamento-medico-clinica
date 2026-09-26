package com.joao_agenda.agendamento_medico_clinica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String cpf;

    @NotBlank
    private String telefone;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    @Column(unique = true, nullable = false)
    private LocalDate dataNascimento;


}
