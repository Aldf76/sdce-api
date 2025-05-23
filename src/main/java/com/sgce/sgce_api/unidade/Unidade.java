package unidade;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "unidades")
@Entity(name = "Unidade")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Unidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SuppressWarnings("unused")
    private String nome;

    @SuppressWarnings("unused")
    private String cidade;

    @Enumerated(EnumType.STRING)
    private TipoUnidade tipo;

    private boolean ativo;

    public Unidade(DadosCadastroUnidade dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.cidade = dados.cidade();
        this.tipo = dados.tipo();
    }

    public void atualizarInformacoes(@Valid AtualizarDadosCadastroUnidade dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if (dados.tipo() != null) {
            this.tipo = dados.tipo();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
