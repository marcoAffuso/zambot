pipeline{
    agent { label 'agent-pipeline' }


    stages {
        stage('Test') {
            steps {
                echo 'Running tests...'
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    sh 'mvn clean test -Dcucumber.features="src/test/resources/features" -Dcucumber.plugin="pretty,html:target/cucumber-reports/cucumber.html,json:target/cucumber-reports/cucumber.json" -Dheadless=true'
                }
            }
        }
    }

    post {
        always {
            cucumber buildStatus: 'UNSTABLE', fileIncludePattern: 'cucumber.json', jsonReportDirectory: 'target/cucumber-reports'
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
            archiveArtifacts artifacts: 'target/surefire-reports/**, target/cucumber-reports/**', allowEmptyArchive: true
        }
    }
}
