pipeline {
    agent any

    tools {
        maven 'mymaven'   // must match name in Manage Jenkins > Tools > Maven installations
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Compiling main and test sources...'
                // test-compile builds both main + test classes so all 3
                // parallel stages below can run without recompiling each other
                bat 'mvn clean test-compile'
            }
        }

        stage('Quality Gates') {
            parallel {

                stage('Unit Testing') {
                    steps {
                        echo 'Running unit tests...'
                        // plugin-goal only (no full lifecycle) avoids re-triggering compile
                        bat 'mvn surefire:test'
                    }
                    post {
                        always {
                            junit '**/target/surefire-reports/*.xml'
                        }
                    }
                }

                stage('Integration Testing') {
                    steps {
                        echo 'Running integration tests...'
                        bat 'mvn failsafe:integration-test failsafe:verify'
                    }
                    post {
                        always {
                            junit '**/target/failsafe-reports/*.xml'
                        }
                    }
                }

                stage('SCA - Static Code Analysis') {
                    steps {
                        echo 'Running static code analysis (SpotBugs)...'
                        bat 'mvn com.github.spotbugs:spotbugs-maven-plugin:spotbugs'
                    }
                    post {
                        always {
                            archiveArtifacts artifacts: '**/target/spotbugsXml.xml', allowEmptyArchive: true
                        }
                    }
                }

            }
        }

        stage('Package') {
            steps {
                echo 'Packaging the application...'
                bat 'mvn package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
        success {
            echo 'All CI stages (Unit Test, SCA, Integration Test, Package) passed!'
        }
        failure {
            echo 'Pipeline failed! Check stage logs above.'
        }
    }
}
