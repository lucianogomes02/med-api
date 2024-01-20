package med.voli.medapi.domain.consulta.domain_specifications.validation_interfaces;

import java.util.List;

public interface ConjuntoDeValidacoesDeAgendamentoConsultaInterface {
    public List<ValidadorAgendamentoConsultaInterface> retornarValidadores();
}
