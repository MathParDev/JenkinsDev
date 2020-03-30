@Library('utils') import mathpar.jenkins.utils.*


pipeline {
    parameters{
        string(
                name: 'fullImageName',
                defaultValue: 'registry.gitlab.com/mathpar/mathpar/mathpar:latest',
                description: 'Branch to fetch app source code from'
        )
        string(
                name: 'dockerCredentialsId',
                defaultValue: 'Gitlab-Web-Key',
                description: 'Credentials id to pull image'
        )
        string(
                name: 'sshCredentialsId',
                defaultValue: 'ssh-mathpar-key',
                description: "Name to give to docker image when created. Should include docker tag"
        )
        string(
                name: 'sshAddress',
                defaultValue: 'mathpar@mathpar.ukma.edu.ua',
                description: "Name to give to docker image when created. Should include docker tag"
        )
    }
    agent any
    stages {
        stage('deployApplication'){
            steps{
                sshagent(credentials:[params.sshCredentialsId]) {
                    sh "ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no ${params.sshAddress} 'sh /mathpar/scripts/runCalcModule.sh ${params.fullImageName}'"
                }
            }
        }
    }
}