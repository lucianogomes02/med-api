package med.voli.medapi.consulta.domain.domain_specifications;

import med.voli.medapi.consulta.domain.domain_specifications.validation_interfaces.PreRequisitosAgendamentoDeConsultaInterface;
import med.voli.medapi.consulta.domain.domain_specifications.validation_interfaces.ValidadorAgendamentoConsultaInterface;
import med.voli.medapi.consulta.dto.AgendamentoConsulta;
import med.voli.medapi.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PreRequisitosAgendamentoDeConsulta implements PreRequisitosAgendamentoDeConsultaInterface {
    @Autowired
    private ConjuntoDeValidacoesAgendamentoConsulta validacoes;

    @Autowired
    private final AgendamentoConsulta agendamentoConsulta;

    public PreRequisitosAgendamentoDeConsulta(AgendamentoConsulta agendamentoConsulta) {
        this.validacoes = new ConjuntoDeValidacoesAgendamentoConsulta();
        this.agendamentoConsulta = agendamentoConsulta;
    }

    @Override
    public void executar_validacoes() {
        var validadores = this.validacoes.retornarValidadores();
        validadores.forEach(validador -> {
            try {
                validador.validar(this.agendamentoConsulta);
            } catch (ValidacaoException e) {
                throw new RuntimeException(e.toString());
            }
        });
    }
}
