package net.guillaume.teaching.refactoring.monopoly;

import java.util.ArrayList;
import java.util.Collections;

public class JeuDeMonopoly {

    private final ArrayList<Joueur> joueurs = new ArrayList<>();
    private final Combinaison combinaison;
    private boolean stop = false;
    private ArrayList<CaseConstructible> caseLibreAAchat = new ArrayList<>();
    private Plateau plateau ;



    public JeuDeMonopoly() {
        plateau= new Plateau();
        joueurs.add(new Joueur("Marina","Elle", plateau.depart));
        joueurs.add(new Joueur("Ambre", "Elle", plateau.depart));
        joueurs.add(new Joueur("Loubna","Elle", plateau.depart));
        joueurs.add(new Joueur("Mathieu","Il", plateau.depart));
        joueurs.add(new Joueur("Cedric","Il", plateau.depart));
        combinaison = new Combinaison();
        caseLibreAAchat=  new ArrayList<>(plateau.getCaseAchetable());
    }


    public void jouerUnePartie() {
        while (!stop) {
            for (Joueur joueur : joueurs) {
                jouerUnTour(joueur);
                liberer(joueur);
            }
        }
        afficheFinDePartie();
    }


    private void jouerUnTour(Joueur unjoueur) {
        if (!stop) { //verifier avant le joueur suivant si la partie est arrete
            int[] valeurLancer = unjoueur.lancer();
            int total = combinaison.faitLaSomme(valeurLancer);
            boolean verifdouble = combinaison.estUnDouble(valeurLancer);
            unjoueur.monLance(total);  // plus logique de l'afficher avant son eventuel deplacement, achat ou paiment de loyer, prison j'ai donc decomposé mon ousuisje initial
    // SI DOUBLE
            if (verifdouble) {
                unjoueur.aFaitUnDouble(plateau.prison);  // incremente double met rejouer a true, le met en prison , condition liberable
                if (!unjoueur.estEnPrison()) {        // si pas ne prison ->  jouer  son resultat
                    jouerLeTotalDe(unjoueur, total);
                    unjoueur.ouSuisJe();
                }
                if (unjoueur.rejoue())    // Si  il a un double il va rejouer  condition nece car appel recursif
                {
                    System.out.println(unjoueur.getNomJ() +" rejoue.");
                    unjoueur.uneFoisCaSuffis();    // on remet a false son droit de rejouer  car appel recursif
                    jouerUnTour(unjoueur);  // il joue un autre tour
                }
                if (unjoueur.getLiberable()) {   // libere le joueur en prison qui a fait un double
                    unjoueur.liberationDouble();
                    jouerLeTotalDe(unjoueur, total);
                    unjoueur.ouSuisJe();
                }
            }
    // SI PAS DOUBLE
            else {
                unjoueur.aPasFaitUnDouble();   // donc on remet compteur double à 0
                if (!unjoueur.estEnPrison()) {
                    jouerLeTotalDe(unjoueur, total);   // il joue son resultat
                }
                unjoueur.ouSuisJe();
            }
        }
    }

    private void liberer(Joueur unjoueur) {
        if (unjoueur.estEnPrison())  // verifier si il est prison
        {
            unjoueur.liberationEnVue();   //   incrementer le nombre de tour et si 3 le liberer
        }
    }

    private void jouerLeTotalDe(Joueur unjoueur, int total) {
        unjoueur.joue(total, plateau.depart, plateau.impot, plateau.luxe, plateau.allerenprison, plateau.prison);   // tester si cas construtible
        if(unjoueur.getPosition() instanceof  CaseConstructible) {
        unjoueur.acheterCase((CaseConstructible) unjoueur.getPosition(),caseLibreAAchat);
        unjoueur.payerLoyer((CaseConstructible) unjoueur.getPosition(),caseLibreAAchat, joueurs);
        }
        stop = unjoueur.finDePartie();
        // avancer sur le plateau et faire action

    }

    private void afficheFinDePartie() {
        System.out.println("La partie est terminee !!!");   // L'affichage de la suite etait pas deamnde mais ca parait plus coherent
        trie();
        System.out.println("Le vainqueur est " + joueurs.get(0).getNomJ() + " avec " + joueurs.get(0).getArgent() +".");
        joueurs.get(0).afficherLesProprietes() ;
        for (int i=1; joueurs.size()>i; i++) {
            if(joueurs.get(i).getArgent()>0){
            System.out.println(joueurs.get(i).getNomJ() + " est " + (i + 1) + " place avec " + joueurs.get(i).getArgent() +".");
            joueurs.get(i).afficherLesProprietes();
        }
            else {
                System.out.println(joueurs.get(i).getNomJ() + " est " + (i + 1) + " place avec 0 argent.");
                joueurs.get(i).afficherLesProprietes();
            }
        }
    }

    private void trie() {  // non demande mais il me parait logique d afficher dans ordre
        Collections.sort(joueurs);
        Collections.sort(joueurs, Collections.reverseOrder());
    }




}

