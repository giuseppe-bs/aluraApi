package br.com.giuseppebs.aluraApi.domain.consulta;

import br.com.giuseppebs.aluraApi.domain.Medico.Medico;
import br.com.giuseppebs.aluraApi.domain.Medico.MedicoRepository;
import br.com.giuseppebs.aluraApi.domain.ValidacaoException;
import br.com.giuseppebs.aluraApi.domain.consulta.validations.ClinicaValidator;
import br.com.giuseppebs.aluraApi.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    List<ClinicaValidator<AgendamentoPostDTO>> clinicaValidatorslist;

    public void agendar(AgendamentoPostDTO dados){
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        clinicaValidatorslist.forEach(clinicaValidator -> clinicaValidator.validar(dados));

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = escolherMedico(dados);
        consultaRepository.save(new Consulta(null, medico, paciente, dados.data()));
    }

    private Medico escolherMedico(AgendamentoPostDTO dados) {
        if (dados.idMedico() != null){
            return medicoRepository.findById(dados.idMedico()).get();
        }
        return medicoRepository
                .escolherMedicoAleatorioLivreNaData(
                        dados.especialidade(),
                        dados.data());
    }
}
