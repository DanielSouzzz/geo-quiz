# Etapa de build com Maven
FROM maven:3.8.5-amazoncorretto-17 AS build

WORKDIR /usr/src/app

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de execução
FROM amazoncorretto:17-alpine

WORKDIR /app

# Copia o JAR final
COPY --from=build /usr/src/app/target/geoquiz-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Permite que o app leia JAVA_OPTS e APP_NAME
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dapp.name=$APP_NAME -jar app.jar"]