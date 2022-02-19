package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import connexion.BD.connexion;

public class Liste_avLot extends Habillage {

	private static Connection connect; // creer varaible connect
	private JFrame win;
	private JPanel prime, buttonPane, ContentPane;
	private JLabel lab1, addbut_label, supbut_label;
	private JButton ajout_but, supp_but;
	private JComboBox<String> cliste;

	public Liste_avLot() {
		createGUI();
		ChargeListe();
	}

	private void createGUI() {
		win = new JFrame("LISTE  POUR TRAME AVEC LOT");
		win.setSize(definition.TP_Large, definition.TP_Haut);
		win.setLocationRelativeTo(null);
		win.setResizable(false);

		prime = new SmallWindow();
		prime.setLayout(new BorderLayout());
		ContentPane = new JPanel();
		buttonPane = new JPanel();

		ContentPane.setOpaque(false);
		buttonPane.setOpaque(false);

		lab1 = new JLabel("MODIFIER LISTE ACTUELLE : ");
		lab1.setForeground(Color.BLACK);
		cliste = new JComboBox<>();
		cliste.setEditable(true);
		ajout_but = new JButton();
		supp_but = new JButton();

		// ajout image sur bouton
		ImageIcon addbut_icon = new ImageIcon(getClass().getResource(definition.URL_AJOUTER));
		addbut_label = new JLabel("AJOUER", addbut_icon, JLabel.CENTER);
		ajout_but.add(addbut_label);
		ImageIcon supbut_icon = new ImageIcon(getClass().getResource(definition.URL_SUPPRIMER));
		supbut_label = new JLabel("SUPPRIMER", supbut_icon, JLabel.CENTER);
		supp_but.add(supbut_label);

		ContentPane.add(lab1);
		ContentPane.add(cliste);
		buttonPane.add(ajout_but);
		buttonPane.add(supp_but);

		ajout_but.addActionListener(new AddListe());
		supp_but.addActionListener(new SuppListe());
		prime.addMouseListener(new myMouseListener());

		win.getContentPane().add(prime);
		prime.add(ContentPane, BorderLayout.NORTH);
		prime.add(buttonPane, BorderLayout.SOUTH);
		win.setVisible(true);
	}

	// *** CLASS BOUTON ***
	public class AddListe implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				connect = connexion.getConnection();
				String str = "INSERT INTO CHOIX (AVECLOT) VALUES (?)";
				PreparedStatement ps = connect.prepareStatement(str);
				ps.setString(1, (String) cliste.getSelectedItem());
				ps.executeUpdate();
				ps.close();

				JOptionPane.showMessageDialog(null, "La Liste a ete modifié");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public class SuppListe implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				connect = connexion.getConnection();
				String str = "DELETE FROM  CHOIX WHERE AVECLOT = ?";
				PreparedStatement ps = connect.prepareStatement(str);
				ps.setString(1, (String) cliste.getSelectedItem());
				ps.executeUpdate();
				ps.close();

				JOptionPane.showMessageDialog(null, "La Liste a ete modifié");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void ChargeListe() {

		try {
			cliste.removeAllItems(); // supprime element residuel
			connect = connexion.getConnection(); // initialisation connection

			String sql = "SELECT AVECLOT FROM CHOIX WHERE AVECLOT IS NOT NULL ORDER BY AVECLOT"; // selecton type de
																									// donne de la base
			PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);

			ResultSet rs = pState.executeQuery(); // resultat de la selection
			// ajout element 1 a 1
			while (rs.next()) {
				cliste.addItem(rs.getString("AVECLOT"));
			}
			// ferme les connexions
			cliste.setSelectedIndex(-1);
			rs.close();
			pState.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

}
