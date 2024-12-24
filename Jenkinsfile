pipeline {
    agent any

    environment {
        SONARQUBE = 'SonarQube'  // SonarQube configuration name
        MAVEN_HOME = tool name: 'M3', type: 'Maven'  // Ensure 'M3' is the correct tool name
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
                    // Print Maven version and home to verify setup
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
