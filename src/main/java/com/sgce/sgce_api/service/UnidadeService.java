package com.sgce.sgce_api.service;

import com.sgce.sgce_api.model.unidade.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import com.sgce.sgce_api.repository.UnidadeRepository;

import java.net.URI;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    public URI cadastrarUnidade(@Valid DadosCadastroUnidade dados, UriComponentsBuilder uriBuilder) {
        Unidade unidade = new Unidade(dados);
        unidadeRepository.save(unidade);
        return uriBuilder.path("/unidades/{id}").buildAndExpand(unidade.getId()).toUri();
    }

    public Page<DadosUnidade> listarUnidadesAtivas(Pageable paginacao) {
        return unidadeRepository.findAllByAtivoTrue(paginacao).map(DadosUnidade::new);
    }

    @Transactional
    public DadosDetalhamentoUnidade atualizarUnidade(@Valid AtualizarDadosCadastroUnidade dados) {
        Unidade unidade = unidadeRepository.getReferenceById(dados.id());
        unidade.atualizarInformacoes(dados);
        return new DadosDetalhamentoUnidade(unidade);
    }

    @Transactional
    public void excluirUnidade(Long id) {
        Unidade unidade = unidadeRepository.getReferenceById(id);
        unidade.excluir();
    }
}
