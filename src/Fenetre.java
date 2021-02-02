
//CTRL + SHIFT + O pour générer les imports 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Fenetre extends JFrame {

	// LE MENU
	private JMenuBar menuBar = new JMenuBar();
	JMenu fichier = new JMenu("Fichier"), edition = new JMenu("Edition"), forme = new JMenu("Forme du pointeur"),
			couleur = new JMenu("Couleur du pointeur"), taille = new JMenu("Taille du pointeur");

	JMenuItem nouveau = new JMenuItem("Effacer"), quitter = new JMenuItem("Quitter"), rond = new JMenuItem("Rond"),
			carre = new JMenuItem("Carré"), arc = new JMenuItem("Arc"), triangle = new JMenuItem("Triangle"),
			etoile = new JMenuItem("Etoile"), bleu = new JMenuItem("Bleu"), rouge = new JMenuItem("Rouge"),
			vert = new JMenuItem("Vert"), jaune = new JMenuItem("Jaune"), orange = new JMenuItem("Orange"),
			rose = new JMenuItem("Rose"), violet = new JMenuItem("Violet"), gris = new JMenuItem("Gris"),
			noir = new JMenuItem("Noir"), gomme = new JMenuItem("Gomme"), tailleXS = new JMenuItem("XS"),
			tailleS = new JMenuItem("S"), tailleM = new JMenuItem("M"), tailleL = new JMenuItem("L"),
			tailleXL = new JMenuItem("XL"), tailleXXL = new JMenuItem("XXL");

	// LA BARRE D'OUTILS
	JToolBar toolBar = new JToolBar();
		
	JButton square = new JButton(new ImageIcon("images/carré.jpg")),
			circle = new JButton(new ImageIcon("images/rond.jpg")), arch = new JButton(new ImageIcon("images/arc.jpg")),
			triangleForm = new JButton(new ImageIcon("images/triangle.jpg")),
			star = new JButton(new ImageIcon("images/etoile.jpg")),
			red = new JButton(new ImageIcon("images/rouge.jpg")), green = new JButton(new ImageIcon("images/vert.jpg")),
			blue = new JButton(new ImageIcon("images/bleu.jpg")),
			yellow = new JButton(new ImageIcon("images/jaune.jpg")),
			mandarine = new JButton(new ImageIcon("images/orange.jpg")),
			pink = new JButton(new ImageIcon("images/rose.jpg")),
			magenta = new JButton(new ImageIcon("images/violet.jpg")),
			gray = new JButton(new ImageIcon("images/gris.jpg")), black = new JButton(new ImageIcon("images/noir.jpg")),
			eraser = new JButton(new ImageIcon("images/gomme2.png"));
				
	// Le  JSlider et son JLabel pour changer la taille du pinceau dans la barre d'outils
	private JSlider slide = new JSlider();
	private JLabel label = new JLabel("taille: 10");

	// LES ÉCOUTEURS
	private FormeListener fListener = new FormeListener();
	private CouleurListener cListener = new CouleurListener();
	private TailleListener tListener = new TailleListener();
	private SliderListener sListener = new SliderListener();

	// Notre zone de dessin
	private DrawPanel drawPanel = new DrawPanel();
		

	public Fenetre() {
		this.setTitle("Ardoise magique");
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On initialise le menu
		this.initMenu();
		// Idem pour la barre d'outils
		this.initToolBar();
		// On positionne notre zone de dessin
		this.getContentPane().add(drawPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	// Initialise le menu
	private void initMenu() {
		nouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawPanel.erase();
			}
		});

		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));

		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));

		fichier.add(nouveau);
		fichier.addSeparator();
		fichier.add(quitter);
		fichier.setMnemonic('F');

		carre.addActionListener(fListener);
		rond.addActionListener(fListener);
		arc.addActionListener(fListener);
		triangle.addActionListener(fListener);
		etoile.addActionListener(fListener);
		forme.add(rond);
		forme.add(carre);
		forme.add(arc);
		forme.add(triangle);
		forme.add(etoile);

		rouge.addActionListener(cListener);
		vert.addActionListener(cListener);
		bleu.addActionListener(cListener);
		bleu.addActionListener(cListener);
		jaune.addActionListener(cListener);
		orange.addActionListener(cListener);
		rose.addActionListener(cListener);
		violet.addActionListener(cListener);
		gris.addActionListener(cListener);
		noir.addActionListener(cListener);
		gomme.addActionListener(cListener);
		couleur.add(rouge);
		couleur.add(vert);
		couleur.add(bleu);
		couleur.add(jaune);
		couleur.add(orange);
		couleur.add(rose);
		couleur.add(violet);
		couleur.add(gris);
		couleur.add(noir);

		tailleXS.addActionListener(tListener);
		tailleS.addActionListener(tListener);
		tailleM.addActionListener(tListener);
		tailleL.addActionListener(tListener);
		tailleXL.addActionListener(tListener);
		tailleXXL.addActionListener(tListener);
		taille.add(tailleXS);
		taille.add(tailleS);
		taille.add(tailleM);
		taille.add(tailleL);
		taille.add(tailleXL);
		taille.add(tailleXXL);

		edition.setMnemonic('E');
		edition.add(forme);
		edition.addSeparator();
		edition.add(couleur);
		edition.addSeparator();
		edition.add(taille);
		edition.addSeparator();
		edition.add(gomme);

		menuBar.add(fichier);
		menuBar.add(edition);

		this.setJMenuBar(menuBar);
	}

	// Initialise la barre d'outils
	private void initToolBar() {

		JPanel panneau = new JPanel();
		square.addActionListener(fListener);
		circle.addActionListener(fListener);
		arch.addActionListener(fListener);
		triangleForm.addActionListener(fListener);
		star.addActionListener(fListener);
		red.addActionListener(cListener);
		green.addActionListener(cListener);
		blue.addActionListener(cListener);
		yellow.addActionListener(cListener);
		mandarine.addActionListener(cListener);
		pink.addActionListener(cListener);
		magenta.addActionListener(cListener);
		gray.addActionListener(cListener);
		black.addActionListener(cListener);
		eraser.addActionListener(cListener);
				
		slide.setMaximum(100);
		slide.setMinimum(0);
		slide.setValue(10);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);
		slide.setMinorTickSpacing(10);
		slide.setMajorTickSpacing(20);
		slide.addChangeListener(sListener);
		

		toolBar.add(square);
		toolBar.add(circle);
		toolBar.add(arch);
		toolBar.add(triangleForm);
		toolBar.add(star);

		toolBar.addSeparator();
		toolBar.add(red);
		toolBar.add(blue);
		toolBar.add(green);
		toolBar.add(yellow);
		toolBar.add(mandarine);
		toolBar.add(pink);
		toolBar.add(magenta);
		toolBar.add(gray);
		toolBar.add(black);
		toolBar.addSeparator();
		toolBar.add(eraser);

		toolBar.addSeparator();
		toolBar.add(slide);
		toolBar.addSeparator();
		toolBar.add(label);
		toolBar.addSeparator();

		this.getContentPane().add(toolBar, BorderLayout.NORTH);
	}

	// ÉCOUTEUR POUR LE CHANGEMENT DE FORME
	class FormeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().getClass().getName().equals("javax.swing.JMenuItem")) {
				if (e.getSource() == carre)
					drawPanel.setPointerType("SQUARE");
				else if (e.getSource() == arc)
					drawPanel.setPointerType("ARCH");
				else if (e.getSource() == triangle)
					drawPanel.setPointerType("TRIANGLE");
				else if (e.getSource() == etoile)
					drawPanel.setPointerType("STAR");
				else
					drawPanel.setPointerType("CIRCLE");
			} else {
				if (e.getSource() == square)
					drawPanel.setPointerType("SQUARE");
				else if (e.getSource() == arch)
					drawPanel.setPointerType("ARCH");
				else if (e.getSource() == triangleForm)
					drawPanel.setPointerType("TRIANGLE");
				else if (e.getSource() == star)
					drawPanel.setPointerType("STAR");
				else
					drawPanel.setPointerType("CIRCLE");
			}
		}
	}

	// ÉCOUTEUR POUR LE CHANGEMENT DE COULEUR
	class CouleurListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().getClass().getName().equals("javax.swing.JMenuItem")) {
				if (e.getSource() == vert)
					drawPanel.setPointerColor(Color.green);
				else if (e.getSource() == bleu)
					drawPanel.setPointerColor(Color.blue);
				else if (e.getSource() == jaune)
					drawPanel.setPointerColor(Color.yellow);
				else if (e.getSource() == orange)
					drawPanel.setPointerColor(Color.orange);
				else if (e.getSource() == rose)
					drawPanel.setPointerColor(Color.pink);
				else if (e.getSource() == violet)
					drawPanel.setPointerColor(Color.magenta);
				else if (e.getSource() == gris)
					drawPanel.setPointerColor(Color.gray);
				else if (e.getSource() == noir)
					drawPanel.setPointerColor(Color.black);
				else if (e.getSource() == gomme)
					drawPanel.setPointerColor(Color.white);
				else
					drawPanel.setPointerColor(Color.red);
			} else {
				if (e.getSource() == green)
					drawPanel.setPointerColor(Color.green);
				else if (e.getSource() == blue)
					drawPanel.setPointerColor(Color.blue);
				else if (e.getSource() == yellow)
					drawPanel.setPointerColor(Color.yellow);
				else if (e.getSource() == mandarine)
					drawPanel.setPointerColor(Color.orange);
				else if (e.getSource() == pink)
					drawPanel.setPointerColor(Color.pink);
				else if (e.getSource() == magenta)
					drawPanel.setPointerColor(Color.magenta);
				else if (e.getSource() == gray)
					drawPanel.setPointerColor(Color.gray);
				else if (e.getSource() == black)
					drawPanel.setPointerColor(Color.black);
				else if (e.getSource() == eraser)
					drawPanel.setPointerColor(Color.white);
				else
					drawPanel.setPointerColor(Color.red);
			}
		}
	}

//ÉCOUTEUR POUR LE CHANGEMENT DE TAILLE via le menu
	class TailleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().getClass().getName().equals("javax.swing.JMenuItem")) {
				if (e.getSource() == tailleXS){
					drawPanel.setPointerSize(2);
					label.setText("taille: 2");
					slide.setValue(2);
					}
				else if (e.getSource() == tailleS){
					drawPanel.setPointerSize(5);
					label.setText("taille: 5");
					slide.setValue(5);
					}
				else if (e.getSource() == tailleM){
					drawPanel.setPointerSize(10);
					label.setText("taille: 10");
					slide.setValue(10);
					}
				else if (e.getSource() == tailleL){
					drawPanel.setPointerSize(20);
					label.setText("taille: 20");
					slide.setValue(20);
					}
				else if (e.getSource() == tailleXL){
					drawPanel.setPointerSize(50);
					label.setText("taille: 50");
					slide.setValue(50);
					}
				else if (e.getSource() == tailleXXL){
					drawPanel.setPointerSize(100);
					label.setText("taille: 100");
					slide.setValue(100);
					}
			}
		}
	}
	
	//ÉCOUTEUR POUR LE CHANGEMENT DE TAILLE via le Slider de la barre d'outils
		class SliderListener implements ChangeListener {
			public void stateChanged(ChangeEvent event) {
				label.setText("taille: " + ((JSlider)event.getSource()).getValue());
				drawPanel.setPointerSize(((JSlider)event.getSource()).getValue());
			}
		}
}