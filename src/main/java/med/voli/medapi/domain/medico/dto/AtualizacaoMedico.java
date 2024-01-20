package med.voli.medapi.domain.medico.dto;

import jakarta.validation.constraints.NotNull;
import med.voli.medapi.domain.endereco.EnderecoDTO;

import java.util.UUID;

public record AtualizacaoMedico(
        @NotNull
        UUID id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {
}
