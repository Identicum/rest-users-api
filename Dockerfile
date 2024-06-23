FROM ghcr.io/identicum/alpine-jdk17-maven:latest as builder
WORKDIR /app
COPY . .
RUN mvn install -DskipTests

FROM ghcr.io/identicum/alpine-jre17:latest
WORKDIR /app
COPY --from=builder /app/target/*.jar ./app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]
