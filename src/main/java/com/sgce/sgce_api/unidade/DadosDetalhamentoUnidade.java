package com.sgce.sgce_api.unidade;

public record DadosDetalhamentoUnidade(Long id, String nome, String cidade, TipoUnidade tipo) {

    public DadosDetalhamentoUnidade(Unidade unidade) {
        this(unidade.getId(), unidade.getNome(), unidade.getCidade(), unidade.getTipo());
    }

}

// DTO de saída responsável por representar os dados públicos de uma Unidade.
// Ao invés de expor diretamente a entidade JPA (Unidade) nos endpoints da API,
// utilizamos esse record para isolar e controlar a estrutura de resposta.
// Essa abordagem segue as boas práticas de arquitetura limpa, evita o acoplamento entre
// camadas, previne exposição indevida de dados internos e protege a aplicação contra
// possíveis injeções de informação via JSON ou parâmetros de requisição maliciosos.