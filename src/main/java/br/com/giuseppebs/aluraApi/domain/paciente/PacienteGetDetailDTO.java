package br.com.giuseppebs.aluraApi.domain.paciente;

import br.com.giuseppebs.aluraApi.domain.endereco.Endereco;

public record PacienteGetDetailDTO(
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco) {
    public PacienteGetDetailDTO(Paciente paciente) {
        this(
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getCpf(),
                paciente.getEndereco());
    }
}