# REST API - Golden Raspberry Awards

Projeto contendo uma REST API montado com:
* [JAVA 8](https://www.java.com/pt-BR/download/ie_manual.jsp?locale=pt_BR)
* [Spring Boot 2.5.3](https://spring.io/projects/spring-boot)
* [H2database](http://www.h2database.com/html/download.html)
* [Lombok](https://projectlombok.org/download)
* [Opencsv](http://opencsv.sourceforge.net/)
* [Junit 5](https://junit.org/junit5/)
* [Swagger 3.0](https://swagger.io/tools/swagger-ui/download/)

REST API desenvolvida em Java utilizando o framework [Spring](https://spring.io/).

## Instalação

Clone este repositório

```bash
git clone https://github.com/fabioherberth/golden_raspberry_awards.git
cd worstmovies
```

Utilize o maven para buildar projeto com as dependẽncias configuradas no pom.xml.

```bash
mvn clean install -DskpiTests
```
Para executar o projeto com a API 

```bash
mvn spring-boot:run
```

Importe o projeto em alguma IDE compatível para executar a API, a classe principal é **WorstMoviesApplication**

## Utilização

A partir deste momento a API pode ser consumida através do endereço [http://localhost:8080/api/v1/](http://localhost:8080/api/v1/). Foram tratados os seguintes casos:

API REST dos indicados e vencedores com Swagger-ui. Disponível em [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)

Retorna a lista completa dos indicados. **GET** [http://localhost:8080/api/v1/nominees](http://localhost:8080/api/v1/nominees)

Deleta o indicado pelo ID informado. **DELETE** [http://localhost:8080/api/v1/nominees/{id}](http://localhost:8080/api/v1/nominees/{id})

Retorna o indicado pelo ID informado. **GET** [http://localhost:8080/api/v1/nominees/{id}](http://localhost:8080/api/v1/nominees/{id})

Retorna uma lista de vencedores com intervalos maiores e menores entre prêmios. **GET** [http://localhost:8080/api/v1/winners](http://localhost:8080/api/v1/winners)


## Documentação e Código fonte

O código elaborado encontra-se integralmente dentro da pasta **src/main/java/fabioherberth.worstmovies**

O arquivo **CSV** que será importado para o banco de dados está dentro da pasta **src/main/resources/files**.

O código elaborado para os testes de integração estão no diretório **tests**.

## License

Código elaborado por Fabio Herbert Freire Gomes.

[MIT](https://choosealicense.com/licenses/mit/)