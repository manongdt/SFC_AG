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
import java.awt.Font;
import java.awt.Color;

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
	private JButton bQuitter;
	private JLabel lTitre;

	/**
	 * Create the dialog.
	 */
	public NouveauTournoi(Frame parent, boolean modal, Tournoi tournoi) {
		super(parent, modal);
		this.tournoi = tournoi;

		setTitle("Gestionnaire de tournoi");
		setBounds(100, 100, 456, 357);
		Chargement.chargerSport();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);

		tfNomTournoi = new JTextField();
		tfNomTournoi.setBounds(169, 82, 177, 28);
		getContentPane().add(tfNomTournoi);
		tfNomTournoi.setColumns(10);

		lNomTournoi = new JLabel("Nom du tournoi :");
		lNomTournoi.setBounds(43, 88, 114, 16);
		getContentPane().add(lNomTournoi);

		bOK1 = new JButton("OK");
		bOK1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bOK1ActionPerformed(e);
			}
		});
		bOK1.setBounds(360, 83, 50, 30);
		getContentPane().add(bOK1);

		bgTypeSport = new ButtonGroup();

		pSport = new JPanel();
		pSport.setBounds(16, 122, 420, 84);
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
		rbSportIndividuel.setBounds(6, 41, 131, 23);
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
		rbSportCollectif.setBounds(6, 6, 120, 23);
		bgTypeSport.add(rbSportCollectif);
		pSport.add(rbSportCollectif);
		rbSportCollectif.setSelected(true);

		cbSports = new JComboBox<Sport>();
		cbSports.setBounds(149, 28, 184, 27);
		pSport.add(cbSports);

		bOK2 = new JButton("OK");
		bOK2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bOK2ActionPerformed(e);
			}
		});
		bOK2.setBounds(345, 27, 50, 30);
		pSport.add(bOK2);

		pNbrEquipes = new JPanel();
		pNbrEquipes.setBounds(16, 206, 420, 76);
		getContentPane().add(pNbrEquipes);
		pNbrEquipes.setLayout(null);
		pNbrEquipes.setVisible(false);

		lNbrEquipes = new JLabel("Nombre de poules de 4 (min 2 - max 20) :");
		lNbrEquipes.setBounds(6, 6, 273, 16);
		if (tournoi.isTournoiPoules()) {
			lNbrEquipes.setText("Nombre de poules de 4 (min 2 - max 20) :");
		} else {
			lNbrEquipes.setText("Nombre d'équipes (min 2 - max 80) :");
		}
		pNbrEquipes.add(lNbrEquipes);

		tfNbrEquipes = new JTextField();
		tfNbrEquipes.setBounds(281, 3, 46, 22);
		pNbrEquipes.add(tfNbrEquipes);
		tfNbrEquipes.setColumns(10);

		bCreer = new JButton("Créer un tournoi");
		bCreer.setBounds(120, 41, 154, 29);
		pNbrEquipes.add(bCreer);
		bCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bCreerActionPerformed(e);
			}
		});

		bQuitter = new JButton("Quitter");
		bQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bQuitter.setBounds(304, 300, 117, 29);
		getContentPane().add(bQuitter);
		
		lTitre = new JLabel("NOUVEAU TOURNOI");
		lTitre.setForeground(new Color(51, 102, 204));
		lTitre.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		lTitre.setBounds(102, 6, 256, 36);
		getContentPane().add(lTitre);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setVisible(true);
	}

	public void bOK1ActionPerformed(ActionEvent evt) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9 ]*$");
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
