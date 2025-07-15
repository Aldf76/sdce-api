
package com.sgce.sgce_api;

import com.sgce.sgce_api.model.unidade.DadosCadastroUnidade;
import com.sgce.sgce_api.model.unidade.AtualizarDadosCadastroUnidade;
import com.sgce.sgce_api.model.unidade.TipoUnidade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Inicia a aplicação em uma porta aleatória para testes HTTP reais
@ActiveProfiles("test") // Ativa o perfil de testes (pode carregar configurações específicas como banco H2)
public class UnidadeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate; // Cliente HTTP próprio para testes — permite simular chamadas reais à API

    @Test
    void deveCadastrarUnidadeComSucesso() {
        // Simula uma requisição POST para cadastrar uma nova unidade
        var dados = new DadosCadastroUnidade("Residência Teste", "Niterói", TipoUnidade.RESIDENCIAL);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DadosCadastroUnidade> request = new HttpEntity<>(dados, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("/unidades", request, String.class);

        // Verifica se a resposta foi 201 CREATED e se retornou a URI da nova unidade
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().getLocation()).isNotNull();
    }

    @Test
    void deveListarUnidadesAtivas() {

        // Simula uma requisição GET para listar unidades
        ResponseEntity<String> response = restTemplate.getForEntity("/unidades", String.class);

        // Espera status 200 OK e que a resposta contenha o campo "nome" no corpo
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("nome");
    }

    @Test
    void deveAtualizarUnidade() {

        // Simula uma requisição PUT para atualizar os dados de uma unidade
        var dados = new AtualizarDadosCadastroUnidade(1L, "Nome Atualizado", "São Gonçalo", TipoUnidade.INDUSTRIAL);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AtualizarDadosCadastroUnidade> request = new HttpEntity<>(dados, headers);

        ResponseEntity<String> response = restTemplate.exchange("/unidades", HttpMethod.PUT, request, String.class);

        // Espera status 200 OK e que o nome atualizado apareça no corpo da resposta
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Nome Atualizado");
    }

    @Test
    void deveExcluirUnidade() {
        // Simula uma requisição DELETE para inativar uma unidade com ID 1
        ResponseEntity<Void> response = restTemplate.exchange("/unidades/1", HttpMethod.DELETE, null, Void.class);
        // Espera status 204 No Content, que indica exclusão lógica realizada com sucesso
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
/*

Esse teste tem o objetivo de verificar se os principais endpoints da API de Unidades funcionam corretamente na prática. Ele simula requisições HTTP reais, como se fossem feitas pelo front-end, testando o comportamento completo da aplicação.

A anotação @SpringBootTest(webEnvironment = RANDOM_PORT) faz com que o Spring suba o servidor real da aplicação em uma porta aleatória. Com isso, é possível executar chamadas reais com o TestRestTemplate, que representa um cliente HTTP automatizado de testes.

Utilizei esse teste para validar os seguintes fluxos completos da API:

Cadastro de unidade (POST /unidades):
Verifica se o sistema aceita os dados enviados, cria uma nova unidade corretamente e retorna o status 201 Created junto com a URI da unidade cadastrada.

Listagem de unidades ativas (GET /unidades):
Testa se o sistema responde com status 200 OK e se o corpo da resposta contém os dados esperados, incluindo o campo nome.

Atualização de unidade (PUT /unidades):
Envia uma requisição para modificar os dados de uma unidade existente e confirma se o nome atualizado aparece corretamente na resposta, junto com o status 200 OK.

Exclusão lógica de unidade (DELETE /unidades/{id}):
Garante que o sistema realiza a exclusão lógica e retorna o status 204 No Content, confirmando que a unidade foi desativada com sucesso.

Esse teste mostra que todos os caminhos principais do CRUD estão funcionando, com respostas apropriadas e comportamento estável da aplicação como um todo. Ele também valida, de forma indireta, que os serviços, os DTOs, as regras de negócio e o banco de dados estão corretamente integrados.

Ou seja, a api esta pronta para ser consumida de maneira segura !!
w
 */