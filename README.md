# <p align="center"> Google Keep Tabajara </p>

### <p align="center"> API para anotações  </p>

  <p align="center" >
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=black" height="25">
    <img src="https://img.shields.io/badge/Spark%20AR-FF5C83?style=for-the-badge&logo=Spark AR&logoColor=black" height="25">
    <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=black" height="25">
  </p>

## Objetivo:

O objetivo deste trabalho é desenvolver uma aplicação similar ao Google Keep, chamada Google Keep Tabajara. A aplicação deve permitir o cadastro de novas anotações, cada uma contendo um título, data/hora, descrição e cor. Além disso, a aplicação deve permitir a exclusão de anotações, com a possibilidade de enviar as anotações para a Lixeira, onde é possível restaurá-las ou excluí-las definitivamente. Também é necessário permitir a alteração de anotações, listagem de anotações com ordenação por data/hora de criação e a possibilidade de copiar uma anotação existente.

## Como utilizar o sistema:

1. Crie um arquivo chamado `.env` para armazenar os dados de conexão com o postgres, seguindo o padrão do arquivo `example.env`:
```
POSTGRES_HOST=localhost
POSTGRES_DBNAME=dbName
POSTGRES_USERNAME=postgres
POSTGRES_PASSWORD=postgres
POSTGRES_PORT=5432
```

2. Configure o `pom.xml` com a versão do java instalado em sua maquina

```xml
 <maven.compiler.source>20</maven.compiler.source>
 <maven.compiler.target>20</maven.compiler.target>   
```

3. Rode o script `keeptabajara.sql` para criar a database no postgres, no console siga as instruções abaixo:

```sql
pqsl -U postgres
\i keeptabajara.sql
```

4. Execute o arquivo App.java que esta na pasta apresentacao.

## Rotas
Foram implementadas as seguintes rotas no projeto: 

* GET <br>
```https://localhost:8080/```

* GET /obter/:id <br>
```https://localhost:8080/obter/:id```

* GET /ordenarPorDataCriacao <br>
```https://localhost:8080/ordenarPorDataCriacao```

* GET /lixeira <br>
```https://localhost:8080/lixeira```

* POST /adicionar <br>
```https://localhost:8080/adicionar```

* PUT /editar/:id <br>
```https://localhost:8080/editar/:id```

* PUT /duplicar/:id <br>
```https://localhost:8080/duplicar/:id```

* PUT /restaurar/:id <br>
```https://localhost:8080/restaurar/:id```

* DELETE /excluir/:id <br>
```https://localhost:8080/excluir/:id```

* DELETE /esvaziarLixeira <br>
```https://localhost:8080/esvaziarLixeira```

Para utilizar as rotas apresentadas neste documento basta possuir o postman, ou similar, instalado no seu computador. <br>

### POST /adicionar

<p> Preencha o campo response do Postman com o seguinte formato: <br>

```json 
{
  "titulo": "Um titulo",
  "descricao": "Descrição da Anotação",
  "cor": "FF0000"
}
```
- ```titulo``` é o titulo da sua anotação; <br>
- ```descricao``` é o que deseja salvar como anotação.<br>
- ```cor``` é a cor da anotação, você deve passar o hexadecimal da cor.<br>

<p> No campo response será retornado um JSON conforme abaixo: <br>

```json
{
    "Anotação adicionada com sucesso!"
}
``` 


# Estrutura de Pastas do Projeto

```
.
|—— keeptabajara.sql
|—— pom.xml
|—— README--.md
|—— src
|    |—— main
|        |—— java
|            |—— .env
|            |—— .env.example
|            |—— apresentacao
|                |—— App.java
|            |—— controller
|                |—— AnotacaoController.java
|            |—— model
|                |—— Anotacao.java
|            |—— repository
|                |—— AnotacaoDAO.java
|                |—— ConexaoPostgreSQL.java
|            |—— routes
|                |—— AnotacaoRoutes.java
|            |—— util
|                |—— utils.java
|            |—— view
|                |—— AnotacaoView.java
|        |—— resources
```

***

# Desenvolvedor

[<img src="https://avatars.githubusercontent.com/u/29494433?v=4?s=400&u=071f7791bb03f8e102d835bdb9c2f0d3d24e8a34&v=" width=115 > <br> <sub> John Marcel Silveira </sub>](https://github.com/JohnMarcelSilveira) |
| :---: |  

# Licença

Este projeto está licenciado sob a Licença MIT - consulte o [Link](https://mit-license.org/) para obter mais detalhes.
