package com.sgce.sgce_api.unidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Marca essa interface como um componente Spring (injeção automática em Services)
@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    Page<Unidade> findAllByAtivoTrue(Pageable paginacao);

}

// Interface que herda de JpaRepository, responsável por fornecer os métodos padrão de persistência (como save, findById, delete, etc.)
// O método customizado findAllByAtivoTrue(Pageable) segue a convenção de nomes do Spring Data JPA e permite buscar apenas
// as unidades com o campo "ativo = true", de forma paginada. Isso é útil para exibir dados em tabelas com controle de
// paginação e evitar o retorno de registros desativados.
