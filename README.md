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
[![Java CI](https://github.com/punchybunchy/IP-Addresses-Counter/actions/workflows/app-check.yml/badge.svg)](https://github.com/punchybunchy/IP-Addresses-Counter/actions/workflows/app-check.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/846aa18423e38a6e90e1/maintainability)](https://codeclimate.com/github/punchybunchy/IP-Addresses-Counter/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/846aa18423e38a6e90e1/test_coverage)](https://codeclimate.com/github/punchybunchy/IP-Addresses-Counter/test_coverage)



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
###### Please don't forget to install before start

```bash
make start
```

## Run application

```bash
make run
```

