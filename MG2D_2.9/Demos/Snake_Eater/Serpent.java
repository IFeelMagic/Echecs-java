import MG2D.Fenetre;
import MG2D.Clavier;
import MG2D.Couleur;

import MG2D.geometrie.Carre;
import MG2D.geometrie.Point;




import java.util.ArrayList;

public class Serpent {
	
    // Attributs //
	
    private ArrayList < Carre > serpent = new ArrayList < Carre > ();
    private Carre tete;
	
    private Fenetre f;
	
    private static int x = 1;
    private static int y = 0;
	
    private boolean jouer;
	
    private int nb;
		
    // Constructeur //
	
    public Serpent ( Fenetre f, Carre c ) {
		
	this.f = f;
		
	nb = 0;
		
	jouer = true;
		
	serpent.add ( c );
	tete = c;
		
	Carre corps_1 = new Carre ( Couleur.BLANC, new Point ( tete.getA().getX() - 10, tete.getA().getY() ), 10, true );
	serpent.add ( corps_1 );
		
	Carre corps_2 = new Carre ( Couleur.BLANC, new Point ( tete.getA().getX() - 20, tete.getA().getY() ), 10, true );
	serpent.add ( corps_2 );
		
	f.ajouter ( serpent.get( 0 ) );
	f.ajouter ( serpent.get( 1 ) );
	f.ajouter ( serpent.get( 2 ) );
    }
	
    // Accesseurs //
	
    // Getter //
	
    public Carre get ( int i ) {
		
	return serpent.get ( i );
    }
	
    public boolean getJouer () {
		
	return jouer;
    }
	
    public int getNb () {
		
	return nb;
    }
	
    // Setter //
	
    public void setJouer ( boolean jouer ) {
		
	this.jouer = jouer;
    }
	
    // Méthodes //
	
    // private //
	
    private void avaler ( Carre c ) {
		
	serpent.add ( c );
				
	c.setA(new Point(serpent.get(serpent.size() - 1).getA().getX(),serpent.get(serpent.size() - 1).getA().getY()));
	//TODO Comprendre ce qu'il se passe quand on commente la ligne suivante
	c.setB(new Point(serpent.get(serpent.size() - 1).getB().getX(),serpent.get(serpent.size() - 1).getB().getY()));
		
	nb++;
    }
	
    // Public
	
    public void intersection ( ArrayList < Pomme > a ) {
		
	// Collision contre les murs //
	if ( tete.getA().getX() == 0 ||
	     tete.getA().getY() == 0 ||
	     tete.getB().getX() == f.getP().getWidth() ||
	     tete.getB().getY() == f.getP().getHeight()
	     )
	    jouer = false;
		
	// Le serpent se mort la queue //
		
	for ( int i = 1; i < serpent.size(); i++ ) {
			
	    if ( ( serpent.get( i ).getA().getX() < tete.getB().getX() && serpent.get( i ).getA().getY() < tete.getB().getY() ) &&
		 ( serpent.get( i ).getB().getX() > tete.getA().getX() && serpent.get( i ).getB().getY() > tete.getA().getY() )
		 )
		jouer = false;
	}
		
	// Collision contre un fruit //

	for ( int i = 0; i < a.size(); i++ ) {
			
	    if ( ( a.get( i ).getC().getA().getX() < tete.getB().getX() && a.get( i ).getC().getA().getY() < tete.getB().getY() ) &&
		 ( a.get( i ).getC().getB().getX() > tete.getA().getX() && a.get( i ).getC().getB().getY() > tete.getA().getY() )
		 ) {
				
		this.avaler ( a.get(i).getC() );
				
		a.get( i ).setEtat( true );
		a.get( i ).getC().setCouleur( Couleur.BLANC );
	    }
	}
    }
		
    public void mouvement ( Clavier c ) {
		
	if ( c.getGauche() ) {
			
	    x = -1;
	    y = 0;
	}
		
	if ( c.getDroite() ) {
			
	    x = 1;
	    y = 0;
	}
		
	if ( c.getHaut() ) {
			
	    x = 0;
	    y = 1;
	}
		
	if ( c.getBas() ) {
			
	    x = 0;
	    y = -1;
	}
		
	// Mouvement du corps //
		
	for ( int i = serpent.size() - 1; i > 0; i-- ) {
	    serpent.get(i).translater(serpent.get(i-1).getA().getX()-serpent.get(i).getA().getX(),serpent.get(i-1).getA().getY()-serpent.get(i).getA().getY());
	}

	// Mouvement de la tête //

	tete.translater(10*x,10*y);
    }
	
    public void effacer () {
		
	for ( int i = 0; i < serpent.size(); i++ )
	    f.supprimer ( serpent.get(i) );
    }
}
