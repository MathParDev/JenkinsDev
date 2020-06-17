library 'utils'

List<String> environmentChoices = environments.getList()

pipeline {
    parameters{
        string(
                name: 'imageName',
                defaultValue: 'calc:latest',
                description: 'Image name and tag to pull'
        )
        choice(
                name: 'environment',
                choices: environmentChoices,
                description: 'Environment to deploy application to'
        )
    }
    agent any
    stages {
        stage('deployApplication'){
            steps{
                sshagent(credentials:["ssh-key-${environments.getShortName(params.environment)}"]) {
                    sh script: "ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no ${environments.getHostString(params.environment)} 'docker pull registry.gitlab.com/mathpar/learning/${params.imageName} && docker stop mathpar_calc | true && docker container rm mathpar_calc | true && docker run -d --name mathpar_calc -v ~/mathpar.mv.db:/root/mathpar.mv.db -e \"SPRING_PROFILES_ACTIVE=prod\" -e \"JAVA_OPTS=-Xms512m -Xmx1536m\" -p9100:8080 registry.gitlab.com/mathpar/learning/${params.imageName}'"
                }
            }
        }
    }
}