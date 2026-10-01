package com.joao_agenda.agendamento_medico_clinica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joao_agenda.agendamento_medico_clinica.dto.EspecialidadeRequestDTO;
import com.joao_agenda.agendamento_medico_clinica.dto.ConsultaRequestDTO;
import com.joao_agenda.agendamento_medico_clinica.dto.PacienteRequestDTO;
import com.joao_agenda.agendamento_medico_clinica.dto.ProfissionalRequestDTO;
import com.joao_agenda.agendamento_medico_clinica.model.Especialidade;
import com.joao_agenda.agendamento_medico_clinica.model.Paciente;
import com.joao_agenda.agendamento_medico_clinica.model.Profissional;
import com.joao_agenda.agendamento_medico_clinica.repository.ConsultaRepository;
import com.joao_agenda.agendamento_medico_clinica.repository.EspecialidadeRepository;
import com.joao_agenda.agendamento_medico_clinica.repository.PacienteRepository;
import com.joao_agenda.agendamento_medico_clinica.repository.ProfissionalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
class ApiIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        consultaRepository.deleteAll();
        profissionalRepository.deleteAll();
        pacienteRepository.deleteAll();
        especialidadeRepository.deleteAll();
    }

    @Test
    @WithMockUser
    void deveCriarEspecialidade() throws Exception {
        EspecialidadeRequestDTO dto = new EspecialidadeRequestDTO("Cardiologia");

        mockMvc.perform(post("/especialidades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Cardiologia"));
    }

    @Test
    @WithMockUser
    void deveCriarPaciente() throws Exception {
        PacienteRequestDTO dto = new PacienteRequestDTO(
                "Maria Silva",
                "12345678900",
                "11999999999",
                "maria@email.com",
                LocalDate.of(1990, 5, 10)
        );

        mockMvc.perform(post("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Maria Silva"))
                .andExpect(jsonPath("$.cpf").value("12345678900"));
    }

    @Test
    @WithMockUser
    void deveCriarProfissional() throws Exception {
        Especialidade especialidade = new Especialidade();
        especialidade.setNome("Ortopedia");
        especialidade = especialidadeRepository.save(especialidade);

        ProfissionalRequestDTO dto = new ProfissionalRequestDTO(
                "Dr. João",
                "98765432100",
                "11888888888",
                especialidade.getId()
        );

        mockMvc.perform(post("/profissionais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Dr. João"))
                .andExpect(jsonPath("$.especialidadeId").value(especialidade.getId()));
    }

    @Test
    @WithMockUser
    void deveCriarConsultaECancelar() throws Exception {
        Especialidade especialidade = especialidadeRepository.save(new Especialidade(null, "Cardiologia"));

        Paciente paciente = pacienteRepository.save(new Paciente(null, "Ana", "11122233344", "11999999999", "ana@email.com", LocalDate.of(1988, 3, 20)));
        Profissional profissional = profissionalRepository.save(new Profissional(null, "Dr. Carlos", "44455566677", "11777777777", especialidade));

        ConsultaRequestDTO dto = new ConsultaRequestDTO(
                paciente.getId(),
                profissional.getId(),
                LocalDateTime.of(2026, 10, 10, 15, 30)
        );

        String response = mockMvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("AGENDADA"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long consultaId = objectMapper.readTree(response).get("id").asLong();

        mockMvc.perform(patch("/consultas/{id}/cancelar", consultaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CANCELADA"));
    }
}
