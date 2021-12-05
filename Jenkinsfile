pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'building the application...'
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('Verify') {
            steps {
                junit '**/target/surefire-reports/TEST-*.xml'
                archiveArtifacts 'uster-ui/target/*.jar'
                archiveArtifacts 'uster-ws/target/*.jar'
            }
        }
    }
}
