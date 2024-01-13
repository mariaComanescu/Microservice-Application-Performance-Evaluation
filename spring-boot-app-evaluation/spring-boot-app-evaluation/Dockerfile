FROM openjdk:11-jdk-slim
# update sources
RUN apt-get update

# run app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
