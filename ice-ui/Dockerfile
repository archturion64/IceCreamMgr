
### STAGE 1: Build ###
FROM timbru31/java-node:17-jre-18 AS build
WORKDIR /usr/src/app
COPY ./ice-ui/package.json ./
RUN npm install
COPY ./ice-ui/ .
COPY ./openapi.yaml ../
RUN npm run build:clean

### STAGE 2: Run ###
FROM nginx:alpine
COPY --from=build /usr/src/app/dist/ice-ui /usr/share/nginx/html
RUN ls -la
EXPOSE 80
