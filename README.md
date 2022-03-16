# Java Annotation Workshop

## Project structure

This is a multi-project build. It contains the projects:

- app: application that uses the annotations.
- lib: library that contains the annotations and the annotation processor.

## Running inside Docker

Requirements:

- Docker (or other compatible container manager)

### Build

> docker build -t annotation .

### Run

> docker run -it --rm annotation

For image tagged `annotation`

## Running in local

Requirements:

- Java 11 (JDK)

### Unix-like (Linux or OSX)

> ./gradlew run

### Windows

> gradlew.bat run
