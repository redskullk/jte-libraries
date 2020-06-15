import groovy.json.JsonSlurper


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

void call(app_env){
    run("pwd")
    stage("Deploy To dev"){
        println "Deploying to ${app_env.servers.size()} servers !!!"
        run("ls")
        try {
            def jsonSlurper = new JsonSlurper()
            //dataval = jsonSlurper.parse(new File(app_env.serverInfo))
            println "${dataval}"
        } catch(Exception e) {
            println("Exception: ${e}")
        }
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


