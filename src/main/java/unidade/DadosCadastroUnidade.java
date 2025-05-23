package unidade;

import jakarta.validation.Valid;
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
