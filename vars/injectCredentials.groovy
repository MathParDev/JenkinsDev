void call(Map properties){
    def script = properties.get("script"), github = properties.get("github"), gitlab = properties.get("gitlab")
    String settingsString = "<settings><servers>"
    if(github){
        script.withCredentials([script.usernamePassword(credentialsId: 'github_sakh_UP', usernameVariable: 'USERNAME', passwordVariable: 'TOKEN')]) {
            settingsString += "<server><id>github-mathpar</id><username>${USERNAME}</username><password>${TOKEN}</password></server>"
        }
    }
    if(gitlab){
        script.withCredentials([script.usernamePassword(credentialsId: 'gitlab-token', usernameVariable: 'USERNAME', passwordVariable: 'TOKEN')]) {
            settingsString += "<server><id>gitlab-mathpar</id><username>${USERNAME}</username><password>${TOKEN}</password></server>"
        }
    }
    settingsString += "</servers></settings>"
    script.sh("set +x && echo '$settingsString' > ~/.m2/settings.xml && set -x")
}