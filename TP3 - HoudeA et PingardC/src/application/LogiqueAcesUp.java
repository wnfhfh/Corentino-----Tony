package application;

import cartes.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La logique du jeu AcesUp solitaire.
 *
 * @see <a href="https://www.solitaire-play.com/aces-up/">https://www.solitaire-play.com/aces-up</a>
 */
public class LogiqueAcesUp implements Serializable {

    /**
     * Nombre de colonne de cartes
     */
    public static final int NB_COLONNES_DE_CARTES = 4;

    /**
     * La pioche qui va recevoir le paquet de cartes
     */
    Pioche pioche;

    /**
     * Contient les colonnes de cartes.
     * Il y en a autant de <b>colonne de carte</b> que la valeur de NB_COLONNES_DE_CARTES.
     */
    private ColonneCartes[] colonneCartes = new ColonneCartes[NB_COLONNES_DE_CARTES];


    /**
     * Initialise la logique du jeu Aces Up Solitaire avec un paquet de carte reçu en argument.
     *
     * @param paquet Paquet qui sera placé directement dans la pioche.
     *               La logique de jeu ne mélange pas le paquet
     */
    public LogiqueAcesUp(PaquetDeCartes paquet) {
        this.pioche = new Pioche(paquet);
        initialiserLesColonnesDeCartes();
    }

    /**
     * Initialise les colonnes de cartes et fait la première pige dans la pioche pour toutes les colonnes.
     */
    private void initialiserLesColonnesDeCartes() {
        for (int i = 0; i < NB_COLONNES_DE_CARTES - 1; i++) {
            colonneCartes[i] = new ColonneCartes(pioche);
        }
    }

    /**
     * Permet de savoir si la pioche est vide
     *
     * @return <b>true</b> si la pioche est vide
     */
    public boolean piocheEstVide() {
        return pioche.isEmpty();
    }

    /**
     * Permet de connaître le nombre de cartes restantes dans la pioche.
     *
     * @return le nombre de cartes dans la pioche du jeu
     */
    public int nbCartesPioche() {
        return pioche.size();
    }

    /**
     * Retourne la liste des cartes qui se trouvent dans la colonne de cartes demandée en paramètre.
     * Cette liste est destinée à l'affichage pour l'application.
     *
     * @param idxColonne le numéro de la colonne souhaitée
     * @return la liste des cartes qui se trouvent dans la colonne de cartes demandée en paramètre. Si l'index
     * est invalide, cette méthode retourne <i>null</i>.
     */
    public ArrayList<Carte> getColonneCartes(int idxColonne) {
        return colonneCartes[idxColonne].getListe();
    }

    /**
     * Permet de savoir si la colonne dont le numéro est passé en paramètre est à redessiner puisqu'elle
     * a changé.
     *
     * @param idxColonne Index de la colonne
     * @return <b>true</b> si la colonne dont le numéro est passé en paramètre est à redessiner puisqu'elle a changé.
     */
    public boolean colonneEstMAJ(int idxColonne) {
        return colonneCartes[idxColonne].isColonneADessiner();
    }

    /**
     * Indique à la logique que la colonne dont le numéro est passé en paramètre est a été redessinée. todo understand this sentence
     * La colonne doit alors mettre à jour son état indiquant que l'affichage est à jour et n'a pas être
     * affiché tant que celle-ci n'a pas été modifiée.
     *
     * @param idxColonne Index de la colonne
     */
    public void setColonneCommeDessine(int idxColonne) {
        colonneCartes[idxColonne].setColonneADessiner(true);
    }

    /**
     * Pour chaque colonne du jeu, une carte est pigé dans la pioche pour l'ajouter èa la colonne
     */
    public void piger() {
        for (int i = 0; i < NB_COLONNES_DE_CARTES; i++) {
            colonneCartes[i].ajouterCarteDessus(pioche.piger());
        }
    }

    /**
     * Permet de déplacer, selon les règles du jeu, une carte de la colonne de
     * cartes dont l'index de la colonne de carte est reçu en entrée.
     * La carte est déplacée dans la première colonne vide.
     *
     * @param idxColonne le numéro de la colonne d'où on veut déplacer la carte.
     */
    public void deplacerCarte(int idxColonne) {
        Carte deplacee = colonneCartes[idxColonne].retirerDessus();
        for (int i = 0; i < NB_COLONNES_DE_CARTES - 1; i++) {
            if (colonneCartes[i].estVide()) {
                colonneCartes[i].ajouterCarteDessus(deplacee);
                break;
            }
        }
    }

    /**
     * Permet d'enlever, selon les règles du jeu, une carte de la colonne de
     * cartes dont l'index de la colonne de carte est reçu en entrée.
     *
     * @param idxColonne le numéro de la colonne d'où on veut enlever la carte.
     */
    public void enleverColonne(int idxColonne) {
        for (int i = 0; i < NB_COLONNES_DE_CARTES - 1; i++) {
            if (i != idxColonne && colonneCartes[i].voirCarteDessus().compareTo(colonneCartes[idxColonne].voirCarteDessus()) > 0 && colonneCartes[i].voirCarteDessus().getSorte() == colonneCartes[idxColonne].voirCarteDessus().getSorte()) {
                colonneCartes[idxColonne].retirerDessus();
            }
        }
    }

    /**
     * Retourne <b>true</b> si la carte1 est plus petite que la carte2 qui sont reçu en paramètre.
     * L'as est considéré comme la carte la plus élevée dans le jeu Aces Up Solitaire.
     *
     * @param carte1
     * @param carte2
     * @return <b>true</b> si la carte1 est plus petite que la carte2.
     */
    private boolean estMemeSorteEtPlusPetite(Carte carte1, Carte carte2) {
        if (carte1.compareTo(carte2) < 0) {
            return true;
        }
        return false;
    }

    /**
     * Permet de vérifier si on a une victoire lorsque la pioche est vide.
     * Il y a victoire, s'il y a seulement un 1 carte, un as,
     * en haut de chacune des colonnes de cartes
     *
     * @return boolean, vrai si on a une victoire.
     */
    public boolean partieEstGagne() {
        boolean estGagne = true;

        for (int i = 0; i < NB_COLONNES_DE_CARTES - 1; i++) {
            if (colonneCartes[i].voirCarteDessus().getValeur() != ValeurCartes.V_AS || colonneCartes[i].nbCartesColonne() != 1) {
                estGagne = false;
            }
        }
        return estGagne;
    }

    /**
     * Permet de savoir, lorsque la pioche est vide, s'il est possible de jouer
     * encore un coup.
     * S'il n'y a aucun coup possible, la partie est terminée.
     * <p>
     * Selon les règles, un coup est possible si on peut <b>retirer</b> ou <b>déplacer</b> des cartes
     * dans l'une des colonnes :
     * 1- on peut <b>retirer</b> du jeu la carte s'il y a une carte plus grande au-dessus dans une autre colonne.
     * 2- on peut <b>déplacer</b> une carte s'il y a au moins une colonne vide dans le jeu.
     *
     * @return boolean <b>true</b> s'il n'est pas possible de jouer un autre coup,
     * donc que la partie est terminée.
     */
    public boolean partieEstTermine() {
        for (int i = 0; i < colonneCartes.length - 1; i++) {
            if (carteDeLaColonneDeplacable(i)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Retourne <b>true</b> si la carte de la colonne dont l'index est passé en argument est <b>déplaçable</b>
     * ou encore <b>retirable</b>.
     * Selon les règles...
     * 1- on peut <b>retirer</b> du jeu la carte s'il y a une carte plus grande au-dessus dans une autre colonne.
     * 2- on peut <b>déplacer</b> une carte s'il y a au moins une colonne vide dans le jeu.
     *
     * @param idxColonne
     * @return <b>true</b> si on peut retirer ou déplacer la carte et
     * <b>false</b> si la colonne est vide ou on ne peut ni retirer et ni déplacer la carte.
     */
    private boolean carteDeLaColonneDeplacable(int idxColonne) {
        for (int i = 0; i < colonneCartes.length - 1; i++) {
            if (colonneCartes[i].estVide() || i != idxColonne && colonneCartes[i].voirCarteDessus().compareTo(colonneCartes[idxColonne].voirCarteDessus()) > 0 && colonneCartes[i].voirCarteDessus().getSorte() == colonneCartes[idxColonne].voirCarteDessus().getSorte()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Permet d'indiquer que toutes les colonnes sont à redessiner.
     * Cette méthode peut être utilisée pour recommencer la partie.
     */
    public void setAllColonneADessiner() {
        for (int i = 0; i < NB_COLONNES_DE_CARTES - 1; i++) {
            setColonneCommeDessine(i);
        }
    }
}