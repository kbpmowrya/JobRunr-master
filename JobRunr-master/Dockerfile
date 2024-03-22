FROM maven:3.8.4-openjdk-17-slim AS build
#FROM maven:3.8.3-openjdk-16 AS build
RUN mkdir -p /root/.m2 \
    && mkdir /root/.m2/repository
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
#RUN mvn -f pom.xml clean package
RUN --mount=type=cache,target=/root/.m2 mvn -f pom.xml clean package
#-Dmaven.test.skip
FROM openjdk:17-jdk-slim
#FROM openjdk:22-ea-16-slim
#FROM openjdk:22-ea-21-slim-bullseye
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

#run the app
#ENV JAVA_OPTS ""
#CMD [ "bash", "-c", "java ${JAVA_OPTS} -jar *.jar -v"]