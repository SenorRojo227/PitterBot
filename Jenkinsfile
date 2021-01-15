pipeline {
    agent { docket { image 'maven:3.3.3' } }
    stages {
        stage('build') {
            steps {
                echo '===> Building...'
                sh 'mvn --version'
            }
        }
    }
}