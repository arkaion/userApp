# Introduction 

Le but de cette application Springboot est de démontrer mes capacités technique en répondant au besoin suivant : 

* Une API doit exposer deux services :
  * Un premier qui permet d'enregistrer un utilisateur
  * Un second qui affiche les détails d'un utilisateur enregistré.
* Un utilisateur est défini par:
  * Un username
  * une date de naissance
  * un pays de résidence
* Un utilisateur peux également contenir (ou non) :
  * Un numéro de téléphone
  * Un genre
 
Deux contraintes supplémentaires : 
* Seulement les résidents Français majeurs sont autorisés à créer un compte.
* Les données d'entrées doivent être validées, et renvoyer des messages d'erreur / statuts HTTP correspondant.

Quelques bonus sont disponible pour les réalisations suivantes : 
* Intégrer une base de donnée directement dans l'application pour faciliter l'installation et l'execution par autrui.
* Utiliser l'AOP pour logger les entrées et sorties de chaque appel, ainsi que le temps d'exécution.
* Divers enrichissements du modèle laissé au gré du développeur.

# Prérequis

Le projet a été développé avec le JDK 21.
Si vous souhaitez le lancer sur votre machine, il faut que celle-ci dispose d'une version de java similaire ou compatible.
Vous pouvez trouver un jdk21 ici : https://jdk.java.net/21/

# Lancement du projet

Deux méthodes sont disponible :
* La première est de télécharger le [jar disponible dans la release 1.0 de l'application.](https://github.com/arkaion/userApp/releases/tag/1.0 "Release 1.0")
  * Récupérez le fichier sur votre machine.
  * Lancez powershell, puis naviguez jusqu'au repertoire contenant le jar.
  * Lancez le jar avec la commande `java -jar .\demo-0.0.1-SNAPSHOT.jar`
* La seconde est de cloner le répertoire git, de le lancer avec votre IDE favori.
  * Clonez le répertoire sur votre machine avec `git clone https://github.com/arkaion/userApp.git`
  * Ouvrez le projet avec votre IDE
  * Configurez le JDK 21 en version java projet
  * Après build Maven et applicatif, lancez le projet avec la classe DemoApplication.java, dans \src\main\java\com\example\demo\DemoApplication.java
 
# Appel API

Une fois l'application lancée, vous pouvez utiliser ses services.
Pour cela, voici [la collection POSTMAN](https://github.com/arkaion/userApp/blob/main/src/main/resources/postman_collection.json "Collection postman") des requêtes utilisées pendant les tests.

Si vous souhaitez reproduire ses appels sans utiliser la collection, voici leurs détails :
* Le premier correspond au service d'enregistrement d'utilisateur :
  * Type de requête : `POST`
  * URL : `http://localhost:8080/user/`
  * Header : `Content-Type` = `application/json`
  * Body :
```
{
  "username": "john_doe",
  "birthdate": "1990-01-15",
  "country": "France",
  "gender": "FEMALE",
  "phoneNumber": "+1234567890"
}

```

* Le second correspond au service de récupération des détails d'un utilisateur enregistré :
  * Type de requête : `GET`
  * URL : `http://localhost:8080/user/`
  * Params : `username` = Un nom d'utilisateur précédemment ajouté par la requête POST.

# Le mot du développeur

L'application est actuellement dans un état satisfaisant, mais certains points restent selon moi à améliorer. Si vous voyez toujours ce message lors de l'étude de l'application, c'est alors que je n'ai pas eu le temps de le faire (ou que j'ai oublié de retirer ce message ;) ) :
 * Le log par AOP des statistiques lors de l'appel d'une API est pour le moment uniquement loggé dans la console de l'application. Il serait bénéfique de les persister dans un fichier à part pour permettre leur analyse ultérieure sans risquer de les perdre à l'arret de l'application.
 * Les messages d'erreurs renvoyés par l'application sont pour l'instant notés en dur dans le code. Il serait bien de les externaliser dans un fichier de configuration, afin de permettre de les modifier à la volée et de préparer le terrain pour un éventuel support multi language.
 
