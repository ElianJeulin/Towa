/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towa;

/**
 *
 * @author Ejeul
 */
public class FusionDestruction {

    /**
     * Indique s'il est possible de faire l'activation de la destruction ou de
     * la fusion sur une case pour ce plateau, ce joueur, dans ce niveau.
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param estNoir vrai ssi il s'agit du tour du joueur noir
     * @return vrai ssi la destruction/fusion autour du pion sur cette case est
     * autorisée dans ce niveau
     */
    static boolean activationPossible(Case[][] plateau, Coordonnees coord, boolean estNoir) {
        //vrai ssi la case n'est pas vide et si le pion sélectionné est de la même couleur que le joueur  
        return (plateau[coord.ligne][coord.colonne].estNoire == estNoir && plateau[coord.ligne][coord.colonne].tourPresente);
    }
    

    /**
     * Indique combien de pions sont a retirer après la fusion.
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param estNoir vrai ssi il s'agit du tour du joueur noir
     * @return le nombre de pions à retirer après une fusion
     */
    static int nbPionsARetirerFusion(Case[][] plateau, Coordonnees coord, boolean estNoir) {
        int nbPionsAEnlever;
        int niveauMax = 4;
        //sont entrés en paramètre !estNoir pour que l'algorithme utilise les fonctions de destruction sur sa propre couleur
        nbPionsAEnlever = nbAdjPlusPetit(plateau, coord, !estNoir) + nbLignesColonnesPlusPetites(plateau, coord, !estNoir) + plateau[coord.ligne][coord.colonne].altitude;
        return Math.max(nbPionsAEnlever - niveauMax, 0);
    }
    

    /**
     * Nombre de pions adjacents et plus petit d'une autre couleur d'un pion
     * donné sur le plateau Ou nombre de pions adjacents de la même couleur d'un
     * pion donné sur le plateau.
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param estNoir vrai si on compte les pions noirs, faux sinon
     * @return le nombre de pions adjacents plus petit et d'une autre couleur
     * que notre pion sur le plateau ou le nombre de pions adjacents d'une même
     * couleur
     */
    static int nbAdjPlusPetit(Case[][] plateau, Coordonnees coord, boolean estNoir) {
        int nbVoisinsPlusPetit = 0;
        int nbAdjFusion = 0;
        int caseSelectionnee = plateau[coord.ligne][coord.colonne].hauteur + plateau[coord.ligne][coord.colonne].altitude;

        //Pour les lignes adjacentes au pion sélectionné 
        for (int i = Math.max(coord.ligne - 1, 0); i < Math.min(coord.ligne + 2, Coordonnees.NB_LIGNES); i++) {
            //Pour les colonnes adjacentes au pion sélectionné
            for (int j = Math.max(coord.colonne - 1, 0); j < Math.min(coord.colonne + 2, Coordonnees.NB_COLONNES); j++) {
                int caseComparee = plateau[i][j].hauteur + plateau[i][j].altitude;
                //si la couleur de la case sélectionnée est la même que celui qui joue (destruction)
                if (plateau[coord.ligne][coord.colonne].estNoire == estNoir) {
                    if (plateau[i][j].tourPresente && plateau[i][j].estNoire == !estNoir && caseSelectionnee > caseComparee) {
                        nbVoisinsPlusPetit += plateau[i][j].hauteur;
                    }
                }
                //si la couleur de la case sélectionnée est différente que celui qui joue (fusion)
                if (plateau[coord.ligne][coord.colonne].estNoire != estNoir) {
                    if (plateau[i][j].tourPresente && plateau[i][j].estNoire != estNoir) {
                        nbAdjFusion += plateau[i][j].hauteur;
                    }
                }
            }
        }

        //selon si la couleur de la case est la même que celle du joueur, retourne soit l'action de fusion soit de destruction
        if (plateau[coord.ligne][coord.colonne].estNoire == estNoir) {
            return nbVoisinsPlusPetit;
        } else {
            return nbAdjFusion;
        }
    }
    

    /**
     * Cherche le 1er pion trouvé dans chaque direction et l'enlève si il s'agit
     * d'un pion d'une autre couleur et qu'il est plus petit que le pion activé
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param estNoir vrai si on compte les pions noirs, faux sinon
     * @return le nombre de pions à enlever sur la diagonale et l'horizontale
     */
    static int nbLignesColonnesPlusPetites(Case[][] plateau, Coordonnees coord, boolean estNoir) {
        //Tableaux comportants les index du 1er pion trouvé selon la direction sinon -1
        int[] indexLigne = {Direction.trouvePionNord(plateau, coord), -Direction.trouvePionSud(plateau, coord), 0, 0};
        int[] indexColonne = {0, 0, -Direction.trouvePionEst(plateau, coord), Direction.trouvePionOuest(plateau, coord)};
        int nbPionsAEnlever = 0;
        int nbColLigneAFusionner = 0;
        int caseSelectionnee = plateau[coord.ligne][coord.colonne].hauteur + plateau[coord.ligne][coord.colonne].altitude;
        int caseCompareeHauteur;
        int caseCompareeAltitude;

        //Pour toutes les cases du tableau
        for (int i = 0; i < indexLigne.length; i++) {
            //si l'index n'est pas égal à 1 soit une case adjacente déjà calculé avec la fonction nbAdjPlusPetit 
            //et si l'index n'est pas égal à -1 soit qu'il n'y ait pas de pion selon une direction
            if (indexLigne[i] != -1 && indexLigne[i] != 1 && indexColonne[i] != -1 && indexColonne[i] != 1) {
                caseCompareeHauteur = plateau[coord.ligne - indexLigne[i]][coord.colonne - indexColonne[i]].hauteur;
                caseCompareeAltitude = plateau[coord.ligne - indexLigne[i]][coord.colonne - indexColonne[i]].altitude;
                //si la couleur de la case sélectionnée est la même que celui qui joue (destruction)
                if (plateau[coord.ligne][coord.colonne].estNoire == estNoir) {
                    if (plateau[coord.ligne - indexLigne[i]][coord.colonne - indexColonne[i]].estNoire != estNoir
                            && caseCompareeAltitude + caseCompareeHauteur < caseSelectionnee) {
                        nbPionsAEnlever += caseCompareeHauteur;
                    }
                }
                //si la couleur de la case sélectionnée est différente que celui qui joue (fusion)
                if (plateau[coord.ligne][coord.colonne].estNoire != estNoir) {
                    if (plateau[coord.ligne - indexLigne[i]][coord.colonne - indexColonne[i]].estNoire != estNoir) {
                        nbColLigneAFusionner += caseCompareeHauteur;
                    }
                }
            }
        }

        //selon si la couleur de la case est la même que celle du joueur, retourne soit l'action de fusion soit de destruction
        if (plateau[coord.ligne][coord.colonne].estNoire == estNoir) {
            return nbPionsAEnlever;
        } else {
            return nbColLigneAFusionner;
        }
    }
    

    /**
     * Fait l'action de destruction
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param joueurNoir vrai si on compte les pions noirs, faux sinon
     * @param actions tableau des actions faites
     * @param nbPionsNoirs nombre de pions noirs
     * @param nbPionsBlancs nombre de pions blancs
     */
    static void actionDestruction(String actions[], int nbPionsNoirs, int nbPionsBlancs, Case[][] plateau, Coordonnees coord, boolean joueurNoir) {
        // si l'activation d'un pion est possible sur cette case
        int nbPionsAEnlever;
        if (activationPossible(plateau, coord, joueurNoir)) {
            // calcul le nombre de pions adjacents plus petit plus celui des lignes et colonnes plus petite d'une couleur différente 
            nbPionsAEnlever = nbAdjPlusPetit(plateau, coord, joueurNoir) + nbLignesColonnesPlusPetites(plateau, coord, joueurNoir);
            // on ajoute l'action dans les actions possibles en fonction de la couleur du joueur
            if (joueurNoir) {
                actions[JoueurTowa.nbActions] = JoueurTowa.chaineActionDestruction(coord, nbPionsNoirs, nbPionsBlancs - nbPionsAEnlever);
                JoueurTowa.nbActions++;
            } else {
                actions[JoueurTowa.nbActions] = JoueurTowa.chaineActionDestruction(coord, nbPionsNoirs - nbPionsAEnlever, nbPionsBlancs);
                JoueurTowa.nbActions++;
            }
        }
    }
          

    /**
     * Fait l'action de fusion
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param joueurNoir vrai si on compte les pions noirs, faux sinon
     * @param actions tableau des actions faites
     * @param nbPionsNoirs nombre de pions noirs
     * @param nbPionsBlancs nombre de pions blancs
     */
    static void actionFusion(String actions[], int nbPionsNoirs, int nbPionsBlancs, Case[][] plateau, Coordonnees coord, boolean joueurNoir) {
        int nbPionsAEnlever;
        // si l'activation d'un pion est possible sur cette case
        if (activationPossible(plateau, coord, joueurNoir)) {
            // calcul le nb de pions ajd plus celui des lignes et colonnes de la même couleur que le pion sur la case
            nbPionsAEnlever = nbPionsARetirerFusion(plateau, coord, joueurNoir);
            // on ajoute l'action dans les actions possibles en fonction de la couleur du joueur
            if (joueurNoir) {
                actions[JoueurTowa.nbActions] = JoueurTowa.chaineActionFusion(coord, nbPionsNoirs - nbPionsAEnlever, nbPionsBlancs);
                JoueurTowa.nbActions++;
            } else {
                actions[JoueurTowa.nbActions] = JoueurTowa.chaineActionFusion(coord, nbPionsNoirs, nbPionsBlancs - nbPionsAEnlever);
                JoueurTowa.nbActions++;
            }
        }
    }
}





