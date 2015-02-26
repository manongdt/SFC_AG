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
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTable;

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

/**
 * @author Manon Gaillardot
 *
 */
public class PouleView extends JDialog {

	private ControllerPoule controller;
	private TournoiPoule tournoi;

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JComboBox<Poule> cbPoules;
	private JTextArea taEquPoule;
	private JLabel lEquPoule;
	private JLabel lMatchsPoule;
	private JTextArea taMatchsPoule;
	private JButton bEnregistrer;
	private JButton bAnnuler;

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
		setBounds(100, 100, 654, 441);
		getContentPane().setLayout(null);

		cbPoules = new JComboBox<Poule>();
		cbPoules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbPoulesActionPerformed(e);
			}
		});
		for (Poule p : tournoi.getListPoules()) {
			cbPoules.addItem(p);
		}
		cbPoules.setBounds(22, 87, 151, 27);
		getContentPane().add(cbPoules);

		taEquPoule = new JTextArea();
		taEquPoule.setWrapStyleWord(true);
		taEquPoule.setLineWrap(true);
		taEquPoule.setBounds(199, 56, 169, 78);
		getContentPane().add(taEquPoule);
		for (Equipe e : tournoi.getListPoules()
				.get(cbPoules.getSelectedIndex()).getListEquipes()) {
			if (taEquPoule.getText().equals("")) {
				taEquPoule.setText(e.toString());
			} else {
				taEquPoule.setText(taEquPoule.getText() + "\n" + e.toString());
			}
		}

		lEquPoule = new JLabel("Equipes de la poule :");
		lEquPoule.setBounds(217, 28, 151, 16);
		getContentPane().add(lEquPoule);

		lMatchsPoule = new JLabel("Matchs de la poule :");
		lMatchsPoule.setBounds(454, 28, 151, 16);
		getContentPane().add(lMatchsPoule);

		taMatchsPoule = new JTextArea();
		taMatchsPoule.setLineWrap(true);
		taMatchsPoule.setWrapStyleWord(true);
		taMatchsPoule.setBounds(422, 56, 215, 114);
		for (Match m : tournoi.getListPoules()
				.get(cbPoules.getSelectedIndex()).getListMatchs()) {
			if (taMatchsPoule.getText().equals("")) {
				taMatchsPoule.setText(m.toString());
			} else {
				taMatchsPoule.setText(taMatchsPoule.getText() + "\n" + m.toString());
			}
		}
		getContentPane().add(taMatchsPoule);

		table = new JTable();
		table.setBounds(22, 195, 453, 162);
		String col[] = {"Equipe 1","Equipe 2"," Score"};
		Vector vector = new Vector();
		Vector vecText = new Vector()
		vector.add(tournoi.getListPoules().get(cbPoules.getSelectedIndex()).getListMatchs());
		vector.add(new Vector())
//		DefaultTableModel tableModel = new DefaultTableModel(col, 3);
//		table.setModel(tableModel);
//		tableModel.addRow(tournoi.getListPoules().get(cbPoules.getSelectedIndex()).getListMatchs());
		getContentPane().add(table);

		bEnregistrer = new JButton("Enregistrer");
		bEnregistrer.setBounds(503, 250, 134, 36);
		getContentPane().add(bEnregistrer);

		bAnnuler = new JButton("Annuler");
		bAnnuler.setBounds(503, 376, 117, 29);
		getContentPane().add(bAnnuler);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		setVisible(true);
	}
	
	public void cbPoulesActionPerformed(ActionEvent e){
		for (Equipe e1 : tournoi.getListPoules()
				.get(cbPoules.getSelectedIndex()).getListEquipes()) {
			if (taEquPoule.getText().equals("")) {
				taEquPoule.setText(e1.toString());
			} else {
				taEquPoule.setText(taEquPoule.getText() + "\n" + e1.toString());
			}
		}
		for (Match m : tournoi.getListPoules()
				.get(cbPoules.getSelectedIndex()).getListMatchs()) {
			if (taMatchsPoule.getText().equals("")) {
				taMatchsPoule.setText(m.toString());
			} else {
				taMatchsPoule.setText(taMatchsPoule.getText() + "\n" + m.toString());
			}
		}
	}

}
