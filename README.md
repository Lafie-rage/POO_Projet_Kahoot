# POO_Projet_Kahoot
Highschool group project done in Java

## Comment build le projet ?

Pour le build, il suffira d'avoir java 15.
Il est possible qu'il faille reconfigurer le workspace sous intellij et également qu'il faille réajouter les dépendences aux librairies : [mysql-connector-java-5.1.6.jar](https://repo1.maven.org/maven2/mysql/mysql-connector-java/5.1.6/mysql-connector-java-5.1.6.jar) et [json-simple-1.1.1.jar](https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/json-simple/json-simple-1.1.1.jar) 

## Comment lancer le projet ?

Notre projet dispose de plusieurs modes d'exécution.
Un premier qui est le lancement de la classe Main. Elle lance un serveur puis autant de joueur qu'il y a de joueur max dans une room (cette variable est définie par utils.Commons.MAX_PLAYER_IN_ROOM).
Un deuxième qui est le lancement de la console d'administration en lançant la classe admin.MainPage. Cette console permet la gestion des utilisateurs et des quizz.
Le dernier est le lancement du serveur seul puis des clients. Celui-ci permet, par exemple, de lancer un serveur sur une machine et les clients sur d'autres. Il suffira que les variables utils.Commons.PORT et  utils.Commons.HOST soient bien définis pour chaque client afin d'atteindre le bon serveur.

## Notre équipe 

Ce projet a été réalisé par :

[Corentin DESTREZ](https://github.com/Lafie-rage)
[Valentin Guiberteau](https://github.com/ValentinIG2I)
[Samuel Saint-Omer](https://github.com/elfamososam)
