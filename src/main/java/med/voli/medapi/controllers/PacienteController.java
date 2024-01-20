package med.voli.medapi.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voli.medapi.domain.paciente.Paciente;
import med.voli.medapi.domain.paciente.dto.AtualizacaoPaciente;
import med.voli.medapi.domain.paciente.dto.CadastroPaciente;
import med.voli.medapi.domain.paciente.dto.PacienteAtualizado;
import med.voli.medapi.domain.paciente.dto.PacienteJSON;
import med.voli.medapi.repository.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity post(@RequestBody @Valid CadastroPaciente cadastroPaciente, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(cadastroPaciente);
        repository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new PacienteJSON(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteJSON>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(PacienteJSON::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable UUID id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new PacienteJSON(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity put(@RequestBody @Valid AtualizacaoPaciente atualizacaoPaciente) {
        var paciente = repository.getReferenceById(atualizacaoPaciente.id());
        paciente.atualizar(atualizacaoPaciente);

        return ResponseEntity.ok(new PacienteAtualizado(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable UUID id) {
        var paciente = repository.getReferenceById(id);
        paciente.desabilitar();

        return ResponseEntity.noContent().build();
    }
}
