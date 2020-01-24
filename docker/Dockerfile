# ############################################################################

# Copy files from source (Only development)
# FROM alpine:latest as build
# WORKDIR /app
# COPY docker-common/wait-for-it.sh docker-common/wait-for-it.sh
# RUN chmod +x docker-common/wait-for-it.sh
# COPY target/*.jar target/app.jar

FROM identicum/centos-java-maven as build
WORKDIR /app
RUN git clone https://github.com/Identicum/rest-users-api.git .
RUN mvn install -DskipTests

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/docker-common/wait-for-it.sh /wait-for-it.sh
RUN apk add --no-cache bash
RUN chmod +x /wait-for-it.sh
COPY --from=build /app/target/*.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
