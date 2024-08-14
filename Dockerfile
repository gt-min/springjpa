FROM openjdk:17-jdk
ARG JAR_FILE=target/springjpa.jar
COPY ${JAR_FILE} springjpa.jar
ENTRYPOINT ["java"]
CMD ["-jar","springjpa.jar"]