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

public class Kamikaze {
    
    /**
     * Indique combien de pions sont a retirer après les chats kamikazes.
     * @param plateau le plateau
     * @param estNoir vrai ssi il s'agit du tour du joueur noir
     * @param cardinalité la direction souhaitée
     * @param sortie true si on veut sortir les noirs, false si on veut sortir les blancs
     * @return le nombre de pions blancs ou noirs à retirer 
     */
    static int nbPionsARetirerKamikaze(Case[][] plateau, boolean estNoir, char cardinalité, boolean sortie) {
        int index;
        int nbPionsNoirsSortie = 0;
        int nbPionsBlancsSortie = 0;
        
        //Selon la cardinalité donnée en paramètre
        switch (cardinalité) {
            case 'S':
                for (int i = 0; i < Coordonnees.NB_LIGNES; i++) {
                    index = Direction.trouvePionNord(plateau, new Coordonnees(Coordonnees.NB_COLONNES, i));
                    if (index != -1) {
                        if (plateau[Coordonnees.NB_LIGNES - index][i].estNoire == estNoir) {
                            nbPionsNoirsSortie += plateau[Coordonnees.NB_LIGNES - index][i].hauteur; 
                        } else {
                            nbPionsBlancsSortie += plateau[Coordonnees.NB_LIGNES - index][i].hauteur; 
                        }
                    }  
                }
                break;
            case 'N':  
                for (int i = 0; i < Coordonnees.NB_LIGNES; i++) {
                    index = Direction.trouvePionSud(plateau, new Coordonnees(-1,i));
                    if (index != -1) {
                        if (plateau[index - 1][i].estNoire == estNoir) {
                            nbPionsNoirsSortie += plateau[index - 1][i].hauteur; 
                        } else{
                            nbPionsBlancsSortie += plateau[index - 1][i].hauteur; 
                        }
                    }  
                }
                break;
            case 'O':
                for (int i = 0; i < Coordonnees.NB_COLONNES; i++) {
                    index = Direction.trouvePionEst(plateau, new Coordonnees(i,-1));
                    if (index != -1) {
                        if (plateau[i][index - 1].estNoire == estNoir) {
                            nbPionsNoirsSortie += plateau[i][index - 1].hauteur; 
                        } else {
                            nbPionsBlancsSortie += plateau[i][index - 1].hauteur; 
                        }
                    }  
                }
                break;
            case 'E':
                for (int i = 0; i < Coordonnees.NB_COLONNES; i++) {
                    index = Direction.trouvePionOuest(plateau, new Coordonnees(i, Coordonnees.NB_LIGNES));
                    if (index != -1) {
                        if (plateau[i][Coordonnees.NB_COLONNES - index].estNoire == estNoir) {
                            nbPionsNoirsSortie += plateau[i][Coordonnees.NB_COLONNES - index].hauteur; 
                        } else {
                            nbPionsBlancsSortie += plateau[i][Coordonnees.NB_COLONNES - index].hauteur; 
                        }
                    }  
                }
                break;
            default:
                break;
        }
        //retourne le nombre de pions d'une couleur selon la valeur de sortie
        if (sortie) {
            return nbPionsNoirsSortie;
        } else {
            return nbPionsBlancsSortie;
        }
    }
    
    
    /**
     * Fait l'action de kamikaze
     *
     * @param plateau le plateau
     * @param joueurNoir vrai si on compte les pions noirs, faux sinon
     * @param actions tableau des actions faites
     * @param nbPionsNoirs nombre de pions noirs
     * @param nbPionsBlancs nombre de pions blancs
     */
    static void actionKamikaze(String actions[], int nbPionsNoirs, int nbPionsBlancs, Case[][] plateau, boolean joueurNoir) {
        //Tableau de caractères indiquant les quatres cardinalités existantes
        char[] Direction = {'N', 'S', 'E', 'O'};
        //Pour toute les directions possibles       
        for(int i = 0; i < Direction.length; i++) {
            // on ajoute l'action dans les actions possibles selon la direction
            actions[JoueurTowa.nbActions] = JoueurTowa.chaineActionKamikaze(
                    nbPionsNoirs - nbPionsARetirerKamikaze(plateau, true, Direction[i], true)
                    , nbPionsBlancs - nbPionsARetirerKamikaze(plateau, true, Direction[i], false)
                    , Direction[i]);
            JoueurTowa.nbActions++;
        }
    }
}









