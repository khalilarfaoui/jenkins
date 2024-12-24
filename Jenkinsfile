pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/khalilarfaoui/jenkins.git', branch: 'main'
            }
        }

        stage('Build with Maven') {
            steps {
                script {
                    // Run the Maven build and SonarCloud analysis
                    sh 'mvn clean install sonar:sonar -Dsonar.login=$SONAR_TOKEN'
                }
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
