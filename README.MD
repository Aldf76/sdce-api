# 🚀 SGCE API – Spring Boot + MySQL

Sistema de Gestão de Consumo Energético (SGCE), desenvolvido com foco em boas práticas de arquitetura Java e integração com banco de dados. A aplicação oferece funcionalidades para cadastro de unidades consumidoras, registro de consumo mensal e visualização analítica dos dados.

> ✅ Projeto completo com estrutura de `DTOs`, `Services`, `Repositories`, `Testes`, e integração com **React** no front-end.

---

## 🔧 Tecnologias

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- H2 (para testes)
- Lombok
- Maven
- JUnit 5
- TestRestTemplate
- Postman (para testes manuais)
- IntelliJ IDEA

---

## 📂 Estrutura do Projeto

```
sgce-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── sgce/
│   │   │           └── sgce_api/
│   │   │               ├── unidade/
│   │   │               └── consumo/
│   └── resources/
├── pom.xml
└── README.md
```

## 🧠 Fluxo de Funcionamento

### 🔁 Cadastro e Consumo

1. **POST `/unidades`**  
   Cria uma nova unidade consumidora. Dados como nome, cidade e tipo (residencial, comercial etc) são informados.

2. **POST `/consumos`**  
   Registra o consumo mensal de uma unidade, vinculando o `id` da unidade ao consumo em kWh e à data de referência.

3. **GET `/consumos?unidadeId=X`**  
   Lista os consumos históricos daquela unidade específica.

4. **PUT `/unidades`**  
   Atualiza os dados da unidade (nome, cidade, tipo).

5. **DELETE `/unidades/{id}`**  
   Marca a unidade como inativa, sem removê-la do banco.

---

## 🔄 Arquitetura

- `@RestController`: Controla as entradas da API.
- `@Service`: Onde está a regra de negócio (validações, lógica de domínio).
- `@Repository`: Camada de acesso ao banco com Spring Data.
- `DTOs (records)`: Criados para transporte de dados sem expor a entidade JPA.

> ✅ Toda estrutura foi comentada com foco em aprendizado, explicando o uso de cada anotação, método e padrão de projeto.

---

## 🧪 Testes

O projeto implementa **dois tipos principais de testes**:

### 1. **Testes de Integração com API real (TestRestTemplate)**

Arquivo: `UnidadeControllerTest.java`

- Testa endpoints reais da API: cadastro, listagem, atualização e exclusão.
- Usa SpringBootTest com `webEnvironment = RANDOM_PORT`.

### 2. **Testes de Unidade**

Arquivo: `UnidadeTest.java`

- Valida métodos internos da entidade `Unidade`, como `excluir()` e `atualizarInformacoes()`.

### 3. **Teste de Inicialização**

Arquivo: `SgceApiApplicationTests.java`

- Verifica se a aplicação sobe corretamente com todas as dependências e beans.

---

## ▶️ Como Executar

### 🔧 Pré-requisitos

- Java 17+
- MySQL rodando (ou configure H2 para testes)
- Maven instalado

### 📥 Instalação

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/sgce-api.git
cd sgce-api

# Instale as dependências
./mvnw clean install

# Inicie o servidor local
./mvnw spring-boot:run


📬 Autor
Desenvolvido por Felipe Medeiros 
🎓 Estudante de Análise e Desenvolvimento de Sistemas E Desenvolvimento FullStack
🔗 linkedin.com/in/seu-linkedin
📫 felipe@email.com
📂 Projeto back-end: GitHub - sgce-api
📂 Projeto front-end: GitHub - sgce-front

ℹ️ Este projeto foi desenvolvido com propósito educacional e profissional, aplicando conceitos reais de arquitetura de software, testes automatizados e integração com front-end React.