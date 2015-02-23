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
import controller.ControllerTournoi;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import model.Sport;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Manon Gaillardot
 *
 */
public class NouveauTournoi extends JDialog {

	private ControllerTournoi ct;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNomTournoi;
	private JLabel lNomTournoi;
	private JButton bOK1;
	private JButton bOK2;
	private JButton bOK3;
	private JButton bOK4;
	private JRadioButton rbSportCollectif;
	private JRadioButton rbSportIndividuel;
	private ButtonGroup bgTypeSport;
	private ButtonGroup bgOrga;
	private JComboBox<String> cbSports;
	private JPanel pOrganisation;
	private JPanel pSport;
	private JRadioButton rbTournoiED;
	private JRadioButton rbTournoiPoules;
	private JPanel pNbrEquipes;
	private JLabel lNbrEquipes;
	private JTextField tfNbrEquipes;
	private JButton bCreer;
	private JButton bAnnuler;
	
	

	/**
	 * Create the dialog.
	 */
	public NouveauTournoi(Frame parent, ControllerTournoi ct) {
		this.ct = ct;
		setTitle("Création d'un tournoi");
		setBounds(100, 100, 450, 370);
		Chargement.chargerSport();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		tfNomTournoi = new JTextField();
		tfNomTournoi.setBounds(171, 24, 177, 28);
		getContentPane().add(tfNomTournoi);
		tfNomTournoi.setColumns(10);
		
		lNomTournoi = new JLabel("Nom du tournoi :");
		lNomTournoi.setBounds(34, 30, 114, 16);
		getContentPane().add(lNomTournoi);
		
		bOK1 = new JButton("OK");
		bOK1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ct.controlNomTournoi(tfNomTournoi.getText())){
					tfNomTournoi.setEnabled(false);
					for (Sport s : Chargement.getSportCo()) {
		                cbSports.addItem(s.getNom());
		            }
					 pSport.setVisible(true); 
		        } else {
		            JOptionPane.showMessageDialog(null, "N'utilisez pas de caractères spéciaux. Veuillez ressaisir un nom de tournoi.");
		            tfNomTournoi.setText("");
		        }
			}
		});
		bOK1.setBounds(360, 25, 48, 29);
		getContentPane().add(bOK1);
		
		bgTypeSport = new ButtonGroup();
		
		pSport = new JPanel();
		pSport.setBounds(60, 64, 325, 84);
		getContentPane().add(pSport);
		pSport.setLayout(null);
		pSport.setVisible(false);
		
		rbSportIndividuel = new JRadioButton("Sport individuel");
		rbSportIndividuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbSports.removeAllItems();
		        for (Sport s : Chargement.getSportIndiv()) {
		            cbSports.addItem(s.getNom());
		        }
			}
		});
		rbSportIndividuel.setBounds(170, 6, 131, 23);
		pSport.add(rbSportIndividuel);
		bgTypeSport.add(rbSportIndividuel);
		
		rbSportCollectif = new JRadioButton("Sport collectif");
		rbSportCollectif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbSports.removeAllItems();
		        for (Sport s : Chargement.getSportCo()) {
		            cbSports.addItem(s.getNom());
		        }
			}
		});
		rbSportCollectif.setBounds(19, 6, 120, 23);
		bgTypeSport.add(rbSportCollectif);
		pSport.add(rbSportCollectif);
		rbSportCollectif.setSelected(true);
		
		cbSports = new JComboBox <String>();
		cbSports.setBounds(29, 40, 167, 27);
		pSport.add(cbSports);
		
		bOK2 = new JButton("OK");
		bOK2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		bOK2.setBounds(228, 39, 45, 29);
		pSport.add(bOK2);
		
		pOrganisation = new JPanel();
		pOrganisation.setBounds(11, 160, 420, 62);
		getContentPane().add(pOrganisation);
		pOrganisation.setLayout(null);
		pOrganisation.setVisible(false);
		
		bgOrga = new ButtonGroup();
		
		rbTournoiED = new JRadioButton("Tournoi à élimination directe");
		rbTournoiED.setBounds(16, 6, 224, 23);
		bgOrga.add(rbTournoiED);
		pOrganisation.add(rbTournoiED);
		
		rbTournoiPoules = new JRadioButton("Tournoi à phase de poules/phase finale");
		rbTournoiPoules.setBounds(16, 33, 288, 23);
		bgOrga.add(rbTournoiPoules);
		pOrganisation.add(rbTournoiPoules);
		
		bOK3 = new JButton("OK");
		bOK3.setBounds(329, 16, 47, 29);
		pOrganisation.add(bOK3);
		
		pNbrEquipes = new JPanel();
		pNbrEquipes.setBounds(11, 234, 239, 51);
		getContentPane().add(pNbrEquipes);
		pNbrEquipes.setLayout(null);
		pNbrEquipes.setVisible(false);
		
		lNbrEquipes = new JLabel("bla");
		lNbrEquipes.setBounds(61, 16, 106, 16);
		pNbrEquipes.add(lNbrEquipes);
		
		tfNbrEquipes = new JTextField();
		tfNbrEquipes.setBounds(6, 10, 43, 28);
		pNbrEquipes.add(tfNbrEquipes);
		tfNbrEquipes.setColumns(10);
		
		bOK4 = new JButton("OK");
		bOK4.setBounds(179, 11, 43, 29);
		pNbrEquipes.add(bOK4);
		
		bCreer = new JButton("Créer un tournoi");
		bCreer.setBounds(266, 241, 165, 44);
		getContentPane().add(bCreer);
		bCreer.setVisible(false);
		
		bAnnuler = new JButton("Annuler");
		bAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		bAnnuler.setBounds(314, 297, 117, 29);
		getContentPane().add(bAnnuler);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

	}
}
