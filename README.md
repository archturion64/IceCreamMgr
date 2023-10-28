# Ice-Cream Coding Challenge
## _a full-stack web application with Spring Boot and Angular_


Create a web application with a SPA (Single Page Application) front.end and a Java back-end. Implement a GET and PUT request functionality for the provided IceCreamFlavor object. Validation and 100% test coverage are not required. Usage of H2 in-memory database is allowed. Should run on any machine so bring your own dependencies or use docker.


## Some Decisions:

- Use Angular 16 for the front-end
- Use Spring Boot 3 and Java 17 for the back-end
- Do at least some form of validation on the server side (even tho it was optional)
- Use H2 in-memory database to reduce set-up time during development

## Build and Deploy
Local: 
Dependencies:
- Java 17
- Node 18

```sh
cd <project folder>/ice-ui
npm install
npm run start:clean
```
```sh
cd <project folder>/ice-app
./gradlew bootRun
```

With docker _(under construction)_:
Dependencies:
- [docker](https://docs.docker.com/get-docker/)
- [docker-compose](https://docs.docker.com/compose/install/)
```sh
cd <project folder>
docker-compose -f docker-compose.yaml up
```

## Usage

Open in your favorite browser (Firefox):

UI: http://localhost:4200


