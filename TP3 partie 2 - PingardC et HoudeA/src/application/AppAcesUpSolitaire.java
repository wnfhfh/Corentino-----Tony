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

        //new AcesUpSolitaire(JeuxDeCartes.getJeuPresqueFini(), false);
        new AcesUpSolitaire(JeuxDeCartes.getJeuNormal(), false);
    }

    /**
     * Créer un paquet de cartes pour les tests et la retourne
     * @return un jeu de carte truqué ou non pour les tests
     */
    private PaquetDeCartes getJeuDeCartePourTests() {
        return (JeuxDeCartes.getJeuPresqueFini());
    }

    public static void main(String[] args) {
        new AppAcesUpSolitaire();
    }
}
