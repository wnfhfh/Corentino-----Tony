package structures.pile;

import exceptions.PileException;

public class Pile {
    private NoeudPile sommet;
    private int taille = 0;

    public Pile() {
    }

    public void push(Object o) {
        if (taille == 0) {
            sommet = new NoeudPile(o);
        } else {
            sommet = new NoeudPile(o, sommet);
        }

        taille++;
    }

    public Object pop() throws PileException {
        if (empty()){
            throw new PileException();
        }

            Object popped = sommet.getElement();

            sommet = sommet.getPrecedent();

            taille--;

            return popped;

    }

    public Object peek() throws PileException {
        if (empty()){
            throw new PileException();
        }
        return sommet.getElement();
    }

    public boolean empty() {
        return taille == 0;
    }

    public int size() {
        return taille;
    }
}
