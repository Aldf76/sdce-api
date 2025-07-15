package com.sgce.sgce_api.controller;

import jakarta.validation.Valid;
import com.sgce.sgce_api.model.consumo.DadosCadastroConsumo;
import com.sgce.sgce_api.model.consumo.DadosDetalhamentoConsumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sgce.sgce_api.service.ConsumoService;

@RestController // Indica que essa classe recebe requisições HTTP e responde diretamente com JSON
@RequestMapping("/consumos") // Define o caminho base da URL para esse controller
@CrossOrigin(origins = "http://localhost:5173") // Permite chamadas do front-end local (evita erro de CORS durante o desenvolvimento)
public class ConsumoController {

    @Autowired
    private ConsumoService consumoService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoConsumo> registrar(@RequestBody @Valid DadosCadastroConsumo dados) {
        try {
            // Chama o serviço responsável por registrar o consumo
            var resposta = consumoService.registrar(dados);

            // Retorna resposta com status 200 (OK) e os dados detalhados do consumo registrado
            return ResponseEntity.ok(resposta);
        } catch (IllegalArgumentException e) {

            // Se o serviço identificar erro de duplicidade ou regra de negócio, retorna erro 400 (requisição inválida)
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<?> listarPorUnidade(@RequestParam Long unidadeId) {
        // Retorna a lista de consumos da unidade informada via parâmetro na URL
        var lista = consumoService.listarPorUnidade(unidadeId);
        return ResponseEntity.ok(lista);
    }
}
