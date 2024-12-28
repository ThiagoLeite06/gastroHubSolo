# Sistema de Gestão de Restaurantes - GastroHubSolo

Este é um sistema desenvolvido em Java com Spring Boot, projetado para gerenciar eficientemente as operações de restaurantes, além de permitir que os clientes consultem informações, deixem avaliações e façam pedidos online. O projeto utiliza PostgreSQL como banco de dados, gerenciado em um ambiente Docker.

## 🛠️ Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **Spring Boot**: Framework para a criação da API.
- **PostgreSQL**: Banco de dados relacional.
- **Docker**: Para containerização do banco de dados.
- **Flyway**: Gerenciamento de migrações de banco de dados.

---

## ⚙️ Pré-requisitos

Antes de começar, você precisará ter instalado em sua máquina:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)
- [Java 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/)

---

## 🚀 Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd GastroHubSolo
   ```

2. **Inicie os contêineres do Docker:**
   Certifique-se de estar na raiz do projeto, onde o arquivo `docker-compose.yml` está localizado, e execute:
   ```bash
   docker-compose up -d
   ```

3. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

4. **Acesse a aplicação:**
   A API estará disponível em: [http://localhost:8080](http://localhost:8080)

---

## 🗃️ Estrutura do Banco de Dados

As migrações serão aplicadas automaticamente ao iniciar a aplicação, criando a tabela necessária e inserindo dados de teste. Certifique-se de que o banco PostgreSQL esteja em execução (via Docker).

---

## 📚 Documentação da API

A API segue os padrões REST e possui os seguintes endpoints principais:

- **GET /users**: Retorna a lista de usuários cadastrados.


> Documentação detalhada poderá ser acessada no Swagger (Work in progress).

---

## 📝 Considerações

Este projeto é uma base inicial para um sistema completo de gestão de restaurantes. Sinta-se à vontade para contribuir ou adaptar conforme as necessidades do seu ambiente de trabalho.
