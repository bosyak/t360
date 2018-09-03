@Library('CRE')
import java.lang.Object

pipeline {
    agent any
    stages {
        stage('print') {
            steps {
                echo 'Start pipeline'
            }
        }
        stage('clone') {
            steps {
                git url: 'https://github.com/panov-andy/t360.git'
                echo 'START'
                script {
                    bar.foo()
                }

            }
        }
    }
}
