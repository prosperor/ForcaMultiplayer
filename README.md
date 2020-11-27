# ForcaMultiplayer

O projeto implementa um jogo de forca multijogador, em que cada jogador atua a partir de seu próprio computador, em redes TCP/IP.

O objetivo principal desse projeto é a manipulação dos mecanismos de paralelismo e concorrência, desenvolvendo um sistema que envolve múltiplos processos e threads. Além disso, serão utilizados mecanismos de comunicação entre processos - compartilhamento de memória e troca de mensagens - para orquestrar o funcionamento do jogo.

## Instalação:
Para instalar, basta baixar a ForcaMultiplayer.zip na [release](https://github.com/prosperor/ForcaMultiplayer/releases/tag/1.0.0), extrair a pasta ForcaMultiplayer para o local onde deseja instalar o jogo.
Importante: dicio.txt e Servidor.jar devem sempre estar juntos.

## Configuração previa:

O jogo tem dois executaveis `.jar`, um que é a instancia do usuario, e outro que é o servidor onde o jogo ira rodar. O jogador que for hospedar o jogo terá que utilizar ambos os executaveis `.jar`, assim como o arquivo `dicio.txt`, enquanto quem for apenas se conectar ao jogo, tera que ter apenas o `.jar` referente a instancia de usuario.

para jogar com alguém que esteja conectado em outra rede de internet, será nececssario configurar uma porta no seu roteador de internet mas caso todos os jogadores estejam na mesma rede, nenhuma porta terá de ser configurada.

## Como iniciar o jogo:

A inicialização de todos os `.jar` devem ser feitos por linha de comando, como no modelo a seguir:
`java -jar <jar-file-name>.jar`
claro, você deve acessar a pasta onde os `.jar` se encontram usando o comando cd antes).

### Iniciando servidor:

Primeiramente deve-se iniciar o `.jar` referente ao servidor, apos isso, o servidor ira perdir o `Socket`, caso você tenha configurado uma porta no seu roteador, utilize o numero dessa porta, se não basta colocar um numero, é recomendavel colocar um numero de 4 ou 5 digitos, uma vez que você apertar enter, o servidor irá retornar alguns dados, como no exemplo abaixo:

`Servidor iniciado`

`O IP é: 173.88.228.4`

`O Socket é 3322`

`Esperando`

nesse ponto o jogo ja estará pronto, basta se conectar.

### Conectar ao servidor:
Agora, inicie o `.jar` referente a instancia de usuario, a primeira coisa que ele vai perguntar é se o jogo está hosteado na sua rede internet ou em outra, caso esteja na sua rede insira 1, do contrario insira 2, apos isso aperte enter.

No caso 1, tudo que você tera que fazer para se conectar é inserir o valor do `Socket`, que foi colocado no servidor e consequentemente fornecido pelo mesmo.

No caso 2, ou seja, se o servidor estiver hospedado em outra rede internet, o jogo ira requisitar o IP de onde o servidor está hospedado junto do valor do `Socket`, escreva separado por ":" como no exemplo a seguir: 
`173.88.228.4:3322`

Apos isso, você tera que colocar um nickname, para te identificar, e então apos isso você estará conectado ao servidor e consequentemente ao jogo, basta mandar suas tentativas de chute ao jogo.

### Extra:

Caso queira ver os comandos do jogo escreva `cs:help`
