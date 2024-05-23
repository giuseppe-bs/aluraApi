package br.com.giuseppebs.aluraApi.domain.Medico;

public record MedicoGetDTO(
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public MedicoGetDTO(Medico medico){
        this(
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getEspecialidade()
        );
    }
}
