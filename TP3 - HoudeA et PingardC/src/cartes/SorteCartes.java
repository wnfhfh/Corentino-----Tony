package cartes;

import java.awt.Color;

public enum SorteCartes {
    COEUR('♥', Color.RED),

    CARREAU('\u2666', Color.RED),

    PIQUE('♠', Color.BLACK),

    TREFLE('♣', Color.BLACK);

    public char symbole;

    SorteCartes(char symbole, Color couleur) {
    }
}

