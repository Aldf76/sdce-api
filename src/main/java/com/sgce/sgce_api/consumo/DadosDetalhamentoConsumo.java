package com.sgce.sgce_api.consumo;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalhamentoConsumo(
        Long id,
        String nomeUnidade,
        String cidade,
        LocalDate dataReferencia,
        BigDecimal consumoKwh
) {
}
