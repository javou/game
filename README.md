# Projeto Haunted Castle

# Descrição Resumida do Projeto/Jogo

> O jogo Haunted Castle é baseado no estilo de jogo roguelike, onde o herói precisa vasculhar as salas na procura do vilão principal. Para tanto, ele irá precisar derrotar inimigos aliados do vilão. Na sua jornada, o héroi irá ficar mais forte e encontrar itens e equipamentos. Além disso, esse jogo não possui check points ou savepoints, então esse jogo não é muito longo e permite recomeçar o jogo caso o herói morra ou queira melhorar seu tempo (matar o vilão no menor tempo possível).

W - Andar para cima

A - Andar para a esquerda

S - Andar para baixo

D - Andar para a direita

Q - Usar poção

Para atacar, avance na direção do monstro

R - Recomaçar a partida quando morrer ou ganhar

# Equipe
* Artur
* Marcos

# Arquivo Executável do Jogo

[Arquivo jar (jdk 17)](assets/game.jar)

[Arquivo jar (jdk 11)](assets/game_jdk11.jar)


# Slides do Projeto

## Slides da Apresentação Final
[Apresentação](assets/apresentacao.pptx)

[link google docs](https://docs.google.com/presentation/d/1B6teRCCtoXIbOURUIhgIFJ0BdB3L0MHDZnfgQQkIvsU/edit?usp=sharing)

# Diagramas

## Diagrama Geral da Arquitetura do Jogo

![](assets/arquitetura.png)

## Relatório de Evolução
> Relatório de evolução, descrevendo as evoluções do design do projeto, dificuldades enfrentadas, mudanças de rumo, melhorias e lições aprendidas. Referências aos diagramas e recortes de mudanças são bem-vindos.

O primeiro desafio foi desenvolver um primeiro esboço da arquitetura foi a falta de experiência em como criar uma interface de jogo. Então vários testes foram realizados para testar o java swing e entender o funcionamento da criação de uma interface gráfica.
No nosso jogo, algumas classes são instanciadas apenas uma vez. Em um primeiro momento, méthodos estáticos para evitar uma dupla instanciação. Após a aula de design patterns, lazy singleton foi implementado.
A complexidade do nosso jogo depende diretamente da nossa criatividade para gerenciar os eventos durante o jogo. Então, foi difícil ponderar o que deveríamos fazer em uma primeira versão. Então, elaborou-se uma arquitetura de jogo base que pudesse receber upgrades.
A importancia do planejamento do projeto de maneira exaustiva. Ao longo do desenvolvimento do projeto, houve momentos em que adaptações foram feitas pois não tinhamos considerado certos aspectos no planejamento inicial.  Por exemplo, como reiniciar o jogo uma vez que o jogo chega ao seu fim (vitória ou derrota).

