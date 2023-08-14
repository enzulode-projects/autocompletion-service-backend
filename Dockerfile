# Stage 1
FROM gradle:8.2.1-jdk17-alpine as backend-builder
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build --no-daemon -x test

# Stage 2
FROM openjdk:17-jdk-slim
ARG BACKEND_VERSION
ARG ACTIVE_PROFILE
WORKDIR /application
COPY --from=backend-builder /app/build/libs/autocompletion-service-backend-${BACKEND_VERSION}.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
