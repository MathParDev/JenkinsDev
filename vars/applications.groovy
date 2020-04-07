def getList(){
    return ['AccountService', 'SchoolService', 'GatewayService', 'TasksService', 'SecretManager'];
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