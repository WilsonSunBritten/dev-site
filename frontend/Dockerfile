# Build stage
FROM node:16-alpine as build

WORKDIR /app

COPY package*.json ./
RUN npm ci --only=production

COPY . .
RUN npm run build

# Production stage
FROM nginx:alpine

COPY --from=build /app/build /usr/share/nginx/html

# Replace localhost with localstack in the API URL
RUN sed -i 's/localhost/localstack/g' /usr/share/nginx/html/static/js/*.js

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
