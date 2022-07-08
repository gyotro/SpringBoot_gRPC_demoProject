FROM openjdk:11
MAINTAINER giovanni <giovanni.dintrono>
EXPOSE 8005
COPY ./server-service/target/grpc-server.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "grpc-server.jar"]