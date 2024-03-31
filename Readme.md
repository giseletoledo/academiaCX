## API de Gerenciamento de Filmes
### Descrição
A API de Gerenciamento de Filmes é uma aplicação para gerenciar informações sobre filmes, incluindo detalhes como título, gênero, estúdio, diretores, elenco, serviço de streaming, entre outros.

### Recursos Disponíveis
**Filmes**: Os filmes são a entidade principal da API e possuem diversos atributos para descrever suas características.<br>
**Usuários**: Usuários podem adicionar os filmes favoritos

#### Endpoints da API
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

### Corpo da Requisição
**id (número)**: O identificador único para o filme.<br>
**title (string)**: O título do filme.<br>
**genre (objeto)**: Detalhes sobre o gênero do filme.<br>
**id (número)**: O identificador único para o gênero.<br>
**genreName (string)**: O nome do gênero.<br>
**studio (objeto)**: Detalhes sobre o estúdio que produz o filme.<br>
**id (número)**: O identificador único para o estúdio.<br>
**name (string)**: O nome do estúdio.<br>
**country (string)**: O país onde o estúdio está localizado.<br>
**franchise (objeto)**: Detalhes sobre a franquia à qual o filme pertence.<br>
**id (número)**: O identificador único para a franquia.<br>
**name (string)**: O nome da franquia.<br>
**genre (objeto)**: Detalhes sobre o gênero da franquia.<br>
**id (número)**: O identificador único para o gênero.<br>
**genreName (string)**: O nome do gênero.<br>
**studio (objeto)**: Detalhes sobre o estúdio que produz a franquia.<br>
**id (número)**: O identificador único para o estúdio.<br>
**name (string)**: O nome do estúdio.<br>
**country (string)**: O país onde o estúdio está localizado.<br>
**directors (array)**: Um array de objetos contendo detalhes sobre os diretores do filme.<br>
**id (número)**: O identificador único para o diretor.<br>
**name (string)**: O nome do diretor.<br>
**movies (array)**: Um array de filmes dirigidos pelo diretor.<br>
**streaming (objeto)**: Detalhes sobre a plataforma de streaming para o filme.<br>
**id (número)**: O identificador único para a plataforma de streaming.<br>
**name (string)**: O nome da plataforma de streaming.<br>
**url (string)**: O URL para fazer streaming do filme.<br>
**actors (array)**: Um array de objetos contendo detalhes sobre os atores no filme.<br>
**id (número)**: O identificador único para o ator.
**name (string)**: O nome do ator.






