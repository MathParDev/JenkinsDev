# curl (REST API)
# Assuming "anonymous read access" has been enabled on your Jenkins instance.
# JENKINS_URL=[root URL of Jenkins master]
# JENKINS_CRUMB is needed if your Jenkins master has CRSF protection enabled as it should
JENKINS_URL=http://mathpar.ukma.edu.ua/jenkins
JENKINS_CRUMB=`curl -u $1:$2 "$JENKINS_URL/crumbIssuer/api/xml?xpath=concat(//crumbRequestField,\":\",//crumb)"`
echo "Crumb = $JENKINS_CRUMB"
curl -u $1:$2 -X POST -H $JENKINS_CRUMB -F "jenkinsfile=<$3" $JENKINS_URL/pipeline-model-converter/validate