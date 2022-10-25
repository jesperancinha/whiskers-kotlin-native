FROM ubuntu:latest

WORKDIR /root

COPY ./build/bin/native/releaseExecutable/whiskers-ktor.kexe /root
COPY application-docker.json /root/application.json

COPY entrypoint.sh /root/

ENTRYPOINT ["/root/entrypoint.sh"]
