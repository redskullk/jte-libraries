import groovy.json.JsonSlurper
import hudson.model.*

void getServers(){
    def jsonSlurper = new JsonSlurper()
    try{
        data = jsonSlurper.parse(new File("servers.json"))
        println "${data}"
    }
    catch(Exception e){
        println "File not found !"
    }
}

def run(String cmd){
     println "${cmd}"
}

void call(app_env){
    run("pwd")
    stage("Deploy To dev"){
        println "Deploying to ${app_env.servers.size()} servers !!!"
        println "ls -l"
        run("ls")
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

void deployToTomcat(String server){
  println "deploying to tomcat server"
}

void deployToWas(String server){
  println "deploying to tomcat server"
}
