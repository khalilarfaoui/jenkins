pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "springboot-app"
        DOCKER_TAG = "latest"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/khalilarfaoui/jenkins.git', branch: 'main'
            }
        }

        stage('Install Docker') {
            steps {
                script {
                    // Utilisation de PowerShell pour appeler WSL et installer Docker
                    powershell '''
                        wsl sudo apt-get update
                        wsl sudo apt-get install -y docker.io
                    '''
                }
            }
        }

        stage('Build Application') {
            steps {
                script {
                    // Utilisation de PowerShell pour appeler WSL et construire l'application
                    powershell '''
                        wsl mvn clean package -DskipTests
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Utilisation de PowerShell pour appeler WSL et construire l'image Docker
                    powershell '''
                        wsl docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .
                    '''
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    // Utilisation de PowerShell pour appeler WSL et exécuter un conteneur Docker
                    powershell '''
                        wsl docker stop springboot-container || true
                        wsl docker rm springboot-container || true
                        wsl docker run -d --name springboot-container -p 8081:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Docker image built and container running successfully!'
        }
        failure {
            echo 'Deployment failed. Please check the logs for more details.'
        }
    }
}
