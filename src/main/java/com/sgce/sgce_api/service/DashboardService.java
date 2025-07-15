package com.sgce.sgce_api.service;

import com.sgce.sgce_api.model.dashboard.DashboardDTO;
import com.sgce.sgce_api.repository.ConsumoRepository;
import com.sgce.sgce_api.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DashboardService {

    private final UnidadeRepository unidadeRepository;
    private final ConsumoRepository consumoRepository;

    public DashboardService(UnidadeRepository unidadeRepository, ConsumoRepository consumoRepository) {
        this.unidadeRepository = unidadeRepository;
        this.consumoRepository = consumoRepository;
    }

    public DashboardDTO obterResumo() {
        long totalUnidades = unidadeRepository.countByAtivoTrue();
        int registros = (int) consumoRepository.count();
        BigDecimal mediaConsumo = consumoRepository.calcularMediaConsumo(); // query criada no repository ! pois ele quem faz contato direto com o banco
        BigDecimal picoConsumo = consumoRepository.calcularPicoConsumo(); // idem

        return new DashboardDTO(
                totalUnidades,
                mediaConsumo != null ? mediaConsumo : BigDecimal.valueOf(0.0), // wrap o valor do bigDecimal para evitar problemas de compilação
                picoConsumo != null ? picoConsumo : BigDecimal.valueOf(0.0),
                registros
        );
    }
}