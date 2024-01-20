package med.voli.medapi.domain.consulta.domain_specifications.validation_interfaces;

import med.voli.medapi.domain.consulta.dto.AgendamentoConsulta;
import med.voli.medapi.infra.exceptions.ValidacaoException;

public interface ValidadorAgendamentoConsultaInterface {

    public void validar(AgendamentoConsulta agendamentoConsulta) throws ValidacaoException;
}
