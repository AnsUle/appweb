pipeline {
    agent any

    tools {
        maven 'MAVEN'
        jdk 'JDK21'
    }

    environment {
        DOCKER_HUB_CREDENTIALS = 'docker-hub-credentials' // Remplace par tes identifiants Docker Hub dans Jenkins
    }

    stages {
        stage('cleanWS') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout') {
            steps {
                script {
                    checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'jenkins', url: 'https://github.com/AnsUle/appweb.git']])
                }
            }
        }

        stage('build Maven') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage ('Build Docker Image') {
            steps {
                script {
                    docker.build('louvea/appweb:latest', '-f Dockerfile .')
                }
            }
        }

        stage('Push to Docker hub') {
            steps {
                script {
                    docker.withRegistry('', 'docker-hub-credentials') {
                        docker.image('louvea/appweb:latest').push()
                    }
                }
            }
        }
    }
}
