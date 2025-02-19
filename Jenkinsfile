pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = 'docker-hub-credentials' // Remplace par tes identifiants Docker Hub dans Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/AnsUle/appweb.git'  // URL de ton repo Git
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
                    docker.build("${louvea/appweb}:${latest}")
                }
            }
        }

        //deploiement du multi containeur avec docker compose
        stage('Deploy with Docker compose'){
                  steps{
                  //initialise le conteneur docker
                    script{
                    // construit les services
                    sh 'docker-compose up -d --build --force-recreate --remove-orphans'
                  }
               }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    // Connexion à Docker Hub et envoie de l'image
                    withDockerRegistry([credentialsId: env.DOCKER_HUB_CREDENTIALS, url: '']) {
                        sh 'louvea/appweb:latest'
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
