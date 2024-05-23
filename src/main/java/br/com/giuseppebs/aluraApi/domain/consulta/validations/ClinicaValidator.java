package br.com.giuseppebs.aluraApi.domain.consulta.validations;


import br.com.giuseppebs.aluraApi.domain.ValidacaoException;

public interface ClinicaValidator<T> {
    public void validar (T dados) throws ValidacaoException;
}
