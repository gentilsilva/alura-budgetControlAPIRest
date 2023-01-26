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
* A API possui endPoints que aceitam requisições do tipo POST para as URIs
  * /incomes
  * /expenses
* Os dados serão enviados no corpo da requisição no formato JSON (JavaScript Object Notation)
* Todas as informações tanto de receita quanto de despesa são obrigatórias
* A API não permite o cadastro de receitas e despesas duplicadas (contendo mesma descrição, dentro do mesmo mês)

---

- [x] Listagem/Detalhando informações
* A API possui endpoints que aceitam requisições do tipo GET que irá listar todas as Receitas e Despesas para as URIs
  * /incomes
  * /expenses
* A API possui também um endpoint que aceita requisições do tipo GET passando o parâmetro ID nas URIs
  * /incomes/{id}
  * /expenses/{id}
* Todos os dados (descrição, valor e data) serão devolvidos no corpo das duas requisições

- [x] Atualizando dados
* A API possui endpoints que aceitam requisições do tipo PUT para atualizar as Receitas e Despesas para as URIs
  * /incomes/{id}
  * /expenses/{id}
* O ID informado na URI recupera os dados gravados no banco para serem atualizados.
* Ao atualizar deverão ser informadas descrição, valor e data e estas informações não podem ser duplicadas.

- [x] Deletando informações
* A API possui endpoinst que aceitam requisições do tipo DELETE para as URIs
  * /incomes/{id}
  * /expenses/{id}
* Os IDs serão referente aos dados que a aplicação deve deletar
* Essa requisição não apaga os dados permanente do banco, simplismente desativa estes
* Ao desativá-los, não será possível listar ou atualizar estes, mas será possível adicionar outra Receita ou Despesa com as mesmas informações
