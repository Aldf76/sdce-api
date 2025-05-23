package com.sgce.sgce_api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import com.sgce.sgce_api.unidade.DadosCadastroUnidade;
import com.sgce.sgce_api.unidade.DadosDetalhamentoUnidade;
import com.sgce.sgce_api.unidade.DadosUnidade;
import com.sgce.sgce_api.unidade.AtualizarDadosCadastroUnidade;
import com.sgce.sgce_api.unidade.Unidade;
import com.sgce.sgce_api.unidade.UnidadeRepository;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    @Autowired
    private final UnidadeRepository unidadeRepository;

    public UnidadeController(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    @PostMapping
    public ResponseEntity<URI> cadastrar(@RequestBody @Valid DadosCadastroUnidade dados,
                                         UriComponentsBuilder uriBuilder) {
        var unidade = new Unidade(dados);
        unidadeRepository.save(unidade);
        var uri = uriBuilder.path("/unidades/{id}").buildAndExpand(unidade.getId()).toUri();
        return ResponseEntity.created(uri).body(uri);
    }

    @GetMapping
    public ResponseEntity<Page<DadosUnidade>> listar(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = unidadeRepository.findAllByAtivoTrue(paginacao).map(DadosUnidade::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUnidade> atualizar(@RequestBody @Valid AtualizarDadosCadastroUnidade dados) {
        var unidade = unidadeRepository.getReferenceById(dados.id());
        unidade.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoUnidade(unidade));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        var unidade = unidadeRepository.getReferenceById(id);
        unidade.excluir();
        return ResponseEntity.noContent().build();
    }
}
