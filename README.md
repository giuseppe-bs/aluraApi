# Projeto de Agendamento de Consultas Médicas

Bem-vindo ao repositório do projeto de agendamento de consultas médicas, desenvolvido durante a Formação em Java e Spring Boot. Este projeto consiste em uma aplicação backend robusta, criada para ser consumida por um aplicativo de agendamento de consultas médicas. O projeto faz uso do Spring Boot, um framework que facilita e acelera o desenvolvimento de aplicações web e microsserviços.

## Funcionalidades do Projeto

- **Cadastro de Usuários e Médicos:** Permite o registro de novos usuários e médicos no sistema.
- **Agendamento de Consultas:** Facilita a marcação, reprogramação e cancelamento de consultas médicas.
- **Consulta de Disponibilidade:** Permite a verificação da disponibilidade dos médicos para novos agendamentos.
- **Gerenciamento de Perfis:** Oferece funcionalidades para a atualização de informações de perfis de usuários e médicos.
- **Autenticação e Autorização:** Implementa mecanismos de autenticação e autorização para garantir a segurança dos dados.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

- `src/main/java/br/com/giuseppebs/aluraApi/`: Contém a lógica de negócio, serviços, controladores e repositórios.
- `src/main/resources`: Arquivos de configuração do Spring Boot.
- `src/test/java/br/com/giuseppebs/aluraApi/`: Testes automatizados para garantir a qualidade do código.

## Requisitos

- **Java 21+**: Linguagem de programação utilizada.
- **Maven**: Gerenciador de dependências.
- **Spring Boot 3.2+**: Framework principal para o desenvolvimento do projeto.
- **Banco de Dados**: foi utilizado o MySQL para armazenamento dos dados.

## Configuração do Ambiente de Desenvolvimento

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/giuseppe-bs/consultas-medicas.git
   cd consultas-medicas
   ```
2. **Execute o projeto:**
   ```bash
   mvn spring-boot:run
   ```
3. **Acesse a documentação da API:**
   ```
   http://localhost:8080/swagger-ui.html
   ```
4. **Explore os endpoints disponíveis e faça requisições para testar a aplicação.**