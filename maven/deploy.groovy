import groovy.json.JsonSlurperClassic

def run(String cmd){
    node {
        sh "${cmd}"
     }
}
void deployToTomcat(server){
  println "deploying to tomcat server"
}

void deployToWas(server){
  println "deploying to WAS server"
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
            if(name.equals("tomcat")){
              deployToTomcat(data[server])
            }
            if(name.equals("was")){
              deployToWas(server)
            }
        }
    }
}


