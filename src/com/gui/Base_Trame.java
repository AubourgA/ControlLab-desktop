package com.gui;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.jdbc.PreparedStatement;

import connexion.BD.connexion;



public class Base_Trame extends Habillage {

	//declaration des variables
	
	private static Connection connect; //creer varaible connect
	
	JFrame fen_Btrame;
	Image img_Btrame;
	private JPanel panel_Btrame, p_MP, p_PF, ptitre, p1_centre,p1_sud, p2_centre,p2_sud;
	private JLabel  lab_text,lab_text2,arch_lab;
	private JTextField t_text,t_text2;
	private JButton ArchMP, ArchPF;
	private JTabbedPane panelOnglet;
	private JTable tab_TrameMP, tab_TramePF;
	private DefaultTableModel model_trameMP,model_tramePF;
	

	
public Base_Trame() {
	fen_BaseTrame();
}
	



private void fen_BaseTrame() {
	//creation frame principal
	fen_Btrame = new JFrame("BASE DES TRAMES MP & PF");
	
	//definition de la frame
	fen_Btrame.setSize(definition.SLarge,definition.SHaut);
	fen_Btrame.setLocationRelativeTo(null);

    fen_Btrame.setResizable(false);
    
    panel_Btrame = new TitreTramePanel(); //panel principale
    panel_Btrame.setLayout(new BorderLayout());
    
    //creation panel
    ptitre = new JPanel(); //panel NORTH
    ptitre.setOpaque(false);
    
    panelOnglet = new JTabbedPane(); //creation des onglets
    p_MP = new  BasePanel();// creation des panel des onglets
    p_PF = new BasePanel();
    p_MP.setLayout(new BorderLayout());
    p_PF.setLayout(new BorderLayout());
    
    panelOnglet.addTab("Base Trame MP",null,p_MP); //ajout panel au onglet
    panelOnglet.addTab("Base Trame PF",null,p_PF);
    
    panel_Btrame.add(ptitre, BorderLayout.NORTH);
    panel_Btrame.add(panelOnglet, BorderLayout.CENTER);
   
    //affichage ensemble panel
    titre();
    onglet_trameMP();
    onglet_tramePF();
    
    //affichage fenetre
    fen_Btrame.getContentPane().add(panel_Btrame);
    fen_Btrame.setVisible(true);
}


private void titre() {
	 ptitre.setPreferredSize(new Dimension(0,40));
	
}

private void onglet_trameMP() {
	//creation panel et affectation
			p1_centre = new JPanel();
			p1_sud = new JPanel(new FlowLayout());
			p_MP.add(p1_centre, BorderLayout.CENTER);
			p_MP.add(p1_sud, BorderLayout.SOUTH);
			
			p1_centre.setOpaque(false);
			p1_sud.setOpaque(false);
			
			//creation composant
			lab_text = new JLabel("Recherche d'une trame dans la base : ");
			t_text = new JTextField(20);
			ArchMP = new JButton();
			
			
			
			//icon bouton
			 ImageIcon arch_icon = new ImageIcon(getClass().getResource(definition.URL_ARCHIVER));
			 arch_lab = new JLabel("ARCHIVER TRAME",arch_icon,SwingConstants.CENTER);
			 arch_lab.setVerticalTextPosition(JLabel.BOTTOM);
			 arch_lab.setHorizontalTextPosition(JLabel.CENTER);
			 ArchMP.setPreferredSize(new Dimension(140,60));
			 ArchMP.add(arch_lab);
			
			 ArchMP.addActionListener(new ActionArchive());
			 
			
			//creation table
			tab_TrameMP = new JTable();
			      // titre colonne
			Object [] col_trameMP = { "TRAME","TRAME_VER","MP","FREQ","QTITE_PREL","MODOP ET NORME","PREPARATION","PARTICULARITE","N_LOT",
					"DATE_VALIDATION","QTITE_UNIT","T1","C1","MIN1","MAX1","T2","C2","MIN2","MAX2","T3","C3","MIN3","MAX3","T4","C4","MIN4","MAX4","T5","C5","MIN5","MAX5",
					"T6","C6","MIN6","MAX6","T7","C7","MIN7","MAX7","T8","C8","MIN8","MAX8","T9","C9","MIN9","MAX9","T10","C10","MIN10","MAX10",
					"T11","C11","MIN11","MAX11","T12","C12","MIN12","MAX12","T13","C13","MIN13","MAX13","T14","C14","MIN14","MAX14","T15","C15","MIN15","MAX15"};
					
					
					
			          // model table 
			   model_trameMP = new DefaultTableModel();
			   model_trameMP.setColumnIdentifiers(col_trameMP);
			   final TableRowSorter<TableModel> sorter1 = new TableRowSorter<>(model_trameMP); 

			   
			        // caractistique table
			   tab_TrameMP.setModel(model_trameMP);
			   tab_TrameMP.setShowHorizontalLines(false);
			   tab_TrameMP.setRowSorter(sorter1);
			   tab_TrameMP.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,11));
			   tab_TrameMP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			 
			      // ajout d'un barre de defillement
			  JScrollPane s1 = new JScrollPane(tab_TrameMP);
			  
			  s1.getViewport().setOpaque(false);
			  s1.setPreferredSize(new Dimension(definition.s_Large,definition.s_Haut));
			 Object[] row_TMP = new Object[72];

			  //Insertion BDD dans la tableau
			 try {
				 connect= connexion.getConnection(); //fait appel a la classe connexion 
				 String tabletmp = "SELECT * FROM TABLE_TMP ORDER BY NOM"; 
  				 PreparedStatement pstable = (PreparedStatement) connect.prepareStatement(tabletmp);
  				ResultSet rs = pstable.executeQuery(); // resultat de la selection
  				while(rs.next()) {
  							
  					row_TMP[0] = rs.getString(2); row_TMP[11] = rs.getString(13);
  					row_TMP[1] = rs.getString(3);row_TMP[12] = rs.getString(14);
  					row_TMP[2] = rs.getString(4);row_TMP[13] = rs.getString(15);
  					row_TMP[3] = rs.getString(5);row_TMP[14] = rs.getString(16);
  					row_TMP[4] = rs.getString(6);row_TMP[15] = rs.getString(17);
  					row_TMP[5] = rs.getString(7);row_TMP[16] = rs.getString(18);
  					row_TMP[6] = rs.getString(8);row_TMP[17] = rs.getString(19);
  					row_TMP[7] = rs.getString(9); row_TMP[18] =rs.getString(20);
  					row_TMP[8] = rs.getString(10);row_TMP[19] =rs.getString(21);
  					row_TMP[9] = rs.getString(11);row_TMP[20] =rs.getString(22);
  					row_TMP[10] = rs.getString(12);row_TMP[21] =rs.getString(23);
  					
  					row_TMP[22] = rs.getString(24); row_TMP[23] = rs.getString(25);
  					row_TMP[24] = rs.getString(26);row_TMP[25] = rs.getString(27);
  					row_TMP[26] = rs.getString(28);row_TMP[27] = rs.getString(29);
  					row_TMP[28] = rs.getString(30);row_TMP[29] = rs.getString(31);
  					row_TMP[30] = rs.getString(32);row_TMP[31] = rs.getString(33);
  					row_TMP[32] = rs.getString(34);row_TMP[33] = rs.getString(35);
  					row_TMP[34] = rs.getString(36);row_TMP[35] = rs.getString(37);
  					row_TMP[36] = rs.getString(38); row_TMP[37] =rs.getString(39);
  					row_TMP[38] = rs.getString(40);row_TMP[39] =rs.getString(41);
  					row_TMP[40] = rs.getString(42);row_TMP[41] =rs.getString(43);
  					row_TMP[42] = rs.getString(44);row_TMP[43] =rs.getString(45);
  					row_TMP[44] = rs.getString(46); row_TMP[45] = rs.getString(47);
  					row_TMP[46] = rs.getString(48);row_TMP[47] = rs.getString(49);
  					row_TMP[48] = rs.getString(50);row_TMP[49] = rs.getString(51);
  					row_TMP[50] = rs.getString(52);row_TMP[51] = rs.getString(53);
  					row_TMP[52] = rs.getString(54);row_TMP[53] = rs.getString(55);
  					row_TMP[54] = rs.getString(56);row_TMP[55] = rs.getString(57);
  					row_TMP[56] = rs.getString(58);row_TMP[57] = rs.getString(59);
  					row_TMP[58] = rs.getString(60); row_TMP[59] =rs.getString(61);
  					row_TMP[60] = rs.getString(62);row_TMP[61] =rs.getString(63);
  					row_TMP[62] = rs.getString(64);row_TMP[63] =rs.getString(65);
  					row_TMP[64] = rs.getString(66);row_TMP[65] =rs.getString(67);
  					
  					row_TMP[66] = rs.getString(68);row_TMP[67] =rs.getString(69);
  					row_TMP[68] = rs.getString(70);row_TMP[69] =rs.getString(71);
  					row_TMP[70] = rs.getString(72);
  				
  					model_trameMP.addRow(row_TMP);}
  					
  				
  			//ferme les connexions		
 				rs.close();  
 				pstable.close();
				 
			 } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				 
			 }
			  
	  // pour rechercher dans le tableau => FILTRE
			 t_text.getDocument().addDocumentListener(new DocumentListener(){
				 @Override public void insertUpdate(DocumentEvent e) {
				 search(t_text.getText());

				  }
				 @Override public void removeUpdate(DocumentEvent e) {
				 search(t_text.getText());
				 }
				 @Override public void changedUpdate(DocumentEvent e) {
				 search(t_text.getText());
				 }
				 public void search(String s)
				 {
				 if (s.length() == 0) {

				  sorter1.setRowFilter(null);
				 } else {

				  sorter1.setRowFilter(RowFilter.regexFilter(s));
				 }
				 }
			 });
			 
			
			 
			//affection au panel
			p1_sud.add(lab_text);
			p1_sud.add(t_text);
			p1_sud.add(ArchMP);
			p1_centre.add(s1);
}


	

private void onglet_tramePF() {
	//creation panel et affectation
		p2_centre = new JPanel();
		p2_sud = new JPanel(new FlowLayout());
		p_PF.add(p2_centre, BorderLayout.CENTER);
		p_PF.add(p2_sud, BorderLayout.SOUTH);
		
		p2_centre.setOpaque(false);
		p2_sud.setOpaque(false);
		
		//creation composant
		lab_text2 = new JLabel("Recherche d'une trame dans la base : ");
		t_text2 = new JTextField(20);
		ArchPF = new JButton();
		
		//icon bouton
		 ImageIcon arch_icon = new ImageIcon(getClass().getResource(definition.URL_ARCHIVER));
		 arch_lab = new JLabel("ARCHIVER TRAME",arch_icon,SwingConstants.CENTER);
		 arch_lab.setVerticalTextPosition(JLabel.BOTTOM);
		 arch_lab.setHorizontalTextPosition(JLabel.CENTER);
		 ArchPF.setPreferredSize(new Dimension(140,60));
		 ArchPF.add(arch_lab);
		
		//listener sur le bouton
		ArchPF.addActionListener(new ActionArchive());
		
		//creation table
		tab_TramePF = new JTable();
		      // titre colonne
		Object [] col_tramePF = { "TRAME", "TRAME_VER","PF","FREQ","QTITE_PREL","MODOP","PREPARATION","PARTICULARITE","N_LOT","LOT_FICTIF",
				"DATE_VALIDATION","QTITE_UNIT","CATEGORIE",
				"T1","C1","MIN1","MAX1","T2","C2","MIN2","MAX2","T3","C3","MIN3","MAX3","T4","C4","MIN4","MAX4","T5","C5","MIN5","MAX5",
				"T6","C6","MIN6","MAX6","T7","C7","MIN7","MAX7","T8","C8","MIN8","MAX8","T9","C9","MIN9","MAX9","T10","C10","MIN10","MAX10",
				"T11","C11","MIN11","MAX11","T12","C12","MIN12","MAX12","T13","C13","MIN13","MAX13","T14","C14","MIN14","MAX14","T15","C15","MIN15","MAX15",
				"T16","C16","MIN16","MAX16","T17","C17","MIN17","MAX17","T18","C18","MIN18","MAX18","T19","C19","MIN19","MAX19","T20","C20","MIN120","MAX20",
				"T21","C21","MIN21","MAX21","T22","C22","MIN22","MAX22","T23","C23","MIN23","MAX23","T24","C24","MIN24","MAX24","T25","C25","MIN25","MAX25"};
				
		    
		 
		   // model table 
		   model_tramePF = new DefaultTableModel();
		   model_tramePF.setColumnIdentifiers(col_tramePF);
		   final TableRowSorter<TableModel> sorter2 = new TableRowSorter<>(model_tramePF); 
		   
		   
		   
		   // caractistique table
		   tab_TramePF.setModel(model_tramePF);
		   tab_TramePF.setShowHorizontalLines(false);
		   tab_TramePF.setRowSorter(sorter2);
		   tab_TramePF.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,11));
		   tab_TramePF.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 
		   
		      // ajout d'un barre de defillement
		  JScrollPane s2 = new JScrollPane(tab_TramePF);
		 
		  s2.getViewport().setOpaque(false);
		  s2.setPreferredSize(new Dimension(definition.s_Large,definition.s_Haut));
		  Object[] row_TPF = new Object[113];


		  
		  //Insertion BDD dans la tableau
			 try {
				 connect= connexion.getConnection(); //fait appel a la classe connexion 
				 String tabletpf = "SELECT * FROM TABLE_TPF ORDER BY NOM"; 
				 PreparedStatement pstable2 = (PreparedStatement) connect.prepareStatement(tabletpf);
				ResultSet rs = pstable2.executeQuery(); // resultat de la selection
				while(rs.next()) {
							
					row_TPF[0] = rs.getString(2); row_TPF[11] = rs.getString(13);
					row_TPF[1] = rs.getString(3);row_TPF[12] = rs.getString(14);
					row_TPF[2] = rs.getString(4);row_TPF[13] = rs.getString(15);
					row_TPF[3] = rs.getString(5);row_TPF[14] = rs.getString(16);
					row_TPF[4] = rs.getString(6);row_TPF[15] = rs.getString(17);
					row_TPF[5] = rs.getString(7);row_TPF[16] = rs.getString(18);
					row_TPF[6] = rs.getString(8);row_TPF[17] = rs.getString(19);
					row_TPF[7] = rs.getString(9); row_TPF[18] =rs.getString(20);
					row_TPF[8] = rs.getString(10);row_TPF[19] =rs.getString(21);
					row_TPF[9] = rs.getString(11);row_TPF[20] =rs.getString(22);
					row_TPF[10] = rs.getString(12);row_TPF[21] =rs.getString(23);					
					row_TPF[22] = rs.getString(24); row_TPF[23] = rs.getString(25);
					row_TPF[24] = rs.getString(26);row_TPF[25] = rs.getString(27);
					row_TPF[26] = rs.getString(28);row_TPF[27] = rs.getString(29);
					row_TPF[28] = rs.getString(30);row_TPF[29] = rs.getString(31);
					row_TPF[30] = rs.getString(32);row_TPF[31] = rs.getString(33);
					row_TPF[32] = rs.getString(34);row_TPF[33] = rs.getString(35);
					row_TPF[34] = rs.getString(36);row_TPF[35] = rs.getString(37);
					row_TPF[36] = rs.getString(38); row_TPF[37] =rs.getString(39);
					row_TPF[38] = rs.getString(40);row_TPF[39] =rs.getString(41);
					row_TPF[40] = rs.getString(42);row_TPF[41] =rs.getString(43);
					row_TPF[42] = rs.getString(44);row_TPF[43] =rs.getString(45);
					row_TPF[44] = rs.getString(46); row_TPF[45] = rs.getString(47);
					row_TPF[46] = rs.getString(48);row_TPF[47] = rs.getString(49);
					row_TPF[48] = rs.getString(50);row_TPF[49] = rs.getString(51);
					row_TPF[50] = rs.getString(52);row_TPF[51] = rs.getString(53);
					row_TPF[52] = rs.getString(54);row_TPF[53] = rs.getString(55);
					row_TPF[54] = rs.getString(56);row_TPF[55] = rs.getString(57);
					row_TPF[56] = rs.getString(58);row_TPF[57] = rs.getString(59);
					row_TPF[58] = rs.getString(60); row_TPF[59] =rs.getString(61);
					row_TPF[60] = rs.getString(62);row_TPF[61] =rs.getString(63);
					row_TPF[62] = rs.getString(64);row_TPF[63] =rs.getString(65);
					row_TPF[64] = rs.getString(66);row_TPF[65] =rs.getString(67);	
					row_TPF[66] = rs.getString(68);row_TPF[67] =rs.getString(69);
					row_TPF[68] = rs.getString(70);row_TPF[69] =rs.getString(71);
					row_TPF[70] = rs.getString(72);row_TPF[71] =rs.getString(73);
					row_TPF[72] = rs.getString(74);row_TPF[73] =rs.getString(75);
					row_TPF[74] = rs.getString(76);row_TPF[75] =rs.getString(77);
					row_TPF[76] = rs.getString(78);row_TPF[77] =rs.getString(79);
					row_TPF[78] = rs.getString(80);row_TPF[79] =rs.getString(81);
					row_TPF[80] = rs.getString(82);row_TPF[81] =rs.getString(83);
					row_TPF[82] = rs.getString(84);row_TPF[83] =rs.getString(85);	
					row_TPF[84] = rs.getString(86);row_TPF[85] =rs.getString(87);
					row_TPF[86] = rs.getString(88);row_TPF[87] =rs.getString(89);
					row_TPF[88] = rs.getString(90);row_TPF[89] =rs.getString(91);
					row_TPF[90] = rs.getString(92);row_TPF[91] =rs.getString(93);
					row_TPF[92] = rs.getString(94);row_TPF[93] =rs.getString(95);
					row_TPF[94] = rs.getString(96);row_TPF[95] =rs.getString(97);
					
					row_TPF[96] = rs.getString(98);row_TPF[97] =rs.getString(99);
					row_TPF[98] = rs.getString(100);row_TPF[99] =rs.getString(101);	
					row_TPF[100] = rs.getString(102);row_TPF[101] =rs.getString(103);
					row_TPF[102] = rs.getString(104);row_TPF[103] =rs.getString(105);
					row_TPF[104] = rs.getString(106);row_TPF[105] =rs.getString(107);
					row_TPF[106] = rs.getString(108);row_TPF[107] =rs.getString(109);
					row_TPF[108] = rs.getString(110);row_TPF[109] =rs.getString(111);
					row_TPF[110] = rs.getString(112);row_TPF[111] =rs.getString(113);
					row_TPF[112] = rs.getString(114);
					
					model_tramePF.addRow(row_TPF);}
					
				
			//ferme les connexions		
				rs.close();  
				pstable2.close();
				 
			 } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				 
			 }
			  
	  // pour rechercher dans le tableau => FILTRE
			 t_text2.getDocument().addDocumentListener(new DocumentListener(){
				 @Override public void insertUpdate(DocumentEvent e) {
				 search(t_text2.getText());

				  }
				 @Override public void removeUpdate(DocumentEvent e) {
				 search(t_text2.getText());
				 }
				 @Override public void changedUpdate(DocumentEvent e) {
				 search(t_text2.getText());
				 }
				 public void search(String s)
				 {
				 if (s.length() == 0) {

				  sorter2.setRowFilter(null);
				 } else {

				  sorter2.setRowFilter(RowFilter.regexFilter(s));
				 }
				 }
			 });
		  	  
		  
		//affection au panel
		p2_sud.add(lab_text2);
		p2_sud.add(t_text2);
		p2_sud.add(ArchPF);
		p2_centre.add(s2);	
}
	
// CLASS BOUTON
public class ActionArchive implements ActionListener {
	  public void actionPerformed(ActionEvent e) {	 
		
		  if(e.getSource() == ArchMP) {
		         // 1-pour connaitre le nom 
			  String name = (String) tab_TrameMP.getValueAt(tab_TrameMP.getSelectedRow(),2);
			     // 2 - copier la ligne table trame vers table archive
			  try {

				   connect= connexion.getConnection(); //fait appel a la classe connexion
				  	String A_MP ="INSERT INTO TABLE_ATMP ( SELECT * FROM TABLE_TMP WHERE NOM = ? )" ;
					PreparedStatement psAMP = (PreparedStatement) connect.prepareStatement(A_MP);		
					psAMP.setString(1,name);
					psAMP.executeUpdate();
					psAMP.close();
					JOptionPane.showMessageDialog(null,"La trame : "+name+" a été archivé. Celle ci n'existe plus dans la base des Trame.");
					
			   } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				   }		  
		       // 3 - supprime les trame correspondante
			    
			  try {

				   connect= connexion.getConnection(); //fait appel a la classe connexion
				  	String A_MP ="DELETE FROM TABLE_TMP WHERE NOM = ? " ;
					PreparedStatement psAMP = (PreparedStatement) connect.prepareStatement(A_MP);		
					psAMP.setString(1,name);
					psAMP.executeUpdate();
					psAMP.close();
					
					
			   } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
	  }	 }
		  
		  if(e.getSource() == ArchPF) {
			
			  // 1-pour connaitre le nom 
			  String name = (String) tab_TramePF.getValueAt(tab_TramePF.getSelectedRow(),2);
			     // 2 - copier la ligne table trame vers table archive
			  try {

				   connect= connexion.getConnection(); //fait appel a la classe connexion
				  	String A_PF ="INSERT INTO TABLE_ATPF ( SELECT * FROM TABLE_TPF WHERE NOM = ? )" ;
					PreparedStatement psAPF = (PreparedStatement) connect.prepareStatement(A_PF);		
					psAPF.setString(1,name);
					psAPF.executeUpdate();
					psAPF.close();
					JOptionPane.showMessageDialog(null,"La trame : "+name+" a été archivé. Celle ci n'existe plus dans la base des Trame.");
					
			   } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				   }		  
		       // 3 - supprime les trame correspondante
			    
			  try {

				   connect= connexion.getConnection(); //fait appel a la classe connexion
				  	String A_PF ="DELETE FROM TABLE_TPF WHERE NOM = ? " ;
					PreparedStatement psAPF = (PreparedStatement) connect.prepareStatement(A_PF);		
					psAPF.setString(1,name);
					psAPF.executeUpdate();
					psAPF.close();
					
					
			   } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
	  }}
			  
			  
}  }



} //fin de classe
