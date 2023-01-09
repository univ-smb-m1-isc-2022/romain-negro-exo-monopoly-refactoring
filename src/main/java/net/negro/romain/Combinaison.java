package net.negro.romain;

public class Combinaison {

    public Combinaison() {
    }

    protected boolean estUnDouble(int[] valeurLancer) {     // test si c'est un double
        return (valeurLancer[0] == valeurLancer[1]);
    }

    public int faitLaSomme(int[] valeurLancer) {    // calcul le total du lancer
        return (valeurLancer[0] + valeurLancer[1]);
    }


}



