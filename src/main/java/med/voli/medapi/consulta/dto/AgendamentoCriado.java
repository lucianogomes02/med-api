package med.voli.medapi.consulta.dto;

import med.voli.medapi.consulta.domain.Consulta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record AgendamentoCriado(UUID id, UUID idPaciente, UUID idMedico, LocalDateTime data) {
    public AgendamentoCriado(Consulta consulta) {
        this(
            consulta.getId(),
            consulta.getPaciente().getId(),
            consulta.getMedico().getId(),
            consulta.getData()
        );
    }
}
