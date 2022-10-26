FROM openjdk:17-alpine

WORKDIR /root

COPY whiskers-graalvm.jar /root/whiskers-graalvm.jar

COPY entrypoint.sh /root/entrypoint.sh

ENTRYPOINT ["/root/entrypoint.sh"]
