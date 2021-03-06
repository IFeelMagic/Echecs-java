import MG2D.Fenetre;
import MG2D.Clavier;
import MG2D.Souris;
import MG2D.Couleur;

import MG2D.geometrie.Cercle;
import MG2D.geometrie.Point;
import MG2D.geometrie.Rectangle;
import MG2D.geometrie.Texture;
import MG2D.geometrie.Texte;
import java.awt.Font;


import MG2D.audio.*;




public class Pong {
    //CONSTANTES
    private int largeur = 700;
    private int hauteur = 400;
    private final int epaisseurLigne = 5;
    private final int rayonBalle = 10;
    private final int vitesseRaquette = 6;
    private final int vitesseBalleInitiale = 4;

    //ATTRIBUTS
    private int vitesseBalle;
    
    private Fenetre f;
    private Clavier clavier;
    private Souris souris;

    private Point centreBalle;
    private Cercle balle;

    private Rectangle limiteGauche, limiteDroite, limiteHaute, limiteBasse;

    private boolean demarrer;

    private int dx,dy;

    private int scoreG, scoreD;

    private Point posRaqGH, posRaqGB, posRaqDH, posRaqDB;

    private Rectangle raqG,raqD;

    private int tailleRaquette;

    private Texture textureScoreG, textureScoreD;

    private int nbRebond;

    private Musique m;

    //CONSTRUCTEUR
    public Pong(){
	
	f = new Fenetre ( "Mon premier Pong", largeur, hauteur );

	clavier = f.getClavier();
	souris = f.getSouris();
       
	f.ajouter ( new Rectangle(Couleur.NOIR, new Point(0,0), new Point(largeur, hauteur), true) );

	limiteGauche = new Rectangle (Couleur.ROUGE, new Point(0,0), new Point(epaisseurLigne,hauteur), true);
	f.ajouter(limiteGauche);
	limiteDroite = new Rectangle (Couleur.ROUGE, new Point(largeur-epaisseurLigne,0), new Point(largeur,hauteur), true);
	f.ajouter(limiteDroite);

	limiteBasse = new Rectangle (Couleur.BLANC, new Point(0,0), new Point(largeur,epaisseurLigne), true);
	f.ajouter(limiteBasse);
	limiteHaute = new Rectangle (Couleur.BLANC, new Point(0,hauteur-epaisseurLigne), new Point(largeur,hauteur), true);
	f.ajouter(limiteHaute);
	
	Rectangle filet = new Rectangle (Couleur.BLANC, new Point(largeur/2-epaisseurLigne/2,0), new Point(largeur/2+epaisseurLigne/2,hauteur), true);
	f.ajouter(filet);

	centreBalle = new Point(largeur/2,hauteur/2);
	balle = new Cercle (Couleur.JAUNE, centreBalle, rayonBalle , true);
	f.ajouter(balle);

	tailleRaquette=hauteur/10;

	posRaqGH=new Point(epaisseurLigne*2,hauteur/2-tailleRaquette/2);
	posRaqGB=new Point(epaisseurLigne*3,hauteur/2+tailleRaquette/2);
	raqG=new Rectangle(Couleur.BLANC,posRaqGH,posRaqGB, false);
	f.ajouter(raqG);

	posRaqDH=new Point(largeur-3*epaisseurLigne,hauteur/2-tailleRaquette/2);
	posRaqDB=new Point(largeur-2*epaisseurLigne,hauteur/2+tailleRaquette/2);
	raqD=new Rectangle(Couleur.BLANC,posRaqDH,posRaqDB, false);
	f.ajouter(raqD);

	textureScoreG = new Texture("img/0.png", new Point(largeur/4,hauteur-50));
	textureScoreD = new Texture("img/0.png", new Point(3*largeur/4,hauteur-50));
	textureScoreG.setA(new Point(textureScoreG.getA().getX(),hauteur-epaisseurLigne-textureScoreG.getHauteur()));
	
	textureScoreD.setA(new Point(textureScoreD.getA().getX(),hauteur-epaisseurLigne-textureScoreD.getHauteur()));
	
	f.ajouter(textureScoreG);
	f.ajouter(textureScoreD);

	scoreG=0;
	scoreD=0;

	init();

	m=new Musique("Tied_Up.mp3");
	m.lecture();

    }

    //INITIALISATION D'UNE PARTIE
    public void init(){
	demarrer=false;
	balle.setO(new Point(largeur/2,(int)((Math.random()*(hauteur*0.5))+hauteur/4)));
	dx=0;
	dy=0;
	nbRebond=0;
	vitesseBalle=vitesseBalleInitiale;
	textureScoreG.setImg("img/"+scoreG+".png");
	textureScoreD.setImg("img/"+scoreD+".png");
	
	if(scoreG==9){
	    Texte t=new Texte(Couleur.ROUGE,"Le joueur de gauche a gagn?? !!!",new Font("Calibri", Font.TYPE1_FONT, 40),new Point(largeur/2,hauteur/2));
	    f.ajouter(t);
	}
	if(scoreD==9){
	    Texte t=new Texte(Couleur.ROUGE,"Le joueur de droite a gagn?? !!!",new Font("Calibri", Font.TYPE1_FONT, 40),new Point(largeur/2,hauteur/2));
	    f.ajouter(t);
	}
	f.rafraichir();
    }

    //UN PAS DU JEU
    public void maj(){

	if(nbRebond==10){
	    vitesseBalle+=2;
	    nbRebond=0;
	}

	if(!demarrer && clavier.getEspaceTape() && scoreG<9 && scoreD<9){
	    demarrer=true;
	    dx=1;dy=1;
	}

	if(clavier.getSEnfoncee()){
	    if(!raqG.intersectionRapide(limiteBasse)){
		raqG.setA(new Point(raqG.getA().getX(),raqG.getA().getY()-vitesseRaquette));
		raqG.setB(new Point(raqG.getB().getX(),raqG.getB().getY()-vitesseRaquette));
	    }
	}

	if(clavier.getZEnfoncee()){
	    if(!raqG.intersectionRapide(limiteHaute)){
		raqG.setA(new Point(raqG.getA().getX(),raqG.getA().getY()+vitesseRaquette));
		raqG.setB(new Point(raqG.getB().getX(),raqG.getB().getY()+vitesseRaquette));
	    }
	}

	raqD.translater(0,-raqD.getB().getY()+souris.getPosition().getY()+tailleRaquette/2);
    
	if(balle.intersectionRapide(limiteGauche)){
	    scoreD++;
	    init();
	}
	if(balle.intersectionRapide(limiteDroite)){
	    scoreG++;
	    init();
	}
	if(balle.intersectionRapide(raqG) || balle.intersectionRapide(raqD)){
	    dx*=-1;nbRebond++;
	    Bruitage b=new Bruitage("bip.mp3");
	    b.lecture();
	}
	if(balle.intersectionRapide(limiteBasse))
	    dy=1;
	if(balle.intersectionRapide(limiteHaute))
	    dy=-1;

	balle.translater(dx*vitesseBalle,dy*vitesseBalle);
	
	f.rafraichir();
    }	
}
