package med.voli.medapi.consulta.domain.domain_specifications;

import med.voli.medapi.consulta.domain.domain_specifications.validation_interfaces.ConjuntoDeValidacoesDeAgendamentoConsultaInterface;
import med.voli.medapi.consulta.domain.domain_specifications.validation_interfaces.ValidadorAgendamentoConsultaInterface;
import med.voli.medapi.consulta.domain.domain_specifications.validators.ValidadorHorarioFuncionamentoClinica;
import med.voli.medapi.consulta.domain.domain_specifications.validators.ValidadorMedicoAgendamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConjuntoDeValidacoesAgendamentoConsulta implements ConjuntoDeValidacoesDeAgendamentoConsultaInterface {
    @Autowired
    public List<ValidadorAgendamentoConsultaInterface> validadores;

    public ConjuntoDeValidacoesAgendamentoConsulta() {
        this.validadores = new ArrayList<>(
            List.of(
                new ValidadorHorarioFuncionamentoClinica(),
                new ValidadorMedicoAgendamentoDeConsulta()
            )
        );
    }

    @Override
    public List<ValidadorAgendamentoConsultaInterface> retornarValidadores() {
        return this.validadores;
    }
}
