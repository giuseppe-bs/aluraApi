package br.com.giuseppebs.aluraApi.controller;

import br.com.giuseppebs.aluraApi.domain.usuario.Usuario;
import br.com.giuseppebs.aluraApi.domain.usuario.UsuarioLoginDTO;
import br.com.giuseppebs.aluraApi.infra.TokenDTO;
import br.com.giuseppebs.aluraApi.infra.TokenService;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Profile(value = {"prod", "test"})
public class AtuenticationController {
    private final AuthenticationManager manager;
    private final TokenService tokenService;


    public AtuenticationController(
            AuthenticationManager manager,
            TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenDTO> Login(@RequestBody @Valid UsuarioLoginDTO usuarioLoginDTO){
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(usuarioLoginDTO.login(), usuarioLoginDTO.senha());
            var authentication = manager.authenticate(authenticationToken);
            var tokenJWT = tokenService.generateToken((Usuario) authentication.getPrincipal());
            return ResponseEntity.ok(new TokenDTO(tokenJWT));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
