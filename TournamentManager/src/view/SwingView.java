/**
 * 
 */
package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import model.Tournoi;
import model.TournoiPoule;
import controller.ControllerElimDirecte;
import controller.ControllerPoule;
import controller.ControllerTournoi;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Manon Gaillardot et Willian Lanners
 *
 */
@SuppressWarnings("serial")
public class SwingView extends JFrame implements ViewInterface{

	NouveauTournoi dialogNouveauTournoi;
	ModifierEquipes dialogModifEq;
	ElimDirecteView dialogED;
	PouleView dialogPoule;
	private JPanel contentPane;
	JPanel pTypeTournoi;
	private JButton bQuitter;
	private JButton bCreer;
	private JLabel lTitre;
	private JButton bTournoiED;

	/**
	 * Create the frame.
	 */
	public SwingView() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Gestionnaire de tournoi");
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		bQuitter = new JButton("Quitter");
		bQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bQuitter.setBounds(309, 212, 117, 29);
		contentPane.add(bQuitter);

		bCreer = new JButton("Créer un nouveau tournoi");
		bCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bCreerActionPerformed(e);

			}
		});
		bCreer.setBounds(143, 83, 177, 40);
		contentPane.add(bCreer);

		lTitre = new JLabel("Bienvenue dans le gestionnaire de tournoi.");
		lTitre.setBounds(93, 23, 279, 29);
		contentPane.add(lTitre);

		pTypeTournoi = new JPanel();
		pTypeTournoi.setBounds(51, 135, 355, 65);
		contentPane.add(pTypeTournoi);
		pTypeTournoi.setVisible(false);
		pTypeTournoi.setLayout(null);

		bTournoiED = new JButton("Tournoi ED");
		bTournoiED.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bTournoiEDActionPerformed();
			}
		});
		bTournoiED.setBounds(20, 18, 117, 29);
		pTypeTournoi.add(bTournoiED);

		JButton bTournoiPoules = new JButton("Tournoi poules");
		bTournoiPoules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bTournoiPoulesActionPerformed();
			}
		});
		bTournoiPoules.setBounds(194, 18, 139, 29);
		pTypeTournoi.add(bTournoiPoules);

		setVisible(true);
		
	}

	public void bTournoiEDActionPerformed() {
		this.setVisible(false);
		ControllerElimDirecte cED = new ControllerElimDirecte(null, this);
		cED.start();
		pTypeTournoi.setVisible(false);
		this.setVisible(true);
	}

	public void bTournoiPoulesActionPerformed() {
		this.setVisible(false);
		ControllerPoule cP = new ControllerPoule(null, this);
		cP.start();
		pTypeTournoi.setVisible(false);
		this.setVisible(true);
	}

	public void bCreerActionPerformed(ActionEvent evt) {
		pTypeTournoi.setVisible(true);
	}

	public void creationTournoi(Tournoi tournoi) {
		dialogNouveauTournoi = new NouveauTournoi(this, true, tournoi);
	}

	public void modifierEquipes(Tournoi tournoi) {
		dialogModifEq = new ModifierEquipes(this, true, tournoi);
	}
	
	public void deroulementElimDirecte(Tournoi tournoi, ControllerTournoi controller){
		dialogED = new ElimDirecteView(this, true, tournoi, controller);
	}
	
	public void deroulementPoule(TournoiPoule tournoi, ControllerPoule controller){
		dialogPoule = new PouleView(this, true, tournoi, controller);
		controller.lancementPhaseFinale(tournoi);
		dialogED = new ElimDirecteView(this, true, tournoi, controller);
	}
}
