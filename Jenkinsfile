
pipeline {
  agent any
  stages {
    stage('Clear packages') {
      parallel {
        stage('Clear packages') {
          steps {
            sh '''echo " Clearing packages en process"
Name="kimo"
echo $HOSTNAME
echo "this the name "+$Name
ls
whoami
cat index.js'''
          }
        }

        stage('Add packages') {
          steps {
            sh '''echo "ADD PACKAGES TO PROYECT"
cd logs 
ls -ltr 
rm 20211211_Acces_Error.log
ls -ltr
cd ..
node index.js
'''
          }
        }

      }
    }

  }
}
