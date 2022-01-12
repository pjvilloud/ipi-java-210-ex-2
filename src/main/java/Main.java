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
        ptsBouclier = PTS_BOUCLIER;
        scanner.close();
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

}
