FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/dishapp.jar .
EXPOSE 8080
CMD [ "java","-jar","dishapp.jar" ]