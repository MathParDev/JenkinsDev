library 'utils'

pipeline {
    agent {
        docker {
            image 'oleksiiretiznyk/maven-npm:latest'
            args '-u 0 -v $HOME/.m2:/root/.m2'
        }
    }
    stages {
        stage('Prepare') {
            steps{
                git url: 'https://github.com/MathparLearningTeam/Client', branch: 'integration', credentialsId: 'Github-Web-Key'
                sh 'git config --global user.email "mathpar.mailer@gmail.com"'
                sh 'git config --global user.name "Mathpar Jenkins"'
                injectCredentials script:this, github: true
            }
        }
        stage('Build artifact'){
            steps{
                sh 'mvn -B build-helper:parse-version release:prepare release:perform'
            }
        }
        stage('Push to master'){
            steps{
                sshagent(credentials:["Github-Web-Key"]) {
                    sh "git push origin HEAD:master"
                }
            }
        }
    }
    //Required to cleanup the checkout as it is happening outside of container
    post {
        always {
            sh "chmod -R 777 ."
            cleanWs()
        }
    }
}