package com.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import connexion.BD.connexion;

//Cette Class permet de creer l'environement de l'application
// Definit l'ensemble des menus, action sur les boutons, position les panneau d'affichage
// Permet d'afficher des infos avec des requettes SQL

public class prime extends Habillage {

	private static Connection connect; // creer varaible connect

	// declaration des variables
	JFrame fen;
	Image img, img2;
	protected JPanel main, mainN, mainW, mainW1, mainW2, mainW12, mainS, mainC, cl4_center;
	private JLabel logoImg, newMP_label, newPF_label, baseMP_label, basePF_label, baseEM_label, resfresh_label,
			butview_label;
	private JLabel Lnom, Lmes, Lmass, Lep1, Lep2, Lep3, Ldens, Letd, Lrf, Lrc, Lc, Ldm, Lec1, Lec2, Lp, Ldmin, Ldmax,
			Lrmin;
	private JLabel Lconf, Lcd, Lcc, Lcd2, Lcc2, Lrcm, Lrfm;
	private JLabel editer_label, calculer_label, transf_label, hist_label, editp_label;
	private JTextField Tnom, tm1, tm2, tm3, td1, td2, td3, Tetd, Trf1, Trc11, Trc12, Trf2, Trc21, Trc22, Trf3, Trc31,
			Trc32;
	private JTextField Tdm, Tec1, Tec2, Tdmin, Tdmax, Trmin, Trcm, Trfm;
	private JTextField NbtotalMP_txt, NbtotalPF_txt, NbtotalEp_txt, QtitetotalPoudre_txt, QtitetotalPate_txt,
			QtitetotalLiquide_txt;
	private JTextField TNbFicheMPActive, TNbFichePFActive, TNbFicheMPArch, TNbFichePFArch;
	private JTextField Pos1_txt, Pos2_txt, Pos3_txt, Pos4_txt, Pos5_txt;
	private JButton newMP_BUTTON, newPF_BUTTON, BaseMP_BUTTON, BasePF_BUTTON, Base_EM_BUTTON, refresh_BUTTON, but_view;

	private JMenuBar Barre;
	private JMenu mFichier, mEdition, mCreer, mRechercher, mControler, mOutils, mAide, mListe;
	private JMenuItem iRech_CMP, iControlMP, iControlPF, iRech_EM, iBATPFMP, iBTPFMP, iReset, iTransfert, iApropos;
	private JMenuItem iQuitter, iAlerte, iModif_TPF, iModif_TMP, iCreate_TMP, iCreate_TPF, iRech_CPF, iTATrame, iHistVal;
	private JMenuItem iiListe1, iiListe2, iiListe3, iiListe4, iiListe5, iiListe6, iiListe7, iiListe8;
	private JTextArea zonetxtsud, HistoricDate;
	public JTextArea zonetxtsud2;

	private TitledBorder mainSBorder, cl_4CBorder;
	private JRadioButton radio1, radio2, radio3, radio4, radio5;
	private ButtonGroup radioGroup;
	private JTable recap_jour, recap_mois, recap_sac, MP_encours, PF_encours, MP_syn, PF_syn, tab_ECE, tab_SCE, EM_jour;
	private DefaultTableModel model_EMM, model_sac, model_MP, model_PF, model_MPS, model_PFS, model_ECE, model_SCE,
			model_EMJ, model_rec;
	Object[] MP_row1, PF_row1, MPS_row, PFS_row, EMJ_row, row_ECE, row_SCE, recap_day, recap_month, recap_bag;
	final static String CARD1 = "card1";
	final static String CARD2 = "card2";
	final static String CARD3 = "card3";
	final static String CARD4 = "card4";
	final static String CARD5 = "card5";
	final CardLayout cardLayout = new CardLayout();
	public static int recup_id = 0;
	private String recup_nom, recup_date;

	// constructeur
	public prime() {
		createview();
	}

	// creation de la fenetre
	protected void createview() {
		fen = new JFrame("QWANT-LAB");
		fen.setSize(definition.WLarge, definition.WHaut);
		fen.setLocationRelativeTo(null);
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setResizable(false);
		// main = new PicturePanel();
		main = new Habillage.PrimePanel();
		main.setLayout(new BorderLayout());

		mainW = new JPanel();
		mainW.setOpaque(false);
		mainW.setLayout(new BoxLayout(mainW, BoxLayout.Y_AXIS));
		mainN = new JPanel(new FlowLayout(FlowLayout.LEFT));
		mainN.setOpaque(false);
		mainC = new JPanel(cardLayout); // def du cardLayout
		mainC.setOpaque(false);
		mainS = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 5));
		mainS.setOpaque(true);

		mainW1 = new JPanel();
		mainW1.setOpaque(false);
		mainW1.setLayout(new BoxLayout(mainW1, BoxLayout.Y_AXIS));

		mainW12 = new JPanel();
		mainW12.setOpaque(false);
		mainW12.setLayout(new BoxLayout(mainW12, BoxLayout.Y_AXIS));

		mainW2 = new JPanel();
		mainW2.setOpaque(false);
		mainW2.setLayout(new BoxLayout(mainW2, BoxLayout.Y_AXIS));

		mainW.add(mainW1);
		mainW.add(Box.createVerticalStrut(40));
		mainW.add(mainW12);
		mainW.add(Box.createVerticalStrut(40));
		mainW.add(mainW2);

		main.add(mainW, BorderLayout.WEST);
		main.add(mainC, BorderLayout.CENTER);
		main.add(mainN, BorderLayout.NORTH);
		main.add(mainS, BorderLayout.SOUTH);

		// initialisation des methodes
		menu();
		MainWest();
		MainSouth();
		centrale();

		fen.getContentPane().add(main);

	}

	// Description PANNEAU PRINCIPAL SUD
	protected void MainSouth() {

		mainSBorder = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK),
				"CADRE INFORMATION", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION);
		mainS.setBorder(mainSBorder);
		// mainS.setBackground(Color.BLACK);
		mainSBorder.setTitleColor(Color.BLACK);
		mainS.setPreferredSize(new Dimension(definition.MS_Large, definition.MS_Haut));
		mainS.setOpaque(false);

		zonetxtsud = new JTextArea(2, 75);
		zonetxtsud.setForeground(Color.RED);
		zonetxtsud.setBackground(Color.LIGHT_GRAY);
		JScrollPane jsp1 = new JScrollPane(zonetxtsud, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp1.getViewport().setOpaque(false);
		zonetxtsud2 = new JTextArea(2, 60);
		zonetxtsud2.setForeground(Color.BLACK);
		JScrollPane jsp = new JScrollPane(zonetxtsud2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getViewport().setOpaque(false);

		mainS.add(jsp1);
		mainS.add(jsp);
	}

	// Description PANNEU PRINCIPA WEST
	private void MainWest() {

		mainW1.add(Box.createVerticalStrut(40));
		mainW1.add(Box.createRigidArea(new Dimension(25, 0)));
		newMP_BUTTON = new JButton();
		newMP_BUTTON.setMaximumSize(new Dimension(Integer.MAX_VALUE, definition.butP_Large));
		// newMP_BUTTON.setContentAreaFilled(false);
		// newMP_BUTTON.setBorderPainted(false);
		mainW1.add(newMP_BUTTON);

		mainW1.add(Box.createVerticalStrut(10));
		newPF_BUTTON = new JButton();
		newPF_BUTTON.setMaximumSize(new Dimension(Integer.MAX_VALUE, definition.butP_Large));
		mainW1.add(newPF_BUTTON);

		mainW1.add(Box.createVerticalStrut(10));
		BaseMP_BUTTON = new JButton();
		BaseMP_BUTTON.setMaximumSize(new Dimension(Integer.MAX_VALUE, definition.butP_Large));
		mainW1.add(BaseMP_BUTTON);

		mainW1.add(Box.createVerticalStrut(10));
		BasePF_BUTTON = new JButton();
		BasePF_BUTTON.setMaximumSize(new Dimension(Integer.MAX_VALUE, definition.butP_Large));
		mainW1.add(BasePF_BUTTON);

		mainW1.add(Box.createVerticalStrut(10));
		Base_EM_BUTTON = new JButton();
		Base_EM_BUTTON.setMaximumSize(new Dimension(Integer.MAX_VALUE, definition.butP_Large));
		mainW1.add(Base_EM_BUTTON);

		mainW12.add(Box.createRigidArea(new Dimension(25, 0)));
		refresh_BUTTON = new JButton();
		refresh_BUTTON.setMaximumSize(new Dimension(Integer.MAX_VALUE, definition.butP_Large));
		mainW12.add(refresh_BUTTON);

		// ajout iamge sur les button du menu

		ImageIcon newMP_icon = new ImageIcon(getClass().getResource(definition.URL_CONTROLEMP));
		newMP_label = new JLabel("CONTOLER MP", newMP_icon, JLabel.CENTER);
		newMP_BUTTON.add(newMP_label);

		ImageIcon newPF_icon = new ImageIcon(getClass().getResource(definition.URL_CONTROLEPF));
		newPF_label = new JLabel("CONTROLER PF", newPF_icon, JLabel.CENTER);
		newPF_BUTTON.add(newPF_label);

		ImageIcon baseMP_icon = new ImageIcon(getClass().getResource(definition.URL_BASEMP));
		baseMP_label = new JLabel("BASE MP", baseMP_icon, JLabel.CENTER);
		BaseMP_BUTTON.add(baseMP_label);

		ImageIcon basePF_icon = new ImageIcon(getClass().getResource(definition.URL_BASEPF));
		basePF_label = new JLabel("BASE PF", basePF_icon, JLabel.CENTER);
		BasePF_BUTTON.add(basePF_label);

		ImageIcon baseEM_icon = new ImageIcon(getClass().getResource(definition.URL_BASEEM));
		baseEM_label = new JLabel("ESSAI MECANIQUE", baseEM_icon, JLabel.CENTER);
		Base_EM_BUTTON.add(baseEM_label);

		ImageIcon refresh_icon = new ImageIcon(getClass().getResource(definition.URL_RAFRAICHIR));
		resfresh_label = new JLabel("ACTUALISER ALERTE", refresh_icon, JLabel.CENTER);
		refresh_BUTTON.add(resfresh_label);

		// contenu menu affichage rapide
		mainW2.add(Box.createVerticalStrut(10));
		mainW2.add(Box.createVerticalStrut(25));

		radioGroup = new ButtonGroup(); // initialise le group

		// creer les jradio
		radio1 = new JRadioButton("CONTROLE EN COURS MP/PF");
		radio2 = new JRadioButton("SYNTHESE MP/PF");
		radio3 = new JRadioButton("ESSAI SUP MORTIER CE");
		radio4 = new JRadioButton("PROCHAINS ESSAIS MECANIQUES");
		radio5 = new JRadioButton("STATISTIQUES ");

		radio1.setForeground(Color.BLACK);
		radio2.setForeground(Color.BLACK);
		radio3.setForeground(Color.BLACK);
		radio4.setForeground(Color.BLACK);
		radio5.setForeground(Color.BLACK);

		radioGroup.add(radio1); // ajout au groupe
		radioGroup.add(radio2);
		radioGroup.add(radio3);
		radioGroup.add(radio4);
		radioGroup.add(radio5);

		mainW2.add(radio1); // affiche sur le panel
		mainW2.add(radio2);
		mainW2.add(radio3);
		mainW2.add(radio4);
		mainW2.add(radio5);

		radio1.setOpaque(false); // rend transparent le fond
		radio1.setSelected(true);
		radio2.setOpaque(false);
		radio3.setOpaque(false);
		radio4.setOpaque(false);
		radio5.setOpaque(false);

		// Association Action sur les bouton
		newMP_BUTTON.addActionListener(new AJOUTER_MP());
		newPF_BUTTON.addActionListener(new AJOUTER_PF());
		BaseMP_BUTTON.addActionListener(new AFFICHER_BMP());
		BasePF_BUTTON.addActionListener(new AFFICHER_BPF());
		Base_EM_BUTTON.addActionListener(new AFFICHER_EM());
		refresh_BUTTON.addActionListener(new REFRESH_ALL());

	}

	// creation barre des menus
	private void menu() {
		Barre = new JMenuBar(); // creer barre des menu

		// creer les titres
		mFichier = new JMenu("Fichier");
		mEdition = new JMenu("Modifier");
		mCreer = new JMenu("Créer");
		mRechercher = new JMenu("Rechercher");
		mOutils = new JMenu("Outils");
		mControler = new JMenu("Contrôler");
		mAide = new JMenu("?");
		// sous menu
		mListe = new JMenu("Liste de Choix");

		// *************creer les item*****************

		iQuitter = new JMenuItem("Quitter"); // FICHIER
		iModif_TMP = new JMenuItem("Version Trame MP"); // EDITION
		iModif_TPF = new JMenuItem("Version Trame PF");

		iCreate_TMP = new JMenuItem("Créer Trame MP"); // CREER
		iCreate_TPF = new JMenuItem("Créer Trame PF");
		iRech_CPF = new JMenuItem("Controle PF"); // RECHERCHE
		iRech_CMP = new JMenuItem("Controle MP");
		iBTPFMP = new JMenuItem("Trame MP/PF");
		iBATPFMP = new JMenuItem("Archive Trame MP/PF");
		iRech_EM = new JMenuItem("Essai Mécanique");
		iControlMP = new JMenuItem("Controler une MP"); // CONTOLER
		iControlPF = new JMenuItem("Controler un PF");
		iReset = new JMenuItem("Remise a Zero des Lots PF/MP/EM"); // OUTILS
		iTransfert = new JMenuItem("Transfert Donnees MP/PF/EM");
		iTATrame = new JMenuItem("Transfert Archive Trame MP/PF");
		iAlerte = new JMenuItem("Configuer une Alerte");
		iHistVal = new JMenuItem("Suivi Changement Valeur");

		iApropos = new JMenuItem("A Propos de"); // AIDE

		// sous menu
		iiListe1 = new JMenuItem("Liste Fiche Capilaire");
		iiListe7 = new JMenuItem("Liste Fiche Classique");
		iiListe2 = new JMenuItem("Liste Mortier pour Essai Complemantaire");
		iiListe3 = new JMenuItem("Methode & Critere pour Essai Mecanique");
		iiListe4 = new JMenuItem("Liste Precision Produit");
		iiListe5 = new JMenuItem("Liste Fiche Sans Lot");
		iiListe6 = new JMenuItem("Liste Fiche Avec Lot");
		iiListe8 = new JMenuItem("Liste Critere PF");

		// ajout des titre à la barre
		Barre.add(mFichier);
		Barre.add(mEdition);
		Barre.add(mRechercher);
		Barre.add(mCreer);
		Barre.add(mControler);
		Barre.add(mOutils);
		Barre.add(mAide);

		// ajout item au titre
		mFichier.add(iQuitter);

		mEdition.add(iModif_TMP);
		mEdition.add(iModif_TPF);
		mEdition.addSeparator();
		mEdition.add(mListe);

		mRechercher.add(iRech_CMP);
		mRechercher.add(iRech_CPF);
		mRechercher.addSeparator();
		mRechercher.add(iBTPFMP);
		mRechercher.add(iBATPFMP);
		mRechercher.addSeparator();
		mRechercher.add(iRech_EM);

		mCreer.add(iCreate_TMP);
		mCreer.add(iCreate_TPF);

		mControler.add(iControlMP);
		mControler.add(iControlPF);

		mOutils.add(iTransfert);
		mOutils.add(iReset);
		mOutils.add(iTATrame);
		mOutils.add(iAlerte);
        mOutils.add(iHistVal);
        
		mAide.add(iApropos);
		// sous menu
		mListe.add(iiListe1);
		mListe.add(iiListe7);
		mListe.add(iiListe5);
		mListe.add(iiListe6);
		mListe.add(iiListe2);
		mListe.add(iiListe3);
		mListe.add(iiListe4);
		mListe.add(iiListe8);

		// ajout de la barre a la frame
		fen.setJMenuBar(Barre);

		// action du JMenu
		SetAction();
	}

	private void SetAction() {
		iQuitter.addActionListener(new MenuActionListener());

		iModif_TMP.addActionListener(new MenuActionListener());
		iModif_TPF.addActionListener(new MenuActionListener());
		iiListe1.addActionListener(new MenuActionListener());
		iiListe2.addActionListener(new MenuActionListener());
		iiListe3.addActionListener(new MenuActionListener());
		iiListe4.addActionListener(new MenuActionListener());
		iiListe5.addActionListener(new MenuActionListener());
		iiListe6.addActionListener(new MenuActionListener());
		iiListe7.addActionListener(new MenuActionListener());
		iiListe8.addActionListener(new MenuActionListener());

		iRech_CMP.addActionListener(new MenuActionListener());
		iRech_CPF.addActionListener(new MenuActionListener());
		iBTPFMP.addActionListener(new MenuActionListener());
		iBATPFMP.addActionListener(new MenuActionListener());
		iRech_EM.addActionListener(new MenuActionListener());
		iCreate_TMP.addActionListener(new MenuActionListener());
		iCreate_TPF.addActionListener(new MenuActionListener());
		iControlMP.addActionListener(new MenuActionListener());
		iControlPF.addActionListener(new MenuActionListener());
		iTransfert.addActionListener(new MenuActionListener());
		iReset.addActionListener(new MenuActionListener());
		;
		iTATrame.addActionListener(new MenuActionListener());
		iAlerte.addActionListener(new MenuActionListener());
		iHistVal.addActionListener(new MenuActionListener());
		iApropos.addActionListener(new MenuActionListener());
	}

	// methode pour centrer les tableaux

	protected static void CentrerTableau(int First, JTable table) {
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
		custom.setHorizontalAlignment(JLabel.CENTER);
		for (int a = First; a < table.getColumnCount(); a++) // centre chaque cellule de ton tableau
			table.getColumnModel().getColumn(a).setCellRenderer(custom);
	}

	// creation des composants de la fenetre
	private void centrale() {

		// iamge du logo
		ImageIcon logo_icon = new ImageIcon(getClass().getResource(definition.URL_Logo));
		logoImg = new JLabel();
		logoImg.setIcon(logo_icon);
		mainN.add(logoImg, FlowLayout.LEFT);
		mainN.setPreferredSize(new Dimension(0, 80));

		// Appel de la methode pour afficher les different cards (via menu rapide)
		CARDLAYOUT();

		// Action sur les JButtonRadio du menu affichage rapide
		radio1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// 1** APPEL DU CARD
				cardLayout.show(mainC, CARD1);
				// 2** MAJ DU CARD
				// a- Tab MP
				try {
					// initialise tableau
					model_MP.setRowCount(0);

					connect = connexion.getConnection(); // initialisation connection

					String sql = "SELECT ID,NOM, RES,DATE_C, RESP, COMPLETE FROM TABLE_MP_INT "; // selecton type de donne de la
																						// base
					PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);

					ResultSet rs = pState.executeQuery(); // resultat de la selection
					// ajout element 1 a 1
					while (rs.next()) {
						// code a remplir row[] = rs.getString(col);
						MP_row1[0] = rs.getString("ID");
						MP_row1[1] = rs.getString("NOM");
						MP_row1[2] = rs.getString("RES"); // lot reserve
						MP_row1[3] = rs.getString("DATE_C");
						MP_row1[4] = rs.getString("RESP");
						MP_row1[5] = rs.getString("COMPLETE");
						model_MP.addRow(MP_row1);
					}

					// ferme les connexions
					rs.close();
					pState.close();
					// centerTable_Card1();
					CentrerTableau(0, MP_encours);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}

				// b- Tab PF
				try {
					// initialise tableau
					model_PF.setRowCount(0);

					connect = connexion.getConnection(); // initialisation connection

					String sql = "SELECT ID,NOM, RES,DATE_C, RESP, COMPLETE FROM TABLE_PF_INT "; // selecton type de donne de la
																						// base
					PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);

					ResultSet rs = pState.executeQuery(); // resultat de la selection
					// ajout element 1 a 1
					while (rs.next()) {
						// code a remplir row[] = rs.getString(col);
						PF_row1[0] = rs.getString("ID");
						PF_row1[1] = rs.getString("NOM");
						PF_row1[2] = rs.getString("RES");
						PF_row1[3] = rs.getString("DATE_C");
						PF_row1[4] = rs.getString("RESP");
						PF_row1[5] = rs.getString("COMPLETE");
						model_PF.addRow(PF_row1);
					}

					// ferme les connexions
					rs.close();
					pState.close();
					CentrerTableau(0, PF_encours);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});

		radio2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// 1 ** appel du CARDS
				cardLayout.show(mainC, CARD2);

				// 2 ** MAJ LE CARD
				// 2a ** TABLEAU MP
				try {
					// initialise tableau
					model_MPS.setRowCount(0);
					connect = connexion.getConnection(); // initialisation connection

					String sql = "SELECT NOM, SUM(CASE WHEN CONFORMITE <>'NC' THEN QTITE ELSE 0 END) AS CUMUL_QTITE, MAX(N_LOT) AS LAST_LOT, MAX(DATE_C) AS LAST_DATE, SUM(Q_NC) AS NB_NC, SUM(DEROG) AS NB_DEROG FROM TABLE_MP GROUP BY NOM"; // selecton
																																																	// type
																																																	// de
																																																	// donne
																																																	// de
																																																	// la
																																																	// base
					PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);

					ResultSet rs = pState.executeQuery(); // resultat de la selection
					// ajout element 1 a 1
					while (rs.next()) {
						// code a remplir row[] = rs.getString(col);
						MPS_row[0] = rs.getString("NOM");
						MPS_row[1] = rs.getString("CUMUL_QTITE");
						MPS_row[2] = rs.getString("LAST_LOT");
						MPS_row[3] = rs.getString("LAST_DATE");
						MPS_row[4] = rs.getString("NB_NC");
						MPS_row[5] = rs.getString("NB_DEROG");
						model_MPS.addRow(MPS_row);
					}

					// ferme les connexions
					rs.close();
					pState.close();
					CentrerTableau(1, MP_syn);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				// 2b ** TABLEAU PF
				try {
					// initialise tableau
					model_PFS.setRowCount(0);

					connect = connexion.getConnection(); // initialisation connection

				//	String sql = "SELECT NOM, SUM(QTITE) AS CUMUL_QTITE, MAX(N_LOT) AS LAST_LOT, MAX(DATE_C) AS LAST_DATE, SUM(Q_NC) AS NB_NC, SUM(DEROG) AS NB_DEROG FROM TABLE_PF GROUP BY NOM"; // selecton
					String sql = "SELECT NOM, SUM(CASE WHEN CONFORMITE <>'NC' THEN QTITE ELSE 0 END) AS CUMUL_QTITE, MAX(N_LOT) AS LAST_LOT, MAX(DATE_C) AS LAST_DATE, SUM(Q_NC) AS NB_NC, SUM(DEROG) AS NB_DEROG FROM TABLE_PF GROUP BY NOM";																																												// type
																																																	// de																																											// base
					PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);

					ResultSet rs = pState.executeQuery(); // resultat de la selection
					// ajout element 1 a 1
					while (rs.next()) {
						// code a remplir row[] = rs.getString(col);
						PFS_row[0] = rs.getString("NOM");
						PFS_row[1] = rs.getString("CUMUL_QTITE");
						PFS_row[2] = rs.getString("LAST_LOT");
						PFS_row[3] = rs.getString("LAST_DATE");
						PFS_row[4] = rs.getString("NB_NC");
						PFS_row[5] = rs.getString("NB_DEROG");
						model_PFS.addRow(PFS_row);
					}

					// ferme les connexions
					rs.close();
					pState.close();
					CentrerTableau(1, PF_syn);

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});

		radio3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				cardLayout.show(mainC, CARD3);
				// Reset ligne des tableau
				model_ECE.setRowCount(0);
				model_SCE.setRowCount(0);

				// Afficher mortier CE/NF dans tableau (1)
				try {
					connect = connexion.getConnection(); // initialisation connection
					String sql31 = "SELECT F.NOM, F.N_LOT,F.QTITE,F.DATE_C, (CASE F.ID WHEN EM.ID_PF THEN 'OUI' END) AS 'ESSAI SUP' "
							+ "FROM table_pf F " + "LEFT JOIN TABLE_EM EM ON F.ID = EM.ID_PF "
							+ "INNER JOIN mortar_liste ML ON F.NOM = ML.NOM AND ML.TYPE = 'C' "
							+ "ORDER BY F.NOM,F.N_LOT ";
					PreparedStatement pState31 = (PreparedStatement) connect.prepareStatement(sql31);
					ResultSet rs31 = pState31.executeQuery(); // resultat de la selection

					while (rs31.next()) {

						row_ECE[0] = rs31.getString("F.NOM");
						row_ECE[1] = rs31.getString("F.N_LOT");
						row_ECE[2] = rs31.getString("F.DATE_C");
						row_ECE[3] = rs31.getString("F.QTITE");
						row_ECE[4] = rs31.getString("ESSAI SUP");
						model_ECE.addRow(row_ECE);
					}
					rs31.close();
					pState31.close();
					CentrerTableau(1, tab_ECE);

				} catch (Exception e4) {
					JOptionPane.showMessageDialog(null, e4);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}

				// insertion tableau (2)
				try {
					connect = connexion.getConnection(); // initialisation connection

					String sql32 = "SELECT DISTINCT EM.PF,EM.N_LOT,EM.D_FAB,EM.D_FAB,EM.D_CASSE, EM.D_FAB + INTERVAL 13 DAY  "
							+ "FROM table_em EM, MORTAR_LISTE ML "
							+ "WHERE ML.NOM = EM.PF AND ML.TYPE = 'C' AND EM.D_FAB + INTERVAL 13 DAY > NOW() AND EM.STATUT = 'COOL'";

					PreparedStatement pState32 = (PreparedStatement) connect.prepareStatement(sql32);
					ResultSet rs32 = pState32.executeQuery(); // resultat de la selection

					while (rs32.next()) {
						row_SCE[0] = rs32.getString("PF"); // nom
						row_SCE[1] = rs32.getString("N_LOT"); // dernier lot control
						row_SCE[2] = rs32.getString("D_FAB"); // date dernier control ess sup
						row_SCE[3] = rs32.getString("EM.D_FAB + INTERVAL 13 DAY"); // date fin 14j a calcule
						model_SCE.addRow(row_SCE);
					}
					rs32.close();
					pState32.close();
					CentrerTableau(1, tab_SCE);

				} catch (Exception e4) {
					JOptionPane.showMessageDialog(null, e4);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}

		});

		radio4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				cardLayout.show(mainC, CARD4);
				model_EMJ.setRowCount(0);

				// MAJ la colonne STATUT pour savoir si casse a faire sauf colonne déja en
				// STATUT = TERMINE

				try {
					connect = connexion.getConnection(); // initialisation connection

					String sql40 = "UPDATE TABLE_EM set STATUT =  (CASE  "
							+ "WHEN D_CASSE = CAST(NOW() AS DATE)  AND STATUT != 'TERMINE' THEN 'A CASSER'"
							+ "WHEN D_CASSE = CAST(NOW() + INTERVAL 1 DAY AS DATE) THEN 'A PREPARER'"
							+ "WHEN D_CASSE < CAST(NOW() AS DATE) AND STATUT != 'TERMINE' THEN 'EN RETARD'"
							+ "WHEN D_CASSE > CAST(NOW() AS DATE) THEN 'COOL'"

							+ "END) WHERE STATUT != 'TERMINE'"; // selecton type de donne de la base

					PreparedStatement pState40 = (PreparedStatement) connect.prepareStatement(sql40);
					pState40.executeUpdate();
					pState40.close();
				} catch (Exception e4) {
					JOptionPane.showMessageDialog(null, e4);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}

				// Afficher EM dans tableau
				try {
					connect = connexion.getConnection(); // initialisation connection
					String sql41 = "SELECT PF,STATUT,D_FAB,D_CASSE FROM table_em WHERE STATUT <> 'TERMINE' ORDER BY D_FAB ASC"; // selecton
																																// type
																																// de
																																// donne
																																// de
																																// la
																																// base
					PreparedStatement pState41 = (PreparedStatement) connect.prepareStatement(sql41);
					ResultSet rs41 = pState41.executeQuery(); // resultat de la selection

					while (rs41.next()) {

						EMJ_row[0] = rs41.getString("PF");
						EMJ_row[1] = rs41.getString("STATUT");
						EMJ_row[2] = rs41.getString("D_FAB");
						EMJ_row[3] = rs41.getString("D_CASSE");
						model_EMJ.addRow(EMJ_row);
					}
					rs41.close();
					pState41.close();
					CentrerTableau(1, EM_jour);
				} catch (Exception e4) {
					JOptionPane.showMessageDialog(null, e4);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});

		radio5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				cardLayout.show(mainC, CARD5);

				// initialisation tableau
				model_rec.setRowCount(0);
				model_EMM.setRowCount(0);
				model_sac.setRowCount(0);

				// requete pour tableau 1 => Controle / Jour
				try {
					connect = connexion.getConnection(); // initialisation connection
					String sql501 = "SELECT NOM, COUNT(NOM) AS TOT_NOM,RESP FROM table_pf WHERE DATE(DATE_C) = DATE(NOW()) GROUP BY NOM "; // selecton
																																			// type
																																			// de
																																			// donne
																																			// de
																																			// la
																																			// base
					PreparedStatement pState501 = (PreparedStatement) connect.prepareStatement(sql501);

					ResultSet rs501 = pState501.executeQuery(); // resultat de la selection
					while (rs501.next()) {
						recap_day[0] = rs501.getString("NOM");
						recap_day[1] = rs501.getString("TOT_NOM");
						recap_day[2] = rs501.getString("RESP");
						model_rec.addRow(recap_day);
					}
					rs501.close();
					pState501.close();
					CentrerTableau(1, recap_jour);
				} catch (Exception e501) {
					JOptionPane.showMessageDialog(null, e501);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				// requete pour tableau 2 => Controle / mois
				try {
					connect = connexion.getConnection(); // initialisation connection
					String sql502 = "SELECT NOM, COUNT(NOM) AS TOT_NOM,RESP FROM table_pf WHERE MONTH(DATE_C) = MONTH(NOW()) GROUP BY NOM "; // selecton
																																				// type
																																				// de
																																				// donne
																																				// de
																																				// la
																																				// base
					PreparedStatement pState502 = (PreparedStatement) connect.prepareStatement(sql502);

					ResultSet rs502 = pState502.executeQuery(); // resultat de la selection
					while (rs502.next()) {
						recap_month[0] = rs502.getString("NOM");
						recap_month[1] = rs502.getString("TOT_NOM");
						recap_month[2] = rs502.getString("RESP");
						model_EMM.addRow(recap_month);
					}
					rs502.close();
					pState502.close();
					CentrerTableau(1, recap_mois);
				} catch (Exception e502) {
					JOptionPane.showMessageDialog(null, e502);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				// requete pour tableau 3 => n° de sac / fab
				try {
					connect = connexion.getConnection(); // initialisation connection
					String sql503 = "SELECT NOM, DATE_C,OBSERVATION FROM table_pf WHERE OBSERVATION LIKE '%sac%' ORDER BY NOM "; // selecton
																																	// type
																																	// de
																																	// donne
																																	// de
																																	// la
																																	// base
					PreparedStatement pState503 = (PreparedStatement) connect.prepareStatement(sql503);

					ResultSet rs503 = pState503.executeQuery(); // resultat de la selection
					while (rs503.next()) {
						recap_bag[0] = rs503.getString("NOM");
						recap_bag[1] = rs503.getString("DATE_C");
						recap_bag[2] = rs503.getString("OBSERVATION");
						model_sac.addRow(recap_bag);
						CentrerTableau(1, recap_sac);
					}
					rs503.close();
					pState503.close();
				} catch (Exception e503) {
					JOptionPane.showMessageDialog(null, e503);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				// Reqetes pour recuprere donnes dans la base Nombre total
				try { // MP
					connect = connexion.getConnection(); // initialisation connection
					String sql51 = "SELECT MAX(ID) AS ID_MAX FROM table_mp"; // selecton type de donne de la base
					PreparedStatement pState5 = (PreparedStatement) connect.prepareStatement(sql51);

					ResultSet rs51 = pState5.executeQuery(); // resultat de la selection
					while (rs51.next()) {
						NbtotalMP_txt.setText(rs51.getString("ID_MAX"));
					}
					rs51.close();
					pState5.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				try { // PF
					connect = connexion.getConnection(); // initialisation connection
					String sql52 = "SELECT MAX(ID) AS ID_MAX FROM table_pf"; // selecton type de donne de la base
					PreparedStatement pState52 = (PreparedStatement) connect.prepareStatement(sql52);

					ResultSet rs52 = pState52.executeQuery(); // resultat de la selection
					while (rs52.next()) {
						NbtotalPF_txt.setText(rs52.getString("ID_MAX"));
					}
					rs52.close();
					pState52.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				// nombre totla essai sup
				try {
					connect = connexion.getConnection(); // initialisation connection
					String sql53 = "SELECT COUNT(PF) AS NB_EM FROM table_em "; // selecton type de donne de la base
					PreparedStatement pState53 = (PreparedStatement) connect.prepareStatement(sql53);

					ResultSet rs54 = pState53.executeQuery(); // resultat de la selection
					while (rs54.next()) {
						NbtotalEp_txt.setText(rs54.getString("NB_EM"));
					}
					rs54.close();
					pState53.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				// nombre total de poudre
				try {
					connect = connexion.getConnection(); // initialisation connection
					String sql54 = "SELECT SUM(QTITE) AS CUMUL_POU FROM table_pf WHERE CAT ='POU'"; // selecton type de
																									// donne de la base
					PreparedStatement pState54 = (PreparedStatement) connect.prepareStatement(sql54);

					ResultSet rs54 = pState54.executeQuery(); // resultat de la selection
					while (rs54.next()) {
						QtitetotalPoudre_txt.setText(rs54.getString("CUMUL_POU"));
					}
					rs54.close();
					pState54.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				// somme total de pateux

				try {
					connect = connexion.getConnection(); // initialisation connection
					String sql55 = "SELECT SUM(QTITE) AS CUMUL_PAT FROM table_pf WHERE CAT ='PAT'"; // selecton type de
																									// donne de la base
					PreparedStatement pState55 = (PreparedStatement) connect.prepareStatement(sql55);

					ResultSet rs55 = pState55.executeQuery(); // resultat de la selection
					while (rs55.next()) {
						QtitetotalPate_txt.setText(rs55.getString("CUMUL_PAT"));
					}
					rs55.close();
					pState55.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				// nombre total de liquide

				try {
					connect = connexion.getConnection(); // initialisation connection
					String sql56 = "SELECT SUM(QTITE) AS CUMUL_LIQ FROM table_pf WHERE CAT ='LIQ'"; // selecton type de
																									// donne de la base
					PreparedStatement pState56 = (PreparedStatement) connect.prepareStatement(sql56);

					ResultSet rs56 = pState56.executeQuery(); // resultat de la selection
					while (rs56.next()) {
						QtitetotalLiquide_txt.setText(rs56.getString("CUMUL_LIQ"));
					}

					rs56.close();
					pState56.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				// top 5 des produits poudre => donc poudre (colonne CAT)
				try {
					String tab[] = { "", "", "", "", "" }; // initailisation tab pour recuperer valeur de la requete
					connect = connexion.getConnection(); // initialisation connection
					String sql57 = "SELECT DISTINCT NOM, SUM(QTITE) AS CUM_P FROM table_pf WHERE CAT ='POU' GROUP BY NOM ORDER BY CUM_P  DESC LIMIT 5 "; // selecton
																																							// type
																																							// de
																																							// donne
																																							// de
																																							// la
																																							// base
					PreparedStatement pState57 = (PreparedStatement) connect.prepareStatement(sql57);
					ResultSet rs57 = pState57.executeQuery(); // resultat de la selection

					int i = 0;
					while (rs57.next()) {
						tab[i] = rs57.getString("NOM");
						i++;
					}
					rs57.close();
					pState57.close();
					// affectation valeur requette au champs jtxtfield
					Pos1_txt.setText(tab[0]);
					Pos2_txt.setText(tab[1]);
					Pos3_txt.setText(tab[2]);
					Pos4_txt.setText(tab[3]);
					Pos5_txt.setText(tab[4]);

				} catch (Exception e7) {
					JOptionPane.showMessageDialog(null, e7);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				// Nombre de trame MP utilise
				try {
					connect = connexion.getConnection(); // initialisation connection
					String sql58 = "SELECT COUNT(DISTINCT NOM) AS NB_NOM FROM table_tmp "; // selecton type de donne de
																							// la base
					PreparedStatement pState58 = (PreparedStatement) connect.prepareStatement(sql58);

					ResultSet rs58 = pState58.executeQuery(); // resultat de la selection
					while (rs58.next()) {
						TNbFicheMPActive.setText(rs58.getString("NB_NOM"));
					}
					rs58.close();
					pState58.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				// Nombre de trame PF utilise

				try {
					connect = connexion.getConnection(); // initialisation connection
					String sql59 = "SELECT COUNT(DISTINCT NOM) AS NB_NOM FROM table_tpf "; // selecton type de donne de
																							// la base
					PreparedStatement pState59 = (PreparedStatement) connect.prepareStatement(sql59);

					ResultSet rs59 = pState59.executeQuery(); // resultat de la selection
					while (rs59.next()) {
						TNbFichePFActive.setText(rs59.getString("NB_NOM"));
					}
					rs59.close();
					pState59.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				// Nombre de trame MP archive
				try {
					connect = connexion.getConnection(); // initialisation connection
					String sql60 = "SELECT DISTINCT COUNT(DISTINCT NOM) AS NB_NOM FROM table_atmp "; // selecton type de
																										// donne de la
																										// base
					PreparedStatement pState60 = (PreparedStatement) connect.prepareStatement(sql60);

					ResultSet rs60 = pState60.executeQuery(); // resultat de la selection
					while (rs60.next()) {
						TNbFicheMPArch.setText(rs60.getString("NB_NOM"));
					}
					rs60.close();
					pState60.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				// Nombre de trame PF archive
				try {
					connect = connexion.getConnection(); // initialisation connection
					String sql61 = "SELECT DISTINCT COUNT(DISTINCT NOM) AS NB_NOM FROM table_atpf "; // selecton type de
																										// donne de la
																										// base
					PreparedStatement pState61 = (PreparedStatement) connect.prepareStatement(sql61);

					ResultSet rs61 = pState61.executeQuery(); // resultat de la selection
					while (rs61.next()) {
						TNbFichePFArch.setText(rs61.getString("NB_NOM"));
					}
					rs61.close();
					pState61.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				finally {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			} // fin du actionpeformed
		});

	}

	private void CARDLAYOUT() {

		// ************ card1
		JPanel cl1 = new JPanel();

		BoxLayout b = new BoxLayout(cl1, BoxLayout.Y_AXIS);
		cl1.setLayout(b);
		cl1.setOpaque(false);
		cl1.setBorder(new EmptyBorder(new Insets(0, 50, 0, 10)));

		cl1.add(Box.createRigidArea(new Dimension(0, 25)));
		// talbe
		MP_encours = new JTable();
		Object[] colonne_entre = { "N0_ID", "MATIERE PREMIERE", "N0_LOT RESERVE", "DATE CONTROLE", "RESPONSABLE",
				"COMPLETE A" };
		model_MP = new DefaultTableModel();
		model_MP.setColumnIdentifiers(colonne_entre);
		MP_encours.setModel(model_MP);
		MP_encours.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 11));
		MP_encours.setShowGrid(false);

		JScrollPane scpMP = new JScrollPane(MP_encours);
		scpMP.setPreferredSize(new Dimension(0, 125));
		scpMP.getViewport().setOpaque(false);
		cl1.add(scpMP);
		MP_row1 = new Object[6];

		cl1.add(Box.createRigidArea(new Dimension(0, 30)));

		// talbe 2
		PF_encours = new JTable();
		Object[] colonne_PF = { "N0_ID", "PRODUIT FINI", "N0_LOT RESERVE", "DATE FABRICATION", "RESPONSABLE",
				"COMPLETE A" };
		model_PF = new DefaultTableModel();
		model_PF.setColumnIdentifiers(colonne_PF);
		PF_encours.setModel(model_PF);
		PF_encours.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 11));
		PF_encours.setShowGrid(false);
		JScrollPane scp2 = new JScrollPane(PF_encours);
		// scp2.setOpaque(false);
		scp2.setPreferredSize(new Dimension(0, 125));
		scp2.getViewport().setOpaque(false);
		cl1.add(scp2);
		PF_row1 = new Object[6];

		cl1.add(Box.createRigidArea(new Dimension(200, 30)));
		but_view = new JButton();
		// image sur bouton
		ImageIcon butview_icon = new ImageIcon(getClass().getResource(definition.URL_RECUPDATA));
		butview_label = new JLabel("RECUPERER FICHE", butview_icon, JLabel.CENTER);
		but_view.add(butview_label);

		cl1.add(but_view);
		cl1.add(Box.createRigidArea(new Dimension(0, 20)));

		but_view.addActionListener(new getFiche());

		// *************** card2
		JPanel cl2 = new JPanel();
		cl2.setOpaque(false);
		MP_syn = new JTable();
		Object[] col_MP = { "MAT_PREM", "QTITE CUMUL", "DERNIER LOT", "DERNIER CONTROLE", "NC", "NC avec Derog" };
		model_MPS = new DefaultTableModel();
		model_MPS.setColumnIdentifiers(col_MP);

		MP_syn.setModel(model_MPS);
		MP_syn.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 10));
		MP_syn.setShowGrid(false);
		JScrollPane scp3 = new JScrollPane(MP_syn);
		// scp3.setOpaque(false);
		scp3.getViewport().setOpaque(false);
		scp3.setPreferredSize(new Dimension(550, 600));
		MP_syn.getColumnModel().getColumn(2).setPreferredWidth(25);
		MP_syn.getColumnModel().getColumn(1).setPreferredWidth(25);
		MP_syn.getColumnModel().getColumn(4).setPreferredWidth(15);
		cl2.add(scp3);
		MPS_row = new Object[6];

		PF_syn = new JTable();
		Object[] col_PF = { "PROD_FINI", "QTITE CUMUL", "DERNIER LOT", "DERNIERE DATE FAB", "NC", "NC avec Derog" };
		model_PFS = new DefaultTableModel();
		model_PFS.setColumnIdentifiers(col_PF);
		PF_syn.setModel(model_PFS);
		PF_syn.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 10));
		PF_syn.setShowGrid(false);
		JScrollPane scp4 = new JScrollPane(PF_syn);
		scp4.getViewport().setOpaque(false);
		scp4.setPreferredSize(new Dimension(definition.scp4_Large, definition.scp4_Haut));
		PF_syn.getColumnModel().getColumn(2).setPreferredWidth(25);
		PF_syn.getColumnModel().getColumn(1).setPreferredWidth(25);
		PF_syn.getColumnModel().getColumn(4).setPreferredWidth(15);
		cl2.add(scp4);

		PFS_row = new Object[6];

		// ************ card3
		JPanel cl3 = new JPanel(new FlowLayout());

		cl3.setOpaque(false);

		tab_ECE = new JTable();
		Object[] col_ECE = { "PRODUIT FINI", "N° DE LOT", "DATE DE FAB", "QUANTITE FAB", "AVEC ESSAI SUPP" };
		model_ECE = new DefaultTableModel();
		model_ECE.setColumnIdentifiers(col_ECE);
		tab_ECE.setModel(model_ECE);
		tab_ECE.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 10));
		tab_ECE.setShowGrid(false);
		JScrollPane scp_ECE = new JScrollPane(tab_ECE);
		scp_ECE.getViewport().setOpaque(false);
		scp_ECE.setPreferredSize(new Dimension(550, 600));
		tab_ECE.getColumnModel().getColumn(1).setPreferredWidth(25);
		tab_ECE.getColumnModel().getColumn(2).setPreferredWidth(25);
		tab_ECE.getColumnModel().getColumn(4).setPreferredWidth(25);
		cl3.add(scp_ECE);
		row_ECE = new Object[5];

		tab_SCE = new JTable();
		Object[] col_SCE = { "PROD_FINI", "DERNIER LOT AVEC CONTROL SUP", "DATE DERNIER CONTROLE SUP",
				"DATE FIN DES 14J" };
		model_SCE = new DefaultTableModel();
		model_SCE.setColumnIdentifiers(col_SCE);
		tab_SCE.setModel(model_SCE);
		tab_SCE.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 10));
		tab_SCE.setShowGrid(false);
		JScrollPane scp_SCE = new JScrollPane(tab_SCE);
		scp_SCE.getViewport().setOpaque(false);
		scp_SCE.setPreferredSize(new Dimension(definition.scp4_Large, definition.scp4_Haut));
		cl3.add(scp_SCE);
		row_SCE = new Object[4];

		// *********** card4

		JPanel cl4 = new Panel_CL4(); // layout principal sur la card4
		cl4.setLayout(new BorderLayout());
		// panels secondaire

		cl4_center = new JPanel(new GridBagLayout()); // resultat
		JPanel cl4_south = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 50)); // bouton
		JPanel cl4_west = new JPanel(); // tableau
		cl4_west.setLayout(new BoxLayout(cl4_west, BoxLayout.Y_AXIS));

		// ajout planels au principal
		cl4.add(cl4_center, BorderLayout.CENTER);
		cl4.add(cl4_south, BorderLayout.SOUTH);
		cl4.add(cl4_west, BorderLayout.WEST);

		// transparence des panels
		cl4.setOpaque(false);
		cl4_center.setOpaque(false);
		cl4_south.setOpaque(false);
		cl4_west.setOpaque(false);

		// Definition des 3 panels secondaire

		// partie west
		// TABLE
		EM_jour = new JTable();
		Object[] col_EMJ = { "PROD_FINI", "STATUT", "DATE FAB", "DATE CASSE" };
		model_EMJ = new DefaultTableModel();
		model_EMJ.setColumnIdentifiers(col_EMJ);
		// caracteristique table
		EM_jour.setModel(model_EMJ);
		EM_jour.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 11));
		EM_jour.setShowGrid(false);
		EM_jour.setShowVerticalLines(true);

		JScrollPane scp7 = new JScrollPane(EM_jour);
		// scp7.getViewport().setOpaque(false);
		scp7.setPreferredSize(new Dimension(definition.scp7_Large, definition.scp7_Haut));
		cl4_west.add(scp7);
		EMJ_row = new Object[4];

		cl4_west.add(Box.createRigidArea(new Dimension(0, 30)));

		HistoricDate = new JTextArea();
		HistoricDate.setEditable(false);
		JScrollPane jcp_HD = new JScrollPane(HistoricDate);
		cl4_west.add(jcp_HD);
		cl4_west.setBorder(BorderFactory.createEmptyBorder(0, 35, 10, 10));

		// PARTIE CENTRE => RESULTATS
		// Border du panel
		cl_4CBorder = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK),
				"Resultats Essais Mecaniques", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION);
		cl4_center.setBorder(cl_4CBorder);

		// definition du positionnement
		GridBagConstraints gbc = new GridBagConstraints();

		// def _ col 0
		Lnom = new JLabel("<html><font color = black><b>PRODUIT :</b></font></html>");
		Lmass = new JLabel("Masse (g) : ");
		Ldens = new JLabel("<html><I>densite </I> : </html>");
		Lrf = new JLabel("Res_flex (MPa) : ");
		Lrc = new JLabel("Res_Comp (MPa) : ");

		// position
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(2, 0, 75, 10);
		cl4_center.add(Lnom, gbc);
		gbc.gridy = 3;
		gbc.insets = new Insets(2, 0, 0, 0);
		cl4_center.add(Lmass, gbc);
		gbc.gridy = 4;
		cl4_center.add(Ldens, gbc);
		gbc.gridy = 5;
		cl4_center.add(Lrf, gbc);
		gbc.gridy = 6;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridheight = 2;
		cl4_center.add(Lrc, gbc);

		// def _col1
		Tnom = new JTextField(14);
		Tnom.setEditable(false);
		Lmes = new JLabel("<html><U>Mesures :</U></html> ");
		Lep1 = new JLabel("ep_1");
		tm1 = new JTextField(4);
		td1 = new JTextField(4);
		td1.setEditable(false);
		Trf1 = new JTextField(4);
		Trc11 = new JTextField(4);
		Trc12 = new JTextField(4);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(2, 5, 0, 0);
		cl4_center.add(Tnom, gbc);
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		cl4_center.add(Lmes, gbc);
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(2, 5, 0, 0);
		cl4_center.add(Lep1, gbc);
		gbc.gridy = 3;
		cl4_center.add(tm1, gbc);
		gbc.gridy = 4;
		cl4_center.add(td1, gbc);
		gbc.gridy = 5;
		cl4_center.add(Trf1, gbc);
		gbc.gridy = 6;
		cl4_center.add(Trc11, gbc);
		gbc.gridy = 7;
		cl4_center.add(Trc12, gbc);

		// def col2
		Lep2 = new JLabel("ep_2");
		tm2 = new JTextField(4);
		td2 = new JTextField(4);
		td2.setEditable(false);
		Trf2 = new JTextField(4);
		Trc21 = new JTextField(4);
		Trc22 = new JTextField(4);

		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.insets = new Insets(2, 5, 0, 0);
		cl4_center.add(Lep2, gbc);
		gbc.gridy = 3;
		cl4_center.add(tm2, gbc);
		gbc.gridy = 4;
		cl4_center.add(td2, gbc);
		gbc.gridy = 5;
		cl4_center.add(Trf2, gbc);
		gbc.gridy = 6;
		cl4_center.add(Trc21, gbc);
		gbc.gridy = 7;
		cl4_center.add(Trc22, gbc);

		// def col3
		Lep3 = new JLabel("ep_3");
		tm3 = new JTextField(4);
		td3 = new JTextField(4);
		td3.setEditable(false);
		Trf3 = new JTextField(4);
		Trc31 = new JTextField(4);
		Trc32 = new JTextField(4);

		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.insets = new Insets(2, 5, 0, 0);
		cl4_center.add(Lep3, gbc);
		gbc.gridy = 3;
		cl4_center.add(tm3, gbc);
		gbc.gridy = 4;
		cl4_center.add(td3, gbc);
		gbc.gridy = 5;
		cl4_center.add(Trf3, gbc);
		gbc.gridy = 6;
		cl4_center.add(Trc31, gbc);
		gbc.gridy = 7;
		cl4_center.add(Trc32, gbc);

		// def_col4
		Lc = new JLabel("<html><U>Valeur Calculé :</U></html>");
		Ldm = new JLabel("Densite moy = ");
		Lrfm = new JLabel("Res Flexion moy = ");
		Lrcm = new JLabel("Res Compression moy =  ");
		Letd = new JLabel("Ecart Type Densite = ");
		Lec1 = new JLabel("Ecart Type Res Flex = ");
		Lec2 = new JLabel("Ecart Type Res Comp = ");
		Lconf = new JLabel("<html><font color = black><U><b>CONFORMITE :</b></U></font></html>");
		Lcd = new JLabel("Densite : ");
		Lcc = new JLabel("Resistance : ");

		gbc.gridx = 4;
		gbc.gridy = 1;
		gbc.insets = new Insets(2, 75, 0, 0);
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		cl4_center.add(Lc, gbc);
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		cl4_center.add(Ldm, gbc);
		gbc.gridy = 4;
		cl4_center.add(Lrfm, gbc);
		gbc.gridy = 5;
		cl4_center.add(Lrcm, gbc);
		gbc.gridy = 6;
		cl4_center.add(Letd, gbc);
		gbc.gridy = 7;
		cl4_center.add(Lec1, gbc);
		gbc.gridy = 8;
		cl4_center.add(Lec2, gbc);
		gbc.gridy = 10;
		gbc.insets = new Insets(30, 0, 5, 0);
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		cl4_center.add(Lconf, gbc);
		gbc.gridy = 11;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(0, 0, 0, 0);
		cl4_center.add(Lcd, gbc);
		gbc.gridy = 12;
		cl4_center.add(Lcc, gbc);

		// def_col5
		Tdm = new JTextField(4);
		Tdm.setEditable(false);
		Trfm = new JTextField(4);
		Trfm.setEditable(false);
		Trcm = new JTextField(4);
		Trcm.setEditable(false);
		Tetd = new JTextField(4);
		Tetd.setEditable(false);
		Tec1 = new JTextField(4);
		Tec1.setEditable(false);
		Tec2 = new JTextField(4);
		Tec2.setEditable(false);
		Lcd2 = new JLabel();
		Lcc2 = new JLabel();

		gbc.gridx = 5;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(2, 5, 0, 0);
		cl4_center.add(Tdm, gbc);
		gbc.gridy = 4;
		cl4_center.add(Trfm, gbc);
		gbc.gridy = 5;
		cl4_center.add(Trcm, gbc);
		gbc.gridy = 6;
		cl4_center.add(Tetd, gbc);
		gbc.gridy = 7;
		cl4_center.add(Tec1, gbc);
		gbc.gridy = 8;
		cl4_center.add(Tec2, gbc);
		gbc.gridy = 11;
		cl4_center.add(Lcd2, gbc);
		gbc.gridy = 12;
		cl4_center.add(Lcc2, gbc);

		// def col6
		Lp = new JLabel("<html><U>Criteres Essai :</U></html>");
		Ldmin = new JLabel("Densite Min : ");
		Ldmax = new JLabel("Densite Max : ");
		Lrmin = new JLabel("Res Comp Min : ");

		gbc.gridx = 6;
		gbc.gridy = 1;
		gbc.insets = new Insets(2, 75, 0, 0);
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		cl4_center.add(Lp, gbc);
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		cl4_center.add(Ldmin, gbc);
		gbc.gridy = 4;
		cl4_center.add(Ldmax, gbc);
		gbc.gridy = 5;
		cl4_center.add(Lrmin, gbc);

		// def col_7
		Tdmin = new JTextField(4);
		Tdmin.setEditable(false);
		Tdmax = new JTextField(4);
		Tdmax.setEditable(false);
		Trmin = new JTextField(4);
		Trmin.setEditable(false);

		gbc.gridx = 7;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(2, 5, 0, 0);
		cl4_center.add(Tdmin, gbc);
		gbc.gridy = 4;
		cl4_center.add(Tdmax, gbc);
		gbc.gridy = 5;
		cl4_center.add(Trmin, gbc);

		// partie sud du BorderLayout => creation bouton
		JButton but_1 = new JButton();
		JButton but_4 = new JButton();
		JButton but_5 = new JButton();
		JButton but_2 = new JButton();
		JButton but_3 = new JButton();

		// icon bouton
		ImageIcon editer_icon = new ImageIcon(getClass().getResource(definition.URL_EDITER));
		editer_label = new JLabel("SAISIR VALEURS", editer_icon, SwingConstants.CENTER);
		editer_label.setVerticalTextPosition(JLabel.BOTTOM);
		editer_label.setHorizontalTextPosition(JLabel.CENTER);
		but_1.setPreferredSize(new Dimension(130, 70));
		but_1.add(editer_label);

		ImageIcon calculer_icon = new ImageIcon(getClass().getResource(definition.URL_CALCULER));
		calculer_label = new JLabel("CALCULER", calculer_icon, SwingConstants.CENTER);
		calculer_label.setVerticalTextPosition(JLabel.BOTTOM);
		calculer_label.setHorizontalTextPosition(JLabel.CENTER);
		but_4.setPreferredSize(new Dimension(100, 70));
		but_4.add(calculer_label);

		ImageIcon trans_icon = new ImageIcon(getClass().getResource(definition.URL_TRANSFERT));
		transf_label = new JLabel("TRANSFERER", trans_icon, SwingConstants.CENTER);
		transf_label.setVerticalTextPosition(JLabel.BOTTOM);
		transf_label.setHorizontalTextPosition(JLabel.CENTER);
		but_5.setPreferredSize(new Dimension(120, 70));
		but_5.add(transf_label);

		ImageIcon histo_icon = new ImageIcon(getClass().getResource(definition.URL_HISTORIQUE));
		hist_label = new JLabel("HISTORIQUE", histo_icon, SwingConstants.CENTER);
		hist_label.setVerticalTextPosition(JLabel.BOTTOM);
		hist_label.setHorizontalTextPosition(JLabel.CENTER);
		but_2.setPreferredSize(new Dimension(110, 70));
		but_2.add(hist_label);

		ImageIcon edit_icon = new ImageIcon(getClass().getResource(definition.URL_MODIFIER));
		editp_label = new JLabel("PARAMETRES", edit_icon, SwingConstants.CENTER);
		editp_label.setVerticalTextPosition(JLabel.BOTTOM);
		editp_label.setHorizontalTextPosition(JLabel.CENTER);
		but_3.setPreferredSize(new Dimension(115, 70));
		but_3.add(editp_label);

		// association bouton au panel 4
		cl4_south.add(but_1);
		cl4_south.add(but_4);
		cl4_south.add(but_5);
		cl4_south.add(but_2);
		cl4_south.add(but_3);

		// fonction bouton
		but_5.addActionListener(new TRANSFERT());
		but_4.addActionListener(new RES_VALIDER());
		but_3.addActionListener(new EDIT_PARAM());
		but_2.addActionListener(new HISTORIC());
		but_1.addActionListener(new SAISIE());

		// ******** card5
		JPanel cl5 = new JPanel();
		cl5.setLayout(new BoxLayout(cl5, BoxLayout.Y_AXIS));
		// JPanel cl5 = new PanelFondCard();

		cl5.setOpaque(false);

		// creation sous panel
		JPanel cl5_table = new JPanel(new FlowLayout(0, 40, 30));
		JPanel cl5_stats = new JPanel(new GridBagLayout());

		// affection panel au principal
		cl5.add(Box.createVerticalStrut(30));
		cl5.add(cl5_table);
		cl5.add(cl5_stats);

		// transparence des panel
		cl5.setOpaque(false);

		cl5_table.setOpaque(false);
		cl5_stats.setOpaque(false);

		// descrption du cl5_table
		// table recap journee
		recap_jour = new JTable();
		Object[] col_jour = { "PROD_FINI", "NB ESSAI", "RESPONSABLE" };
		model_rec = new DefaultTableModel();
		model_rec.setColumnIdentifiers(col_jour);
		recap_jour.setModel(model_rec);
		recap_jour.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 11));
		recap_jour.setShowGrid(false);
		JScrollPane jsp_jour = new JScrollPane(recap_jour);
		jsp_jour.getViewport().setOpaque(false);
		jsp_jour.setPreferredSize(new Dimension(definition.scp_jour_Large, definition.scp_jour_Haut));
		cl5_table.add(jsp_jour);
		recap_day = new Object[3];

		// table recap mois
		recap_mois = new JTable();
		Object[] col_mois = { "PROD_FINI", "NB ESSAI", "RESPONSABLE" };
		model_EMM = new DefaultTableModel();
		model_EMM.setColumnIdentifiers(col_mois);
		recap_mois.setModel(model_EMM);
		recap_mois.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 11));
		recap_mois.setShowGrid(false);
		JScrollPane jsp_mois = new JScrollPane(recap_mois);
		jsp_mois.getViewport().setOpaque(false);
		jsp_mois.setPreferredSize(new Dimension(definition.scp_jour_Large, definition.scp_jour_Haut));
		cl5_table.add(jsp_mois);
		recap_month = new Object[3];

		// table suivi sac
		recap_sac = new JTable();
		Object[] col_sac = { "PROD_FINI", "DATE FAB", "N° SAC PRELEVE" };
		model_sac = new DefaultTableModel();
		model_sac.setColumnIdentifiers(col_sac);
		recap_sac.setModel(model_sac);
		recap_sac.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 11));
		recap_sac.setShowGrid(false);
		JScrollPane jsp_sac = new JScrollPane(recap_sac);
		jsp_sac.getViewport().setOpaque(false);
		jsp_sac.setPreferredSize(new Dimension(definition.scp_jour_Large, definition.scp_jour_Haut));
		cl5_table.add(jsp_sac);
		recap_bag = new Object[3];

		// Description du CL5 stats
		// definition du positionnement
		GridBagConstraints c = new GridBagConstraints();

		// def label
		JLabel NbtotalMP = new JLabel("Nombre Total Controle MP : ");
		JLabel NbtotalPF = new JLabel("Nombre Total Controle PF : ");
		JLabel NbtotalEp = new JLabel("Nombre Total Essai Supp : ");
		JLabel QtitetotalPoudre = new JLabel("Quantite Total POUDRE (Tonnes): ");
		JLabel QtitetotalPate = new JLabel("Quantite Total PATEUX (Tonnes): ");
		JLabel QtitetotalLiquide = new JLabel("Quantite Total LIQUIDE (x 1000 L) : ");
		JLabel Top_FivePF = new JLabel("Top 5 des PF Fabriqués : ");
		Top_FivePF.setForeground(Color.BLACK);
		JLabel Pos1 = new JLabel("N° 1 : ");
		JLabel Pos2 = new JLabel("N° 2 : ");
		JLabel Pos3 = new JLabel("N° 3 : ");
		JLabel Pos4 = new JLabel("N° 4 : ");
		JLabel Pos5 = new JLabel("N° 5 : ");
		JLabel NbFicheMPActive = new JLabel("Nombre de Trame MP actives : ");
		JLabel NbFichePFActive = new JLabel("Nombre de Trame PF actives : ");
		JLabel NbFicheMPArch = new JLabel("Nombre de Trame MP archivees : ");
		JLabel NbFichePFArch = new JLabel("Nombre de Trame PF archivees : ");

		NbtotalMP_txt = new JTextField(5);
		NbtotalPF_txt = new JTextField(5);
		NbtotalEp_txt = new JTextField(5);
		QtitetotalPoudre_txt = new JTextField(5);
		QtitetotalPate_txt = new JTextField(5);
		QtitetotalLiquide_txt = new JTextField(5);
		Pos1_txt = new JTextField(10);
		Pos2_txt = new JTextField(10);
		Pos3_txt = new JTextField(10);
		Pos4_txt = new JTextField(10);
		Pos5_txt = new JTextField(10);
		TNbFicheMPActive = new JTextField(6);
		TNbFichePFActive = new JTextField(6);
		TNbFicheMPArch = new JTextField(6);
		TNbFichePFArch = new JTextField(6);

		// def positionnement
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.insets = new Insets(10, 0, 0, 10);
		cl5_stats.add(NbtotalMP, c);
		c.gridy = 1;
		cl5_stats.add(NbtotalPF, c);
		c.gridy = 2;
		cl5_stats.add(NbtotalEp, c);
		c.gridy = 3;
		cl5_stats.add(QtitetotalPoudre, c);
		c.gridy = 4;
		cl5_stats.add(QtitetotalPate, c);
		c.gridy = 5;
		cl5_stats.add(QtitetotalLiquide, c);

		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 0, 0, 10);
		cl5_stats.add(NbtotalMP_txt, c);
		c.gridy = 1;
		cl5_stats.add(NbtotalPF_txt, c);
		c.gridy = 2;
		cl5_stats.add(NbtotalEp_txt, c);
		c.gridy = 3;
		cl5_stats.add(QtitetotalPoudre_txt, c);
		c.gridy = 4;
		cl5_stats.add(QtitetotalPate_txt, c);
		c.gridy = 5;
		cl5_stats.add(QtitetotalLiquide_txt, c);

		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(10, 30, 0, 20);
		cl5_stats.add(Top_FivePF, c);

		c.insets = new Insets(10, 30, 0, 20);
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridy = 1;
		cl5_stats.add(Pos1, c);
		c.gridy = 2;
		cl5_stats.add(Pos2, c);
		c.gridy = 3;
		cl5_stats.add(Pos3, c);
		c.gridy = 4;
		cl5_stats.add(Pos4, c);
		c.gridy = 5;
		cl5_stats.add(Pos5, c);

		c.gridx = 3;
		c.gridy = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 0, 0, 10);
		cl5_stats.add(Pos1_txt, c);
		c.gridy = 2;
		cl5_stats.add(Pos2_txt, c);
		c.gridy = 3;
		cl5_stats.add(Pos3_txt, c);
		c.gridy = 4;
		cl5_stats.add(Pos4_txt, c);
		c.gridy = 5;
		cl5_stats.add(Pos5_txt, c);

		c.gridx = 4;
		c.gridy = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.insets = new Insets(10, 30, 0, 10);
		cl5_stats.add(NbFicheMPActive, c);
		c.gridy = 2;
		cl5_stats.add(NbFichePFActive, c);
		c.gridy = 3;
		cl5_stats.add(NbFicheMPArch, c);
		c.gridy = 4;
		cl5_stats.add(NbFichePFArch, c);

		c.gridx = 5;
		c.gridy = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 10, 0, 10);
		cl5_stats.add(TNbFicheMPActive, c);
		c.gridy = 2;
		cl5_stats.add(TNbFichePFActive, c);
		c.gridy = 3;
		cl5_stats.add(TNbFicheMPArch, c);
		c.gridy = 4;
		cl5_stats.add(TNbFichePFArch, c);

		// association du main au cards
		mainC.add(cl1, CARD1);
		mainC.add(cl2, CARD2);
		mainC.add(cl3, CARD3);
		mainC.add(cl4, CARD4);
		mainC.add(cl5, CARD5);

		// obtention corrdonne souris => CODE TEMPORAIRE

		main.addMouseListener(new myMouseListener());
		cl1.addMouseListener(new myMouseListener()); // position au clic que la souris
		cl2.addMouseListener(new myMouseListener()); // position au clic que la souris
		cl3.addMouseListener(new myMouseListener()); // position au clic que la souris
		cl5.addMouseListener(new myMouseListener()); // position au clic que la souris
	}

	// ************ CLASS DES ACTIONS MENU RAPIDE *******************

	public class AJOUTER_PF implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Ajouter_PF();
		}
	}

	public class AJOUTER_MP implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Ajouter_MP();
		}
	}

	public class AFFICHER_BMP implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				new Base_MP();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public class AFFICHER_BPF implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Base_PF();
		}
	}

	public class AFFICHER_EM implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Base_EM();
		}
	}

	public class REFRESH_ALL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// CADRE INFORMATION

			try {

				connect = connexion.getConnection(); // initialisation connection
				String sql = "SELECT DATE_DEBUT, MESSAGE FROM LISTE_ALERTE ORDER BY DESIGNATION "; // selecton type de
																									// donne de la base
				PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);

				ResultSet rs = pState.executeQuery(); // resultat de la selection
				// ajout element 1 a 1
				while (rs.next()) {
					zonetxtsud.append(("DEBUT ALERTE LE   " + rs.getString(1)) + " : ");
					zonetxtsud.append((rs.getString(2)) + "\n");
				}
				// ferme les connexions

				rs.close();
				pState.close();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
			finally {
				try {
					connect.close();
				} catch (SQLException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				
			}
		}
	}

	// *********************************************************
	// **************** CLASS ACTION BOUTON DES CARDS ***********
	// **********************************************************
	public class getFiche implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// selection d'une colonne danbs le tableau pour afficher une fiche
			if (MP_encours.isColumnSelected(0) || MP_encours.isColumnSelected(1) || MP_encours.isColumnSelected(2)
					|| MP_encours.isColumnSelected(3)) {
				recup_id = Integer.parseInt((String) MP_encours.getValueAt(MP_encours.getSelectedRow(), 0));
				new Ajouter_MP();
			
			} else if (PF_encours.isColumnSelected(0) || PF_encours.isColumnSelected(1)
					|| PF_encours.isColumnSelected(2) || PF_encours.isColumnSelected(3)) {
				recup_id = Integer.parseInt((String) PF_encours.getValueAt(PF_encours.getSelectedRow(), 0));
				new Ajouter_PF();
			}
		}
	}

	public class RES_VALIDER implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// declaration des variable
			Float res_Tdmin, res_Tdmax, res_Trcm, res_Tdm, res_Trcmoy;

			// calcul de la densite => utilisation Class Calcul

			td1.setText(Calcul.Densite(tm1.getText()));
			td2.setText(Calcul.Densite(tm2.getText()));
			td3.setText(Calcul.Densite(tm3.getText()));

			Tdm.setText(Calcul.DensiteMoyenne(td1.getText(), td2.getText(), td3.getText()));

			// calcul de la moy Rf et Rc
			Trfm.setText(Calcul.ResFlexMoy(Trf1.getText(), Trf2.getText(), Trf3.getText()));
			Trcm.setText(Calcul.ResComMoy(Trc11.getText(), Trc12.getText(), Trc21.getText(), Trc22.getText(),
					Trc31.getText(), Trc32.getText()));

			// calcul de l'ecart type

			Tetd.setText(Calcul.EcartType(td1.getText(), td2.getText(), td3.getText()));
			Tec1.setText(Calcul.EcartType(Trf1.getText(), Trf2.getText(), Trf3.getText()));
			Tec2.setText(Calcul.EcartType(Trc11.getText(), Trc12.getText(), Trc21.getText(), Trc22.getText(),
					Trc31.getText(), Trc32.getText()));

			// indication de la conformite
			res_Tdmin = Float.parseFloat(Tdmin.getText());
			res_Tdmax = Float.parseFloat(Tdmax.getText());
			res_Trcm = Float.parseFloat(Trmin.getText());

			res_Tdm = Float.parseFloat(Tdm.getText());
			res_Trcmoy = Float.parseFloat(Trcm.getText());

			if (res_Tdm <= res_Tdmax && res_Tdm >= res_Tdmin) {
				Lcd2.setText("CONFORME");
			} else
				Lcd2.setText("NC");

			if (res_Trcmoy >= res_Trcm) {
				Lcc2.setText("CONFORME");
			} else
				Lcc2.setText("NC");
		}
	} // fin class sec

	public class EDIT_PARAM implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Liste_PARAM();
		}
	}

	public class HISTORIC implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			HistoricDate.setText("Historique des derniers résultats des compresssions mecaniques :" + "\n");
			HistoricDate.setLineWrap(true);
			HistoricDate.setWrapStyleWord(true);

			try {
				connect = connexion.getConnection(); // initialisation connection
				String sqlh = "SELECT D_CASSE,Rf_moy,Rc_moy FROM table_em WHERE PF = ? AND D_CASSE < NOW()"; // selecton
																												// type
																												// de
																												// donne
																												// de la
																												// base
				PreparedStatement phis = (PreparedStatement) connect.prepareStatement(sqlh);
				phis.setString(1, recup_nom);
				ResultSet rsh = phis.executeQuery(); // resultat de la selection
				while (rsh.next()) {
					HistoricDate.append("Date Casse : " + rsh.getString("D_CASSE") + "\n");
					HistoricDate.append("Valeur Rf_moyen : " + rsh.getString("Rf_moy") + "\n");
					HistoricDate.append("Valeur Rc_moyen : " + rsh.getString("Rc_moy") + "\n");
					HistoricDate.append("\n");
				}

			} catch (Exception es) {
				JOptionPane.showMessageDialog(null, es);
			}
			finally {
				try {
					connect.close();
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
			}
		}
	}

	public class SAISIE implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// selection d'une colonne danbs le tableau pour afficher le nom du produit dans
			// jtextfield
			if (EM_jour.isColumnSelected(0) || EM_jour.isColumnSelected(1) || EM_jour.isColumnSelected(2)
					|| EM_jour.isColumnSelected(3)) {
				recup_nom = (String) EM_jour.getValueAt(EM_jour.getSelectedRow(), 0);
				recup_date = (String) EM_jour.getValueAt(EM_jour.getSelectedRow(), 2);
				Tnom.setText(recup_nom);
			}

			// afficher donnes du mortier selectionné dans les cases des parametre
			try {
				connect = connexion.getConnection(); // initialisation connection
				String sqls = "SELECT D_MIN,D_MAX,RC_MIN FROM LISTE_PARA WHERE NOM = ? "; // selecton type de donne de
																							// la base
				PreparedStatement pStates = (PreparedStatement) connect.prepareStatement(sqls);
				pStates.setString(1, recup_nom);

				ResultSet rss = pStates.executeQuery(); // resultat de la selection
				while (rss.next()) {
					Tdmin.setText(rss.getString("D_MIN"));
					Tdmax.setText(rss.getString("D_MAX"));
					Trmin.setText(rss.getString("RC_MIN"));
				}
				// ferme les connexions
				rss.close();
				pStates.close();
			} catch (Exception es) {
				JOptionPane.showMessageDialog(null, es);
			}
			finally {
				try {
					connect.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		}
	}

	public class TRANSFERT implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// update table_EM avec elements du panel CARD4 (mesure, valeur calcule)

			try {
				connect = connexion.getConnection(); // initialisation connection
				String upstr = "UPDATE table_em SET m_1 = ?, m_2=?, m_3=?, rf_1 = ?, rf_2=?, rf_3 = ?, rc_11 = ?, rc_12=?, rc_21=?,rc_22=?, rc_31=?,rc_32=?, "
						+ "STATUT = ?, CONF_D = ?, CONF_R =?, d_1=?,d_2=?,d_3=?,d_moy=?,Rf_moy=?,Rc_moy=?,ET_d=?,ET_f=?,ET_c=?,ANNE=YEAR(?),d_min=?,d_max=?,rc_min=?"
						+ "WHERE D_FAB = ? AND PF=?";
				PreparedStatement upState = (PreparedStatement) connect.prepareStatement(upstr);
				upState.setString(1, tm1.getText());
				upState.setString(2, tm2.getText());
				upState.setString(3, tm3.getText());
				upState.setString(4, Trf1.getText());
				upState.setString(5, Trf2.getText());
				upState.setString(6, Trf3.getText());
				upState.setString(7, Trc11.getText());
				upState.setString(8, Trc12.getText());
				upState.setString(9, Trc21.getText());
				upState.setString(10, Trc22.getText());
				upState.setString(11, Trc31.getText());
				upState.setString(12, Trc32.getText());
				upState.setString(13, "TERMINE");
				upState.setString(14, Lcd2.getText());
				upState.setString(15, Lcc2.getText());
				upState.setString(16, td1.getText());
				upState.setString(17, td2.getText());
				upState.setString(18, td3.getText());
				upState.setString(19, Tdm.getText());
				upState.setString(20, Trfm.getText());
				upState.setString(21, Trcm.getText());
				upState.setString(22, Tetd.getText());
				upState.setString(23, Tec1.getText());
				upState.setString(24, Tec2.getText());
				upState.setString(25, recup_date);
				upState.setString(26, Tdmin.getText());
				upState.setString(27, Tdmax.getText());
				upState.setString(28, Trmin.getText());
				upState.setString(29, recup_date);
				upState.setString(30, recup_nom);

				upState.executeUpdate();
				upState.close();

				JOptionPane.showMessageDialog(null, "Les resultats mecaniques ont été mis a jour dans la base");
			} catch (Exception es) {
				JOptionPane.showMessageDialog(null, es);
			}
			finally {
				try {
					connect.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			// effacement des donnes a saisir
			tm1.setText("");
			tm2.setText("");
			tm3.setText("");
			td1.setText("");
			td2.setText("");
			td3.setText("");
			Trf1.setText("");
			Trf2.setText("");
			Trf3.setText("");
			Trc11.setText("");
			Trc12.setText("");
			Trc21.setText("");
			Trc22.setText("");
			Trc31.setText("");
			Trc32.setText("");
			// effacement donne de calcul
			td1.setText("");
			td2.setText("");
			td3.setText("");
			Tdm.setText("");
			Trfm.setText("");
			Trcm.setText("");
			Tetd.setText("");
			Tec1.setText("");
			Tec2.setText("");
		}
	}
	
	// *************************************************
	// ****** CLASS DES ACTION DE LA BARRE DE MENU ***
	// *************************************************
	public class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == iQuitter) {
				System.exit(0);
			}

			if (e.getSource() == iModif_TMP) {
				new Modifer_TMP();
				
			}

			if (e.getSource() == iModif_TPF) {
				new Modifier_TPF();
			}

			if (e.getSource() == iiListe1) {
				new Liste_Cap();
			}

			if (e.getSource() == iiListe2) {
				new Liste_Mortar();
			}

			if (e.getSource() == iiListe3) {
				new Liste_PARAM();
			} // parametre et methode

			if (e.getSource() == iiListe4) {
				new Liste_Pres();
			}

			if (e.getSource() == iiListe5) {
				new Liste_ssLot();
			}

			if (e.getSource() == iiListe6) {
				new Liste_avLot();
			}

			if (e.getSource() == iiListe7) {
				new Liste_Classique();
			}

			if (e.getSource() == iiListe8) {
				new Liste_CriterePF();
			}

			if (e.getSource() == iCreate_TMP) {
				new Creer_TMP();
			}

			if (e.getSource() == iCreate_TPF) {
				new Creer_TPF();
			}

			if (e.getSource() == iRech_CMP) {
				try {
					new Base_MP();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (e.getSource() == iRech_CPF) {
				new Base_PF();
			}

			if (e.getSource() == iBTPFMP) {
				new Base_Trame();
			}

			if (e.getSource() == iBATPFMP) {
				new Base_ArchiveTrame();
			}

			if (e.getSource() == iRech_EM) {
				new Base_EM();
			}

			if (e.getSource() == iControlMP) {
				new Ajouter_MP();
			}

			if (e.getSource() == iControlPF) {
				new Ajouter_PF();
			}

			if (e.getSource() == iTransfert) {
				SaveData Tr = new SaveData();
				Tr.DataTransfert();
			}

			if (e.getSource() == iReset) {
				SaveData Rs = new SaveData();
				Rs.ResetBase();
			}

			if (e.getSource() == iTATrame) {
				new PopPupArchiveTrame();
			}

			if (e.getSource() == iAlerte) {
				new Alerte();
			}
			
			if (e.getSource() == iHistVal) {
				new SuiviVal();
			}
			

			if (e.getSource() == iApropos) {
				new apropos();
			}
		}
	}

}
