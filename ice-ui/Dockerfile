
### STAGE 1: Build ###
FROM node:16.13.2-alpine AS build
WORKDIR /usr/src/app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

### STAGE 2: Run ###
FROM nginx:1.20.2-alpine
COPY --from=build /usr/src/app/dist/ice-cream-ui /usr/share/nginx/html
EXPOSE 80
