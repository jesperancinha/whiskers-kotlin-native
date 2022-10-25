FROM ubuntu:latest

WORKDIR /root

COPY ./build/bin/native/releaseExecutable/whiskers-ktor.kexe /root
COPY application-docker.json /root/application.json

COPY entrypoint.sh /root/

RUN cat entrypoint.sh

ENTRYPOINT ["tail", "-f", "/dev/null"]
