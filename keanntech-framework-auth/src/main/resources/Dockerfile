FROM centos:8
RUN mkdir /home/app
ADD ./keanntech-framework-auth-1.0-SNAPSHOT.jar /home/app/authorization-server.jar
WORKDIR /usr
RUN mkdir /usr/local/java
ADD ./jdk-11.0.8_linux-x64_bin.tar.gz /usr/local/java/
ENV JAVA_HOME /usr/local/java/jdk-11.0.8
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$CLASSPATH
ENV PATH $JAVA_HOME/bin:$PATH
WORKDIR /
EXPOSE 8081
ENTRYPOINT ["java","-jar","/home/app/authorization-server.jar"]