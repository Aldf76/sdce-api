package com.sgce.sgce_api.service;

import com.sgce.sgce_api.model.consumo.Consumo;
import com.sgce.sgce_api.repository.ConsumoRepository;
import com.sgce.sgce_api.model.consumo.DadosCadastroConsumo;
import com.sgce.sgce_api.model.consumo.DadosDetalhamentoConsumo;
import com.sgce.sgce_api.model.unidade.Unidade;
import com.sgce.sgce_api.repository.UnidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Diz ao Spring que essa classe cuida da lógica de negócios (funcionamento real do sistema)
@Service
public class ConsumoService {

    @Autowired
    private ConsumoRepository consumoRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    // Garante que, se algo der errado no meio do processo, nada será salvo no banco
    @Transactional
    public DadosDetalhamentoConsumo registrar(@Valid DadosCadastroConsumo dados) {

        // Primeiro, o sistema procura a unidade correspondente ao ID informado
        // Se não encontrar, dá erro e nem tenta salvar o consumo
        Unidade unidade = unidadeRepository.findById(dados.unidadeId())
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada"));

        // Aqui o sistema checa se já existe um consumo para essa unidade e essa mesma data
        // Isso evita que o usuário registre consumo duplicado para o mesmo mês
        boolean consumoDuplicado = consumoRepository.existsByUnidadeAndDataReferencia(
                unidade, dados.dataReferencia());

        if (consumoDuplicado) {
            // Se já existe, o sistema interrompe e mostra mensagem de erro
            throw new IllegalArgumentException("Consumo já registrado para esta data.");
        }

        // Se passou por todas as validações, o sistema cria o novo consumo
        Consumo novoConsumo = new Consumo(unidade, dados.dataReferencia(), dados.consumoKwh());

        // E salva no banco de dados
        consumoRepository.save(novoConsumo);

        // Por fim, retorna os dados prontos para serem mostrados no front-end
        return new DadosDetalhamentoConsumo(
                novoConsumo.getId(),
                unidade.getNome(),
                unidade.getCidade(),
                novoConsumo.getDataReferencia(),
                novoConsumo.getConsumoKwh()
        );
    }

    public List<DadosDetalhamentoConsumo> listarPorUnidade(Long unidadeId) {

        // Aqui o sistema busca a unidade antes de tentar listar os consumos
        Unidade unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada"));


        // Depois busca todos os consumos já registrados para essa unidade
        // E transforma cada um deles em um DTO pronto para ser exibido no front-end
        return consumoRepository.findAllByUnidade(unidade).stream()
                .map(consumo -> new DadosDetalhamentoConsumo(
                        consumo.getId(),
                        unidade.getNome(),
                        unidade.getCidade(),
                        consumo.getDataReferencia(),
                        consumo.getConsumoKwh()
                )).toList();
    }
}