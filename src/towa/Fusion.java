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
public class Fusion {
    
    /**
     * Indique s'il est possible de faire l'activation de la destruction
     * sur une case pour ce plateau, ce joueur, dans ce niveau.
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param estNoir vrai ssi il s'agit du tour du joueur noir
     * @return vrai ssi la destruction autour du pion sur cette case est 
     * autorisée dans ce niveau
     */
    static boolean activationPossible(Case[][] plateau, Coordonnees coord, boolean estNoir) {
        return (plateau[coord.ligne][coord.colonne].estNoire==estNoir && plateau[coord.ligne][coord.colonne].tourPresente);
    }
    
    
    /**
     * Indique combien de pions sont a retirer après la fusion.
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param estNoir vrai ssi il s'agit du tour du joueur noir
     * @return le nombre de pions à retirer après une fusion
     */
    static int nbPionsARetirerFusion(Case[][] plateau, Coordonnees coord, boolean estNoir) {
        int nbPionsAEnlever;
        int niveauMax = 4;
        nbPionsAEnlever = nbAdjPlusPetit(plateau, coord, !estNoir) + nbLignesColonnesPlusPetites(plateau, coord, !estNoir) + plateau[coord.ligne][coord.colonne].altitude;
            if (nbPionsAEnlever > 4) {
                return nbPionsAEnlever - niveauMax;
            } else {
                return 0;
            }
    }
    
    
    /**
     * Nombre de pions adjacents et plus petit d'un pion donné sur le plateau.
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param estNoir vrai si on compte les pions noirs, faux sinon
     * @return le nombre de pions adjacents plus petit et d'une autre couleur
     * que notre pion sur le plateau
     */
    static int nbAdjPlusPetit(Case[][] plateau, Coordonnees coord, boolean estNoir) {
        int nbVoisinsPlusPetit = 0;
        int nbAdjFusion = 0;
        int caseSelectionnee = plateau[coord.ligne][coord.colonne].hauteur + plateau[coord.ligne][coord.colonne].altitude;
        
        for (int i = Math.max(coord.ligne - 1, 0); i < Math.min(coord.ligne + 2, Coordonnees.NB_LIGNES); i++) {
            for (int j = Math.max(coord.colonne - 1, 0); j < Math.min(coord.colonne + 2, Coordonnees.NB_COLONNES); j++) {
                int caseComparee = plateau[i][j].hauteur + plateau[i][j].altitude;
                if (plateau[coord.ligne][coord.colonne].estNoire == estNoir) {
                    if (plateau[i][j].tourPresente && plateau[i][j].estNoire == !estNoir && caseSelectionnee > caseComparee) {
                        nbVoisinsPlusPetit += plateau[i][j].hauteur;
                    }
                }
                if (plateau[coord.ligne][coord.colonne].estNoire == !estNoir) {
                    if (plateau[i][j].tourPresente && plateau[i][j].estNoire == !estNoir) {
                        nbAdjFusion += plateau[i][j].hauteur;
                    }
                }
            }
        } 
        
        if (plateau[coord.ligne][coord.colonne].estNoire == estNoir) {
            return nbVoisinsPlusPetit;
        } else {
            return nbAdjFusion;
        }
    }
    
    
    /**
     * Cherche le 1er pion trouvé dans chaque direction et l'enlève si il s'agit d'un 
     * pion d'une autre couleur et qu'il est plus petit que le pion activé
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param estNoir vrai si on compte les pions noirs, faux sinon
     * @return le nombre de pions à enlever sur la diagonale et l'horizontale
     */
    static int nbLignesColonnesPlusPetites(Case[][] plateau, Coordonnees coord, boolean estNoir) {
        int[] indexLigne = {Direction.trouvePionNord(plateau, coord), -Direction.trouvePionSud(plateau, coord), 0, 0};
        int[] indexColonne = {0, 0, -Direction.trouvePionEst(plateau, coord), Direction.trouvePionOuest(plateau, coord)};
        int nbPionsAEnlever = 0;
        int nbColLigneAFusionner = 0;
        int caseSelectionnee = plateau[coord.ligne][coord.colonne].hauteur + plateau[coord.ligne][coord.colonne].altitude;
        int caseCompareeHauteur;
        int caseCompareeAltitude;
        
        
        for (int i = 0; i < indexLigne.length; i++) {  
            if (indexLigne[i] != -1 && indexLigne[i] != 1 && indexColonne[i] != -1 && indexColonne[i] != 1) {
                caseCompareeHauteur = plateau[coord.ligne - indexLigne[i]][coord.colonne - indexColonne[i]].hauteur;
                caseCompareeAltitude = plateau[coord.ligne - indexLigne[i]][coord.colonne - indexColonne[i]].altitude;
                if (plateau[coord.ligne][coord.colonne].estNoire == estNoir) {
                    if (plateau[coord.ligne - indexLigne[i]][coord.colonne - indexColonne[i]].estNoire != estNoir
                            && caseCompareeAltitude + caseCompareeHauteur < caseSelectionnee) {
                        nbPionsAEnlever += caseCompareeHauteur;  
                    }
                } 
                if (plateau[coord.ligne][coord.colonne].estNoire == !estNoir) {
                    if (plateau[coord.ligne - indexLigne[i]][coord.colonne - indexColonne[i]].estNoire == !estNoir) {
                        nbColLigneAFusionner += caseCompareeHauteur;
                    }
                }
            }
        }
    
        
        if (plateau[coord.ligne][coord.colonne].estNoire == estNoir) {
            return nbPionsAEnlever;
        } else {
            return nbColLigneAFusionner;
        }   
    }   
}





