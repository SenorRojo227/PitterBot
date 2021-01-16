pipeline {
    agent {
        docker {
            image 'gradle:6.7-jdk8'
        }
    }

    stages {
        stage('build') {
            steps {
                echo '===> Creating Gradle Wrapper...'
                sh 'gradle wrapper'
                echo '===> Making ./gradlew executable...'
                sh 'chmod +x ./gradlew'
                echo '===> Building...'
                sh './gradlew build'
                echo '===> Running built Jar file...'
                sh 'java -jar build/libs/CoroBot-1.0-SNAPSHOT-all.jar'
            }
        }
    }
}