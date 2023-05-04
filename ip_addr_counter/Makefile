setup:
	./gradlew wrapper --gradle-version 7.4

clean:
	./gradlew clean

build:
	./gradlew clean build
	
run:
	./gradlew run

install:
	./gradlew installDist

start:
	./build/install/ip_addr_counter/bin/ip_addr_counter

lint:
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

check-updates:
	./gradlew dependencyUpdates

.PHONY: build
