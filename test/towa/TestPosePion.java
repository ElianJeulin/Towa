/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ejeul
 */
public class TestPosePion {
    
    @Test
    public void testPosePossible() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        Coordonnees coord;
        //Pose d'un pion sur une case vide qui a une altitude inferieure à 3
        coord = Coordonnees.depuisCars('a', 'F');
        assertTrue(PosePion.posePossible(plateau, coord, true));
        //Pose d'un pion sur une case vide qui a une altitude égale à 4
        coord = Coordonnees.depuisCars('e', 'M');
        assertFalse(PosePion.posePossible(plateau, coord, true));
        //Pose d'un pion une case qui a un niveau inferieure à 3 et pas de pions adjacents
        coord = Coordonnees.depuisCars('h', 'J');
        assertTrue(PosePion.posePossible(plateau, coord, true));
        //Pose d'un pion noir sur une altitude de 3 adjacent à un pion blanc: impossible
        coord = Coordonnees.depuisCars('g', 'M');
        assertFalse(PosePion.posePossible(plateau, coord, true));  
        //Pose d'un pion sur une case vide
        coord = Coordonnees.depuisCars('a', 'A');
        assertTrue(PosePion.posePossible(plateau, coord, true));
        //Pose d'un pion sur une case qui a déjà un pion de la même couleur et altitude < 4
        coord = Coordonnees.depuisCars('d', 'L');
        assertTrue(PosePion.posePossible(plateau, coord, false));
        //Pose d'un pion sur une case qui à déjà un pion de la même couleur et altitude >= 4
        coord = Coordonnees.depuisCars('d', 'K');
        assertFalse(PosePion.posePossible(plateau, coord, true));
        //Pose d'un pion sur une case qui à déjà un pion d'une couleur différente
        coord = Coordonnees.depuisCars('d', 'L');
        assertFalse(PosePion.posePossible(plateau, coord, true));
        //Pose d'un pion sur une case qui à un niveau 3 et une case de couleur différente adjacente
        coord = Coordonnees.depuisCars('c', 'K');
        assertFalse(PosePion.posePossible(plateau, coord, false));
        //Pose d'un pion sur une case qui à un niveau 2 et une case de couleur différente adjacente
        coord = Coordonnees.depuisCars('c', 'M');
        assertTrue(PosePion.posePossible(plateau, coord, true));
        
        //Pose d'un pion qui a une nature d'eau: impossible
        coord = Coordonnees.depuisCars('a', 'G');
        assertFalse(PosePion.posePossible(plateau, coord, true));
    }
    
    @Test
    public void testCaseAdjacente() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        Coordonnees coord;
        //Vérifie si il y a une tour adjacente d'une autre couleur
        coord = Coordonnees.depuisCars('m', 'G');
        assertTrue(PosePion.caseAdjacente(plateau, coord, true));
        //Vérifie une case noire qui n'a pas de tour adjacente autour
        coord = Coordonnees.depuisCars('a', 'P');
        assertFalse(PosePion.caseAdjacente(plateau, coord, true));
        //Verifie une case blanche qui a un tour adjacente à lui mais de la même couleur
        coord = Coordonnees.depuisCars('j', 'H');
        assertFalse(PosePion.caseAdjacente(plateau, coord, false));
    }
    
     @Test
    public void testPionsAdj() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1); 
        Coordonnees coord;
        //Selection d'une case qui a une altitude inferieur à 3 et pas de pions adj autour
        coord = Coordonnees.depuisCars('d', 'G');
        assertEquals(0, 
                PosePion.pionsAdj(plateau, coord, false));
        //Selection d'une case qui a une altitude inferieur à 2 et un pion adj d'une autre couleur autour
        coord = Coordonnees.depuisCars('f', 'H');
        assertEquals(1, 
                PosePion.pionsAdj(plateau, coord, true)); 
        //Selection d'une case vide qui n'a pas de pions adjacents
        coord = Coordonnees.depuisCars('a', 'A');
        assertEquals(0, 
                PosePion.pionsAdj(plateau, coord, false));
        //Selection d'une case où il y a déjà un pion
        coord = Coordonnees.depuisCars('d', 'K');
        assertEquals(0,
                PosePion.pionsAdj(plateau, coord, true));
         //Selection d'une case vide qui a un ou plusieurs pions adjacents d'une autre couleur
        coord = Coordonnees.depuisCars('f', 'H');
        assertEquals(1,
                PosePion.pionsAdj(plateau, coord, true));
        //Selection d'une case vide qui a des pions adjacents de la même couleur
        coord = Coordonnees.depuisCars('k', 'H');
        assertEquals(0,
                PosePion.pionsAdj(plateau, coord, false));
        //Selection d'une case vide qui a des pions adjacents de la même couleur et de la couleur différente
        coord = Coordonnees.depuisCars('h', 'I');
        assertEquals(1,
                PosePion.pionsAdj(plateau, coord, false));
    }
    
    @Test
    public void testPlateauCouvert() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        //Vérifie si le plateau est couvrant: faux
        assertFalse(PosePion.plateauCouvert(plateau, true));
        
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        
        //Vérifie si le plateau est couvrant: vrai
        assertTrue(PosePion.plateauCouvert(plateau2, true));
        
    }
    
    
    final String PLATEAU_NIVEAU1
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+E--+---+---+---+---+---+---+---+---+---+\n"
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
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
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
            + "p|  3|  2|  2|  2|  2|  1|  1|  1|  1|  2|  2|  2|  3|  2|  2|  2|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+";

    final String PLATEAU_NIVEAU2
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |B4 |   |   |   |N4 |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|N1 |   |   |   |   |   |   |B1 |   |B3 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |B1 |   |   |   |   |   |   |   |B1 |   |N1 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |B1 |   |   |N2 |B4 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|B1 |   |   |N11|   |   |   |   |   |   |   |   |   |B3 |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |N2 |   |B11|   |   |   |   |   |N1 |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |B2 |   |   |   |   |  2|   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |B4 |   |  3|  3|   |N1 |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |N1 |N1 |   |   |   |   |B22|B1 |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |N1 |   |N2 |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |N1 |   |   |   |   |N2 |   |   |   |   |B1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|N1 |N4 |   |   |N3 |   |B1 |   |   |   |   |   |   |   |   |N1 |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |B1 |   |   |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |B2 |N1 |N1 |   |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |N1 |   |   |   |   |   |N1 |   |   |   |   |   |   |   |B4 |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |N2 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";  
}












































