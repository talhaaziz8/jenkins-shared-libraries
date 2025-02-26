def call(String credId, String imageName) {
    withCredentials([usernamePassword(
        credentialsId: credId,
        passwordVariable: "dockerHubPass",
        usernameVariable: "dockerHubUser"
    )]) {
        echo "🔐 Logging into Docker Hub..."
        sh "echo ${env.dockerHubPass} | docker login -u ${env.dockerHubUser} --password-stdin"

        echo "🏗️ Tagging Docker image..."
        sh "docker tag ${imageName} ${env.dockerHubUser}/${imageName}:latest"

        echo "📤 Pushing Docker image..."
        sh "docker push ${env.dockerHubUser}/${imageName}:latest"
        
        echo "✅ Successfully pushed ${env.dockerHubUser}/${imageName}:latest to Docker Hub!"
    }
}


