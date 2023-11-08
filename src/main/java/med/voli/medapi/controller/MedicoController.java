package med.voli.medapi.controller;

import jakarta.validation.Valid;
import med.voli.medapi.medico.Medico;
import med.voli.medapi.medico.MedicoDTO;
import med.voli.medapi.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
