package br.com.giuseppebs.aluraApi.controller;

import br.com.giuseppebs.aluraApi.domain.usuario.Usuario;
import br.com.giuseppebs.aluraApi.domain.usuario.UsuarioLoginDTO;
import br.com.giuseppebs.aluraApi.domain.usuario.UsuarioRepository;
import br.com.giuseppebs.aluraApi.infra.SecurityConfigurations;
import br.com.giuseppebs.aluraApi.infra.TokenDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AtuenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<UsuarioLoginDTO> loginDTOJacksonTester;

    @Autowired
    private JacksonTester<TokenDTO> tokenDTOJacksonTester;

    @Autowired
    private SecurityConfigurations securityConfigurations;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deveri devolver o codigo 400 quando nada é enviado")
    void login_cenario_1() throws Exception {
        var response = mockMvc.perform(post("/login")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver o código 200 quando o usuário esta correto")
    @WithMockUser
    @Transactional
    void login_cenario_2()throws Exception {
        var encodedPass = securityConfigurations.passwordEncoder().encode("password");
        var user = new Usuario(null, "loginName", encodedPass);
        usuarioRepository.save(user);
        var response = mockMvc
                .perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(loginDTOJacksonTester.write(
                                        new UsuarioLoginDTO(
                                                "loginName",
                                                encodedPass
                                        )
                                    ).getJson()
                                )
                ).andReturn().getResponse();
        System.out.println(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}