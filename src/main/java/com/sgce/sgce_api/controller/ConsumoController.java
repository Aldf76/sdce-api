package com.sgce.sgce_api.controller;

import com.sgce.sgce_api.consumo.Consumo;
import com.sgce.sgce_api.consumo.ConsumoRepository;
import com.sgce.sgce_api.consumo.DadosCadastroConsumo;
import com.sgce.sgce_api.consumo.DadosDetalhamentoConsumo;
import com.sgce.sgce_api.unidade.Unidade;
import com.sgce.sgce_api.unidade.UnidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumos")
@CrossOrigin(origins = "http://localhost:5173")
public class ConsumoController {

    @Autowired
    private final ConsumoRepository consumoRepository;

    @Autowired
    private final UnidadeRepository unidadeRepository;

    public ConsumoController(ConsumoRepository consumoRepository, UnidadeRepository unidadeRepository) {
        this.consumoRepository = consumoRepository;
        this.unidadeRepository = unidadeRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsumo> registrar(@RequestBody @Valid DadosCadastroConsumo dados) {
        Unidade unidade = unidadeRepository.findById(dados.unidadeId())
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada"));

        boolean consumoDuplicado = consumoRepository.existsByUnidadeAndDataReferencia(
                unidade, dados.dataReferencia());

        if (consumoDuplicado) {
            return ResponseEntity.badRequest()
                    .body(null); // Alternativamente, você pode usar ResponseEntity.status(409) com mensagem
        }

        Consumo novoConsumo = new Consumo(unidade, dados.dataReferencia(), dados.consumoKwh());
        consumoRepository.save(novoConsumo);

        var response = new DadosDetalhamentoConsumo(
                novoConsumo.getId(),
                unidade.getNome(),
                unidade.getCidade(),
                novoConsumo.getDataReferencia(),
                novoConsumo.getConsumoKwh()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> listarPorUnidade(@RequestParam Long unidadeId) {
        Unidade unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada"));

        var lista = consumoRepository.findAllByUnidade(unidade).stream()
                .map(consumo -> new DadosDetalhamentoConsumo(
                        consumo.getId(),
                        unidade.getNome(),
                        unidade.getCidade(),
                        consumo.getDataReferencia(),
                        consumo.getConsumoKwh()
                ))
                .toList();

        return ResponseEntity.ok(lista);
    }
}
