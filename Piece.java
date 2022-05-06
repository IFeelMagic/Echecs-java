import java.util.ArrayList;
import java.util.Objects;

public abstract class Piece {
    private char couleur;
    private Position position;

    // Constructeurs

    public Piece() {
        this.couleur = 'B';
        this.position = new Position("A2");
    }

    public Piece(Piece p){
        this.couleur = p.getCouleur();
        this.position = p.getPosition();
    }

    public Piece(char couleur, int x, int y) {
        this.couleur = couleur;
        this.position = new Position(x,y);
    }

    public Piece(char couleur, Position position) {
        this.couleur = couleur;
        this.position = new Position(position);
    }

    public Piece(char couleur, String position) {
        this.couleur = couleur;
        this.position = new Position(position);
    }

    // Méthodes

    public abstract String getType();

    public char getCouleur() {
        return this.couleur;
    }

    public void setCouleur(char couleur) {
        this.couleur = couleur;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String nomCouleur(){
        if(getCouleur()=='N'){
            return("noir");
        } else {
            return("blanc");
        }
    }

    public String getNomCourt(){
        String[] nom = getType().split("");
        return(nom[0].toUpperCase()+nom[1]+getCouleur());
    }

    public String getNomLong(){
        return(getType()+"_"+getCouleur());
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Piece)) {
            return false;
        }
        Piece piece = (Piece) o;
        return couleur == piece.couleur && Objects.equals(position, piece.position);
    }

    public abstract ArrayList<Position> getDeplacementPossible(Plateau plateau);

    public String toString() {
        return(getType()+" "+ nomCouleur()+" en "+position);
    }

    // public static void main(String[] args) {
        // Piece p1 = new Piece();
        // System.out.println(p1);
        // Piece p2 = new Piece("roi",'B',"E4");
        // System.out.println(p2);
        // Piece p3 = new Piece("fou",'N',4,6);
        // System.out.println(p3);
        // Piece p4 = new Piece(p1);
        // System.out.println(p4);

    // }
}
