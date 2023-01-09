package net.negro.romain;

public class Goblet {
    private static final De[] des = new De[]{new De(), new De()};
    public static int lancer() {
        des[0].lancer();
        des[1].lancer();
        return valeurDes();
    }
    public static int valeurDes() {
        return des[0].getValeur() + des[1].getValeur();
    }
    public static boolean estUnDouble() {     // test si c'est un double
        return (des[0].getValeur() == des[1].getValeur());
    }

}
