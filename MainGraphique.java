import java.util.ArrayList;

import javax.swing.ImageIcon;
import java.awt.Font;

import MG2D.*;
import MG2D.geometrie.*;

public class MainGraphique {

    public static char couleurJoueur(int j){
        // retourne la couleur du joueur en fonction de son numéro.
        if(j==0){
            return 'B';
        } else {
            return 'N';
        }
    }

    public static String couleurNom(int j){
        // retourne le nom de la couleur en fonction de son numéro.
        if(j==0){
            return "blanc";
        } else {
            return "noir";
        }
    }


    public static boolean appartenir(Plateau p, Position pos, Position pos2){
        // retourne vrai si le coups que l'on veut jouer (pos2) fait bien parti des coups possibles de la piece que l'on veut jouer (pos).
        int i;
        ArrayList<Position> listeCoupsPossibleDePos = new ArrayList<Position>(p.getCase(pos.getX()/75,pos.getY()/75).getDeplacementPossible(p));
        for(i=0;i<listeCoupsPossibleDePos.size();i++){
            if(listeCoupsPossibleDePos.get(i).equals(new Position(pos2.getX()/75,pos2.getY()/75))){
                return true;
            }
        }
        return false;
    }

    public static boolean mainJeu(Fenetre f, Plateau plateauJeu, Souris souris, int nbrtour){
        Position pos = new Position(); Position pos2 = new Position(); boolean not = false;  int joueur;
        joueur=nbrtour%2;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // premier clic
        if(souris.getClicGauche()){
            pos.setX(souris.getPosition().getX());
            pos.setY(souris.getPosition().getY());
            try{
                // verifie si le pion choisit est de la couleur du joueur
                if(plateauJeu.getCase(pos.getX()/75,pos.getY()/75).getCouleur()==couleurJoueur(joueur)){
                    try{
                        // affiche les coups possible 
                        plateauJeu.affichageCoupsPossible(f, plateauJeu.getCase(pos.getX()/75,pos.getY()/75), plateauJeu);
                        while(!not){
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // deuxieme clic
                            if(souris.getClicGauche()) {
                                pos2.setX(souris.getPosition().getX());
                                pos2.setY(souris.getPosition().getY());
                                if(appartenir(plateauJeu, pos, pos2)){ //  verifie que la case d'arrivée fait bien partie des coups possible
                                   plateauJeu.affichageDeplacer(f,new Position(pos.getX()/75,pos.getY()/75), new Position(pos2.getX()/75, pos2.getY()/75));
                                    f.rafraichir();
                                    return true; 
                                } else {
                                    f.effacer();
                                    plateauJeu.affichageGraphique(f);
                                    plateauJeu.affichagePieces(f);
                                    f.rafraichir();
                                    return false;
                                }
                            }
                        }
                        not = false;
                    }catch(NullPointerException e){ // evite les erreur lorsqu'on choisit une case sans piece
                    e.printStackTrace();    
                    return false;   
                    }
                }
            }catch(NullPointerException e){
                e.printStackTrace(); 
                return false;   
            }
        }
        return false;
    }

    public static boolean echecEtMat(Plateau p, int nbrtour){
        // fonction qui regarde si il y a echec et mat sur le plateau 
        int joueursuivant; int i;
        joueursuivant = nbrtour % 2;
        if(p.estEchec(couleurJoueur(joueursuivant))){
            char couleurJoueurSuivant = couleurJoueur(joueursuivant);
            ArrayList<Position> listeDeplacementRoiSuivant = (p.getRoi(couleurJoueurSuivant).getDeplacementPossible(p));
            for(i=0;i<listeDeplacementRoiSuivant.size();i++){
                if(!p.estEchec(new Position(listeDeplacementRoiSuivant.get(i)))){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) { // Lancement du jeu d'echec
        // initialisation
        int nbrtour = 0;
        Plateau plateauJeu = new Plateau();
        Souris souris;
        plateauJeu.rempliPlateau();
        Fenetre f = new Fenetre("Jeu Echecs", 600, 600);
        souris = f.getSouris();
        // changement de l'iconne de la fenetre
        ImageIcon img = new ImageIcon("./images/echiquier.png");
        f.setIconImage(img.getImage());
        // affichage initiale
        plateauJeu.affichageGraphique(f);
        plateauJeu.affichagePieces(f);
        f.rafraichir();
        // boucle du jeu
        while(!echecEtMat(plateauJeu, nbrtour)){
            if(mainJeu(f, plateauJeu, souris, nbrtour)){
                nbrtour++;
                System.out.println(plateauJeu);
            }
        }
        // ecran de fin de partie
        f.effacer();
        f.ajouter(new Rectangle(new Couleur(190,190,190), new Point(100, 250), new Point(500, 350), true));
        f.ajouter(new Texte(new Couleur(0, 0, 0), new String("Victoire du joueur "+ couleurNom((nbrtour+1)%2)), new Font("arial", 16, 35), new Point(300, 300)));
        f.ajouter(new Texture("./images/"+(plateauJeu.getRoi(couleurJoueur((nbrtour+1)%2))).getNomLong()+".png", new Point(200,30), 200, 200));
        f.rafraichir();
    }
}
