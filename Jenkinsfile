pipeline {
    agent any

    environment {
        SONARQUBE = 'SonarQube'  // Nom de la configuration SonarQube dans Jenkins
        MAVEN_HOME = tool name: 'M3', type: 'Maven'  // Use the configured Maven tool
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
                    // Print Maven version and home to check the setup
                    sh 'echo "Maven Home: $MAVEN_HOME"'
                    sh "'${MAVEN_HOME}/bin/mvn' --version"
                    // Build the project using Maven
                    sh "'${MAVEN_HOME}/bin/mvn' clean install"
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Run SonarQube analysis
                    withSonarQubeEnv(SONARQUBE) {
                        sh "'${MAVEN_HOME}/bin/mvn' sonar:sonar"
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    // Wait for and check the quality gate
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
