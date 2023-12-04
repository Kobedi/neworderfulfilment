FROM openjdk:17
MAINTAINER zadscience.com
COPY target/new-order-fulfilment-0.0.1.jar new-order-fulfilment-0.0.1.jar
ENTRYPOINT ["java","-jar","/new-order-fulfilment-0.0.1.jar"]