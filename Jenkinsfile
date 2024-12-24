pipeline {
    agent any

    environment {
        SONARQUBE = 'SonarQube'  // Nom de la configuration SonarQube dans Jenkins
        MAVEN_HOME = tool name: 'M3', type: 'Maven'

    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/khalilarfaoui/jenkins.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                script {
                    // Utiliser Maven pour construire le projet
                    sh "'${MAVEN_HOME}/bin/mvn' clean install"
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Exécuter l'analyse SonarQube
                    withSonarQubeEnv(SONARQUBE) {
                        sh "'${MAVEN_HOME}/bin/mvn' sonar:sonar"
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    // Attendre et vérifier la qualité du code
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
