FROM openjdk:11
MAINTAINER giovanni <giovanni.dintrono>
EXPOSE 8005
COPY ./server-service/target/grpc-spring-boot-example-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "grpc-spring-boot-example-0.0.1-SNAPSHOT.jar"]