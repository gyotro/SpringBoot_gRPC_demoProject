pipeline {
    environment {
        registry = "gyotro/grpc-springboot"
        registryCredential = 'gyo_docker'
        dockerImage = ''
    }
    agent any
    tools {
        maven 'maven_3_8_1'
    }
    stages {
        stage('Building Maven'){
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'gyo_gitHub', url: 'https://github.com/gyotro/SpringBoot_gRPC_demoProject']]])
                withMaven {
                    dir('server-service') {
                        // on Linux server put sh instead of bat
                        bat 'mvn clean install'
                    }
                }
            }
        }
        stage('Building our image') {
            steps {
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }
        stage('Deploy our image') {
            steps {
                script {
                    docker.withRegistry( '', registryCredential ) {
                // in this way we are pushing the build version as well as the tag 'latest'
                        dockerImage.push()
                        dockerImage.push("latest")
                    }
                }
            }
        }
        stage('Cleaning up') {
            steps {
                 // on Linux server put sh instead of bat
                bat "docker rmi $registry:$BUILD_NUMBER"
            }
        }
    }
}