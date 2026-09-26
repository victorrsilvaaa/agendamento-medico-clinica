package com.joao_agenda.agendamento_medico_clinica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String cpf;

    @NotBlank
    @Column(nullable = false)
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "especialidade_id" , nullable = false)
    private Especialidade especialidade;
}
