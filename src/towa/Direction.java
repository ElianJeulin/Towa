package towa;

/**
 * @author Ejeul
 */
public class Direction {
    
    /**
     * Cette fonction renvoie la position du 1er pion trouvé en allant au nord
     *
     * @param plateau le plateau considéré
     * @param coord coordonnées de la case à considérer
     * @return la position du 1er pion trouvé selon une direction et -1 si il ne trouve pas de pion
     */
    static int trouvePionNord(Case[][] plateau, Coordonnees coord) {
        //On commence au pion supérieur de la ligne à celui choisi
        int ligne = coord.ligne - 1;
        int index = 1;
        
        while (ligne >= 0) {
            if (plateau[ligne][coord.colonne].tourPresente) {
                return index;
            }
            ligne--;
            index++;
        }
        return -1;
    }
    
    /**
     * Cette fonction renvoie la position du 1er pion trouvé en allant au sud
     * @param plateau le plateau considéré
     * @param coord coordonnées de la case à considérer
     * @return la position du 1er pion trouvé selon une direction et -1 si il ne trouve pas de pion
     */
    static int trouvePionSud(Case[][] plateau, Coordonnees coord) {
        //On commence au pion inférieur de la ligne à celui choisi
        int ligne = coord.ligne+1;
        int index = 1;
        
        while (ligne < Coordonnees.NB_LIGNES) {
            if (plateau[ligne][coord.colonne].tourPresente) {
                return index;
            }
            ligne++;
            index++;
        }
        return -1;
    }
    
    /**
     * Cette fonction renvoie la position du 1er pion trouvé en allant à l'est
     * @param plateau le plateau considéré
     * @param coord coordonnées de la case à considérer
     * @return la position du 1er pion trouvé selon une direction et -1 si il ne trouve pas de pion
     */
    static int trouvePionEst(Case[][] plateau, Coordonnees coord) {
        //On commence au pion supérieur de la colonne à celui choisi
        int colonne = coord.colonne+1;
        int index = 1;
        
        while (colonne < Coordonnees.NB_COLONNES) {
            if (plateau[coord.ligne][colonne].tourPresente) {
                return index;
            }
            colonne++;
            index++;
        }
        return -1;
    }
    
    /**
     * Cette fonction renvoie la position du 1er pion trouvé en allant à l'Ouest
     * @param plateau le plateau considéré
     * @param coord coordonnées de la case à considérer
     * @return la position du 1er pion trouvé selon une direction et -1 si il ne trouve pas de pion
     */
    static int trouvePionOuest(Case[][] plateau, Coordonnees coord) {
        //On commence au pion inférieur de la colonne à celui choisi
        int colonne = coord.colonne-1;
        int index = 1;
        
        while (colonne >= 0) {
            if (plateau[coord.ligne][colonne].tourPresente) {
                return index;
            }
            colonne--;
            index++;
        }
        return -1;
    }
    
}


























