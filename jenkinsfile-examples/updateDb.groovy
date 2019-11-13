#!/usr/bin/env groovy

pipeline {
	
  agent {	
    node {	
      label 'jenkins'
    }	
  }
	
  // For Linux/OS environments use sh versus bat
  
  stages {
    stage ('Check Environment') {
      steps {      
		bat ''' 
		  java -version
		  cd liquibase_pro
		  ./liquibase --version
		'''
      } // steps
    } // stage 'Check Environment'
	  
    stage('Update H2 Database') {
      steps {
		bat '''
		  cd h2_project
		  ../liquibase_pro/liquibase.bat update
		'''
		echo 'H2 database changes can be viewed at http://localhost:8082/'
      } // steps
    } // stage 'Update H2 Database'
	  
  } // stages
} // pipeline
