package med.voli.medapi.domain.medico.dto;

import med.voli.medapi.domain.medico.Especialidade;
import med.voli.medapi.domain.medico.Medico;

import java.util.UUID;

public record MedicoJSON(
        UUID id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade
) {
    public MedicoJSON(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCrm(),
                medico.getEspecialidade()
        );

    }
}
