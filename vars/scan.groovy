def scanner(Map params) {
    pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    git branch: "${params.branch}", url: "${params.gitrepo}" 
                }
            }

            stage('scan') {
                steps {
                    def scannerHome = tool "${params.scannerHome}" 
                    sh "${scannerHome}/bin/sonar-scanner \
                    -Dsonar.projectKey=${params.projectKey} \
                    -Dsonar.projectName=${params.projectName} \
                    -Dsonar.projectVersion=${params.projectVersion} \
                    -Dsonar.sources=${params.sources} \
                    -Dsonar.host.url=${params.sonarHostUrl} \
                    -Dsonar.login=${params.sonarLogin} \
                    -Dsonar.password=${params.sonarPassword} "
                }
            }
        }
    }
}