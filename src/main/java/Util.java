public class Util {
    public static String color(short number, Color color) {
        return color(String.valueOf(number), color);
    }

    /**
     * Méthode permettant d'afficher du texte en couleur dans la console
     * @param text Le texte à afficher
     * @param color La couleur avec laquelle le texte doit s'afficher
     * @return Le texte à afficher avec la couleur
     */
    public static String color(String text, Color color){
        String colorCode = "0";
        if(color == Color.RED){
            colorCode = "31";
        } else if(color == Color.BLUE){
            colorCode = "34";
        } else if(color == Color.GREEN){
            colorCode = "32";
        } else if(color == Color.YELLOW){
            colorCode = "33";
        } else if(color == Color.PURPLE){
            colorCode = "35";
        }

        return (char)27 + "[" + colorCode + "m" + text + (char)27 + "[0m";
    }
}
