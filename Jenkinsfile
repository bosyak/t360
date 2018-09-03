pipeline {
  agent any
  stages {
    stage('print') {
      steps {
        echo 'Start pipeline'
      }
    }
    stage('') {
      steps {
        git(branch: '/master', url: 'https://github.com/panov-andy/t360.git')
        echo 'START'
      }
    }
  }
}
