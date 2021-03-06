import MG2D.Fenetre;
import MG2D.Clavier;
import MG2D.Couleur;

import MG2D.geometrie.Carre;
import MG2D.geometrie.Point;
import MG2D.geometrie.Texte;
import MG2D.geometrie.Texture;



import java.awt.Font;

public class Snake_Eater {
	
    // Attributs //

    final static int largeur = 800;
    final static int hauteur = 600;
	
    static Fenetre f = new Fenetre ( "Snake Eater | 0 pomme", largeur, hauteur );
	
    //private static Clavier clavier = new Clavier ();
    private static Clavier clavier;
	
    // Géometrie //
	
    private static Texture background = new Texture ( "img/background.jpg", new Point ( 0, 0 ) );	
	
    private static Point a = new Point ( 400, 300 );
	
    private static Carre joueur = new Carre ( new Couleur ( 255, 200, 200 ), a, 10, true );
			
    // Game Over & Statistique //
	
    private static Font calibri = new Font("Calibri", Font.TYPE1_FONT, 40);
		
    private static Texte gameover = new Texte (
					       Couleur.ROUGE, 
					       new String ("Game Over !"),	
					       calibri,
					       f.getMilieu()
					       );
		
    private static Texte statistique = new Texte (
						  Couleur.ROUGE,
						  new String ("Vous avez mangé 0 pomme."),
						  calibri,
						  new Point ( f.getMilieu().getX(), f.getMilieu().getY() + 50 )
						  );
		
    private static Texte commentaire = new Texte (
						  Couleur.ROUGE,
						  new String (" "),
						  calibri,
						  new Point ( f.getMilieu().getX(), f.getMilieu().getY() + 100 )
						  );
		
    // Main //	
	
    public static void main ( String [] args ) {
		
	//f.addKeyListener ( clavier );
	clavier = f.getClavier();
	f.setBackground ( Couleur.NOIR );
		
	f.ajouter ( background );
		
	Serpent s = new Serpent ( f, joueur );

	Nourriture n = new Nourriture ( f );
		
	int vitesse = 100;
	int compteur = 0;
				
	while ( s.getJouer() ) {
			
	    try {
				
		Thread.sleep ( vitesse );
	    }
			
	    catch ( Exception e ) {
				
		System.out.println ( e );
	    }
			
	    s.mouvement ( clavier );
	    s.intersection ( n.getPomme() );
			
	    n.jeu();
			
	    if ( vitesse > 50 && ( s.getNb() - s.getNb() % 10 ) != compteur ) {
				
		vitesse -= 5;
		compteur = s.getNb() - s.getNb() % 10;
	    }
			
	    if ( s.getNb() < 2 )
		f.setTitle ("Snake Eater | " + s.getNb() + " pomme");
			
	    else
		f.setTitle ("Snake Eater | " + s.getNb() + " pommes");
			
	    f.rafraichir();
	}
		
	f.supprimer ( background );
		
	s.effacer();
	n.effacer();
		
	f.rafraichir();
		
	if ( s.getNb() < 2 )
	    statistique.setTexte ( "Vous avez mangé " + s.getNb() + " pomme.");
		
	else
	    statistique.setTexte ( "Vous avez mangé " + s.getNb() + " pommes.");
		
	f.ajouter ( gameover );
	f.ajouter ( statistique );
		
	if ( s.getNb() == 0 )
	    commentaire.setTexte ( "Sérieusement ?!");

	if ( s.getNb() == 1 )
	    commentaire.setTexte ( "Snake looser ;) !" );
		
	if ( s.getNb() >= 2 )
	    commentaire.setTexte ( "Pas mal, jeune Snakewan." );
		
	if ( s.getNb() >= 10 )
	    commentaire.setTexte ( "Tu veux qu'on se tire l'oreille ?" );
		
	if ( s.getNb() >= 50 )
	    commentaire.setTexte ( "Snake, tu va mourir !" );
		
	f.ajouter ( commentaire );
    }	
}
