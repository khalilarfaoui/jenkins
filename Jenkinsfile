pipeline {
    agent any

    tools {
        maven 'Maven 3'  // This matches the name you set in the Global Tool Configuration
    }

    environment {
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'  // L'ID des identifiants Docker Hub
        IMAGE_NAME = 'khalilarfaoui/votre-image'  // Remplacez par le nom de votre image Docker
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/khalilarfaoui/jenkins.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                // Construire l'application Java avec Maven
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    // Utilisation des identifiants Docker Hub pour se connecter à Docker Hub
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                        def app = docker.build("${IMAGE_NAME}:${BUILD_NUMBER}")
                    }
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                        docker.image("${IMAGE_NAME}:${BUILD_NUMBER}").push()
                    }
                }
            }
        }


    }

    post {
        success {
            echo 'L\'image Docker a été construite et poussée avec succès!'
        }
        failure {
            echo 'Le déploiement a échoué.'
        }
    }
}
