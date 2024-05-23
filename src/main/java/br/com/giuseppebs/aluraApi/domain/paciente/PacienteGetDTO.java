package br.com.giuseppebs.aluraApi.domain.paciente;

public record PacienteGetDTO(
        String nome,
        String email,
        String cpf) {
    public PacienteGetDTO(Paciente paciente) {
        this(paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf());
    }
}
