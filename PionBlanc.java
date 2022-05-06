import java.util.ArrayList;

public class PionBlanc extends Pion {

    public PionBlanc(){
        this.setCouleur('B');
        this.setPosition(new Position("A1"));
    }

    public PionBlanc(Position P){
        this.setCouleur('B');
        this.setPosition(P);
    }
    public PionBlanc(int x, int y){
        this.setCouleur('B');
        this.setPosition(new Position(x, y));
    }

    @Override
    public ArrayList<Position> getDeplacementPossible(Plateau plateau) {
        // Méthode qui retourne une liste de toutes les positions où un pion blanc peut aller

        ArrayList<Position> Liste_pos_possible = new ArrayList<Position>();
        Position actuelle = this.getPosition();
        int i=1; boolean cond_fin = false; int max=1;
        if(actuelle.getY()+1 > 7){
            cond_fin = true;
        }
        if(actuelle.getY()==1){
            max=2;
        }
        while(cond_fin==false){
            int case_suivante = actuelle.getY()+i;
            if(plateau.getCase(actuelle.getX(),case_suivante)!=null){
                break;
            }
            if(case_suivante >= 0 && plateau.getCase(actuelle.getX(), case_suivante)==null){
                Liste_pos_possible.add(new Position(actuelle.getX(), case_suivante));
            }
            if(case_suivante+1>7 || i==max){
                cond_fin=true;
            }
            i++;
        }
        // Si il peut prendre
        if(plateau.getCase(actuelle.getX()-1, actuelle.getY()+1)!=null){
            if(plateau.getCase(actuelle.getX()-1, actuelle.getY()+1).getCouleur()!=this.getCouleur()){
               Liste_pos_possible.add(new Position(actuelle.getX()-1, actuelle.getY()+1));
            }
        }
        //
        if(plateau.getCase(actuelle.getX()+1, actuelle.getY()+1)!=null){
            if(plateau.getCase(actuelle.getX()+1, actuelle.getY()+1).getCouleur()!=this.getCouleur()){
               Liste_pos_possible.add(new Position(actuelle.getX()+1, actuelle.getY()+1));
            }
        }
        return Liste_pos_possible;
    }
}
