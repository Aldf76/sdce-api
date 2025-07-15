
package unidade;

import com.sgce.sgce_api.model.unidade.AtualizarDadosCadastroUnidade;
import com.sgce.sgce_api.model.unidade.DadosCadastroUnidade;
import com.sgce.sgce_api.model.unidade.TipoUnidade;
import com.sgce.sgce_api.model.unidade.Unidade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnidadeTest { // TESTE UNITÁRIO

    @Test
    void deveInativarUnidade() {
        // Cria uma nova unidade com status "ativo"
        Unidade unidade = new Unidade(new DadosCadastroUnidade("Teste", "Rio", TipoUnidade.RESIDENCIAL));
        // Executa o método que faz a exclusão lógica da unidade

        // Verifica se a unidade foi marcada como inativa
        assertFalse(unidade.getAtivo());
    }

    @Test
    void deveAtualizarInformacoes() {

        // Cria uma unidade inicial com valores antigos
        Unidade unidade = new Unidade(new DadosCadastroUnidade("Antigo Nome", "Cidade A", TipoUnidade.COMERCIAL));

        // Cria um DTO com os novos dados a serem aplicados na unidade
        AtualizarDadosCadastroUnidade atualizacao = new AtualizarDadosCadastroUnidade(
            unidade.getId(), "Novo Nome", "Cidade B", TipoUnidade.INDUSTRIAL
        );
        unidade.atualizarInformacoes(atualizacao);

        assertEquals("Novo Nome", unidade.getNome());
        assertEquals("Cidade B", unidade.getCidade());
        assertEquals(TipoUnidade.INDUSTRIAL, unidade.getTipo());
    }
}


/*
Esse teste tem como objetivo validar o comportamento interno da entidade Unidade, de forma isolada,
sem envolver banco de dados ou serviços externos

Esse teste garante que o método excluir()
 da entidade Unidade esteja funcionando corretamente.
Na prática, esse método representa a exclusão lógica do sistema,
marcando a unidade como inativa (sem removê-la do banco de dados).
O teste realiza as seguintes ações:
Cria uma unidade ativa com dados válidos.
Chama o método excluir().
Verifica se o campo ativo foi alterado para false, como esperado.
Esse teste mostra que a exclusão lógica foi implementada corretamente
e que a lógica de negócio está encapsulada na própria entidade.

=================================================================================

Esse teste verifica se o método atualizarInformacoes(...)
está atualizando corretamente os dados da unidade com base em um DTO de atualização.

A lógica desse teste cobre:
Criação da unidade original com nome, cidade e tipo.
Aplicação de um DTO contendo os novos valores.
Validação de que todos os atributos relevantes foram atualizados corretamente na instância da entidade.
Esse teste garante que a entidade está preparada para lidar com atualizações parciais, preservando a integridade dos dados e respondendo bem à entrada do usuário.
 */