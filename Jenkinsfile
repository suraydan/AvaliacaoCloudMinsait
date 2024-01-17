pipeline {
    agent any
    
    stages {
        stage('Build do Projeto') {
            steps {
                script {
                    //dockerapp = docker.build("suraydan/avaliacao:v${env.BUILD_ID} .", '-f ./dockerfile .')
                    sh 'mvn clean dependency:copy-dependencies install'
                }
            }
        }
        
        stage('Testes Unitários') {
            steps {
                script {
                    sh 'mvn test'
                }
            }
        }
        
        stage('Gerar Imagem Docker') {
            steps {
                script {
                    dockerapp = docker.build("suraydan/avaliacao:v${env.BUILD_ID}", '-f ./Dockerfile .')
                }
            }
        }
        
        stage('Push para DockerHub') {
            steps {
                script {
                    docker.withRegistry("https://registry.hub.docker.com",'dockerhub'){
                        dockerapp.push("v${env.BUILD_ID}")
                        dockerapp.push("latest")
                    }
                }
            }
        }
        
        stage('Deploy no Kubernetes ') {
            steps {
                withKubeConfig([credentialsId: 'kubeconfig']){
                    sh "pwd && ls && cd k8s && kubectl apply -f service.yaml,mysqldeployments.yaml,deployment.yaml"
                    //sh "kubectl set image deployment/web web=suraydan/avaliacao:latest"
                }
            }
        }
    }
}