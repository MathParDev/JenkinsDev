library 'utils'

List<String> infrastructureChoices = infrastructures.getList()
List<String> environmentChoices = environments.getList()

pipeline {
    parameters{
        choice(
                name: 'infrastructure',
                choices: infrastructureChoices,
                description: 'Application to build an image and push to registry'
        )
        choice(
                name: 'environment',
                choices: environmentChoices,
                description: 'Environment to deploy application to'
        )
    }
    agent {
        docker {
            image 'oleksiiretiznyk/ubuntu-utils:latest'
            args '-u 0'
        }
    }
    stages {
        stage('Deploy'){
            steps{
                git url: "https://github.com/MathparLearningTeam/IaC", branch: 'master', credentialsId: 'github-token'
                sshagent(credentials:["ssh-key-${environments.getShortName(params.environment)}"]){
                    sh "cd ${infrastructures.getDeploymentScriptPath(params.infrastructure)} && COMMAND=\$(bash ./script.sh ${infrastructures.getDeploymentScriptPath(params.infrastructure)} ${infrastructures.getScriptArguments(this, params.infrastructure, params.environment)}) && ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no ${environments.getHostString(params.environment)} \"\$COMMAND\""
                }
            }
        }
    }
}