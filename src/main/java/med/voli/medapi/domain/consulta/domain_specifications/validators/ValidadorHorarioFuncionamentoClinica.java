package med.voli.medapi.domain.consulta.domain_specifications.validators;

import med.voli.medapi.domain.consulta.domain_specifications.validation_interfaces.ValidadorAgendamentoConsultaInterface;
import med.voli.medapi.domain.consulta.dto.AgendamentoConsulta;
import med.voli.medapi.infra.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsultaInterface {
    @Override
    public void validar(AgendamentoConsulta agendamentoConsulta) throws ValidacaoException{
        var dataConsulta = agendamentoConsulta.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoFechamentoDaClinica = dataConsulta.getHour() > 18;

        if (domingo || antesDaAberturaDaClinica || depoisDoFechamentoDaClinica)
            throw new ValidacaoException("A clínica não funciona neste horário.");
    }
}
