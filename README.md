# Solução Desafio

## Pré-requisitos
- Java 1.8.0_281
- Spring Boot 2.4.4
- Maven 4.0.0

## Execução
 Por ser uma apliação Maven, a solução pode ser executada em qualquer IDE com suporte. 
 Exemplos: Eclipse, IntelliJ, STS e outras.
 
 Api estará disponivel no endereço: **localhost:8080/senha/validacao/{senha}**

 Para execução via CI usando Maven executar os seguintes comandos:

- API:
```bash
$ ./mvnw spring-boot:run
```  

- Testes:

```bash
$ ./mvnw test
```  

 Para execução via CI usando o .jar da aplicação executar os seguintes comandos:

- API:
```bash
$ ./mvnw clean install
$ java -jar target/challenge-0.0.1-SNAPSHOT.jar
```  

# Solução
Escolhi Java para linguagem por ser uma das linguagens que mais tenho conhecimento e que se aproxima ao Kotlin que é a linguagem padrão de suas aplicações.

Para a validação da senha evitei o máximo de operações lógicas e repetidas no código, sabendo que parte das regras para garantir a senha válida poderiam ser solucionadas usando expressões regulares escolhi utilizar uma String composta com varias condições encadeadas para garantir a validação.
```java
/*
*(?=.*[0-9]) ao menos um digito
*(?=.*[a-z]) ao menos uma letra minuscula
*(?=.*[A-Z]) ao menos uma letra maiuscula
*(?=.*[!@#$%^&*()-+]) ao menos um caractere especial
*(?=\\S+$) sem esppaços vazios
*.{9,} minimo de 9 caracteres
*/
"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-+])(?=\\S+$).{9,}$"
``` 
Já na regra de duplicidade e repetição de caracteres não usei regexp, utilizei de algumas bibliotecas do Java 8 para me ajudar, separando a String em um HashMap de caracteres e garantindo que esses só fossem encontrados uma única vez.

```java
Map<String, Long> x = senha.chars().mapToObj(i -> (char) i)
				.collect(Collectors.groupingBy(Object::toString, Collectors.counting()));
Boolean naoRepete = x.values().stream().allMatch(i -> i.equals((long) 1));
```    
Por fim foi necessário que tanto as condições da minha expressão regular como a validação de caracteres não repetidos fossem somadas para retornar ao usuário a resposta.

Nos testes unitários usei a biblioteca JUnit e algumas classes do Spring Boot para testar boa parte dos cenários possiveis, garantindo os testes exemplificados no enunciado do desafio e também a integridade da aplicação.


