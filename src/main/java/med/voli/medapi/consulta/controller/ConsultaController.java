package med.voli.medapi.consulta.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voli.medapi.consulta.dto.AgendamentoConsulta;
import med.voli.medapi.consulta.dto.AgendamentoCriado;
import med.voli.medapi.consulta.dto.DetalhamentoConsulta;
import med.voli.medapi.consulta.exceptions.EspecialidadeObrigatoria;
import med.voli.medapi.consulta.service.AgendaDeConsultas;
import med.voli.medapi.consulta.exceptions.EntidadeInexistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity<AgendamentoCriado> post(@RequestBody @Valid AgendamentoConsulta agendamentoConsulta) throws EntidadeInexistente, EspecialidadeObrigatoria {
        var agendamento = agendaDeConsultas.agendar(agendamentoConsulta);
        return ResponseEntity.ok(agendamento);
    }

}
