FROM maven:3-openjdk-11 as builder
WORKDIR /app
COPY . .
RUN mvn install -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar ./app.jar
COPY docker-common/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]
