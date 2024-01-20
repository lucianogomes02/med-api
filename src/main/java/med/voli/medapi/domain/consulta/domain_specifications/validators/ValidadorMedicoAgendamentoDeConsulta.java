package med.voli.medapi.domain.consulta.domain_specifications.validators;

import med.voli.medapi.domain.consulta.domain_specifications.validation_interfaces.ValidadorAgendamentoConsultaInterface;
import med.voli.medapi.domain.consulta.dto.AgendamentoConsulta;
import med.voli.medapi.infra.exceptions.ValidacaoException;

public class ValidadorMedicoAgendamentoDeConsulta implements ValidadorAgendamentoConsultaInterface {
    @Override
    public void validar(AgendamentoConsulta agendamentoConsulta) throws ValidacaoException {
        if(agendamentoConsulta.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando o Médico não for escolhido.");
        }
    }
}