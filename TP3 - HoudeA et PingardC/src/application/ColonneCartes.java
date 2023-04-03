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
     *
     * @param pioche la référence à la pioche utilisée pour le
     */
    public ColonneCartes(Pioche pioche) {
        //TODO public ColonneCartes(Pioche pioche)
        this.pioche = pioche;
        pigerEtAjouterCarte();
    }

    /**
     * Ajoute dans la colonne une carte pigée dans la pioche si c'elle-ci n'est pas vide.
     *
     * @return <b>true</b> si colonne est à redessiner. <b>false</b> autrement
     */
    public boolean pigerEtAjouterCarte() {
        if (pioche.size() != 0) {
            pileDeCartes.push(pioche.piger());
        }
        if (colonneADessiner) {
            return true;
        }
        return false;
    }

    /**
     * Ajoute une carte au-dessus de la colonne.
     *
     * @param carte La carte à ajouter sur la colonne
     */
    public void ajouterCarteDessus(Carte carte) {
        pileDeCartes.push(carte);
    }

    /**
     * Retourne true si la colonne est vide
     *
     * @return true si la colonne est vide
     */
    public boolean estVide() {
        if (pileDeCartes.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Retourne true si la colonne est à dessiner parce qu'il y a eu un changement
     * dans la colonne.
     *
     * @return true si la colonne est à dessiner
     */
    public boolean isColonneADessiner() {
        return colonneADessiner;
    }

    /**
     * Permet d'indiquer si la colonne est à dessiner ou non.
     * La colonne est à dessiner si elle est mise à jour.
     * La colonne n'est plus à dessiner si elle vient de l'être.
     *
     * @param idxColonneADessiner true pour indiquer que la colonne doit être redessinée.
     *                            false pour indiquer que la colonne n'a pas à être dessinée.
     */
    public void setColonneADessiner(boolean idxColonneADessiner) {
        this.colonneADessiner = idxColonneADessiner;
    }


    /**
     * Retourne la valeur de la carte sur le dessus sans toutefois la retirer.
     *
     * @return la carte du dessus sans toutefois la retirer.
     */
    public Carte voirCarteDessus() {
        return (Carte) pileDeCartes.peek();
    }


    /**
     * Retourne la carte sur le dessus.
     * La carte est retirée du dessus de la colonne.
     *
     * @return la carte du dessus sans toutefois la retirer. todo wtf is with this comment
     */
    public Carte retirerDessus() {
        return (Carte) pileDeCartes.pop();
    }


    /**
     * Retourne le nombre de cartes dans la colonne
     *
     * @return
     */
    public int nbCartesColonne() {
        return pileDeCartes.size();
    }

    /**
     * Retourne une liste qui donne le contenu de la colonne.
     * Si le contenu de la liste est modifié, la colonne n'est pas affectée.
     * Cette liste permet d'afficher la colonne.
     *
     * @return
     */
    public ArrayList<Carte> getListe() {
        ArrayList listeCartes = new ArrayList<Carte>();

        while (!pileDeCartes.empty()) {
            listeCartes.add(pileDeCartes.pop());
        }

        for (int i = 0; i < listeCartes.size(); i++) {
            pileDeCartes.push(listeCartes.get(listeCartes.size()));
            listeCartes.remove(listeCartes.size());
        }
        return listeCartes;
    }
}
