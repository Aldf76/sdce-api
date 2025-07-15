package com.sgce.sgce_api.model.unidade;


// DTO de saída responsável por representar os dados públicos de uma Unidade
// Usado nas respostas da API (ex: após atualizar ou listar unidades)
public record DadosDetalhamentoUnidade(Long id, String nome, String cidade, TipoUnidade tipo) {


    // Construtor prático que recebe diretamente a entidade Unidade
    // Facilita a conversão no Service, sem precisar escrever todos os campos manualmente
    public DadosDetalhamentoUnidade(Unidade unidade) {
        this(unidade.getId(), unidade.getNome(), unidade.getCidade(), unidade.getTipo());
    }

}

/*
 * Diferença em relação ao DTO de cadastro (DadosCadastroUnidade):
 * - Este é usado para devolver dados ao front-end (resposta)
 * - Já possui o ID da unidade, pois ela já foi registrada no sistema
 * - Não possui validações, pois não recebe dados externos — apenas exibe
 */