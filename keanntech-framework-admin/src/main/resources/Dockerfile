FROM centos:8
RUN mkdir /home/app
RUN mkdir /home/app/config
RUN mkdir /home/app/lib
ADD ./keanntech-framework-admin-1.0-SNAPSHOT.jar /home/app/administrator-server.jar
COPY ./config/ /home/app/config
COPY ./lib/ /home/app/lib
WORKDIR /usr
RUN mkdir /usr/local/java
ADD ./jdk-11.0.8_linux-x64_bin.tar.gz /usr/local/java/
ENV JAVA_HOME /usr/local/java/jdk-11.0.8
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$CLASSPATH
ENV PATH $JAVA_HOME/bin:$PATH
WORKDIR /
EXPOSE 8081
ENTRYPOINT ["java","-jar","/home/app/administrator-server.jar"]