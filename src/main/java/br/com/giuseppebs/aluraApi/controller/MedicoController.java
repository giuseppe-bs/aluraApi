package br.com.giuseppebs.aluraApi.controller;

import br.com.giuseppebs.aluraApi.domain.Medico.*;
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
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
    private final MedicoRepository medicoRepository;

    // alternativa ao @Autowired
    public MedicoController(final MedicoRepository medicoRepository){
        this.medicoRepository = medicoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoGetDetailDTO> registerMedico(@RequestBody DadosCadastroMedico dados, UriComponentsBuilder uriComponentsBuilder){
        var medico = new Medico(dados);
        this.medicoRepository.save(medico);

        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicoGetDetailDTO(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoGetDTO>> listar(
            @PageableDefault(size = 10, sort = {"nome"})
            Pageable pageable){
        var medicos = this.medicoRepository
                .findAllByActiveTrue(pageable)
                .map(MedicoGetDTO::new);
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<MedicoGetDetailDTO> detalhar(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoGetDetailDTO(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicoGetDetailDTO> atualizar(@RequestBody @Valid MedicoPutDTO dados) {
        var medico = this.medicoRepository.getReferenceById(dados.id());
        medico.atualizar(dados);
        return ResponseEntity.ok(new MedicoGetDetailDTO(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(
            @PathVariable Long id
    ){
        var medico = this.medicoRepository.getReferenceById(id);
        medico.excluir();
        return  ResponseEntity.noContent().build();
    }
}
