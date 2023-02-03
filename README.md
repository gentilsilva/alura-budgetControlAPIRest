# <h1 align="center"> API REST de controle orcamentário familiar </h1>

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
* Intellij IDEA -> Eclipse

# <h2 align="center"> 1º Semana  </h2>

- [x] Banco de Dados
* A modelagem do banco ficou da seguinte forma: 
  * Entidade Receita 
    * Id - como id
    * Descrição - como description
    * Valor - como entryValue
    * Data - como createAt
  
  * Entidade Despesa
    * Id - como id
    * Descrição - como description
    * Valor - como entryValue
    * Data - como createAt

---

- [x] Cadastro
* A API possui endPoints que aceitam requisições do tipo POST para as URIs
  * /incomes - referente a Receitas
  * /expenses - referente a Despesas
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

---

- [x] Atualizando dados
* A API possui endpoints que aceitam requisições do tipo PUT para atualizar as Receitas e Despesas para as URIs
  * /incomes/{id}
  * /expenses/{id}
* O ID informado na URI recupera os dados gravados no banco para serem atualizados.
* Ao atualizar deverão ser informadas descrição, valor e data e estas informações não podem ser duplicadas.

---

- [x] Deletando informações
* A API possui endpoinst que aceitam requisições do tipo DELETE para as URIs
  * /incomes/{id}
  * /expenses/{id}
* Os IDs serão referente aos dados que a aplicação deve deletar
* Essa requisição não apaga os dados permanente do banco, simplismente desativa estes
* Ao desativá-los, não será possível listar ou atualizar estes, mas será possível adicionar outra Receita ou Despesa com as mesmas informações

# <h2 alignt="center"> 2º Semana </h2>

- [x] Banco de Dados
* Adicionado em:
  * Entidade Despesa
    * Categoria - como category

---

- [x] Categorização de despesas
* Despesas possuem uma das seguintes categorias
  * Alimentação
  * Saúde
  * Moradia
  * Transporte
  * Educação
  * Lazer
  * Imprevistos
  * Outras
* Ao cadastrar despesa, a opção categoria é opcional.
* Se a categoria não for informada, a API atribui automaticamente o valor Outras.

---

- [x] Buscar Receitas/Despesas
* A API possui um endpoint que aceita requisições do tipo GET para busca de receitas e despesas por descrição ou/e categoria
* O endpoint é o mesmo utilizado pela listagem de receitas, mas incluindo um parâmetro chamado descrição ou/e categoria
  * /incomes?description={descrição} ou /incomes?category={categoria}
  * /incomes?description={descrição}?category={categoria}
  * /expenses?description={descrição} ou /expenses?category={categoria}
  * /expenses?description={descrição}?category={categoria}

---

- [x] Listar Receitas/Despesas por mês
* A API possui um endpoint com requisição do tipo GET para listar receitas e despesas por ano/mês
* Ano e mês serão passados pela URI e serão retornados os os dados descrição, valor e data em formato JSON
  * /incomes/{ano}/{mes}
  * /expenses/{ano}/{mes}

---

- [x] Resumo do mês
* A API possui um endpoint que aceita requisição do tipo GET para as URIs
  * /summary/{ano}/{mes}
* O resumo contém as seguintes informações
  * Valor total da Receita no mês
  * Valor total da Despesa no mês
  * Saldo final no mês
  * Valor total gasto no mês em cada categoria 

--- 

- [ ] Testes automatizados
* A ser implementado
