package cartes;

import java.util.Comparator;
import java.util.Objects;
import java.util.Stack;

public class Carte implements Comparable {
    public final char IMAGE_DOS = 's';
    SorteCartes sorte;
    ValeurCartes valeur;
    private boolean visible;

    public Carte(cartes.ValeurCartes valeur, cartes.SorteCartes sorte) {
        this.valeur = valeur;
        this.sorte = sorte;
    }

    public boolean estVisible() {
        return visible;
    }

    public SorteCartes getSorte() {
        return sorte;
    }

    public char getSorteSymbole() {
        return sorte.symbole;
    }

    public ValeurCartes getValeur() {
        return valeur;
    }

    public String getValeurSymbole() {
        return valeur.symbole;
    }

    public void setSorte(SorteCartes sorte) {
        this.sorte = sorte;
    }

    public void setValeur(ValeurCartes valeur) {
        this.valeur = valeur;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carte carte = (Carte) o;
        return IMAGE_DOS == carte.IMAGE_DOS && visible == carte.visible && sorte == carte.sorte && valeur == carte.valeur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(IMAGE_DOS, sorte, valeur, visible);
    }

    @Override
    public String toString() {
        return getValeurSymbole()+getSorteSymbole();
    }

    public String toStringCarte() {
        String str = "";
        if (visible) {
            str += getValeurSymbole() + getSorteSymbole();
        } else {
            str += IMAGE_DOS;
        }
        return str;
    }

    @Override
    public int compareTo(Object o) {
        if (this == null || o == null){
            if (this == null || o == null) {
                return 0;
            }
        }
        if (this.valeur.getValeurDecarte() == ((Carte) o).getValeur().getValeurDecarte())
            return 0;
        else if (this.valeur.getValeurDecarte() > ((Carte) o).getValeur().getValeurDecarte()){
    return 1;
        }
        else return -1;

        //return this.valeur.compareTo(((Carte) o).getValeur());
        //return this.valeur.getValeurDecarte() - ((Carte) o).getValeur().getValeurDecarte();
    }
}
