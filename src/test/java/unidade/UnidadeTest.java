
package unidade;

import com.sgce.sgce_api.unidade.AtualizarDadosCadastroUnidade;
import com.sgce.sgce_api.unidade.DadosCadastroUnidade;
import com.sgce.sgce_api.unidade.TipoUnidade;
import com.sgce.sgce_api.unidade.Unidade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnidadeTest {

    @Test
    void deveInativarUnidade() {
        Unidade unidade = new Unidade(new DadosCadastroUnidade("Teste", "Rio", TipoUnidade.RESIDENCIAL));
        unidade.excluir();
        assertFalse(unidade.getAtivo());
    }

    @Test
    void deveAtualizarInformacoes() {
        Unidade unidade = new Unidade(new DadosCadastroUnidade("Antigo Nome", "Cidade A", TipoUnidade.COMERCIAL));
        AtualizarDadosCadastroUnidade atualizacao = new AtualizarDadosCadastroUnidade(
            unidade.getId(), "Novo Nome", "Cidade B", TipoUnidade.INDUSTRIAL
        );
        unidade.atualizarInformacoes(atualizacao);

        assertEquals("Novo Nome", unidade.getNome());
        assertEquals("Cidade B", unidade.getCidade());
        assertEquals(TipoUnidade.INDUSTRIAL, unidade.getTipo());
    }
}
