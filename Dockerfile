ARG REGISTRY=""
FROM openjdk:17-jdk-slim


ENV PROJECT_NAME=order-service
ENV PROJECT_VERSION=0.0.1-SNAPSHOT

COPY build/libs/${PROJECT_NAME}-${PROJECT_VERSION}.jar orderservice.jar

EXPOSE 8083

ENTRYPOINT ["sh","-c","java -jar /orderservice.jar --server.port=8083"]