void call(){
  stage('trigger another job'){
    steps {
      build 'jte'
    }
  }
  
}
