FROM java:8
ARG jarfile=target\zuulservice-docker.jar
COPY ${jarfile} zuulservice-docker.jar
ENTRYPOINT [ "java","-jar","zuulservice-docker.jar" ]