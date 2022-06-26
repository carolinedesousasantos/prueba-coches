FROM maven:3.6.3 AS maven
WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn package 

# For Java 11, 
FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=coches-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

# Copy the spring-boot-api-tutorial.jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

# docker build . -t carolinedesousa/prueba-coches-back:latest
# docker push carolinedesousa/prueba-coches-back:latest