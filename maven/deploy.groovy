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
void getServers(String path) {
    def jsonSlurper = new JsonSlurper()
    def file = new File(path)
    dataval = jsonSlurper.parse(file)
    println "${dataval}"
}

@NonCPS
void call(app_env){
    run("pwd")
    stage("Deploy To dev"){
        println "Deploying to ${app_env.servers.size()} servers !!!"
        run("ls")
        try {
            getServers(app_env.serverInfo)
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
     
        } catch(Exception e) {
            println("Exception: ${e}")
        }
        
    }
}


