pipeline {
    agent any
    tools {
        maven 'M2_HOME'
        jdk 'JAVA_HOME'
    }

    stages {
        stage('GIT') {
            steps {
                git branch: 'AymenChebli-5Arctic4-G3',
                url: 'https://github.com/MDJ-GitHub/5ArcTIC4-G3-Kaddem.git'
            }
        }

        stage('CLEAN AND COMPILE STAGE') {
            steps {
                sh 'mvn clean compile';
            }
        }

        stage('JUNIT/MOCKITO TEST') {
            steps {
                sh 'mvn test'
            }
        }

        stage('JACOCO REPORT') {
            steps {
                sh 'mvn jacoco:report'
            }
        }

        stage('MVN SONARQUBE') {
            steps {
                withCredentials([string(credentialsId: 'sonarqube-token', variable: 'SONAR_TOKEN')]) {
                    sh 'mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml'
                }
            }
        }

        stage('MVN BUILD') {
            steps {
                // Build locally
                sh 'mvn install'
            }
        }

        stage('MVN DEPLOY') {
            steps {
                // Deploy to Nexus
                sh 'mvn deploy'
            }
        }

        stage('DOCKER BUILD BACKEND') {
            steps {
                // Build a Docker Image
                sh 'docker build -t  chebliaymen/aymenchebli-5arctic4-g3-kaddem:latest .'
            }
        }

        stage('DOCKER BUILD FRONTEND') {
            steps {
                dir('front') {
                    sh 'npm install'
                    sh 'ng build --configuration production'
                    sh 'docker build -t  chebliaymen/aymenchebli-5arctic4-g3-kaddem-front:latest .'
                    }
                }
            }

        stage('DOCKER DEPLOY') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials-id', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    // Log in to Docker Hub
                    sh "echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin"

                    // Push the Docker Backend image to Docker Hub
                    sh 'docker push chebliaymen/aymenchebli-5arctic4-g3-kaddem:latest'

                    // Push the Docker Frontend image to Docker Hub
                    sh 'docker push chebliaymen/aymenchebli-5arctic4-g3-kaddem-front:latest'
                }
            }
        }

        stage('DOCKER COMPOSE') {
            steps {
                script {
                    sh 'docker compose up -d'
                    }
                }
        }
    }
}
