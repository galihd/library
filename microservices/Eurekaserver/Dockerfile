FROM java:8
ARG jarfile=target/eurekaserver-docker.jar
ADD ${jarfile} eurekaserver-docker.jar
ENTRYPOINT [ "java","-jar","eurekaserver-docker.jar" ]