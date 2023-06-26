FROM alpine:3.18.0

# make sure the package repository is up to date
RUN apk update \
 && apk upgrade \
 && apk add bash \
 && rm -rf /var/cache/*/* \
 && echo "" > /root/.ash_history \
 && apk add openjdk11

# change default shell from ash to bash
RUN sed -i -e "s/bin\/ash/bin\/bash/" /etc/passwd

ENV LC_ALL=en_US.UTF-8


COPY kafka_2.13-3.4.1.tgz /
RUN tar -zxvf kafka_2.13-3.4.1.tgz
ENV PATH="${PATH}:/kafka_2.13-3.4.1/bin"
