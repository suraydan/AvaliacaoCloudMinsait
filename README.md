Entrega do Projeto aluno Jonatas Argolo matricula 684403

Montando ambiente 
- Realizar o clone do repositorio
- Criar cluster
  -   k3d cluster create
- Acessar pasta jenkins
  - cd jenkins
- Executar "k3d kubeconfig" write e copiar o arquivo renomeando-o para kubeconfig.yaml e colocando-o na pasta do jenkins
- Realizar build do Jenkins estando dentro da pasta do jenkins
  - docker build -t jenkinsavaliacao:latest .
- Executar o container do jenkins e suas configuracoes:
  - docker container run --name jenkins -d -u root -v /var/run/docker.sock:/var/run/docker.sock -v jenkins_home:/var/jenkins_home -v $HOME/.kube:/var/jenkins_home/.kube -p 8080:8080 -p 50000:50000 --restart=always jenkinsavaliacao
  -  O primeiro passo vai ser inserir a chave de administrador no jenkins, para isso, execute o seguinte comando no seu terminal.
      -  docker container exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
  -  Nesse passo iremos realizar a configuração de duas credenciais importantes para nosso projeto, e para isso vamos acessar no menu lateral do jenkins na sessão Gerenciar Jenkins -> Credentials e por fim em Global Credentials
    - Docker Hub Credentials
      - Nessa primeira Credencial vamos acionar o botão de ** + add credentials** e preencher da seguinte maneira.
          - Kind: Username with Password
          - Scope: Global
          - Username: Seu username do docker hub.
          - Password: Sua senha do docker hub.
          - ID: dockerhub
      - vamos adicionar uma nova credencial no jenkins e preencher da seguinte maneira.
          - Kind: Secret File
          - Scope: Global
          - File: Escolha seu arquivo gerado pelo k3d
          - ID: kubeconfig
    -  Instalando novos plugins em Gerenciar Jenkins -> Plugins e por fim em Extensões disponíveis e instalar os seguintes plugins:
      - Docker
      - Docker pipeline
      - Kubernetes CLI
       
  -  Criar pipeline nome "avaliacao"
      -  Na sessão de Pipeline iremos selecionar:
        - Definition: Pipeline Script from SCM
        - SCM: Git
        - Repositories URL: https://github.com/suraydan/AvaliacaoCloudMinsait
        - Branch Specifier: */main
- Clicar em Construir agora 
     
