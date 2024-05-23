package br.com.giuseppebs.aluraApi.domain.Medico;

import br.com.giuseppebs.aluraApi.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record MedicoPutDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
