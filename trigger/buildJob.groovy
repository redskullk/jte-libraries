void call(){
  stage('trigger another job'){
    build job: 'jte'
  }
  
}
