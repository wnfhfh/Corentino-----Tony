package cartes;

import application.ColonneCartes;

import java.awt.Color;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;

public enum SorteCartes {
    COEUR('♥', RED),

    CARREAU('♦', RED),

    PIQUE('♠', BLACK),

    TREFLE('♣', BLACK);

    public char symbole;

    public final Color couleur;

    SorteCartes(char symbole, Color couleur) {
        this.couleur = couleur;
        this.symbole = symbole;
    }

    public Color getCouleur() {
        return couleur;
    }
}

