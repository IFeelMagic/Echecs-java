public class Position {
    private int x;
    private int y;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(int x, int y) {
        if(x<8 && y<8){
            this.x = x;
            this.y = y;
        } else {
            System.out.println("Impossible, x et y doient etre compris entre 0 et 7.");
        }
    }

    public Position(Position p){
        this.x = p.getX();
        this.y = p.getY();
    }

    public Position(String texte){
        this.y = this.convertirStringEnPosition(texte, false);
        this.x = this.convertirStringEnPosition(texte, true);
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    public int convertirStringEnPosition(String texte, boolean coordXouY){
        String[] Coordonees = texte.split("");
        int i;
        if(coordXouY){
            String[] lettre = {"A","B","C","D","E","F","G","H"};
            for(i=0;i<lettre.length;i++){
                if (Coordonees[0].equals(lettre[i])) {
                    return i;
                }
            }
        } 
        else {
            int Y = Integer.parseInt(Coordonees[1]);
            return(Y - 1);
        }
        return -1;
    }

    public String convertirPosisionEnString(int x, int y){
        String[] lettre = {"A","B","C","D","E","F","G","H"};
        String X = lettre[x];
        String Y = String.valueOf(y+1);
        return(X+Y);
    }

    @Override
    public String toString() {
        return (convertirPosisionEnString(getX(), getY()));
    }

}