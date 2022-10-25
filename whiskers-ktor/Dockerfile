FROM alpine:latest

COPY ./build/bin/native/releaseExecutable/whiskers-ktor.kexe /root

WORKDIR /root

ENTRYPOINT ["./entrypoint.sh"]
