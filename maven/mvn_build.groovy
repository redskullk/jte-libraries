void call(app_env){
  stage('Build maven'){
    app_env.value = '728638'
    println "maven: build()"
  }
  
}
