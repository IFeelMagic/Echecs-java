import java.util.ArrayList;

public class Roi extends Piece {

    public Roi(){
        this.setCouleur('B');
        this.setPosition(new Position("A1"));
    }

    public Roi(char couleur, Position p){
        this.setCouleur(couleur);
        this.setPosition(p);
    }

    public Roi(char couleur, int i, int b){
        this.setCouleur(couleur);
        this.setPosition(new Position(i, b));
    }

    public String getType() {
        return "roi";
    }

    @Override
    public ArrayList<Position> getDeplacementPossible(Plateau plateau) {
        // Méthode qui retourne une liste de toutes les positions où un roi peut aller
        ArrayList<Position> Liste_pos_possible = new ArrayList<Position>();
        Position actuelle = this.getPosition();
        //haut
        if (actuelle.getY()+1 < 8) {
            if((plateau.getCase(actuelle.getX(), actuelle.getY()+1) == null) || (plateau.getCase(actuelle.getX(), actuelle.getY()+1).getCouleur() != this.getCouleur())){
            Liste_pos_possible.add(new Position(actuelle.getX(), actuelle.getY()+1));
            }
        } 
        //bas
        if (actuelle.getY()-1 >= 0) {
            if((plateau.getCase(actuelle.getX(), actuelle.getY()-1) == null) || (plateau.getCase(actuelle.getX(), actuelle.getY()-1).getCouleur() != this.getCouleur())){
            Liste_pos_possible.add(new Position(actuelle.getX(), actuelle.getY()-1));
            }
        }
        //gauche
        if (actuelle.getX()-1 >= 0) {
            if((plateau.getCase(actuelle.getX()-1, actuelle.getY()) == null) || (plateau.getCase(actuelle.getX()-1, actuelle.getY()).getCouleur() != this.getCouleur())){
            Liste_pos_possible.add(new Position(actuelle.getX()-1, actuelle.getY()));
            }
        }
        //droite
        if (actuelle.getX()+1 < 8) {
            if((plateau.getCase(actuelle.getX()+1, actuelle.getY()) == null) || (plateau.getCase(actuelle.getX()+1, actuelle.getY()).getCouleur() != this.getCouleur())){
            Liste_pos_possible.add(new Position(actuelle.getX()+1, actuelle.getY()));
            }
        }
        //bas droit
        if (actuelle.getX()+1 < 8 && actuelle.getY()-1 >= 0) {
            if((plateau.getCase(actuelle.getX()+1, actuelle.getY()-1) == null) || (plateau.getCase(actuelle.getX()+1, actuelle.getY()-1).getCouleur() != this.getCouleur())){
            Liste_pos_possible.add(new Position(actuelle.getX()+1, actuelle.getY()-1));
            }
        } 
        //haut droit
        if (actuelle.getX()+1 < 8 && actuelle.getY()+1 < 8) {
            if((plateau.getCase(actuelle.getX()+1, actuelle.getY()+1) == null) || (plateau.getCase(actuelle.getX()+1, actuelle.getY()+1).getCouleur() != this.getCouleur())){
            Liste_pos_possible.add(new Position(actuelle.getX()+1, actuelle.getY()+1));
            }
        }
        //bas gauche
        if (actuelle.getX()-1 >= 0 && actuelle.getY()-1 >= 0) {
            if((plateau.getCase(actuelle.getX()-1, actuelle.getY()-1) == null) || (plateau.getCase(actuelle.getX()-1, actuelle.getY()-1).getCouleur() != this.getCouleur())){
            Liste_pos_possible.add(new Position(actuelle.getX()-1, actuelle.getY()-1));
            }
        }
        //haut gauche
        if (actuelle.getX()-1 >= 0 && actuelle.getY()+1 < 8) {
            if((plateau.getCase(actuelle.getX()-1, actuelle.getY()+1) == null) || (plateau.getCase(actuelle.getX()-1, actuelle.getY()+1).getCouleur() != this.getCouleur())){
            Liste_pos_possible.add(new Position(actuelle.getX()-1, actuelle.getY()+1));
            }
        }
        return Liste_pos_possible;
    }
    
    // public static void main(String[] args) {
    //     Roi t1 = new Roi();
    //     System.out.println(t1);
    // }
}
