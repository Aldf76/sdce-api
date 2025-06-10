package com.sgce.sgce_api.consumo;

import com.sgce.sgce_api.unidade.Unidade;
import com.sgce.sgce_api.unidade.UnidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumoService {

    @Autowired
    private ConsumoRepository consumoRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Transactional
    public DadosDetalhamentoConsumo registrar(@Valid DadosCadastroConsumo dados) {
        Unidade unidade = unidadeRepository.findById(dados.unidadeId())
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada"));

        boolean consumoDuplicado = consumoRepository.existsByUnidadeAndDataReferencia(
                unidade, dados.dataReferencia());

        if (consumoDuplicado) {
            throw new IllegalArgumentException("Consumo já registrado para esta data.");
        }

        Consumo novoConsumo = new Consumo(unidade, dados.dataReferencia(), dados.consumoKwh());
        consumoRepository.save(novoConsumo);

        return new DadosDetalhamentoConsumo(
                novoConsumo.getId(),
                unidade.getNome(),
                unidade.getCidade(),
                novoConsumo.getDataReferencia(),
                novoConsumo.getConsumoKwh()
        );
    }

    public List<DadosDetalhamentoConsumo> listarPorUnidade(Long unidadeId) {
        Unidade unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada"));

        return consumoRepository.findAllByUnidade(unidade).stream()
                .map(consumo -> new DadosDetalhamentoConsumo(
                        consumo.getId(),
                        unidade.getNome(),
                        unidade.getCidade(),
                        consumo.getDataReferencia(),
                        consumo.getConsumoKwh()
                )).toList();
    }
}