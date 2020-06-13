pipeline {
    parameters{
        string(
                name: 'branch',
                defaultValue: 'master',
                description: 'Branch to fetch app source code from'
        )
        string(
                name: 'repoUrl',
                defaultValue: 'git@bitbucket.org:mathpar/mathpar2020.git',
                description: 'Repository to fetch app source code from'
        )
        string(
                name: 'imageName',
                defaultValue: 'calc:latest',
                description: "Name to give to docker image when created. Should include docker tag"
        )
    }
    agent {
        docker {
            image 'maven:3-jdk-11'
            args '-u 0 -v $HOME/.m2:/root/.m2'
        }
    }
    stages {
        stage('setup') {
            steps{
                script {
                    git url: params.repoUrl, branch: params.branch, credentialsId: 'bitbucket-credentials'
                }
            }
        }
        stage('compile'){
            stages{
                stage('test'){
                    steps{
                        sh 'mvn test'
                    }
                }
                stage('package'){
                    steps{
                        sh 'mvn -B clean package -Dmaven.test.skip=true'
                    }
                }
            }
        }
        stage('docker'){
            steps{
                script {
                    withDockerRegistry(credentialsId: 'bitbucket-credentials', url: 'https://registry.gitlab.com') {
                        sh "docker build -t registry.gitlab.com/mathpar/mathpar/${params.imageName} ."
                        sh "docker push registry.gitlab.com/mathpar/mathpar/${params.imageName}"
                    }
                }
            }
        }
    }
}