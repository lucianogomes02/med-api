package med.voli.medapi.medico.dto;

import med.voli.medapi.medico.domain.Medico;

import java.util.UUID;

public record MedicoAtualizado(UUID id) {
    public MedicoAtualizado(Medico medico) {
        this(medico.getId());
    }
}
