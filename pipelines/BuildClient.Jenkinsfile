library 'utils'

pipeline {
    agent {
        docker {
            image 'oleksiiretiznyk/maven-npm:latest'
            args '-u 0 -v $HOME/.m2:/root/.m2'
        }
    }
    environment {
            GIT_SSH_COMMAND="ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no"
        }
    stages {
        stage('Prepare') {
            steps{
                git url: 'https://github.com/MathParDev/ClientDev.git', branch: 'integration', credentialsId: 'github_sakh_mathpar'
                sh 'git config --global user.email "mathpar.mailer@gmail.com"'
                sh 'git config --global user.name "Mathpar Jenkins"'
                //injectCredentials script:this, github: true
            }
        }
        stage('Build artifact'){
            steps{
                sh 'mvn -B build-helper:parse-version release:prepare release:perform'
            }
        }
        stage('Push to master'){
            steps{
                sshagent(credentials:["github_sakh_mathpar"]) {
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
