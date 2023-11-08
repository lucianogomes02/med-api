package med.voli.medapi.controller;

import jakarta.validation.Valid;
import med.voli.medapi.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid CadastroMedico cadastroMedico) {
        repository.save(new Medico(cadastroMedico));
    }

    @GetMapping
    public Page<MedicoJSON> buscarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(MedicoJSON::new);
    }

    @PutMapping
    @Transactional
    public void atualizarMedico(@RequestBody @Valid AtualizacaoMedico atualizacaoMedico) {
        var medico = repository.getReferenceById(atualizacaoMedico.id());
        medico.atualizar(atualizacaoMedico);
    }
}
