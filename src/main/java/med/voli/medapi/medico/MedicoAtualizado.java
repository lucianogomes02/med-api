package med.voli.medapi.medico;

import java.util.UUID;

public record MedicoAtualizado(UUID id) {
    public MedicoAtualizado(Medico medico) {
        this(medico.getId());
    }
}
