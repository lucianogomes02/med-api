package med.voli.medapi.medico.controller;

import jakarta.validation.Valid;
import med.voli.medapi.medico.domain.Medico;
import med.voli.medapi.medico.dto.AtualizacaoMedico;
import med.voli.medapi.medico.dto.CadastroMedico;
import med.voli.medapi.medico.dto.MedicoAtualizado;
import med.voli.medapi.medico.dto.MedicoJSON;
import med.voli.medapi.medico.repository.MedicoRepository;
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
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity post(@RequestBody @Valid CadastroMedico cadastroMedico, UriComponentsBuilder uriBuilder) {
        var medico =new Medico(cadastroMedico);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicoJSON(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoJSON>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(MedicoJSON::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable UUID id) {
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new MedicoJSON(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity put(@RequestBody @Valid AtualizacaoMedico atualizacaoMedico) {
        var medico = repository.getReferenceById(atualizacaoMedico.id());
        medico.atualizar(atualizacaoMedico);

        return ResponseEntity.ok(new MedicoAtualizado(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable UUID id) {
        var medico = repository.getReferenceById(id);
        medico.desabilitar();

        return ResponseEntity.noContent().build();
    }
}
