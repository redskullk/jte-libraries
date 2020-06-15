import groovy.json.JsonSlurper
import java.io.File


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
    def jsonSlurper = new JsonSlurper()
    def file = new File(path)
    dataval = new HashMap<>(jsonSlurper.parse(file))
    //dataval = file.text
    
    return dataval
}

void call(app_env){
    stage("Deploy To dev"){
        println "Deploying to ${app_env.servers.size()} servers !!!"
        def x = getServers(app_env.serverInfo)
        println "${x}"
        app_env.servers.each{ server ->
            println "deploying to ${server}"
            def name = "tomcat"
            if(name.equals("tomcat")){
              deployToTomcat(server)
            }
            if(name.equals("was")){
              deployToWas(server)
            }
        }
    }
}


