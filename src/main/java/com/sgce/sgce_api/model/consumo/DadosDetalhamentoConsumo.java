package com.sgce.sgce_api.model.consumo;

import java.math.BigDecimal;
import java.time.LocalDate;

// DTO de saída (resposta) usado para retornar ao front-end os dados completos de um consumo
// O uso de `record` é ideal aqui por ser imutável, conciso e voltado apenas ao transporte de dados
public record DadosDetalhamentoConsumo(
        Long id, // ID do consumo — facilita ações futuras como edição ou exclusão
        String nomeUnidade, // Nome da unidade (extraído da entidade Unidade, mas "achatado" aqui para facilitar a leitura)
        String cidade, // Cidade da unidade — útil para exibição sem precisar retornar o objeto Unidade inteiro
        LocalDate dataReferencia, // Data em que o consumo foi registrado

        BigDecimal consumoKwh // Valor registrado do consumo em kWh com precisão decimal (BigDecimal é ideal para evitar arredondamentos incorretos)
) {
    // Este DTO serve como uma "resposta pronta" para a API, desacoplada da entidade JPA
    // Evita expor relações completas do modelo e facilita a visualização no front-end
}
