## API de Gerenciamento de Filmes
### Descrição
A API de Gerenciamento de Filmes é uma aplicação para gerenciar informações sobre filmes, incluindo detalhes como título, gênero, estúdio, diretores, elenco, serviço de streaming, entre outros.
Obs: Descrição da API atualizada no link do postman na descrição ao lado.

🛠 Tecnologias
- Java 17
- JPA
- Banco de dados H2
- Postman

### Recursos Disponíveis
**Filmes**: Os filmes são a entidade principal da API e possuem diversos atributos para descrever suas características.<br>
**Usuários**: Usuários podem adicionar os filmes favoritos

#### Endpoints da API

## Endpoint do Usuário

### GET /users

Este endpoint envia uma solicitação HTTP GET para recuperar uma lista de usuários do servidor.
Security, colocar user e senha o código gerado, em progresso a funcionalidade.

#### Requisição

- Método: GET
- URL: `http://localhost:8080/users`

#### Resposta

A resposta será em formato JSON com uma matriz de objetos de usuário, cada um contendo os seguintes campos:

- `id`: O identificador único do usuário.
- `name`: O nome do usuário.
- `username`: O nome de usuário do usuário.
- `email`: O endereço de e-mail do usuário.
- `password`: A senha do usuário.

O status da última execução foi 200, indicando uma solicitação bem-sucedida.

#### Exemplo de Resposta

```json
[
    {
        "id": 1,
        "name": "João Silva",
        "username": "joaosilva",
        "email": "joao@example.com",
        "password": "senha123"
    },
    {
        "id": 2,
        "name": "Maria Souza",
        "username": "mariasouza",
        "email": "maria@example.com",
        "password": "senha456"
    }
]
```


**GET /movies**: Retorna todos os filmes cadastrados.<br>
**GET /movies/{id}**: Retorna os detalhes de um filme específico com base no seu ID.<br>
**POST /movies**: Adiciona um novo filme ao sistema.<br>
**PUT /movies/{id}**: Atualiza as informações de um filme existente com base no seu ID.<br>
**DELETE /movies/{id}**: Remove um filme do sistema com base no seu ID.

1. Listar todos os filmes: Faça uma requisição GET para /movies para obter uma lista de todos os filmes cadastrados.<br>

2. Obter detalhes de um filme específico: Faça uma requisição GET para /movies/{id}, onde {id} é o ID do filme desejado, para obter os detalhes desse filme específico.<br>

3. Criar um novo filme: Faça uma requisição POST para /movies com os dados do novo filme no corpo da requisição em formato JSON.<br>

4. Atualizar informações de um filme: Faça uma requisição PUT para /movies/{id}, onde {id} é o ID do filme que você deseja atualizar, e inclua os novos dados do filme no corpo da requisição em formato JSON.<br>

5. Excluir um filme: Faça uma requisição DELETE para /movies/{id}, onde {id} é o ID do filme que você deseja excluir.

| ID | Title                             | Genre ID | Genre Name | Studio ID | Studio Name    | Studio Country | Franchise ID | Franchise Name            | Franchise Genre ID | Franchise Genre Name | Franchise Studio ID | Franchise Studio Name | Franchise Studio | Country | Streaming ID | Streaming Name               | Streaming URL | Actor ID | Actor Name        |
|----|-----------------------------------|----------|------------|-----------|----------------|----------------|--------------|---------------------------|--------------------|----------------------|---------------------|-----------------------|------------------|---------|--------------|------------------------------|---------------|----------|-------------------|
| 1  | Vingadores: Ultimato              | 1        | Ação       | 1         | Marvel Studios | EUA            | 1            | Marvel Cinematic Universe | 1                  | Ação                 | 1                   | Marvel Studios        | EUA              | 1       | DisneyPlus   | http://www.disneyplus.com.br | 1             | 1        | Samuel L. Jackson |
| 2  | Homem-Aranha: Sem Volta Para Casa | 1        | Ação       | 1         | Marvel Studios | EUA            | 1            | Marvel Cinematic Universe | 1                  | Ação                 | 1                   | Marvel Studios        | EUA              | 1       | DisneyPlus   | http://www.disneyplus.com.br | 1             | 2        | Tom Holland       |



### Como Usar

### Restrições e Pré-requisitos
Antes de cadastrar um filme na API, é necessário observar algumas restrições e pré-requisitos devido à estrutura do banco de dados:

#### Restrições:

**Cadastro de Gênero e Estúdio**: Antes de cadastrar um filme, é necessário que o gênero e o estúdio associados ao filme já estejam cadastrados na API. O filme deve ser atribuído a um gênero existente e a um estúdio existente.

**Cadastro de Franchise (Franquia)**: Se o filme pertencer a uma franquia específica, tanto o gênero quanto o estúdio associados à franquia devem ser cadastrados previamente na API.

**Cadastro de Atores e Diretores**: Os atores e diretores que participam do filme devem estar cadastrados na API antes de serem associados a um filme.

#### Pré-requisitos:
Cadastre os Gêneros, Estúdios, Franchises, Atores e Diretores relevantes antes de cadastrar um novo filme.

Certifique-se de que todas as informações necessárias estão disponíveis antes de realizar o cadastro de um filme.

Por favor, observe essas restrições e pré-requisitos ao utilizar a API para garantir o correto funcionamento das operações de cadastro de filmes.


## Endpoint de Filmes

### GET /movies

Este endpoint envia uma solicitação HTTP GET para recuperar uma lista de filmes do servidor.

#### Requisição

- Método: GET
- URL: `http://localhost:8080/movies`

#### Resposta

A resposta será em formato JSON com uma matriz de objetos de filme, cada um contendo os seguintes campos:

- `id`: O identificador único do filme.
- `title`: O título do filme.
- `genre`: Detalhes sobre o gênero do filme, incluindo `id` e `genreName`.
- `studio`: Detalhes sobre o estúdio que produziu o filme, incluindo `id`, `name` e `country`.
- `franchise`: Detalhes sobre a franquia à qual o filme pertence, incluindo `id`, `name`, `genre` e `studio`.
- `streaming`: Detalhes sobre a plataforma de streaming do filme, incluindo `id`, `name` e `url`.
- `actors`: Uma matriz de objetos contendo detalhes sobre os atores no filme, cada um com `id` e `name`.
- `directors`: Uma matriz de objetos contendo detalhes sobre os diretores do filme, cada um com `id` e `name`.

O status da última execução foi 200, indicando uma solicitação bem-sucedida.

#### Exemplo de Resposta

```json
[
    {
        "id": 1,
        "title": "Vingadores: Ultimato",
        "genre": {
            "id": 1,
            "genreName": "Ação"
        },
        "studio": {
            "id": 1,
            "name": "Marvel Studios",
            "country": "EUA"
        },
        "franchise": {
            "id": 1,
            "name": "Marvel Cinematic Universe",
            "genre": {
                "id": 1,
                "genreName": "Ação"
            },
            "studio": {
                "id": 1,
                "name": "Marvel Studios",
                "country": "EUA"
            }
        },
        "streaming": {
            "id": 1,
            "name": "DisneyPlus",
            "url": "http://www.disneyplus.com.br"
        },
        "actors": [
            {
                "id": 1,
                "name": "Samuel L. Jackson"
            },
            {
                "id": 2,
                "name": "Brie Larson"
            },
            {
                "id": 3,
                "name": "Chris Evans"
            }
        ],
        "directors": [
            {
                "id": 1,
                "name": "Joe Russo"
            },
            {
                "id": 2,
                "name": "Anthony Russo"
            }
        ]
    },
    {
        "id": 2,
        "title": "Homem-Aranha: Sem Volta Para Casa",
        "genre": {
            "id": 1,
            "genreName": "Ação"
        },
        "studio": {
            "id": 1,
            "name": "Marvel Studios",
            "country": "EUA"
        },
        "franchise": {
            "id": 1,
            "name": "Marvel Cinematic Universe",
            "genre": {
                "id": 1,
                "genreName": "Ação"
            },
            "studio": {
                "id": 1,
                "name": "Marvel Studios",
                "country": "EUA"
            }
        },
        "streaming": {
            "id": 1,
            "name": "DisneyPlus",
            "url": "http://www.disneyplus.com.br"
        },
        "actors": [
            {
                "id": 1,
                "name": "Samuel L. Jackson"
            },
            {
                "id": 2,
            }
         ]
        }
