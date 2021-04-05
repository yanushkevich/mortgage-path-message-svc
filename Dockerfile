FROM registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift

LABEL runtime="SpringBoot"
LABEL description="Promontory Mortgage Path Message Service"
LABEL mantainer="yanushkevich.anton@gmail.com"

COPY env_files/* /tmp/evn_files/
COPY /volumes/deployments/mortgage-path-message-svcAPI-0.0.1-SNAPSHOT.jar /deployments/

ENV GC_MAX_METASPACE_SIZE=256
ENV JAVA_MAX_MEM_RATIO=100