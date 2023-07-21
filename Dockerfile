FROM ghcr.io/identicum/centos-java-maven:latest as builder
WORKDIR /app
COPY . .
RUN mvn install -DskipTests

FROM ghcr.io/identicum/centos-java:latest
WORKDIR /app
COPY --from=builder /app/target/*.jar ./app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]
