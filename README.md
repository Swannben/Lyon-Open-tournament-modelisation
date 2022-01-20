# Projet de CPOA - Team Canada

## Présentation du projet

Ceci est le travail de CPOA de la team Canada. Le projet comprend deux packages : **planning_des_matchs** et **hebergement**. Le premier est une application Java alors que le deuxième est une application Web.


## Présentation de la team

La team Canada est composée de :
- Clément Darne
- Swann Benzianne
- Khalissa Rhoulam
- Arthaud Morin

## Participer au développement

### Cloner le dépôt

Pour cloner le dépôt, il ne faut pas oublier le mot-clef `--recursive` afin d'aussi cloner le dépôt de l'application Heroku. 
Pour que l'application Heroku soit bien clonée, il faut en plus s'y connecter grâce au [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli#download-and-install) (le processus sera détaillé par la suite).

```git
git clone --recursive https://forge.univ-lyon1.fr/p2001337/cpoa.git
```

### Le package planning_des_matchs

Pour développer l'application qui gère le planning des matchs, il faut utiliser un environnement Java. Il est conseillé d'utiliser l'IDE Netbeans. 
Tous les fichiers nécessaires se trouvent dans le dépôt dans le répertoire [src/planning/](src/planning/).

### Le package hebergement

Dans le dépôt il y a un submodule `heroku` qui correspond à l'application Web mise à jour en temps réel. Vous pouvez visualiser plus en détail l'application depuis votre [compte Heroku](https://herokuapp.com).

L'application Web est accessible depuis l'url [https://cpoa-canada.herokuapp.com/](https://cpoa-canada.herokuapp.com/).

#### Préparation de l'environnement

Pour apporté des changements à l'application, il faut suivre plusieurs étapes.

1. Télécharger le [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli#download-and-install).

2. Indiquer ses identifiants Heroku pour pouvoir se connecter par la suite.

```bash
$ heroku login
```

3. A chaque modification il suffit de *commit* les changements du submodule Heroku et de *push* sur la branche `heroku/master`.

/!\ Afin de ne pas avoir à *push* à chaque test, il est possible d'utiliser un wamp en local pour tester les fonctionnalités 
avant d'appliquer les changements sur le dépôt distant.


### La base de données

La base de données fonctionne sous PostegreSQL et est liée à l'application Heroku.

Les informations nécessaires (*credentials*) pour s'y connecter se trouve sur la page de l'application Heroku, dans la section add-ons.

L'IDE conseillé pour manier la base de données est **pgAdmin**. Il peut être téléchargé avec [PostgreSQL](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads).



