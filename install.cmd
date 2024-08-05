mvn package
C:\Program Files\liquibase\liquibase.bat --driver=org.postgresql.Driver --url="jdbc:postgresql://localhost:5432/zitrus" --changeLogFile=changelog.sql --username=postgres --password=un1m3d update
docker build -t zitrus
docker run -p 8080:8080 zitrus