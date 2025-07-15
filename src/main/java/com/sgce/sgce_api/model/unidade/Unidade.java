package com.sgce.sgce_api.model.unidade;

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

    @SuppressWarnings("unused")    // // Suprime alertas de campo não usado diretamente — comum em campos acessados via getter ou serialização
    private String nome;

    @SuppressWarnings("unused")
    private String cidade;

    @Enumerated(EnumType.STRING) // Armazena o enum como texto no banco (ex: "ESCOLA", "HOSPITAL")
    private TipoUnidade tipo;

    private boolean ativo; // Flag de "soft delete" para marcar unidades como inativas sem removê-las do banco

    // Construtor baseado em record DTO validado - evita uso de setters
    public Unidade(DadosCadastroUnidade dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.cidade = dados.cidade();
        this.tipo = dados.tipo();
    }
    // Atualiza apenas os campos recebidos (evita sobrescrever nulos)
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
    // "Exclusão lógica": marca a unidade como inativa sem removê-la do banco
    public void excluir() {
        this.ativo = false;
    }


// fins de teste ( Unidade.teste ) -->  // Getter explícito necessário para frameworks e testes que verificam diretamente o valor do atributo 'ativo'
    public Boolean getAtivo() {
        return this.ativo;
    }







}
