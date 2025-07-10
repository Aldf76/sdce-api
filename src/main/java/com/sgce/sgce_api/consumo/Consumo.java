package com.sgce.sgce_api.consumo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgce.sgce_api.unidade.Unidade;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "consumos") // define explicitamente o nome da tabela no banco de dados
@Entity(name = "Consumo") // define a entidade JPA com nome lógico "Consumo"
@Getter // Lombok: gera os getters de todos os campos
@NoArgsConstructor // gera um construtor padrão ( sem argumentos )
@AllArgsConstructor // gera um construtor padrão ( com todos os campos )
@EqualsAndHashCode(of = "id") // Lombok: Sobrescreve equals e hashCode usando ID
public class Consumo implements Serializable { // boa prática : pemite transformar o objeto em bytes para salvar ou enviar pela rede

    private static final long serialVersionUID = 1L; // versão da classe para serialização ( controle de compatibilidade )

    @Id // define o campo como Chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID gerado automaticamente pelo banco com auto incremento (útil para garantir unicidade e ordem crescente)
    private Long id;

    @ManyToOne(optional = false) // Relação muitos-para-um com a entidade Unidade (obrigatória)
    @JoinColumn(name = "unidade_id") // Nome da coluna no banco que faz a ligação com a tabela unidade ( outra classe )
    private Unidade unidade;

    @Column(name = "data_referencia", nullable = false) // Define nome da coluna e torna obrigatório no banco
    @JsonFormat(pattern = "yyyy-MM-dd")  // Formato da data no JSON (útil para o front-end interpretar corretamente)
    private LocalDate dataReferencia;

    @DecimalMin(value = "0.0", inclusive = false) // Validação: o consumo deve ser maior que 0
    @Column(name = "consumo_kwh", nullable = false, precision = 10, scale = 2) //Define tipo decimal com 2 casas decimais
    private BigDecimal consumoKwh;

    // Construtor auxiliar sem o ID (útil para criação de novos registros)
    public Consumo(Unidade unidade, LocalDate dataReferencia, BigDecimal consumoKwh) {
        this.unidade = unidade;
        this.dataReferencia = dataReferencia;
        this.consumoKwh = consumoKwh;
    }
}