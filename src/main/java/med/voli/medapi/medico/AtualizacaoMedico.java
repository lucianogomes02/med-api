package med.voli.medapi.medico;

import jakarta.validation.constraints.NotNull;
import med.voli.medapi.endereco.EnderecoDTO;

import java.util.UUID;

public record AtualizacaoMedico(
        @NotNull
        UUID id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {
}
