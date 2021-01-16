pipeline {
    agent { docker { image 'gradle:6.7-jdk8' } }
    stages {
        stage('build') {
            steps {
                echo '===> Building...'
                sh 'gradlew build'
            }
        }
    }
}