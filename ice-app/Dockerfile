FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package

FROM maven:3.9.2-eclipse-temurin-17
COPY --from=build /usr/src/app/target/${JAR_FILE} /opt/app/
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
