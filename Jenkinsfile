pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = 'docker-hub-credentials' // Remplace par tes identifiants Docker Hub dans Jenkins
    }

    stages {
        stage('cleanWS')  {
            steps {
                cleanWS()
            }
        }

        stage('Checkout') {
            steps {
                script {
                    checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'jenkins', url: 'https://github.com/AnsUle/appweb.git']])
                }
            }
        }

        stage('Build JAR') {
            steps {
                script {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.withRegistry('', env.DOCKER_HUB_CREDENTIALS) {
                        docker.build("louvea/appweb:latest")
                    }
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    withDockerRegistry([credentialsId: env.DOCKER_HUB_CREDENTIALS, url: '']) {
                        sh 'docker push louvea/appweb:latest'
                    }
                }
            }
        }

        stage('Deploy with Docker compose') {
            steps {
                script {
                    withDockerRegistry([credentialsId: env.DOCKER_HUB_CREDENTIALS, url: '']) {
                        sh 'docker-compose up -d --build --force-recreate --remove-orphans'
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
