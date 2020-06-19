void call(){
  stage('Approval'){
    println "Getting  approval"
    input 'do you want to deploy ?'
  }
  
}
