package com.sgce.sgce_api.model.unidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// DTO utilizado para receber os dados no momento de criação de uma nova unidade
public record DadosCadastroUnidade(
        @NotBlank// Garante que o nome não seja nulo nem vazio (campo obrigatório)
        String nome,

        @NotBlank
        String cidade,

        @NotNull
        TipoUnidade tipo
) {

        // Esse DTO é usado no controller para receber e validar os dados da nova unidade
        // Centraliza as regras de entrada e protege a entidade principal contra dados inválidos
}
