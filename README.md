# Minha Pokedex

O Minha Pokedex é um aplicativo Android nativo desenvolvido em Kotlin, que lista todos os pokemon através da [PokéAPI](https://pokeapi.co/docs/v2#pokemon), sendo possível também realizar uma busca por algum específico. Nele também é possível favoritar qualquer pokemon, sendo este então armazenado em um banco de dados local e disposto na lista de favoritos. Além disso, ao clicar em algum pokemon é mostrada uma tela de detalhes com informações adicionais do mesmo.

## Screenshots

<p align="center">
  <img src="https://user-images.githubusercontent.com/98563516/215861488-ebe7368e-262f-45e0-86dc-04a0ce6ae62a.jpg" width="300px" height="600px">   <img src="https://user-images.githubusercontent.com/98563516/215863116-46564202-5c3a-43e3-8f76-2691966b021b.jpg" width="300px" height="600px">
</p>

<p align="center">
<img src="https://user-images.githubusercontent.com/98563516/215863153-ce210915-027b-48b1-98b3-c828afeba23f.jpg" width="300px" height="600px">   <img src="https://user-images.githubusercontent.com/98563516/215863205-acc22042-16f5-45fa-80dc-ba0536638f29.jpg" width="300px" height="600px">
</p>

Obs: as prioridades dadas nesse app foram as funcionalidades implementadas, portanto o visual não está muito estilizado.

## Desenvolvimento

O app foi desenvolvido utilizando a arquitetura **MVVM** com os princípios do **Clean Architecture**. Ele conta com uma Bottom Navigation Bar, seguindo o Single
Activity Pattern, sendo a Main Activity a host dos demais fragmentos que contém as variadas views. Para isso o app faz o uso de diversos recursos e bibliotecas
que estão listadas e detalhadas abaixo.

### Recursos e Bibliotecas principais

- **Koin** - Framework de injeção de dependências, que provê uma api fácil e poderosa para recuperar as dependências em qualquer lugar nos componentes Android
- **Retrofit** - HTTP client usado para obtenção dos dados da [PokéAPI](https://pokeapi.co/docs/v2#pokemon)
- **Room DB** - Base de dados local que oferece uma camada de abstração sobre o SQLite
- **Paging 3** - Biblioteca que ajuda a carregar e exibir páginas de um conjunto de dados, que no caso é da rede. Dá a sensação de uma "lista sem fim", pois
busca novos dados antes de atingir o fim da lista visível ao usuário.
- **Glide** - Image loading framework para Android
- **MVVM e Clean** - Padrões de arquitetura
- **Custom dialogs** - Usados em casos de erros e também nos loadings
- **Navigation Component** - Usado para lidar com a navegação entre os fragmentos
- **Flow** - Stream de dados que são computados de forma assíncrona
- **Coroutines** - Usadas para realizar tarefas de modo assíncrono, que são tarefas de longa duração e se executadas de modo síncrono podem bloquear a linha
de execução principal e fazer com que o app pare de responder.
