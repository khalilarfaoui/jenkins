pipeline {
    agent any

    environment {
        SONARQUBE_URL = 'http://localhost:9000'  // Remplacez par l'URL de votre serveur SonarQube
        SONARQUBE_TOKEN = credentials('sonarqube-key')  // Utilisez les credentials de Jenkins pour gérer votre token SonarQube
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/khalilarfaoui/jenkins.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Lancer l'analyse SonarQube
                    sh '''
                    mvn sonar:sonar \
                        -Dsonar.host.url=$SONARQUBE_URL \
                        -Dsonar.login=$SONARQUBE_TOKEN
                    '''
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

  

    
    }

    post {
        success {
            echo 'Déploiement réussi sur le VPS (local) !'
        }
        failure {
            echo 'Le déploiement a échoué.'
        }
    }
}
