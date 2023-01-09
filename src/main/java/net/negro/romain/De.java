package net.negro.romain;

public class De {

    private int valeurFace;


    public De() {
        valeurFace = 1;
    }

    public int getValeur() {
        return valeurFace;
    }

    public void lancer() {
        valeurFace = (int) (Math.random() * 6) + 1;
    }

}