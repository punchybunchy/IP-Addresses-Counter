# IP Addresses Counter App
The problem of counting unique ip addresses is proposed to solve using Trie prefix tree. 
The child elements of the node will be stored as pairs of "key-value" (HashMap). 
The chosen data structure excludes duplicate data storage. 
So checking the uniqueness and the subsequent calculation of ip addresses is reduced to the following tasks: create a prefix tree and add to it the required ip addresses.

The application also includes the following utilities:
* File reader utility class to read line by line from a file
* IP address generator - generate random ip addresses
* IP Address Converter - convert IP Address to Decimal

### Maintainability tests and linter statuses:
[![Actions Status](https://github.com/punchybunchy/java-project-73/workflows/hexlet-check/badge.svg)](https://github.com/punchybunchy/java-project-73/actions)
[![Java CI](https://github.com/punchybunchy/java-project-73/actions/workflows/app-check.yml/badge.svg)](https://github.com/punchybunchy/java-project-73/actions/workflows/app-check.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/ae0551eef6a05ab2c0d2/maintainability)](https://codeclimate.com/github/punchybunchy/java-project-73/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/ae0551eef6a05ab2c0d2/test_coverage)](https://codeclimate.com/github/punchybunchy/java-project-73/test_coverage)



## Requirements:
Before you can build this project, you must install and configure the following dependencies:
* JDK 17
* Gradle 7.4


## Stack:

* Java 17;
* Gradle 7;
* JUnit


## Build application

```bash
make build
```

## Install application

```bash
make install
```

## Start application

```bash
make start
Please don't forget to install before start
```

## Run application

```bash
make run
```

