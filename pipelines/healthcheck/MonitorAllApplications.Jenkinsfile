library 'utils'

pipeline {
    agent any
    stages(){
        stage('Schedule checks for applications'){
            steps{
                script{
                    for(def application: applications.getMonitoredApplicationsList()){
                        build job: '/Mathpar Learning/Healthcheck/MonitorApplicationStatus', parameters: [[$class: "StringParameterValue", name: "application", value: application], [$class: "StringParameterValue", name: "environment", value: 'mathpar.ukma.edu.ua']], wait: false
                    }
                }
            }
        }
    }
}