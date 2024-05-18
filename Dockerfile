FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
ARG JAR_FILE=/build/libs/demo-0.0.1-SNAPSHOT.jar
RUN mkdir -p lib META-INF
COPY ${JAR_FILE} ./app.jar
RUN mkdir -p temp && (cd temp; jar -xf /app/app.jar)
RUN cp -r /app/temp/BOOT-INF/lib /app/ && \
    cp -r /app/temp/META-INF/ /app/ && \
    cp -r /app/temp/BOOT-INF/classes/ /app/
RUN rm -rf /app/temp
ENTRYPOINT ["java","-cp","/app/classes:/app/lib/*","cicd.githubactions.demo.DemoApplication"]