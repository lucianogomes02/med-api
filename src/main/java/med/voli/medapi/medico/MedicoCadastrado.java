package med.voli.medapi.medico;

import med.voli.medapi.endereco.Endereco;

import java.util.UUID;

public record MedicoCadastrado(
        UUID id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco
) {
    public MedicoCadastrado(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
