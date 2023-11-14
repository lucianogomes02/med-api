package med.voli.medapi.paciente.dto;

import med.voli.medapi.paciente.domain.Paciente;

import java.util.UUID;

public record PacienteAtualizado(UUID id) {
    public PacienteAtualizado(Paciente paciente) {
        this(paciente.getId());
    }
}
