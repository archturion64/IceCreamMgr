FROM openjdk:17-alpine AS build
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN ./gradlew bootJar

FROM openjdk:17-alpine
COPY --from=build /usr/src/app/build/libs/${JAR_FILE} .
EXPOSE 8080
ENTRYPOINT ["java","-jar","CodingChallengeIceCream-0.0.1-SNAPSHOT.jar"]
