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
import java.awt.Color;
import java.awt.Font;

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
		setBounds(100, 100, 450, 265);
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
		bQuitter.setBounds(301, 197, 116, 29);
		contentPane.add(bQuitter);

		bCreer = new JButton("Créer un nouveau tournoi");
		bCreer.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		bCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bCreerActionPerformed(e);

			}
		});
		bCreer.setBounds(114, 72, 202, 40);
		contentPane.add(bCreer);

		lTitre = new JLabel("GESTIONNAIRE DE TOURNOI");
		lTitre.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		lTitre.setForeground(new Color(51, 102, 204));
		lTitre.setBounds(44, 20, 362, 40);
		contentPane.add(lTitre);

		pTypeTournoi = new JPanel();
		pTypeTournoi.setBounds(6, 124, 438, 49);
		contentPane.add(pTypeTournoi);
		pTypeTournoi.setVisible(false);
		pTypeTournoi.setLayout(null);

		bTournoiED = new JButton("Tournoi à élimination directe");
		bTournoiED.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		bTournoiED.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bTournoiEDActionPerformed();
			}
		});
		bTournoiED.setBounds(6, 6, 201, 34);
		pTypeTournoi.add(bTournoiED);

		JButton bTournoiPoules = new JButton("Tournoi à phase de poule/finale");
		bTournoiPoules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bTournoiPoulesActionPerformed();
			}
		});
		bTournoiPoules.setBounds(216, 6, 216, 34);
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
