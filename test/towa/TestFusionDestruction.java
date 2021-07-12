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

public class TestFusionDestruction {
    
    @Test
    public void testNbPionsARetirerFusion() {
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        Coordonnees coord;
        //Activation d'un pion N3 qui doit fusionner seulement avec un N1 et donc ne pas perdre de pions
        coord = Coordonnees.depuisCars('l', 'E');
        assertEquals(0,
                FusionDestruction.nbPionsARetirerFusion(plateau2, coord, true));
        //Activation d'un pion N1 qui doit fusionner avec un N1 et un N2 et donc pas perdre de pions
        coord = Coordonnees.depuisCars('g', 'J');
        assertEquals(0,
                FusionDestruction.nbPionsARetirerFusion(plateau2, coord, true));
        //Activation d'un pion N1 qui doit fusionner avec un N5 et un N1 et donc perdre 3 pions
        coord = Coordonnees.depuisCars('h', 'K');
        assertEquals(3,
                FusionDestruction.nbPionsARetirerFusion(plateau2, coord, true));
        //Activation d'un pion N3 qui doit fusionner avec quatre N1 et donc devenir un N4 mais perdre 3 pions
        coord = Coordonnees.depuisCars('n', 'G');
        assertEquals(3,
                FusionDestruction.nbPionsARetirerFusion(plateau2, coord, true)); 
        //Activation d'un pion N3 qui doit fusionner avec aucun pion et donc ne rien perdre
        coord = Coordonnees.depuisCars('f', 'G');
        assertEquals(0,
                FusionDestruction.nbPionsARetirerFusion(plateau2, coord, true)); 
        
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        //Activation d'un pion N1 de niveau 2 qui doit fusionner avec deux N2 et donc perdre deux pions
        coord = Coordonnees.depuisCars('h', 'F');
        assertEquals(2,
                FusionDestruction.nbPionsARetirerFusion(plateau, coord, true));
         //Activation d'un pion B2 de niveau 3 qui doit fusionner avec deux B1 et donc perdre un pion
        coord = Coordonnees.depuisCars('g', 'H');
        assertEquals(1,
                FusionDestruction.nbPionsARetirerFusion(plateau, coord, false));
        //Activation d'un pion B1 de niveau 3 qui doit fusionner avec un B1 et un B2 et donc perdre deux pions, l'eau ne dérange pas la fusion
        coord = Coordonnees.depuisCars('g', 'L');
        assertEquals(2,
                FusionDestruction.nbPionsARetirerFusion(plateau, coord, false));
        //Activation d'un pion B1 de niveau 2 qui doit fusionner avec un B1 et donc ne pas perdre de pions
        coord = Coordonnees.depuisCars('d', 'L');
        assertEquals(0,
                FusionDestruction.nbPionsARetirerFusion(plateau, coord, false));
    }
    
    
    @Test
    public void testNbLignesColonnesPlusPetites() {
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        Coordonnees coord;
        //Selection d'un Blanc de h=4 qui ne prend pas le N1 car il est bloqué par un B2 ni le N3 car il est adjacent
        coord = Coordonnees.depuisCars('l', 'F');
        assertEquals(0, 
                FusionDestruction.nbLignesColonnesPlusPetites(plateau2, coord, false)); 
        //Selection d'un Noir de h=1, ne dépasse donc personne
        coord = Coordonnees.depuisCars('b', 'A');
        assertEquals(0, 
               FusionDestruction.nbLignesColonnesPlusPetites(plateau2, coord, true));
        //Selection d'un Noir de h=2 mais qui n'a aucun blanc à dépasser en hauteur
        coord = Coordonnees.depuisCars('f', 'P');
        assertEquals(0, 
                FusionDestruction.nbLignesColonnesPlusPetites(plateau2, coord, true));    
        //Selection d'un Noir de h=2 prend que un B1 en Sud car les autres sont protégés
         coord = Coordonnees.depuisCars('n', 'G');
        assertEquals(1, 
                FusionDestruction.nbLignesColonnesPlusPetites(plateau2, coord, true));    
        //Selection d'un Blanc de h=3 qui prend que un N2 et un N1
         coord = Coordonnees.depuisCars('f', 'M');
        assertEquals(3, 
                FusionDestruction.nbLignesColonnesPlusPetites(plateau2, coord, false));
        //Selection d'un Noir de h=5 qui prend que le B1 et pas celui d'après car il est bloqué par ce dernier
        coord = Coordonnees.depuisCars('c', 'K');
        assertEquals(1, 
                FusionDestruction.nbLignesColonnesPlusPetites(plateau2, coord, true));
        
        
        //Activation d'une fusion d'un noir h=1 avec un N5
        coord = Coordonnees.depuisCars('h', 'K');
        assertEquals(5, 
                FusionDestruction.nbLignesColonnesPlusPetites(plateau2, coord, false));
        //Activation d'une fusion d'un B1 avec un B1
        coord = Coordonnees.depuisCars('c', 'E');
        assertEquals(1, 
                FusionDestruction.nbLignesColonnesPlusPetites(plateau2, coord, true));
        //Activation d'une fusion entre un N3 et un N1
        coord = Coordonnees.depuisCars('n', 'G');
        assertEquals(1,
                FusionDestruction.nbLignesColonnesPlusPetites(plateau2, coord, false));
        //Activation d'une fusion d'un N2 avec personne
        coord = Coordonnees.depuisCars('f', 'P');
        assertEquals(0,
                FusionDestruction.nbLignesColonnesPlusPetites(plateau2, coord, false));
        
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        //Activation d'une destruction qui detruit personne
        coord = Coordonnees.depuisCars('a', 'P');
        assertEquals(0,
                FusionDestruction.nbLignesColonnesPlusPetites(plateau, coord, true));
         //Activation d'une destruction d'un N2 sur un B2 mais le niveau du N2 est 4 tandis que le B2 est 3 donc détruit 2 blancs
        coord = Coordonnees.depuisCars('c', 'H');
        assertEquals(2,
                FusionDestruction.nbLignesColonnesPlusPetites(plateau, coord, true));
        //Fusion d'un blanc où il y a une case d'eau
        coord = Coordonnees.depuisCars('d', 'L');
        assertEquals(1,
                FusionDestruction.nbLignesColonnesPlusPetites(plateau, coord, true));
    }
    
    
     @Test
    public void testNbAdjPlusPetit() {
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        Coordonnees coord;
        //Destruction avec un B2 plus grand que 3 N1 ajdacents
        coord = Coordonnees.depuisCars('m', 'F');
        assertEquals(3, 
                FusionDestruction.nbAdjPlusPetit(plateau2, coord, false));
        //Destruction avec un N3 plus grand que un B1 et un B2 adjacents
        coord = Coordonnees.depuisCars('l', 'E');
        assertEquals(3, 
                FusionDestruction.nbAdjPlusPetit(plateau2, coord, true)); 
        //Fusion d'un B2 adjacent à un B4 et deux B1
        coord = Coordonnees.depuisCars('m', 'F');
        assertEquals(8, 
                FusionDestruction.nbAdjPlusPetit(plateau2, coord, true));
        //Fusion d'un N1 adjacent à un N3 et deux N1
        coord = Coordonnees.depuisCars('n', 'F');
        assertEquals(6, 
                FusionDestruction.nbAdjPlusPetit(plateau2, coord, false));
        //Fusion d'un pion N3 adjacent avec trois N1
        coord = Coordonnees.depuisCars('n', 'G');
        assertEquals(6,
                FusionDestruction.nbAdjPlusPetit(plateau2, coord, false)); 
        
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        //Destruction avec un N21 (niveau 3) plus grand que un B2 et un B1 ajdacents
        coord = Coordonnees.depuisCars('k', 'F');
        assertEquals(3, 
                FusionDestruction.nbAdjPlusPetit(plateau, coord, true));
        //Destruction avec un N13 (niveau 4) plus grand que un B12 (niveau 3) ajdacent
        coord = Coordonnees.depuisCars('f', 'M');
        assertEquals(1, 
                FusionDestruction.nbAdjPlusPetit(plateau, coord, true));
        //Fusion avec un N13 (niveau 4) avec un N2 ajdacents
        coord = Coordonnees.depuisCars('b', 'H');
        assertEquals(3, 
                FusionDestruction.nbAdjPlusPetit(plateau, coord, false));
    }
    
    
    @Test
    public void testactivationPossible() {
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        Coordonnees coord;
        //Activation d'un pion sur une case vide: impossible
        coord = Coordonnees.depuisCars('a', 'A');
        assertFalse(FusionDestruction.activationPossible(plateau2, coord, true));
        //Activation d'un pion sur une case qui a déjà un pion de la même couleur
        coord = Coordonnees.depuisCars('d', 'I');
        assertTrue(FusionDestruction.activationPossible(plateau2, coord, false));
        //Activation d'un pion sur une case qui à déjà un pion d'une couleur différente: impossible
        coord = Coordonnees.depuisCars('b', 'A');
        assertFalse(FusionDestruction.activationPossible(plateau2, coord, false));
        
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        //Activation d'un pion sur une case vide avec une altitude: impossible
        coord = Coordonnees.depuisCars('a', 'I');
        assertFalse(FusionDestruction.activationPossible(plateau, coord, true));
        //Activation d'un pion qui à déjà un pion de la même couleur de niveau 4
        coord = Coordonnees.depuisCars('a', 'A');
        assertFalse(FusionDestruction.activationPossible(plateau2, coord, true));
    }
    
    
    final String PLATEAU_NIVEAU1
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |  1|  2|  2|  2|  2|  2|  3|  3|  3|  4|  4|  4|  3|  2|N22|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|  1|  2|  2|  2|  2|  2|  2|N13|  3|  3|  3|  4|  3|  2|  2|  2|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|  1|  2|  2|  2|  2|  2|  2|N22|  2|  3|  3|  3|  2|  3|  2|  2|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|  2|  3|  2|  2|  3|  2|  3|  2|  2|  2|N22|B12|  3|  3|  3|  3|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|  3|  3|  3|  3|  3|  3|  2|  2|  2|  2|  3|  3|  4|  4|  4|  4|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+E--+---+---+---+---+\n"
            + "f|  4|  3|  3|  2|  2|  2|  2|  2|  2|  2|  2|  3|N13|  4|  4|  4|\n"
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
            + "a|   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|N1 |   |N4 |   |   |   |   |B1 |   |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |B1 |   |B1 |   |   |   |   |   |N5 |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |B4 |   |   |   |   |   |B1 |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|B1 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |B3 |   |   |N2 |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |B1 |   |   |   |   |   |B1 |N1 |   |   |   |   |   |   |\n"
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
            + "n|   |   |   |   |N1 |N1 |N3 |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |N1 |   |   |   |   |   |N1 |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";
    
}















