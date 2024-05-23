package br.com.giuseppebs.aluraApi.domain.Medico;

import br.com.giuseppebs.aluraApi.domain.endereco.Endereco;

public record MedicoGetDetailDTO(
            String nome,
            String email,
            String telefone,
            String crm,
            Especialidade especialidade,

            Endereco endereco) {
    public MedicoGetDetailDTO(Medico medico) {
        this(
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.getEndereco()
        );
    }
}