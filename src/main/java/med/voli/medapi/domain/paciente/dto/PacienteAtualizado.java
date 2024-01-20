package med.voli.medapi.domain.paciente.dto;

import med.voli.medapi.domain.paciente.Paciente;

import java.util.UUID;

public record PacienteAtualizado(UUID id) {
    public PacienteAtualizado(Paciente paciente) {
        this(paciente.getId());
    }
}
