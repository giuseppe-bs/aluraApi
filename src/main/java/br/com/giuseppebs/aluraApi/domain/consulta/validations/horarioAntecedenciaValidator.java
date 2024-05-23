package br.com.giuseppebs.aluraApi.domain.consulta.validations;

import br.com.giuseppebs.aluraApi.domain.ValidacaoException;
import br.com.giuseppebs.aluraApi.domain.consulta.AgendamentoPostDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class horarioAntecedenciaValidator implements ClinicaValidator<AgendamentoPostDTO>{

    public void validar(AgendamentoPostDTO dados) throws ValidacaoException{
        var dataConsulta = dados.data();
        var diferencaMin = Duration.between(LocalDateTime.now(), dataConsulta).toMinutes();
        if (diferencaMin < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
