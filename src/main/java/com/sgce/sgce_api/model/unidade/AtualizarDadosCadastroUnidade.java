package com.sgce.sgce_api.model.unidade;

import jakarta.annotation.Nonnull;

public record AtualizarDadosCadastroUnidade(
        @Nonnull
        Long id,
        String nome,
        String cidade,
        TipoUnidade tipo
) {
}
