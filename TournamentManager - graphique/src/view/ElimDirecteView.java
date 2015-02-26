/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerElimDirecte;
import controller.ControllerTournoi;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import model.Equipe;
import model.Match;
import model.Tournoi;
import model.TournoiElimDirecte;

import javax.swing.JScrollBar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.awt.Font;
import java.awt.Color;

/**
 * @author Manon Gaillardot
 *
 */
public class ElimDirecteView extends JDialog {

	private ControllerTournoi controller;
	private Tournoi tournoi;
	
	private final JPanel contentPanel = new JPanel();
	private JPanel pMatchs;
	private JLabel lNumTour;
	private JLabel lChoisirMatch;
	private JLabel lScore1;
	private JTextField tfScore1;
	private JLabel lScore2;
	private JTextField tfScore2;
	private JPanel panel;
	private JLabel lNomE1;
	private JLabel lNomE2;
	private JLabel lVS;
	private JLabel lFinTournoi;
	private JTextArea taDescE1;
	private JTextArea taDescE2;
	private JTextArea taMatchsOK;
	private JLabel lMatchsTourOK;
	private JComboBox<Match> cbMatchs;
	private JTextArea taMatchsTour;
	private JButton bSuivant;
	private JButton bEnregistrer;
	private JButton bRecommencer;
	private JButton bStatistiques;
	private JButton bAnnuler;

	/**
	 * Create the dialog.
	 */
	public ElimDirecteView(Frame parent, boolean modal, Tournoi tournoi,
			ControllerTournoi controller) {
		super(parent, modal);
		this.controller = controller;
		this.tournoi = tournoi;

		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 654, 471);
		setLocationRelativeTo(null);
		setTitle("Tournoi "+tournoi.getNom());
		getContentPane().setLayout(null);

		pMatchs = new JPanel();
		pMatchs.setBounds(20, 24, 350, 277);
		getContentPane().add(pMatchs);
		pMatchs.setLayout(null);

		lChoisirMatch = new JLabel("Choisir un match (match nul impossible) :");
		lChoisirMatch.setBounds(20, 121, 309, 16);
		pMatchs.add(lChoisirMatch);

		lScore1 = new JLabel("Score e1");
		lScore1.setBounds(20, 186, 124, 16);
		pMatchs.add(lScore1);

		tfScore1 = new JTextField();
		tfScore1.setBounds(156, 186, 48, 21);
		pMatchs.add(tfScore1);
		tfScore1.setColumns(10);

		lScore2 = new JLabel("Score e2");
		lScore2.setBounds(20, 214, 124, 16);
		pMatchs.add(lScore2);

		tfScore2 = new JTextField();
		tfScore2.setColumns(10);
		tfScore2.setBounds(156, 211, 48, 21);
		pMatchs.add(tfScore2);

		bEnregistrer = new JButton("Enregistrer");
		bEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bEnregisterActionPerformed();
			}
		});
		bEnregistrer.setBounds(216, 198, 113, 32);
		pMatchs.add(bEnregistrer);

		lNumTour = new JLabel("Tour num");
		lNumTour.setBounds(142, 6, 48, 28);
		lNumTour.setText("Tour " + (tournoi.getNumTourActuel() + 1));
		pMatchs.add(lNumTour);

		cbMatchs = new JComboBox<Match>();
		for (Match m : tournoi.getTour()) {
			cbMatchs.addItem(m);
		}
		cbMatchs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMatchs.getItemCount() != 0) {
					Equipe e1 = cbMatchs.getItemAt(cbMatchs.getSelectedIndex())
							.getEquipe1();
					Equipe e2 = cbMatchs.getItemAt(cbMatchs.getSelectedIndex())
							.getEquipe2();
					lNomE1.setText(e1.getNom());
					taDescE1.setText(e1.getDescription());
					lScore1.setText("Score de " + e1.getNom());
					lNomE2.setText(e2.getNom());
					taDescE2.setText(e2.getDescription());
					lScore2.setText("Score de " + e2.getNom());
				}
			}
		});

		cbMatchs.setBounds(65, 140, 207, 34);
		pMatchs.add(cbMatchs);

		taMatchsTour = new JTextArea();
		taMatchsTour.setWrapStyleWord(true);
		taMatchsTour.setLineWrap(true);
		taMatchsTour.setEnabled(false);
		taMatchsTour.setBounds(17, 32, 298, 77);
		for (Match m : tournoi.getTour()) {
			if (taMatchsTour.getText().equals("")) {
				taMatchsTour.setText(m.toString());
			} else {
				taMatchsTour.setText(taMatchsTour.getText() + "\n"
						+ m.toString());
			}
		}
		pMatchs.add(taMatchsTour);

		bSuivant = new JButton("Passer au tour suivant");
		bSuivant.setVisible(false);
		bSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bSuivantActionPerformed(e);
			}
		});
		bSuivant.setBounds(82, 242, 182, 29);
		pMatchs.add(bSuivant);

		taMatchsOK = new JTextArea();
		taMatchsOK.setWrapStyleWord(true);
		taMatchsOK.setLineWrap(true);
		taMatchsOK.setEnabled(false);
		taMatchsOK.setText(" Tour " + (tournoi.getNumTourActuel() + 1));
		taMatchsOK.setBounds(42, 339, 302, 88);
		getContentPane().add(taMatchsOK);
		// JScrollPane scrollBar = new JScrollBar(taMatchsOK);

		// scrollBar.setBounds(299, 32, 15, 77);
		// pMatchs.add(scrollBar);

		panel = new JPanel();
		panel.setBounds(382, 24, 267, 245);
		getContentPane().add(panel);
		panel.setLayout(null);

		lNomE1 = new JLabel("Equipe1");
		lNomE1.setBounds(10, 6, 70, 22);
		panel.add(lNomE1);

		lNomE2 = new JLabel("Equipe2");
		lNomE2.setBounds(10, 134, 70, 22);
		panel.add(lNomE2);

		lVS = new JLabel("VS");
		lVS.setBounds(127, 110, 26, 22);
		panel.add(lVS);

		taDescE1 = new JTextArea();
		taDescE1.setLineWrap(true);
		taDescE1.setWrapStyleWord(true);
		taDescE1.setEnabled(false);
		taDescE1.setBounds(34, 32, 204, 66);
		panel.add(taDescE1);

		taDescE2 = new JTextArea();
		taDescE2.setWrapStyleWord(true);
		taDescE2.setLineWrap(true);
		taDescE2.setEnabled(false);
		taDescE2.setBounds(34, 157, 204, 66);
		panel.add(taDescE2);

		JScrollBar scrollBar_1 = new JScrollBar();
		scrollBar_1.setBounds(223, 32, 15, 66);
		panel.add(scrollBar_1);

		lMatchsTourOK = new JLabel("Matchs renseignés du tour :");
		lMatchsTourOK.setBounds(42, 313, 285, 22);
		getContentPane().add(lMatchsTourOK);

		bStatistiques = new JButton("Voir les statistiques");
		bStatistiques.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Equipe> meilAtta = controller
						.statsMeilleureAttaque(tournoi);
				ArrayList<Equipe> meilDef = controller
						.statsMeilleureDefense(tournoi);
				String attaque = "", defense = "";
				int i = 1, j = 1;
				for (Equipe e1 : meilAtta) {
					attaque += i + ". " + e1.getNom() + "\n";
					i++;
				}
				for (Equipe e2 : meilDef) {
					defense += j + ". " + e2.getNom() + "\n";
					j++;
				}
				JOptionPane.showMessageDialog(null,
						"     STATISTIQUES : \n\n\n Meilleure attaque :\n" + attaque
								+ "\n Meilleure défense :\n" + defense);

			}
		});
		bStatistiques.setBounds(425, 321, 199, 29);
		bStatistiques.setVisible(false);
		getContentPane().add(bStatistiques);

		bAnnuler = new JButton("Quitter");
		bAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bAnnuler.setBounds(512, 398, 117, 29);
		getContentPane().add(bAnnuler);

		lFinTournoi = new JLabel("Tournoi terminé");
		lFinTournoi.setForeground(Color.RED);
		lFinTournoi.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lFinTournoi.setBounds(441, 279, 166, 35);
		lFinTournoi.setVisible(false);
		getContentPane().add(lFinTournoi);

		bRecommencer = new JButton("Nouveau tournoi");
		bRecommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bRecommencerActionPerformed(e);
			}
		});
		bRecommencer.setBounds(425, 351, 199, 29);
		bRecommencer.setVisible(false);
		getContentPane().add(bRecommencer);

		JScrollBar scrollBar_2 = new JScrollBar();
		scrollBar_2.setBounds(307, 339, 15, 88);
		getContentPane().add(scrollBar_2);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		initChamps();

		setVisible(true);
	}

	public void bEnregisterActionPerformed() {
		Pattern pattern = Pattern
				.compile("^([0-9]|[1-9][0-9]|1[0-9][0-9]|200)$");
		// si format des scores correct
		if (pattern.matcher(tfScore1.getText()).find()
				&& pattern.matcher(tfScore2.getText()).find()) {
			// si non egalite
			if (!tfScore1.getText().equals(tfScore2.getText())) {
				// on selectionne le match de la combobox
				Match m = (Match) cbMatchs.getSelectedItem();
				// on enregistre les scores
				m.setScore1(Integer.parseInt(tfScore1.getText()));
				m.setScore2(Integer.parseInt(tfScore2.getText()));
				taMatchsOK.setText(taMatchsOK.getText() + "\n" + m.toString()
						+ " : " + m.getScore1() + " - " + m.getScore2());
				finMatch();
			} else {
				JOptionPane.showMessageDialog(null, "Egalité impossible.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Saisir des nombres (<200).");
		}
		tfScore1.setText("");
		tfScore2.setText("");
	}

	public void bSuivantActionPerformed(ActionEvent e) {
		// si fin du tournoi
		if (controller.finTournoiED(tournoi)) {
			lFinTournoi.setVisible(true);
			bStatistiques.setVisible(true);
			bRecommencer.setVisible(true);
			bEnregistrer.setVisible(false);
		} else {// sinon
			// affiche tour suivant
			afficherTour();
		}
		bSuivant.setVisible(false);
	}

	public void finMatch() {
		// actualise la CB
		cbMatchs.removeAllItems();
		for (Match m : tournoi.getTour()) {
			if (m.getVainqueur() == null) {
				cbMatchs.addItem(m);
			}
		}
		// si tour fini
		if (cbMatchs.getItemCount() == 0) {
			// on passe au tour suivant
			controller.passeTourSuivantED(tournoi);
			bSuivant.setVisible(true);
		}
	}

	public void afficherTour() {
		taMatchsOK.setText(taMatchsOK.getText() + "\n\n Tour "
				+ (tournoi.getNumTourActuel() + 1) + "\n");
		taMatchsTour.setText("");
		lNumTour.setText("Tour " + (tournoi.getNumTourActuel() + 1));
		for (Match m : tournoi.getTour()) {
			cbMatchs.addItem(m);
			if (taMatchsTour.getText().equals("")) {
				taMatchsTour.setText(m.toString());
			} else {
				taMatchsTour.setText(taMatchsTour.getText() + "\n"
						+ m.toString());
			}
		}
	}

	public void initChamps() {
		Equipe e1 = cbMatchs.getItemAt(cbMatchs.getSelectedIndex())
				.getEquipe1();
		Equipe e2 = cbMatchs.getItemAt(cbMatchs.getSelectedIndex())
				.getEquipe2();
		lNomE1.setText(e1.getNom());
		taDescE1.setText(e1.getDescription());
		lScore1.setText("Score de " + e1.getNom());
		lNomE2.setText(e2.getNom());
		taDescE2.setText(e2.getDescription());
		lScore2.setText("Score de " + e2.getNom());
	}
	
	public void bRecommencerActionPerformed(ActionEvent e){
		this.dispose();
	}
}
