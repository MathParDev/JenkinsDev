# Jenkins sources
This repository contains sources for all Jenkins jobs used by Mathpar Learning project.
Those sources include:
1. Shared library for utility functions and configurations
2. Pipeline files for the jobs

## Shared library 
Shared library sources are contained within folders `resources`, `src` and `vars`.

## Pipelines
Available declarative pipelines:

Name | Description
---- | ------------
BuildClient.Jenkinsfile | this job was created to build and publish web browser client bundle (Client repository). On each trigger the job releases a new minor version of client bundle using build process described in `pom.xml`. Package will be deployed to github packages.
BuildService.Jenkinsfile | similar to build client job but for back end applications. Triggering this job will create a new minor release as described in `pom.xml`. In result a new package will be deployed to github package; also docker image will be created and pushed to private registry.
DeployApplication.Jenkinsfile | this job deploys existing application image to specified environment. In order to do this, the job uses instructions from `IaC` repository. More details available in [IaC readme](https://github.com/MathparLearningTeam/IaC)
DeployInfrastructure.Jenkinsfile | this job is the same as `DeployApplication` but deploys infrastructure instead. Uses same scripts from `IaC` repository
 
Legacy pipelines are jobs which will were added for support of systems which are not the part of Mathpar Learning so should be treated as obsolete and likely to be removed. 