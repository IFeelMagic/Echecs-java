import java.util.ArrayList;

public class PionNoir extends Pion {

    public PionNoir(){
        this.setCouleur('N');
        this.setPosition(new Position("A8"));
    }

    public PionNoir(Position P){
        this.setCouleur('N');
        this.setPosition(P);
    }

    @Override
    public ArrayList<Position> getDeplacementPossible(Plateau plateau) {
        // Méthode qui retourne une liste de toutes les positions où un pion noir peut aller
        ArrayList<Position> Liste_pos_possible = new ArrayList<Position>();
        Position actuelle = this.getPosition();
        int i=1; boolean cond_fin = false; int max=1;
        if(actuelle.getY()-1 < 0){
            cond_fin = true;
        }
        if(actuelle.getY()==6){
            max=2;
        }
        while(cond_fin==false){
            int case_suivante = actuelle.getY()-i;
            if(plateau.getCase(actuelle.getX(),case_suivante)!=null){
                break;
            }
            if(case_suivante >= 0 && plateau.getCase(actuelle.getX(), case_suivante)==null){
                Liste_pos_possible.add(new Position(actuelle.getX(), case_suivante));
            }
            if(case_suivante-1<0 || i==max){
                cond_fin=true;
            }
            i++;
        }
        // Si il peut prendre
        if(plateau.getCase(actuelle.getX()-1, actuelle.getY()-1)!=null){
            if(plateau.getCase(actuelle.getX()-1, actuelle.getY()-1).getCouleur()!=this.getCouleur()){
               Liste_pos_possible.add(new Position(actuelle.getX()-1, actuelle.getY()-1));
            }
        }
        //
        if(plateau.getCase(actuelle.getX()+1, actuelle.getY()-1)!=null){
            if(plateau.getCase(actuelle.getX()+1, actuelle.getY()-1).getCouleur()!=this.getCouleur()){
               Liste_pos_possible.add(new Position(actuelle.getX()+1, actuelle.getY()-1));
            }
        }
        return Liste_pos_possible;
    }
}
