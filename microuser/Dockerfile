FROM java:8
ARG jarfile=target\microuser-docker.jar
COPY ${jarfile} microuser-docker.jar
ENTRYPOINT [ "java","-jar","microuser-docker.jar" ]