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
    stages {
        stage('Checkout') {
            steps{
                git url: applications.getSourceUrl(params.application), branch: 'master', credentialsId: 'github-token'
            }
        }
        stage('Build artifact'){
            environment {
                GIT_SSH_COMMAND="ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no"
            }
            steps{
                sh 'git config --global user.email "mathpar.mailer@gmail.com"'
                sh 'git config --global user.name "Mathpar Jenkins"'
                injectCredentials script:this, github: true, gitlab: true
                sh 'mvn -B build-helper:parse-version release:prepare release:perform'
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