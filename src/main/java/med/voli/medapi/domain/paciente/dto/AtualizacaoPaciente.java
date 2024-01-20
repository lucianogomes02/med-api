package med.voli.medapi.domain.paciente.dto;

import jakarta.validation.constraints.NotNull;
import med.voli.medapi.domain.endereco.EnderecoDTO;

import java.util.UUID;

public record AtualizacaoPaciente(
        @NotNull
        UUID id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {
}
