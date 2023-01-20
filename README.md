# <h1 align="center"> API de controle orcamentário familiar </h1>

<p align="center">
<img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge"/>
</p>

# <h2 align="center"> Introdução </h2>

BudgetControlAPI é um projeto feito através do desafio proposto pela Alura - cursos online. 
Tem como objetivo construir uma aplicação capaz de realizar o controle orçamentário familiar.

Essa aplicação permitirá que uma pessoa cadastre as receitas e as despesas do mês e gerar um relatório mensal para o usuário.

O desafio é dividido em 4 semanas com diferentes tarefas para serem implementadas em cada uma destas.

# <h2 align="center"> Técnicas e tecnologias utilizadas </h2>

* Java 17
* Spring Boot 3
* Lambok
* MariaDB/Flyway
* JPA/Hibernate
* Maven
* Isomnia
* Intellij IDEA

# <h2 align="center"> 1º Semana  </h2>

- [x] Banco de Dados
* A modelagem do banco ficou da seguinte forma: 
  * Entidade Receita 
    * Id
    * Descrição
    * Valor
    * Data
  
  * Entidade Despesa
    * Id
    * Descrição
    * Valor
    * Data

- [x] Cadastro
* A API possui um endPoint que aceita requisições do tipo POST para as URIs
  * /incomes
  * /expenses
* Os dados dessas requisições serão enviados no corpo da requisição no formato JSON (JavaScript Object Notation);
* Todas as informações tanto de receita quanto de despesa são obrigatórias;
* A API não permit o cadastro de receitas e despesas duplicadas (contendo mesma descrição, dentro do mesmo mês);
