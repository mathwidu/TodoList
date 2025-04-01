FROM ubuntu:latest AS build

RUN apt-get update
RUN apt install openJdk-17-jdk -y

copy . . 

RUN apt install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080
COPY --from=build target/todoLista-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
