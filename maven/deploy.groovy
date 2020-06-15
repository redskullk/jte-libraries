import groovy.json.JsonSlurper

def getServers(String path){
    def jsonSlurper = new JsonSlurper()
    try{
        data = jsonSlurper.parse(new File("${path}"))
        println "${data}"
    }
    catch(Exception e){
        println "File not found !"
    }
    return data
}

def run(String cmd){
    node {
        sh "${cmd}"
     }
}

void call(app_env){
    run("pwd")
    stage("Deploy To dev"){
        println "Deploying to ${app_env.servers.size()} servers !!!"
        run("ls")
        data = getServers(app_env.serverInfo)
        println "${data}"
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
  println "deploying to WAS server"
}
