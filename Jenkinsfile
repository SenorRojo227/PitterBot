pipeline {
    agent {
        docker {
            image 'gradle:6.7-jdk8'
        }
    }

    stages {
        stage('build') {
            steps {
                echo '===> Making ./gradle-wrapper executable...'
                sh 'chmod +x ./gradle-wrapper.sh'
                echo '===> Gradle Wrapper check...'
                sh './gradle-wrapper.sh'
                echo '===> Making ./gradlew executable...'
                sh 'chmod +x ./gradlew'
                echo '===> Building...'
                sh './gradlew build'
            }
        }
    }
}