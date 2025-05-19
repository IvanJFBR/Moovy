## Moovy

Uma aplicação android que consome dados da api do TMDB (The Movie Database).

A proposta é construir uma listagem de filmes em uma arquitetura MVVM + Clean Architecture com o plus de criar uma pagina de busca usando Room para persistir dados e salvar buscas recentes.

Na projeção inicial um dos objetivos seria desenvolver uma parte para exibição e controle de favoritos, mas essa parte exige a criação de conta na base do TMDB o que demanda um tempo maior, essa feature foi colocada para desenvolvimento futuro.

### Stack usada nesta aplicação

- Retrofit – Cliente HTTP para consumir APIs REST.

- Gson – Biblioteca para converter JSON em objetos Kotlin/Java.

- Flow – Tipo assíncrono que emite múltiplos valores ao longo do tempo.

- Hilt – Framework de injeção de dependência para Android.

- Navigation Components – Gerencia a navegação entre telas no app.

- Mockito Kotlin – Biblioteca para criar mocks e testar comportamentos.

- OkHttp – Cliente HTTP eficiente e usado internamente pelo Retrofit.

- Coil – Biblioteca leve para carregar imagens com Compose.

- Room – Abstração do SQLite para persistência local com ORM.

- Material Compose – Componentes de UI baseados no Material Design para Compose.

### Considerações para rodar o projeto

é necessário colocar no seu arquivo `local.properties` no root do projeto a linha TMDB_API_KEY=[sua chave api] para que funcione corretamente
