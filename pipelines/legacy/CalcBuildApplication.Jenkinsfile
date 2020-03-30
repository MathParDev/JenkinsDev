@Library('utils')
import mathpar.jenkins.utils.Constants

def fullImageName = "${Constants.buildGitlabImageNamePrefix()}/${params.imageName}"


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
                defaultValue: 'mathpar:latest',
                description: "Name to give to docker image when created. Should include docker tag"
        )
        booleanParam(name: 'deployToMUEU', defaultValue: true, description: 'Either to deploy new version on mathpar.ukma.edu.ua right after building image')
    }
    environment {
        MAVEN_OPTS="-Duser.home=${JENKINS_HOME}"
    }
    agent {
        docker {
            image 'registry.gitlab.com/mathpar/mathpar/maven_agent'
            registryUrl Constants.buildGitlabRegistryUrl()
            registryCredentialsId Constants.GitlabCredentialsId
            //This property is required to run pipeline in admin mode so sshagent has authorities to create files
            //And also to make sure that such user exists in passwd file (also required for sshagent)
            args '-u 0'
        }
    }
    stages {
        stage('setup') {
            steps{
                script {
                    git url: params.repoUrl, branch: params.branch, credentialsId: Constants.BitbucketCredentialsId
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
                    withDockerRegistry(credentialsId: Constants.GitlabCredentialsId, url: Constants.buildGitlabRegistryUrl()) {
                        sh "docker build -t ${fullImageName} ."
                        sh "docker push ${fullImageName}"
                    }
                }
            }
        }
        stage('deployToMUEU'){
            when{
                equals expected: true, actual: params.deployToMUEU
            }
            steps{
                build job: 'DeployCalc', parameters: [string(name: 'fullImageName', value: fullImageName), string(name: 'dockerCredentialsId', value: Constants.GitlabCredentialsId), string(name: 'sshCredentialsId', value: Constants.MathparSshCredentialsId), string(name: 'sshAddress', value: 'mathpar@mathpar.ukma.edu.ua')]
            }
        }
    }
}