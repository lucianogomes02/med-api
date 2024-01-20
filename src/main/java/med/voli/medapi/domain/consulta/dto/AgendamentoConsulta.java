package med.voli.medapi.domain.consulta.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voli.medapi.domain.medico.Especialidade;

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
