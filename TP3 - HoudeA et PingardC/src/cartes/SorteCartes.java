package cartes;

import application.ColonneCartes;

import java.awt.Color;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;

public enum SorteCartes {
    COEUR('♥', RED),

    CARREAU('\u2666', RED),

    PIQUE('♠', BLACK),

    TREFLE('♣', BLACK);

    public char symbole;

    public Color couleur;

    SorteCartes(char symbole, Color couleur) {
        this.couleur = couleur;
    }

    public Color getCouleur() {
        return couleur;
    }
}

