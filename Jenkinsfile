@Library('CRE')
import my.Bar

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
                    Bar bar = new Bar()
                    bar.foo()
                }
                log.info('hello from VARS!!')
            }
        }
    }
}
