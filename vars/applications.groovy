def getList(){
    return ['AccountService', 'SchoolService', 'GatewayService', 'TasksService', 'SecretManager'];
}

def getMonitoredApplicationsList(){
    return ['AccountService', 'SchoolService', 'GatewayService', 'TasksService'];
}

def getSourceUrl(String application){
    def sources = [
            'AccountService': 'git@github.com:MathparLearningTeam/Account.git',
            'SchoolService': 'git@github.com:MathparLearningTeam/School.git',
            'GatewayService': 'git@github.com:MathparLearningTeam/Gateway.git',
            'TasksService': 'git@github.com:MathparLearningTeam/Tasks.git',
            'SecretManager': 'git@github.com:MathparLearningTeam/SecretManager.git'
    ]
    return sources.get(application)
}

def getMonitoringUrl(String application){
    def monitoringUrls = [
            'AccountService': '/learning/account/api/status',
            'SchoolService': '/learning/account/api/status',
            'GatewayService': '/learning/account/api/status',
            'TasksService': '/learning/account/api/status'
    ]
    return monitoringUrls.get(application)
}

def getDeploymentScriptPath(String application){
    return [
            'AccountService': 'applications/account',
            'SchoolService': 'applications/school',
            'GatewayService': 'applications/gateway',
            'TasksService': 'applications/tasks',
            'SecretManager': 'applications/secret-manager'
    ].get(application)
}

def getDeploymentArguments(String application, String environment, String tag){
    def args = "properties=${environments.getPropertiesFilename(environment)} tag=${tag}"
    return args
}

def getDatabaseName(String application){
    return [
            'AccountService': 'AccountDatabase',
            'SchoolService': 'SchoolDatabase',
            'GatewayService': null,
            'TasksService': 'TasksDatabase',
            'SecretManager': null
    ].get(application)
}