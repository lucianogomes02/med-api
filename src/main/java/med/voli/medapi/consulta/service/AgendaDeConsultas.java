package med.voli.medapi.consulta.service;

import med.voli.medapi.consulta.domain.Consulta;
import med.voli.medapi.consulta.dto.AgendamentoConsulta;
import med.voli.medapi.consulta.repository.ConsultaRepository;
import med.voli.medapi.consulta.exceptions.EntidadeInexistente;
import med.voli.medapi.consulta.exceptions.EspecialidadeObrigatoria;
import med.voli.medapi.medico.domain.Medico;
import med.voli.medapi.medico.repository.MedicoRepository;
import med.voli.medapi.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(AgendamentoConsulta agendamentoConsulta) throws EntidadeInexistente, EspecialidadeObrigatoria {
        if (!pacienteRepository.existsById(agendamentoConsulta.idPaciente())) {
            throw new EntidadeInexistente("Paciente informado não está cadastrado.");
        }

        if (agendamentoConsulta.idMedico() != null && !medicoRepository.existsById(agendamentoConsulta.idMedico())) {
            throw new EntidadeInexistente("Médico informado não está cadastrado.");
        }

        var medico = selecionarMedico(agendamentoConsulta);
        var paciente = pacienteRepository.getReferenceById(agendamentoConsulta.idPaciente());
        var consulta = new Consulta(null, medico, paciente, agendamentoConsulta.data());
        consultaRepository.save(consulta);
    }

    private Medico selecionarMedico(AgendamentoConsulta agendamentoConsulta) throws EspecialidadeObrigatoria{
        if(agendamentoConsulta.idMedico() != null) {
            return medicoRepository.getReferenceById(agendamentoConsulta.idMedico());
        }

        if(agendamentoConsulta.especialidade() == null) {
            throw new EspecialidadeObrigatoria("Especialidade é obrigatória quando o Médico não for escolhido.");
        }

        return medicoRepository.buscarMedicoDisponivelNaData(agendamentoConsulta.especialidade(), agendamentoConsulta.data());
    }
}
