# Expense Tracker CLI 💰

Este projeto é uma implementação prática do desafio [Expense Tracker](https://roadmap.sh/projects/expense-tracker) proposto pelo site **roadmap.sh**.

Trata-se de uma aplicação de linha de comandos (CLI) simples e interativa, construída em Java, que ajuda a gerir e monitorizar as suas despesas financeiras. Os dados são guardados de forma persistente num ficheiro CSV.

## ✨ Funcionalidades

A aplicação suporta os seguintes comandos principais:

* **Adicionar Despesa (`add`)**: Regista uma nova despesa com uma descrição e o respetivo valor.

* **Listar Despesas (`list`)**: Exibe um histórico completo de todas as despesas registadas.

* **Eliminar Despesa (`delete`)**: Remove uma despesa específica com base no seu ID.

* **Resumo de Despesas (`summary`)**: Mostra o somatório de todas as despesas. Pode ser filtrado por um mês específico.

## 🛠️ Tecnologias Utilizadas

* **Java 20**: Linguagem principal da aplicação.

* **Maven**: Gestão de dependências e build do projeto.

* **Picocli**: Biblioteca utilizada para o parsing elegante dos comandos, opções e geração de menus de ajuda.

* **OpenCSV**: Utilizado para ler, escrever e mapear os dados das despesas de e para o ficheiro CSV (`data/expenses.csv`).

* **Command Pattern**: A arquitetura do código baseia-se no padrão de desenho Command, separando a lógica de cada ação (Add, List, Delete, Summary) em classes distintas.

## 🚀 Como Executar

Pode executar a aplicação diretamente através do Maven ou compilar e correr o ficheiro gerado.

### 1. Pré-requisitos

Certifique-se de que tem o **Java JDK 20** (ou superior) e o **Maven** instalados na sua máquina.

### 2. Clonar e Compilar

```bash
git clone [https://github.com/AmaralPH/expense-tracker-cli.git](https://github.com/AmaralPH/expense-tracker-cli.git)
cd expense-tracker-cli
mvn clean install


3. Executar o Projeto

A aplicação possui um Modo Interativo (REPL). Se a iniciar sem argumentos, entrará num terminal próprio da aplicação:

mvn exec:java -Dexec.mainClass="org.example.Main"


No modo interativo, verá o prompt expense-tracker > .

### 📖 Exemplos de Uso

Dentro do modo interativo (ou passando diretamente via terminal), pode utilizar os seguintes comandos:

1. Adicionar uma despesa:

add --description "Jantar no restaurante" --amount 45.50
 ou a versão curta:
add -d "Jantar no restaurante" -a 45.50


2. Listar todas as despesas:

list


Exemplo de saída:

ID   Date           Description    Amount
1    2026-02-24     Jantar no restaurante          $45.5


3. Ver o resumo total das despesas:

summary


4. Ver o resumo de despesas de um mês específico (ex: Fevereiro):

summary --month 2


5. Eliminar uma despesa pelo ID:

delete --id 1


6. Sair da aplicação:

exit


### 📄 Licença

Este projeto é de código aberto e está disponível para fins educativos e de portefólio.