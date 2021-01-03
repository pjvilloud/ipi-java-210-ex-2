import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyShort;
import static org.mockito.ArgumentMatchers.eq;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest {

    @Test
    public void exo00() throws Exception {
        TestUtils.checkStaticMethod("Main", "main", "void", String[].class);
    }

    @Test
    public void exo01() throws Exception {
        TestUtils.checkStaticFinalField("Main", "MAX_PTS_VIE", "short", (short)100);
        TestUtils.checkStaticFinalField("Main", "ACTIVER_BOUCLIER", "boolean", true);
        TestUtils.checkStaticFinalField("Main", "PTS_BOUCLIER", "short", (short)25);
        TestUtils.checkStaticFinalField("Main", "MAX_ATTAQUE_ENNEMI", "short", (short)5);
        TestUtils.checkStaticFinalField("Main", "MAX_VIE_ENNEMI", "short", (short)30);
        TestUtils.checkStaticFinalField("Main", "MAX_ATTAQUE_JOUEUR", "short", (short)5);
        TestUtils.checkStaticFinalField("Main", "REGENARATION_BOUCLIER_PAR_TOUR", "short", (short)1);
    }

    @Test
    public void exo02() throws Exception{
        TestUtils.checkStaticField("Main", "nomPersonnage", TestUtils.STRING, null);
        TestUtils.checkStaticField("Main", "ptsDeVie", "short", (short)0);
        TestUtils.checkStaticField("Main", "ptsBouclier", "short", (short)0);
        TestUtils.checkStaticField("Main", "nbEnnemisTues", "short", (short)0);
    }

    @Test
    public void exo03() throws Exception{
        TestUtils.checkStaticMethod("Main", "initPersonnage", "void");

        ByteArrayInputStream inContent;
        inContent = new ByteArrayInputStream("test".getBytes());
        System.setIn(inContent);

        ByteArrayOutputStream outContent;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        TestUtils.callDMethod("Main", "initPersonnage");
        Assertions.assertThat(outContent.toString().trim()).isEqualToNormalizingNewlines("Saisir le nom de votre personnage\n" +
                "OK " + Util.color("test", Color.GREEN) + " ! C'est parti !");
        TestUtils.checkStaticField("Main", "ptsDeVie", "short", (short)100);
        TestUtils.checkStaticField("Main", "ptsBouclier", "short", (short)25);
        TestUtils.checkStaticField("Main", "nomPersonnage", TestUtils.STRING, "test");
    }

    @Test
    public void exo04() throws Exception{
        TestUtils.checkStaticMethod("Main", "hasard", "boolean", double.class);
    }

    @Test
    public void exo05() throws Exception{
        TestUtils.checkStaticMethod("Main", "nombreAuHasard", "short", short.class);
        List<Boolean> present = Arrays.asList(false, false, false, false, false);
        while(present.contains(false)){
            short result = (short)TestUtils.callMethodPrimitiveParameters("Main", "nombreAuHasard", (short)4);
            Assertions.assertThat(result).isGreaterThanOrEqualTo((short) 0);
            Assertions.assertThat(result).isLessThanOrEqualTo((short) 4);
            present.set(result, true);
        }
    }

    @Test
    public void exo06() throws Exception {
        /**
         *  //Le joueur attaque l'ennemi
         *         short dommages = nombreAuHasard(MAX_ATTAQUE_JOUEUR);
         *         System.out.print(Util.color(nomPersonnage, Color.GREEN) + " attaque l'" + Util.color("ennemi", Color.YELLOW) + " !");
         *         System.out.println("Il lui fait " + Util.color(dommages, Color.PURPLE) + " points de dommages");
         *         ennemi -= dommages;
         *         return ennemi;
         */
        TestUtils.checkStaticMethod("Main", "attaqueJoueur", "short", short.class);
        Main.nomPersonnage = "test";
        ByteArrayOutputStream outContent;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try (MockedStatic<Main> mockedStatic = Mockito.mockStatic(Main.class)) {

            mockedStatic
                    .when(() -> Main.nombreAuHasard((short) 5))
                    .thenReturn((short)3);
            mockedStatic.when(() -> Main.attaqueJoueur((short) 10))
                    .thenCallRealMethod();

            short result = Main.attaqueJoueur((short) 10);

            Assertions.assertThat(result).isEqualTo((short)7);

            Assertions.assertThat(outContent.toString()).isEqualToIgnoringNewLines(
                    "" + Util.color("test", Color.GREEN) + " attaque l'" +  Util.color("ennemi", Color.YELLOW)
                            + " ! Il lui fait perdre "+Util.color("3", Color.PURPLE)+" points de dommages");


        }

    }

}
