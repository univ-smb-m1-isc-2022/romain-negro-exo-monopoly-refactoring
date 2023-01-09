package net.negro.romain;

public abstract class Case {

    private final String name;
    private Case suivante;

    public Case(String name) {
        this.name = name;
    }

    public void setSuivante(Case c) {
        suivante = c;
    }

    public Case retourneCaseSuivante() {
        return suivante;
    }


    public String getName() {
        return name;
    }


}
