package com.sgce.sgce_api.consumo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgce.sgce_api.unidade.Unidade;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "consumos")
@Entity(name = "Consumo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consumo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unidade_id")
    private Unidade unidade;

    @Column(name = "data_referencia", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataReferencia;

    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "consumo_kwh", nullable = false, precision = 10, scale = 2)
    private BigDecimal consumoKwh;

    public Consumo(Unidade unidade, LocalDate dataReferencia, BigDecimal consumoKwh) {
        this.unidade = unidade;
        this.dataReferencia = dataReferencia;
        this.consumoKwh = consumoKwh;
    }
}