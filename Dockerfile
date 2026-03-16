FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk maven -y

COPY . .

RUN mvn clean install 

# Segunda fase: runtime
FROM eclipse-temurin:17-jdk

EXPOSE 8080

COPY --from=build /target/nexus-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
