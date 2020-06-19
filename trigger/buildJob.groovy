void call(){
  stage('trigger another job'){
    build 'jte'
  }
  
}
