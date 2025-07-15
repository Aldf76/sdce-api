package com.sgce.sgce_api.controller;

import java.net.URI;

import com.sgce.sgce_api.model.unidade.AtualizarDadosCadastroUnidade;
import com.sgce.sgce_api.model.unidade.DadosCadastroUnidade;
import com.sgce.sgce_api.model.unidade.DadosDetalhamentoUnidade;
import com.sgce.sgce_api.model.unidade.DadosUnidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;

import com.sgce.sgce_api.service.UnidadeService;


@RestController // Define que esta classe responde a requisições HTTP e retorna JSON
@RequestMapping("/unidades") // Define o caminho base para todos os endpoints relacionados a unidades
@CrossOrigin(origins = "http://localhost:5173") // Permite chamadas do front-end local (evita erro de CORS durante o desenvolvimento)
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @PostMapping
    public ResponseEntity<URI> cadastrar(@RequestBody @Valid DadosCadastroUnidade dados,
                                         UriComponentsBuilder uriBuilder) {

        // Cadastra uma nova unidade com os dados enviados no corpo da requisição
        // Retorna a URI do recurso recém-criado (padrão para criação em APIs REST)
        URI uri = unidadeService.cadastrarUnidade(dados, uriBuilder);
        return ResponseEntity.created(uri).body(uri);
    }

    @GetMapping
    public ResponseEntity<Page<DadosUnidade>> listar(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        // Lista todas as unidades ativas, paginadas
        // A ordenação e o tamanho da página podem ser ajustados por parâmetros ou pelo @PageableDefault
        Page<DadosUnidade> unidades = unidadeService.listarUnidadesAtivas(paginacao);
        return ResponseEntity.ok(unidades);
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoUnidade> atualizar(@RequestBody @Valid AtualizarDadosCadastroUnidade dados) {
        // Atualiza os dados de uma unidade existente, se os campos forem válidos
        DadosDetalhamentoUnidade detalhamento = unidadeService.atualizarUnidade(dados);
        return ResponseEntity.ok(detalhamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        // Marca a unidade como inativa com base no ID informado (exclusão lógica)
        unidadeService.excluirUnidade(id);
        return ResponseEntity.noContent().build(); // Retorna status 204 (sem conteúdo), como esperado em remoções
    }
}