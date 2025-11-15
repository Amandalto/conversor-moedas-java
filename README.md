# 💰 Conversor de Moedas

## Descrição
Este projeto é um conversor de moedas em linha de comando (Console Application) desenvolvido em Java. Ele permite consultar taxas de câmbio em tempo real através da ExchangeRate-API e realizar conversões entre Dólar (USD), Real (BRL) e Euro (EUR).

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java (versão 17 ou superior)
* **Gerenciador de Dependências:** Apache Maven
* **Biblioteca de JSON:** Google Gson
* **Cliente HTTP:** `java.net.http.HttpClient` (padrão do Java 11+)

## ⚙️ Como Executar o Projeto

### Pré-requisitos
* Java Development Kit (JDK) 17 ou superior instalado.
* Apache Maven instalado.
* Uma **chave da API** válida da [ExchangeRate-API](https://www.exchangerate-api.com/).

### Passos para Rodar
1.  **Clone o repositório:**
    `git clone [LINK DO SEU REPOSITÓRIO]`
2.  **Configure a API Key:**
    Abra o arquivo `ConsultaApiCambio.java` e substitua `"SUA_CHAVE_AQUI"` pela sua chave real da ExchangeRate-API.
3.  **Compile e Execute via Maven (Opcional):**
    * Navegue até a raiz do projeto (onde está o `pom.xml`).
    * Compile o projeto: `mvn clean install`
    * Execute a aplicação (usando a classe principal, por exemplo `com.conversor.Main`):
        `mvn exec:java -Dexec.mainClass="com.conversor.Main"`
4.  **Execute via IDE (Recomendado):**
    * Abra o projeto no VSCode (ou outra IDE).
    * Execute o método `main` na classe `Main.java`.

## 💡 Funcionalidades
O programa apresenta um menu interativo com as seguintes opções de conversão:
- USD >> BRL
- BRL >> USD
- USD >> EUR
- EUR >> USD
- EUR >> BRL
- BRL >> EUR
- Sair
