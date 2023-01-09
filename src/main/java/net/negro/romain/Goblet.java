package net.negro.romain;

public class Goblet {
    private final De[] des;

    public Goblet(De[] des) {
        this.des = des;
    }
    public int lancer() {
        des[0].lancer();
        des[1].lancer();
        return valeurDes();
    }
    public int valeurDes() {
        return des[0].getValeur() + des[1].getValeur();
    }
    public boolean estUnDouble() {     // test si c'est un double
        return (des[0].getValeur() == des[1].getValeur());
    }

}
