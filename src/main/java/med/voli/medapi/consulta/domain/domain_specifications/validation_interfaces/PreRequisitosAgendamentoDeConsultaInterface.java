package med.voli.medapi.consulta.domain.domain_specifications.validation_interfaces;

import med.voli.medapi.consulta.dto.AgendamentoConsulta;
import med.voli.medapi.infra.exceptions.ValidacaoException;

public interface PreRequisitosAgendamentoDeConsultaInterface {
    public void executar_validacoes(AgendamentoConsulta agendamentoConsulta) throws ValidacaoException;
}
