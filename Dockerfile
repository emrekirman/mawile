FROM eclipse-temurin:17

COPY /target/mawile-0.0.1-SNAPSHOT.jar mawile-0.0.1-SNAPSHOT.jar

ENTRYPOINT java -jar mawile-0.0.1-SNAPSHOT.jar
