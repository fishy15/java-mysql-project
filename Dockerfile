FROM maven:3.6.3-jdk-11

COPY . /usr/java-mysql-project
WORKDIR /usr/java-mysql-project

RUN mvn compile

CMD ["mvn", "exec:java", "-Dexec.cleanupDaemonThreads=false", "-Dexec.mainClass=com.aaryanprakash.app.App"]
