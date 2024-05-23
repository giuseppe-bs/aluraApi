package br.com.giuseppebs.aluraApi.domain.paciente;

import br.com.giuseppebs.aluraApi.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record PacientePutDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}