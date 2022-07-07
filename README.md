# Projeto Haunted Castle

# Descrição Resumida do Projeto/Jogo

> O jogo Haunted Castle é baseado no estilo de jogo roguelike, onde o herói precisa vasculhar as salas na procura do vilão principal. Para tanto, ele irá precisar derrotar inimigos aliados do vilão. Na sua jornada, o héroi irá ficar mais forte e encontrar itens e equipamentos. Além disso, esse jogo não possui check points ou savepoints, então esse jogo não é muito longo e permite recomeçar o jogo caso o herói morra ou queira melhorar seu tempo (matar o vilão no menor tempo possível).

>W - Andar para cima
>
>A - Andar para a esquerda
>
>S - Andar para baixo
>
>D - Andar para a direita
>
>Q - Usar poção

>Para atacar, avance na direção do monstro

>R - Recomaçar a partida quando morrer ou ganhar

# Equipe
>* Artur
>* Marcos

# Arquivo Executável do Jogo

[Arquivo jar (jdk 17)](assets/game.jar)

[Arquivo jar (jdk 11)](assets/game_jdk11.jar)

PS: Para que o jogo funcione é necessário colocar o arquivo jar e a pasta assets no mesmo local.


# Slides do Projeto

## Slides da Apresentação Final
[Apresentação](assets/apresentacao.pptx)

[link google docs](https://docs.google.com/presentation/d/1B6teRCCtoXIbOURUIhgIFJ0BdB3L0MHDZnfgQQkIvsU/edit?usp=sharing)

# Diagramas

## Diagrama Geral da Arquitetura do Jogo

![](assets/arquitetura.png)

## Relatório de Evolução
> Relatório de evolução, descrevendo as evoluções do design do projeto, dificuldades enfrentadas, mudanças de rumo, melhorias e lições aprendidas. Referências aos diagramas e recortes de mudanças são bem-vindos.

>O primeiro desafio foi desenvolver um primeiro esboço da arquitetura foi a falta de experiência em como criar uma interface de jogo. Então vários testes foram realizados para testar o java swing e entender o funcionamento da criação de uma interface gráfica.
>
>No nosso jogo, algumas classes são instanciadas apenas uma vez. Em um primeiro momento, méthodos estáticos para evitar uma dupla instanciação. Após a aula de design patterns, lazy singleton foi implementado.
>
>A complexidade do nosso jogo depende diretamente da nossa criatividade para gerenciar os eventos durante o jogo. Então, foi difícil ponderar o que deveríamos fazer em uma primeira versão. Então, elaborou-se uma arquitetura de jogo base que pudesse receber upgrades.
>
>A importancia do planejamento do projeto de maneira exaustiva. Ao longo do desenvolvimento do projeto, houve momentos em que adaptações foram feitas pois não tinhamos considerado certos aspectos no planejamento inicial.  Por exemplo, como reiniciar o jogo uma vez que o jogo chega ao seu fim (vitória ou derrota).

# Destaques de Código

> O primeiro destaque é o uso do método paintComponent do java swing para a criação da interface gráfica. O jogo possui 4 estados (tela inicial, tela principal, tela de vitória e tela de derrota) e a renderização é feita com base nesse estado. Por exemplo, no estado 1 (player jogando) o método vai ler o modelo e gerar o mapa. Além disso, o método paintComponent não precisa ser chamado em nenhum momento pois isso é feita de forma autônoma pelo javaswing, ou seja, o método será chamado quando ele detectar uma mudança (como se fosse um observer).

~~~java
public void paintComponent(Graphics graphics) {
		super.paintComponents(graphics);
		try {
			if(god.getGameState() == 0) {
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
			renderer.firstScreen(graphics);
			}
			else if(god.getGameState() == 1) {
				if(!running) {
					this.startTime = System.currentTimeMillis();
					running = true;
				}
				graphics.setColor(Color.BLACK);
				graphics.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
				//renderer.drawFloor(graphics, castle.getCurrentFloor());
				//renderer.drawActors(graphics, castle.getCurrentFloor());
				renderer.rayTracing(graphics, castle.getCurrentFloor());
				renderer.messageBox(graphics, message);
				renderer.heroStatus(graphics, castle.getCurrentFloor().getHero(),(System.currentTimeMillis()-startTime)/1000);
			}
			else if(god.getGameState() == -1) {
				//gameover
				if(running) {
					this.stopTime = System.currentTimeMillis();
					this.running = false;
				}
				renderer.gameOverScreen(graphics, (stopTime - startTime)/1000);
				if(bestTime > 99999999)
					renderer.bestTime(graphics, 0);
				else
					renderer.bestTime(graphics, bestTime);
			}
			else if(god.getGameState() == 2) {
				//win
				if(running) {
					this.stopTime = System.currentTimeMillis();
					this.running = false;
				}
				renderer.victoryScreen(graphics, (stopTime - startTime)/1000);
				if((stopTime - startTime)/1000 < bestTime)
					bestTime = (stopTime - startTime)/1000;
				renderer.bestTime(graphics, bestTime);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		repaint();
	}
~~~



# Destaques de Orientação a Objetos
> No jogo, as classes Hero e Enemy são classes filhas da classe mãe Actor. No método gameloop, um vetor actors que contém todos os inimigos e o herói é percorrido a cada iteração. Na versão atual, apenas o herói é inteligente o suficiente para usar itens, poções, e etc. A vantagem desse tipo de implementação é a facilidade de adaptar o código para fazer com que os inimigos possuam uma certa inteligência e também possam usar items como o herói.


## Código do Destaque OO
~~~java

	public void gameLoop() {
		boolean quit = false;
		while (quit == false) {
			sortFloorActors();
			int i = 0;
			while (gameState == 1) { // hp > 0; bossAlive.
				IActor actor = floorActors.get(i);
				actor.setEnergy(actor.getEnergy() + actor.getSpeed() * 10);
				if (actor.getEnergy() >= 1000) {
					while (true) {
						try {
							act(actor);
							break;
						} catch (InvalidMovement e) {
							e.printStackTrace();
						}
					}
					actor.setEnergy(actor.getEnergy() - 1000);
				}
				i = (i + 1) % floorActors.size();
				if (hero.getHp() <= 0)
					gameState = -1;
				else if (!bossAlive)
					gameState = 2;
				
			}
			while (gameState == -1 || gameState == 2) {
				System.out.print("");
				if (nextAction.getKey() == KeyEvent.VK_R) {
					gameState = 0;
					restart();
					
				}
			}
		}
	}
~~~

# Destaques de Pattern

## Diagrama do Pattern
> Foram usados 2 design patterns:
> 
> Singleton: Algumas classes só precisam ser instanciadas uma vez e o uso desse design pattern garante isso.
> 
>Facade: Do ponto de vista do usuário, quanto mais simples for para lançar o jogo melhor. A implementação desse design pattern faz com que o usuário precise apenas abrir o jogo e jogar!

## Código do Pattern
~~~java
public class Assembler {
	private static Assembler assembler;
	private IWindow window;
	private ICastleController castle;
	
	private Assembler() {}
	
	public static Assembler getInstance() {
		if (assembler == null) {
			assembler = new Assembler();
		}
		return assembler;
	}
	
public class App {
	private static God god;
	private static Assembler assembler;
	
	public static void main(String[] args) {
		executeGame(); //facade pattern
	}
	
	private static void executeGame() {
		assembler = Assembler.getInstance();
		assembler.newGame();
		god = God.getInstance();
		god.nameHero();
		god.gameLoop();
	}
}
~~~

# Conclusões e Trabalhos Futuros

>Por ser um jogo de RPG, várias melhorias podem ser feitas para melhorar a experiência do usuário e deixar o jogo mais divertido/desafiador. No início, foi difícil ponderar o que poderia ser feito no tempo de um mês. Abaixo seguem algumas melhorias que poderiam ser implementadas:
>
>Mais tipos de ações: atualmente o herói pode se mover, usar poção e atacar um inimigo. Ações como esconder, fugir, e defender poderiam ser adicionados ao jogo.
>
>Movimentação mais otimizada monstros: Em certas condições, é possível prender um monstro em uma parede e o monstro não consegue perseguir o herói. Isso é bem díficil de acontecer na versão atual, mas algo mais interessante poderia ser feito utilizando grafos.
>
>Novos itens, texturas, monstros, equipamentos: Melhorias no design do jogo de maneira geral.
>
>Multiplayer: Adicionar um segundo herói ou mesmo um pet que ajude o herói (pode ser um bot ou um outro jogador)
>
>Sistema de ataque mais complexo: Ataques à distância, uso de magias
>
>Mais inteligência para os monstros: Na versão atual, os monstros apenas seguem o herói quando ele se aproxima e ataca ele até a sua morte. Alguns monstros, poderiam ser mais inteligentes e fazer ações mais estratégicas do que apenas perseguir e atacar.
>
>Implementação de efeitos sonoros: A proposta inicial era usar sons de suspenses, músicas de combate, sons de ataques e etc, mas devido há um bug que acontecia em função do computador utilizado, isso não foi implementado.
>
>Raytracing: Na versão atual, alguns cantos não são exibidos quando deriam ser. Então uma melhoria seria criar um lógica que melhorasse esse aspecto.









# Documentação dos Componentes

O vídeo a seguir apresenta um detalhamento de um projeto baseado em componentes:

[![Projeto baseado em Componentes](http://img.youtube.com/vi/1LcSghlin6o/0.jpg)](https://youtu.be/1LcSghlin6o)

# Diagramas

## Diagrama Geral da Arquitetura do Jogo

> Apresente um diagrama geral da arquitetura do jogo. O formato é livre. A escolha de um ou mais estilos arquiteturais será considerado um diferencial.

> Faça uma breve descrição do diagrama.

## Diagrama Geral de Componentes

> Se você adotou componentes de software, apresente a documentação de componentes conforme o modelo.

### Exemplo 1

Este é o diagrama compondo componentes para análise:

![Diagrama Analise](diagrama-componentes-analise.png)

### Exemplo 2

Este é um diagrama inicial do projeto de jogos:

![Diagrama Jogos](diagrama-componentes-jogos.png)

### Exemplo 3

Este é outro diagrama de um projeto de vendas:

![Diagrama Vendas](diagrama-componentes-vendas.png)

Para cada componente será apresentado um documento conforme o modelo a seguir:

## Componente `<Nome do Componente>`

> Resumo do papel do componente e serviços que ele oferece.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `<listagem das interfaces do componente>`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `<nome da interface>`

`<Resumo do papel da interface.>`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Exemplo:

### Interface `ITableProducer`

Interface provida por qualquer fonte de dados que os forneça na forma de uma tabela.

~~~java
public interface ITableProducer {
  String[] requestAttributes();
  String[][] requestInstances();
}
~~~

Método | Objetivo
-------| --------
`requestAttributes` | Retorna um vetor com o nome de todos os atributos (colunas) da tabela.
`requestInstances` | Retorna uma matriz em que cada linha representa uma instância e cada coluna o valor do respectivo atributo (a ordem dos atributos é a mesma daquela fornecida por `requestAttributes`.

### Interface `IDataSetProperties`

Define o recurso (usualmente o caminho para um arquivo em disco) que é a fonte de dados.

~~~java
public interface IDataSetProperties {
  public String getDataSource();
  public void setDataSource(String dataSource);
}
~~~

Método | Objetivo
-------| --------
`getDataSource` | Retorna o caminho da fonte de dados.
`setDataSource` | Define o caminho da fonte de dados, informado através do parâmetro `dataSource`.

# Plano de Exceções

## Diagrama da hierarquia de exceções
> Elabore um diagrama com a hierarquia de exceções como detalhado a seguir.

![Hierarquia Exceções](exception-hierarchy.png)

## Descrição das classes de exceção

> Monte uma tabela descritiva seguindo o exemplo:

Classe | Descrição
----- | -----
DivisaoInvalida | Engloba todas as exceções de divisões não aceitas.
DivisaoInutil | Indica que a divisão por 1 é inútil.
DivisaoNaoInteira | Indica uma divisão não inteira.

