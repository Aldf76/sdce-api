package com.sgce.sgce_api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;

import com.sgce.sgce_api.unidade.*;
import com.sgce.sgce_api.unidade.UnidadeService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @PostMapping
    public ResponseEntity<URI> cadastrar(@RequestBody @Valid DadosCadastroUnidade dados,
                                         UriComponentsBuilder uriBuilder) {
        URI uri = unidadeService.cadastrarUnidade(dados, uriBuilder);
        return ResponseEntity.created(uri).body(uri);
    }

    @GetMapping
    public ResponseEntity<Page<DadosUnidade>> listar(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<DadosUnidade> unidades = unidadeService.listarUnidadesAtivas(paginacao);
        return ResponseEntity.ok(unidades);
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoUnidade> atualizar(@RequestBody @Valid AtualizarDadosCadastroUnidade dados) {
        DadosDetalhamentoUnidade detalhamento = unidadeService.atualizarUnidade(dados);
        return ResponseEntity.ok(detalhamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        unidadeService.excluirUnidade(id);
        return ResponseEntity.noContent().build();
    }
}