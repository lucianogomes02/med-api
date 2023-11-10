package med.voli.medapi.pacientes.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voli.medapi.endereco.Endereco;
import med.voli.medapi.pacientes.dto.AtualizacaoPaciente;
import med.voli.medapi.pacientes.dto.CadastroPaciente;

import java.util.UUID;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Paciente(CadastroPaciente cadastroPaciente) {
        this.nome = cadastroPaciente.nome();
        this.email = cadastroPaciente.email();
        this.telefone = cadastroPaciente.telefone();
        this.cpf = cadastroPaciente.cpf();
        this.ativo = true;
        this.endereco = new Endereco(cadastroPaciente.endereco());
    }

    public void atualizar(AtualizacaoPaciente atualizacaoPaciente) {
        if (atualizacaoPaciente.nome() != null) {
            this.nome = atualizacaoPaciente.nome();
        }
        if (atualizacaoPaciente.telefone() != null) {
            this.telefone = atualizacaoPaciente.telefone();
        }
        if (atualizacaoPaciente.endereco() != null) {
            this.endereco.atualizarInformacoes(atualizacaoPaciente.endereco());
        }
    }

    public void desabilitar() {
        this.ativo = false;
    }
}
