FROM openjdk:11.0.5-jre

COPY target/starbux-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT exec java $JAVA_OPTS  -jar /app.jar

EXPOSE 8080:8080