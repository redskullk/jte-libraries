import groovy.json.JsonSlurperClassic

def run(String cmd){
    node {
        sh "${cmd}"
     }
}
void deployToLinux(server){
    def remote = [:]
    remote.name = server.name
    remote.host = server.host
    remote.user = server.username
    remote.password = server.password
    remote.allowAnyHosts = true
    sshScript remote: remote, script: server.script_path
}

void deployToWindows(server){
    winRMClient {
        hostName(server.host)
        credentialsId(server.credentialsId)
        sendFile(server.script_path, server.destination, server.configurationName)
        invokeCommand("powershell ${server.destination}")
        invokeCommand("powershell rm ${server.destination}")
     }
}

@NonCPS
def getServers(String path) {
    def jsonSlurper = new JsonSlurperClassic()
    def file = new File(path)
    dataval = jsonSlurper.parse(file)
    return dataval
}

void call(app_env){
    stage("Deploy To dev"){
        println "Deploying to ${app_env.servers.size()} servers !!!"
        def data =  getServers "${app_env.serverInfo}"
        app_env.servers.each{ server ->
            println "deploying to ${server}"
            def name = data[server].type
            if(name.equals("linux")){
              deployToLinux(data[server])
            }
            if(name.equals("windows")){
              deployToWindows(server)
            }
        }
    }
}


