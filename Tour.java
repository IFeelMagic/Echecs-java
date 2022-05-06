import java.util.ArrayList;

public class Tour extends Piece {

    public Tour(){
        this.setCouleur('B');
        this.setPosition(new Position("A1"));
    }

    public Tour(char couleur, Position p){
        this.setCouleur(couleur);
        this.setPosition(p);
    }

    public Tour(char couleur, int i, int b){
        this.setCouleur(couleur);
        this.setPosition(new Position(i, b));
    }

    public String getType() {
        return "tour";
    }

    @Override
    public ArrayList<Position> getDeplacementPossible(Plateau plateau) {
        // Méthode qui retourne une liste de toutes les positions où une tour peut aller
        boolean cond_fin = false; boolean col = false;
        int i=1;
        Position actuelle = this.getPosition();
        ArrayList<Position> Liste_pos_possible = new ArrayList<Position>();
        //droite
        if(actuelle.getX()+1 > 7){
            cond_fin = true;
        }
        while(cond_fin==false){
            int case_suivante = actuelle.getX()+i;
            if(case_suivante < 8 && plateau.getCase(case_suivante,this.getPosition().getY())==null){
                Liste_pos_possible.add(new Position(case_suivante, actuelle.getY()));
            }
            if(plateau.getCase(case_suivante,this.getPosition().getY())!=null){
                if(plateau.getCase(case_suivante, actuelle.getY()).getCouleur()==this.getCouleur()){
                    col=true;
                }
                if(plateau.getCase(case_suivante,this.getPosition().getY()).getCouleur()!=this.getCouleur()){
                   Liste_pos_possible.add(new Position(case_suivante, actuelle.getY()));
                   col=true;
                }
            }
            i++;
            if(case_suivante+1>=8 || col==true){
                cond_fin=true;
            }
        }
        //gauche
        i=1; cond_fin = false; col = false;
        if(actuelle.getX()-1 < 0){
            cond_fin = true;
        }
        while(cond_fin==false){
            int case_suivante = actuelle.getX()-i;
            if(case_suivante >= 0 && plateau.getCase(case_suivante,this.getPosition().getY())==null){
                Liste_pos_possible.add(new Position(case_suivante, actuelle.getY()));
            }
            if(plateau.getCase(case_suivante,this.getPosition().getY())!=null){
                if(plateau.getCase(case_suivante, actuelle.getY()).getCouleur()==this.getCouleur()){
                    col=true;
                }
                if(plateau.getCase(case_suivante,this.getPosition().getY()).getCouleur()!=this.getCouleur()){
                   Liste_pos_possible.add(new Position(case_suivante, actuelle.getY()));
                   col=true;
                }
            }
            i++;
            if(case_suivante-1<0 || col==true){
                cond_fin=true;
            }
        }
        //bas
        i=1; cond_fin = false; col = false;
        if(actuelle.getY()-1 < 0){
            cond_fin = true;
        }
        while(cond_fin==false){
            int case_suivante = actuelle.getY()-i;
            if(case_suivante >= 0 && plateau.getCase(actuelle.getX(), case_suivante)==null){
                Liste_pos_possible.add(new Position(actuelle.getX(), case_suivante));
            }
            if(plateau.getCase(actuelle.getX(), case_suivante)!=null){
                if(plateau.getCase(actuelle.getX(), case_suivante).getCouleur()==this.getCouleur()){
                    col=true;
                }
                if(plateau.getCase(actuelle.getX(), case_suivante).getCouleur()!=this.getCouleur()){
                   Liste_pos_possible.add(new Position(actuelle.getX(), case_suivante));
                   col=true;
                }
            }
            i++;
            if(case_suivante-1<0 || col==true){
                cond_fin=true;
            }
        }
        //haut
        i=1; cond_fin = false; col = false;
        if(actuelle.getY()+1 > 7){
            cond_fin = true;
        }
        while(cond_fin==false){
            int case_suivante = actuelle.getY()+i;
            if(case_suivante < 8 && plateau.getCase(actuelle.getX(), case_suivante)==null){
                Liste_pos_possible.add(new Position(actuelle.getX(), case_suivante));
            }
            if(plateau.getCase(actuelle.getX(), case_suivante)!=null){
                if(plateau.getCase(actuelle.getX(), case_suivante).getCouleur()==this.getCouleur()){
                    col=true;
                }
                if(plateau.getCase(actuelle.getX(), case_suivante).getCouleur()!=this.getCouleur()){
                   Liste_pos_possible.add(new Position(actuelle.getX(), case_suivante));
                   col=true;
                }
            }
            i++;
            if(case_suivante+1<0 || col==true){
                cond_fin=true;
            }
        }
        return Liste_pos_possible;
    }
    
    public static void main(String[] args) {
        Plateau plateauJeu = new Plateau();
        Tour t1 = new Tour('B',new Position("A4"));
        System.out.println(t1);
        System.out.println(t1.getDeplacementPossible(plateauJeu));
    }
}
