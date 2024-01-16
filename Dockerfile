FROM openjdk:17-alpine as base
RUN apk update && apk add maven git
WORKDIR /opt/app
RUN cd /opt/app && git clone https://github.com/suraydan/AvaliacaoCloudMinsait AvaliacaoCNMinsait_Jonatas 
RUN cd /opt/app/AvaliacaoCNMinsait_Jonatas && mvn clean dependency:copy-dependencies install 
RUN cp /opt/app/AvaliacaoCNMinsait_Jonatas/target/dependency/* /opt/app/.

FROM openjdk:17-alpine
ARG APPLICATION_USER=appuser
RUN adduser --no-create-home -u 1000 -D $APPLICATION_USER
RUN mkdir /app && chown -R $APPLICATION_USER /app
USER 1000
COPY --chown=1000:1000 --from=base /opt/app/AvaliacaoCNMinsait_Jonatas/target/minsait-0.0.1-SNAPSHOT.jar  /app/app.jar
COPY --chown=1000:1000 --from=base /opt/app/*.jar /app/.
WORKDIR /app
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]
