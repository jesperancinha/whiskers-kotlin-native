FROM ubuntu:latest

WORKDIR /root

COPY ./build/native/nativeCompile/whiskers-graalvm /root

COPY entrypoint.sh /root/

ENTRYPOINT ["/root/entrypoint.sh"]
