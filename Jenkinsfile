pipeline {
    agent any

    tools {
        // Maven configuré dans "Global Tool Configuration"
        maven 'Maven 3'
    }

    environment {
        // Variables pour l'image Docker
        DOCKER_IMAGE = "springboot-app"
        DOCKER_TAG = "latest"
    }

    stages {
        stage('Checkout') {
            steps {
                // Cloner le dépôt Git
                git url: 'https://github.com/khalilarfaoui/jenkins.git', branch: 'main'
            }
        }

        

        stage('Build Application') {
            steps {
                // Construire l'application Spring Boot avec Maven
                echo 'Building the Spring Boot application...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Construire l'image Docker
                    echo 'Building the Docker image...'
                    sh """
                    docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .
                    """
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    // Arrêter et supprimer tout conteneur existant, puis en démarrer un nouveau
                    echo 'Running the Docker container on port 8081...'
                    sh """
                    docker stop springboot-container || true
                    docker rm springboot-container || true
                    docker run -d --name springboot-container -p 8081:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}
                    """
                }
            }
        }
    }

    post {
        // Actions après l'exécution du pipeline
        success {
            echo 'Docker image built and container running successfully!'
        }
        failure {
            echo 'Deployment failed. Please check the logs for more details.'
        }
    }
}
