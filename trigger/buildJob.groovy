void call(jobname){
  stage('trigger another job'){
    build(jobname)
  }
  
}
