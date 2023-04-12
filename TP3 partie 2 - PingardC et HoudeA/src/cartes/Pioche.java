package cartes;

import structures.pile.Pile;

import java.io.Serializable;

public class Pioche implements Serializable {
    private Pile pioche = new Pile();

    public Pioche(PaquetDeCartes paquet) {
        for (int i = 0; i < paquet.size(); i++) {
            pioche.push(paquet.consulterCarte(i));
        }
    }

    public Carte piger() {
        return (Carte) pioche.pop();
    }

    public boolean isEmpty() {
        return pioche.empty();
    }

    public String consulterDessus() {
        return ((Carte) pioche.peek()).toStringCarte();
    }

    public int size() {
        return pioche.size();
    }
}
