pipeline {
    agent any
    
    stages {
        stage('Build do Projeto') {
            steps {
                script {
                    dockerapp = docker.build("suraydan/sampletodojenkins:${env.BUILD_ID}", '-f ./dockerfile .')
                    //dockerapp = docker.build("suraydan/avaliacao:v${env.BUILD_ID} .", '-f ./dockerfile .')
                    //sh 'mvn clean dependency:copy-dependencies install'
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
                    dockerapp = docker.build("suraydan/avaliacao:v${env.BUILD_ID}", '-f ./dockerfile .')
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
                    //sh "kubectl apply -f ./k8s/deployment.yaml"
                    sh "kubectl set image deployment/web web=suraydan/avaliacao:latest"
                }
            }
        }
    }
}