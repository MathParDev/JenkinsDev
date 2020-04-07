def getList(){
    return ['AccountDatabase', 'SchoolDatabase', 'TasksDatabase'];
}

def getDeploymentScriptPath(String infrastructure){
    def paths = [
            'AccountDatabase': 'infrastructure/database/account',
            'SchoolDatabase': 'infrastructure/database/school',
            'TasksDatabase': 'infrastructure/database/tasks'
    ]
    return paths.get(infrastructure);
}

def getScriptArguments(def script, String infrastructure, String environment){
    switch (infrastructure){
        case 'AccountDatabase':
            String args = ''
            script.withCredentials([script.usernamePassword(credentialsId: "account-db-${environments.getShortName(environment)}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                args+="password=${PASSWORD} "
            }
            args+= "properties=${environments.getPropertiesFilename(environment)}"
            return args;
        case 'SchoolDatabase':
            String args = ''
            script.withCredentials([script.usernamePassword(credentialsId: "school-db-${environments.getShortName(environment)}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                args+="password=${PASSWORD} "
            }
            args+= "properties=${environments.getPropertiesFilename(environment)}"
            return args;
        case 'TasksDatabase':
            String args = ''
            script.withCredentials([script.usernamePassword(credentialsId: "tasks-db-${environments.getShortName(environment)}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                args+="password=${PASSWORD} "
            }
            args+= "properties=${environments.getPropertiesFilename(environment)}"
            return args;
        default:
            throw new RuntimeException("Unsupported infrastructure $infrastructure");
    }
}