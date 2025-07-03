FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/GasGuardAPI-0.0.1-SNAPSHOT.jar /app/GasGuardAPI-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "GasGuardAPI-0.0.1-SNAPSHOT.jar"]