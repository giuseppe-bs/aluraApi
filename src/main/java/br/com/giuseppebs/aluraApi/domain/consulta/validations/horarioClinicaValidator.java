package br.com.giuseppebs.aluraApi.domain.consulta.validations;

import br.com.giuseppebs.aluraApi.domain.ValidacaoException;
import br.com.giuseppebs.aluraApi.domain.consulta.AgendamentoPostDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class horarioClinicaValidator {
    public void validar(AgendamentoPostDTO dados){
        var dataConsulta = dados.data();
        var isDomingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var isAfterClosing = dataConsulta.getHour() > 18;
        var isBeforeOpening = dataConsulta.getHour() < 7;
        if (isDomingo || isBeforeOpening || isAfterClosing){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clinica");
        }
    }
}
