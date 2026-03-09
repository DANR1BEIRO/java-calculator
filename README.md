# 🧮 Calculadora Java com Swing

![calculator](https://github.com/user-attachments/assets/2a2a4b58-ec05-44ff-9855-c96e19744e9d)


Uma calculadora desktop simples desenvolvida com **Java Swing**.  
Este projeto foi criado com o objetivo de praticar **desenvolvimento de interfaces gráficas (GUI)**, **gerenciamento de estado** e **conceitos de orientação a objetos** utilizando apenas recursos nativos do Java.

A aplicação possui uma interface gráfica limpa e permite realizar operações matemáticas básicas, além de tratar alguns casos comuns de erro, como divisão por zero ou entradas inválidas.

---

# 🚀 Funcionalidades

## Operações Matemáticas Básicas

A calculadora suporta as quatro operações fundamentais:

- Adição (`+`)
- Subtração (`-`)
- Multiplicação (`x`)
- Divisão (`/`)

---

## Interface Gráfica (Swing)

A interface foi construída utilizando as bibliotecas padrão de GUI do Java:

- `JFrame`
- `JPanel`
- `JButton`
- `JTextField`

O layout da aplicação utiliza uma combinação de:

- `BorderLayout`
- `GridLayout`

Essa estrutura divide a interface em três áreas principais:

- **Display (NORTH)** – exibe a expressão atual ou o resultado
- **Teclado (CENTER)** – botões numéricos e operadores
- **Controles inferiores (SOUTH)** – botões de zero, ponto decimal e resultado

---

## Segurança de Entrada

A calculadora possui algumas validações para evitar erros comuns durante o uso.

Exemplos:

- Impede **múltiplos pontos decimais** no mesmo número
- Impede **múltiplos operadores** na mesma expressão
- Trata **divisão por zero**
- Exibe **mensagens de erro amigáveis** no display em vez de quebrar a aplicação

---

## Botões de Controle

Existem dois botões auxiliares para manipular o conteúdo atual do display:

- **AC** – limpa completamente o estado da calculadora
- **del** – remove o último caractere digitado

---

# 🛠 Tecnologias Utilizadas

- **Java**
- **Java Swing**
- **AWT (Abstract Window Toolkit)**

Nenhuma biblioteca externa foi utilizada.

---

# Estrutura do Código e Boas Práticas

## Criação Reutilizável de Botões

Para evitar repetição de código, todos os botões são criados por meio de um único método:

```java
private JButton createButton(String name)
```

Esse método centraliza:

- cor dos botões
- fonte
- estilo padrão

Isso segue o princípio **DRY (Don't Repeat Yourself)**.

---

## Tratamento Centralizado de Eventos

Todos os cliques de botão são processados por um único método:

```java
private void buttonClick(String userDigit)
```

Esse método é responsável por:

- entrada de números
- escolha de operadores
- execução do cálculo
- limpeza do estado
- tratamento de erros

Ter um único ponto de entrada para as ações do usuário mantém a lógica da interface mais organizada.

---

## Gerenciamento de Estado

A calculadora mantém internamente três variáveis principais:

```java
double number1
double number2
String operator
```

Essas variáveis representam:

1. o primeiro número
2. o operador selecionado
3. o segundo número

Quando o usuário pressiona `=`, o programa extrai o segundo número do display e realiza o cálculo.

---

## Switch Expression Moderno (Java)

A lógica do cálculo utiliza a sintaxe moderna de **switch expression** introduzida em versões recentes do Java:

```java
double result = switch (operator) {
    case "+" -> number1 + number2;
    case "-" -> number1 - number2;
    case "x" -> number1 * number2;
    case "/" -> {
        if (number2 == 0) {
            throw new ArithmeticException("divide by zero");
        }
        yield number1 / number2;
    }
};
```

Essa abordagem deixa o código **mais legível e conciso** em comparação com o `switch` tradicional.

---

# 💻 Como Executar o Projeto

## 1. Clonar o repositório

```bash
git clone https://github.com/DANR1BEIRO/java-calculator.git
```

---

## 2. Acessar a pasta do projeto

```bash
cd java-calculator
```

---

## 3. Compilar o projeto

```bash
javac src/com/daniel/Calculator.java
```

---

## 4. Executar a aplicação

```bash
java -cp src com.daniel.Calculator
```

---

# 📚 Objetivos de Aprendizado

Este projeto foi desenvolvido para reforçar conhecimentos práticos em:

- desenvolvimento de interfaces gráficas com **Java Swing**
- **event handling** com `ActionListener`
- **layout managers**
- **gerenciamento de estado em aplicações de interface**
- **tratamento de exceções**
- escrita de código **mais limpo e reutilizável**

---
