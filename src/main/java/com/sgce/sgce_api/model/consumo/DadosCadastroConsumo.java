package com.sgce.sgce_api.model.consumo;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;


// DTO (Data Transfer Object) usado para entrada de dados no registro de consumo
// O uso de `record` aqui favorece clareza, imutabilidade e integração com validações automáticas
public record DadosCadastroConsumo(

        @NotNull
        Long unidadeId,

        @NotNull //  A data de referência também é obrigatória para registrar o consumo
        LocalDate dataReferencia,

        @NotNull // O valor do consumo não pode ser omitido
        @DecimalMin(value = "0.0", inclusive = false) // O consumo precisa ser maior que zero
        BigDecimal consumoKwh

) {
        // Este record será automaticamente validado pelo Spring com @Valid nos endpoints do controller
        // É uma forma concisa e segura de representar os dados esperados na requisição POST
}
