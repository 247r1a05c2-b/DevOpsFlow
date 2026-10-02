pipeline {
  agent any
  stages {
    stage('Checkout'){steps{checkout scm}}
    stage('Build'){steps{sh 'mvn -B clean package -DskipTests=false'}}
    stage('Test'){steps{sh 'mvn -B test'}}
    stage('Docker Build'){steps{sh 'docker build -t devopsflow:' + env.BUILD_NUMBER + ' .'}}
    stage('Deploy'){steps{sh 'docker rm -f devopsflow || true; docker run -d --name devopsflow -p 8080:8080 devopsflow:' + env.BUILD_NUMBER}} 
    stage('Health Check'){steps{sh 'sleep 8; curl --fail http://localhost:8080/actuator/health'}}
  }
  post {
    success { echo 'DevOpsFlow pipeline completed successfully.' }
    failure { echo 'Pipeline stopped because a stage failed.' }
  }
}