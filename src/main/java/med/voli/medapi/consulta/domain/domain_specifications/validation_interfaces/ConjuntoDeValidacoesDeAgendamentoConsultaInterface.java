package med.voli.medapi.consulta.domain.domain_specifications.validation_interfaces;

import java.util.List;

public interface ConjuntoDeValidacoesDeAgendamentoConsultaInterface {
    public List<ValidadorAgendamentoConsultaInterface> retornarValidadores();
}
