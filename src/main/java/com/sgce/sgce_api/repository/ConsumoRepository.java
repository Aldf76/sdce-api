package com.sgce.sgce_api.repository;

import com.sgce.sgce_api.model.consumo.Consumo;
import com.sgce.sgce_api.model.unidade.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


// Marca essa interface como um componente Spring (injeção automática em Services)
@Repository
public interface ConsumoRepository extends JpaRepository<Consumo, Long> {

    /**
     * Verifica se já existe um consumo para a unidade e data informadas.
     * Usado para impedir registros duplicados por mês.
     */
    boolean existsByUnidadeAndDataReferencia(Unidade unidade, LocalDate dataReferencia);

/*Busca todos os consumos associado a uma unidade*/

    List<Consumo> findAllByUnidade(Unidade unidade);


    /* Querys referentes ao dashboard */

    // Calcula a média de todos os consumos
    @Query("SELECT AVG(c.consumoKwh) FROM Consumo c WHERE c.unidade.ativo = true")
    BigDecimal calcularMediaConsumo();

    // Busca o maior consumo registrado (pico)
    @Query("SELECT MAX(c.consumoKwh) FROM Consumo c WHERE c.unidade.ativo = true")
    BigDecimal calcularPicoConsumo();



/*
Destrinchando o código

exemplo : @Query("SELECT AVG(c.consumoKwh) FROM Consumo c")

SELECT AVG(...): quero calcular a média de algum campo.

c.valor: pegue o valor do campo valor dentro da entidade Java Consumo (não a tabela).

FROM Consumo c: estamos consultando a entidade Consumo, que representa a tabela consumo no banco.

c é apenas um apelido (alias) que usamos dentro da consulta.


 */
}


