pipeline {
    agent {
        docker {
            image 'gradle:6.7-jdk8'
        }
    }

    stages {
        stage('build') {
            steps {
                echo '===> Creating Gradle Wrapper'
                sh 'gradle wrapper'
                echo '===> Making ./gradlew executable'
                sh 'chmod +x ./gradlew'
                echo '===> Building...'
                sh './gradlew build'
            }
        }
    }
}