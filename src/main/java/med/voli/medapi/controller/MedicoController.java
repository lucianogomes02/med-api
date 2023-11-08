package med.voli.medapi.controller;

import jakarta.validation.Valid;
import med.voli.medapi.medico.Medico;
import med.voli.medapi.medico.MedicoDTO;
import med.voli.medapi.medico.MedicoJSON;
import med.voli.medapi.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid MedicoDTO medicoDTO) {
        repository.save(new Medico(medicoDTO));
    }

    @GetMapping
    public List<MedicoJSON> buscarMedicos() {
        return repository.findAll().stream().map(MedicoJSON::new).toList();
    }
}
