package med.voli.medapi.paciente.dto;

import jakarta.validation.constraints.NotNull;
import med.voli.medapi.endereco.EnderecoDTO;

import java.util.UUID;

public record AtualizacaoPaciente(
        @NotNull
        UUID id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {
}
