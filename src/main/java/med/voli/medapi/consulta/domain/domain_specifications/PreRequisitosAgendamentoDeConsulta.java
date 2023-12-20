package med.voli.medapi.consulta.domain.domain_specifications;

import med.voli.medapi.consulta.domain.domain_specifications.validation_interfaces.PreRequisitosAgendamentoDeConsultaInterface;
import med.voli.medapi.consulta.dto.AgendamentoConsulta;
import med.voli.medapi.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PreRequisitosAgendamentoDeConsulta implements PreRequisitosAgendamentoDeConsultaInterface {
    @Autowired
    private ConjuntoDeValidacoesAgendamentoConsulta validacoes;

    public PreRequisitosAgendamentoDeConsulta() {
        this.validacoes = new ConjuntoDeValidacoesAgendamentoConsulta();
    }

    @Override
    public void executar_validacoes(AgendamentoConsulta agendamentoConsulta) {
        var validadores = this.validacoes.retornarValidadores();
        validadores.forEach(validador -> {
            try {
                validador.validar(agendamentoConsulta);
            } catch (ValidacaoException e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }
}
