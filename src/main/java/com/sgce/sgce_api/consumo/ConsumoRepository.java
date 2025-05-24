package com.sgce.sgce_api.consumo;

import com.sgce.sgce_api.unidade.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;



@Repository
public interface ConsumoRepository extends JpaRepository<Consumo, Long> {

    /**
     * Verifica se já existe um consumo para a unidade e data informadas.
     * Usado para impedir registros duplicados por mês.
     */
    boolean existsByUnidadeAndDataReferencia(Unidade unidade, LocalDate dataReferencia);

/*Busca todos os consumos associado a uma unidade*/

    List<Consumo> findAllByUnidade(Unidade unidade);
}


