FROM identicum/centos-java-maven as build-env
RUN git clone https://github.com/Identicum/rest-users-api.git .
RUN mvn install -DskipTests

# ############################################################################
# Build runtime image
FROM identicum/centos-java:latest
MAINTAINER Gustavo J Gallardo <ggallard@identicum.com>

RUN yum -y install unzip
COPY --from=build-env /source/target/rest-users-api-0.1.1.jar /app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]
