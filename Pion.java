import java.util.ArrayList;

public abstract class Pion extends Piece {

    public Pion(){
        this.setCouleur('B');
        this.setPosition(new Position("A1"));
    }

    public Pion(char couleur, Position p){
        this.setCouleur(couleur);
        this.setPosition(p);
    }

    public Pion(char couleur, int i, int b){
        this.setCouleur(couleur);
        this.setPosition(new Position(i, b));
    }

    public String getType() {
        return "pion";
    }

    public abstract ArrayList<Position> getDeplacementPossible(Plateau plateau);
    
    // public static void main(String[] args) {
    //     Pion t1 = new Pion();
    //     System.out.println(t1);
    // }
}
