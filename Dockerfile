FROM maven:3.9.1-amazoncorretto-19 AS build

WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM amazoncorretto:19

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 80

CMD ["java", "-jar", "app.jar"]
