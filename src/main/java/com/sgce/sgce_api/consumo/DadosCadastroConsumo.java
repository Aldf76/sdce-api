package com.sgce.sgce_api.consumo;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosCadastroConsumo(

        @NotNull
        Long unidadeId,

        @NotNull
        LocalDate dataReferencia,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal consumoKwh

) {
}
