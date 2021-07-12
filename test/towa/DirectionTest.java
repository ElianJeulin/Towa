package towa;

/**
 * @author Ejeul
 */

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Tests sur la classe Direction.
 */
public class DirectionTest {
    
    /**
    * Test des différents index dans la class Direction
    */
    @Test
    public void testIndex() {
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        Coordonnees coord;
        //Selection d'un pion sur le plateau qui retourne un indexN
        coord = Coordonnees.depuisCars('j', 'H');
        assertEquals(8,
                Direction.trouvePionNord(plateau2, coord));
        //Selection d'une case vide sur le plateau
        coord = Coordonnees.depuisCars('b', 'C');
        assertEquals(-1,
                Direction.trouvePionNord(plateau2, coord));
        //Selection d'un pion sur le plateau qui retourne un indexE
        coord = Coordonnees.depuisCars('c', 'C');
        assertEquals(2,
                Direction.trouvePionEst(plateau2, coord));
        //Selection d'un pion sur le plateau qui retourne un indexS
        coord = Coordonnees.depuisCars('c', 'C');
        assertEquals(4,
                Direction.trouvePionSud(plateau2, coord));
        //Selection d'un pion sur le plateau qui retourne un indexO
        coord = Coordonnees.depuisCars('c', 'L');
        assertEquals(1,
                Direction.trouvePionOuest(plateau2, coord));
        //Selection d'un pion sur le plateau n'a rien sur sa colonne Ouest
        coord = Coordonnees.depuisCars('f', 'P');
        assertEquals(-1,
                Direction.trouvePionOuest(plateau2, coord));
        //Selection d'un pion sur le plateau qui est au bord de ce dernier
        coord = Coordonnees.depuisCars('b', 'A');
        assertEquals(-1,
                Direction.trouvePionOuest(plateau2, coord));
        //Selection d'un pion qui n'a rien sur sa colonne sud
        coord = Coordonnees.depuisCars('f', 'P');
        assertEquals(-1,
                Direction.trouvePionSud(plateau2, coord));
        //Selection d'un pion qui n'a rien sur sa colonne est
        coord = Coordonnees.depuisCars('f', 'P');
        assertEquals(-1,
                Direction.trouvePionEst(plateau2, coord));
        //Selection d'un pion qui n'a rien sur sa colonne nord
        coord = Coordonnees.depuisCars('b', 'L');
        assertEquals(-1,
                Direction.trouvePionNord(plateau2, coord));
 
        
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        //Selection d'un pion qui n'a pas de pion vers le nord car il est sur le bord
        coord = Coordonnees.depuisCars('a', 'P');
        assertEquals(-1,
                Direction.trouvePionNord(plateau, coord));
        //Selection d'un pion qui a un pion vers le sud à l'opposé
        coord = Coordonnees.depuisCars('a', 'P');
        assertEquals(15,
                Direction.trouvePionSud(plateau, coord));
         //Selection d'un pion qui a un pion vers l'est qui n'est pas influencé par l'eau
        coord = Coordonnees.depuisCars('g', 'H');
        assertEquals(4,
                Direction.trouvePionEst(plateau, coord));
    }
    
    final String PLATEAU_NIVEAU1
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |  1|  2|  2|  2|  2|  2|  3|  3|  3|  4|  4|  4|  3|  2|N22|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|  1|  2|  2|  2|  2|  2|  2|  3|  3|  3|  3|  4|  3|  2|  2|  2|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|  1|  2|  2|  2|  2|  2|  2|  2|  2|  3|  3|  3|  2|  3|  2|  2|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|  2|  3|  2|  2|  3|  2|  3|  2|  2|  2|N22|B12|  3|  3|  3|  3|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|  3|  3|  3|  3|  3|  3|  2|  2|  2|  2|  3|  3|  4|  4|  4|  4|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|  4|  3|  3|  2|  2|  2|  2|  2|  2|  2|  2|  3|  3|  4|  4|  4|\n"
            + " +---+---+---+---+---+---+---+---+---+E--+---+---+---+---+---+---+\n"
            + "g|  4|  4|  3|  2|  2|  1|   |B21|  1|  1|  1|B12|  3|  4|  4|  4|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|  4|  4|  3|  2|  2|N11|   |   |  1|N21|  1|  1|  3|  3|  4|  4|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|  4|  4|  4|  3|  2|  1|   |   |   |  1|  1|  1|  2|  3|  4|  4|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|  4|  4|  4|  3|  2|  1|   |B1 |   |   |   |  1|  1|  2|  3|  3|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|  4|  4|  4|  3|  2|N21|B1 |   |   |   |   |  1|  1|  2|  3|  3|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|  3|  3|  4|  3|  2|  1|B2 |   |   |   |   |   |  1|  2|  3|  3|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|  3|  3|  3|  3|  2|  1|   |   |   |   |   |   |  1|  1|  2|  3|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|  3|  3|  2|  2|  1|  1|   |   |   |   |   |  1|  1|  1|  2|  2|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|  3|  2|  1|  1|  1|  1|  1|   |   |   |  1|  1|  2|  2|  2|  2|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|  2|  2|  2|  2|  2|  1|  1|  1|  1|  2|  2|  2|  3|  2|  2|N12|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+";
            
    

    final String PLATEAU_NIVEAU2
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |   |B1 |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|N1 |   |   |   |   |   |   |B1 |   |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |B1 |   |B1 |   |   |   |   |   |N5 |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|B1 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |N2 |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |B1 |   |   |   |   |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |N1 |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |N1 |N1 |   |   |   |   |   |   |   |   |   |N1 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |N1 |   |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |N1 |   |   |   |   |N2 |   |   |   |   |B1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |N3 |B4 |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |B1 |B2 |N1 |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |N1 |N1 |N2 |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |N1 |   |   |   |   |   |N1 |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";
}

























