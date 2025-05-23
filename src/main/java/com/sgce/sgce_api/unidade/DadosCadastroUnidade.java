package com.sgce.sgce_api.unidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUnidade(
        @NotBlank
        String nome,

        @NotBlank
        String cidade,

        @NotNull
        TipoUnidade tipo
) {

}
