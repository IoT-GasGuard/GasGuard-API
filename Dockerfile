FROM openjdk:17-jdk-slim
WORKDIR /app
COPY /target/GasGuardAPI-0.0.1-SNAPSHOT.jar /app/GasGuardAPI-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "GasGuardAPI-0.0.1-SNAPSHOT.jar"]