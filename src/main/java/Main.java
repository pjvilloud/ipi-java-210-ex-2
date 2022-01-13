import java.util.Scanner;
public class Main {
    public static final short MAX_PTS_VIE = 100;
    public static final short PTS_BOUCLIER = 25;
    public static final short MAX_ATTAQUE_ENNEMI = 5;
    public static final short MAX_VIE_ENNEMI = 30;
    public static final short MAX_ATTAQUE_JOUEUR = 5;
    public static final short REGENARATION_BOUCLIER_PAR_TOUR = 10;

    public static String nomPersonnage;
    public static short ptsDeVie;
    public static short ptsBouclier;
    public static short nbEnnemisTues;
    public static boolean bouclierActif = true;

    public static void main(String[] args)
    {
        initPersonnage();
        short ennemi = 5;
        ennemi = attaqueJoueur(ennemi);
        System.out.println("Il reste " + ennemi + " points de vie à l'ennemi");
        ptsBouclier = 1;
        attaqueEnnemi();
    }

    public static void initPersonnage()
    {   //Afficher le messafe de saisi
        //Afficher("Saisir le nom de votre personnage")
        System.out.println("Saisir le nom de votre personnage");
        //Lire la saisie utilisateur dans ma variable nomPersonnage
        //Lire(nomPersonnage);
        Scanner scanner = new Scanner(System.in);
        nomPersonnage = scanner.nextLine();
        //Afficher le message c'est parti !
        System.out.println("OK " + Util.color(nomPersonnage, Color.GREEN) + " ! C'est parti !");
        //Affecter la variable ptdDeVie
        ptsDeVie = MAX_PTS_VIE;
        //Affecter la variable ptsBouclier
        ptsBouclier = bouclierActif ? PTS_BOUCLIER : 0;
        scanner.close();
        int a = Math.min(4,5); //=>4
        int b = Math.max(4,5); //=>5
    }

    public static boolean hasard(double pourcentage){
        //pourcentage < résultat du chiffre random => true
        //sinon faux
        return pourcentage < Math.random();
    }

    public static short nombreAuHasard(short nombre) {
        return (short) Math.round(Math.random() * nombre);
    }


    public static short attaqueJoueur(short ptsVieEnnemi){
        //Déterminer la force de l'attaque du joueur
        short forceAttaque = nombreAuHasard(MAX_ATTAQUE_JOUEUR);
        //Retreancher les points de l'attaque sure les points de vie de l'ennemi
        ptsVieEnnemi -= forceAttaque;
        //Afficher les caractéristiques de l'attaque
        System.out.println(Util.color(nomPersonnage, Color.GREEN)
                + " attaque l'" + Util.color("ennemi", Color.YELLOW) + " ! Il lui fait perdre "
                + Util.color(forceAttaque, Color.PURPLE) + " points de dommages");
        //Retourner le nombre de points de vie de l'ennemi après l'attaque
        return ptsVieEnnemi;
    }

    public static void afficherPersonnage(){
    System.out.print(Util.color(nomPersonnage, Color.GREEN) + " (" + Util.color(ptsDeVie, Color.RED));
    if(bouclierActif) {
        System.out.print(" " + Util.color(ptsBouclier, Color.BLUE));
    }
    System.out.print(")");
    }

    public static void attaqueEnnemi() {
        //Déterminer la force de l'attaque
        short dommages = nombreAuHasard(MAX_ATTAQUE_ENNEMI);
        //Affiche le début de la description de l'attaque
        System.out.print("L'" + Util.color("ennemi", Color.YELLOW) + " attaque " + Util.color(nomPersonnage, Color.GREEN) + " ! ");
        System.out.print("Il lui fait" + dommages + " points de dommages ! ");
        //Le bouclier absorbe les dégats en premier s'il est actif et qu'il n'est pas bide
        if (bouclierActif && ptsBouclier > 0) {
            if (ptsBouclier >= dommages) {
                ptsBouclier -= dommages;
                System.out.print("Le bouclier perd" + Util.color(dommages, Color.BLUE) + " points de vie ! ");
                dommages = 0;
            }
            else {
                dommages -= ptsBouclier;
                ptsBouclier = 0;
            }
        }

        //Les points de vie du joueur absorbent le reste
        if (dommages > 0) {
            ptsDeVie -= dommages; //ptsDeVie = (short) (ptsDeVie - dommages)
            System.out.print(nomPersonnage + " perd " + Util.color(dommages, Color.RED) + " points de vie ! ");
        }
    }
    /*Créer la méthode (toujours `static`) `initEnnemis` renvoyant un tableau de `short` et permettant de gérer un nombre
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
*/
    public static short[] initEnnemis(){
        System.out.println("Combien souhaitez-vous combattre d'ennemis ?");
        //Récupère le nombre d'ennemis saisis par l'utilisateur
        Scanner scanner = new Scanner(System.in);
        int nbEnnemis = scanner.nextInt();
        System.out.println("Génération des ennemis...");
        //Déclarer et Initialiser le tableau qui va contenir les points de vie de tous mes ennemis
        short[] ennemis = new short[nbEnnemis];
        for(int i = 0; i < nbEnnemis; i++) {
            //Remplir la case i avec un nombre ou hasard entre 0 et la vie max des ennemis
            ennemis[i] = nombreAuHasard(MAX_ATTAQUE_ENNEMI);
            //Affichage de l'ennemi
            System.out.println("Ennemi numéro " + (i + 1) + " : " + ennemis[i]);
        }
        return ennemis;
    }
}
