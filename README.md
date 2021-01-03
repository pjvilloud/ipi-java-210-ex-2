# TP du cours Java 210

Nous allons créer un jeu de combat simple en mode tour par tour entre le joueur et des ennemis. Pour cela, prenez les questions dans
l'ordre et exécuter les tests de la classe `MainTest` (Clic droit sur la classe `MainTest` => `Run MainTest`).
Les tests sont tous rouge ou orange et passeront verts (signifiant que vous avez répondu correctement à la question) au fur et à mesure que vous avancez.
Si le test est rouge ou orange alors que vous pensez avoir répondu à la question, cliquer sur le test en question en bas à gauche de votre IDE
et analysez le message d'erreur.

## Exercice 1

Créer les constantes suivantes dans la classe `Main` (au niveau de la classe, pas à l'intérieur de la méthode `main`).
Utiliser des types primitifs et préfixer toutes les déclarations par `static`

- `MAX_PTS_VIE` de type `short` et de valeur `100`
- `PTS_BOUCLIER` de type `short` et de valeur `25`
- `MAX_ATTAQUE_ENNEMI` de type `short` et de valeur `5`
- `MAX_VIE_ENNEMI` de type `short` et de valeur `30`
- `MAX_ATTAQUE_JOUEUR` de type `short` et de valeur `5`
- `REGENARATION_BOUCLIER_PAR_TOUR` de type `short` et de valeur `10` 

## Exercice 2

Créer les variables suivantes dans la classe `Main` (au niveau de la classe, pas à l'intérieur de la méthode `main`).

- `nomPersonnage` de type `String`
- `ptsDeVie` de type `short`
- `ptsBouclier` de type `short`
- `nbEnnemisTues` de type `short`
- `bouclierActif` de type `boolean` et de valeur `true`

## Exercice 3

Créer la méthode (toujours `static`) `initPersonnage` permettant de demander à l'utilisateur le nom de son personnage,
et d'affecter ses points de vie et de bouclier (voir exo 2) aux valeurs par défaut (voir les constantes de l'exo 1). 
L'affichage devra être exactement celui-ci (la deuxième ligne représente le nom saisi par l'utilisateur) : 

A noter que partout dans le programme ou le nom du personnage sera affiché (ici John dans l'exemple), il faudra qu'il soit en vert 
(Utiliser la méthode `Util.color()`).

```
Saisir le nom de votre personnage
John
OK John ! C'est parti !
```

## Exercice 4

Créer la méthode (toujours `static`) `hasard` prenant en paramètre un pourcentage de chance (exemple `0.5` pour 50% de chance)
et renvoyant (au hasard) vrai dans X% des cas (avec X = votre pourcentage de chance passé en paramètre). Vous pouvez
vous aider de la méthode `Math.random()` qui renvoie un `double` entre 0 et 1.

## Exercice 5 

Créer la méthode (toujours `static`) `nombreAuHasard` prenant en paramètre un nombre de type `short`
et renvoyant (au hasard) un nombre entre 0 et ce nombre. Vous pouvez
vous aider de la méthode `Math.random()` qui renvoie un `double` entre 0 et 1 et de la méthode `Math.round(nb)` permettant 
d'arrondir un nombre à virgule (passé en paramètre) à l'entier le plus proche.

## Exercice 6

Créer la méthode (toujours `static`) `attaqueJoueur` prenant en paramètre un nombre de type `short` correspondant au nombre de 
points de vie de l'ennemi à combattre. Cette méthode retourne le nombre de points de vie restant de l'ennemi (peut être négatif...)
après l'attaque du joueur. Le nombre de points de vie à retrancher est un nombre au hasard entre 0 et le nombre de points maximum
que peut enlever le joueur (voir les constantes). L'affichage produit par cette méthode devra être exactement celui-ci () : 

A noter que le mot ennemi doit être affiché en jaune et le nombre de points de dommage retranchés (ici 4 dans l'exemple)
doit être affiché en violet (et le nom toujours en vert !).

Pour tester complètement votre méthode une fois celle-ci développée, décommenter les lignes 77 à 98 dans `MainTest`

```
John attaque l'ennemi ! Il lui fait perdre 4 points de dommages
```

## Exercice 7

Créer la méthode (toujours `static`) `afficherPersonnage` permettant d'afficher le nom du personnage, le nombre de points de vie 
et de bouclier (si le bouclier est actif...) qu'il détient actuellement. L'affichage produit par cette méthode devra être exactement celui-ci

A noter que le nombre de points de vie doit être affiché en rouge et le nombre de points du bouclier en bleu (et le nom toujours en vert !)

``` 
John (100 25)
```
ou si le bouclier est desactivé 
``` 
John (100)
```

## Exercice 8

Créer la méthode (toujours `static`) `attaqueEnnemi` qui permet à l'ennemi d'attaquer le personnage. Le nombre de points 
de dommages effectués par l'attaque est un nombre au hasard entre 0 et le nombre de points maximum
que peut enlever l'ennemi (voir les constantes). C'est en priorité le bouclier qui prend les dommages jusqu'à épuisement
puis les points de vie du personnage. L'affichage produit par cette méthode devra être exactement le suivant :

À noter que le mot ennemi est à afficher en jaune, le nom du personnage en vert, le nombre de points de bouclier enlevés en bleu, 
et le nombre de points de vie enlevés en rouge. Le nombre de points de dommages total est affiché normalement

Pour tester complètement votre méthode une fois celle-ci développée, décommenter les lignes 134 à 172 dans `MainTest`

Si le bouclier a ici 3 points
``` 
L'ennemi attaque John ! Il lui fait 4 points de dommages ! Le bouclier perd 3 points. John perd 1 points de vie
```
ou si le bouclier a plus de points que l'attaque
``` 
L'ennemi attaque John ! Il lui fait 4 points de dommages ! Le bouclier perd 4 points.
```
ou si le bouclier est vide
``` 
L'ennemi attaque John ! Il lui fait 4 points de dommages ! John perd 4 points de vie
```

## Exercice 9

Créer la méthode (toujours `static`) `attaque` prenant en paramètre un nombre de points d'ennemi et un booléen
définissant si c'est le joueur qui joue et en fonction de ces paramètres, appeler la bonne méthode d'attaque. Renvoyer
le nombre de points de l'ennemi après l'attaque en retour. Si l'ennemi ou le personnage est mort, pas besoin de combattre !

## Exercice 10

Créer la méthode (toujours `static`) `initEnnemis` renvoyant un tableau de `short` et permettant de gérer un nombre 
d'ennemis saisi par l'utilisateur (ici dans l'exemple 3). Le tableau contiendra les points de vie des ennemis qui seront déterminés au hasard
entre 0 et le nombre de points de vie maximum (voir les constantes...). L'affichage produit par cette méthode devra être exactement celui-ci :

A noter que le nombre de points de vie de l'ennemi doit être affiché en violet, le reste de manière normale.

``` 
Combien souhaitez-vous combattre d'ennemis ?
3
Génération des ennemis...
Ennemi numéro 1 : 4
Ennemi numéro 2 : 3
Ennemi numéro 3 : 5
```

## Exercice 11

Il est temps de tout câbler ! Remplir la méthode `main` afin de mettre en place le jeu :

- Commencer par initialiser le personnage du joueur ainsi que les ennemis.
- Combattre chacun des ennemis en choisissant qui attaque en premier au hasard (une chance sur 2). 
Les combats sont à mort (ils combattent donc jusqu'à ce que l'un des deux meure) ! 
  On affichera avant chaque tour l'état du personnage et l'état de l'ennemi. 
  Si c'est l'ennemi qui meure, on incrémentera le nombre de victimes du joueur.
  Si c'est le joueur qui meure, on affichera le nombre de victimes qu'il a courageusement vaincues.
- Entre chaque ennemi, le bouclier (si activé) se régénère partiellement (voir les constantes...) 
  sans toutefois pouvant excéder la capacité maximale du bouclier.
- Après chaque ennemi, le joueur peut passer à l'ennemi suivant en saisissant la lettre S 
  ou il peut fuir les combats en saisissant toute autre chaîne.

Exemples d'exécution du jeu : 

``` 
Saisir le nom de votre personnage
John
OK John ! C'est parti !
Combien souhaitez-vous combattre d'ennemis ?
2
Génération des ennemis...
Ennemi numéro 1 : 25
Ennemi numéro 2 : 8
==========================================================
Combat avec un ennemi possédant 25 points de vie !
John (100 25) vs ennemi (25)
John attaque l'ennemi ! Il lui fait perdre 3 points de dommages
L'ennemi attaque John ! Il lui fait 5 points de dommages ! Le bouclier perd 5 points. 
John (100 20) vs ennemi (22)
John attaque l'ennemi ! Il lui fait perdre 1 points de dommages
L'ennemi attaque John ! Il lui fait 1 points de dommages ! Le bouclier perd 1 points. 
John (100 19) vs ennemi (21)
John attaque l'ennemi ! Il lui fait perdre 2 points de dommages
L'ennemi attaque John ! Il lui fait 2 points de dommages ! Le bouclier perd 2 points. 
John (100 17) vs ennemi (19)
John attaque l'ennemi ! Il lui fait perdre 2 points de dommages
L'ennemi attaque John ! Il lui fait 4 points de dommages ! Le bouclier perd 4 points. 
John (100 13) vs ennemi (17)
John attaque l'ennemi ! Il lui fait perdre 0 points de dommages
L'ennemi attaque John ! Il lui fait 1 points de dommages ! Le bouclier perd 1 points. 
John (100 12) vs ennemi (17)
John attaque l'ennemi ! Il lui fait perdre 4 points de dommages
L'ennemi attaque John ! Il lui fait 1 points de dommages ! Le bouclier perd 1 points. 
John (100 11) vs ennemi (13)
John attaque l'ennemi ! Il lui fait perdre 4 points de dommages
L'ennemi attaque John ! Il lui fait 2 points de dommages ! Le bouclier perd 2 points. 
John (100 9) vs ennemi (9)
John attaque l'ennemi ! Il lui fait perdre 1 points de dommages
L'ennemi attaque John ! Il lui fait 4 points de dommages ! Le bouclier perd 4 points. 
John (100 5) vs ennemi (8)
John attaque l'ennemi ! Il lui fait perdre 2 points de dommages
L'ennemi attaque John ! Il lui fait 0 points de dommages ! Le bouclier perd 0 points. 
John (100 5) vs ennemi (6)
John attaque l'ennemi ! Il lui fait perdre 1 points de dommages
L'ennemi attaque John ! Il lui fait 4 points de dommages ! Le bouclier perd 4 points. 
John (100 1) vs ennemi (5)
John attaque l'ennemi ! Il lui fait perdre 1 points de dommages
L'ennemi attaque John ! Il lui fait 4 points de dommages ! Le bouclier perd 1 points. John perd 3 points de vie ! 
John (97 0) vs ennemi (4)
John attaque l'ennemi ! Il lui fait perdre 2 points de dommages
L'ennemi attaque John ! Il lui fait 4 points de dommages ! John perd 4 points de vie ! 
John (93 0) vs ennemi (2)
John attaque l'ennemi ! Il lui fait perdre 2 points de dommages
L'ennemi est mort ! Au suivant !
Régénération du bouclier : +10
Saisisser S pour passer au combat suivant ou n'importe quoi d'autre pour fuir...
S
==========================================================
Combat avec un ennemi possédant 8 points de vie !
John (93 10) vs ennemi (8)
John attaque l'ennemi ! Il lui fait perdre 4 points de dommages
L'ennemi attaque John ! Il lui fait 4 points de dommages ! Le bouclier perd 4 points. 
John (93 6) vs ennemi (4)
John attaque l'ennemi ! Il lui fait perdre 4 points de dommages
L'ennemi est mort ! Au suivant !
John a tué tous les ennemis !
```

ou

```
Saisir le nom de votre personnage
John
OK John ! C'est parti !
Combien souhaitez-vous combattre d'ennemis ?
2
Génération des ennemis...
Ennemi numéro 1 : 2
Ennemi numéro 2 : 20
==========================================================
Combat avec un ennemi possédant 2 points de vie !
John (100 25) vs ennemi (2)
L'ennemi attaque John ! Il lui fait 1 points de dommages ! Le bouclier perd 1 points. 
John attaque l'ennemi ! Il lui fait perdre 3 points de dommages
L'ennemi est mort ! Au suivant !
Régénération du bouclier : +10
Saisisser S pour passer au combat suivant ou n'importe quoi d'autre pour fuir...
Courage fuyons !
Vous avez tué 1 ennemis mais êtes partis lâchement avant la fin...
```

ou encore

```
Saisir le nom de votre personnage
John
OK John ! C'est parti !
Combien souhaitez-vous combattre d'ennemis ?
50
Génération des ennemis...
Ennemi numéro 1 : 14
Ennemi numéro 2 : 7
Ennemi numéro 3 : 6
Ennemi numéro 4 : 2
Ennemi numéro 5 : 13
[...]
==========================================================
Combat avec un ennemi possédant 14 points de vie !
John (100 25) vs ennemi (14)
L'ennemi attaque John ! Il lui fait 2 points de dommages ! Le bouclier perd 2 points. 
John attaque l'ennemi ! Il lui fait perdre 0 points de dommages
John (100 23) vs ennemi (14)
L'ennemi attaque John ! Il lui fait 0 points de dommages ! Le bouclier perd 0 points. 
John attaque l'ennemi ! Il lui fait perdre 4 points de dommages
John (100 23) vs ennemi (10)
L'ennemi attaque John ! Il lui fait 3 points de dommages ! Le bouclier perd 3 points. 
John attaque l'ennemi ! Il lui fait perdre 1 points de dommages
John (100 20) vs ennemi (9)
L'ennemi attaque John ! Il lui fait 3 points de dommages ! Le bouclier perd 3 points. 
John attaque l'ennemi ! Il lui fait perdre 2 points de dommages
John (100 17) vs ennemi (7)
L'ennemi attaque John ! Il lui fait 0 points de dommages ! Le bouclier perd 0 points. 
John attaque l'ennemi ! Il lui fait perdre 2 points de dommages
John (100 17) vs ennemi (5)
L'ennemi attaque John ! Il lui fait 2 points de dommages ! Le bouclier perd 2 points. 
John attaque l'ennemi ! Il lui fait perdre 2 points de dommages
John (100 15) vs ennemi (3)
L'ennemi attaque John ! Il lui fait 4 points de dommages ! Le bouclier perd 4 points. 
John attaque l'ennemi ! Il lui fait perdre 2 points de dommages
John (100 11) vs ennemi (1)
L'ennemi attaque John ! Il lui fait 3 points de dommages ! Le bouclier perd 3 points. 
John attaque l'ennemi ! Il lui fait perdre 2 points de dommages
L'ennemi est mort ! Au suivant !
Régénération du bouclier : +10
Saisisser S pour passer au combat suivant ou n'importe quoi d'autre pour fuir...
S
[...]
==========================================================
Combat avec un ennemi possédant 27 points de vie !
John (9 10) vs ennemi (27)
L'ennemi attaque John ! Il lui fait 3 points de dommages ! Le bouclier perd 3 points. 
John attaque l'ennemi ! Il lui fait perdre 3 points de dommages
John (9 7) vs ennemi (24)
L'ennemi attaque John ! Il lui fait 2 points de dommages ! Le bouclier perd 2 points. 
John attaque l'ennemi ! Il lui fait perdre 5 points de dommages
John (9 5) vs ennemi (19)
L'ennemi attaque John ! Il lui fait 3 points de dommages ! Le bouclier perd 3 points. 
John attaque l'ennemi ! Il lui fait perdre 0 points de dommages
John (9 2) vs ennemi (19)
L'ennemi attaque John ! Il lui fait 4 points de dommages ! Le bouclier perd 2 points. John perd 2 points de vie ! 
John attaque l'ennemi ! Il lui fait perdre 1 points de dommages
John (7 0) vs ennemi (18)
L'ennemi attaque John ! Il lui fait 4 points de dommages ! John perd 4 points de vie ! 
John attaque l'ennemi ! Il lui fait perdre 4 points de dommages
John (3 0) vs ennemi (14)
L'ennemi attaque John ! Il lui fait 3 points de dommages ! John perd 3 points de vie ! 
John est mort mais a tué 23 ennemis
```