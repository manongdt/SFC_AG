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

import controller.ControllerElimDirecte;
import controller.ControllerTournoi;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import model.Equipe;
import model.Tournoi;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;

import java.awt.Color;
import java.awt.SystemColor;
import java.util.regex.Pattern;

/**
 * @author Manon Gaillardot
 *
 */
public class ModifierEquipes extends JDialog {

	private Tournoi tournoi;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<Equipe> cbEquipes;
	private JButton bAnnuler;
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
	private JSeparator separator;
	private JLabel lInfoModif;

	/**
	 * Create the dialog.
	 */
	public ModifierEquipes(Frame parent, boolean modal, Tournoi tournoi) {
		super(parent, modal);
		this.tournoi = tournoi;

		setTitle("Modification des équipes");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 554, 529);
		getContentPane().setLayout(null);

		cbEquipes = new JComboBox<Equipe>();
		for (Equipe e : tournoi.getListEquipes()) {
			cbEquipes.addItem(e);
		}
		cbEquipes.setSelectedIndex(0);
		cbEquipes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = cbEquipes.getSelectedIndex();
				Equipe e1 = tournoi.getListEquipes().get(index);
				lNbr.setText(Integer.toString(e1.getNbrJoueurs()));
				taInfoDesc.setText(e1.getDescription());
			}
		});
		cbEquipes.setBounds(16, 23, 179, 27);

		getContentPane().add(cbEquipes);

		bAnnuler = new JButton("Annuler");
		bAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		bAnnuler.setBounds(447, 448, 82, 29);
		getContentPane().add(bAnnuler);

		bLancerTournoi = new JButton("Lancer le tournoi");
		bLancerTournoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bLancerTournoiActionPerformed(e);
			}
		});
		bLancerTournoi.setBounds(69, 440, 136, 45);
		getContentPane().add(bLancerTournoi);

		pModif = new JPanel();
		pModif.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		pModif.setBounds(26, 170, 494, 258);
		getContentPane().add(pModif);
		pModif.setLayout(null);

		tfNomEq = new JTextField();
		tfNomEq.setBounds(17, 59, 236, 28);
		pModif.add(tfNomEq);
		tfNomEq.setColumns(10);

		lModifNom = new JLabel("Nom :");
		lModifNom.setBounds(46, 41, 59, 16);
		pModif.add(lModifNom);

		tfNbrJoueurs = new JTextField();
		tfNbrJoueurs.setBounds(298, 59, 47, 28);
		pModif.add(tfNbrJoueurs);
		tfNbrJoueurs.setColumns(10);

		lModifNbr = new JLabel("Nombre de joueurs :");
		lModifNbr.setBounds(312, 41, 238, 16);
		pModif.add(lModifNbr);

		lInfoDesc = new JLabel("Description :");
		lInfoDesc.setBounds(46, 99, 84, 16);
		pModif.add(lInfoDesc);

		taModifDesc = new JTextArea();
		taModifDesc.setWrapStyleWord(true);
		taModifDesc.setLineWrap(true);
		taModifDesc.setBounds(23, 127, 452, 85);
		pModif.add(taModifDesc);

		bEnregistrer = new JButton("Enregistrer les modifications");
		bEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bEnregistrerActionPerformed(e);
			}
		});
		bEnregistrer.setBounds(121, 223, 247, 29);
		pModif.add(bEnregistrer);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		pModif.enableInputMethods(false);
		pModif.setOpaque(true);
		pModif.setEnabled(false);

		lModif = new JLabel(
				"Modification - ne remplir que les champs que vous voulez modifier :");
		lModif.setBounds(17, 6, 458, 16);
		pModif.add(lModif);

		pInfo = new JPanel();
		pInfo.setBounds(16, 62, 521, 85);
		getContentPane().add(pInfo);
		pInfo.setLayout(null);

		lInfoNbr = new JLabel("Nombre de joueur(s) :");
		lInfoNbr.setForeground(SystemColor.textText);
		lInfoNbr.setBounds(6, 6, 148, 16);
		pInfo.add(lInfoNbr);

		lNbr = new JLabel("New label");
		lNbr.setBounds(40, 27, 61, 16);
		// System.out.println(cbEquipes.getSelectedIndex());
		lNbr.setText(Integer.toString(tournoi.getListEquipes()
				.get(cbEquipes.getSelectedIndex()).getNbrJoueurs()));
		pInfo.add(lNbr);

		taInfoDesc = new JTextArea();
		taInfoDesc.setEnabled(false);
		taInfoDesc.setBackground(SystemColor.window);
		taInfoDesc.setWrapStyleWord(true);
		taInfoDesc.setLineWrap(true);
		taInfoDesc.setBounds(184, 6, 320, 72);
		taInfoDesc.setText(tournoi.getListEquipes()
				.get(cbEquipes.getSelectedIndex()).getDescription());
		pInfo.add(taInfoDesc);

		separator = new JSeparator();
		separator.setForeground(Color.ORANGE);
		separator.setBounds(16, 155, 521, 12);
		getContentPane().add(separator);

		lInfoModif = new JLabel("");
		lInfoModif.setForeground(Color.RED);
		lInfoModif.setBounds(218, 27, 315, 16);
		getContentPane().add(lInfoModif);

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
			Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]*$");
			if (pattern.matcher(nom).find()) {
				tournoi.getListEquipes().get(iEqu).setNom(nom);
				isModified = true;
			} else {
				isError = true;
			}
		}
		if (!nbrJoueurs.equals("")) {
			Pattern pattern2 = Pattern.compile("^([1-9]|[1-2][0-9])$");
			if (pattern2.matcher(nbrJoueurs).find()) {
				tournoi.getListEquipes().get(iEqu)
						.setNbrJoueurs(Integer.parseInt(nbrJoueurs));
				isModified = true;
			} else {
				isError = true;
			}
		}
		if (!desc.equals("")) {
			Pattern pattern3 = Pattern.compile("^[a-zA-Z0-9 ]*$");
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
							+ "\n-Aucun caractère spécial autorisé"
							+ "\n-Nombre d'équipes inférieur à 80");
		}
		if (isModified == true) {
			lInfoModif.setText("Equipe '"
					+ tournoi.getListEquipes().get(iEqu).getNom()
					+ "' modifiée avec succès!");
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
