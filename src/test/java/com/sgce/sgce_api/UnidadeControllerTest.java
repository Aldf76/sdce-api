
package com.sgce.sgce_api;

import com.sgce.sgce_api.unidade.DadosCadastroUnidade;
import com.sgce.sgce_api.unidade.AtualizarDadosCadastroUnidade;
import com.sgce.sgce_api.unidade.TipoUnidade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UnidadeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void deveCadastrarUnidadeComSucesso() {
        var dados = new DadosCadastroUnidade("Residência Teste", "Niterói", TipoUnidade.RESIDENCIAL);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DadosCadastroUnidade> request = new HttpEntity<>(dados, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("/unidades", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().getLocation()).isNotNull();
    }

    @Test
    void deveListarUnidadesAtivas() {
        ResponseEntity<String> response = restTemplate.getForEntity("/unidades", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("nome");
    }

    @Test
    void deveAtualizarUnidade() {
        var dados = new AtualizarDadosCadastroUnidade(1L, "Nome Atualizado", "São Gonçalo", TipoUnidade.INDUSTRIAL);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AtualizarDadosCadastroUnidade> request = new HttpEntity<>(dados, headers);

        ResponseEntity<String> response = restTemplate.exchange("/unidades", HttpMethod.PUT, request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Nome Atualizado");
    }

    @Test
    void deveExcluirUnidade() {
        ResponseEntity<Void> response = restTemplate.exchange("/unidades/1", HttpMethod.DELETE, null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
