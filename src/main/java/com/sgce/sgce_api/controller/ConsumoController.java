package com.sgce.sgce_api.controller;

import com.sgce.sgce_api.consumo.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumos")
@CrossOrigin(origins = "http://localhost:5173")
public class ConsumoController {

    @Autowired
    private ConsumoService consumoService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoConsumo> registrar(@RequestBody @Valid DadosCadastroConsumo dados) {
        try {
            var resposta = consumoService.registrar(dados);
            return ResponseEntity.ok(resposta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<?> listarPorUnidade(@RequestParam Long unidadeId) {
        var lista = consumoService.listarPorUnidade(unidadeId);
        return ResponseEntity.ok(lista);
    }
}
