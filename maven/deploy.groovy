import groovy.json.JsonSlurper

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


void call(app_env){
    stage("Deploy To dev"){
        println "Deploying to ${app_env.servers.size()} servers !!!"
        steps {
            script {
                sh "ls -l"
            }
        }
        println "ls -l"
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
