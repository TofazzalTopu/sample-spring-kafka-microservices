# Microservices with Spring Boot and Kafka Demo Project
## Description
There are three microservices: \
`order-service` - it sends `Order` events to the Kafka topic and orchestrates the process of a distributed transaction \
`payment-service` - it performs local transaction on the customer account basing on the `Order` price \
`stock-service` - it performs local transaction on the store basing on number of products in the `Order`

Here's the diagram with our architecture:

![image](https://raw.githubusercontent.com/piomin/sample-spring-kafka-microservices/master/arch.png)

(1) `order-service` send a new `Order` -> `status == NEW` \
(2) `payment-service` and `stock-service` receive `Order` and handle it by performing a local transaction on the data \
(3) `payment-service` and `stock-service` send a reponse `Order` -> `status == ACCEPT` or `status == REJECT` \
(4) `order-service` process incoming stream of orders from `payment-service` and `stock-service`, join them by `Order` id and sends Order with a new status -> `status == CONFIRMATION` or `status == ROLLBACK` or `status == REJECTED` \
(5) `payment-service` and `stock-service` receive `Order` with a final status and "commit" or "rollback" a local transaction make before

## Running on Docker locally
You can easily run all the apps on Docker with Spring Boot support for
(a) Testcontainers
(b) Docker Compose

(a) For Testcontainers
Go to the `order-service` directory and execute:
```shell
$ mvn clean spring-boot:test-run
```

Then go to the `payment-service` directory and execute:
```shell
$ mvn clean spring-boot:test-run
```

Finally go to the `stock-service` directory and execute:
```shell
$ mvn clean spring-boot:test-run
```

You will have three apps running with a single shared Kafka running on Testcontainers.

(b) For Docker Compose
First build the whole project and images with the following command:
```shell
$ mvn clean package -DskipTests -Pbuild-image
```

Then, you can go to the one of available directories: `order-service`, `payment-service` or `stock-service` and execute:
```shell
$ mvn spring-boot:run
```

You start your app and have Kafka and two other containers started with Docker Compose.