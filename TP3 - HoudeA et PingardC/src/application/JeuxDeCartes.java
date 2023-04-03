package application;

import cartes.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Les méthodes de cette classe mets en œuvre des jeux de cartes destinés à faire des tests.
 * @author Robert Aubé
 * @version 1.0 (Date de création: 2023-03-28)
 */
public class JeuxDeCartes {
    private static final Carte[]quatreAs = {
            new Carte(ValeurCartes.V_AS, SorteCartes.COEUR),
            new Carte(ValeurCartes.V_AS, SorteCartes.CARREAU),
            new Carte(ValeurCartes.V_AS, SorteCartes.TREFLE),
            new Carte(ValeurCartes.V_AS, SorteCartes.PIQUE),
    };

    /**
     * Créer un jeu de carte normal
     * @return un jeu de carte normal
     */
    public static PaquetDeCartes getJeuNormal() {
        return new PaquetDeCartes();
    }

    /**
     * Créer un paquet de cartes qui permet de finir rapidement au jeu Aces Up Solitaire
     * pour les tests.
     * @return un jeu de carte truqué pour les tests
     */
    public static PaquetDeCartes getJeuPresqueFini() {
        ArrayList listeCartes = new ArrayList(Arrays.asList(quatreAs));
        Carte[]petitJeu = {
                new Carte(ValeurCartes.V_3, SorteCartes.COEUR),
                new Carte(ValeurCartes.V_4, SorteCartes.COEUR),
                new Carte(ValeurCartes.V_5, SorteCartes.COEUR),
        };
        listeCartes.addAll(Arrays.asList(petitJeu));

        return (new PaquetDeCartes(listeCartes));
    }
    /**
     * Créer un paquet de cartes pour les tests et la retourne.
     * @return un jeu de carte truqué pour les tests
     */
    public static PaquetDeCartes getJeuDeCarteTruque() {
        ArrayList listeCartes = new ArrayList(Arrays.asList(quatreAs));


        for (SorteCartes sorte : SorteCartes.values()) {
            for (ValeurCartes valeur : ValeurCartes.values()) {
                listeCartes.add(0, new Carte(valeur, sorte));
            }
        }

        return (new PaquetDeCartes(listeCartes));
    }
}
