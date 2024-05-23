package br.com.giuseppebs.aluraApi.controller;

import br.com.giuseppebs.aluraApi.domain.paciente.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
    private final PacienteRepository repository;

    public PacienteController(PacienteRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteGetDetailDTO> registerPaciente(@RequestBody DadosCadastroPaciente dados, UriComponentsBuilder uriComponentsBuilder){
        var paciente = new Paciente(dados);
        this.repository.save(paciente);

        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new PacienteGetDetailDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteGetDTO>> listar(
            @PageableDefault(size = 10, sort = {"nome"})
            Pageable pageable){
        var pacientes = this.repository
                .findAll(pageable)
                .map(PacienteGetDTO::new);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<PacienteGetDetailDTO> detalhar(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new PacienteGetDetailDTO(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PacienteGetDetailDTO> atualizar(@RequestBody @Valid PacientePutDTO dados) {
        var paciente = this.repository.getReferenceById(dados.id());
        paciente.atualizar(dados);
        return ResponseEntity.ok(new PacienteGetDetailDTO(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(
            @PathVariable Long id
    ){
        var paciente = this.repository.getReferenceById(id);
        repository.delete(paciente);
        return  ResponseEntity.noContent().build();
    }
}

