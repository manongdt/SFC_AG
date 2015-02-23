/**
 * 
 */
package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import controller.ControllerTournoi;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Manon Gaillardot
 *
 */
public class SwingView2 extends JFrame {
      
	private ControllerTournoi ct;
	private JPanel contentPane;
	private JButton bQuitter;
	private JButton bCreer;
	private JLabel lTitre;

	/**
	 * Create the frame.
	 */
	public SwingView2(ControllerTournoi ct) {
		this.ct = ct;
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
	}
	
	private void bCreerActionPerformed(ActionEvent evt){
		NouveauTournoi dialog = new NouveauTournoi(this, ct);
		dialog.setVisible(true);
	}
}
