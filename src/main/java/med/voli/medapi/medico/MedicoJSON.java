package med.voli.medapi.medico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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
