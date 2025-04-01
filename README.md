📋 Sistema de Gerenciamento de Tarefas com Spring Boot
Este é um projeto desenvolvido durante um curso de Java com Spring Boot que tem como objetivo construir uma API RESTful para gerenciamento de tarefas. O sistema permite o cadastro de usuários e a criação, listagem e atualização de tarefas de maneira segura, com autenticação básica.

🚀 Funcionalidades
📌 Cadastro de Usuário
Criação de conta com senha criptografada (BCrypt)
Validação para evitar usuários duplicados
📝 Gerenciamento de Tarefas
Criação de tarefas com título, descrição, datas de início e fim, e prioridade
Validação de datas para garantir integridade dos dados
Atualização parcial de tarefas (apenas campos não nulos são atualizados)
Listagem de tarefas específicas do usuário autenticado
🔐 Autenticação
Filtro personalizado que protege as rotas de tarefas
Autenticação via HTTP Basic com verificação de senha criptografada
🛠️ Infraestrutura
Utiliza Spring Boot 3.4.4
Banco de dados em memória H2 com acesso via /h2-console
Lombok para reduzir boilerplate
JPA com Hibernate para persistência de dados
Testes com JUnit e Spring Boot Starter Test
🧪 Tecnologias
Java 17
Spring Boot
Spring Data JPA
H2 Database
Lombok
BCrypt
Maven
