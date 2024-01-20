package med.voli.medapi.repository.medico;

import med.voli.medapi.domain.consulta.Consulta;
import med.voli.medapi.domain.endereco.EnderecoDTO;
import med.voli.medapi.domain.medico.Especialidade;
import med.voli.medapi.domain.medico.Medico;
import med.voli.medapi.domain.medico.dto.CadastroMedico;
import med.voli.medapi.domain.paciente.Paciente;
import med.voli.medapi.domain.paciente.dto.CadastroPaciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deve retornar médico disponível na data ou null caso o médico não esteja disponível")
    void buscarMedicoDisponivelNaDataDeveNaoEncontrarUmMedico() {
        var data = LocalDateTime.of(2021, 10, 10, 10, 0);
        var medicoDispnivel = medicoRepository.buscarMedicoDisponivelNaData(Especialidade.CARDIOLOGIA, data);

        var medico = cadastrarMedico("medico", "medico@vol.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("paciente", "paciente@email.com", "123");
        agendarConsulta(medico, paciente, data);

        assertThat(medicoDispnivel).isNull();
    }

    @Test
    @DisplayName("Deve retornar médico disponível na data ou null caso o médico não esteja disponível")
    void buscarMedicoDisponivelNaDataDeveEncontrarUmMedicoDisponivel() {
        var data = LocalDateTime.of(2021, 10, 10, 10, 0);
        var dataConsultaExistente = LocalDateTime.of(2021, 10, 9, 10, 0);

        var medico = cadastrarMedico("medico", "medico@vol.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("paciente", "paciente@email.com", "123");
        agendarConsulta(medico, paciente, dataConsultaExistente);

        var medicoDispnivel = medicoRepository.buscarMedicoDisponivelNaData(Especialidade.CARDIOLOGIA, data);

        assertThat(medicoDispnivel).isEqualTo(medico);
    }

    private void agendarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        entityManager.persist(new Consulta(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        entityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        entityManager.persist(paciente);
        return paciente;
    }

    private CadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new CadastroMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private CadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new CadastroPaciente(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private EnderecoDTO dadosEndereco() {
        return new EnderecoDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}