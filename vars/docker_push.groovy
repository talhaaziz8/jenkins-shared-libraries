def call(String Project, String ImageTag, String dockerhubuser) {
    withCredentials([usernamePassword(credentialsId: 'dockerhubcredss', passwordVariable: 'dockerHubPass', usernameVariable: 'dockerHubUser')]) {
        sh """
            echo \$dockerHubPass | docker login -u \$dockerHubUser --password-stdin
            docker tag ${Project}:${ImageTag} ${dockerHubUser}/${Project}:${ImageTag}
            docker push ${dockerHubUser}/${Project}:${ImageTag}
        """
    }
}
