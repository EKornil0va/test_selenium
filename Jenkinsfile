pipeline {
    agent any

    tools {
        jdk 'JDK 21'
        maven 'test_selenium'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Клонирование репозитория'
                git branch: 'master', url: 'https://github.com/EKornil0va/test_selenium.git'
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Запуск сборки и Selenium-тестов на Windows'
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            echo 'Публикация результатов тестов'
            junit '**/target/surefire-reports/*.xml'

            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
        success {
            echo 'Selenium-тесты прошли успешно'
        }
        failure {
            echo 'Selenium-тесты упали'
        }
    }
}