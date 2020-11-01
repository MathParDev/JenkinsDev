library 'utils'

pipeline {
    agent any
    stages(){
        stage('Schedule checks for application across all environments'){
            steps{
                script{
                    for(def environment: environments.getList()){
                        build job: '/Mathpar Calculator/Healthcheck/MonitorApplication', parameters: [[$class: "StringParameterValue", name: "environment", value: environment]], wait: false
                    }
                }
            }
        }
    }
}