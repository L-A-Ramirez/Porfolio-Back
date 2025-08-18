# Etapa 1: Build con Maven
<<<<<<< HEAD
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
=======
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY . .
>>>>>>> 553441f1dda280804aebd533e916759f49540c2b
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final para producción
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
<<<<<<< HEAD
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
=======
COPY --from=builder /app/target/porfolio-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
>>>>>>> 553441f1dda280804aebd533e916759f49540c2b
