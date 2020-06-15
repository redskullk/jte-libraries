import groovy.json.JsonSlurperClassic

def run(String cmd){
    node {
        sh "${cmd}"
     }
}
void deployToTomcat(String server){
  println "deploying to tomcat server"
}

void deployToWas(String server){
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
        println "${data}"
        app_env.servers.each{ server ->
            println "deploying to ${server}"
            def name = data[server].type
            if(name.equals("tomcat")){
              deployToTomcat(server)
            }
            if(name.equals("was")){
              deployToWas(server)
            }
        }
    }
}


