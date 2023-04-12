package application;

import cartes.PaquetDeCartes;

/**
 * Application du jeu Aces up solitaire.
 * Cette classe permet d'exécuter le jeu.
 * @author Robert Aubé
 * @version 1.0 (Date de création: 2023-03-28)
 */
public class AppAcesUpSolitaire {
    public AppAcesUpSolitaire() {
        PaquetDeCartes pq = JeuxDeCartes.getJeuPresqueFini();
        //new AcesUpSolitaire(pq, true);
        new AcesUpSolitaire(JeuxDeCartes.getJeuNormal(), false);
    }

    public static void main(String[] args) {
        new AppAcesUpSolitaire();
    }
}
