package med.voli.medapi.pacientes.dto;

import med.voli.medapi.pacientes.domain.Paciente;

import java.util.UUID;

public record PacienteAtualizado(UUID id) {
    public PacienteAtualizado(Paciente paciente) {
        this(paciente.getId());
    }
}
