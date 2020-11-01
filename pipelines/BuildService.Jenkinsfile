library 'utils'

def list = applications.getList()

pipeline {
    parameters {
        choice(
                name: 'application',
                choices: list,
                description: 'Application to build an image and push to registry'
        )
    }
    agent {
        docker {
            image 'maven:3-jdk-11'
            args '-u 0 -v $HOME/.m2:/root/.m2'
        }
    }
    environment {
        GIT_SSH_COMMAND="ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no"
    }
    stages {
        stage('Checkout') {
            steps{
                git url: applications.getSourceUrl(params.application), branch: "integration", credentialsId: 'Github-Web-Key'
            }
        }
        stage('Build artifact'){
            steps{
                sh 'git config --global user.email "mathpar.mailer@gmail.com"'
                sh 'git config --global user.name "Mathpar Jenkins"'
                injectCredentials script:this, github: true, gitlab: true
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