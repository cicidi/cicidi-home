cd /Volumes/WD/project/cicidi-home
mvn clean install
#scp -i /Volumes/WD/aws/KeyPairCCD.pem /Volumes/WD/project/cicidi-home/target/resume-1.0.jar ubuntu@ec2-54-183-9-27.us-west-1.compute.amazonaws.com:/tmp
scp  /Volumes/WD/project/cicidi-home/target/resume-1.0.jar dse01@dse01:/cicidi-home
scp  /Volumes/WD/project/cicidi-home/src/main/resources/resume_config/resume-xsl-fo.xsl dse01@dse01:/cicidi-home
#ssh -i /Volumes/WD/aws/KeyPairCCD.pem ubuntu@ec2-54-183-9-27.us-west-1.compute.amazonaws.com 'tmux'
#ssh /Volumes/WD/aws/KeyPairCCD.pem ubuntu@ec2-54-183-9-27.us-west-1.compute.amazonaws.com 'java -Dspring.profiles.active=prod -jar /tmp/resume-1.0.jar &'
ssh dse01@dse01 'kill -9 $(lsof -i:8080)'
ssh dse01@dse01 'java -Dspring.profiles.active=prod -jar /cicidi-home/resume-1.0.jar &'
