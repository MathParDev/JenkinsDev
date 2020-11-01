def getList(){
    return ['AccountService', 'SchoolService', 'GatewayService', 'TasksService', 'SecretManager'];
}

def getMonitoredApplicationsList(){
    return ['AccountService', 'SchoolService', 'GatewayService', 'TasksService'];
}

def getSourceUrl(String application){
    def sources = [
            'AccountService': 'https://github.com/MathparLearningTeam/Account',
            'SchoolService': 'https://github.com/MathparLearningTeam/School',
            'GatewayService': 'https://github.com/MathparLearningTeam/Gateway',
            'TasksService': 'https://github.com/MathparLearningTeam/Tasks',
            'SecretManager': 'https://github.com/MathparLearningTeam/SecretManager'
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