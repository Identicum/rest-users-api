FROM identicum/centos-java-maven as build-env
RUN git clone https://github.com/Identicum/rest-users-api.git .
RUN mvn install -DskipTests

# ############################################################################
# Build runtime image
FROM identicum/centos-tomcat:latest
MAINTAINER Gustavo J Gallardo <ggallard@identicum.com>

RUN yum -y install unzip
COPY --from=build-env /source/target/rest-users-api.war /tmp/
RUN unzip -qq /tmp/rest-users-api.war -d /usr/local/tomcat/webapps/rest-users-api && \
  rm -f /tmp/rest-users-api.war && \
  echo "<% response.sendRedirect(\"/rest-users-api/\"); %>" > webapps/ROOT/index.jsp
