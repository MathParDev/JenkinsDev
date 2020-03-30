library 'utils'

List<String> applicationChoices = applications.getList()
List<String> environmentChoices = environments.getList()

pipeline {
    parameters{
        choice(
                name: 'application',
                choices: applicationChoices,
                description: 'Application to build an image and push to registry'
        )
        choice(
                name: 'environment',
                choices: environmentChoices,
                description: 'Environment to deploy application to'
        )
        string(
                name: 'tag',
                defaultValue: 'latest',
                description: 'Tag to be used for an image. Use separate value for test builds'
        )
    }
    agent {
        docker {
            // Consider using own image for this, just for a good measure
            image 'oleksiiretiznyk/ubuntu-utils:latest'
            args '-u 0'
        }
    }
    stages {
        stage('Deploy'){
            steps{
                //Compose command to run on server using prepared properties
                git url: "https://github.com/MathparLearningTeam/IaC", branch: 'master', credentialsId: 'github-token'
                sshagent(credentials:["ssh-key-${environments.getShortName(params.environment)}"]) {
                    sh script: "cd ${applications.getDeploymentScriptPath(params.application)} && COMMAND=\$(bash ./script.sh ${applications.getDeploymentArguments(params.application, params.environment, params.tag)}) && ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no ${environments.getHostString(params.environment)} \"\$COMMAND\""
                }
            }
        }
    }
}