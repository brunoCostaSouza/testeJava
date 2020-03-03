# Teste Java - Platform Builders

### API criada para fornecer CRUD de clientes

Você precisa ter instalado na sua máquina:
- [java](https://www.java.com/pt_BR/download/)
- [git](https://git-scm.com/book/pt-br/v2/Come%C3%A7ando-Instalando-o-Git)
- [docker](https://hub.docker.com/)
- [docker-compose](https://docs.docker.com/compose/install/)

- Obs: A variável de ambiente JAVA_HOME deve está configurada. 

Instalação
Para instalar e deixar a API rodando é muito simples :) Você so precisa de quatro comandos...

```
git clone https://github.com/brunoCostaSouza/testeJava.git
cd testeJava/project
docker-compose up
./mvnw spring-boot:run
```

Pronto, Sua API estará rodando em http://localhost:8080/

## Rotas presentes na API

| ROTAS                   | VERBOS | OBJETIVO                                                                   |
|-------------------------|--------|----------------------------------------------------------------------------|
| /clientes               | GET    | Rota usada para listagem de todos clientes                                 |
| /clientes/search        | GET    | Rota usada para listar os clientes por nome e/ou CPF                       |
| /clientes               | POST   | Rota para inserir um novo cliente                                          |
| /clientes/{cpf}         | DELETE | Rota usada remover um cliente por CPF                                      |
| /clientes/{id}          | PUT    | Rota para atualizar um cliente por ID                                      |
| /clientes               | PATCH  | Rota para atualizar o nome, cpf ou data de nascimento de um cliente por ID |

- A collection para testar a API no Postman está na raiz do projeto: APITesteJava.postman_collection.json

## Tecnologias usadas

* [JAVA](https://www.java.com/pt_BR/) - A linguagem usada
* [DOCKER](https://hub.docker.com/) - Container utilizado para o banco de dados
* [SPRING-BOOT](https://start.spring.io/) para configuração do projeto
* [SPRING-JPA](https://spring.io/projects/spring-data-jpa) - Para persistência de dados


## Authors

* **Bruno Costa** - *Trabalho completo* - [brunoCostaSouza](https://github.com/brunoCostaSouza)
