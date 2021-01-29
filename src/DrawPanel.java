// CTRL + SHIFT + O pour générer les imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
 
public class DrawPanel extends JPanel{

  //Couleur du pointeur
  private Color pointerColor = Color.red;
  //Forme du pointeur
  private String pointerType = "CIRCLE";
  //Position X du pointeur
  private int posX = -10, oldX = -10;
  //Position Y du pointeur
  private int posY = -10, oldY = -10;
  //Pour savoir si on doit dessiner ou non
  private boolean erasing = true;
  //Taille du pointeur
  private int pointerSize = 10;
  //Collection de points ! 
  private ArrayList<Point> points = new ArrayList<Point>();  

  public DrawPanel(){

    this.addMouseListener(new MouseAdapter(){
      public void mousePressed(MouseEvent e){
        points.add(new Point(e.getX() - (pointerSize / 2), e.getY() - (pointerSize / 2), pointerSize, pointerColor, pointerType));
        repaint();
      }
    });

    this.addMouseMotionListener(new MouseMotionListener(){
      public void mouseDragged(MouseEvent e) {
        //On récupère les coordonnées de la souris et on enlève la moitié de la taille du pointeur pour centrer le tracé
        points.add(new Point(e.getX() - (pointerSize / 2), e.getY() - (pointerSize / 2), pointerSize, pointerColor, pointerType));
        repaint();
      }

      public void mouseMoved(MouseEvent e) {}
    });

  }

  // Vous la connaissez maintenant, celle-là
  public void paintComponent(Graphics g) {

    g.setColor(Color.white);
    g.fillRect(0, 0, this.getWidth(), this.getHeight());

    //Si on doit effacer, on ne passe pas dans le else => pas de dessin
    if(this.erasing){
      this.erasing = false;
    }
    else{
      //On parcourt notre collection de points
      for(Point p : this.points)
      {
        //On récupère la couleur
        g.setColor(p.getColor());

        //Selon le type de point
        if(p.getType().equals("SQUARE")){
          g.fillRect(p.getX(), p.getY(), p.getSize(), p.getSize());
        }
        else if(p.getType().equals("ARCH")){
        	g.fillArc(p.getX()-p.getSize(), p.getY(), p.getSize()*2, p.getSize()*2, 0, 90);
        }
        else if(p.getType().equals("TRIANGLE")){
        	int s1X = p.getX() + p.getSize()/2;
            int s1Y = p.getY();
            int s2X = p.getX() + p.getSize();
            int s2Y = p.getY() + p.getSize();
            int s3X = p.getX();
            int s3Y = p.getY() + p.getSize();      
            int[] ptsX = {s1X, s2X, s3X};
            int[] ptsY = {s1Y, s2Y, s3Y};
        	g.fillPolygon(ptsX, ptsY, 3);
        }
        else if(p.getType().equals("STAR")){
        	//1er triangle
        	int s1X = p.getX() + p.getSize()/2;
            int s1Y = p.getY();
            int s2X = p.getX() + p.getSize();
            int s2Y = p.getY() + p.getSize();
            int s3X = p.getX();
            int s3Y = p.getY() + p.getSize();      
            int[] ptsX = {s1X, s2X, s3X};
            int[] ptsY = {s1Y, s2Y, s3Y};
        	
        	//2nd triangle inversé 
            int s4X = p.getX();
            int s4Y = p.getY() + p.getSize()/3;
            int s5X = p.getX() + p.getSize();
            int s5Y = p.getY() + p.getSize()/3;
            int s6X = p.getX() + p.getSize()/2;
            int s6Y = p.getY() + (p.getSize() + p.getSize()/4); 
            int[] ptsX2 = {s4X, s5X, s6X};
            int[] ptsY2 = {s4Y, s5Y, s6Y};
            
            //dessin des 2 triangles emboités formant une étoile à 5 branches
            g.fillPolygon(ptsX, ptsY, 3);
        	g.fillPolygon(ptsX2, ptsY2, 3);
        }
        else{
          g.fillOval(p.getX(), p.getY(), p.getSize(), p.getSize());
        }
      }
    }        
  }

  //Efface le contenu
  public void erase(){
    this.erasing = true;
    this.points = new ArrayList<Point>();
    repaint();
  }

  //Définit la couleur du pointeur
  public void setPointerColor(Color c){
    this.pointerColor = c;
  }

  //Définit la forme du pointeur
  public void setPointerType(String str){
    this.pointerType = str;
  }

  //Définit la taille du pointer
  public void setPointerSize(int taille){
	    this.pointerSize = taille;
	  }
}