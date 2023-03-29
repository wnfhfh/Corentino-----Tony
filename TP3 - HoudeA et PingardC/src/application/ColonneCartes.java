package application;

import cartes.Carte;
import cartes.Pioche;
import structures.pile.Pile;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Colonne de cartes du jeu AcesUpSolitaire.
 * Cette classe fait la gestion d'une colonne de carte dans le jeu.
 * Les cartes sont ajoutées ou retirées au-dessus de la colonne.
 */
public class ColonneCartes implements Serializable {
    /**
     * Structure de données qui conserve les cartes dans la colonne
     */
    private Pile pileDeCartes;
    /**
     * La pioche dans laquelle on pige dans la partie.
     * Normalement, c'est la même pour toutes les colonnes
     */
    Pioche pioche;

    /**
     * Si cet attribut est vrai. C'est qu'on indique que cette colonne a changé.
     * Il permet une gestion de l'affichage de la colonne de cartes
     */
    boolean colonneADessiner;


    /**
     * Initialise la colonne de cartes
     * @param pioche la référence à la pioche utilisée pour le
     */
    public ColonneCartes(Pioche pioche) {
        //todo public ColonneCartes(Pioche pioche)
    }

    /**
     * Ajoute dans la colonne une carte pigée dans la pioche si c'elle-ci n'est pas vide.
     * @return <b>true</b> si colonne est à redessiner. <b>false</b> autrement
     */
    public boolean pigerEtAjouterCarte() {
        //todo public boolean pigerEtAjouterCarte()
        return true;
    }
    /**
     * Ajoute une carte au-dessus de la colonne.
     * @param carte La carte à ajouter sur la colonne
     */
    public void ajouterCarteDessus(Carte carte) {
        //todo public void ajouterCarteDessus(Carte carte)
    }
    /**
     * Retourne true si la colonne est vide
     * @return true si la colonne est vide
     */
    public boolean estVide() {
        //todo public boolean estVide()
        return true;
    }

    /**
     * Retourne true si la colonne est à dessiner parce qu'il y a eu un changement
     * dans la colonne.
     * @return true si la colonne est à dessiner
     */
    public boolean isColonneADessiner() {
        return colonneADessiner;
    }

    /**
     * Permet d'indiquer si la colonne est à dessiner ou non.
     * La colonne est à dessiner si elle est mise à jour.
     * La colonne n'est plus à dessiner si elle vient de l'être.
     * @param idxColonneADessiner true pour indiquer que la colonne doit être redessinée.
     *                            false pour indiquer que la colonne n'a pas à être dessinée.
     */
    public void setColonneADessiner(boolean idxColonneADessiner) {
        this.colonneADessiner = idxColonneADessiner;
    }



    /**
     * Retourne la valeur de la carte sur le dessus sans toutefois la retirer.
     * @return la carte du dessus sans toutefois la retirer.
     */
    public Carte voirCarteDessus() {
        //todo public Carte voirCarteDessus()
        return null;
    }


    /**
     * Retourne la carte sur le dessus.
     * La carte est retirée du dessus de la colonne.
     * @return la carte du dessus sans toutefois la retirer.
     */
    public Carte retirerDessus() {
        //todo public Carte retirerDessus()
        return null;
    }


    /**
     * Retourne le nombre de cartes dans la colonne
     * @return
     */
    public int nbCartesColonne() {
        //todo public int nbCartesColonne()
        return 0;
    }

    /**
     * Retourne une liste qui donne le contenu de la colonne.
     * Si le contenu de la liste est modifié, la colonne n'est pas affectée.
     * Cette liste permet d'afficher la colonne.
     * @return
     */
    public ArrayList<Carte> getListe() {
        //todo public ArrayList<Carte> getListe()

        return null;
    }
}
