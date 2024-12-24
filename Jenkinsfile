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
