/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import model.DynamicModelTable;
import model.Equipe;
import model.Match;
import model.Poule;
import model.Tournoi;
import model.TournoiPoule;
import controller.ControllerElimDirecte;
import controller.ControllerPoule;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import java.util.regex.Pattern;
import java.awt.SystemColor;

import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Color;
import java.awt.Font;

/**
 * @author Manon Gaillardot
 *
 */
public class PouleView extends JDialog {

	private ControllerPoule controller;
	private TournoiPoule tournoi;

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DynamicModelTable model;
	private JComboBox<Poule> cbPoules;
	private JTextArea taEquPoule;
	private JLabel lEquPoule;
	private JLabel lMatchsPoule;
	private JTextArea taMatchsPoule;
	private JButton bPhaseFinale;
	private JButton bQuitter;
	private JScrollPane JS1;
	private JScrollPane JS2;
	private JScrollPane JS3;
	private JLabel lRemplir;
	private JButton bEnregistrerScores;
	private JButton bVainqueurs;
	private JLabel lInfo;
	private JLabel lPhasePoule;
	private JLabel lChoixPoule;

	/**
	 * Create the dialog.
	 */
	public PouleView(Frame parent, boolean modal, TournoiPoule tournoi,
			ControllerPoule controller) {
		super(parent, modal);
		this.controller = controller;
		this.tournoi = tournoi;

		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Tournoi " + tournoi.getNom());
		setBounds(100, 100, 652, 463);
		getContentPane().setLayout(null);

		cbPoules = new JComboBox<Poule>();

		for (Poule p : tournoi.getListPoules()) {
			cbPoules.addItem(p);
		}
		cbPoules.setBounds(50, 140, 166, 27);
		getContentPane().add(cbPoules);

		taEquPoule = new JTextArea();
		taEquPoule.setBackground(SystemColor.window);
		taEquPoule.setEnabled(false);
		taEquPoule.setWrapStyleWord(true);
		JS1 = new JScrollPane(taEquPoule);
		JS1.setViewportBorder(null);
		JS1.setBounds(257, 95, 174, 74);
		getContentPane().add(JS1);
		for (Equipe e : tournoi.getListPoules()
				.get(cbPoules.getSelectedIndex()).getListEquipes()) {
			if (taEquPoule.getText().equals("")) {
				taEquPoule.setText(e.toString());
			} else {
				taEquPoule.setText(taEquPoule.getText() + "\n" + e.toString());
			}
		}

		lEquPoule = new JLabel("Equipes de la poule :");
		lEquPoule.setBounds(274, 67, 151, 16);
		getContentPane().add(lEquPoule);

		lMatchsPoule = new JLabel("Matchs de la poule :");
		lMatchsPoule.setBounds(479, 67, 151, 16);
		getContentPane().add(lMatchsPoule);

		taMatchsPoule = new JTextArea();
		taMatchsPoule.setBackground(SystemColor.window);
		taMatchsPoule.setEnabled(false);
		taMatchsPoule.setWrapStyleWord(true);
		JS2 = new JScrollPane(taMatchsPoule);
		JS2.setViewportBorder(null);
		JS2.setBounds(457, 95, 174, 107);
		getContentPane().add(JS2);
		for (Match m : tournoi.getListPoules().get(cbPoules.getSelectedIndex())
				.getListMatchs()) {
			if (taMatchsPoule.getText().equals("")) {
				taMatchsPoule.setText(m.toString());
			} else {
				taMatchsPoule.setText(taMatchsPoule.getText() + "\n"
						+ m.toString());
			}
		}

		model = new DynamicModelTable();
		model.setMatchs(tournoi.getListPoules()
				.get(cbPoules.getSelectedIndex()).getListMatchs());
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JS3 = new JScrollPane(table);
		JS3.setBounds(24, 250, 412, 120);
		getContentPane().add(JS3);

		bPhaseFinale = new JButton("Passer à la phase finale");
		bPhaseFinale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bPhaseFinaleActionPerformed(e);
			}
		});
		bPhaseFinale.setBounds(448, 320, 190, 36);
		bPhaseFinale.setVisible(false);
		getContentPane().add(bPhaseFinale);

		bQuitter = new JButton("Quitter");
		bQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bQuitter.setBounds(479, 382, 123, 36);
		getContentPane().add(bQuitter);

		lRemplir = new JLabel(
				"Double-cliquez sur un score pour l'éditer et appuyez sur entrée :");
		lRemplir.setBounds(24, 215, 423, 16);
		getContentPane().add(lRemplir);

		bEnregistrerScores = new JButton("Enregister les scores de la poule");
		bEnregistrerScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexCB = cbPoules.getSelectedIndex();
				boolean isError = false;
				for (int i = 0; i < TournoiPoule.NBR_MATCHS_POULE
						&& isError == false; i++) {
					if (!(((Integer) table.getValueAt(i, 1)).equals(-1))
							&& !(((Integer) table.getValueAt(i, 1)).equals(-1))) {
						tournoi.getListPoules().get(indexCB).getListMatchs()
								.get(i)
								.setScore1((Integer) table.getValueAt(i, 1));
						tournoi.getListPoules().get(indexCB).getListMatchs()
								.get(i)
								.setScore2((Integer) table.getValueAt(i, 2));
					} else {
						isError = true;
					}
				}
				if (!isError) {
					table.setEnabled(false);
					bEnregistrerScores.setVisible(false);
					controller.finMatchsPoule(tournoi.getListPoules().get(
							indexCB));
					lInfo.setText("Scores enregistrés!");
					if (controller.passePhaseFinale(tournoi)) {
						table.setEnabled(false);
						bEnregistrerScores.setVisible(false);
						bPhaseFinale.setVisible(true);
						bVainqueurs.setVisible(true);
						lInfo.setText("Phase de poule terminée.");
					}
				}
			}
		});
		bEnregistrerScores.setBounds(113, 382, 234, 36);
		getContentPane().add(bEnregistrerScores);

		bVainqueurs = new JButton("Vainqueurs phase de poule");
		bVainqueurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sVainq = "";
				int j = 1;
				for (Poule p : tournoi.getListPoules()) {
					sVainq += "Poule " + j + ": ";
					for (int i = 0; i < p.getListVainqueurs().size(); i++) {
						if (i == 0) {
							sVainq += "1."
									+ p.getListVainqueurs().get(i).getNom();
						} else {
							sVainq += " - 2."
									+ p.getListVainqueurs().get(i).getNom();
						}
					}
					sVainq += "\n";
					j++;
				}
				JOptionPane.showMessageDialog(null,
						"     VAINQUEURS POULES : \n\n" + sVainq);
			}
		});
		bVainqueurs.setBounds(447, 272, 190, 36);
		bVainqueurs.setVisible(false);
		getContentPane().add(bVainqueurs);

		lInfo = new JLabel("");
		lInfo.setForeground(Color.RED);
		lInfo.setBounds(24, 179, 344, 23);
		getContentPane().add(lInfo);

		lPhasePoule = new JLabel("PHASE DE POULE");
		lPhasePoule.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		lPhasePoule.setForeground(new Color(51, 102, 204));
		lPhasePoule.setBounds(218, 6, 218, 36);
		getContentPane().add(lPhasePoule);

		lChoixPoule = new JLabel("Choisissez une poule :");
		lChoixPoule.setBounds(61, 122, 156, 16);
		getContentPane().add(lChoixPoule);

		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		cbPoules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbPoulesActionPerformed(e);
			}
		});

		setVisible(true);
	}

	public void cbPoulesActionPerformed(ActionEvent e) {
		taEquPoule.setText("");
		taMatchsPoule.setText("");
		if (tournoi.getListPoules().get(cbPoules.getSelectedIndex())
				.isMatchsFinis()) {
			bEnregistrerScores.setVisible(false);
			lInfo.setText("Scores déjà enregistrés pour cette poule.");
			table.setEnabled(false);
		} else {
			bEnregistrerScores.setVisible(true);
			lInfo.setText("");
			table.setEnabled(true);
		}
		for (Equipe e1 : tournoi.getListPoules()
				.get(cbPoules.getSelectedIndex()).getListEquipes()) {
			if (taEquPoule.getText().equals("")) {
				taEquPoule.setText(e1.toString());
			} else {
				taEquPoule.setText(taEquPoule.getText() + "\n" + e1.toString());
			}
		}
		model.setMatchs(tournoi.getListPoules()
				.get(cbPoules.getSelectedIndex()).getListMatchs());
		for (Match m : tournoi.getListPoules().get(cbPoules.getSelectedIndex())
				.getListMatchs()) {
			if (taMatchsPoule.getText().equals("")) {
				taMatchsPoule.setText(m.toString());
			} else {
				taMatchsPoule.setText(taMatchsPoule.getText() + "\n"
						+ m.toString());
			}
		}
	}

	public void bPhaseFinaleActionPerformed(ActionEvent e) {
		this.dispose();
	}

}
