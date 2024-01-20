package med.voli.medapi.domain.medico.dto;

import med.voli.medapi.domain.medico.Medico;

import java.util.UUID;

public record MedicoAtualizado(UUID id) {
    public MedicoAtualizado(Medico medico) {
        this(medico.getId());
    }
}
