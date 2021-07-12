package towa;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Joueur implémentant les actions possibles à partir d'un plateau, pour un
 * niveau donné.
 */
public class JoueurTowa implements IJoueurTowa {

    static public int nbActions;
    
    /**
     * Cette méthode renvoie, pour un plateau donné et un joueur donné, toutes
     * les actions possibles pour ce joueur.
     *
     * @param plateau le plateau considéré
     * @param joueurNoir vrai si le joueur noir joue, faux si c'est le blanc
     * @param niveau le niveau de la partie à jouer
     * @return l'ensemble des actions possibles
     */
    @Override
    public String[] actionsPossibles(Case[][] plateau, boolean joueurNoir, int niveau) {
        // afficher l'heure de lancement
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        System.out.println("actionsPossibles : lancement le " + format.format(new Date()));
        // on compte le nombre de pions sur le plateau avant action
        final int nbPionsNoirs = nbPions(plateau, true);
        final int nbPionsBlancs = nbPions(plateau, false);
        // calculer les actions possibles
        String actions[] = new String[1028];
        nbActions = 0;
        // pour chaque ligne
        for (int lig = 0; lig < Coordonnees.NB_LIGNES; lig++) {
            // pour chaque colonne
            for (int col = 0; col < Coordonnees.NB_COLONNES; col++) {
                Coordonnees coord = new Coordonnees(lig, col);
                PosePion.actionPose(actions, nbPionsNoirs, nbPionsBlancs, plateau, coord, joueurNoir);
                FusionDestruction.actionDestruction(actions, nbPionsNoirs, nbPionsBlancs, plateau, coord, joueurNoir);
                FusionDestruction.actionFusion(actions, nbPionsNoirs, nbPionsBlancs, plateau, coord, joueurNoir);
                Magie.actionMagie(actions, nbPionsNoirs, nbPionsBlancs, plateau, coord, joueurNoir);   
            }
        }
        Kamikaze.actionKamikaze(actions, nbPionsNoirs, nbPionsBlancs, plateau, joueurNoir);
        
        System.out.println("actionsPossibles : fin");
        return Utils.nettoyerTableau(actions);
    }
     

    /**
     * Nombre de pions d'une couleur donnée sur le plateau.
     *
     * @param plateau le plateau
     * @param estNoir vrai si on compte les pions noirs, faux sinon
     * @return le nombre de pions de cette couleur sur le plateau
     */
    static int nbPions(Case[][] plateau, boolean estNoir) {
        int nbPions = 0;
        // pour chaque ligne
        for (int lig = 0; lig < Coordonnees.NB_LIGNES; lig++) {
            // pour chaque colonne
            for (int col = 0; col < Coordonnees.NB_COLONNES; col++) {
                if (plateau[lig][col].tourPresente && plateau[lig][col].estNoire == estNoir) {
                    nbPions += plateau[lig][col].hauteur;
                } 
            }
        }
        return nbPions;
    }
    

    /**
     * Chaîne de caractères correspondant à une action-mesure de pose.
     *
     * @param coord coordonnées de la case où poser le pion
     * @param nbPionsNoirs nombre de pions noirs si cette action était jouée
     * @param nbPionsBlancs nombre de pions blancs si cette action était jouée
     * @return la chaîne codant cette action-mesure
     */
    static String chaineActionPose(Coordonnees coord,
            int nbPionsNoirs, int nbPionsBlancs) {
        return "P" + coord.carLigne() + coord.carColonne() + ","
                + nbPionsNoirs + "," + nbPionsBlancs;
    }
    
    
    /**
     * Chaîne de caractères correspondant à une action-mesure de destruction.
     *
     * @param coord coordonnées de la case où poser le pion
     * @param nbPionsNoirs nombre de pions noirs si cette action était jouée
     * @param nbPionsBlancs nombre de pions blancs si cette action était jouée
     * @return la chaîne codant cette action-mesure
     */
    static String chaineActionDestruction(Coordonnees coord,
            int nbPionsNoirs, int nbPionsBlancs) {
        return "A" + coord.carLigne() + coord.carColonne() + ","
                + nbPionsNoirs + "," + nbPionsBlancs;
    }
    
    
    /**
     * Chaîne de caractères correspondant à une action-mesure de destruction.
     *
     * @param coord coordonnées de la case où poser le pion
     * @param nbPionsNoirs nombre de pions noirs si cette action était jouée
     * @param nbPionsBlancs nombre de pions blancs si cette action était jouée
     * @return la chaîne codant cette action-mesure
     */
    static String chaineActionFusion(Coordonnees coord,
            int nbPionsNoirs, int nbPionsBlancs) {
        return "F" + coord.carLigne() + coord.carColonne() + ","
                + nbPionsNoirs + "," + nbPionsBlancs;
    }
    
    
    /**
     * Chaîne de caractères correspondant à une action-mesure de chaton-kamikaze.
     *
     * @param nbPionsNoirs nombre de pions noirs si cette action était jouée
     * @param nbPionsBlancs nombre de pions blancs si cette action était jouée
     * @param Direction direction souhaité pour lancer le chaton kamikaze
     * @return la chaîne codant cette action-mesure
     */
    static String chaineActionKamikaze(int nbPionsNoirs, int nbPionsBlancs, char Direction) {
        return "C" + Direction + "," + nbPionsNoirs + "," + nbPionsBlancs;
    }
    
    
    /**
     * Chaîne de caractères correspondant à une action-mesure de magie.
     *
     * @param coord coordonnées de la case où poser le pion
     * @param nbPionsNoirs nombre de pions noirs si cette action était jouée
     * @param nbPionsBlancs nombre de pions blancs si cette action était jouée
     * @return la chaîne codant cette action-mesure
     */
    static String chaineActionMagie(Coordonnees coord,
            int nbPionsNoirs, int nbPionsBlancs) {
        return "M" + coord.carLigne() + coord.carColonne() + ","
                + nbPionsNoirs + "," + nbPionsBlancs;
    }   
}
