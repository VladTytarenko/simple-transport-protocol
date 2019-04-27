# simple-transport-protocol
A simple transport protocol on top of a lower level communication stack.

## Introduction
Protocol implement lower level communication stack.
Protocol provides:
- sending data in chunks of 20 bytes.
- the synchronous callback on data arrival 
- synchronous call to send data

The link layer doesn't give any transmission order guaranties.
Data loss or corruption error in the link layer is unlikely but can occur.

## Requirements
- JRE 8 or JDK 8
- Maven 3
- JUnit v4.12
- log4j v1.2.17

## Application start
1. Clone this repository. 
2. Go to the ```/simple-transport-protocol``` folder 
3. Run Maven command in terminal:
```mvn clean package```
4. Go to the ```/target``` folder 
5. Run command: ```java -jar stp-jar-with-dependencies.jar.jar```

### Outputs
Outputs will be logged in a terminal and display information about:
- establishing of ```Server``` and ```Client```'s
- ```Socket```'s connections
- sending and receiving messages
- connection closing
