# Jeu de Pong

## Présentation

Ce Pong est un jeu de raquettes programmé avec Java 17 en utilisant JavaFX à l'occasion de la 50ᵉ anniversaire de ce jeu. Le projet est configuré avec Gradle utilisant le plugin JavaFX. Ce jeu est largement inspiré du jeu [Pong](https://fr.wikipedia.org/wiki/Pong), un classique des salles d'arcades dans les années 1970.

Le principe est simple : un terrain (le "*court*") deux raquettes et une balle. Le jeu se joue à deux, chaque joueur pouvant déplacer sa raquette sur un axe haut/bas et ayant pour but de ne pas laisser passer la balle derrière sa raquette. La balle se déplace à vitesse constante et rebondit sur les parois (limites haute et basse de la fenêtre) et sur les raquettes en fonction de l'angle de collision avec la raquette.

Le jeu s'ouvre sur une jolie interface avec des animations faites avec JavaFX et du CSS combiné avec du Java, il propose un mécanisme de jeu plus moderne, un adversaire IA avec plusieurs niveaux de difficulté, un mode contre la montre; le joueur a la possibilité de choisir parmi tout ça à partir de menu d'accueil.

## Améliorer Pong

Pour participer au développement du jeu et proposer des améliorations ou des modes différents, il est possible de cloner le dépôt. Depuis la console :

```bash
$ git clone https://gaufre.informatique.univ-paris-diderot.fr/NeXT/pong
```

## Exécution, compilation

Après avoir téléchargé/cloné les sources, vous pouvez compiler et exécuter le projet à l'aide de gradle.
Le principe c'est que le script `gradlew` dans le répertoire du projet téléchargera puis utilisera la version de gradle qui fonctionne avec le projet.

Pour compiler, il suffit d'exécuter, depuis le répertoire `pong` :

```bash
`./gradlew build`
```

Pour exécuter, il suffit d'exécuter, depuis le répertoire `pong` :

```bash
`./gradlew run`
```



Le projet en lui-même a besoin de Java 17 pour être compilé et exécuté.

## Jouer

La raquette de gauche est contrôlée par les touches CONTROL et ALT, alors que celle de droite est contrôlée par les flèches HAUT et BAS.