FROM jenkins/jenkins:lts
USER root
 
# Instalação de dependências necessárias para o Docker
RUN apt-get update -qq \
    && apt-get install -qqy apt-transport-https ca-certificates curl gnupg2 software-properties-common
 
# Adiciona a chave GPG do Docker e configura o repositório
RUN curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add -
RUN add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/debian \
   $(lsb_release -cs) \
   stable"
 
# Instala o Docker
RUN apt-get update -qq \
    && apt-get -y install docker-ce wget

# Instala o maven
ARG MAVEN_VERSION=3.8.8

ENV MAVEN_HOME /opt/maven

RUN mkdir -p /opt \
    && wget -qO- "https://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz" | tar -xz -C /opt \
    && ln -s /opt/apache-maven-$MAVEN_VERSION $MAVEN_HOME \
    && ln -s $MAVEN_HOME/bin/mvn /usr/local/bin/mvn

ENV PATH $MAVEN_HOME/bin:$PATH

 
# Adiciona o usuário "jenkins" ao grupo "docker"
RUN usermod -aG docker jenkins
 
# Instalação do kubectl
RUN curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl \
    && chmod +x ./kubectl \
    && mv ./kubectl /usr/local/bin/kubectl
 
# Copia os arquivos de configuração do Kubernetes
COPY --chown=jenkins:jenkins ./kubeconfig.yaml /var/jenkins_home/.kube/config