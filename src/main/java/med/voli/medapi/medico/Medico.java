package med.voli.medapi.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voli.medapi.endereco.Endereco;

import java.util.UUID;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(CadastroMedico cadastroMedico) {
        this.nome = cadastroMedico.nome();
        this.email = cadastroMedico.email();
        this.telefone = cadastroMedico.telefone();
        this.crm = cadastroMedico.crm();
        this.especialidade = cadastroMedico.especialidade();
        this.endereco = new Endereco(cadastroMedico.endereco());
    }

    public void atualizar(AtualizacaoMedico atualizacaoMedico) {
        if (atualizacaoMedico.nome() != null) {
            this.nome = atualizacaoMedico.nome();
        }
        if (atualizacaoMedico.telefone() != null) {
            this.telefone = atualizacaoMedico.telefone();
        }
        if (atualizacaoMedico.endereco() != null) {
            this.endereco.atualizarInformacoes(atualizacaoMedico.endereco());
        }
    }
}
