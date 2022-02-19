package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
//import java.text.ParseException;
//import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

//import com.gui.Ajouter_MP.AffRes2;

//import org.jfree.data.time.Day;

import connexion.BD.connexion;




public class Ajouter_PF extends Habillage{

	
	//declaration des variables
	
	    private static Connection connect; //creer varaible connect
		private JFrame fen_PF;
		
		private JPanel panel_PF, paneE, paneC, paneO, paneS, paneBox1, paneBox2, paneS1,paneS2;
		//private TitledBorder paneOBorder, paneBox1Border,paneBox2Border,paneS1Border, paneS2Border;		
		private JButton b_resultat, b2_resultat,b_encours, b_transfert, b_fiche;
        private JLabel Lchoix, Ltrame, Lfreq, LQt, Lmod, Lpre, Lpar, Lcum, Lt, Lnc,Lderog, Llot1, Lnc1, Lderog1, Lconf;
        private JLabel Lresp, Ldate1, Ldate2, Lq, Lobs, Lpresi, Lres,Lcat,LlotAnnee;
        private JTextField Tresp, Tdate1, Tdate2,TQ, Tobs,Tres;
        private JTextField Ttrame, Tfreq, TQt, Tmod, Tpar, Tt, Tnc, Tderog, Tlot1,Tnc1,Tderog1,Tconf;
        private JTextArea Tpre;
        private JComboBox<String> Cchoix, Cpres, CRes1;
        private JTable result_tab;
	    private DefaultTableModel model_res;
	    private Object[] result_tabRow;
	    private int Casse;
	    //def var
	    private int Qnc =0 ; //pour nombre de non conformite
	    private int NB_DEROG=0 ; // pour nombre de derog
	    private int D_LOT,D_LOT_DIX, DERNIER_LOT, LOT_ENCOURS;
	    private String getVersion; //recupere version de la trame mp
	    private int lotfictif;
	    private ArrayList<Integer> NO_ligne;
	    int countNC, countDATA,CountNbNC;
	 //   private Date Date_dernierEssai;
	    private String LastDateES;
	    private LocalDate date1, date2;
	    private String no_id, Methode, Q_eau, Q_res,Q_poudre;
	    
public Ajouter_PF() {
		
		 fen_PF();
		 ListeNomPF();
		 ChargeListe();
}
	

private void fen_PF() {
		fen_PF = new JFrame("CONTROLE : Nouveau Produit Fini");
		fen_PF.setSize(definition.SLarge,definition.SHaut);
		fen_PF.setLocationRelativeTo(null);
        fen_PF.setResizable(false);
      
        //definition des panels 
         //panel principal
        panel_PF = new ControlPanel();
        panel_PF.setLayout(new BorderLayout());
         
        //panel Centre definit avec 2 panels Verticale
        paneC = new JPanel();
        paneC.setLayout(new BoxLayout(paneC, BoxLayout.Y_AXIS));
        		
        paneE = new JPanel(new GridBagLayout());
        paneS = new JPanel();
        paneS.setLayout(new BoxLayout(paneS, BoxLayout.X_AXIS));
        paneO = new JPanel(new GridBagLayout());
        
        paneS1 =new JPanel();
       // paneS2= new JPanel(new GridLayout(4,1,0,20));
        paneS2 = new JPanel();
        paneS2.setLayout(new BoxLayout(paneS2,BoxLayout.Y_AXIS));
        
        paneS.add(paneS1);
        paneS.add(paneS2);
        
        //definition du paneau central avec 2 verticalements
        paneBox1 = new JPanel(new GridBagLayout());
        paneBox2 = new JPanel(new GridBagLayout());
        paneC.add(paneBox1);
        paneC.add(paneBox2);
        
        //ajout des paneau au celui princiapel
        panel_PF.add(paneC, BorderLayout.CENTER);
        panel_PF.add(paneE, BorderLayout.EAST);
        panel_PF.add(paneO,BorderLayout.WEST);
        panel_PF.add(paneS, BorderLayout.SOUTH);
        
        //initialisation des vues de chaque panel
        view_PRODUIT();
        view_STAT();
        view_CONFORMITE();
        view_ESSAI();
        view_TAB();
        view_VALID();
        affichage();
        
        //transparence de ts les panel
        paneC.setOpaque(false);
         paneE.setOpaque(false);
         paneS.setOpaque(false);
         paneS1.setOpaque(false);
         paneS2.setOpaque(false);
         paneO.setOpaque(false);
        paneBox1.setOpaque(false);
         paneBox2.setOpaque(false);
        
        
        fen_PF.getContentPane().add(panel_PF);
        fen_PF.setVisible(true);
}
	
private void view_PRODUIT() {
	
		paneO.setPreferredSize(new Dimension(definition.O_Large,definition.O_Haut));
		
		GridBagConstraints g1 = new GridBagConstraints();
		GridBagConstraints g2 = new GridBagConstraints();
		
		//def et position colonne 1
		Lchoix = new JLabel("CHOIX DU PF :");
		Ltrame = new JLabel("TYPE DE TRAME :");
		Lfreq  = new JLabel("FREQUENCE :");
		LQt = new JLabel("QTITE A PRELEVER : ");
		Lmod = new JLabel("MODOP ET NORMES : ");
		Lpre = new JLabel("PREPARATION : ");
 		Lpar = new JLabel("PARTICULARITE : ");
 		Lcat = new JLabel(); // pour stocker le type de categorie
 		
 		g1.gridx =0;
		g1.gridy =0;
		g1.anchor = GridBagConstraints.FIRST_LINE_END;
		g1.insets = new Insets(10,0,0,0);
		paneO.add(Lchoix, g1);
		g1.gridy=1;
		paneO.add(Ltrame,g1);
		g1.gridy=2;
		paneO.add(Lfreq,g1);
		g1.gridy=3;
		paneO.add(LQt,g1);
		g1.gridy=4;
		paneO.add(Lmod,g1);
		g1.gridy=5;
		paneO.add(Lpre,g1);
		g1.gridy=6;
		paneO.add(Lpar,g1);
		
		//def et pos colonne 2
		
		Cchoix = new JComboBox<>();
		Cchoix.setPreferredSize(new Dimension(200,20));
		Ttrame = new JTextField(7);
		Ttrame.setEditable(false);
		Tfreq = new JTextField(20);
		Tfreq.setEditable(false);
		TQt = new JTextField(7);
		TQt.setEditable(false);
		Tmod = new JTextField(7);
		Tmod.setEditable(false);
		Tpre = new JTextArea(5,20);
		JScrollPane Tpre_sp = new JScrollPane(Tpre);
		Tpre.setEditable(false);
		Tpre.setOpaque(false);
		Tpar = new JTextField(20);
		Tpar.setEditable(false);
		
		g2.gridx =1;
		g2.gridy =0;
		g2.anchor = GridBagConstraints.FIRST_LINE_START;
		g2.insets = new Insets(10,5,0,0);
		paneO.add(Cchoix, g2);
		g2.gridy=1;
		paneO.add(Ttrame,g2);
		g2.gridy=2;
		paneO.add(Tfreq,g2);
		g2.gridy=3;
		paneO.add(TQt,g2);
		g2.gridy=4;
		paneO.add(Tmod,g2);
		g2.gridy=5;
		paneO.add(Tpre_sp,g2);
		g2.gridy=6;
		paneO.add(Tpar,g2);
		
		//listener sur composant
		Cchoix.addItemListener(new AppelTrame());
}
	
private void view_STAT() {
		
		
		GridBagConstraints col1 = new GridBagConstraints();
		GridBagConstraints col2 = new GridBagConstraints();
		
		//def et pos col1
		Lcum = new JLabel("<html><u>CUMUL SUR 1 AN :</u></html>");
		
		Lt = new JLabel("TONNAGE : ");
		Lnc = new JLabel("NB NC : ");
		Lderog = new JLabel("NB DEROG : ");
		
		col1.gridx =0;
		col1.gridy =0;
		col1.gridwidth = 2; //pour mettre le titre sur toute la ligne
		col1.fill = GridBagConstraints.HORIZONTAL;
		paneBox1.add(Lcum, col1);
		col1.gridwidth=1;
		col1.fill = GridBagConstraints.NONE;
		col1.insets = new Insets(10,0,0,0);
		col1.anchor = GridBagConstraints.FIRST_LINE_END;
		col1.gridy=1;
		paneBox1.add(Lt, col1);
		col1.gridy=2;
		paneBox1.add(Lnc, col1);
		col1.gridy=3;
		paneBox1.add(Lderog, col1);
		
		//def et pos col2
		
		Tt = new JTextField(5);
		Tt.setEditable(false);
		Tnc = new JTextField(5);
		Tnc.setEditable(false);
		Tderog = new JTextField(5);
		Tderog.setEditable(false);
		
		col2.gridx =1;
		col2.insets = new Insets(10,10,0,0);
		col2.anchor = GridBagConstraints.FIRST_LINE_START;
		col2.gridy=1;
		paneBox1.add(Tt, col2);
		col2.gridy=2;
		paneBox1.add(Tnc, col2);
		col2.gridy=3;
		paneBox1.add(Tderog, col2);
}
	
private void view_CONFORMITE() {
	//	paneBox2Border = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED),"CONFORMITE", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("Time new Roman",Font.BOLD,14));
		//paneBox2.setBorder(paneBox2Border);
		
		GridBagConstraints gbc1 = new GridBagConstraints();
		GridBagConstraints gbc2 = new GridBagConstraints();
		
		//def et pos col 1
		Llot1 = new JLabel("N° DE LOT : ");
		Lnc1 = new JLabel("N° FICHE NC : "); 
		Lderog1 = new JLabel("DEROG OUI/vide : ");
		Lconf = new JLabel("CONFORMITE : ");
		
		
		gbc1.gridx =0;
		gbc1.gridy =0;
		gbc1.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc1.insets = new Insets(10,0,0,0);
		paneBox2.add(Llot1, gbc1);
		gbc1.gridy=1;
		paneBox2.add(Lnc1, gbc1);
		gbc1.gridy=2;
		paneBox2.add(Lderog1, gbc1);
		gbc1.gridy=3;
		paneBox2.add(Lconf, gbc1);
		
		//def et pos col2
		Tlot1 = new JTextField(5);
		Tlot1.setEditable(false);
		Tnc1 = new JTextField(8);
		Tderog1 = new JTextField(8);
		Tconf = new JTextField(8);
		Tconf.setEditable(false);
		
		gbc2.gridx =1;
		gbc2.gridy =0;
		gbc2.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc2.insets = new Insets(10,10,0,0);
		paneBox2.add(Tlot1, gbc2);
		gbc2.gridy=1;
		gbc2.gridwidth =2;
		paneBox2.add(Tnc1, gbc2);
		gbc2.gridy=2;
		paneBox2.add(Tderog1, gbc2);
		gbc2.gridy=3;
		paneBox2.add(Tconf, gbc2);	
		
		//recupere annee en cours
		 //donne annee en cours sous 2 chiffre
		  String motif = "yy";
		  Date date = new Date();
		  SimpleDateFormat affiche = new SimpleDateFormat(motif);
		
		LlotAnnee = new JLabel();
		LlotAnnee.setText("/ "+affiche.format(date));
		
		//affichage de l'annee dans l'encadrer
		gbc2.gridx =2;
		gbc2.gridy =0;
		gbc2.insets = new Insets(10,3,0,0);
		gbc2.anchor = GridBagConstraints.FIRST_LINE_START;
		paneBox2.add(LlotAnnee, gbc2);
		
		//listner sur textfield (N° NC)
		Tnc1.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub		
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			  if(Tconf.getText().equals("NC") && b2_resultat.isEnabled()) {
				  Tlot1.setText("");
				  b_transfert.setEnabled(true);
				  RecupDernierLot();
			  }
			  if(Tconf.getText().equals("CONFORME") &&b2_resultat.isEnabled() ) {
				  Tlot1.setText("");
				  RecupDernierLot(); 
			  }
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				if(Tconf.getText().equals("NC") && b2_resultat.isValid()) {
					  Tlot1.setText("Saisir N° NC");
					  b_transfert.setEnabled(false);
				  }
				 if(Tconf.getText().equals("CONFORME") &&b2_resultat.isEnabled() ) {
					 Tlot1.setText("Saisir N° NC");		 
				 }	
				if(Tconf.getText().equals("CONFORME") ) {		
				}
			}	
		});
		
//listner sur textfield (derog)
		Tderog1.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub		
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				INSERT();
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				if(Tlot1.getText()!=null && b_resultat.isEnabled()) {
					Tconf.setText("CONFORME");
				}
				if(b2_resultat.isEnabled() && Tnc1.getText()!=null) {
					Tconf.setText("NC");
				}
				if(Tlot1.getText()!=null && Tnc1.getText()!=null && b2_resultat.isEnabled()) {
					Tconf.setText("CONFORME");
				}
			}
			public void INSERT() {
			 if(Tres.getText().isEmpty()) {
				  if(Tnc1.getText()!=null && b_resultat.isEnabled()) {
					 Tconf.setText("CONFORME SOUS DEROG");
				  } 
				 if(Tnc1.getText()!=null && b2_resultat.isEnabled()) {
					Tconf.setText("CONFORME SOUS DEROG");
					Llot1.setText("N° DE LOT : ");
				    b_transfert.setEnabled(true);
				    RecupDernierLot();
				}
				 }
			 else {
					 Tlot1.setText(Tres.getText());
					 Tconf.setText("CONFORME SOUS DEROG");
				 }
			}
		
		});
		
	}
	
	private void view_ESSAI() {
		//paneOBorder = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),"INFOS ESSAI", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("Time new Roman",Font.BOLD,14));
		//paneE.setBorder(paneOBorder);
		paneE.setPreferredSize(new Dimension(definition.E_Large,definition.E_Haut));
		
		GridBagConstraints gbc1 = new GridBagConstraints();
		GridBagConstraints gbc2 = new GridBagConstraints();
		
		//def et placement col 1
		Lresp = new JLabel("RESPONSABLE ESSAI : ");
		Ldate1 = new JLabel("DATE CONTROLE : ");
		Ldate2 = new JLabel("DATE FABRICATION : ");
		Lq = new JLabel();
		Lobs = new JLabel("OBSERVATION : ");
		Lpresi = new JLabel("PRECISION PRODUIT : ");
		Lres = new JLabel("RESERVATION LOT : ");
		
		gbc1.gridx =0;
		gbc1.gridy =0;
		gbc1.anchor = GridBagConstraints.FIRST_LINE_END;
		paneE.add(Lresp, gbc1);
		gbc1.gridy =1;
		gbc1.insets = new Insets(15,0,0,0);
		paneE.add(Ldate2, gbc1);
		gbc1.gridy=2;
		paneE.add(Ldate1, gbc1);
		gbc1.gridy=3;
		paneE.add(Lq, gbc1);
		gbc1.gridy=4;
		paneE.add(Lobs, gbc1);
		gbc1.gridy=5;
		paneE.add(Lpresi, gbc1);
		gbc1.gridy=6;
		paneE.add(Lres, gbc1);
		
		//def et placemnt col2
		Tresp = new JTextField(5);
		Tdate1 = new JTextField(8);
		Tdate2 = new JTextField(8);
		TQ = new JTextField(8);
		Tobs = new JTextField(8);
		Cpres = new JComboBox<>();
		Tres = new JTextField(8);
		
		
		Tdate2.setText("YYYY-MM-DD"); //fab
		
		gbc2.gridx =1;
		gbc2.gridy =0;
		gbc2.anchor = GridBagConstraints.FIRST_LINE_START;
		
		paneE.add(Tresp, gbc2);
		gbc2.gridy =1;
		gbc2.insets = new Insets(15,0,0,0);
		paneE.add(Tdate2, gbc2);
		gbc2.gridy =2;
		paneE.add(Tdate1, gbc2);
		gbc2.gridy =3;
		paneE.add(TQ, gbc2);
		gbc2.gridy =4;
		paneE.add(Tobs, gbc2);
		gbc2.gridy =5;
		paneE.add(Cpres, gbc2);
		gbc2.gridy =6;
		paneE.add(Tres, gbc2);	
		
		
		//Listner just Date de Fabrication
				Tdate2.getDocument().addDocumentListener(new DocumentListener() {
					@Override
					public void changedUpdate(DocumentEvent arg0) {
						// TODO Auto-generated method stub	
						
					}
					@Override
					public void insertUpdate(DocumentEvent arg0) {
						// TODO Auto-generated method stub
						Tdate1.setText(Tdate2.getText());
					}
					@Override
					public void removeUpdate(DocumentEvent arg0) {
						// TODO Auto-generated method stub
						Tdate1.setText(Tdate2.getText());
					}
					
				});
		
	}
	
	private void view_TAB() {
		//paneS1Border = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),"RESULTATS", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("Time new Roman",Font.BOLD,14));
		//paneS1.setBorder(paneS1Border);
		paneS1.setPreferredSize(new Dimension(definition.S1_Large,definition.S1_Haut));
		
		 //table
	    result_tab = new JTable();
		Object [] colonne_entre = { "TYPE", "CRITERES","VALEURS","MIN", "MAX","STATUT","RETOUCHE","STATUT 2ND ESSAI"};
		
		//model table => redefinition methode pour colonne non modifiable ormis 2 & 6
		model_res = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int col){       
				return col ==2 || col ==6 ;
		    }};	
		
		 model_res.setColumnIdentifiers(colonne_entre);
		 
		 //association model à la table
		 result_tab.setModel(model_res);
		 result_tab.setShowGrid(false);
		 result_tab.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,11));
		 result_tab.getTableHeader().setBackground(Color.YELLOW);
		 result_tab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 TableColumn ColonneR1 = result_tab.getColumnModel().getColumn(2);
		 TableColumn ColonneR6 = result_tab.getColumnModel().getColumn(6);
		 result_tab.getColumnModel().getColumn(0).setPreferredWidth(50); 
		 result_tab.getColumnModel().getColumn(1).setPreferredWidth(175); 
		 result_tab.getColumnModel().getColumn(2).setPreferredWidth(75); 
		 result_tab.getColumnModel().getColumn(3).setPreferredWidth(80); 
		 result_tab.getColumnModel().getColumn(4).setPreferredWidth(80); 
		 result_tab.getColumnModel().getColumn(5).setPreferredWidth(110); 
		 result_tab.getColumnModel().getColumn(6).setPreferredWidth(80); 
		 result_tab.getColumnModel().getColumn(7).setPreferredWidth(110); 
		 
		//Creation combobox pour la table
		 CRes1 = new JComboBox<>();
		 CRes1.setEditable(true);
		 
		 //Ajout Combobox a la table
		 ColonneR1.setCellEditor(new DefaultCellEditor(CRes1));
		 ColonneR6.setCellEditor(new DefaultCellEditor(CRes1));
		//creation barre def et asso à la table
		 JScrollPane scp = new JScrollPane(result_tab);
			 
		scp.setPreferredSize(new Dimension(720,250));
		//scp.getViewport().setOpaque(true);
		paneS1.add(scp);
		result_tabRow = new Object[7];
	}
	
private void view_VALID() {
		//paneS2Border = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK),"", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("Time new Roman",Font.BOLD,14));
		//paneS2.setBorder(paneS2Border);
	    paneS2.setPreferredSize(new Dimension(0,200));
	    //paneS2Border.setTitleColor(Color.BLACK);
	    
	    //def bouton
	    b_resultat = new JButton("CONTROLE");
	    b2_resultat = new JButton("CONTRE ESSAI");
	    b_encours = new JButton("MISE EN ATTENTE");
	    b_fiche = new JButton("RECUPERER DONNEES");
	    b_transfert = new JButton("VALIDATION");
	    b_transfert.setEnabled(false);
	    b2_resultat.setEnabled(false);
	    
	    //aligmement centre des bouton
	    b_resultat.setAlignmentX(Component.CENTER_ALIGNMENT);
	    b2_resultat.setAlignmentX(Component.CENTER_ALIGNMENT);
	    b_encours.setAlignmentX(Component.CENTER_ALIGNMENT);
	    b_fiche.setAlignmentX(Component.CENTER_ALIGNMENT);
	    b_transfert.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
		//dimension bouton
	    b_resultat.setMaximumSize(new Dimension(definition.butAJ_Large,definition.butAJ_Haut));
	    b2_resultat.setMaximumSize(new Dimension(definition.butAJ_Large,definition.butAJ_Haut));
	    b_encours.setMaximumSize(new Dimension(definition.butAJ_Large,definition.butAJ_Haut));
	    b_fiche.setMaximumSize(new Dimension(definition.butAJ_Large,definition.butAJ_Haut));
	    b_transfert.setMaximumSize(new Dimension(definition.butAJ_Large,definition.butAJ_Haut));
	    
	    
	    
	  //ajout bouton au panel		
	  		paneS2.add(b_resultat);
	  		paneS2.add(Box.createVerticalStrut(20));
	  		paneS2.add(b2_resultat);
	  		paneS2.add(Box.createVerticalStrut(20));
	  		paneS2.add(b_encours);
	  		paneS2.add(Box.createVerticalStrut(20));
	  		paneS2.add(b_fiche);
	  		paneS2.add(Box.createVerticalStrut(20));
	  		paneS2.add(b_transfert);
	  		paneS2.add(Box.createVerticalStrut(30));
	    
	
	    
	    //action sur les boutons
	    b_resultat.addActionListener(new AffRes());
	    b2_resultat.addActionListener(new AffRes2());
	    b_encours.addActionListener(new EnCours());
	    b_fiche.addActionListener(new RecupData());
	    b_transfert.addActionListener(new transf());
	    
}
	
	
	//****************CLASS BOUTON
	
public class AffRes  implements ActionListener {                            
				  public void actionPerformed(ActionEvent e) {	 
					  
					  
// Regle pour les conditions de CONFORMITE pour les controle	
			  // Evaluer 1ere resulats en fonction du type de trame
                    //1ere passe du tableau		 	
		switch (Ttrame.getText()) {
		case "CAPILLAIRE":
		//1ere instruction	
			for(int li=0; li<result_tab.getRowCount(); li++) { 	
				 String st = String.valueOf(result_tab.getValueAt(li,5)); //colonne statut
				 String colmin = String.valueOf(result_tab.getValueAt(li,3)); // colonne MIN
				 String coltype =String.valueOf(result_tab.getValueAt(li,0)); // colonne type
				 if (st.equals("donnée a saisir") && coltype != "CS" ) {
					        if(isNumeric(colmin)   )  {
					        	
					        	if(coltype.equals("A")) {
						            if (String.valueOf(result_tab.getValueAt(li,2)).equals("Appareil HS") ) {
							            model_res.setValueAt("Faire Controle de Secours", li,5);
				    		            model_res.setValueAt("donnée a saisir",li+1,5);
						               } else if (Float.parseFloat(colmin)<=Float.parseFloat(result_tab.getValueAt(li, 2).toString()) && Float.parseFloat(result_tab.getValueAt(li, 2).toString())<=Float.parseFloat(result_tab.getValueAt(li, 4).toString())){
						    	                model_res.setValueAt("CONFORME", li,5);
						                      }
					                         else {
			        	                       model_res.setValueAt("NC", li,5);
					                          } }
				           } else {
				               if(colmin.equals(model_res.getValueAt(li, 2).toString())) {
				        	          model_res.setValueAt("CONFORME", li,5);
				               } else {
				                     model_res.setValueAt("NC", li,5);
				                      }
				             }
					if(st.equals("donnée a saisir") && String.valueOf(result_tab.getValueAt(li,0)).equals("AL")) {
						 if (String.valueOf(result_tab.getValueAt(li,2)).equals("Appareil HS") ) {
							 model_res.setValueAt("NC", li,5);
						 } else {
							 model_res.setValueAt("CONFORME", li,5);
						 }
					} 
			}
		}	
			
			//2em instruction
		for(int li=0; li<result_tab.getRowCount(); li++) { 		
				 String colmin = String.valueOf(result_tab.getValueAt(li,3)); // colonne MIN
				  String st = String.valueOf(result_tab.getValueAt(li,5)); //colonne statut
				  String res =String.valueOf(result_tab.getValueAt(li,2)); // colonne res
				  String Coltype =String.valueOf(result_tab.getValueAt(li,0));
				
				if (st.equals("donnée a saisir") && Coltype.equals("CS") && res !="null" )  {
			    	 if ( Float.parseFloat(colmin)<=Float.parseFloat(result_tab.getValueAt(li, 2).toString()) && Float.parseFloat(result_tab.getValueAt(li, 2).toString())<=Float.parseFloat(result_tab.getValueAt(li, 4).toString())) {
					     model_res.setValueAt("CONFORME", li,5);
					 } else {
					     model_res.setValueAt("NC", li,5);
				       }
		         }
		   }
			
		break;
		
		case "SANS LOT":
			
			for(int li=0; li<result_tab.getRowCount(); li++) { 	
				 String statut = String.valueOf(result_tab.getValueAt(li,5)); //colonne statut
				 String colmin = String.valueOf(result_tab.getValueAt(li,3)); // colonne MIN
				 String colmax = String.valueOf(result_tab.getValueAt(li,4)); // colonne Max
				 String type = String.valueOf(result_tab.getValueAt(li,0)); //colonne type
				 String colR1 = String.valueOf(result_tab.getValueAt(li,2)); // colonne R1
				 
				 if (statut.equals("donnée a saisir")) {
					 if (type.equals("A") && isNumeric(colmin)) {
						  if(colR1.equals("Bourrage") || colR1.equals("sans object") ) {
							  model_res.setValueAt("NC", li,5);
						  } else   if(Float.parseFloat(colmin)<=Float.parseFloat(result_tab.getValueAt(li, 2).toString()) && Float.parseFloat(result_tab.getValueAt(li, 2).toString())<=Float.parseFloat(colmax)) { 
							      model_res.setValueAt("CONFORME", li,5);
							          } else  {
								      model_res.setValueAt("NC", li,5);
						              }
					 } else if (type.equals("A") && colR1.equals(colmin)) {
						 model_res.setValueAt("CONFORME", li,5);
					       } else if (type.equals("L") && colR1.equals("VRAI")){
					    	   model_res.setValueAt("CONFORME", li,5);
					          } else if (type.equals("L") && colR1.equals("Hors Tolerance") ||colR1.equals("Bourrage") ){
					        	  model_res.setValueAt("Faire Controle de Secours", li,5);
					             } else if (type.equals("G") & colR1.equals(colmin)) {
					            	 model_res.setValueAt("CONFORME", li,5);}
					             else if (type.equals("G") & colR1.equals("FAUX")) {
					            	   model_res.setValueAt("NC", li,5);
					                    }
					                 else if (type.equals("G") && Float.parseFloat(colmin)<=Float.parseFloat(result_tab.getValueAt(li, 2).toString()) && Float.parseFloat(result_tab.getValueAt(li, 2).toString())<=Float.parseFloat(colmax)) {
					            		   model_res.setValueAt("CONFORME", li,5);
					            	      } 
					                   else {
					            	       model_res.setValueAt("NC", li,5);
					                 } 
					 }
        }		
		break;
		
		case "AVEC LOT":
			//1er traitement 
			for(int li=0; li<result_tab.getRowCount(); li++) { 	
				 String statut = String.valueOf(result_tab.getValueAt(li,5)); //colonne statut
				 String colmin = String.valueOf(result_tab.getValueAt(li,3)); // colonne MIN
				 String colmax = String.valueOf(result_tab.getValueAt(li,4)); // colonne Max
				 String type = String.valueOf(result_tab.getValueAt(li,0)); //colonne type
				 String colR1 = String.valueOf(result_tab.getValueAt(li,2)); // colonne R1
				 
				 if (statut.equals("donnée a saisir")) {
					 if (type.equals("A") && isNumeric(colmin)) {
						  if(colR1.equals("Bourrage") || colR1.equals("sans object") ) {
							  model_res.setValueAt("NC", li,5);
						  } else   if(Float.parseFloat(colmin)<=Float.parseFloat(result_tab.getValueAt(li, 2).toString()) && Float.parseFloat(result_tab.getValueAt(li, 2).toString())<=Float.parseFloat(colmax)) { 
								    model_res.setValueAt("CONFORME", li,5);
							          } else  {
								      model_res.setValueAt("NC", li,5);
						              }
					 } else if (type.equals("A") && colR1.equals(colmin)) {
						 model_res.setValueAt("CONFORME", li,5);
					       } else if (type.equals("L") && colR1.equals("VRAI")){
					    	   model_res.setValueAt("CONFORME", li,5);
					          } else if (type.equals("L") && colR1.equals("Hors Tolerance") ||colR1.equals("Bourrage") ){
					        	  model_res.setValueAt("Faire Controle de Secours", li,5);
					             } else if (type.equals("G") & colR1.equals("VRAI")) {
					            	 model_res.setValueAt("CONFORME", li,5);}
					             else if (type.equals("G") & colR1.equals("FAUX")) {
					            	   model_res.setValueAt("NC", li,5);
					                    }
					                 else if (type.equals("G") && Float.parseFloat(colmin)<=Float.parseFloat(result_tab.getValueAt(li, 2).toString()) && Float.parseFloat(result_tab.getValueAt(li, 2).toString())<=Float.parseFloat(colmax)) {
					            		   model_res.setValueAt("CONFORME", li,5);
					            	      } 
					                 else if (type.equals("S") & colR1.equals("VRAI")) {
					                	  model_res.setValueAt("CONFORME", li,5);
					                       } else if(type.equals("S")&& isNumeric(colmin) && Float.parseFloat(colmin)<=Float.parseFloat(result_tab.getValueAt(li, 2).toString()) && Float.parseFloat(result_tab.getValueAt(li, 2).toString())<=Float.parseFloat(colmax)) {
					                    	   model_res.setValueAt("CONFORME", li,5);
					                              }
				                            else if(type.equals("SG")&& isNumeric(colmin) && Float.parseFloat(colmin)<=Float.parseFloat(result_tab.getValueAt(li, 2).toString()) && Float.parseFloat(result_tab.getValueAt(li, 2).toString())<=Float.parseFloat(colmax)) {
               	                                  model_res.setValueAt("CONFORME", li,5);
                                                 }
					                            else {
					            	                model_res.setValueAt("NC", li,5);
					                               } 
					 }       
       }	
			break;
		
			default:
			break;
			
		}
					  
						
// 2em passe pour si essai de secours 
		//Declanche ou pas CONTRE ESSAI
		 
		    // compteur1 => compteur de secours
		  int CompteurDeSecours=0;
		    for(int li2=0; li2<result_tab.getRowCount(); li2++) { 
			   String statut = String.valueOf(result_tab.getValueAt(li2,5)); //colonne statut
			      if (statut.equals("Faire Controle de Secours")) {
				      CompteurDeSecours=CompteurDeSecours+1;
				      model_res.setValueAt("Faire CRTL de Secours", li2,5);
				    			         }
		        }
		     
		    if(CompteurDeSecours > 0) {
		    	for(int li3=0; li3<result_tab.getRowCount(); li3++) {
		    		 String type = String.valueOf(result_tab.getValueAt(li3,0)); //colonne type
		    		 String statut1 = String.valueOf(result_tab.getValueAt(li3,5)); //colonne type
		    		 if(type.equals("G")) {
		    			 model_res.setValueAt("donnée a saisir", li3,5);
		    		 }
		    		 if(type.equals("SG") && statut1.equals("sans object") ) {
		    			 model_res.setValueAt("donnée a saisir", li3,5);
		    		 }
		    	}
		    }
					
	        //compteur 2 => compteur si encore donnée a saisir
		        countDATA =0;
			   for(int cda=0; cda<result_tab.getRowCount(); cda++) { 
					if(String.valueOf(result_tab.getValueAt(cda,5)).equals("donnée a saisir")) {
						 countDATA++;
		    }}
						
						 
	      // compteur 3 => determine si NC
		     countNC =0;
			   for(int li=0; li<result_tab.getRowCount(); li++) { 
					 if(String.valueOf(result_tab.getValueAt(li,5)).equals("NC")) {
						 countNC++;
				 }}
						 
	     //recuperer lignes  "sans object" pour pouvoir les afficher dans STATUS2ND si NC
			    NO_ligne = new ArrayList<Integer>() ;
			   for (int so=0; so<result_tab.getRowCount(); so++) {
					 if(String.valueOf(result_tab.getValueAt(so,5)).equals("sans object")) {
							  NO_ligne.add(so);
						 }
			}
		
	    //recuperer lignes "Faire CTRL de Secours" pour afficher dans STAUTS2ND si NC
				 ArrayList<Integer> NO_ligne2 = new ArrayList<Integer>() ;
				 for (int so2=0; so2<result_tab.getRowCount(); so2++) {
					 if(String.valueOf(result_tab.getValueAt(so2,5)).equals("Faire CRTL de Secours") ||String.valueOf(result_tab.getValueAt(so2,5)).equals("Faire Controle de Secours")) {
							  NO_ligne2.add(so2);
						 }
			}	 
	//TRaitement resultats 1ere passe
			 int line_tab = 0;
			 int index_line = 0;
			 int boucle =0; //incremente nombre de boucle pour chaque association ligne tab a index tab
			 if(countNC>0 && countDATA ==0) {
						 for ( int li2=0; li2<result_tab.getRowCount(); li2++) { 
							    if(NO_ligne.size()>0) {
							    	  line_tab=NO_ligne.get(index_line);
								          if(li2 == line_tab ){
				
								            	   boucle++;
								        	     if(boucle < NO_ligne.size()) { //test nb de boucle pour ne pas depasser la taille du tableau
								        	        index_line++;
								        	        line_tab=NO_ligne.get(index_line);
								        	        model_res.setValueAt("sans object", li2,7);
								        	     }  
								              else if(boucle == NO_ligne.size()) {
								        	     line_tab=NO_ligne.get(index_line);
								            	    model_res.setValueAt("sans object", li2,7);	   
								                }
								                  else {
							    	                model_res.setValueAt("donnée a saisir", li2,7);
							                       }
								          }  else if (li2 != line_tab) {
								        	  model_res.setValueAt("donnée a saisir", li2,7);
								        	  System.out.println(boucle);
								          } 
							       }else {
						            	 model_res.setValueAt("donnée a saisir", li2,7);
						            	
			                             }
			     b2_resultat.setEnabled(true);
				 b_resultat.setEnabled(false);		  
							       } 
						} 
			//condition si controle de secours a faire
			 if(countNC>0 && NO_ligne2.size()>0) {
				 int line_tab2 = 0;
				 int index_line2 = 0;
				 int boucle2=0;
				 for ( int li3=0; li3<result_tab.getRowCount(); li3++) {
					 line_tab2=NO_ligne2.get(index_line2);
					 if(li3==line_tab2) {
						 boucle2++;
						 if(boucle2<NO_ligne2.size()) {
						 index_line2++;
						 model_res.setValueAt("", li3,7);
					 }  else if (boucle2==NO_ligne2.size()) {
						 model_res.setValueAt("", li3,7);
					 }
				 }
				 }
			 }
			 
		// Condition pour afficher conforme si NB NC = 0 et toutes les donnée remplis
			 if (countDATA ==0 && countNC == 0) {
				 Tconf.setText("CONFORME");
			 }
						
		//Debloquer le bouton valider 
				if (Tconf.getText().equals("CONFORME") && countNC ==0 && countDATA ==0 ) {
						b_transfert.setEnabled(true);
						 RecupDernierLot();
					} else if (Tres.getText().isEmpty() == false && Tconf.getText().equals("CONFORME")) {
						Tlot1.setText(Tres.getText());
						b_transfert.setEnabled(true);
					}
					
}}
	
public class AffRes2 implements ActionListener { 
	public void actionPerformed(ActionEvent e) {	
	//traitement si NC
		switch (Ttrame.getText()) {
		case "CAPILLAIRE":
			
			for(int li21=0; li21<result_tab.getRowCount(); li21++) { 	
				 String st2 = String.valueOf(result_tab.getValueAt(li21,7)); //colonne statut2
				 String colmin = String.valueOf(result_tab.getValueAt(li21,3)); // colonne MIN
				 String coltype =String.valueOf(result_tab.getValueAt(li21,0)); // colonne type
				 if (st2.equals("donnée a saisir") ) {
				        if(isNumeric(colmin)   )  {
				        	if(coltype.equals("A") || coltype.equals("CS")) {
					            if (Float.parseFloat(colmin)<=Float.parseFloat(result_tab.getValueAt(li21, 6).toString()) && Float.parseFloat(result_tab.getValueAt(li21, 6).toString())<=Float.parseFloat(result_tab.getValueAt(li21, 4).toString())){
					    	                model_res.setValueAt("CONFORME", li21,7);
					                      }
				                         else {
		        	                       model_res.setValueAt("NC", li21,7);
				                          } }
			           } else {
			               if(colmin.equals(model_res.getValueAt(li21, 6).toString())) {
			        	          model_res.setValueAt("CONFORME", li21,7);
			               } else {
			                     model_res.setValueAt("NC", li21,7);
			                      }
			             }
				if(st2.equals("donnée a saisir") && String.valueOf(result_tab.getValueAt(li21,0)).equals("AL")) {
					 if (String.valueOf(result_tab.getValueAt(li21,6)).equals("Appareil HS") ) {
						 model_res.setValueAt("NC", li21,7);
					 } else {
						 model_res.setValueAt("CONFORME", li21,7);
					 }
				} 
		}
				//REMPLIR CASE N°NC (et DEROG)
				 Tlot1.setText("Saisir N° de NC"); 
			}
			
			break;
			
		
		case "SANS LOT":
			
			for(int li=0; li<result_tab.getRowCount(); li++) { 	
				 String statut = String.valueOf(result_tab.getValueAt(li,7)); //colonne statut
				 String colmin = String.valueOf(result_tab.getValueAt(li,3)); // colonne MIN
				 String colmax = String.valueOf(result_tab.getValueAt(li,4)); // colonne Max
				 String type = String.valueOf(result_tab.getValueAt(li,0)); //colonne type
				 String colR2 = String.valueOf(result_tab.getValueAt(li,6)); // colonne R2
				 
				 if (statut.equals("donnée a saisir")) {
					 if (type.equals("A") && isNumeric(colmin)) {
						  if(colR2.equals("Bourrage") || colR2.equals("sans object") ) {
							  model_res.setValueAt("NC", li,7);
						  } else   if(Float.parseFloat(colmin)<=Float.parseFloat(result_tab.getValueAt(li, 2).toString()) && Float.parseFloat(result_tab.getValueAt(li, 2).toString())<=Float.parseFloat(colmax)) { 
								    model_res.setValueAt("CONFORME", li,7);
							          } else  {
								      model_res.setValueAt("NC", li,7);
						              }
					 } else if (type.equals("A") && colR2.equals(colmin)) {
						 model_res.setValueAt("CONFORME", li,7);
					       } else if (type.equals("L") && colR2.equals("VRAI")){
					    	   model_res.setValueAt("CONFORME", li,7);
					          } else if (type.equals("L") && colR2.equals("Hors Tolerance") ||colR2.equals("Bourrage") ){
					        	  model_res.setValueAt("Faire Controle de Secours", li,7);
					             } else if (type.equals("G") & colR2.equals(colmin)) {
					            	 model_res.setValueAt("CONFORME", li,7);}
					             else if (type.equals("G") & colR2.equals("FAUX")) {
					            	   model_res.setValueAt("NC", li,7);
					                    }
					                 else if (type.equals("G") && Float.parseFloat(colmin)<=Float.parseFloat(result_tab.getValueAt(li, 6).toString()) && Float.parseFloat(result_tab.getValueAt(li, 6).toString())<=Float.parseFloat(colmax)) {
					            		   model_res.setValueAt("CONFORME", li,7);
					            	      } 
					                   else {
					            	       model_res.setValueAt("NC", li,7);
					                     } 
					 }
       }		
				
			break;
			
		case "AVEC LOT":
			
			for(int li=0; li<result_tab.getRowCount(); li++) { 	
				 String statut2 = String.valueOf(result_tab.getValueAt(li,7)); //colonne statu2
				 String colmin = String.valueOf(result_tab.getValueAt(li,3)); // colonne MIN
				 String colmax = String.valueOf(result_tab.getValueAt(li,4)); // colonne Max
				 String type = String.valueOf(result_tab.getValueAt(li,0)); //colonne type
				 String colR2 = String.valueOf(result_tab.getValueAt(li,6)); // colonne R2
				 
				 if (statut2.equals("donnée a saisir")) {
					 if (type.equals("A") && isNumeric(colmin)) {
						  if(colR2.equals("Bourrage") || colR2.equals("sans object") ) {
							  model_res.setValueAt("NC", li,7);
						  } else   if(Float.parseFloat(colmin)<=Float.parseFloat(result_tab.getValueAt(li, 6).toString()) && Float.parseFloat(result_tab.getValueAt(li, 6).toString())<=Float.parseFloat(colmax)) { 
								    model_res.setValueAt("CONFORME", li,7);
							          } else  {
								      model_res.setValueAt("NC", li,7);
						              }
					 } else if (type.equals("A") && colR2.equals(colmin)) {
						 model_res.setValueAt("CONFORME", li,7);
					       } else if (type.equals("L") && colR2.equals("VRAI")){
					    	   model_res.setValueAt("CONFORME", li,7);
					          } else if (type.equals("L") && colR2.equals("Hors Tolerance") ||colR2.equals("Bourrage") ){
					        	  model_res.setValueAt("Faire Controle de Secours", li,7);
					             } else if (type.equals("G") & colR2.equals("VRAI")) {
					            	 model_res.setValueAt("CONFORME", li,7);}
					             else if (type.equals("G") & colR2.equals("FAUX")) {
					            	   model_res.setValueAt("NC", li,7);
					                    }
					                 else if (type.equals("G") && Float.parseFloat(colmin)<=Float.parseFloat(result_tab.getValueAt(li, 6).toString()) && Float.parseFloat(result_tab.getValueAt(li, 6).toString())<=Float.parseFloat(colmax)) {
					            		   model_res.setValueAt("CONFORME", li,7);
					            	      } 
					                 else if (type.equals("S") & colR2.equals("VRAI")) {
					                	  model_res.setValueAt("CONFORME", li,7);
					                       } else if(type.equals("S")&& isNumeric(colmin) && Float.parseFloat(colmin)<=Float.parseFloat(result_tab.getValueAt(li, 6).toString()) && Float.parseFloat(result_tab.getValueAt(li, 6).toString())<=Float.parseFloat(colmax)) {
					                    	   model_res.setValueAt("CONFORME", li,7);
					                              }
				                            else if(type.equals("SG")&& isNumeric(colmin) && Float.parseFloat(colmin)<=Float.parseFloat(result_tab.getValueAt(li, 6).toString()) && Float.parseFloat(result_tab.getValueAt(li, 6).toString())<=Float.parseFloat(colmax)) {
              	                                  model_res.setValueAt("CONFORME", li,7);
                                                }
					                            else {
					            	                model_res.setValueAt("NC", li,7);
					                               } 
					 }    
				//REMPLIR CASE N°NC (et DEROG)
				 Tlot1.setText("Saisir N° de NC"); 
      }	
			break;
			
		default:
			break;
		}
		
		
		// Indiquer resutlat dans TCONF
		 CountNbNC =0;
		 for (int j=0;j<result_tab.getRowCount();j++) {
			 String st3 = String.valueOf(result_tab.getValueAt(j,7));
			 if(st3.equals("NC")) {
				 CountNbNC++;
			 }
		 }
		 
       //REsultat de la conformite
		 if(CountNbNC>0) {
			 Tconf.setText("NC");
		 } else {
			 Tconf.setText("CONFORME");
		 }
		
	}
}




public class EnCours implements ActionListener {
				  public void actionPerformed(ActionEvent e) {	 
					
						// AJOUT DANS LA TABLE MP INTERMEDIARE LES RESULTAT EN ATTENTE DE TRANSFERT DANS LA BASE MP
	  if(Tres.getText().isEmpty()) {
			 JOptionPane.showMessageDialog(null, "Renseigner la case N° de LOT Reservation pour enregistrement ulterieur");
				  }
		  else {					  
					  
					  // 1 **** Transfert de la fiche vers la base mp_int
					  try {		 
						  int c_r=13;
						  
						   connect= connexion.getConnection(); //fait appel a la classe connexion
						    	String StAjout ="INSERT into TABLE_PF_INT "
				   		
						   
	                                           +   " VALUES (?,?,?,?,?,?,?,?,?,?,?,?" //12
	                                                    +",?,?,?,?,?,?,?,?,?,?" //10
	                                                    +",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," //20
	                                                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; //20
		
							
							PreparedStatement psA = (PreparedStatement) connect.prepareStatement(StAjout);		
							
							//champs info
							psA.setString(1,null);
							psA.setString(2, (String) Cchoix.getSelectedItem());
							psA.setString(3, Tconf.getText());
							psA.setString(4,Tres.getText());
							psA.setString(5,Tnc1.getText());
							psA.setInt(6,Qnc);
							psA.setInt(7,NB_DEROG);
							psA.setString(8,Tdate2.getText());
							psA.setString(9,TQ.getText());
							psA.setString(10,Tresp.getText());
							psA.setString(11, Tobs.getText());
							psA.setString(12, Calcul.Complete(model_res,result_tab, Tresp, Tdate2,TQ));
							
							       //Ajout valeur tableau à la base en 
							
								for (int l = 0; l <result_tab.getRowCount(); l++) {
									 psA.setString(c_r,(String) result_tab.getValueAt(l, 2)); // ajout a R(x)
									 c_r ++;  
								}
														
								// pour completer BDD non utilisé
								   int somme = result_tab.getRowCount()+13; //calcul du nombre de ligne bdd utilisé
								   int ligne;
								 
									if (c_r<63) {
									  	for (ligne=somme; ligne <63; ligne++) {	
									  		psA.setString(ligne,null); 		
									  	}}
									
									 //Ajout Resultat 2nd controle si existe
									int  c_r2=38;
									if(b2_resultat.isEnabled())	{			
											for(int r2=0; r2 <result_tab.getRowCount();r2++) {
												psA.setString(c_r2, (String) result_tab.getValueAt(r2, 6));
												c_r2++;
												
											}
									   }		
									
							psA.executeUpdate();
							psA.close();	
							JOptionPane.showMessageDialog(null,"Le Controle du Produit Fini : "+Cchoix.getSelectedItem()+" a été mise en attente de validation.");
							fen_PF.dispose();
							//zonetxtsud.setText(new Date() + "      Donner enregistrer dans la base de donnees");
						   
					  } catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						   }		  	  				  
				 }
		  }}




//bouton RECUPERER DONNEE DE LA FICHE DE CONTROLE
public class RecupData implements ActionListener {
		  public void actionPerformed(ActionEvent e) {	 
			//1 Affiche data base INTERMEDIARE vers fiche MP 
			  try {
					
					connect= connexion.getConnection(); //initialisation connection
					 String info = "SELECT * FROM TABLE_PF_INT WHERE ID = ?"; 
					 PreparedStatement pinfo = (PreparedStatement) connect.prepareStatement(info);
					 pinfo.setInt(1, prime.recup_id);
					 ResultSet rs_info = pinfo.executeQuery(); // resultat de la selection
					 
					  while(rs_info.next()) {
							  Cchoix.setSelectedItem(rs_info.getString("NOM"));
							  Tdate2.setText(rs_info.getString("DATE_C"));
							  TQ.setText(rs_info.getString("QTITE"));
							  Tresp.setText(rs_info.getString("RESP"));
							  Tobs.setText(rs_info.getString("OBSERVATION"));
							  Tres.setText(rs_info.getString("RES"));
						 //remplissage tableau => colonne Resultat 1
							int res =13; 
				        	for (int ligne=0; ligne<result_tab.getRowCount();ligne++) {		  
						         model_res.setValueAt(rs_info.getString(res), ligne,2);	 
						         res++;
				     	}	
				        	//remplissage tableau => colonen Resultat 2
					        int res2=38;
					        for (int ligne2=0; ligne2<result_tab.getRowCount();ligne2++) {		  
						         model_res.setValueAt(rs_info.getString(res2), ligne2,6);	 
						         res2++;
				     	        }	
				        	
					   }
					  rs_info.close();
					  pinfo.close();
					 
               } catch (Exception e1) {
		JOptionPane.showMessageDialog(null, e1);
                 }
			  
			  
	       // 2 Supprime dans la base intermediaire l'enregistrement
			  try {
					
					connect= connexion.getConnection(); //initialisation connection
					 String info = "DELETE FROM TABLE_PF_INT WHERE ID = ?"; 
					 PreparedStatement pinfo = (PreparedStatement) connect.prepareStatement(info);
					 pinfo.setInt(1, prime.recup_id);
					 pinfo.executeUpdate(); // resultat de la selection	
					  pinfo.close(); 
               } catch (Exception e1) {
		JOptionPane.showMessageDialog(null, e1);
                 }				  
	}}


public class transf implements ActionListener {
	 public void actionPerformed(ActionEvent e) {	 
					
	 //Validiter du controle pour le transerfert
	if(Tconf.getText().equals("CONFORME") && CountNbNC>0) {
			 JOptionPane.showMessageDialog(null, "Le controle n'est pas valide ! \n <CONFORME> ne peut etre associé a une NC");
		} else {
					  
					  
					  //Affection Numeraire d'une non conformite
					  if (Tnc1.getText().isEmpty()) {
						  Qnc = 0;
					  } else {
						  Qnc = 1;
					  }
					  
					  //Affection Numeraire de la derogation
					  if (Tderog1.getText().equals("OUI")) {
						  NB_DEROG = 1;
					  } else {
						  NB_DEROG = 0;
					  } 
					  
			
					  
					  //1 *** Recupere le numero de version de la trame PF pourl'associer ensuite dans la requette suivante
					  try {
						  connect= connexion.getConnection(); //fait appel a la classe connexion
						  String St_Ver = "SELECT (TRAME_VER)  FROM TABLE_TPF  WHERE NOM = ? AND ID = (SELECT MAX(ID) FROM TABLE_TPF WHERE NOM= ?)"; 
		     				PreparedStatement pps = (PreparedStatement) connect.prepareStatement(St_Ver);
		     				pps.setString(1, (String) Cchoix.getSelectedItem());
		     				pps.setString(2, (String) Cchoix.getSelectedItem());
		     				
		     				ResultSet rs_ver = pps.executeQuery(); // resultat de la selection
		     				  while(rs_ver.next()) {
		     					  getVersion = rs_ver.getString("TRAME_VER"); 
		     				  }
						  
		     				    rs_ver.close();  
			     				pps.close();
			     				
					  } catch  (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						   }
				  
					  
					  // 2 **** Transfert de la fiche vers la base PF
					  try {
							
						  int c_t=21; 
						  int c_c=22;
						  int c_mi=23;
						  int c_Ma=24;
						  int c_r=25;
						  
						   connect= connexion.getConnection(); //fait appel a la classe connexion

						    	String StAjout ="INSERT into TABLE_PF "
				   		
						   
	                                           +   " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" //20
	                                                    +",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" //25
	                                                    +",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" //25
	                                                    +",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" //25
	                                                    +",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" //25
	                                                    +",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" //25
	                                                    +",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" //25
						                                +",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )"; //25
		
							
							PreparedStatement psA = (PreparedStatement) connect.prepareStatement(StAjout);
							//champs info
							psA.setString(1,null);
							psA.setString(2,Ttrame.getText());
							psA.setString(3,  getVersion);
							psA.setString(4, (String) Cchoix.getSelectedItem());
							psA.setString(5, Tfreq.getText());
							psA.setString(6, TQt.getText() );
							psA.setString(7, Tmod.getText());
							psA.setString(8, Tpre.getText());
							psA.setString(9, Tpar.getText());
							psA.setString(10, Tconf.getText());
							
							//determination remplissage de la colonne lot
							if(Ttrame.getText().equals("AVEC LOT") || Ttrame.getText().equals("CAPILLAIRE")){
								psA.setString(11,Tlot1.getText());
							} else {
								psA.setString(11,null);
							}
							   
							//determination du lot fictif
							if (lotfictif == 0) {
								psA.setString(12, null);
							} else {
								psA.setString(12, "1");
							}
							
							
							psA.setString(13,Tnc1.getText());
							psA.setInt(14,Qnc);
							psA.setInt(15,NB_DEROG);
							psA.setString(16,Tdate2.getText());
							psA.setString(17,TQ.getText());
							psA.setString(18,Tresp.getText());
							psA.setString(19, Tobs.getText());
							psA.setString(20, Lcat.getText());
						
							       //Ajout valeur tableau à la base en 
							
								for (int l = 0; l <result_tab.getRowCount(); l++) {			
									 psA.setString(c_t,result_tab.getValueAt(l, 0).toString()); //ajout a T(x)
									 psA.setString(c_c,result_tab.getValueAt(l, 1).toString()); // ajout a C(x)
									 psA.setString(c_mi,(String) result_tab.getValueAt(l, 3)); // ajout a Min(x)
									 psA.setString(c_Ma,(String) result_tab.getValueAt(l, 4)); // ajout a Max(x)
									 psA.setString(c_r,(String) result_tab.getValueAt(l, 2)); // ajout a R(x)
									 c_t=c_t+5;
									 c_c=c_c+5;
									 c_mi=c_mi+5;
									 c_Ma=c_Ma+5;
									 c_r = c_r+5;  
								}
														
								// pour completer BDD non utilisé
								   int somme = result_tab.getRowCount()*5+21; //calcul du nombre de ligne bdd utilisé
								   int ligne;
								 
									if (c_t<196) {
									  	for (ligne=somme; ligne <196; ligne++) {	
									  		psA.setString(ligne,null); 		
									  	}}
									
								
									
									//(d) Ajout valeur (2er resultat) tableau à la base MP => pour revalider les donnée
									int base_ligne = 146; //a partir de la ligne 94 de la BDD : RS1 
									for (int l = 0; l <result_tab.getRowCount(); l++) {		
										 psA.setString(base_ligne,(String) result_tab.getValueAt(l, 6)); // ajout a R(x)
										 base_ligne++;	
									}
									
								// (e) Ajout valeur Resultat 1 (R) ou Resultat 2 (RS) dans Result final (RF) en fonction presence (RS)
								int base_ligne2 = 171;
									if(result_tab.getValueAt(0, 6)== null) {
									  for (int l = 0; l <result_tab.getRowCount(); l++) {	
										 psA.setString(base_ligne2,(String) result_tab.getValueAt(l, 2)); // ajout a R(x) à RF
										 base_ligne2++;
									    }
								   } else {
									   for (int l = 0; l <result_tab.getRowCount(); l++) {		
											 psA.setString(base_ligne2,(String) result_tab.getValueAt(l, 6)); // ajout a R(x)
											 base_ligne2++;	
										}
								   }	
									
				//3 (a) Determination Si Eprouvette faite ou pas dans la fiche de controle
									
							 switch (Ttrame.getText())	
								{
								case "AVEC LOT":
									 for(int ligne2=0; ligne2<result_tab.getRowCount(); ligne2++) { 
									
							                if(result_tab.getValueAt(ligne2, 1).toString().equals("Caracteristiques mecaniques a 28 jours")  && result_tab.getValueAt(ligne2, 5).toString().equals("CONFORME")) {
									            Casse ++; 
								              } 
							                }
							      break;
							  
								case "SANS LOT":
									
									 for(int ligne2=0; ligne2<result_tab.getRowCount(); ligne2++) {
										  String colMax = String.valueOf(result_tab.getValueAt(ligne2,4)); // colonne max
										  
									     if(result_tab.getValueAt(ligne2, 1).toString().equals("Caracteristiques mecaniques a 28 jours")  && result_tab.getValueAt(ligne2, 2).toString().equals(colMax)) {
									         Casse++; 
								          } }
									break;
									
								default:
									break;
							  }
						 
							psA.executeUpdate();
							psA.close();	
							JOptionPane.showMessageDialog(null,"Le Controle sur le PF : "+Cchoix.getSelectedItem()+" a été ajoutée à la base.");
							fen_PF.dispose();
							
						   
					  } catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						   }	
					  
		//3 (b) Creation ligne dans la base EMP pour essai supplementaire			  
						 if(Casse ==1 && Ttrame.getText().equals("AVEC LOT")) {
					
							 AjoutEMAvecLot();
						 }
				
						 if(Casse ==1 && Ttrame.getText().equals("SANS LOT") && Tconf.getText() != "NC") {	
							
							 AjoutEMSansLot();
						 }
				  }
	}
}
			
public class AppelTrame implements ItemListener {
				@Override
				public void itemStateChanged(ItemEvent arg) {
					// TODO Auto-generated method stub
				//initialise varirable
					model_res.setRowCount(0); //reinitialiser tableau
					int val =0;
					
					
		 // 1-  appel trame dans base trame
					if(arg.getStateChange() == ItemEvent.SELECTED){
		                
		                 try {
		                	 connect= connexion.getConnection(); //initialisation connection
		     				
		     				                // selecton type de donne de la base en fonction du derniere trame effectuée
		                	String sql = "SELECT * FROM TABLE_TPF  WHERE NOM = ? AND ID = (SELECT MAX(ID) FROM TABLE_TPF WHERE NOM= ?)"; 
		     				PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
		     				
		     				pState.setString(1, (String) Cchoix.getSelectedItem());
		     				pState.setString(2, (String) Cchoix.getSelectedItem());
		     				ResultSet rs = pState.executeQuery(); // resultat de la selection
		     				//ajout element 1 a 1
		     				while(rs.next()) {
		     				    int count;
		     					//ajout element donne
		     					Ttrame.setText(rs.getString(2));
		     					Tfreq.setText(rs.getString("FREQUENCE"));
		     					TQt.setText(rs.getString("QTITE_PREL"));
		     					Tmod.setText(rs.getString("MODOP"));
		     					Tpre.setText(rs.getString("PREPARATION"));
		     					Tpar.setText(rs.getString("PARTICULARITE"));
		     					Lq.setText(rs.getString("QTITE_UNIT"));
		     					Lcat.setText(rs.getString("CAT"));
		     					lotfictif = rs.getInt("LOT_FICTIF");
		     					
		     					//ajout element dans tableau
		     					for (count=15; count<112; count=count+4) {
		    	   					result_tabRow[0] = rs.getString(count);
		    	   					result_tabRow[1] = rs.getString(count+1);
		    	   					result_tabRow[3] = rs.getString(count+2);
		    	   					result_tabRow[4] = rs.getString(count+3);
		    	   					model_res.addRow(result_tabRow);}
		     				}
		     				//ferme les connexions
		     				
		     				rs.close();  
		     				pState.close();
		                    prime.CentrerTableau(2, result_tab);
		     				
		                      } catch (Exception e) {
		                	 e.printStackTrace();
		                        }
		     //recuperer valeur du lot_fictif => adaptation trame en fonction du type de trame
		                 try {
		                	     connect= connexion.getConnection(); //initialisation connection
			     				
                        	  String sql = "SELECT LOT_FICTIF FROM TABLE_TPF  WHERE NOM = ? AND ID = (SELECT MAX(ID) FROM TABLE_TPF WHERE NOM= ?)"; 
			             	PreparedStatement pState2 = (PreparedStatement) connect.prepareStatement(sql);
				             pState2.setString(1, (String) Cchoix.getSelectedItem());
				             pState2.setString(2, (String) Cchoix.getSelectedItem());
				             ResultSet rs2 = pState2.executeQuery(); // resultat de la selection
				             while(rs2.next()) {
				            	 val = rs2.getInt("LOT_FICTIF");
				             }
				             pState2.close();
				             rs2.close();
		                 }  catch (Exception e) {
		                	 e.printStackTrace();
	                        }
		                 
		                 //1 bis- afficher le label lot/sans lot/capilaire
						  if(Ttrame.getText().equals("SANS LOT")) {
							  Llot1.setVisible(false);
							  Tlot1.setVisible(false);
							  Lres.setVisible(false);
							  Tres.setVisible(false);
							  LlotAnnee.setVisible(false);
							  
						  }
						  
						  if((Ttrame.getText().equals("AVEC LOT") && val == 0) || Ttrame.getText().equals("CAPILLAIRE")){
							  Llot1.setVisible(true);
							  Tlot1.setVisible(true);
							  Llot1.setText("N° DE LOT :");
							  Lres.setVisible(true);
							  Tres.setVisible(true);
							 
						  }
						  
						  if(Ttrame.getText().equals("AVEC LOT") && val == 1) {
							  Llot1.setVisible(true);
							  Llot1.setText("N° LOT (Pour Crtl) : ");
							  Tlot1.setVisible(true);
							  Lres.setVisible(true);
							  Tres.setVisible(true);
						  }  } 
					
					
					 ListeComboxTrame(); // Appel methode pour charger la liste de choix en fonction du type de trame
					 
		// 2- remplir colonne STATUT dans le tableau
					 for (int j =0; j<result_tab.getRowCount(); j++) {
					        
		        		 if (result_tab.getValueAt(j, 0).toString().equals("A")) {
		        			 model_res.setValueAt("donnée a saisir", j,5);		
		        		     }
		        		 if (result_tab.getValueAt(j, 0).toString().equals("L")) {
		        			 model_res.setValueAt("donnée a saisir", j,5);		
		        		     }
		        		 if(result_tab.getValueAt(j, 0).toString().equals("CS")) {
		        			 model_res.setValueAt("sans object", j,5);
		        		 }
		        		 if(result_tab.getValueAt(j, 0).toString().equals("AL")) {
		        			 model_res.setValueAt("donnée a saisir", j,5);
		        		 }
		        		 
		        		 if(result_tab.getValueAt(j, 0).toString().equals("G")) {
		        			 model_res.setValueAt("sans object", j,5);
		        		 }
		        		 if(result_tab.getValueAt(j, 0).toString().equals("SG") || result_tab.getValueAt(j, 0).toString().equals("S") ) {
			        		model_res.setValueAt(EssaiAFaire((String) Cchoix.getSelectedItem()), j, 5);
			        		 }	
		              }			   
					
	 //3-redefini la dimension du tableau a afficher
					  int count =0;
					                   // compte nb de ligne non null
					      for(int ligne =0; ligne<result_tab.getRowCount();ligne++) {
						  if(result_tab.getValueAt(ligne, 4) != null)
							  count++;	 
					       } 
					                  //suprime les lignes null
					       for(int col =result_tab.getRowCount()-1 ; col >= count; col--) {
						  model_res.removeRow(col);
					      }	
					       
					       
		//4 - calcul Encadre STATS
					       // cumul Tonnage
					      try {
					    	  connect= connexion.getConnection(); //initialisation connection
					    	 // String St2 = "SELECT SUM(QTITE) AS CUMUL_ANNEE FROM TABLE_PF  WHERE NOM = ? "; 
					    	  String St2 = "SELECT SUM(CASE WHEN CONFORMITE <>'NC' THEN QTITE ELSE 0 END) AS CUMUL_ANNEE FROM TABLE_PF  WHERE NOM = ? "; 
					    	  PreparedStatement pSt =  (PreparedStatement) connect.prepareStatement(St2);
					    	  pSt.setString(1, (String) Cchoix.getSelectedItem());
					    	  ResultSet rs2 = pSt.executeQuery(); // resultat de la selection
					    	  while(rs2.next()) {
					    		  Tt.setText(rs2.getString("CUMUL_ANNEE"));
					    	  }
					    	  rs2.close();  
			     			 pSt.close();
					          } catch (Exception e) {
			                	 e.printStackTrace();
	                      }
					      // cumul NC
					      
					      try {
					    	  connect= connexion.getConnection(); //initialisation connection
					    	  String St3 = "SELECT SUM(Q_NC) AS Q_NC_CUMUL FROM TABLE_PF  WHERE NOM = ? "; 
					    	  PreparedStatement pSt3 =  (PreparedStatement) connect.prepareStatement(St3);
					    	  pSt3.setString(1, (String) Cchoix.getSelectedItem());
					    	  ResultSet rs3 = pSt3.executeQuery(); // resultat de la selection
					    	  while(rs3.next()) {
					    		  Tnc.setText(rs3.getString("Q_NC_CUMUL"));
					    	  }
					    	  rs3.close();  
			     			 pSt3.close();
					          } catch (Exception e3) {
			                	 e3.printStackTrace();
	                      }
					     
					      
					      // cumul Derog
					      try {
					    	  connect= connexion.getConnection(); //initialisation connection
					    	  String St4 = "SELECT SUM(DEROG) AS DEROG_CUMUL FROM TABLE_PF  WHERE NOM = ? "; 
					    	  PreparedStatement pSt4 =  (PreparedStatement) connect.prepareStatement(St4);
					    	  pSt4.setString(1, (String) Cchoix.getSelectedItem());
					    	  ResultSet rs4 = pSt4.executeQuery(); // resultat de la selection
					    	  while(rs4.next()) {
					    		  Tderog.setText(rs4.getString("DEROG_CUMUL"));
					    	  }
					    	  rs4.close();  
			     			 pSt4.close();
					          } catch (Exception e4) {
			                	 e4.printStackTrace();
	                      }					  
}}
			
public boolean isNumeric(String s) {  
			    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
			}  		
			
			
// Fonction divers************
	private void affichage() {
		if (Ttrame.getText().equals("SANS LOT")) {
			Llot1.setVisible(false);
			Tlot1.setVisible(false);
			Lres.setVisible(false);
			Tres.setVisible(false);
		}}
	
	
	
private void ListeNomPF() {
				
				try {
					 Cchoix.removeAllItems(); //supprime element residuel
					connect= connexion.getConnection(); //initialisation connection
					
					String sql = "SELECT DISTINCT NOM FROM TABLE_TPF ORDER BY NOM "; // selecton type de donne de la base
					
					PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
					
					ResultSet rs = pState.executeQuery(); // resultat de la selection
					
					while(rs.next()) {
						Cchoix.addItem(rs.getString("NOM"));
					}
					//ferme les connexions
					Cchoix.setSelectedIndex(-1);
					rs.close();  
					pState.close();
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
	}}
	
private void ChargeListe() {  //charge liste de precision ** appel dans le constructeur **
		
		try {
			 Cpres.removeAllItems(); //supprime element residuel
			connect= connexion.getConnection(); //initialisation connection
			
			String sql = "SELECT PRES FROM CHOIX WHERE PRES IS NOT NULL ORDER BY PRES"; // selecton type de donne de la base
			PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
			
			ResultSet rs = pState.executeQuery(); // resultat de la selection
			//ajout element 1 a 1
			while(rs.next()) {
				Cpres.addItem(rs.getString("PRES"));
			}
			//ferme les connexions
			Cpres.setSelectedIndex(-1);
			rs.close();  
			pState.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}}
	
	
	private void ListeComboxTrame() {

		//determination du type trame : CAPPILAIRE OU CLASSIQUE
		//if(Ttrame.getText() == "CLASSIQUE") {
		switch (Ttrame.getText())	
		{
		case "CAPILLAIRE":
		
			// affectation de la liste cappilaire au combobox
			try {
				 CRes1.removeAllItems(); //supprime element residuel
				connect= connexion.getConnection(); //initialisation connection
				
				String sql = "SELECT CAPILLAIRE FROM CHOIX WHERE CAPILLAIRE IS NOT NULL ORDER BY CAPILLAIRE "; // selecton type de donne de la base
				
				PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
				
				ResultSet rs = pState.executeQuery(); // resultat de la selection
				//ajout element 1 a 1
				while(rs.next()) {
					CRes1.addItem(rs.getString("CAPILLAIRE"));
				}
				//ferme les connexions
				CRes1.setSelectedIndex(-1);
				rs.close();  
				pState.close();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		
		break;
		
		
		case "SANS LOT":
			
			// affectation de la liste cappilaire au combobox
					try {
						 CRes1.removeAllItems(); //supprime element residuel
						connect= connexion.getConnection(); //initialisation connection
						
						String sql = "SELECT SANSLOT FROM CHOIX WHERE SANSLOT IS NOT NULL ORDER BY SANSLOT "; // selecton type de donne de la base
						
						PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
						
						ResultSet rs = pState.executeQuery(); // resultat de la selection
						//ajout element 1 a 1
						while(rs.next()) {
							CRes1.addItem(rs.getString("SANSLOT"));
						}
						//ferme les connexions
						CRes1.setSelectedIndex(-1);
						rs.close();  
						pState.close();
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}	
			break;
			
        case "AVEC LOT":
			
			// affectation de la liste cappilaire au combobox
					try {
						 CRes1.removeAllItems(); //supprime element residuel
						connect= connexion.getConnection(); //initialisation connection
						
						String sql = "SELECT AVECLOT FROM CHOIX WHERE AVECLOT IS NOT NULL ORDER BY AVECLOT "; // selecton type de donne de la base
						
						PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
						
						ResultSet rs = pState.executeQuery(); // resultat de la selection
						//ajout element 1 a 1
						while(rs.next()) {
							CRes1.addItem(rs.getString("AVECLOT"));
						}
						//ferme les connexions
						CRes1.setSelectedIndex(-1);
						rs.close();  
						pState.close();
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}	
			break;		
			default:
				System.out.println("ERREUR");
				break;
		}}	


public String EssaiAFaire(String nom) {  // pour determiner si essai sup a faire ou pas
	String Statut ="";
	//REGLE : essai sup si + de 13 jours apres dernier essai sup
	//                              ou
	//                      + de 10 lots apres dernier essai sup
	
	//1definition de la date du jour
		Date Date_jour = new Date();
		DateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		
		
		//2 Recuperer date du dernier essai sup avec son lot 
		try {
			   connect= connexion.getConnection(); //initialisation connection
		
		      String Recup = "SELECT MAX(D_FAB) AS DERNIER_DATE, MAX(N_LOT) AS MAXLOT FROM TABLE_EM WHERE PF = ? "; // selecton type de donne de la base
		      PreparedStatement pRDEM = (PreparedStatement) connect.prepareStatement(Recup);
		      pRDEM.setString(1, nom);
		       ResultSet rs = pRDEM.executeQuery(); // resultat de la selection	      
		          while(rs.next()) {
			          LastDateES = rs.getString("DERNIER_DATE");
		              D_LOT  = rs.getInt("MAXLOT");
		          }
		         rs.close();  
		        pRDEM.close();
		     
	      }catch (Exception e) {
		         JOptionPane.showMessageDialog(null, e);
	       }
		
		//3 Regle pour laps de 10 lot avant nouveau essai sup
		  D_LOT_DIX = D_LOT +10;
		 
		     // determiner lot en cours
		  try {
			   connect= connexion.getConnection(); //initialisation connection
		
		       String Recup = "SELECT MAX(N_LOT) AS DERNIER_LOT FROM TABLE_PF WHERE NOM = ? "; // selecton type de donne de la base
		      PreparedStatement pRDEM = (PreparedStatement) connect.prepareStatement(Recup);
		      pRDEM.setString(1, nom);
		       ResultSet rs = pRDEM.executeQuery(); // resultat de la selection	      
		          while(rs.next()) {
			         
		              DERNIER_LOT  = rs.getInt("DERNIER_LOT");
		          }
		         rs.close();  
		        pRDEM.close();
		     
	      }catch (Exception e) {
		         JOptionPane.showMessageDialog(null, e);
	       }
		  
		      LOT_ENCOURS = DERNIER_LOT + 1;
		     
		//var       
	String formatDateduJour = sdf.format(Date_jour);
		
	if (LastDateES!= null) { //voir si essai existe
		//3 Comparer 2 dates
		date1 =  LocalDate.parse(LastDateES);
		date2 = LocalDate.parse(formatDateduJour);
		
		Period period = Period.between(date1, date2);
		
		
		System.out.println("interval jour : "+period.getDays());
		System.out.println("lot en cours : "+LOT_ENCOURS);
		System.out.println("lot + 10 : "+D_LOT_DIX);
		// indiquer resultat
		       if (period.getDays()>13 || period.getMonths()>0) {
			            Statut = "donnée a saisir";	
			            
		           } 
		       else if(period.getDays()<13 && period.getMonths()==0 && LOT_ENCOURS == D_LOT_DIX) {
			             Statut = "donnée a saisir";
			            
		               } else {
		        	       Statut = "sans object";
		        	      
		                      }
		       } 
	
	if (LastDateES == null){ //si date d'eprouvette n'existe pas
			       Statut = "donnée a saisir";
			      
		              }
	
		return Statut;
}

protected void AjoutEMAvecLot() {
	 //recuperer n°_idPF
	 try {
			connect = connexion.getConnection();
			String Id_recupPF = "SELECT ID FROM TABLE_PF WHERE NOM = ? AND DATE_C = ?";
			PreparedStatement id_state = (PreparedStatement) connect.prepareStatement(Id_recupPF);
			id_state.setString(1, (String) Cchoix.getSelectedItem());
			id_state.setString(2, Tdate2.getText());
			ResultSet rs_id = id_state.executeQuery();
			while (rs_id.next()) {
				no_id = rs_id.getString(1);
			}
         
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	 
	// Recuperation des données de la liste des critere de conformite a importer
		// dans la table_em
		try {

			connect = connexion.getConnection(); // fait appel a la classe connexion
			String temp = "SELECT * FROM LISTE_PARA WHERE NOM=? "; // selecton type de donne de la base
			PreparedStatement ptemp = (PreparedStatement) connect.prepareStatement(temp);
			ptemp.setString(1, (String) Cchoix.getSelectedItem());
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
	 
		String LotAss = JOptionPane.showInputDialog(null,"Indiquer N° lot produit associé (Resine ou poudre si bicomposant) sinon indiqué : 0 ");
	//creation ligne dans la BASE EM
	  try {
		  connect = connexion.getConnection(); // fait appel a la classe connexion
			String EM_Ajout = "INSERT into TABLE_EM (ID_PF,PF,METHODE,Q_RES,Q_EAU,Q_POUDRE,RES,D_COUL,D_FAB,N_LOT,N_LOT_C,STATUT,D_CASSE)"
					+ "  VALUES (?,?,?,?,?,?,?,?,?,?,?,?, DATE_ADD(?, INTERVAL 28 DAY))  "; // 11

			PreparedStatement psEM = (PreparedStatement) connect.prepareStatement(EM_Ajout);

			// champs info

			psEM.setString(1, no_id);
			psEM.setString(2, (String) Cchoix.getSelectedItem());
			psEM.setString(3, Methode);
			psEM.setString(4, Q_res);
			psEM.setString(5, Q_eau);
			psEM.setString(6, Q_poudre);
			psEM.setString(7, Tres.getText());
			psEM.setString(8, Tdate1.getText());
			psEM.setString(9, Tdate2.getText());
			psEM.setString(10, Tlot1.getText());
			psEM.setString(11, LotAss);
			psEM.setString(12, "COOL");
			psEM.setString(13, Tdate1.getText()); // permet d'avoir la date a 28 jours

			psEM.executeUpdate();
			psEM.close();
		 JOptionPane.showMessageDialog(null, "Des eprouvettes ont été réalisé. La ligne a été ajouté à la base EM");	
	  } catch (SQLException e3) {
		// TODO Auto-generated catch block
			e3.printStackTrace();
	  }
}


protected void AjoutEMSansLot() {
	
	 //recuperer n°_idPF
	 try {
			connect = connexion.getConnection();
			String Id_recupPF = "SELECT ID FROM TABLE_PF WHERE NOM = ? AND DATE_C = ?";
			PreparedStatement id_state = (PreparedStatement) connect.prepareStatement(Id_recupPF);
			id_state.setString(1, (String) Cchoix.getSelectedItem());
			id_state.setString(2, Tdate2.getText());
			ResultSet rs_id = id_state.executeQuery();
			while (rs_id.next()) {
				no_id = rs_id.getString(1);
			}
        
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	 
	// Recuperation des données de la liste des critere de conformite a importer
			// dans la table_em
			try {

				connect = connexion.getConnection(); // fait appel a la classe connexion
				String temp = "SELECT * FROM LISTE_PARA WHERE NOM=? "; // selecton type de donne de la base
				PreparedStatement ptemp = (PreparedStatement) connect.prepareStatement(temp);
				ptemp.setString(1, (String) Cchoix.getSelectedItem());
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
	
			String LotAss = JOptionPane.showInputDialog(null,"Indiquer N° lot produit associé (Resine ou poudre si bicomposant) sinon indiqué : 0 ");
			//creation ligne dans la BASE EM
			  try {
				  connect = connexion.getConnection(); // fait appel a la classe connexion
					String EM_Ajout = "INSERT into TABLE_EM (ID_PF,PF,METHODE,Q_RES,Q_EAU,Q_POUDRE,RES,D_COUL,D_FAB,N_LOT_C,STATUT,D_CASSE)"
							+ "  VALUES (?,?,?,?,?,?,?,?,?,?,?, DATE_ADD(?, INTERVAL 28 DAY))  "; // 12

					PreparedStatement psEM = (PreparedStatement) connect.prepareStatement(EM_Ajout);

					// champs info

					psEM.setString(1, no_id);
					psEM.setString(2, (String) Cchoix.getSelectedItem());
					psEM.setString(3, Methode);
					psEM.setString(4, Q_res);
					psEM.setString(5, Q_eau);
					psEM.setString(6, Q_poudre);
					psEM.setString(7, Tres.getText());
					psEM.setString(8, Tdate1.getText());
					psEM.setString(9, Tdate2.getText());
					psEM.setString(10, LotAss);
					psEM.setString(11, "COOL");
					psEM.setString(12, Tdate1.getText()); // permet d'avoir la date a 28 jours

					psEM.executeUpdate();
					psEM.close();
				 JOptionPane.showMessageDialog(null, "Des eprouvettes ont été réalisé. La ligne a été ajouté à la base EM");	
			  } catch (SQLException e3) {
				// TODO Auto-generated catch block
					e3.printStackTrace();
			  }
}

protected void RecupDernierLot() {
	String dernierlot = null;
	String prochainlot =null;
	//recuper du du dernier lot 
	try {
		  connect= connexion.getConnection();
		  String Nlot = "SELECT MAX(N_LOT) AS LOT FROM TABLE_PF WHERE NOM=? ";
		  PreparedStatement pNlot = (PreparedStatement) connect.prepareStatement(Nlot);
		  pNlot.setString(1, (String) Cchoix.getSelectedItem());
		  ResultSet rNlot = pNlot.executeQuery(); 
		  while(rNlot.next()) {
			 dernierlot = rNlot.getString("LOT");
			}
	    } catch (Exception e) {
		  JOptionPane.showMessageDialog(null, e);
	  }
	  
	//prochain lot
	try {
		  connect= connexion.getConnection();
		  String Nlot = "SELECT MAX(N_LOT)+1 AS PLOT FROM TABLE_PF WHERE NOM=? ";
		  PreparedStatement pNlot = (PreparedStatement) connect.prepareStatement(Nlot);
		  pNlot.setString(1, (String) Cchoix.getSelectedItem());
		  ResultSet rNlot = pNlot.executeQuery(); 
		  while(rNlot.next()) {
			 prochainlot = rNlot.getString("PLOT");
			}
	    } catch (Exception e) {
		  JOptionPane.showMessageDialog(null, e);
	  }
      
	//attribution du lot en fonction du resultat de la conformite

	switch (Tconf.getText()) {
	case "CONFORME":
		if(dernierlot!=null && Tres.getText().length() ==0) {
			Tlot1.setText(prochainlot);
		    b_transfert.setEnabled(true);
		} else if(Tres.getText().length()==0){
			Tlot1.setText("1");
			  b_transfert.setEnabled(true);
		}	else {
			   Tlot1.setText(Tres.getText());
		     }
		break;
	case "CONFORME SOUS DEROG":
		if(dernierlot!=null && Tres.getText().length() ==0) {	
		Tlot1.setText(prochainlot);
		b_transfert.setEnabled(true);}
		else if(Tres.getText().length()==0){
			Tlot1.setText("1");
			  b_transfert.setEnabled(true);
		} else {
			 Tlot1.setText(Tres.getText());
		}
		
		break;
	case "NC":
		if(Tnc1.getText()!=null) {
			if(dernierlot != null) {
			Tlot1.setText(dernierlot);
			Llot1.setText("*** BASE ***");
			b_transfert.setEnabled(true);
			}
			else {
				Tlot1.setText("0"); /// si 1ere controle non conforme et pas de lot enregistre
				Llot1.setText("*** BASE ***");
				b_transfert.setEnabled(true);
			}
		}
		break;
	default:
		break;
	}	
}	


} //fin de classe primaire
