# Stage 1: Build the JAR file
FROM eclipse-temurin:17-jdk-alpine as build
# Set the working directory inside the container
WORKDIR /app
# Copy the Gradle wrapper and build files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Copy the source code
COPY src src
COPY config config

# Ensure Gradle wrapper is executable
RUN chmod +x gradlew

# Build the application and unpack the JAR file
RUN ./gradlew clean build
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*-SNAPSHOT.jar)


# Stage 2: Create the final image
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","/app:/app/lib/*","cicd.githubactions.demo.DemoApplication"]
