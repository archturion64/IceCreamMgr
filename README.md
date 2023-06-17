# Ice-Cream Coding Challenge
## _a full-stack web application with Spring Boot and Angular_


Create a web application with a SPA (Single Page Application) front.end and a Java back-end. Implement a GET and PUT request functionality for the provided IceCreamFlavor object. Validation and 100% test coverage are not required. Usage of H2 in-memory database is allowed. Should run on any machine so bring your own dependencies or use docker.


## Some Decisions:

- Use newest version of Angular as of Jan 2022 for the front-end
- Use newest version Bootstrap as of Jan 2022 for UI and CSS 
- Use Spring Boot 3 and Java 17 for the back-end
- Do at least some form of validation on the server side (even tho it was optional)
- Use H2 in-memory database to reduce set-up time during development
- Use docker to prevent deployment headaches

## Build and Deploy

Dependencies:
- [docker](https://docs.docker.com/get-docker/)
- [docker-compose](https://docs.docker.com/compose/install/)

Build and run:
```sh
cd <project folder>
docker-compose -f docker-compose.yml up
```

## Usage

Open in your favorite browser (Firefox):

UI: http://localhost:4200


