import java.util.ArrayList;

import MG2D.*;
import MG2D.geometrie.*;

public class Plateau {
    private ArrayList<Piece> plateau = new ArrayList<Piece>();

    public Plateau() { 
        // initialisation des places de base des pièces 
        plateau.add(new Tour('B', 0, 0));
        plateau.add(new Cavalier('B', 1,0));
        plateau.add(new Fou('B', 2, 0));
        plateau.add(new Reine( 'B', 3, 0)); 
        plateau.add(new Roi('B', 4, 0)); 
        plateau.add(new Fou('B', 5, 0));
        plateau.add(new Cavalier('B', 6, 0)); 
        plateau.add(new Tour('B', 7, 0)); 
        plateau.add(new PionBlanc(0, 1));
        plateau.add(new PionBlanc(1, 1)); 
        plateau.add(new PionBlanc(2, 1)); 
        plateau.add(new PionBlanc(3, 1));
        plateau.add(new PionBlanc(4, 1)); 
        plateau.add(new PionBlanc(5, 1)); 
        plateau.add(new PionBlanc(6, 1));
        plateau.add(new PionBlanc(7, 1)); 
        plateau.add(new PionNoir(new Position(0,6))); 
        plateau.add(new PionNoir(new Position(1,6)));
        plateau.add(new PionNoir(new Position(2,6))); 
        plateau.add(new PionNoir(new Position(3,6))); 
        plateau.add(new PionNoir(new Position(4,6)));
        plateau.add(new PionNoir(new Position(5,6))); 
        plateau.add(new PionNoir(new Position(6,6))); 
        plateau.add(new PionNoir(new Position(7,6)));
        plateau.add(new Tour('N', 0, 7)); 
        plateau.add(new Cavalier( 'N', 1, 7)); 
        plateau.add(new Fou('N', 2, 7));
        plateau.add(new Reine('N', 3, 7)); 
        plateau.add(new Roi('N', 4, 7)); 
        plateau.add(new Fou('N', 5, 7));
        plateau.add(new Cavalier( 'N', 6, 7)); 
        plateau.add(new Tour('N', 7, 7));
    }

    public Piece getCase(int a, int b){
        // retourne la piece se trouvant dans une case donnée, si il n'y a pas de piece a la position, retourne null
        int i = 0;
        boolean trouve = false ;
        while(!trouve){
            if (this.plateau.get(i).getPosition().getX()==a && (this.plateau.get(i).getPosition().getY()==b)) {
                return this.plateau.get(i);
            }
            if (i==this.plateau.size()-1) {
                trouve = true;
            }
            i++;
        }
        return null;
    }

    public Piece getCase(Position p){
        int a = p.getX();
        int b = p.getY();
        return this.getCase(a, b);
    }

    public Piece getCase(String texte){
        Position p = new Position(texte);
        int a = p.getX();
        int b = p.getY();
        return this.getCase(a, b);
    }

    public String[][] rempliPlateau(){
        // Retourne un plateau rempli avec les pieces
        int tY = 8;
        int tX= 8;
        int i, j;
        String[][] plateauJeu = new String[tY][tX];
        for(j=(tY-1);j>=0;j--){
            for(i=0;i<tX;i++){
                if(this.getCase(i,j)==null){
                    plateauJeu[i][j]="   ";
                } else {
                    plateauJeu[i][j]=this.getCase(i,j).getNomCourt();
                }
            }
        }
        return plateauJeu;
    }

    public void affichagePieces(Fenetre f){
        // affichage graphique des images pour les pieces
        int i, j;
        for(i=0;i<8;i++){
            for(j=0;j<8;j++){
                if(this.getCase(j,i)!=null){
                    // si le roi est echec
                    if(this.getCase(j,i) instanceof Roi && estEchec(this.getCase(j,i).getCouleur())){
                        f.ajouter(new Texture("./images/"+this.getCase(j, i).getNomLong()+"_echec.png", new Point(j*75,i*75),75,75));
                        f.rafraichir();
                    } else {
                        f.ajouter(new Texture("./images/"+this.getCase(j, i).getNomLong()+".png", new Point(j*75,i*75),75,75));
                        f.rafraichir();
                    }  
                }
            }
        }
    }

    public void affichageGraphique(Fenetre f) {
        // Affichage graphique des cases du plateau
        int i,j;
        for (i = 0; i<8; i+=2) {
            for(j=0; j <8; j+=2) {
                f.ajouter( new Rectangle(new Couleur(100, 100, 100), new Point(i*75, j*75),  75, 75, true));
            }
        }
        for (i = 1; i<8; i+=2) {
            for(j=1; j <8; j+=2) {
                f.ajouter(new Rectangle(new Couleur(100, 100, 100), new Point(i*75, j*75),  75, 75, true));
            }
        }
    }

    public void affichageCoupsPossible(Fenetre f, Piece p, Plateau plateau){
        // Affiche les cercles rouges représentant les coups possible d'une piece passée en paramètres
        int i; Point pos;
        ArrayList<Position> listcoup = new ArrayList<Position>(p.getDeplacementPossible(plateau));
        listcoup = p.getDeplacementPossible(plateau);
        f.effacer();
        this.affichageGraphique(f);
        this.affichagePieces(f);
        for(i=0;i<listcoup.size();i++){
            pos=new Point(listcoup.get(i).getX()*75+(75/2),listcoup.get(i).getY()*75+(75/2));
            f.ajouter(new Ovale(new Couleur(250, 20, 20), pos, 70, 70, false));
        }
        f.rafraichir();
    }

    public boolean affichePlateau(){
        // affiche le plateau rempli dans le terminal
        int tY = 8;
        int tX= 8;
        int i, j;
        String[][] plateauJeu = new String[tY][tX];
        System.out.print("  |-----|-----|-----|-----|-----|-----|-----|-----|\n");
        for(j=(tY-1);j>=0;j--){
            System.out.print((j+1)+" |");
            for(i=0;i<tX;i++){

                if(this.getCase(i,j)==null){
                    plateauJeu[i][j]="   ";
                } else {
                    plateauJeu[i][j]=this.getCase(i,j).getNomCourt();
                }
                System.out.print(" "+plateauJeu[i][j]+" |");
            }
            System.out.print(" "+(j+1));
            System.out.print("\n");
            System.out.print("  |-----|-----|-----|-----|-----|-----|-----|-----|");
            System.out.print("\n");
        }
        System.out.print("     A     B     C     D     E     F     G     H ");
        return true;
    }

    public String toString(){
        return (""+affichePlateau());
    }

    public ArrayList<Piece> getPiecesBlanches(){
        // retourne la liste des pieces blanches encore en jeu
        int i;
        ArrayList<Piece> listePiecesBlanches = new ArrayList<Piece>();
        for(i=0;i<this.plateau.size();i++){
            if(this.plateau.get(i).getCouleur()=='B'){
                listePiecesBlanches.add(this.plateau.get(i));
            }
        }
        return listePiecesBlanches;
    }

    public ArrayList<Piece> getPiecesNoires(){
        // retourne la liste des pieces noires encore en jeu
        int i;
        ArrayList<Piece> listePiecesBlanches = new ArrayList<Piece>();
        for(i=0;i<this.plateau.size();i++){
            if(this.plateau.get(i).getCouleur()=='N'){
                listePiecesBlanches.add(this.plateau.get(i));
            }
        }
        return listePiecesBlanches;
    }

    public boolean deplacer(Position from, Position to){
        // permet le déplacement d'une piece d'une posisiton a une autre. retourne true si cela c'est bien passé, sinon false
        int i;
        if(this.getCase(from)==null){
            return false;
        }if(to.getX() > 7 || to.getY() > 7 || to.getX() < 0 || to.getY() < 0){
            return false;
        }if(from.getX() > 7 || from.getY() > 7 || from.getX() < 0 || from.getY() < 0){
            return false;
        }
        ArrayList<Position> listcoup = (this.getCase(from.getX(),from.getY()).getDeplacementPossible(this));
        if(to.getY()==7 && this.getCase(from) instanceof PionBlanc){
            Position pos = new Position(from);
            this.plateau.remove(this.getCase(from));
            this.plateau.add(new Reine('B', pos));
        }
        if(to.getY()==0 && this.getCase(from) instanceof PionNoir){
            Position pos = new Position(from);
            this.plateau.remove(this.getCase(from));
            this.plateau.add(new Reine('N', pos));
        }
        for(i=0;i<listcoup.size();i++){
            if(to.equals(listcoup.get(i))){
                // null ?
                Piece piece = this.getCase(to);
                if (piece != null) {
                    this.plateau.remove(this.getCase(to));
                }
                this.getCase(from).setPosition(new Position(to));
                return true;
            }
        }
        return false;
    }

    public void affichageDeplacer(Fenetre f,Position from, Position to){
        // Affichage graphique du déplacement
        f.effacer();
        this.deplacer(from, to);
        this.affichageGraphique(f);
        this.affichagePieces(f);
        f.rafraichir();
    }

    public Piece getRoi(char couleur){
        // retourne le roi de la couleur passée en paramètre
        int i;
        for(i=0;i<this.plateau.size();i++){
            if(plateau.get(i) instanceof Roi && this.plateau.get(i).getCouleur()==couleur){
                return (this.plateau.get(i));
            }
        }
        return null;
    }

    public boolean estEchec(char couleur){
        // regarde si le le roi de la couleur passée en paramètre est en echec, retourne true si cela c'est vrai, sinon false
        int i; int j;
        if(couleur=='N') {
            ArrayList<Piece> listePiecesBlanches = new ArrayList<Piece>(this.getPiecesBlanches());
            ArrayList<Position> listeDeTousLesCoupsPossible = new ArrayList<Position>();
            Position posRoi = this.getRoi(couleur).getPosition();
            for(i=0;i<listePiecesBlanches.size();i++){
                for(j=0;j<listePiecesBlanches.get(i).getDeplacementPossible(this).size();j++){
                    listeDeTousLesCoupsPossible.add(listePiecesBlanches.get(i).getDeplacementPossible(this).get(j));
                }
            }
            for(i=0;i<listeDeTousLesCoupsPossible.size();i++){
                if(posRoi.equals(listeDeTousLesCoupsPossible.get(i))){
                    return true;
                }
            }
            return false;
        }
        if(couleur=='B'){
            ArrayList<Piece> listePiecesNoires = new ArrayList<Piece>(this.getPiecesNoires());
            ArrayList<Position> listeDeTousLesCoupsPossible = new ArrayList<Position>();
            Position posRoi = this.getRoi(couleur).getPosition();
            for(i=0;i<listePiecesNoires.size();i++){
                for(j=0;j<listePiecesNoires.get(i).getDeplacementPossible(this).size();j++){
                    listeDeTousLesCoupsPossible.add(listePiecesNoires.get(i).getDeplacementPossible(this).get(j));
                }
            }
            for(i=0;i<listeDeTousLesCoupsPossible.size();i++){
                if(posRoi.equals(listeDeTousLesCoupsPossible.get(i))){
                    return true;
                }
            }
            return false;
        } return false; }

        public boolean estEchec(Position pos){
            int i; int j;
            if(getCase(pos)==null){
                return false;
            }
            if(getCase(pos).getCouleur()=='N') {
                ArrayList<Piece> listePiecesBlanches = new ArrayList<Piece>(this.getPiecesBlanches());
                ArrayList<Position> listeDeTousLesCoupsPossible = new ArrayList<Position>();
                for(i=0;i<listePiecesBlanches.size();i++){
                    for(j=0;j<listePiecesBlanches.get(i).getDeplacementPossible(this).size();j++){
                        listeDeTousLesCoupsPossible.add(listePiecesBlanches.get(i).getDeplacementPossible(this).get(j));
                    }
                }
                for(i=0;i<listeDeTousLesCoupsPossible.size();i++){
                    if(pos.equals(listeDeTousLesCoupsPossible.get(i))){
                        return true;
                    }
                }
                return false;
            }
            if(getCase(pos).getCouleur()=='B'){
                ArrayList<Piece> listePiecesNoires = new ArrayList<Piece>(this.getPiecesNoires());
                ArrayList<Position> listeDeTousLesCoupsPossible = new ArrayList<Position>();
                for(i=0;i<listePiecesNoires.size();i++){
                    for(j=0;j<listePiecesNoires.get(i).getDeplacementPossible(this).size();j++){
                        listeDeTousLesCoupsPossible.add(listePiecesNoires.get(i).getDeplacementPossible(this).get(j));
                    }
                }
                for(i=0;i<listeDeTousLesCoupsPossible.size();i++){
                    if(pos.equals(listeDeTousLesCoupsPossible.get(i))){
                        return true;
                    }
                }
                return false;
            } return false;
    }
}
