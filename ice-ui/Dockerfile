
### STAGE 1: Build ###
FROM node:16.20.0-alpine3.18 AS build
WORKDIR /usr/src/app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

### STAGE 2: Run ###
FROM nginx:1.25.1-alpine
COPY --from=build /usr/src/app/dist/ice-cream-ui /usr/share/nginx/html
EXPOSE 80
