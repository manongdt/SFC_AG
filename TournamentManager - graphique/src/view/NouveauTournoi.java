/**
 * 
 */
package view;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Chargement;
import controller.ControllerElimDirecte;
import controller.ControllerPoule;
import controller.ControllerTournoi;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import model.Sport;
import model.Tournoi;
import model.TournoiElimDirecte;
import model.TournoiPoule;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;

/**
 * @author Manon Gaillardot
 *
 */
public class NouveauTournoi extends JDialog {

	private Tournoi tournoi;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNomTournoi;
	private JLabel lNomTournoi;
	private JButton bOK1;
	private JButton bOK2;
	private JRadioButton rbSportCollectif;
	private JRadioButton rbSportIndividuel;
	private ButtonGroup bgTypeSport;
	private JComboBox<Sport> cbSports;
	private JPanel pSport;
	private JPanel pNbrEquipes;
	private JLabel lNbrEquipes;
	private JTextField tfNbrEquipes;
	private JButton bCreer;
	private JButton bAnnuler;

	/**
	 * Create the dialog.
	 */
	public NouveauTournoi(Frame parent, boolean modal, Tournoi tournoi) {
		super(parent, modal);
		this.tournoi = tournoi;

		setTitle("Création d'un tournoi");
		setBounds(100, 100, 456, 331);
		Chargement.chargerSport();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);

		tfNomTournoi = new JTextField();
		tfNomTournoi.setBounds(90, 58, 177, 28);
		getContentPane().add(tfNomTournoi);
		tfNomTournoi.setColumns(10);

		lNomTournoi = new JLabel("Nom du tournoi :");
		lNomTournoi.setBounds(103, 42, 114, 16);
		getContentPane().add(lNomTournoi);

		bOK1 = new JButton("OK");
		bOK1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bOK1ActionPerformed(e);
			}
		});
		bOK1.setBounds(295, 59, 48, 29);
		getContentPane().add(bOK1);

		bgTypeSport = new ButtonGroup();

		pSport = new JPanel();
		pSport.setBounds(16, 98, 420, 84);
		getContentPane().add(pSport);
		pSport.setLayout(null);
		pSport.setVisible(false);

		rbSportIndividuel = new JRadioButton("Sport individuel");
		rbSportIndividuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbSports.removeAllItems();
				for (Sport s : Chargement.getSportIndiv()) {
					cbSports.addItem(s);
				}
			}
		});
		rbSportIndividuel.setBounds(217, 6, 131, 23);
		pSport.add(rbSportIndividuel);
		bgTypeSport.add(rbSportIndividuel);

		rbSportCollectif = new JRadioButton("Sport collectif");
		rbSportCollectif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbSports.removeAllItems();
				for (Sport s : Chargement.getSportCo()) {
					cbSports.addItem(s);
				}
			}
		});
		rbSportCollectif.setBounds(19, 6, 120, 23);
		bgTypeSport.add(rbSportCollectif);
		pSport.add(rbSportCollectif);
		rbSportCollectif.setSelected(true);

		cbSports = new JComboBox<Sport>();
		cbSports.setBounds(85, 41, 167, 27);
		pSport.add(cbSports);

		bOK2 = new JButton("OK");
		bOK2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bOK2ActionPerformed(e);
			}
		});
		bOK2.setBounds(264, 41, 45, 29);
		pSport.add(bOK2);

		pNbrEquipes = new JPanel();
		pNbrEquipes.setBounds(16, 183, 420, 71);
		getContentPane().add(pNbrEquipes);
		pNbrEquipes.setLayout(null);
		pNbrEquipes.setVisible(false);

		lNbrEquipes = new JLabel("");
		lNbrEquipes.setBounds(24, 6, 373, 16);
		if (tournoi.isTournoiPoules()) {
			lNbrEquipes.setText("Nombre de poules de 4 (min 2 - max 20) :");
		} else {
			lNbrEquipes.setText("Nombre d'équipes (min 2 - max 80) :");
		}
		pNbrEquipes.add(lNbrEquipes);

		tfNbrEquipes = new JTextField();
		tfNbrEquipes.setBounds(34, 34, 46, 22);
		pNbrEquipes.add(tfNbrEquipes);
		tfNbrEquipes.setColumns(10);

		bCreer = new JButton("Créer un tournoi");
		bCreer.setBounds(162, 30, 147, 32);
		pNbrEquipes.add(bCreer);
		bCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bCreerActionPerformed(e);
			}
		});

		bAnnuler = new JButton("Annuler");
		bAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		bAnnuler.setBounds(319, 266, 117, 29);
		getContentPane().add(bAnnuler);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setVisible(true);
	}

	public void bOK1ActionPerformed(ActionEvent evt) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]*$");
		if (pattern.matcher(tfNomTournoi.getText()).find()) {
			tfNomTournoi.setEnabled(false);
			for (Sport s : Chargement.getSportCo()) {
				cbSports.addItem(s);
			}
			pSport.setVisible(true);
			tournoi.setNom(tfNomTournoi.getText());
		} else {
			JOptionPane
					.showMessageDialog(
							null,
							"Saisie non valide. \n-Aucun caractère spécial autorisé");
			tfNomTournoi.setText("");
		}
	}

	public void bOK2ActionPerformed(ActionEvent evt) {
		cbSports.getSelectedItem();
		tournoi.setSport((Sport) cbSports.getSelectedItem());
		cbSports.setEnabled(false);
		rbSportCollectif.setEnabled(false);
		rbSportIndividuel.setEnabled(false);
		pNbrEquipes.setVisible(true);
	}

	public void bCreerActionPerformed(ActionEvent evt) {
		int nbrEquipes = -1;
		Pattern pattern = Pattern.compile("^([2-9]|1[0-9]|20)$");
		Pattern pattern2 = Pattern.compile("^([2-9]|[1-7][0-9]|80)$");
		if ((pattern.matcher(tfNbrEquipes.getText()).find() && tournoi
				.isTournoiPoules())
				|| (pattern2.matcher(tfNbrEquipes.getText()).find() && !tournoi
						.isTournoiPoules())) {
			if (!tournoi.isTournoiPoules()) {
				nbrEquipes = Integer.parseInt(tfNbrEquipes.getText());
			} else {
				nbrEquipes = 4 * Integer.parseInt(tfNbrEquipes.getText());
			}
			tournoi.setNbrEquipes(nbrEquipes);
			this.setVisible(false);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null,
					"Format incorrect.\n-Nombre d'équipes inférieur à 80.");
		}
	}

}
