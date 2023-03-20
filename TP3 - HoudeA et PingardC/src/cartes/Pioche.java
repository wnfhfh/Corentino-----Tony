package cartes;

import exceptions.PiocheException;
import structures.pile.Pile;

public class Pioche {
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

    public String consulterDessus() throws PiocheException {
        if (!pioche.empty() && !(pioche.peek() == null)) {
            return ((Carte) pioche.peek()).toStringCarte();
        }
        throw new PiocheException("Guess what? Ca chie.");
    }

    public int size() {
        return pioche.size();
    }
}
