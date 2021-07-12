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

public class Magie {
    
    /**
     * Indique la position de la symétrique d'une case donnée.
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @return la position symétrique d'une case donnée
     */
    static Coordonnees caseSymetrique(Case[][] plateau, Coordonnees coord){
        int colSym = Coordonnees.NB_COLONNES - 1 - coord.colonne;
        int ligSym = Coordonnees.NB_LIGNES - 1 - coord.ligne;
        return new Coordonnees(ligSym, colSym);
    }
    
    
    /**
     * Indique si il est possible de faire l'action magie
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param estNoir vrai ssi il s'agit du tour du joueur noir
     * @return vrai ssi l'action magie est possible
     */
    static boolean magiePossible(Case[][] plateau, Coordonnees coord, boolean estNoir) {
        Coordonnees coord2 = caseSymetrique(plateau, coord);
        Case pionChoisi = plateau[coord.ligne][coord.colonne];
        Case pionSym = plateau[coord2.ligne][coord2.colonne];
        return (pionChoisi.tourPresente && !pionSym.tourPresente 
                && pionChoisi.hauteur + pionSym.altitude <= 4
                && pionChoisi.estNoire == estNoir
                && pionSym.nature != 'E');
    }
    
    
    /**
     * Fait l'action de magie
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param joueurNoir vrai si on compte les pions noirs, faux sinon
     * @param actions tableau des actions faites
     * @param nbPionsNoirs nombre de pions noirs
     * @param nbPionsBlancs nombre de pions blancs
     */
    static void actionMagie(String actions[], int nbPionsNoirs, int nbPionsBlancs, Case[][] plateau, Coordonnees coord, boolean joueurNoir) {
        // si l'activation de la magie est possible sur cette case
                if (magiePossible(plateau, coord, joueurNoir)) {
                    // on ajoute l'action dans les actions possibles en fonction de la couleur du joueur
                    if (joueurNoir) {
                        actions[JoueurTowa.nbActions] = JoueurTowa.chaineActionMagie(coord, nbPionsNoirs, nbPionsBlancs);
                        JoueurTowa.nbActions++;
                    } else {
                        actions[JoueurTowa.nbActions] = JoueurTowa.chaineActionMagie(coord, nbPionsNoirs, nbPionsBlancs);
                        JoueurTowa.nbActions++;
                    }
                }
    }
}











