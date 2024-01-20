package med.voli.medapi.domain.paciente.dto;

import med.voli.medapi.domain.paciente.Paciente;

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
