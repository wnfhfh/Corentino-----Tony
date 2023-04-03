package structures.pile;

<<<<<<< HEAD
import exceptions.PileException;
=======
import java.io.IOException;
>>>>>>> main

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

<<<<<<< HEAD
    public Object pop() throws PileException {
        if (empty()){
            throw new PileException();
        }

            Object popped = sommet.getElement();
=======
    public Object pop() {
        if (sommet == null) {
            return null;
        }

        Object popped = sommet.getElement();
>>>>>>> main

            sommet = sommet.getPrecedent();

            taille--;

            return popped;

    }

<<<<<<< HEAD
    public Object peek() throws PileException {
        if (empty()){
            throw new PileException();
=======
    public Object peek() {
        if (sommet == null) {
            return null;
>>>>>>> main
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
