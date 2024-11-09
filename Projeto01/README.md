# Documentação do Sistema de Gerenciamento de Projetos

O sistema de gerenciamento de projetos foi desenvolvido para facilitar o controle e a organização de projetos e suas tarefas, permitindo a associação de membros da equipe às tarefas, a visualização de status e a conclusão de tarefas. Além disso, o sistema permite o cadastro de projetos, membros da equipe, e tarefas, assim como a associação de membros às tarefas.

## Funcionalidades

O sistema oferece as seguintes funcionalidades principais:

- **Cadastro de Projetos**: Permite que novos projetos sejam criados no sistema.
- **Cadastro de Membros de Equipe**: Permite o registro de membros.
- **Deletar Membros de Equipe**: Permite deletar um membro específico.
- **Cadastro de Tarefas**: O sistema possibilita o cadastro de tarefas associadas a um projeto específico cadastrado.
- **Associação de Tarefas a Membros de Equipe**: As tarefas podem ser atribuídas a membros da equipe posteriormente.
- **Visualização de Tabelas**:
  - **Tabela de Projetos**: Exibe todos os projetos cadastrados.
  - **Tabela de Tarefas**: Exibe todas as tarefas que estejam marcadas como não concluídas, mostrando o responsável por cada uma.
  - **Tabela de Membros da Equipe**: Exibe todos os membros da equipe, com uma opção para visualizar as tarefas associadas a cada membro.
- **Marcação de Tarefas como Concluídas**: O sistema permite que uma tarefa seja marcada como concluída, mudando seu status.

## Tecnologias Utilizadas

O sistema foi desenvolvido utilizando as seguintes tecnologias:

- **Java**: A principal linguagem de programação usada no desenvolvimento do sistema.
- **JSP (JavaServer Pages)**: Utilizado para a criação das interfaces de usuário.
- **Servlets**: São responsáveis por controlar as requisições do usuário, processar os dados e fornecer a resposta. Os servlets se comunicam com o banco de dados, utilizando as DTOs para realizar operações de Create, Read e Update.
- **PostgreSQL ou MySQL**: Banco de dados utilizado para guardar os dados do sistema.
- **DTO (Data Transfer Objects)**: Usados para transportar dados entre o frontend, recebido pelos Servlets, e o backend.
- **JDBC (Java Database Connectivity)**: Para conectar o sistema ao banco de dados MySQL e realizar as operações de manipulação de dados (inserção, consulta, atualização, etc).

## Arquitetura do Sistema

O sistema segue uma arquitetura baseada no padrão **MVC (Model-View-Controller)**:

- **Model**: Contém as classes de modelo (Projeto, Tarefa e Equipe) com os seus respectivos getters e setters e listas.
- **View**: As páginas JSP, que são responsáveis pela apresentação e interação com o usuário.
- **Controller**: Os servlets, que recebem as requisições HTTP e os DTOs que, em conjunto, retornam as respostas.

A comunicação entre o front-end e o back-end é realizada através de requisições HTTP (GET e POST), que são tratadas pelos servlets e DTOs. As respostas do servidor são repassadas para as páginas JSP, que exibem os dados.

## Principal Dificuldade Encontrada

**Gerenciamento de Tarefas e Associação de Membros**: 

Uma das dificuldades enfrentadas foi a associação de tarefas a membros da equipe e a marcação de tarefas como concluídas. Existiram alguns conflitos nos caminhos de implementação, e para resolver isso, foram criados servlets com funcionalidades específicas. A solução envolveu o uso de atualizações (update) tanto para marcar uma tarefa como concluída quanto para associar uma tarefa a um membro da equipe.
