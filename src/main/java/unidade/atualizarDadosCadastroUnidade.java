package unidade;

import jakarta.annotation.Nonnull;

public record atualizarDadosCadastroUnidade(
        @Nonnull
        Long id,
        String nome,
        String cidade,
        TipoUnidade tipo
) {
}
