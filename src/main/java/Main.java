import java.util.Scanner;

public class Main {

    //Constantes
    static final short MAX_PTS_VIE = 100;
    static final short PTS_BOUCLIER = 25;
    static final short MAX_ATTAQUE_ENNEMI = 5;
    static final short MAX_VIE_ENNEMI = 30;
    static final short MAX_ATTAQUE_JOUEUR = 5;
    static final short REGENARATION_BOUCLIER_PAR_TOUR = 10;

    //Variables
    static String nomPersonnage;
    static short ptsDeVie;
    static boolean bouclierActif = true;
    static short ptsBouclier;
    static short nbEnnemisTues = 0;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * Méthode permettant d'initialiser un nombre d'ennemis saisi avec un nombre au hasard de point de vie
     * @return le tableau contenant les ennemis
     */
    static short[] initEnnemis(){
        System.out.println("Combien souhaitez-vous combattre d'ennemis ?");
        Scanner scanner = new Scanner(System.in);
        short nbEnnemis = scanner.nextShort();
        System.out.println("Génération des ennemis...");
        short[] ennemis = new short[nbEnnemis];
        for(short i = 0; i < nbEnnemis; i++){
            ennemis[i] = (short)nombreAuHasard(MAX_VIE_ENNEMI);
            System.out.println("Ennemi numéro " + (i + 1) + " : " + Util.color(ennemis[i], Color.PURPLE));
        }
        return ennemis;
    }

    /**
     * Méthode qui demande le nom du personnage et lui affecte ses points de vie et de bouclier
     */
    static void initPersonnage() {
        System.out.println("Saisir le nom de votre personnage");
        Scanner scanner = new Scanner(System.in);
        nomPersonnage = scanner.next();
        ptsDeVie = MAX_PTS_VIE;
        ptsBouclier = bouclierActif ? PTS_BOUCLIER : 0;
        System.out.println("OK " + Util.color(nomPersonnage, Color.GREEN) + " ! C'est parti !");
    }

    /**
     * Méthode qui affiche le nom du joueur et ses points de vie en rouge et bouclier en bleu
     */
    public static void afficherPersonnage(){
        System.out.print(Util.color(nomPersonnage, Color.GREEN) + " (" + Util.color(ptsDeVie, Color.RED));
        if(bouclierActif){
            System.out.print(" " + Util.color(ptsBouclier, Color.BLUE));
        }
        System.out.print(")");
    }

    /**
     * Méthode réalisant l'attaque du joueur sur l'ennemi ou de l'ennemi sur le joueur
     * @param ennemi Points de vie de l'ennemi
     * @param joeurAttaque Est-ce que c'est le joueur qui attaque ?
     * @return les points de vie restants de l'ennemi après l'attaque
     */
    static short attaque(short ennemi, boolean joeurAttaque) {
        if(ennemi <= 0 || (ptsBouclier <= 0 && ptsDeVie <= 0)){
            //Un de deux combattants est mort, on arrête le combat
            return ennemi;
        }
        if (joeurAttaque){
            ennemi = attaqueJoueur(ennemi);
        } else {
            attaqueEnnemi();
        }
        return ennemi;
    }

    /**
     * Méthode réalisant l'attaque de l'ennemi sur le joueur
     */
    static void attaqueEnnemi() {
        //Le bouclier reçoit en priorité les dommages
        short dommages = nombreAuHasard(MAX_ATTAQUE_ENNEMI);
        System.out.print("L'" + Util.color("ennemi", Color.YELLOW) + " attaque " + Util.color(nomPersonnage, Color.GREEN) + " ! ");
        System.out.print("Il lui fait " + dommages + " points de dommages ! ");
        if (ptsBouclier > 0){
            short dommagesBouclier = (short) Math.min(ptsBouclier, dommages);
            System.out.print("Le bouclier perd " + Util.color(dommagesBouclier, Color.BLUE) + " points. ");
            ptsBouclier -= dommagesBouclier;
            dommages -= dommagesBouclier;
        }
        //Ensuite la vie du joueur
        if (dommages > 0){
            System.out.print(Util.color(nomPersonnage, Color.GREEN) + " perd " + Util.color(dommages, Color.RED) + " points de vie ! ");
            ptsDeVie -= dommages;
        }
        System.out.println();
    }

    /**
     * Méthode réalisant l'attaque du joueur sur un ennemi
     * @param ennemi Points de vie de l'ennemi
     * @return les points de vie de l'ennemi après l'attaque
     */
    static short attaqueJoueur(short ennemi) {
        //Le joueur attaque l'ennemi
        short dommages = nombreAuHasard(MAX_ATTAQUE_JOUEUR);
        System.out.print(Util.color(nomPersonnage, Color.GREEN) + " attaque l'" + Util.color("ennemi", Color.YELLOW) + " ! ");
        System.out.println("Il lui fait perdre " + Util.color(dommages, Color.PURPLE) + " points de dommages");
        ennemi -= dommages;
        return ennemi;
    }

    /**
     * Méthode simulant une tentative avec un pourcentage de réussite donné
     * @param pourcentageDeChance Le pourcentage de chance que la tentative réussisse
     * @return si la tentative a fonctionné
     */
    static boolean hasard(double pourcentageDeChance) {
        return pourcentageDeChance < Math.random();
    }

    /**
     * Méthode renvoyant un nombre au hasard entre 0 et le paramètre max
     * @param max valeur maximale que peut prendre le nombre
     * @return le nombre au hasard
     */
    static short nombreAuHasard(short max){
        return (short) Math.round(Math.random() * max);
    }


}
