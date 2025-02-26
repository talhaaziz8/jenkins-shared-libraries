def call(String credId, String imageName, String tag = "latest") {
    withCredentials([usernamePassword(
        credentialsId: credId,
        passwordVariable: "dockerHubPass",
        usernameVariable: "dockerHubUser"
    )]) {
        echo "🔐 Logging into Docker Hub..."
        sh "echo ${env.dockerHubPass} | docker login -u ${env.dockerHubUser} --password-stdin"

        echo "🏗️ Tagging Docker image..."
        sh "docker tag ${imageName} ${env.dockerHubUser}/${imageName}:${tag}"

        echo "📤 Pushing Docker image..."
        sh "docker push ${env.dockerHubUser}/${imageName}:${tag}"
        
        echo "✅ Successfully pushed ${env.dockerHubUser}/${imageName}:${tag} to Docker Hub!"
    }
}
