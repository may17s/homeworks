FROM amazoncorretto:21-alpine-jdk
MAINTAINER baeldung.com
ARG JAR_FILE=target/a03-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar","app.jar"]
