package structures.pile;

import com.sun.source.doctree.SerialTree;

import java.io.IOException;
import java.io.Serializable;
import java.util.Stack;

public class Pile implements Serializable {
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

    public Object pop() {
        if (sommet == null) {
            return null;
        }

        Object popped = sommet.getElement();

        sommet = sommet.getPrecedent();

        taille--;

        return popped;
    }

    public Object peek() {
        if (sommet == null) {
            return null;
        }
        return sommet.getElement();
    }

    public boolean empty() {
        return size() == 0;
    }

    public int size() {
        return taille;
    }
}
