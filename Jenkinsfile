pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = 'docker-hub-credentials' // Remplace par tes identifiants Docker Hub dans Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://url.de.ton.repository.git'  // URL de ton repo Git
            }
        }

        stage('Build JAR') {
            steps {
                script {
                    // Étape de build du JAR avec Maven
                    sh 'mvn clean package'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Étape de build de l'image Docker
                    sh 'docker build -t ton_docker_hub_username/appweb:latest .'
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    // Connexion à Docker Hub et push de l'image
                    withDockerRegistry([credentialsId: env.DOCKER_HUB_CREDENTIALS, url: '']) {
                        sh 'docker push ton_docker_hub_username/appweb:latest'
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Le pipeline a réussi !'
        }
        failure {
            echo 'Le pipeline a échoué.'
        }
    }
}
