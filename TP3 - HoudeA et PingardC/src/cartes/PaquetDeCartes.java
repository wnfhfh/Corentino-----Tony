package cartes;

import java.util.ArrayList;
import java.util.Random;

public class PaquetDeCartes {
    ArrayList<Carte> paquet = new ArrayList<>();
    public final int NBR_ECHANGE = 52;

    public PaquetDeCartes(ArrayList<Carte> cartes) {
    }

    public PaquetDeCartes() {
    }

    public void melanger() {
        for (int i = 0; i < paquet.size(); i++) {
            Carte cTemp = paquet.get(i);

            Random rand = new Random();
            int randomNb = rand.nextInt(0, paquet.size());

            paquet.set(i, paquet.get(randomNb));
            paquet.set(randomNb, cTemp);
        }
    }

    public Carte consulterCarte(int emplacement) {
        return paquet.get(emplacement);
    }

    public boolean isEmpty() {
        return paquet.size() == 0;
    }

    private void permuter(int pos1, int pos2) {
        Carte temp = paquet.get(pos1);
        paquet.set(pos1, paquet.get(pos2));
        paquet.set(pos2, temp);
    }

    public Carte prendreCarte(int emplacement) {
        return paquet.remove(emplacement);
    }

    public void retournerToutesLesCartes(boolean estCoteFace) {
        for (int i = 0; i < paquet.size(); i++) {
            paquet.get(i).setVisible(estCoteFace);
        }
    }

    public int size() {
        return paquet.size();
    }

    //TODO methode validerPosition
}