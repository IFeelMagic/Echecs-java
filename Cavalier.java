import java.util.ArrayList;

public class Cavalier extends Piece {

    public Cavalier(){
        this.setCouleur('B');
        this.setPosition(new Position("A1"));
    }

    public Cavalier(char couleur, Position p){
        this.setCouleur(couleur);
        this.setPosition(p);
    }

    public Cavalier(char couleur, int i, int b){
        this.setCouleur(couleur);
        this.setPosition(new Position(i, b));
    }

    public String getType() {
        return "cavalier";
    }

    @Override
    public ArrayList<Position> getDeplacementPossible(Plateau plateau) {
        // Méthode qui retourne une liste de toutes les positions où un cavalier peut aller
        ArrayList<Position> Liste_pos_possible = new ArrayList<Position>();
        Position actuelle = this.getPosition();
        if (actuelle.getX()+2 < 8 && actuelle.getY()+1 < 8) {
            if((plateau.getCase(actuelle.getX()+2, actuelle.getY()+1) == null) || (plateau.getCase(actuelle.getX()+2, actuelle.getY()+1).getCouleur() != this.getCouleur())){
            Liste_pos_possible.add(new Position(actuelle.getX()+2, actuelle.getY()+1));
            }
        } 
        if (actuelle.getX()+2 < 8 && actuelle.getY()-1 >= 0) {
            if((plateau.getCase(actuelle.getX()+2, actuelle.getY()-1) == null) || (plateau.getCase(actuelle.getX()+2, actuelle.getY()-1).getCouleur() != this.getCouleur())){
            Liste_pos_possible.add(new Position(actuelle.getX()+2, actuelle.getY()-1));
            }
        }
        if (actuelle.getX()-2 >= 0 && actuelle.getY()+1 < 8) {
            if((plateau.getCase(actuelle.getX()-2, actuelle.getY()+1) == null) || (plateau.getCase(actuelle.getX()-2, actuelle.getY()+1).getCouleur() != this.getCouleur())){
            Liste_pos_possible.add(new Position(actuelle.getX()-2, actuelle.getY()+1));
            }
        }
        if (actuelle.getX()-2 >= 0 && actuelle.getY()-1 >= 0) {
            if((plateau.getCase(actuelle.getX()-2, actuelle.getY()-1) == null) || (plateau.getCase(actuelle.getX()-2, actuelle.getY()-1).getCouleur() != this.getCouleur())){
            Liste_pos_possible.add(new Position(actuelle.getX()-2, actuelle.getY()-1));
            }
        }
        if (actuelle.getX()+1 < 8 && actuelle.getY()+2 < 8) {
            if((plateau.getCase(actuelle.getX()+1, actuelle.getY()+2) == null) || (plateau.getCase(actuelle.getX()+1, actuelle.getY()+2).getCouleur() != this.getCouleur())){
            Liste_pos_possible.add(new Position(actuelle.getX()+1, actuelle.getY()+2));
            }
        } 
        if (actuelle.getX()-1 >= 0 && actuelle.getY()+2 < 8) {
            if((plateau.getCase(actuelle.getX()-1, actuelle.getY()+2) == null) || (plateau.getCase(actuelle.getX()-1, actuelle.getY()+2).getCouleur() != this.getCouleur())){
            Liste_pos_possible.add(new Position(actuelle.getX()-1, actuelle.getY()+2));
            }
        }
        if (actuelle.getX()+1 < 8 && actuelle.getY()-2 >= 0) {
            if((plateau.getCase(actuelle.getX()+1, actuelle.getY()-2) == null) || (plateau.getCase(actuelle.getX()+1, actuelle.getY()-2).getCouleur() != this.getCouleur())){
            Liste_pos_possible.add(new Position(actuelle.getX()+1, actuelle.getY()-2));
            }
        }
        if (actuelle.getX()-1 >= 0 && actuelle.getY()-2 >= 0) {
            if((plateau.getCase(actuelle.getX()-1, actuelle.getY()-2) == null) || (plateau.getCase(actuelle.getX()-1, actuelle.getY()-2).getCouleur() != this.getCouleur())){
            Liste_pos_possible.add(new Position(actuelle.getX()-1, actuelle.getY()-2));
            }
        }
        return Liste_pos_possible;
    }
    
    // public static void main(String[] args) {
    //     Cavalier t1 = new Cavalier();
        // Plateau plateauJeu = new Plateau();
        // System.out.println(t1);
        // System.out.println(t1.getDeplacementPossible(plateauJeu));
    // }
}