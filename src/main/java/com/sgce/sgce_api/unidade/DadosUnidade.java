package com.sgce.sgce_api.unidade;

// Declaração da classe como um "record" do Java, uma forma concisa de criar classes imutáveis
public record DadosUnidade(Long id, String nome, String cidade, TipoUnidade tipo) {

    // Construtor adicional que aceita um objeto Unidade e cria um objeto DadosUnidade
    public DadosUnidade(Unidade unidade) {
        // Utiliza o construtor primário do record para inicializar os campos com base no objeto Unidade fornecido
        this(unidade.getId(), unidade.getNome(), unidade.getCidade(), unidade.getTipo());
    }
}

// DTO intermediário utilizado para retornar dados resumidos ou estruturados da entidade Unidade,
// sem expor diretamente o objeto JPA no corpo da resposta HTTP.
// Essa abordagem ajuda a preservar a integridade da arquitetura, evita vazamento de atributos sensíveis,
// e oferece maior controle sobre o formato da resposta da API.
// O uso de records como esse também garante imutabilidade, clareza semântica e menor acoplamento
// entre as camadas da aplicação (domínio, serviço e controle).
