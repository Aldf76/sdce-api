package com.sgce.sgce_api.model.dashboard;

import lombok.Getter;

import java.math.BigDecimal;


// feito para modelar a resposta json e proteger dados, enviando apenas o necessário para a requisição

@Getter
public class DashboardDTO {
    private long totalUnidades;
    private BigDecimal mediaConsumo;
    private BigDecimal picoConsumo;
    private int registros;

    // Getters e setters

    public DashboardDTO(long totalUnidades, BigDecimal mediaConsumo, BigDecimal picoConsumo, int registros) {
        this.totalUnidades = totalUnidades;
        this.mediaConsumo = mediaConsumo;
        this.picoConsumo = picoConsumo;
        this.registros = registros;
    }

    // Getters e Setters omitidos para brevidade
}
