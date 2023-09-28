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

# Jar exécutable du projet
