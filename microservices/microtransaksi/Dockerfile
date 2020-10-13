FROM java:8
ARG jarfile=/target/mirotrans-docker.jar
COPY ${jarfile} microtrans-docker.jar
ENTRYPOINT [ "java","-jar","microtrans-docker.jar" ]