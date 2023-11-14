package med.voli.medapi.consulta.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voli.medapi.medico.domain.Especialidade;

import java.time.LocalDateTime;
import java.util.UUID;

public record AgendamentoConsulta(
        UUID idMedico,

        @NotNull
        UUID idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade
) {
}
