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
public class PosePion {

    /**
     * Indique s'il est possible de poser un pion sur une case pour ce plateau,
     * ce joueur, dans ce niveau.
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param estNoir vrai ssi il s'agit du tour du joueur noir
     * @return vrai ssi la pose d'un pion sur cette case est autorisée dans ce
     * niveau
     */
    static boolean posePossible(Case[][] plateau, Coordonnees coord, boolean estNoir) {
        Case pion = plateau[coord.ligne][coord.colonne];
        //vrai ssi il y a des cases adjacentes à la case ciblée
        boolean caseAdjacente = caseAdjacente(plateau, coord, estNoir);
        if (pion.nature == 'E') {
            return false;
        } else {
            return ((pion.tourPresente && pion.estNoire == estNoir && pion.hauteur + pion.altitude < 4)
                    || (!pion.tourPresente && pion.altitude < 4 && !caseAdjacente)
                    || (!pion.tourPresente && pion.altitude < 3 && caseAdjacente));
        }
    }

    /**
     * Dit si il y a des pions adjacents à une case donnée.
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param estNoir vrai si on compte les pions noirs, faux sinon
     * @return si il y a un pion adjacent selon une case donnée
     */
    static boolean caseAdjacente(Case[][] plateau, Coordonnees coord, boolean estNoir) {
        //Pour chaque ligne adjacente
        for (int i = Math.max(coord.ligne - 1, 0); i < Math.min(coord.ligne + 2, Coordonnees.NB_LIGNES); i++) {
            //Pour chaque colonne adjacente
            for (int j = Math.max(coord.colonne - 1, 0); j < Math.min(coord.colonne + 2, Coordonnees.NB_COLONNES); j++) {
                if (plateau[i][j].tourPresente && plateau[i][j].estNoire != estNoir) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Vérifie si il y a des pions adjacents d'une couleur différente d'un pion
     * donné sur le plateau.
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param estNoir vrai si on compte les pions noirs, faux sinon
     * @return un si il y un ou plusieurs pions adjacents à la case souhaitée
     */
    static int pionsAdj(Case[][] plateau, Coordonnees coord, boolean estNoir) {
        int pion = 0;

        if (!plateau[coord.ligne][coord.colonne].tourPresente && plateau[coord.ligne][coord.colonne].altitude < 3) {
            if (caseAdjacente(plateau, coord, estNoir)) {
                pion = 1;
            }
        }

        return pion;
    }

    /**
     * Vérifie toutes les lignes et colonnes du tableau pour savoir si elles
     * sont composées d'au moins un pion noir et un pion blanc.
     *
     * @param plateau le plateau
     * @param estNoir vrai si on compte les pions noirs, faux sinon
     * @return vrai ssi toutes les lignes et colonnes sont composées d'au moins
     * un pion noir et un pion blanc
     */
    static boolean plateauCouvert(Case[][] plateau, boolean estNoir) {
        boolean trouveBlancCol = false; boolean trouveBlancLig = false;
        boolean trouveNoirCol = false; boolean trouveNoirLig = false;
        //Pour chaque ligne
        for (int i = 0; i < Coordonnees.NB_LIGNES; i++) {
            //Pour chaque colonne
            for (int j = 0; j < Coordonnees.NB_COLONNES; j++) {
                if (plateau[j][i].tourPresente && plateau[j][i].estNoire == estNoir)  {
                    trouveNoirLig = true;
                } else if (plateau[j][i].tourPresente && plateau[j][i].estNoire != estNoir) { 
                    trouveBlancLig = true;
                }
                if (plateau[i][j].tourPresente && plateau[i][j].estNoire == estNoir) {
                    trouveNoirCol = true;
                } else if (plateau[i][j].tourPresente && plateau[i][j].estNoire != estNoir){
                    trouveBlancCol = true;
                }
            }
            //Si une seule couleur n'a pas été trouvé sur les lignes ou les colonnes retourne faux
            if (!trouveNoirLig || !trouveBlancLig || !trouveNoirCol || !trouveBlancCol) {
                return false;
                //Sinon réinitialise les booleens et i s'incrémente puis refait le test et ainsi de suite
            } else {
                trouveNoirCol = false; trouveNoirLig = false;
                trouveBlancCol = false; trouveBlancLig = false;
            }
        }
        
        //Si après le for la fonction n'a toujours pas retourner faux alors le plateau est couvrant
        return true;
    }
    

    /**
     * Fait l'action pose
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param joueurNoir vrai si on compte les pions noirs, faux sinon
     * @param actions tableau des actions faites
     * @param nbPionsNoirs nombre de pions noirs
     * @param nbPionsBlancs nombre de
     */
    static void actionPose(String actions[], int nbPionsNoirs, int nbPionsBlancs, Case[][] plateau, Coordonnees coord, boolean joueurNoir) {
        if (posePossible(plateau, coord, joueurNoir)) {
            //Vérifie si le plateau est couvert avant la pose du pion
            boolean plateauCouvert = plateauCouvert(plateau, joueurNoir);
            //Si le plateau était déjà couvrant
            if (plateauCouvert) {
                // on ajoute l'action dans les actions possibles en fonction de la couleur du joueur
                if (joueurNoir) {
                    actions[JoueurTowa.nbActions] = JoueurTowa.chaineActionPose(coord, nbPionsNoirs + (1 + PosePion.pionsAdj(plateau, coord, joueurNoir)), nbPionsBlancs);
                    JoueurTowa.nbActions++;
                } else {
                    actions[JoueurTowa.nbActions] = JoueurTowa.chaineActionPose(coord, nbPionsNoirs, nbPionsBlancs + (1 + PosePion.pionsAdj(plateau, coord, joueurNoir)));
                    JoueurTowa.nbActions++;
                }
            }
            else {
                //Vérifie si le plateau est couvert après la pose du pion (ne marche pas)
                plateauCouvert = plateauCouvert(plateau, joueurNoir);
                //Le nombre de pions à rajouter pour remplir la case à un niveau 4
                int nbPionsAjout = 4 - plateau[coord.ligne][coord.colonne].altitude;
                if (plateauCouvert) {
                    // on ajoute l'action dans les actions possibles en fonction de la couleur du joueur
                    if (joueurNoir) {
                        actions[JoueurTowa.nbActions] = JoueurTowa.chaineActionPose(coord, nbPionsNoirs + nbPionsAjout, nbPionsBlancs);
                        JoueurTowa.nbActions++;
                    } else {
                        actions[JoueurTowa.nbActions] = JoueurTowa.chaineActionPose(coord, nbPionsNoirs, nbPionsBlancs + nbPionsAjout);
                        JoueurTowa.nbActions++;
                    }
                } else {
                    // on ajoute l'action dans les actions possibles en fonction de la couleur du joueur
                    if (joueurNoir) {
                        actions[JoueurTowa.nbActions] = JoueurTowa.chaineActionPose(coord, nbPionsNoirs + (1 + PosePion.pionsAdj(plateau, coord, joueurNoir)), nbPionsBlancs);
                        JoueurTowa.nbActions++;
                    } else {
                        actions[JoueurTowa.nbActions] = JoueurTowa.chaineActionPose(coord, nbPionsNoirs, nbPionsBlancs + (1 + PosePion.pionsAdj(plateau, coord, joueurNoir)));
                        JoueurTowa.nbActions++;
                    }
                }
            }
        }
    }
}









