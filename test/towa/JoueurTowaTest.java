package towa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests sur la classe JoueurTowa.
 */
public class JoueurTowaTest {

    /**
     * Test de la fonction actionsPossibles. Commentez les appels aux tests des
     * niveaux inférieurs, n'activez que le test du niveau à valider.
     */
    @Test
    public void testActionsPossibles() {
        //testActionsPossibles_niveau1();
        //testActionsPossibles_niveau2();
        //testActionsPossibles_niveau3();
        //testActionsPossibles_niveau4();
        //testActionsPossibles_niveau5();
        //testActionsPossibles_niveau6();
        //testActionsPossibles_niveau7();
        //testActionsPossibles_niveau8();
        //testActionsPossibles_niveau9();
        //testActionsPossibles_niveau10();
        testActionsPossibles_niveau11();
        testActionsPossibles_niveau12();
        testActionsPossibles_niveau13();
    }

    /**
     * Test de la fonction actionsPossibles, au niveau 1.
     */
    public void testActionsPossibles_niveau1() {
        JoueurTowa joueur = new JoueurTowa();
        // un plateau sur lequel on veut tester actionsPossibles()
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        // on choisit la couleur du joueur
        boolean estNoir = true;
        // on choisit le niveau
        int niveau = 1;
        // on lance actionsPossibles
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        // on peut afficher toutes les actions possibles calculées :
        Utils.afficherActionsPossibles(actionsPossibles);
        // on peut aussi tester si une action est dans les actions possibles :
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaB,1,0"));
        // on peut aussi tester si une action n'est pas dans les actions 
        // possibles :
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "PaQ,1,0"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "PaA,0,0"));
        // testons les 4 coins :
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaA,1,0"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PpA,1,0"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaP,1,0"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PpP,1,0"));
        // vérifions s'il y a le bon nombre d'actions possibles :
        assertEquals(Coordonnees.NB_LIGNES * Coordonnees.NB_COLONNES,
                actionsPossibles.length);
    }

    /**
     * Test de la fonction actionsPossibles, au niveau 2.
     */
    public void testActionsPossibles_niveau2() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        boolean estNoir = true;
        int niveau = 2;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        // on lance actionsPossibles
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        Coordonnees coord;
        // pose sur case vide : possible
        coord = Coordonnees.depuisCars('a', 'B');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs)));
        // pose sur case de même couleur : possible
        coord = Coordonnees.depuisCars('b', 'A');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs)));
        // pose sur case de couleur opposée : impossible
        coord = Coordonnees.depuisCars('a', 'G');
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs)));
        // pose sur case de même couleur et hauteur > 1 : possible
        coord = Coordonnees.depuisCars('c', 'K');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs)));
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 3.
     */
    public void testActionsPossibles_niveau3() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        boolean estNoir = true;
        int niveau = 3;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        Coordonnees coord;
        //pose sur une case vide: possible
        coord = Coordonnees.depuisCars('k', 'B');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs)));
        // pose sur case de même couleur avec h < 4: possible
        coord = Coordonnees.depuisCars('g', 'J');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs)));
         // pose sur case de couleur opposée : impossible
        coord = Coordonnees.depuisCars('b', 'L');
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs)));
        //pose sur une case de même couleur avec h >= 4: impossible
        coord = Coordonnees.depuisCars('c', 'K');
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs)));
    }   
        
    /**
     * Test de la fonction actionsPossibles, au niveau 4.
     */
    public void testActionsPossibles_niveau4() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        boolean estNoir = true;
        int niveau = 4;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        Coordonnees coord;
        //Activation d'un pion sur une case vide
        coord = Coordonnees.depuisCars('a', 'A');
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionDestruction(coord, nbPionsNoir, nbPionsBlancs - FusionDestruction.nbAdjPlusPetit(plateau, coord, estNoir))));
        //Activation d'un pion sur une case qui a déjà un pion de la même couleur
        coord = Coordonnees.depuisCars('i', 'M');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionDestruction(coord, nbPionsNoir, nbPionsBlancs - FusionDestruction.nbAdjPlusPetit(plateau, coord, estNoir))));
        //Activation d'un pion sur une case qui à déjà un pion d'une couleur différente
        coord = Coordonnees.depuisCars('j', 'L');
        assertFalse((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionDestruction(coord, nbPionsNoir, nbPionsBlancs - FusionDestruction.nbAdjPlusPetit(plateau, coord, estNoir)))));
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 5.
     */
    public void testActionsPossibles_niveau5() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        boolean estNoir = true;
        int niveau = 5;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        Coordonnees coord;
        //Pose de deux pions si case vide et des pions adjacents d'une autre couleur
        coord = Coordonnees.depuisCars('b', 'G');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 2, nbPionsBlancs)));
        //Pose de deux pions si case rempli
        coord = Coordonnees.depuisCars('i', 'M');
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 2, nbPionsBlancs)));
        //Pose de deux pions si case vide et pas d'ajacents
        coord = Coordonnees.depuisCars('f', 'G');
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 2, nbPionsBlancs)));
        //Pose de deux pions si case vide et adjacents de la même couleur
         coord = Coordonnees.depuisCars('j', 'B');
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 2, nbPionsBlancs)));
        //Pose de deux pions si case vide et adjacents des deux couleurs
         coord = Coordonnees.depuisCars('b', 'K');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 2, nbPionsBlancs)));
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 6.
     */
    public void testActionsPossibles_niveau6() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        boolean estNoir = true;
        int niveau = 6;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        int nbPionsAEnlever;
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        Coordonnees coord;
        //Activation d'un pion sur une case vide
        coord = Coordonnees.depuisCars('a', 'A');
        nbPionsAEnlever = FusionDestruction.nbAdjPlusPetit(plateau, coord, estNoir) + FusionDestruction.nbLignesColonnesPlusPetites(plateau, coord, estNoir);
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionDestruction(coord, nbPionsNoir, nbPionsBlancs - nbPionsAEnlever)));
        //Activation d'un pion sur une case qui a déjà un pion de la même couleur
        coord = Coordonnees.depuisCars('i', 'M');
        nbPionsAEnlever = FusionDestruction.nbAdjPlusPetit(plateau, coord, estNoir) + FusionDestruction.nbLignesColonnesPlusPetites(plateau, coord, estNoir);
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionDestruction(coord, nbPionsNoir, nbPionsBlancs - nbPionsAEnlever)));
        //Activation d'un pion sur une case qui à déjà un pion d'une couleur différente
        coord = Coordonnees.depuisCars('j', 'L');
        nbPionsAEnlever = FusionDestruction.nbAdjPlusPetit(plateau, coord, estNoir) + FusionDestruction.nbLignesColonnesPlusPetites(plateau, coord, estNoir);
        assertFalse((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionDestruction(coord, nbPionsNoir, nbPionsBlancs - nbPionsAEnlever))));
    }

    /**
     * Test de la fonction actionsPossibles, au niveau 7.
     */
    public void testActionsPossibles_niveau7() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        boolean estNoir = true;
        int niveau = 7;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        Coordonnees coord;
        //Activation d'un pion Noir pour tester l'adjacence avec le joueurNoir
        coord = Coordonnees.depuisCars('l', 'E');
        assertTrue((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionDestruction(coord, nbPionsNoir, nbPionsBlancs - 3))));
        //Activation d'un pion sur un Blanc pour tester l'adjacence + les colonnes et les lignes avec le joueurNoir
        coord = Coordonnees.depuisCars('n', 'G');
        assertTrue((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionDestruction(coord, nbPionsNoir, nbPionsBlancs - 3))));
        //Activation d'une case vide: interdit
        coord = Coordonnees.depuisCars('d', 'E');
        assertFalse((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionDestruction(coord, nbPionsNoir, nbPionsBlancs - 0)))); 
        //Activation d'un pion sur une case qui à déjà un pion d'une couleur différente: interdit
        coord = Coordonnees.depuisCars('j', 'L');
        assertFalse((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionDestruction(coord, nbPionsNoir, nbPionsBlancs - 0))));
        //Activation d'un pion N1 tout seul
        coord = Coordonnees.depuisCars('b', 'A');
        assertTrue((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionDestruction(coord, nbPionsNoir, nbPionsBlancs - 0))));
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 8.
     */
    public void testActionsPossibles_niveau8() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        boolean estNoir = true;
        int niveau = 8;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        Coordonnees coord;
        //Activation d'un pion N3 qui doit fusionner seulement avec un N1 et donc ne pas perdre de pions
        coord = Coordonnees.depuisCars('l', 'E');
        assertTrue((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionFusion(coord, nbPionsNoir - 0, nbPionsBlancs))));
        //Activation d'un pion N1 qui doit fusionner avec un N1 et un N2 et donc pas perdre de pions
        coord = Coordonnees.depuisCars('g', 'J');
        assertTrue((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionFusion(coord, nbPionsNoir - 0, nbPionsBlancs))));
        //Activation d'un pion N1 qui doit fusionner avec un N5 et un N1 et donc perdre 3 pions
        coord = Coordonnees.depuisCars('h', 'K');
        assertTrue((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionFusion(coord, nbPionsNoir - 3, nbPionsBlancs))));
        //Activation d'un pion N3 qui doit fusionner avec quatre N1 et donc devenir un N4 mais perdre 3 pions
        coord = Coordonnees.depuisCars('n', 'G');
        assertTrue((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionFusion(coord, nbPionsNoir - 3, nbPionsBlancs))));
        //Fusion d'une case vide: interdit
        coord = Coordonnees.depuisCars('d', 'E');
        assertFalse((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionFusion(coord, nbPionsNoir - 0, nbPionsBlancs)))); 
        //Activation d'un pion sur une case qui à déjà un pion d'une couleur différente: interdit
        coord = Coordonnees.depuisCars('j', 'L');
        assertFalse((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionFusion(coord, nbPionsNoir - 0, nbPionsBlancs))));
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 9.
     */
    public void testActionsPossibles_niveau9() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        boolean estNoir = true;
        int niveau = 9;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        char[] Direction = {'N', 'S', 'E', 'O'};
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        // Test Chaton kamikaze sur le plateau 2 avec le Sud
        assertTrue((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionKamikaze(nbPionsNoir - 10, nbPionsBlancs - 5, Direction[1]))));
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 10.
     */
    public void testActionsPossibles_niveau10() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        boolean estNoir = true;
        int niveau = 10;
        Coordonnees coord;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        // Test pose d'un pion sur case vide de niveau 3
        coord = Coordonnees.depuisCars('d', 'E');
        assertTrue((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs))));
         // Test pose d'un pion sur case vide de niveau 4: impossible
        coord = Coordonnees.depuisCars('b', 'L');
        assertFalse((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs))));
         // Test pose d'un pion sur case de même couleur de hauteur 2 et altitude 2: impossible
        coord = Coordonnees.depuisCars('d', 'K');
        assertFalse((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs))));
         // Test pose d'un pion sur case vide de niveau 3 adjacente à une case ennemie: impossible 
        coord = Coordonnees.depuisCars('d', 'M');
        assertFalse((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 2, nbPionsBlancs))));
         // Test de destruction de deux pions blancs de niveau 1 et 2 par un pion noir de niveau 3
        coord = Coordonnees.depuisCars('k', 'F');
        assertTrue((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionDestruction(coord, nbPionsNoir, nbPionsBlancs - 3))));   
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 11.
     */
    public void testActionsPossibles_niveau11() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        boolean estNoir = true;
        int niveau = 11;
        Coordonnees coord;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        //Magie déplacement d'un N2 sur une case vide
        coord = Coordonnees.depuisCars('k', 'F');
        assertTrue((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionMagie(coord, nbPionsNoir, nbPionsBlancs))));
        //Magie déplacement d'un N2 sur une case vide de niveau 3: impossible
        coord = Coordonnees.depuisCars('a', 'P');
        assertFalse((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionMagie(coord, nbPionsNoir, nbPionsBlancs))));
        
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        int nbPionsNoir2 = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs2 = JoueurTowa.nbPions(plateau, false);
        String[] actionsPossibles2 = joueur.actionsPossibles(plateau2, estNoir, niveau);
        //Magie déplacement d'un N1 sur une case déjà prise: impossible
        coord = Coordonnees.depuisCars('j', 'H');
        assertFalse((Utils.actionsPossiblesContient(actionsPossibles2,
                JoueurTowa.chaineActionMagie(coord, nbPionsNoir2, nbPionsBlancs2))));
        
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 12.
     */
    public void testActionsPossibles_niveau12() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        boolean estNoir = true;
        int niveau = 12;
        Coordonnees coord;
        char[] Direction = {'N', 'S', 'E', 'O'};
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        //Magie déplacement d'un B1 sur une case eau: impossible
        coord = Coordonnees.depuisCars('a', 'A');
        assertFalse((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionMagie(coord, nbPionsNoir, nbPionsBlancs))));
        //Pose sur une case d'eau: impossible
        coord = Coordonnees.depuisCars('a', 'G');
        assertFalse((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir, nbPionsBlancs))));
        //Magie déplacement d'un B1 sur une case eau: impossible
        coord = Coordonnees.depuisCars('a', 'A');
        assertFalse((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionMagie(coord, nbPionsNoir, nbPionsBlancs))));
        //Chaton Kamikaze qui passe sur une case d'eau:possible
        assertTrue((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionKamikaze(nbPionsNoir - 4, nbPionsBlancs - 6, Direction[2]))));
        //Fusion marche toujours même avec une case d'eau
        coord = Coordonnees.depuisCars('h', 'J');
        assertTrue((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionFusion(coord, nbPionsNoir, nbPionsBlancs))));
        //Destruction marche toujours même avec une case d'eau
        coord = Coordonnees.depuisCars('a', 'P');
        assertTrue((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionDestruction(coord, nbPionsNoir, nbPionsBlancs - 1))));    
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 13.
     */
    public void testActionsPossibles_niveau13() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        boolean estNoir = true;
        int niveau = 13;
        Coordonnees coord;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        //La pose du pion noir en case e,D rend le plateau couvrant et la case e,D à un niveau de 1 donc on pose 3 pions
        coord = Coordonnees.depuisCars('e', 'D');
        assertTrue((Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 3, nbPionsBlancs))));
    }
    
    @Test
    public void testActionsPossible() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        JoueurTowa joueur = new JoueurTowa();
        boolean estNoir = true;
        int niveau = 11;
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        for (String s : actionsPossibles) {
            System.out.println(s);
        }
    }
    
    @Test
    public void testNbPions() {
        // plateau1 : 9 noirs, 9 blancs et des altitudes
        Case[][] plateau1 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        assertEquals(9, JoueurTowa.nbPions(plateau1, true));
        assertEquals(9, JoueurTowa.nbPions(plateau1, false));
        // plateau2 : 38 noirs, 41 blancs
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        assertEquals(38, JoueurTowa.nbPions(plateau2, true));
        assertEquals(41, JoueurTowa.nbPions(plateau2, false));           
    }
    
    @Test
    public void testChaineActionPose() {
        assertEquals("PfK,3,8", 
                JoueurTowa.chaineActionPose(Coordonnees.depuisCars('f', 'K'), 3, 8));
        assertEquals("PaA,0,0", 
                JoueurTowa.chaineActionPose(Coordonnees.depuisCars('a', 'A'), 0, 0));
        assertEquals("PpP,10,10", 
                JoueurTowa.chaineActionPose(Coordonnees.depuisCars('p', 'P'), 10, 10));
    }
    
    @Test
    public void testChaineActionDestruction() {
        assertEquals("AfK,3,8", 
                JoueurTowa.chaineActionDestruction(Coordonnees.depuisCars('f', 'K'), 3, 8));
        assertEquals("AaA,0,0", 
                JoueurTowa.chaineActionDestruction(Coordonnees.depuisCars('a', 'A'), 0, 0));
        assertEquals("ApP,10,10", 
                JoueurTowa.chaineActionDestruction(Coordonnees.depuisCars('p', 'P'), 10, 10));
    }
    
    @Test
    public void testChaineActionFusion() {
        assertEquals("FfK,3,8", 
                JoueurTowa.chaineActionFusion(Coordonnees.depuisCars('f', 'K'), 3, 8));
        assertEquals("FaA,0,0", 
                JoueurTowa.chaineActionFusion(Coordonnees.depuisCars('a', 'A'), 0, 0));
        assertEquals("FpP,10,10", 
                JoueurTowa.chaineActionFusion(Coordonnees.depuisCars('p', 'P'), 10, 10));
    }
    
    @Test
    public void testChaineActionKamikaze() {
        assertEquals("CO,3,8", 
                JoueurTowa.chaineActionKamikaze(3, 8, 'O'));
        assertEquals("CE,0,0", 
                JoueurTowa.chaineActionKamikaze(0, 0, 'E'));
        assertEquals("CN,10,10", 
                JoueurTowa.chaineActionKamikaze(10, 10, 'N'));
        assertEquals("CS,5,15", 
                JoueurTowa.chaineActionKamikaze(5, 15, 'S'));
    }
    
    @Test
    public void testChaineActionMagie() {
        assertEquals("MfK,3,8", 
                JoueurTowa.chaineActionMagie(Coordonnees.depuisCars('f', 'K'), 3, 8));
        assertEquals("MaA,0,0", 
                JoueurTowa.chaineActionMagie(Coordonnees.depuisCars('a', 'A'), 0, 0));
        assertEquals("MpP,10,10", 
                JoueurTowa.chaineActionMagie(Coordonnees.depuisCars('p', 'P'), 10, 10));
    }
    
    /**
     * Un plateau de base, sous forme de chaîne. Pour construire une telle
     * chaîne depuis votre sortie.log, déclarez simplement : final String
     * MON_PLATEAU = ""; puis copiez le plateau depuis votre sortie.log, et
     * collez-le entre les guillemets. Puis Alt+Shift+f pour mettre en forme.
     */
    final String PLATEAU_NIVEAU1
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+E--+---+---+---+---+---+---+---+---+---+\n"
            + "a|B1 |  1|  2|  2|  2|  2|  2|  3|  3|  3|  4|  4|  4|  3|  2|N22|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|  1|  2|  2|  2|  2|  2|  2|  3|  3|  3|  3|  4|  3|  2|  2|  2|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|  1|  2|  2|  2|  2|  2|  2|  2|  2|  3|  3|  3|  2|  3|  2|  2|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+E--+---+---+\n"
            + "d|  2|  3|  2|  2|  3|  2|  3|  2|  2|  2|N22|B12|  3|  3|  3|  3|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|  3|  3|  3|  3|  3|  3|  2|  2|  2|  2|  3|  3|  4|  4|  4|  4|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|  4|  3|  3|  2|  2|  2|  2|  2|  2|  2|  2|  3|  3|  4|  4|  4|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|  4|  4|  3|  2|  2|  1|   |B21|  1|  1|  1|B12|  3|  4|  4|  4|\n"
            + " +---+---+---+---+---+---+---+E--+---+---+---+---+---+---+---+---+\n"
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
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+E--+\n"
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
            + "d|   |   |   |   |B1 |   |   |   |B1 |   |   |N2 |B4 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|B1 |   |   |  1|   |   |   |   |   |   |   |   |   |B3 |   |   |\n"
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
