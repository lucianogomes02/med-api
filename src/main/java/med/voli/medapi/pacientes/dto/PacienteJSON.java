package med.voli.medapi.pacientes.dto;

import med.voli.medapi.pacientes.domain.Paciente;

import java.util.UUID;

public record PacienteJSON(
        UUID id,
        String nome,
        String email,
        String telefone,
        String cpf
) {
    public PacienteJSON(Paciente medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCpf()
        );

    }
}
