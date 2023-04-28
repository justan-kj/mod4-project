FROM openjdk:17

WORKDIR /app

COPY mvnw .

COPY .mvn/ .mvn/

COPY pom.xml .

RUN ./mvnw dependency:go-offline -B

COPY src/ ./src/

COPY .env .

COPY target/m3project-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
