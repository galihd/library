FROM java:8
ARG jarfile=microdompet-docker.jar
COPY ${jarfile} microdompet-docker.jar
ENTRYPOINT [ "java","-jar","microdompet-docker.jar" ]