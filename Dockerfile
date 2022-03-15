FROM openjdk:11-jdk

WORKDIR /app

COPY gradlew gradlew
COPY gradle gradle
COPY settings.gradle settings.gradle
COPY app app
COPY lib lib

RUN /app/gradlew

ENTRYPOINT [ "/app/gradlew", "run" ]