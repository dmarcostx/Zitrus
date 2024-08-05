# Processo Seletivo Zitrus

Esse é um sistema de controle de autorizações de procedimentos médicos para a UNIMED.

## Java

O projeto foi construído com o Java 17

## Criando a rede no docker

O comando para criar a rede no docker é
`docker network create zitrus`

## Postgres

O comando para iniciar o Banco de Dados é
`docker run --network zitrus --name postegres -e POSTGRES_PASSWORD=un1m3d -p 5432:5432 -d postgres`

## Database

O comando SQL para criar o Banco de Dados é
`CREATE DATABASE zitrus;`

## Liquibase

O script para iniciar o Banco de Dados é o `changelog.sql`

`liquibase --driver=org.postgresql.Driver --url="jdbc:postgresql://localhost:5432/zitrus" --changeLogFile=changelog.sql --username=postgres --password=un1m3d update`

# Iniciar a aplicação

`docker build -t zitrus .`

`docker run --network zitrus -p 8080:8080 -d zitrus`