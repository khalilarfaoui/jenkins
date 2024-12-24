FROM jenkins/jenkins:lts

# Installer Maven
USER root
RUN apt-get update && apt-get install -y maven

# Retourner à l'utilisateur Jenkins
USER jenkins
