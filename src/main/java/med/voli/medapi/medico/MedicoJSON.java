package med.voli.medapi.medico;

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
