package com.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Image;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import connexion.BD.connexion;

public class Base_EM extends Habillage {

	// declaration des variables
	private static Connection connect; // creer varaible connect
	JFrame fen_BEM;
	Image img_BEM;
	private JButton InsertEssai, ValidEssai, refresh, rechercher, graph, export;
	private JPanel panel_BEM, pane_N, pane_C, pBox1, pBox2, pBox3, pCard0, pCard1, pCard2, pCard3;
	private TitledBorder pBox1Border, pBox2Border, pBox3Border;
	private JLabel NbDossier, NbDossierClos, NbCoulage, NbDNC, NbResNC, NbRetard, NbPrep, NbAEcraser, NbCool;
	private JLabel tNbDossier, tNbDossierClos, tNbCoulage, tNbDNC, tNbResNC, tNbRetard, tNbPrep, tNbAEcraser, tNbCool;
	private JLabel ajout_label, valider_label, refresh_label, rech_label, graph_label, export_label;
	private JTextField ope, date_Tgachage, date_Tfab, lot_PF2, lot_Associe2, T_text;
	private JTable tab_EssSupp;
	private JComboBox<String> cbname, cmortier;
	private DefaultTableModel model_EssSupp;
	private Object[] row_EM;
	final CardLayout cLayout = new CardLayout();
	private String Methode, Q_eau, Q_res, Q_poudre, no_id;

	String[] Liste_Card = { "C0", "C1", "C2", "C3" };

	public Base_EM() {
		fen_BEM();
		ChargeNom();
		ChargeMortier();
	}

	private void fen_BEM() {

		// creation frame principal
		fen_BEM = new JFrame("BASE DES ESSAIS MECANIQUES");

		// definition de la frame
		fen_BEM.setSize(definition.BEM_Large, definition.BEM_Haut);
		fen_BEM.setLocationRelativeTo(null);

		fen_BEM.setResizable(false);
		panel_BEM = new BaseEMPanel(); // panel principale
		panel_BEM.setLayout(new BorderLayout());

		pane_N = new JPanel();
		pane_N.setLayout(new BoxLayout(pane_N, BoxLayout.X_AXIS));
		pane_C = new JPanel();

		pBox1 = new JPanel();
		pBox2 = new JPanel();
		pBox3 = new JPanel();
		pane_N.add(pBox1);
		pane_N.add(pBox2);
		pane_N.add(pBox3);

		pane_N.setOpaque(false);
		pane_C.setOpaque(false);
		pBox1.setOpaque(false);
		pBox2.setOpaque(false);
		pBox3.setOpaque(false);

		panel_BEM.add(pane_N, BorderLayout.NORTH);
		panel_BEM.add(pane_C, BorderLayout.CENTER);

		// initalisation des paneau
		viewBox1();
		viewBox2();
		viewBox3();
		viewCenter();

		// CODE TEMPORAIRE => obtenetion coordonne clic
		panel_BEM.addMouseListener(new myMouseListener());

		// affichage fenetre
		fen_BEM.getContentPane().add(panel_BEM);
		fen_BEM.setVisible(true);
	}

	private void viewBox1() {
		pBox1Border = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),
				"SUIVI ESSAI MECANIQUE", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,
				new Font("Time new Roman", Font.BOLD, 14));
		pBox1.setBorder(pBox1Border);

		pBox1.setLayout(new GridBagLayout());
		GridBagConstraints pb1 = new GridBagConstraints();

		NbDossier = new JLabel("<html><font color = black>Nb Dossiers Total : </font></html>");
		NbDossierClos = new JLabel("Nb Dossiers Clos : ");
		NbDNC = new JLabel("Nb Densite NC : ");
		NbCoulage = new JLabel("Nb Coulage a Faire : ");
		NbResNC = new JLabel("Nb Resistance NC : ");
		NbRetard = new JLabel("Nb Serie en Retard : ");
		NbPrep = new JLabel("Nb Serie a Preparer : ");
		NbAEcraser = new JLabel("Nb Serie à Ecraser : ");
		NbCool = new JLabel("Nb Serie En Cours : ");

		pb1.gridx = 0;
		pb1.gridy = 0;
		pb1.anchor = GridBagConstraints.FIRST_LINE_END;
		pb1.insets = new Insets(5, 0, 0, 0);
		pBox1.add(NbDossier, pb1);

		pb1.gridy = 1;
		pBox1.add(NbDossierClos, pb1);
		pb1.gridy = 2;
		pBox1.add(NbCoulage, pb1);
		pb1.gridy = 3;
		pBox1.add(NbDNC, pb1);
		pb1.gridy = 4;
		pBox1.add(NbResNC, pb1);
		pb1.gridy = 5;
		pBox1.add(NbRetard, pb1);
		pb1.gridy = 6;
		pBox1.add(NbPrep, pb1);
		pb1.gridy = 7;
		pBox1.add(NbAEcraser, pb1);
		pb1.gridy = 8;
		pBox1.add(NbCool, pb1);

		tNbDossier = new JLabel();
		tNbDossierClos = new JLabel();
		tNbCoulage = new JLabel();
		tNbDNC = new JLabel();
		tNbResNC = new JLabel();
		tNbRetard = new JLabel();
		tNbPrep = new JLabel();
		tNbAEcraser = new JLabel();
		tNbCool = new JLabel();

		pb1.gridx = 1;
		pb1.gridy = 0;
		pb1.insets = new Insets(5, 15, 0, 0);
		pb1.anchor = GridBagConstraints.FIRST_LINE_START;
		pBox1.add(tNbDossier, pb1);
		pb1.gridy = 1;
		pBox1.add(tNbDossierClos, pb1);
		pb1.gridy = 2;
		pBox1.add(tNbCoulage, pb1);
		pb1.gridy = 3;
		pBox1.add(tNbDNC, pb1);
		pb1.gridy = 4;
		pBox1.add(tNbResNC, pb1);
		pb1.gridy = 5;
		pBox1.add(tNbRetard, pb1);
		pb1.gridy = 6;
		pBox1.add(tNbPrep, pb1);
		pb1.gridy = 7;
		pBox1.add(tNbAEcraser, pb1);
		pb1.gridy = 8;
		pBox1.add(tNbCool, pb1);

		pBox1.setPreferredSize(new Dimension(150, 215));

		// acutalisation des données sur la base EM
		// nb total de dossier
		try {
			connect = connexion.getConnection(); // fait appel a la classe connexion
			String SC1 = "SELECT COUNT(PF) AS NB_PF  FROM table_em ";
			PreparedStatement psC1 = (PreparedStatement) connect.prepareStatement(SC1);
			ResultSet rsC1 = psC1.executeQuery(); // resultat de la selection
			while (rsC1.next()) {
				tNbDossier.setText(rsC1.getString("NB_PF"));
			}
			// ferme les connexions
			rsC1.close();
			psC1.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// nb total de dossier clos
		try {
			connect = connexion.getConnection(); // fait appel a la classe connexion
			String SC2 = "SELECT COUNT(STATUT) AS NB_DC  FROM table_em WHERE STATUT = 'TERMINE'";
			PreparedStatement psC2 = (PreparedStatement) connect.prepareStatement(SC2);
			ResultSet rsC2 = psC2.executeQuery(); // resultat de la selection
			while (rsC2.next()) {
				tNbDossierClos.setText(rsC2.getString("NB_DC"));
			}
			// ferme les connexions
			rsC2.close();
			psC2.close();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// nb total coulage a faire
		try {
			connect = connexion.getConnection(); // fait appel a la classe connexion
			String SC3 = "SELECT COUNT(PF) AS NB_COUL  FROM table_em WHERE D_COUL IS NULL  ";
			PreparedStatement psC3 = (PreparedStatement) connect.prepareStatement(SC3);

			ResultSet rsC3 = psC3.executeQuery(); // resultat de la selection
			while (rsC3.next()) {
				tNbCoulage.setText(rsC3.getString("NB_COUL"));
			}
			// ferme les connexions
			rsC3.close();
			psC3.close();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		// nb de densite nc
		try {
			connect = connexion.getConnection(); // fait appel a la classe connexion
			String SC4 = "SELECT COUNT(PF) AS NB_RNC  FROM table_em WHERE CONF_R = 'NC'  ";
			PreparedStatement psC4 = (PreparedStatement) connect.prepareStatement(SC4);

			ResultSet rsC4 = psC4.executeQuery(); // resultat de la selection
			while (rsC4.next()) {
				tNbResNC.setText(rsC4.getString("NB_RNC"));
			}
			// ferme les connexions
			rsC4.close();
			psC4.close();
		} catch (Exception e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		// nb de res nc
		try {
			connect = connexion.getConnection(); // fait appel a la classe connexion
			String SC5 = "SELECT COUNT(PF) AS NB_DNC  FROM table_em WHERE CONF_D = 'NC'  ";
			PreparedStatement psC5 = (PreparedStatement) connect.prepareStatement(SC5);

			ResultSet rsC5 = psC5.executeQuery(); // resultat de la selection
			while (rsC5.next()) {
				tNbDNC.setText(rsC5.getString("NB_DNC"));
			}
			// ferme les connexions
			rsC5.close();
			psC5.close();
		} catch (Exception e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}

		// nb serie en retard
		try {
			connect = connexion.getConnection(); // fait appel a la classe connexion
			String SC6 = "SELECT COUNT(PF) AS NB_RET  FROM table_em WHERE STATUT = 'EN RETARD'  ";
			PreparedStatement psC6 = (PreparedStatement) connect.prepareStatement(SC6);

			ResultSet rsC6 = psC6.executeQuery(); // resultat de la selection
			while (rsC6.next()) {
				tNbRetard.setText(rsC6.getString("NB_RET"));
			}
			// ferme les connexions
			rsC6.close();
			psC6.close();
		} catch (Exception e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}

		// nb serie a preparer
		try {
			connect = connexion.getConnection(); // fait appel a la classe connexion
			String SC7 = "SELECT COUNT(PF) AS NB_PREP  FROM table_em WHERE STATUT = 'A PREPARER'  ";
			PreparedStatement psC7 = (PreparedStatement) connect.prepareStatement(SC7);

			ResultSet rsC7 = psC7.executeQuery(); // resultat de la selection
			while (rsC7.next()) {
				tNbPrep.setText(rsC7.getString("NB_PREP"));
			}
			// ferme les connexions
			rsC7.close();
			psC7.close();
		} catch (Exception e7) {
			// TODO Auto-generated catch block
			e7.printStackTrace();
		}

		// nb serie a casser

		try {
			connect = connexion.getConnection(); // fait appel a la classe connexion
			String SC8 = "SELECT COUNT(PF) AS NB_CAS  FROM table_em WHERE STATUT = 'A CASSER'  ";
			PreparedStatement psC8 = (PreparedStatement) connect.prepareStatement(SC8);

			ResultSet rsC8 = psC8.executeQuery(); // resultat de la selection
			while (rsC8.next()) {
				tNbAEcraser.setText(rsC8.getString("NB_CAS"));
			}
			// ferme les connexions
			rsC8.close();
			psC8.close();
		} catch (Exception e8) {
			// TODO Auto-generated catch block
			e8.printStackTrace();
		}

		// nb serie ouvert
		try {
			connect = connexion.getConnection(); // fait appel a la classe connexion
			String SC9 = "SELECT COUNT(PF) AS NB_COOL  FROM table_em WHERE STATUT = 'COOL'  ";
			PreparedStatement psC9 = (PreparedStatement) connect.prepareStatement(SC9);

			ResultSet rsC9 = psC9.executeQuery(); // resultat de la selection
			while (rsC9.next()) {
				tNbCool.setText(rsC9.getString("NB_COOL"));
			}
			// ferme les connexions
			rsC9.close();
			psC9.close();
		} catch (Exception e9) {
			// TODO Auto-generated catch block
			e9.printStackTrace();
		}

	}

	private void viewBox2() {
		pBox2Border = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),
				"FONCTIONS", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,
				new Font("Time new Roman", Font.BOLD, 14));
		pBox2.setBorder(pBox2Border);

		pBox2.setLayout(new BoxLayout(pBox2, BoxLayout.Y_AXIS));
		pBox2.setPreferredSize(new Dimension(200, 215));

		// def bouton
		InsertEssai = new JButton();
		ValidEssai = new JButton();
		refresh = new JButton();
		rechercher = new JButton();
		graph = new JButton();
		export = new JButton();

		// icon bouton
		ImageIcon ajout_icon = new ImageIcon(getClass().getResource(definition.URL_AJOUTER_P));
		ajout_label = new JLabel("AJOUT ESSAI", ajout_icon, JLabel.CENTER);
		InsertEssai.add(ajout_label);

		ImageIcon valider_icon = new ImageIcon(getClass().getResource(definition.URL_VALIDER_P));
		valider_label = new JLabel("VALIDER ESSAI", valider_icon, JLabel.CENTER);
		ValidEssai.add(valider_label);

		ImageIcon refresh_icon = new ImageIcon(getClass().getResource(definition.URL_RAFRAICHIR_P));
		refresh_label = new JLabel("RAFRAICHIR TAB", refresh_icon, JLabel.CENTER);
		refresh.add(refresh_label);

		ImageIcon rech_icon = new ImageIcon(getClass().getResource(definition.URL_RECHERCHER_P));
		rech_label = new JLabel("RECHERCHER", rech_icon, JLabel.CENTER);
		rechercher.add(rech_label);

		ImageIcon graph_icon = new ImageIcon(getClass().getResource(definition.URL_GRAPH_P));
		graph_label = new JLabel("VOIR GRAPHIQUE", graph_icon, JLabel.CENTER);
		graph.add(graph_label);

		ImageIcon export_icon = new ImageIcon(getClass().getResource(definition.URL_PRINT_P));
		export_label = new JLabel("EDITER RAPPORT", export_icon, JLabel.CENTER);
		export.add(export_label);

		// location bouton

		InsertEssai.setAlignmentX(Component.CENTER_ALIGNMENT);
		ValidEssai.setAlignmentX(Component.CENTER_ALIGNMENT);
		refresh.setAlignmentX(Component.CENTER_ALIGNMENT);
		rechercher.setAlignmentX(Component.CENTER_ALIGNMENT);
		graph.setAlignmentX(Component.CENTER_ALIGNMENT);
		export.setAlignmentX(Component.CENTER_ALIGNMENT);

		InsertEssai.setMaximumSize(new Dimension(definition.butEM_Large, definition.butEM_Haut));
		ValidEssai.setMaximumSize(new Dimension(definition.butEM_Large, definition.butEM_Haut));
		refresh.setMaximumSize(new Dimension(definition.butEM_Large, definition.butEM_Haut));
		rechercher.setMaximumSize(new Dimension(definition.butEM_Large, definition.butEM_Haut));
		graph.setMaximumSize(new Dimension(definition.butEM_Large, definition.butEM_Haut));
		export.setMaximumSize(new Dimension(definition.butEM_Large, definition.butEM_Haut));

		pBox2.add(Box.createVerticalStrut(5));
		pBox2.add(InsertEssai);
		pBox2.add(Box.createVerticalStrut(5));
		pBox2.add(ValidEssai);
		pBox2.add(Box.createVerticalStrut(5));
		pBox2.add(refresh);
		pBox2.add(Box.createVerticalStrut(5));
		pBox2.add(graph);
		pBox2.add(Box.createVerticalStrut(5));
		pBox2.add(rechercher);
		pBox2.add(Box.createVerticalStrut(5));
		pBox2.add(export);
		pBox2.add(Box.createVerticalStrut(8));

		// actionlistern
		InsertEssai.addActionListener(new fonctionAjout());
		rechercher.addActionListener(new fonctionRech());
		graph.addActionListener(new fonctionGraph());
		ValidEssai.addActionListener(new fonctionValid());
		refresh.addActionListener(new fonctionRefresh());

		// activation bouton
		ValidEssai.setEnabled(false);

	}

	private void viewBox3() {
		pBox3Border = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),
				"PANNEAU AFFICHAGE", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,
				new Font("Time new Roman", Font.BOLD, 14));
		pBox3.setBorder(pBox3Border);
		pBox3.setPreferredSize(new Dimension(500, 215));

		pBox3.setLayout(cLayout);

		// creation des cards

		pCard0 = new JPanel();
		pCard1 = new JPanel();
		pCard2 = new JPanel();
		pCard3 = new JPanel();

		// pCard0
		pCard0.setOpaque(false);

		// card1 - ajouter essai
		pCard1.setOpaque(false);
		pCard1.setLayout(new GridBagLayout());

		GridBagConstraints cem = new GridBagConstraints();

		// def des composant
		JLabel titre = new JLabel("<html><font size=4><U>Ajouter un nouvel essai mecanique : </U></font></html>");
		JLabel name = new JLabel("Nom du Produit : ");
		JLabel operateur = new JLabel("Operateur : ");
		JLabel date_gachage = new JLabel("Date de Coulage : ");
		JLabel date_fab = new JLabel("Date de Fabrication : ");

		// position des composant
		cem.gridx = 0; // col 0;
		cem.gridy = 0;
		cem.insets = new Insets(0, 0, 30, 0);
		pCard1.add(titre, cem);
		cem.anchor = GridBagConstraints.FIRST_LINE_END;
		cem.insets = new Insets(0, 10, 5, 0);
		cem.gridy = 1;
		pCard1.add(name, cem);
		cem.gridy = 2;
		pCard1.add(operateur, cem);
		cem.gridy = 3;
		pCard1.add(date_gachage, cem);
		cem.gridy = 4;
		pCard1.add(date_fab, cem);

		// decl des compososant col 1
		cbname = new JComboBox<>();
		ope = new JTextField(5);
		date_Tgachage = new JTextField(5);
		date_Tfab = new JTextField(5);

		// posiition des composant col 1
		cem.gridx = 1; // col 0;
		cem.gridy = 1;
		cem.anchor = GridBagConstraints.FIRST_LINE_START;
		pCard1.add(cbname, cem);
		cem.gridy = 2;
		pCard1.add(ope, cem);
		cem.gridy = 3;
		pCard1.add(date_Tgachage, cem);
		cem.gridy = 4;
		pCard1.add(date_Tfab, cem);

		// decl composant col 2
		JLabel lot_PF = new JLabel("N° de lot PF : ");
		JLabel lot_Associe = new JLabel("N° de lot Poudre/Res : ");

		// position
		cem.gridx = 2;
		cem.gridy = 2;
		cem.anchor = GridBagConstraints.FIRST_LINE_END;
		pCard1.add(lot_PF, cem);
		cem.gridy = 3;
		pCard1.add(lot_Associe, cem);

		// decl composant col3
		lot_PF2 = new JTextField(5);
		lot_Associe2 = new JTextField(5);

		// position
		cem.gridx = 3;
		cem.gridy = 2;
		cem.anchor = GridBagConstraints.FIRST_LINE_START;
		pCard1.add(lot_PF2, cem);
		cem.gridy = 3;
		pCard1.add(lot_Associe2, cem);

		// card2 - rechercher
		pCard2.setOpaque(false);
		// creation composant
		JLabel L_text = new JLabel("<html><font size=4>Recherche Essai : </font></html>");
		T_text = new JTextField(20);
		JButton Rechercher = new JButton("RECHERCHER");

		Rechercher.addActionListener(new RechTab());

		// ajout des composant
		pCard2.add(L_text);
		pCard2.add(T_text);
		pCard2.add(Rechercher);

		// card3 - graphique

		pCard3.setLayout(new BorderLayout());
		JPanel pCard3_North = new JPanel();
		JPanel pCard3_Center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pCard3_South = new JPanel();

		pCard3.setOpaque(false);
		pCard3_North.setOpaque(false);
		pCard3_Center.setOpaque(false);
		pCard3_South.setOpaque(false);

		JLabel type = new JLabel(
				"<html><font size=4><U>Visualiser sous forme graphique les données de l'anée en cours</U></font></html>");
		JLabel selection = new JLabel("Selection du produit dans la table : ");
		cmortier = new JComboBox<>();
		cmortier.setPreferredSize(new Dimension(200, 20));
		JButton G_Densite = new JButton("Courbe Densite");
		JButton G_Rf = new JButton("Courbe Res. Flexion");
		JButton G_Rc = new JButton("Courbe Res. Compression");
		JButton Cumul_Ep = new JButton("Nb total essais");

		// definition action des boutons
		G_Densite.addActionListener(new AffDensite());
		G_Rf.addActionListener(new AffRf());
		G_Rc.addActionListener(new AffRc());
		Cumul_Ep.addActionListener(new AffTotal());

		// association diff panel au panel princial
		pCard3.add(pCard3_North, BorderLayout.NORTH);
		pCard3.add(pCard3_Center, BorderLayout.CENTER);
		pCard3.add(pCard3_South, BorderLayout.SOUTH);

		// assocoation composant au panels
		pCard3_North.add(type);
		pCard3_Center.add(selection);
		pCard3_Center.add(cmortier);
		pCard3_South.add(G_Densite);
		pCard3_South.add(G_Rf);
		pCard3_South.add(G_Rc);
		pCard3_South.add(Cumul_Ep);

		// assocation panel cards au different card
		pBox3.add(pCard0, Liste_Card[0]); // panneau vide lancement de la fenetre
		pBox3.add(pCard1, Liste_Card[1]);
		pBox3.add(pCard2, Liste_Card[2]);
		pBox3.add(pCard3, Liste_Card[3]);

	}

	private void viewCenter() {

		// creation tab pour l'ensemble des essais
		tab_EssSupp = new JTable();

		Object[] col_EssSupp = { "PROD_FINI", "METODE GACHAGE", "QTITE RESINE", "QTITE EAU", "QTITE POUDRE",
				"OPERATEUR", "DATE COULAGE", "DATE FAB", "N° DE LOT PF", "N° LOT POUDRE/RESINE", "ETAT AVANCEMENT",
				"DATE DE CASSE", "MASSE_EP1", "MASSE_EP2", "MASSE_EP3", "R_FLEX_EP1", "R_FLEX_EP2", "R_FLEX_EP3",
				"R_COMP_EP11", "R_COMP_EP12", "R_COMP_EP21", "R_COMP_EP22", "R_COMP_EP31", "R_COMP_EP32",
				"CONFORMITE DENSITE", "CONFORMITE RE_COMPRESSION", "DENSITE_EP1", "DENSITE_EP2", "DENSITE_EP3",
				"DENSITE_MOY", "R_FLEX_MOY", "R_COMP_MOY", "ECART TYPE_DENSITE", "ECART TYPE_FLEX", "ECART TYPE_COMP",
				"ANNEE", "DENSITE_MIN", "DENSITE_MAX", "R_COMP_MIN" };

		// model tabke
		model_EssSupp = new DefaultTableModel();
		model_EssSupp.setColumnIdentifiers(col_EssSupp);

		// caracteritique table
		tab_EssSupp.setModel(model_EssSupp);
		tab_EssSupp.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 11));
		tab_EssSupp.setShowHorizontalLines(false);

		tab_EssSupp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// ajout defilement
		JScrollPane scp_10 = new JScrollPane(tab_EssSupp);
		scp_10.getViewport().setOpaque(false);
		scp_10.setPreferredSize(new Dimension(definition.scp_10_Large, definition.scp_10_Haut));
		pane_C.add(scp_10);

		row_EM = new Object[39];

	}

	// **********************************
	// **** ACTION SUR LES BOUTONS ******
	// *********************************
	public class fonctionAjout implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cLayout.show(pBox3, Liste_Card[1]);
			ValidEssai.setEnabled(true);
		}
	}

	public class fonctionRech implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cLayout.show(pBox3, Liste_Card[2]);
			ValidEssai.setEnabled(false);
		}
	}

	public class fonctionGraph implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cLayout.show(pBox3, Liste_Card[3]);
			ValidEssai.setEnabled(false);
		}
	}

	public class fonctionValid implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// Condition pour determiner si les champs sont bien rempli pour enregisterer
			// essai
			if (ope.getText().isEmpty() || date_Tfab.getText().isEmpty() || date_Tgachage.getText().isEmpty()
					|| lot_PF2.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Vous avez oublié de remplir une ou plusieurs case(s)",
						"MANQUE INFORMATION", JOptionPane.ERROR_MESSAGE);
			} else {
				int i = JOptionPane.showConfirmDialog(null,
						"Vous est sur le point d'ajouter un nouvel essai complemantaire du  : "
								+ cbname.getSelectedItem() + " ? ",
						"BOITE DE CONFIRMATION", JOptionPane.OK_CANCEL_OPTION);
				if (i == 0) {

					// 1- Selection des données de la liste des critere de conformite a importer
					// dans la table_em
					try {

						connect = connexion.getConnection(); // fait appel a la classe connexion
						String temp = "SELECT * FROM LISTE_PARA WHERE NOM=? "; // selecton type de donne de la base
						PreparedStatement ptemp = (PreparedStatement) connect.prepareStatement(temp);
						ptemp.setString(1, (String) cbname.getSelectedItem());
						ResultSet rs_temp = ptemp.executeQuery(); // resultat de la selection

						while (rs_temp.next()) {
							Methode = rs_temp.getString(3);
							Q_eau = rs_temp.getString(4);
							Q_res = rs_temp.getString(5);
							Q_poudre = rs_temp.getString(6);
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// 2- Va récuperer l'ID_PF dans la table des PF pour ajouter a la colonne dans
					// la table_em
					try {
						connect = connexion.getConnection();
						String Id_recupPF = "SELECT ID FROM TABLE_PF WHERE NOM = ? AND DATE_C = ?";
						PreparedStatement id_state = (PreparedStatement) connect.prepareStatement(Id_recupPF);
						id_state.setString(1, (String) cbname.getSelectedItem());
						id_state.setString(2, date_Tfab.getText());
						ResultSet rs_id = id_state.executeQuery();
						while (rs_id.next()) {
							no_id = rs_id.getString(1);
						}

					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					// 3- Insertion dans la table_em des donnée récupérer en (1) et celle dans le
					// formulaire par l'utilisateur
					try {

						connect = connexion.getConnection(); // fait appel a la classe connexion
						String StAjout = "INSERT into TABLE_EM (ID_PF,PF,METHODE,Q_RES,Q_EAU,Q_POUDRE,RES,D_COUL,D_FAB,N_LOT,N_LOT_C,STATUT,D_CASSE)"
								+ "  VALUES (?,?,?,?,?,?,?,?,?,?,?,?, DATE_ADD(?, INTERVAL 28 DAY))  "; // 11

						PreparedStatement psA = (PreparedStatement) connect.prepareStatement(StAjout);

						// champs info
                        if(no_id != null ) {
						   psA.setString(1, no_id);
                          } else {
                        	  psA.setString(1, "0");
                        }
						psA.setString(2, (String) cbname.getSelectedItem());
						psA.setString(3, Methode);
						psA.setString(4, Q_res);
						psA.setString(5, Q_eau);
						psA.setString(6, Q_poudre);
						psA.setString(7, ope.getText());
						psA.setString(8, date_Tgachage.getText());
						psA.setString(9, date_Tfab.getText());
						psA.setString(10, lot_PF2.getText());
						psA.setString(11, lot_Associe2.getText());
						psA.setString(12, "COOL");
						psA.setString(13, date_Tgachage.getText()); // permet d'avoir la date a 28 jours

						psA.executeUpdate();
						psA.close();
						JOptionPane.showMessageDialog(null, "Esai Mecanique Ajouté pour le : "
								+ cbname.getSelectedItem() + " a été ajouter à la liste.");

					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}

					clearChamp(); // fonction effacer champs de frappe (
				}
			}
		}
	}

	public class fonctionRefresh implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ValidEssai.setEnabled(false);
			// initialise tableau
			model_EssSupp.setRowCount(0);

			// code a avenir
			try {
				connect = connexion.getConnection(); // fait appel a la classe connexion
				String tableem = "SELECT * FROM TABLE_EM ORDER BY D_CASSE DESC ";
				PreparedStatement pstable = (PreparedStatement) connect.prepareStatement(tableem);
				ResultSet rs = pstable.executeQuery(); // resultat de la selection
				while (rs.next()) {
					int y = 3; // pour remplir le tableau
					for (int x = 0; x < 39; x++) {
						row_EM[x] = rs.getString(y);
						y++;
					}
					model_EssSupp.addRow(row_EM);
				}

				// ferme les connexions
				rs.close();
				pstable.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	// **** class sous bouton du menu
	public class RechTab implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ValidEssai.setEnabled(false);
			// code a avenir
		}
	}

	public class AffDensite implements ActionListener { // courbe densite
		public void actionPerformed(ActionEvent e) {

			// affiche d'un graph
			DefaultCategoryDataset data = new DefaultCategoryDataset();

			// row keys
			String serie = (String) cmortier.getSelectedItem();

			// database connection
			try {
				connect = connexion.getConnection(); // fait appel a la classe connexion
				String Schart = "SELECT  D_MOY, D_COUL,D_MIN,D_MAX FROM TABLE_EM WHERE PF = ?";
				PreparedStatement psChart = (PreparedStatement) connect.prepareStatement(Schart);
				psChart.setString(1, (String) cmortier.getSelectedItem());
				ResultSet rs = psChart.executeQuery(); // resultat de la selection
				while (rs.next()) {
					data.addValue((Double) rs.getDouble("D_MOY"), serie, rs.getString("D_COUL"));
					data.addValue((Double) rs.getDouble("D_MIN"), "MIN", rs.getString("D_COUL"));
					data.addValue((Double) rs.getDouble("D_MAX"), "MAX", rs.getString("D_COUL"));
				}

				// ferme les connexions
				rs.close();
				psChart.close();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// create a chart...
			JFreeChart chart = ChartFactory.createLineChart("Courbe de Densite", "Date", "Densite", data,
					PlotOrientation.VERTICAL, true, true, false);
			chart.setBackgroundPaint(Color.WHITE);

			CategoryPlot plot = chart.getCategoryPlot();
			// customise the range axis...
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

			rangeAxis.setAutoRange(true);
			rangeAxis.setAutoRangeIncludesZero(false);
			rangeAxis.setUpperMargin(0.5);

			// create and display a frame...
			ChartFrame Gframe = new ChartFrame("First", chart);
			Gframe.pack();
			Gframe.setVisible(true);
		}
	}

	public class AffRf implements ActionListener { // courbe resistance flexion
		public void actionPerformed(ActionEvent e) {
			// affiche d'un graph
			DefaultCategoryDataset data = new DefaultCategoryDataset();

			// row keys
			String serie = (String) cmortier.getSelectedItem();

			// database connection
			try {
				connect = connexion.getConnection(); // fait appel a la classe connexion
				String Schart = "SELECT  RF_MOY, D_COUL FROM TABLE_EM WHERE PF = ?";
				PreparedStatement psChart = (PreparedStatement) connect.prepareStatement(Schart);
				psChart.setString(1, (String) cmortier.getSelectedItem());
				ResultSet rs = psChart.executeQuery(); // resultat de la selection
				while (rs.next()) {
					data.addValue((Double) rs.getDouble("RF_MOY"), serie, rs.getString("D_COUL"));
				}

				// ferme les connexions
				rs.close();
				psChart.close();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// create a chart...
			JFreeChart chart = ChartFactory.createLineChart("Courbe Resistance Flexion", "Date", "MPa", data,
					PlotOrientation.VERTICAL, true, true, false);
			chart.setBackgroundPaint(Color.WHITE);

			CategoryPlot plot = chart.getCategoryPlot();
			// customise the range axis...
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

			rangeAxis.setAutoRange(true);
			rangeAxis.setAutoRangeIncludesZero(false);
			rangeAxis.setUpperMargin(0.5);

			// create and display a frame...
			ChartFrame Gframe = new ChartFrame("First", chart);
			Gframe.pack();
			Gframe.setVisible(true);
		}
	}

	public class AffRc implements ActionListener { // courbe resistance compression
		public void actionPerformed(ActionEvent e) {
			// affiche d'un graph
			DefaultCategoryDataset data = new DefaultCategoryDataset();

			// row keys
			String serie = (String) cmortier.getSelectedItem();

			// database connection
			try {
				connect = connexion.getConnection(); // fait appel a la classe connexion
				String Schart = "SELECT  RC_MOY, D_COUL,RC_MIN FROM TABLE_EM WHERE PF = ?";
				PreparedStatement psChart = (PreparedStatement) connect.prepareStatement(Schart);
				psChart.setString(1, (String) cmortier.getSelectedItem());
				ResultSet rs = psChart.executeQuery(); // resultat de la selection
				while (rs.next()) {
					data.addValue((Double) rs.getDouble("RC_MOY"), serie, rs.getString("D_COUL"));
					data.addValue((Double) rs.getDouble("RC_MIN"), "MIN", rs.getString("D_COUL"));

				}

				// ferme les connexions
				rs.close();
				psChart.close();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// create a chart...
			JFreeChart chart = ChartFactory.createLineChart("Courbe Resistance à la compression", "Date", "MPa", data,
					PlotOrientation.VERTICAL, true, true, false);
			chart.setBackgroundPaint(Color.WHITE);

			CategoryPlot plot = chart.getCategoryPlot();
			// customise the range axis...
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

			rangeAxis.setAutoRange(true);
			rangeAxis.setAutoRangeIncludesZero(false);
			rangeAxis.setUpperMargin(0.5);

			// create and display a frame...
			ChartFrame Gframe = new ChartFrame("First", chart);
			Gframe.pack();
			Gframe.setVisible(true);
		}
	}

	public class AffTotal implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// code a avenir

			// affiche d'un graph
			DefaultCategoryDataset data_bar = new DefaultCategoryDataset();

			// database connection
			try {
				connect = connexion.getConnection(); // fait appel a la classe connexion
				String Schart = "SELECT PF,COUNT(*) AS NB FROM table_em GROUP BY PF ";
				PreparedStatement psChart = (PreparedStatement) connect.prepareStatement(Schart);
				ResultSet rs = psChart.executeQuery(); // resultat de la selection

				while (rs.next()) {
					data_bar.addValue(Double.parseDouble(rs.getString("NB")), rs.getString("PF"),
							(String) "Type de Mortier CE/NF");
				}

				// ferme les connexions
				rs.close();
				psChart.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// create a chart...
			JFreeChart barChart = ChartFactory.createBarChart("Nombre Essai Mécanique par Mortier", "Mortier", "Nombre",
					data_bar, PlotOrientation.VERTICAL, true, true, false);

			// get a reference to the plot for further customisation...
			CategoryPlot plot = barChart.getCategoryPlot();

			// set the range axis to display integers only...
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			// create and display a frame...
			ChartFrame Gframe = new ChartFrame("First", barChart);
			Gframe.pack();
			Gframe.setLocationRelativeTo(null);
			Gframe.setVisible(true);

		}
	}

	// **************************
	// ***** FONCTION DIVERS ***
	// ***************************

	// effacer les jtextfield de mode <AJOUT>
	private void clearChamp() {
		ope.setText("");
		date_Tfab.setText("");
		date_Tgachage.setText("");
		lot_PF2.setText("");

	}

	// charge liste des mortiers pour ajouter essai mecanique
	private void ChargeNom() {

		try {
			cbname.removeAllItems(); // supprime element residuel
			connect = connexion.getConnection(); // initialisation connection

			String sql = "SELECT NOM FROM MORTAR_LISTE ORDER BY NOM"; // selecton type de donne de la base
			PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);

			ResultSet rs = pState.executeQuery(); // resultat de la selection
			// ajout element 1 a 1
			while (rs.next()) {
				cbname.addItem(rs.getString("NOM"));
			}
			// ferme les connexions
			cbname.setSelectedIndex(-1);
			rs.close();
			pState.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// charge liste des mortiers de la table EM (essai mecanique present) => dans
	// card graph
	private void ChargeMortier() {

		try {
			cmortier.removeAllItems(); // supprime element residuel
			connect = connexion.getConnection(); // initialisation connection

			String sql = "SELECT DISTINCT PF FROM TABLE_EM "; // selecton type de donne de la base
			PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);

			ResultSet rs = pState.executeQuery(); // resultat de la selection
			// ajout element 1 a 1
			while (rs.next()) {
				cmortier.addItem(rs.getString("PF"));
			}
			// ferme les connexions
			cmortier.setSelectedIndex(-1);
			rs.close();
			pState.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
