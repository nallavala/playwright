pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building the project...'
                dir('C:/Users/rajes/IdeaProjects/demo-test'){
                bat 'mvn clean install'
                }
            }
        }
        stage('Run') {
            steps {
                echo 'Running the project...'
                bat 'mvn test -DsuiteXmlFile=testng.xml'
            }
        }
    }
}
