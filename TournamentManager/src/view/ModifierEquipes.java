/**
 * 
 */
package view;

import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import model.Equipe;
import model.Tournoi;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.SystemColor;
import java.util.regex.Pattern;
import java.awt.Font;

/**
 * @author Manon Gaillardot et Willian Lanners
 *
 */
@SuppressWarnings("serial")
public class ModifierEquipes extends JDialog {

	private Tournoi tournoi;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<Equipe> cbEquipes;
	private JButton bQuitter;
	private JButton bLancerTournoi;
	private JButton bEnregistrer;
	private JPanel pModif;
	private JPanel pInfo;
	private JLabel lModifNom;
	private JLabel lModifNbr;
	private JLabel lInfoDesc;
	private JTextArea taModifDesc;
	private JTextField tfNomEq;
	private JTextField tfNbrJoueurs;
	private JLabel lInfoNbr;
	private JLabel lNbr;
	private JLabel lModif;
	private JTextArea taInfoDesc;
	private JLabel lInfoModif;
	private JScrollPane JS1;
	private JScrollPane JS2;
	private JLabel lblChoisirLquipe;
	private JLabel lTitre;

	/**
	 * Create the dialog.
	 */
	public ModifierEquipes(Frame parent, boolean modal, Tournoi tournoi) {
		super(parent, modal);
		this.tournoi = tournoi;

		setTitle("Gestionnaire de tournoi");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 513, 500);
		getContentPane().setLayout(null);

		bQuitter = new JButton("Quitter");
		bQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bQuitter.setBounds(405, 440, 93, 29);
		getContentPane().add(bQuitter);

		bLancerTournoi = new JButton("Lancer le tournoi");
		bLancerTournoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bLancerTournoiActionPerformed(e);
			}
		});
		bLancerTournoi.setBounds(174, 423, 136, 36);
		getContentPane().add(bLancerTournoi);

		pModif = new JPanel();
		pModif.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		pModif.setBounds(26, 168, 458, 232);
		getContentPane().add(pModif);
		pModif.setLayout(null);

		tfNomEq = new JTextField();
		tfNomEq.setBounds(70, 35, 166, 28);
		pModif.add(tfNomEq);
		tfNomEq.setColumns(10);

		lModifNom = new JLabel("Nom :");
		lModifNom.setBounds(23, 41, 59, 16);
		pModif.add(lModifNom);

		tfNbrJoueurs = new JTextField();
		tfNbrJoueurs.setBounds(383, 35, 47, 28);
		pModif.add(tfNbrJoueurs);
		tfNbrJoueurs.setColumns(10);

		lModifNbr = new JLabel("Nombre de joueurs :");
		lModifNbr.setBounds(248, 41, 137, 16);
		pModif.add(lModifNbr);

		lInfoDesc = new JLabel("Description :");
		lInfoDesc.setBounds(23, 75, 84, 16);
		pModif.add(lInfoDesc);

		taModifDesc = new JTextArea();
		taModifDesc.setWrapStyleWord(true);
		taModifDesc.setLineWrap(true);
		JS1 = new JScrollPane(taModifDesc);
		JS1.setViewportBorder(null);
		JS1.setBounds(23, 99, 347, 85);
		pModif.add(JS1);

		bEnregistrer = new JButton("Enregistrer les modifications");
		bEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bEnregistrerActionPerformed(e);
			}
		});
		bEnregistrer.setBounds(17, 196, 208, 29);
		pModif.add(bEnregistrer);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		pModif.enableInputMethods(false);
		pModif.setOpaque(true);
		pModif.setEnabled(false);

		lModif = new JLabel(
				"Modification - ne remplir que les champs que vous voulez modifier :");
		lModif.setBounds(6, 7, 446, 16);
		pModif.add(lModif);

		lInfoModif = new JLabel("Modification effectuée!");
		lInfoModif.setBounds(234, 201, 166, 16);
		lInfoModif.setVisible(false);
		pModif.add(lInfoModif);
		lInfoModif.setForeground(Color.RED);

		pInfo = new JPanel();
		pInfo.setBounds(26, 54, 458, 103);
		getContentPane().add(pInfo);
		pInfo.setLayout(null);

		lInfoNbr = new JLabel("Nombre de joueur(s) :");
		lInfoNbr.setForeground(SystemColor.textText);
		lInfoNbr.setBounds(28, 70, 141, 16);
		pInfo.add(lInfoNbr);

		lNbr = new JLabel("New label");
		lNbr.setBounds(171, 70, 30, 16);
		pInfo.add(lNbr);

		taInfoDesc = new JTextArea();
		taInfoDesc.setEnabled(false);
		taInfoDesc.setBackground(SystemColor.window);
		taInfoDesc.setWrapStyleWord(true);
		taInfoDesc.setLineWrap(true);
		JS2 = new JScrollPane(taInfoDesc);
		JS2.setViewportBorder(null);
		JS2.setBounds(213, 6, 234, 91);
		pInfo.add(JS2);

		cbEquipes = new JComboBox<Equipe>();
		for (Equipe e : tournoi.getListEquipes()) {
			cbEquipes.addItem(e);
		}
		cbEquipes.setBounds(6, 28, 195, 27);
		pInfo.add(cbEquipes);
		cbEquipes.setSelectedIndex(0);
		cbEquipes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = cbEquipes.getSelectedIndex();
				Equipe e1 = tournoi.getListEquipes().get(index);
				lNbr.setText(Integer.toString(e1.getNbrJoueurs()));
				taInfoDesc.setText(e1.getDescription());
				lInfoModif.setVisible(false);
			}
		});
		lNbr.setText(Integer.toString(tournoi.getListEquipes()
				.get(cbEquipes.getSelectedIndex()).getNbrJoueurs()));
		taInfoDesc.setText(tournoi.getListEquipes()
				.get(cbEquipes.getSelectedIndex()).getDescription());

		lblChoisirLquipe = new JLabel("Choisir l'équipe à modifier :");
		lblChoisirLquipe.setBounds(16, 6, 182, 16);
		pInfo.add(lblChoisirLquipe);

		lTitre = new JLabel("MODIFICATION DES EQUIPES");
		lTitre.setForeground(new Color(51, 102, 204));
		lTitre.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		lTitre.setBounds(77, 6, 362, 36);
		getContentPane().add(lTitre);

		setVisible(true);
	}

	public void bEnregistrerActionPerformed(ActionEvent e) {
		int iEqu = cbEquipes.getSelectedIndex();
		String nom = tfNomEq.getText();
		String nbrJoueurs = tfNbrJoueurs.getText();
		String desc = taModifDesc.getText();
		boolean isModified = false;
		boolean isError = false;
		if (!nom.equals("")) {
			Pattern pattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9 ]*$");
			if (pattern.matcher(nom).find()) {
				tournoi.getListEquipes().get(iEqu).setNom(nom);
				isModified = true;
			} else {
				isError = true;
			}
		}
		if (!nbrJoueurs.equals("")) {
			Pattern pattern2 = Pattern.compile("^([1-9]|[1-2][0-9]|30)$");
			if (pattern2.matcher(nbrJoueurs).find()) {
				tournoi.getListEquipes().get(iEqu)
						.setNbrJoueurs(Integer.parseInt(nbrJoueurs));
				isModified = true;
			} else {
				isError = true;
			}
		}
		if (!desc.equals("")) {
			Pattern pattern3 = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9 ]*$");
			if (pattern3.matcher(desc).find()) {
				tournoi.getListEquipes().get(iEqu).setDescription(desc);
				isModified = true;
			} else {
				isError = true;
			}
		}
		if (isError == true) {
			JOptionPane
					.showMessageDialog(
							null,
							"Format incorrect pour au moins un des champs."
									+ "\n-Aucun caractère spécial autorisé\n-Nombre de joueurs compris entre 1 et 30");
		}
		if (isModified == true) {
			lInfoModif.setVisible(true);
		}

		taModifDesc.setText("");
		tfNbrJoueurs.setText("");
		tfNomEq.setText("");
	}

	public void bLancerTournoiActionPerformed(ActionEvent e) {
		setVisible(false);
		this.dispose();
	}

}
