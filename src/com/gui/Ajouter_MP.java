package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Image;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;

//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
//import javax.swing.ImageIcon;
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

import com.mysql.jdbc.PreparedStatement;
//import com.mysql.jdbc.Statement;

import connexion.BD.connexion;




public class Ajouter_MP extends Habillage{

	//declaration des variables
	
	        private static Connection connect; //creer varaible connect
	        
			JFrame fen_MP;
			Image img_MP;
			private JPanel panel_MP,paneE, paneC, paneO, paneS, paneBox1, paneBox2, paneS1,paneS2;
			
			private JButton b_resultat, b2_resultat,b_encours, b_transfert, b_fiche;
	        private JLabel Lchoix, Ltrame, Lfreq, LQt, Lmod, Lpre,Lres, Lpar, Lcum, Lt, Lnc,Lderog, Llot1, Lnc1, Lderog1, Lconf;
	        private JLabel Lresp, Ldate1, Lq, Lobs,LlotAnnee;
	        //private JLabel check_label,check2_label,MEA_label,valid_label,recup_label;
	        private JTextField Tresp, Tdate1,TQ, Tobs;
	        private JTextField Ttrame, Tfreq, TQt, Tmod, Tpar, Tres,Tt, Tnc, Tderog, Tlot1,Tnc1,Tderog1,Tconf;
	        private JTextArea Tpre;
	        private JComboBox<String> Cchoix, CRes1;
	        private JTable result_tab;
		    private DefaultTableModel model_res;
		    private Object[] result_tabRow;
		    private int Qnc =0 ; //pour nombre de non conformite
		    private int NB_DEROG=0 ; // pour nombre de derog
		    private String getVersion; //recupere version de la trame mp
		    
		    // var
		    int countNC, countDATA,CountNbNC;
		    
		public Ajouter_MP() {
			 fen_MP();
			 ListeNomMP();
		}
		
		
		
		private void fen_MP() {
			fen_MP = new JFrame("CONTROLE : Nouvel Matiere Premiere");	
			fen_MP.setSize(definition.SLarge,definition.SHaut);
			fen_MP.setLocationRelativeTo(null);
	        fen_MP.setResizable(false);
	       
	      //definition des panels 
	         //panel principal
	        panel_MP = new ControlPanel();
	        panel_MP.setLayout(new BorderLayout());
	         
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
	     
	        //ajout panel secondaire au primaire
	        paneS.add(paneS1);
	        paneS.add(paneS2);
	        
        
	        //definition du paneau central avec 2 verticalements
	        paneBox1 = new JPanel(new GridBagLayout());
	        paneBox2 = new JPanel(new GridBagLayout());
	        paneC.add(paneBox1);
	        paneC.add(paneBox2);
	         
	         
	        //ajout des paneau au celui princiapel
	        panel_MP.add(paneC, BorderLayout.CENTER);
	        panel_MP.add(paneE, BorderLayout.EAST);
	        panel_MP.add(paneO,BorderLayout.WEST);
	        panel_MP.add(paneS, BorderLayout.SOUTH);
	        
	        //initialisation des vues de chaque panel
	        view_MATIERE();
	        view_STAT();
	        view_CONFORMITE();
	        view_ESSAI();
	        view_TAB();
	        view_VALID();
	        
	        //transparence de ts les panel
	        paneC.setOpaque(false);
	         paneE.setOpaque(false);
	         paneS.setOpaque(false);
	         paneS1.setOpaque(false);
	         paneS2.setOpaque(false);
	         paneO.setOpaque(false);
	        paneBox1.setOpaque(false);
	         paneBox2.setOpaque(false);
	         
	         //code temporaire => a effacer
	         panel_MP.addMouseListener(new myMouseListener());
	         
	        fen_MP.getContentPane().add(panel_MP);
	        fen_MP.setVisible(true);   
		}
		
		private void view_MATIERE() {
			/*paneOBorder = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),"MATIERE PREMIERE", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("Time new Roman",Font.BOLD,14));
			paneO.setBorder(paneOBorder);*/
			paneO.setPreferredSize(new Dimension(400,0));
			
			GridBagConstraints g1 = new GridBagConstraints();
			GridBagConstraints g2 = new GridBagConstraints();
			
			//def et position colonne 1
			Lchoix = new JLabel("CHOIX DE LA MP :");
			Ltrame = new JLabel("TYPE DE TRAME :");
			Lfreq  = new JLabel("FREQUENCE :");
			LQt = new JLabel("QTITE A PRELEVER : ");
			Lmod = new JLabel("MODOP ET NORMES : ");
			Lpre = new JLabel("PREPARATION : ");
	 		Lpar = new JLabel("PARTICULARITE : ");
	 		
	 		
	 		
	 		g1.gridx =0;
			g1.gridy =0;
			g1.anchor = GridBagConstraints.FIRST_LINE_END;
			g1.insets = new Insets(10,10,0,0);
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
			Ttrame = new JTextField(8);
			Ttrame.setEditable(false);
			Tfreq = new JTextField(10);
			Tfreq.setEditable(false);
			TQt = new JTextField(5);
			TQt.setEditable(false);
			Tmod = new JTextField(5);
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
			g2.insets = new Insets(10,5,0,10);
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
			
			Cchoix.addItemListener(new AppelTrame());
		}	
		
		private void view_STAT() {
		/*	paneBox1Border = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),"STATS PRODUIT", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("Time new Roman",Font.BOLD,14));
			paneBox1.setBorder(paneBox1Border);*/
			
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
			Tlot1 = new JTextField(8);
			Tlot1.setEditable(false);		
			Tnc1 = new JTextField(4);
			Tderog1 = new JTextField(4);
			Tconf = new JTextField(12);
			Tconf.setEditable(false);
			
			gbc2.gridx =1;
			gbc2.gridy =0;
			gbc2.anchor = GridBagConstraints.FIRST_LINE_START;
			gbc2.insets = new Insets(10,0,0,0);
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
			gbc2.gridwidth =1;
			
			gbc2.anchor = GridBagConstraints.FIRST_LINE_START;
			gbc2.insets = new Insets(10,3,0,0);
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
				} else {
					Tlot1.setText(Tres.getText());
					Tconf.setText("CONFORME SOUS DEROG");
				}
			  }
			});
}

private void view_ESSAI() {
		
			paneE.setPreferredSize(new Dimension(300,0));
			
			GridBagConstraints gbc1 = new GridBagConstraints();
			GridBagConstraints gbc2 = new GridBagConstraints();
			
			//def et placement col 1
			Lresp = new JLabel("RESPONSABLE ESSAI : ");
			Ldate1 = new JLabel("DATE CONTROLE : ");
			Lq = new JLabel();
			Lobs = new JLabel("OBSERVATION : ");
			Lres = new JLabel("RESERVATION LOT : ");
			
			gbc1.gridx =0;
			gbc1.gridy =0;
			gbc1.anchor = GridBagConstraints.FIRST_LINE_END;
			paneE.add(Lresp, gbc1);
			gbc1.gridy =1;
			gbc1.insets = new Insets(15,0,0,0);
			paneE.add(Ldate1, gbc1);
			gbc1.gridy=2;
			paneE.add(Lq, gbc1);
			gbc1.gridy=3;
			paneE.add(Lobs, gbc1);
			gbc1.gridy=4;
			paneE.add(Lres, gbc1);
			
			//def et placemnt col2
			Tresp = new JTextField(8);
			Tdate1 = new JTextField(8);
			Tdate1.setText("YYYY-MM-DD");
			TQ = new JTextField(8);
			Tobs = new JTextField(8);
			Tres = new JTextField(5);
			
			gbc2.gridx =1;
			gbc2.gridy =0;
			gbc2.anchor = GridBagConstraints.FIRST_LINE_START;
			paneE.add(Tresp, gbc2);
			gbc2.gridy =1;
			gbc2.insets = new Insets(15,0,0,0);
			paneE.add(Tdate1, gbc2);
			gbc2.gridy =2;
			paneE.add(TQ, gbc2);
			gbc2.gridy =3;
			paneE.add(Tobs, gbc2);
			gbc2.gridy =4;
			paneE.add(Tres, gbc2);
				 
		}
		
private void view_TAB() {
		
			paneS1.setPreferredSize(new Dimension(600,300));
			
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
				paneS1.add(scp);
				result_tabRow = new Object[7];				
		}
		
private void view_VALID() {
		
		    paneS2.setPreferredSize(new Dimension(0,200));
		    //paneS2Border.setTitleColor(Color.BLACK);
		    
 //DEFINITION BOUTON
		    b_resultat = new JButton("CONTROLE");
		    b2_resultat = new JButton("CONTRE ESSAI");
		    b_encours = new JButton("MISE EN ATTENTE");
		    b_fiche = new JButton("DONNEES ESSAI");
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
		
//***********************************************************		
//*************CLASS BOUTON************************************
//************************************************************		
public class AffRes implements ActionListener {
			public void actionPerformed(ActionEvent e) {	 
			
	// Regle pour les conditions de CONFORMITE pour les controle	  
    // Evaluer 1ere resulats avec les lignes sur "donnee a saisir"
	 			  
	 for(int li=0; li<result_tab.getRowCount(); li++) { 	
		 String st = String.valueOf(result_tab.getValueAt(li,5)); //colonne statut
		 String colmin = String.valueOf(result_tab.getValueAt(li,3)); // colonne MIN
		 if (st.equals("donnée a saisir")) {
			  if(isNumeric(colmin)) {
				  if (String.valueOf(result_tab.getValueAt(li,2)).equals("Appareil HS")) {
					  model_res.setValueAt("Faire controle de secours", li,5);
		    		    model_res.setValueAt("donnée a saisir",li+1,5);
				     } else if (Float.parseFloat(colmin)<=Float.parseFloat(result_tab.getValueAt(li, 2).toString()) && Float.parseFloat(result_tab.getValueAt(li, 2).toString())<=Float.parseFloat(result_tab.getValueAt(li, 4).toString())){
				    	 model_res.setValueAt("CONFORME", li,5);
				               }
			                    else {
	        	                   model_res.setValueAt("NC", li,5);
			                    } 
		         }  else {
		        	  if(colmin.equals(model_res.getValueAt(li, 2).toString())) {
		         
		        	          model_res.setValueAt("CONFORME", li,5);
		                  } else {
		                	  model_res.setValueAt("NC", li,5);
		                  }
		}}}
	 
	 //boucle pour evaluer nb de donnee a saisir
	 int countDATA =0;
	 for(int cda=0; cda<result_tab.getRowCount(); cda++) { 
		 if(String.valueOf(result_tab.getValueAt(cda,5)).equals("donnée a saisir")) {
			 countDATA++;
		 }}
	 
	 
	  // boucle pour evaluer nb NC dans STATUT
	 countNC =0;
	 for(int li=0; li<result_tab.getRowCount(); li++) { 
		 if(String.valueOf(result_tab.getValueAt(li,5)).equals("NC")) {
			 countNC++;
		 }}
	 
	 //recuperer lignes  "sans object" pour pouvoir les afficher dans STATUS2ND si NC
	 ArrayList<Integer> NO_ligne = new ArrayList<Integer>() ;
	 for (int so=0; so<result_tab.getRowCount(); so++) {
		 if(String.valueOf(result_tab.getValueAt(so,5)).equals("sans object")) {
			  NO_ligne.add(so);
		 }
	 }
	
	//indiquer Statut essai dans STATUT2ND
	  if(countNC>0) {
		  b2_resultat.setEnabled(true);
		  b_resultat.setEnabled(false);
		   if(NO_ligne.size()>0) {
		  
		        for (int li2=0; li2<result_tab.getRowCount(); li2++) {
			        for(int i=0; i<NO_ligne.size();i++) {
			           if(li2 == NO_ligne.get(i) ) {
			        	model_res.setValueAt("sans object", li2,7);
			            }else {
		    	        model_res.setValueAt("donnée a saisir", li2,7);
		                }
	                 }
	             }	     
	          }
		   else {
			   for (int li2=0; li2<result_tab.getRowCount(); li2++) {
				   model_res.setValueAt("donnée a saisir", li2,7);
			   }
		    }
	      } else {
	    	  Tconf.setText("CONFORME");
	     }

	//Debloquer le bouton valider et forcer n° de lot
		if (Tconf.getText().equals("CONFORME") && countNC ==0 && countDATA ==0 && Tres.getText().isEmpty() ) {
			b_transfert.setEnabled(true);
			 RecupDernierLot();
		}  	 else if (Tres.getText().isEmpty() == false && Tconf.getText().equals("CONFORME")) {
			Tlot1.setText(Tres.getText());
			b_transfert.setEnabled(true);
		}
	
	
 }}

public class AffRes2 implements ActionListener { 
	public void actionPerformed(ActionEvent e) {	
			
		
				  // Evaluer 2ere resulats avec les lignes sur "donnee a saisir"
	
			 for(int li2=0; li2<result_tab.getRowCount(); li2++) { 	
						 String st2 = String.valueOf(result_tab.getValueAt(li2,7)); //colonne statut2
						 String colmin2 = String.valueOf(result_tab.getValueAt(li2,3)); // colonne MIN
						
						 if (st2.equals("donnée a saisir")) {
							  if(isNumeric(colmin2)) {
								  if (String.valueOf(result_tab.getValueAt(li2,6)).equals("Appareil HS")) {
									  model_res.setValueAt("Faire controle de secours", li2,7);
						    		    model_res.setValueAt("donnée a saisir",li2+1,7);
								     } else { 
								    	 if (Float.parseFloat(colmin2)<=Float.parseFloat(result_tab.getValueAt(li2, 6).toString()) && Float.parseFloat(result_tab.getValueAt(li2, 6).toString())<=Float.parseFloat(result_tab.getValueAt(li2, 4).toString())){
								    	 model_res.setValueAt("CONFORME", li2,7);
								               }
							                    else {
					        	                   model_res.setValueAt("NC", li2,7);
							                    } 
						      }} else {
						        if(colmin2.equals(model_res.getValueAt(li2, 6).toString())) {
						        	     model_res.setValueAt("CONFORME", li2,7);
						               } else {
						                  model_res.setValueAt("NC", li2,7);
						                  }
						}}
				//REMPLIR CASE N°NC (et DEROG)
						 Tlot1.setText("Saisir N° de NC"); 
						 
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
		
		//
			 
			
	}
	
}
		
		    
public class EnCours implements ActionListener { 
			  public void actionPerformed(ActionEvent e) {	 
		
  if(Tres.getText().isEmpty()) {
	 JOptionPane.showMessageDialog(null, "Renseigner la case N° de LOT Reservation pour enregistrement ulterieur");
  }
  else {
	  
		// AJOUT DANS LA TABLE MP INTERMEDIARE LES RESULTAT EN ATTENTE DE TRANSFERT DANS LA BASE MP		  
				  // 1 **** Transfert de la fiche vers la base mp_int
				  try {		 
					  int c_r=13;
					  
					   connect= connexion.getConnection(); //fait appel a la classe connexion
					    	String StAjout ="INSERT into TABLE_MP_INT "
			   		
					   
                                           +   " VALUES (?,?,?,?,?,?,?,?,?,?,?,?" //12
                                                    +",?,?,?,?,?,?,?,?,?,?" //10
                                                    +",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; //20					
						PreparedStatement psA = (PreparedStatement) connect.prepareStatement(StAjout);		
						
						//champs info
						psA.setString(1,null);
						psA.setString(2, (String) Cchoix.getSelectedItem());
						psA.setString(3, Tconf.getText());
						psA.setString(4,Tres.getText());
						psA.setString(5,Tnc1.getText());
						psA.setInt(6,Qnc);
						psA.setString(7,Tderog1.getText());
						psA.setString(8,Tdate1.getText());
						psA.setString(9,TQ.getText());
						psA.setString(10,Tresp.getText());
						psA.setString(11, Tobs.getText());
						psA.setString(12, Calcul.Complete(model_res,result_tab, Tresp, Tdate1,TQ));
						       //Ajout valeur tableau à la base en 
						
							for (int r1 = 0; r1 <result_tab.getRowCount(); r1++) {
								 psA.setString(c_r,(String) result_tab.getValueAt(r1, 2)); // ajout a R(x)
								 c_r ++;  
							}
	
							// pour completer BDD non utilisé
							   int somme = result_tab.getRowCount()+13; //calcul du nombre de ligne bdd utilisé
							   int ligne;
							 
								if (c_r<43) {
								  	for (ligne=somme; ligne <43; ligne++) {	
								  		psA.setString(ligne,null); 		
								  	}}
				 //Ajout Resultat 2nd controle si existe
								int  c_r2=28;
								if(b2_resultat.isEnabled())	{			
										for(int r2=0; r2 <result_tab.getRowCount();r2++) {
											psA.setString(c_r2, (String) result_tab.getValueAt(r2, 6));
											c_r2++;
											
										}
								   }	
			
						psA.executeUpdate();psA.close();						
						JOptionPane.showMessageDialog(null,"Le Controle de la  Matiere Premiere : "+Cchoix.getSelectedItem()+" a été mise en attente de validation.");
						fen_MP.dispose();
					   
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
						 String info = "SELECT * FROM TABLE_MP_INT WHERE ID = ?"; 
						 PreparedStatement pinfo = (PreparedStatement) connect.prepareStatement(info);
						 pinfo.setInt(1, prime.recup_id);
						 ResultSet rs_info = pinfo.executeQuery(); // resultat de la selection
						 
						  while(rs_info.next()) {
								  Cchoix.setSelectedItem(rs_info.getString("NOM"));
								  Tdate1.setText(rs_info.getString("DATE_C"));
								  Tnc1.setText(rs_info.getString("FICHE_NC"));
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
					        int res2=28;
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
						 String info = "DELETE FROM TABLE_MP_INT WHERE ID = ?"; 
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
				  
			/*	  if (Tconf.getText().equals("CONFORME") || Tconf.getText().equals("CONFORME SOUS DEROG") && Tres.getText()== "") {
						 Tlot1.setText(Tres.getText());
						System.out.println( "Le numero a été forcé"+Tres.getText());
						 
					 }	*/ 
				  
				  
				  
	 //Regle de Validation du controle
		 if ( CountNbNC>0 && Tconf.getText().equals("CONFORME") || Tnc1.getText().isEmpty() && CountNbNC>0 || Tlot1.getText().equals("Saisir N° NC")) {
					  JOptionPane.showMessageDialog(null, "Validité  Controle Incorrect. \n CAUSE :  2ND STATUT : NC & CONFORMITE : CONFORME OU SOUS DEROG","Erreur - Incoherence",JOptionPane.ERROR_MESSAGE);
				  }
		    else {
		    	//si donnes manquante
		    	 if(Tresp.getText().isEmpty() || Tdate1.getText().equals("YYYY-MM-DD")||   TQ.getText().isEmpty()) {
		    		  JOptionPane.showMessageDialog(null, "Donnée(s) manquante(s) ou non valide(s)","Fiche Incomplete",JOptionPane.ERROR_MESSAGE);
		    	 }else {
				  //1 *** Recupere le numero de version de la trame MP pourl'associer ensuite dans la requette suivante
				  try {
					  connect= connexion.getConnection(); //fait appel a la classe connexion
					  String St_Ver = "SELECT (TRAME_VER)  FROM TABLE_TMP  WHERE NOM = ? AND ID = (SELECT MAX(ID) FROM TABLE_TMP WHERE NOM= ?)"; 
	     				PreparedStatement pps = (PreparedStatement) connect.prepareStatement(St_Ver);
	     				pps.setString(1, (String) Cchoix.getSelectedItem());
	     				pps.setString(2, (String) Cchoix.getSelectedItem());
	     				
	     				ResultSet rs_ver = pps.executeQuery(); // resultat de la selection
	     				  while(rs_ver.next()) {
	     					  getVersion = rs_ver.getString("TRAME_VER"); }	  
	     				 rs_ver.close();  pps.close();	     						     				
				   } catch  (Exception e1) {
						// TODO Auto-generated catch block
						  e1.printStackTrace();
					   }
			  
				  
				  // 2 **** Transfert de la fiche vers la base mp
				  try {
						
					  int c_t=19; 
					  int c_c=20;
					  int c_mi=21;
					  int c_Ma=22;
					  int c_r=23;
					  
					   connect= connexion.getConnection(); //fait appel a la classe connexion

					    	String StAjout ="INSERT into TABLE_MP "
			   		
					   
                                           +   " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" //18
                                                    +",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" //25
                                                    +",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" //25
                                                    +",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" //25
                                                    +",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; //30
	
						
						PreparedStatement psA = (PreparedStatement) connect.prepareStatement(StAjout);
						// (a) champs info	
						
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
						psA.setString(11,Tlot1.getText());
						psA.setString(12,Tnc1.getText());
						psA.setInt(13,Qnc);
						
						psA.setInt(14,NB_DEROG);
						psA.setString(15,Tdate1.getText());
						psA.setString(16,TQ.getText());
						psA.setString(17,Tresp.getText());
						psA.setString(18, Tobs.getText());
						
					
						  // (b) Ajout valeur (1er resultat) tableau à la base en 
						
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
							
							// (c)pour completer BDD non utilisé
							   int somme = result_tab.getRowCount()*5+19; //calcul du nombre de ligne bdd utilisé
							   int ligne;
							 
								if (c_t<124) {
								  	for (ligne=somme; ligne <124; ligne++) {	
								  		psA.setString(ligne,null); 		
								  	}}
				
								//(d) Ajout valeur (2er resultat) tableau à la base MP => pour revalider les donnée
								int base_ligne = 94; //a partir de la ligne 94 de la BDD : RS1 
								for (int l = 0; l <result_tab.getRowCount(); l++) {		
									 psA.setString(base_ligne,(String) result_tab.getValueAt(l, 6)); // ajout a R(x)
									 base_ligne++;	
								}
								
							// (e) Ajout valeur Resultat 1 (R) ou Resultat 2 (RS) dans Result final (RF) en fonction presence (RS)
							int base_ligne2 = 109;
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
						psA.executeUpdate();
						psA.close();	
						JOptionPane.showMessageDialog(null,"Le Controle de la  Matiere Premiere : "+Cchoix.getSelectedItem()+" a été ajoutée à la base.");
						fen_MP.dispose();
						
					   
				  } catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					   }
		    }
			  
		    }}
			 
}
		
public class AppelTrame implements ItemListener {
			@Override
			public void itemStateChanged(ItemEvent arg) {
				// TODO Auto-generated method stub
				
			//personalisation afficahge
				model_res.setRowCount(0); 
				Tconf.setText("");
				Tnc1.setText("");
				Tlot1.setText("");
			// Lors du changement intituler du COMBOBOX FAIT :	 
	          if(arg.getStateChange() == ItemEvent.SELECTED){
	        	  
					
					//1 - appel la trame et rempli la fiche de controle
	                 try {
	                	 connect= connexion.getConnection(); //initialisation connection
	     				
	     				                // selecton type de donne de la base en fonction du derniere trame effectuée
	                	String sql = "SELECT * FROM TABLE_TMP  WHERE NOM = ? AND ID = (SELECT MAX(ID) FROM TABLE_TMP WHERE NOM= ?)"; 
	     				PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
	     				
	     				pState.setString(1, (String) Cchoix.getSelectedItem());
	     				pState.setString(2, (String) Cchoix.getSelectedItem());
	     				ResultSet rs = pState.executeQuery(); // resultat de la selection
	     				
	     				//ajout element 1 a 1 à la fiche de controle
	     				while(rs.next()) {
	     				    int count;
	     					//ajout element donne
	     					Ttrame.setText(rs.getString(2));
	     					Tfreq.setText(rs.getString("FREQUENCE"));
	     					TQt.setText(rs.getString("QTITE_PREL"));
	     					Tmod.setText(rs.getString("MODOP"));
	     					Tpre.setText(rs.getString("PREPARATION"));
	     					Tpar.setText(rs.getString("PARTICULARITE"));
	     					Lq.setText(rs.getString(12));
	     					
	     					//ajout element dans tableau
	     					for (count=13; count<70; count=count+4) {
	    	   					result_tabRow[0] = rs.getString(count);
	    	   					result_tabRow[1] = rs.getString(count+1);
	    	   					result_tabRow[3] = rs.getString(count+2);
	    	   					result_tabRow[4] = rs.getString(count+3);
	    	   					model_res.addRow(result_tabRow);}
	     				}
	     				
	     				//ferme les connexions
	     				rs.close(); pState.close(); 
	     				
	                    prime.CentrerTableau(2, result_tab);
	                      } catch (Exception e) {
	                	       e.printStackTrace();
	                        }} 
	          
	             ListeComboxTrame(); // Appel methode pour charger la liste de choix en fonction du type de trame
	          
				// 2 - remplir colonne STATUT
				 for (int j =0; j<result_tab.getRowCount(); j++) {
				        
	        		 if (result_tab.getValueAt(j, 0).toString().equals("O")) {
	        			 model_res.setValueAt("donnée a saisir", j,5);		
	        		     }
	        		 if (result_tab.getValueAt(j, 0).toString().equals("S")) {
	        			 model_res.setValueAt("sans object", j,5);		
	        		   } }			   
				
				 //3 - redefini la dimension du tableau a afficher
				  int count =0;
				                   // compte nb de ligne non null
				      for(int ligne =0; ligne<result_tab.getRowCount();ligne++) {
					         if(result_tab.getValueAt(ligne, 4) != null)
						              count++;}	 
				            
				                  //suprime les lignes null
				       for(int col =result_tab.getRowCount()-1 ; col >= count; col--) {
					  model_res.removeRow(col);}
				           	
				       
				//4 - calcul Encadre STATS
				       // cumul Tonnage
				      try {
				    	  connect= connexion.getConnection(); //initialisation connection
				    	  String St2 = "SELECT SUM(QTITE) AS CUMUL_ANNEE FROM TABLE_MP  WHERE NOM = ? AND CONFORMITE <> ?"; 
				    	  PreparedStatement pSt =  (PreparedStatement) connect.prepareStatement(St2);
				    	  pSt.setString(1, (String) Cchoix.getSelectedItem());
				    	  pSt.setString(2, "NC");
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
				    	  String St3 = "SELECT SUM(Q_NC) AS Q_NC_CUMUL FROM TABLE_MP  WHERE NOM = ? "; 
				    	  PreparedStatement pSt3 =  (PreparedStatement) connect.prepareStatement(St3);
				    	  pSt3.setString(1, (String) Cchoix.getSelectedItem());
				    	  ResultSet rs3 = pSt3.executeQuery(); // resultat de la selection
				    	  while(rs3.next()) {
				    		  Tnc.setText(rs3.getString("Q_NC_CUMUL"));
				    	  }
				    	  rs3.close();  pSt3.close(); 
		     			
				          } catch (Exception e3) { e3.printStackTrace(); }	
                          

			// cumul Derog
				      try {
				    	  connect= connexion.getConnection(); //initialisation connection
				    	  String St4 = "SELECT SUM(DEROG) AS DEROG_CUMUL FROM TABLE_MP  WHERE NOM = ? "; 
				    	  PreparedStatement pSt4 =  (PreparedStatement) connect.prepareStatement(St4);
				    	  pSt4.setString(1, (String) Cchoix.getSelectedItem());
				    	  ResultSet rs4 = pSt4.executeQuery(); // resultat de la selection
				    	  while(rs4.next()) {
				    		  Tderog.setText(rs4.getString("DEROG_CUMUL"));
				    	  }
				    	  rs4.close(); pSt4.close(); 
		     			 
				          } catch (Exception e4) {e4.printStackTrace();
                      }
				      Tpre.setToolTipText(Tpre.getText()); //affichage complet de l'encadrer
				      
	}}
		
		//*************Diverses Fonctions***********
private void ListeNomMP() {
			
			try {
				 Cchoix.removeAllItems(); //supprime element residuel
				connect= connexion.getConnection(); //initialisation connection
				
				String sql = "SELECT DISTINCT NOM FROM TABLE_TMP ORDER BY NOM "; // selecton type de donne de la base
				
				PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
				
				ResultSet rs = pState.executeQuery(); // resultat de la selection
				//ajout element 1 a 1
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
		


private void ListeComboxTrame() {

	//determination du type trame : CAPPILAIRE OU CLASSIQUE
	
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
	
	
	case "CLASSIQUE":
		
		// affectation de la liste cappilaire au combobox
				try {
					 CRes1.removeAllItems(); //supprime element residuel
					connect= connexion.getConnection(); //initialisation connection
					
					String sql = "SELECT CLASSIQUE FROM CHOIX WHERE CLASSIQUE IS NOT NULL ORDER BY CLASSIQUE "; // selecton type de donne de la base
					
					PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
					
					ResultSet rs = pState.executeQuery(); // resultat de la selection
					//ajout element 1 a 1
					while(rs.next()) {
						CRes1.addItem(rs.getString("CLASSIQUE"));
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


public boolean isNumeric(String s) {  
    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}  


protected void RecupDernierLot() {
	String dernierlot = null;
	String prochainlot =null;
	//recuper du du dernier lot 
	try {
		  connect= connexion.getConnection();
		  String Nlot = "SELECT MAX(N_LOT) AS LOT FROM TABLE_MP WHERE NOM=? ";
		  PreparedStatement pNlot = (PreparedStatement) connect.prepareStatement(Nlot);
		  pNlot.setString(1, (String) Cchoix.getSelectedItem());
		  ResultSet rNlot = pNlot.executeQuery(); 
		  while(rNlot.next()) {
			 dernierlot = rNlot.getString("LOT");
			}
	    } catch (Exception e) {
		  JOptionPane.showMessageDialog(null, e);
	  }
	  
	//prochian lot
	try {
		  connect= connexion.getConnection();
		  String Nlot = "SELECT MAX(N_LOT)+1 AS PLOT FROM TABLE_MP WHERE NOM=? ";
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
		if(dernierlot!=null) {
			Tlot1.setText(prochainlot);
		    b_transfert.setEnabled(true);
		} else {
			Tlot1.setText("1");
			  b_transfert.setEnabled(true);
		}	
		break;
	case "CONFORME SOUS DEROG":
		if(dernierlot!=null) {	
		Tlot1.setText(prochainlot);
		b_transfert.setEnabled(true);}
		else {
			Tlot1.setText("1");
			  b_transfert.setEnabled(true);
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



} //fin de classe
	
	
	

