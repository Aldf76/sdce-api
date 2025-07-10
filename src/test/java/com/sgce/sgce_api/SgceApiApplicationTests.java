package com.sgce.sgce_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Usa spring boot test - Usa Junit 5 (@Test)

@SpringBootTest // Faz o Spring subir toda a aplicação para verificar se tudo está funcionando (teste de integração)
class SgceApiApplicationTests {

	@Test
	void contextLoads() {
	}

	// Esse teste garante que a aplicação inicializa corretamente.
	// Se algum componente essencial estiver mal configurado (como Controller, Service ou Bean), esse teste vai falhar.
	// É um bom teste de "sanidade" geral da aplicação.

}
