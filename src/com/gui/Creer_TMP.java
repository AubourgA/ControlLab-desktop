package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import connexion.BD.connexion;



public class Creer_TMP extends Habillage {

	//declaration des variables
	
	private static Connection connect; //creer varaible connect
	
	JFrame fen_TMP;
	Image img_TMP;
	JPanel panel_TMP, panel_titre,panel_O, panel_E, panel_E1, panel_E2, panel_E3;
	JLabel  ltrame, lnom, lver, lfreq, lqtitep, lmod, lprep,  lpart, lnb, ldate, lqtite, lcarac, ltype,lmin, lmax,crit_max,crit2_max;
	JLabel print_label,trans_label,lVrai_Faux;
	JTextField tnom, tver, tfreq, tqtitep, tmod, tpart, tnb, tdate, tqtite, tcarac, tmin, tmax;
    JTextArea tprep;
    JButton b_ajout, b_suppr, b_visualtrame, b_record, histo;
    JTable tab_crit;
    JCheckBox Vrai_Faux;
    DefaultTableModel mod;
    private JComboBox <String> ctrame, cqtite, ctype;
    public static String chemin ="\\";
    
public Creer_TMP() {
	
	 fen_TMP();
}


private void fen_TMP() {
	fen_TMP = new JFrame("CREATION : Trame MP");
	
	fen_TMP.setSize(definition.SLarge,definition.SHaut);
	fen_TMP.setLocationRelativeTo(null);

    fen_TMP.setResizable(false);
  
    panel_TMP = new CreerTMP_Panel();
    panel_TMP.setLayout(new BorderLayout());
    panel_titre = new JPanel();
    panel_titre.setOpaque(false);
    panel_O = new JPanel(new GridBagLayout()); // partie info
    panel_O.setOpaque(false);
    panel_E = new JPanel(new BorderLayout()); // partie controle
    panel_E.setOpaque(false);
    panel_E1 = new JPanel(new GridBagLayout());
    panel_E1.setOpaque(false);
    panel_E2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,30));
    panel_E2.setOpaque(false);
    panel_E3 = new JPanel(new FlowLayout(0,40,50));
    panel_E3.setOpaque(false);
    panel_E.add(panel_E1, BorderLayout.NORTH);
    panel_E.add(panel_E2, BorderLayout.CENTER);
    panel_E.add(panel_E3, BorderLayout.SOUTH);
    
    panel_TMP.add(panel_titre, BorderLayout.NORTH);
    panel_TMP.add(panel_O, BorderLayout.WEST);
    panel_TMP.add(panel_E, BorderLayout.EAST);
    
    //code provisoirre
    panel_TMP.addMouseListener(new myMouseListener());
    
    //JLabel titre = new JLabel("NOUVELLE TRAME POUR MATIERE PREMIERE");
    //panel_titre.add(titre);
    
    info_MP(); // methode remplissage info MP
    carac_MP(); // methode pour caracteristique MP
    
    fen_TMP.getContentPane().add(panel_TMP);
    fen_TMP.setVisible(true);
}



private void info_MP() {
	//panel O
	GridBagConstraints c1 = new GridBagConstraints();
	GridBagConstraints c2 = new GridBagConstraints();
	
   //def et placement des composants
	ltrame = new JLabel("Type de trame : ");
	lnom = new JLabel("Nom de la Matiere : ");
	lver = new JLabel("Version trame : ");
	lfreq = new JLabel("Frequence Controle : ");
	lqtitep = new JLabel("Quantité à prélever : ");
	lmod = new JLabel("Modop : ");
	lprep = new JLabel("Préparation : ");
	lpart = new JLabel("Particularité : ");
	lnb = new JLabel("Numero de Lot : ");
	ldate = new JLabel("Date de création : ");
	lqtite = new JLabel("Choix de l'unité : ");
	
	
	ltrame.setForeground(Color.BLACK);
	
	lnom.setForeground(Color.BLACK);
	lver.setForeground(Color.BLACK);
	lfreq.setForeground(Color.BLACK);
	lqtitep.setForeground(Color.BLACK);
	lmod.setForeground(Color.BLACK);
	lprep.setForeground(Color.BLACK);
	lpart.setForeground(Color.BLACK);
	lnb.setForeground(Color.BLACK);
	ldate.setForeground(Color.BLACK);
	lqtite.setForeground(Color.BLACK);
	
	c1.gridx =0;
	c1.gridy =0;
	c1.anchor = GridBagConstraints.FIRST_LINE_END;
	c1.insets = new Insets(20,50,0,0);
	panel_O.add(ltrame, c1);
	c1.gridy=1;
	panel_O.add(lnom, c1);
	c1.gridy=2;
	panel_O.add(lver, c1);
	c1.gridy=3;
	panel_O.add(lfreq, c1);
	c1.gridy=4;
	panel_O.add(lqtitep, c1);
	c1.gridy=5;
	panel_O.add(lmod, c1);
	c1.gridy=6;
	panel_O.add(lprep, c1);
	c1.gridy=7;
	panel_O.add(lpart, c1);
	c1.gridy=8;
	panel_O.add(lnb, c1);
	c1.gridy=9;
	panel_O.add(ldate, c1);
	c1.gridy=10;
	panel_O.add(lqtite, c1);
	
	String[] MPtype = {"CLASSIQUE","CAPILLAIRE"};
	ctrame = new JComboBox<>(MPtype);
	tnom = new JTextField(15);
	tver = new JTextField(10);
	tver.setText("XXX - aa/mm/jj");
	tfreq = new JTextField(10);
	tfreq.setText("Systematique");
	tqtitep = new JTextField(10);
	tmod = new JTextField(20);
	tprep = new JTextArea(5,20);
	JScrollPane tprep_sp = new JScrollPane(tprep);
	
	tpart = new JTextField(20);
	tnb = new JTextField(5);
	tdate = new JTextField(10);
	tdate.setText("aa-mm-jj");
	String[] U = {"Qtite x 1000 Kg : ","Qtite x 1000 L : "};
	cqtite = new JComboBox<>(U);
	
	
	c2.gridx =1;
	c2.gridy =0;
	c2.anchor = GridBagConstraints.FIRST_LINE_START;
	c2.insets = new Insets(20,10,0,0);
	panel_O.add(ctrame, c2);
	c2.gridy=1;
	panel_O.add(tnom, c2);
	c2.gridy=2;
	panel_O.add(tver, c2);
	c2.gridy=3;
	panel_O.add(tfreq, c2);
	c2.gridy=4;
	panel_O.add(tqtitep, c2);
	c2.gridy=5;
	panel_O.add(tmod, c2);
	c2.gridy=6;
	panel_O.add(tprep_sp, c2);
	c2.gridy=7;
	panel_O.add(tpart, c2);
	c2.gridy=8;
	panel_O.add(tnb, c2);
	c2.gridy=9;
	panel_O.add(tdate, c2);
	c2.gridy=10;
	panel_O.add(cqtite, c2);
	
	
}

private void carac_MP() {
	//panel E
	GridBagConstraints c3 = new GridBagConstraints();
	GridBagConstraints c4 = new GridBagConstraints();
	
	lcarac = new JLabel("Nouveau Critére : ");
	ltype = new JLabel("Type du Critere (?) : ");
	lVrai_Faux = new JLabel("Inserer Boolean :");
	lmin = new JLabel(" Valeur MIN : ");
	lmax = new JLabel("Valeur MAX : ");
	b_ajout = new JButton("AJOUTER");
	crit_max = new JLabel("Nb de criteres Disponibles : ");

	
	//couloueur des composant
			lcarac.setForeground(Color.BLACK);
			ltype.setForeground(Color.BLACK);
			lmin.setForeground(Color.BLACK);
			lmax.setForeground(Color.BLACK);
			crit_max.setForeground(Color.BLACK);
			lVrai_Faux.setForeground(Color.BLACK);
	
	
	//info bulle
	ltype.setToolTipText("<html> O : pour valeur obligatoire<br/> S : pour critere de Secours</html>");
	
	
	//placement des labels
	c3.gridx =0;
	c3.gridy =0;
	c3.anchor = GridBagConstraints.FIRST_LINE_END;
	c3.insets = new Insets(60,0,0,0);
	panel_E1.add(ltype,c3);
	c3.insets = new Insets(10,0,0,0);
	c3.gridy = 1;
	panel_E1.add(lcarac,c3);
	c3.gridy = 2;
	panel_E1.add(lVrai_Faux,c3);
	c3.gridy=3;
	panel_E1.add(lmin,c3);
	c3.gridy=4;
	panel_E1.add(lmax,c3);
	c3.gridy=5;
	panel_E1.add(b_ajout,c3);
	c3.gridy=6;
	panel_E1.add(crit_max,c3);
	
	// def et placement des enregistrements
	tcarac = new JTextField(15);
	String[] T = {"O","S"};
	ctype = new JComboBox<>(T);
	Vrai_Faux = new JCheckBox(" VRAI/FAUX "); 
	Vrai_Faux.setOpaque(false);
	tmin = new JTextField(5);
	tmax = new JTextField(5);
	b_suppr = new JButton("SUPPRIMER");
	crit2_max = new JLabel("15");
	
	c4.gridx =1;
	c4.gridy =0;
	c4.anchor = GridBagConstraints.FIRST_LINE_START;
	c4.insets = new Insets(60,10,0,40);
	panel_E1.add(ctype, c4);
	
	c4.insets = new Insets(10,10,0,40);
	c4.gridy=1;
	panel_E1.add(tcarac,c4);
	c4.gridy=2;
	panel_E1.add(Vrai_Faux,c4);
	c4.gridy=3;
	panel_E1.add(tmin,c4);
	c4.gridy=4;
	panel_E1.add(tmax,c4);
	c4.gridy=5;
	panel_E1.add(b_suppr,c4);
	c4.gridy=6;
	panel_E1.add(crit2_max,c4);
	
	//creation du tableau pour afficher les criteres
	tab_crit = new JTable();
	Object [] col1 = {"TYPE", "CRITERES", "MIN", "MAX" };
	tab_crit.setShowHorizontalLines(false);
	mod = new DefaultTableModel();
	mod.setColumnIdentifiers(col1);
	tab_crit.setModel(mod);
	JScrollPane tableSP = new JScrollPane(tab_crit);
	//panel_E2.add(new JScrollPane(tab_crit));
	tableSP.setPreferredSize(new Dimension(definition.tablesp_Large,definition.tablesp_Haut));
	
	panel_E2.add(tableSP, FlowLayout.LEFT);
	tab_crit.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,10));
	tab_crit.getTableHeader().setBackground(Color.YELLOW);
	
	
	
	
	

	
	//icon bouton
	b_visualtrame = new JButton();
	 ImageIcon print_icon = new ImageIcon(getClass().getResource(definition.URL_PRINT));
	 print_label = new JLabel("IMPRIMER",print_icon,SwingConstants.CENTER);
	 print_label.setVerticalTextPosition(JLabel.BOTTOM);
	 print_label.setHorizontalTextPosition(JLabel.CENTER);
	 b_visualtrame.setPreferredSize(new Dimension(100,60));
	 b_visualtrame.add(print_label);
	
	 b_record = new JButton();
	 ImageIcon trans_icon = new ImageIcon(getClass().getResource(definition.URL_TRANSFERT));
	 trans_label = new JLabel("TRANSFERER",trans_icon,SwingConstants.CENTER);
	 trans_label.setVerticalTextPosition(JLabel.BOTTOM);
	trans_label.setHorizontalTextPosition(JLabel.CENTER);
	 b_record.setPreferredSize(new Dimension(120,60));
	 b_record.add(trans_label);
	 
	
	//actionlistener
	b_record.addActionListener(new TRANSFERT());
	b_visualtrame.addActionListener(new PDFTRAME());
	
	panel_E3.add(b_record, FlowLayout.LEFT);
	panel_E3.add(b_visualtrame, FlowLayout.LEFT);
	
	
	
	//action sur les bouton
	b_ajout.addActionListener(new ADDLINE()); //class  pour bouton
	
	b_suppr.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			mod.removeRow(tab_crit.getSelectedRow());
			int sup_ligne =0;
			sup_ligne++;
			sup_ligne = Integer.parseInt(crit2_max.getText())+sup_ligne;
			crit2_max.setText(String.valueOf(sup_ligne));
	}});
	
	//condition en fonciton de la selection ou non du VRAI/FAUX pour afficher directement dans MIN/MAX
			Vrai_Faux.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					if (Vrai_Faux.isSelected()) {
						tmin.setText("VRAI"); 
						tmax.setText("FAUX");
					     }else {
					    	 tmin.setText(""); 
								tmax.setText(""); 
					     }
				 }	
			});
} // fin du void



//Class des  BOUTONS
public class ADDLINE implements ActionListener {
	public void actionPerformed(ActionEvent ev) {
		int ligne=0;
		Object[] row = new Object[4];
		row[0] = ctype.getSelectedItem();
		row[1] = tcarac.getText();
		row[2] = tmin.getText();
		row[3] = tmax.getText();
		mod.addRow(row);
		
		//nb de paramatre
		ligne++;
		ligne = Integer.parseInt(crit2_max.getText())-ligne;
		crit2_max.setText(String.valueOf(ligne));
		prime.CentrerTableau(0, tab_crit);
	}
}
public class TRANSFERT implements ActionListener {
	public void actionPerformed(ActionEvent ev) {
		 try {
				int t=12;
				int u=13;
				int v=14;
				int w=15;
			   connect= connexion.getConnection(); //fait appel a la classe connexion
				
				String str ="INSERT into TABLE_TMP (TYPE_TRAME,TRAME_VER,NOM,FREQUENCE,QTITE_PREL,MODOP,PREPARATION,PARTICULARITE,N_LOT,DATE_C,QTITE_UNIT,"
						+ "T1,C1,MIN1,MAX1,T2,C2,MIN2,MAX2,T3,C3,MIN3,MAX3,T4,C4,MIN4,MAX4,T5,C5,MIN5,MAX5,T6,C6,MIN6,MAX6,T7,C7,MIN7,MAX7,T8,C8,MIN8,MAX8,"
						+ "T9,C9,MIN9,MAX9,T10,C10,MIN10,MAX10,T11,C11,MIN11,MAX11,T12,C12,MIN12,MAX12,T13,C13,MIN13,MAX13,T14,C14,MIN14,MAX14,T15,C15,MIN15,MAX15)" 
				
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = connect.prepareStatement(str);
				//champs info
				ps.setString(1, (String) ctrame.getSelectedItem());
				ps.setString(2,  tver.getText());
				ps.setString(3,  tnom.getText());
				ps.setString(4,  tfreq.getText());
				ps.setString(5,  tqtitep.getText());
				ps.setString(6,  tmod.getText());
				ps.setString(7,  tprep.getText());
				ps.setString(8,  tpart.getText());
				ps.setString(9,  tnb.getText());
				ps.setString(10,  tdate.getText());
				ps.setString(11,  (String) cqtite.getSelectedItem());
				
				//champ type+critere
				for (int l = 0; l < tab_crit.getRowCount(); l++) {
					
						 ps.setString(t,tab_crit.getValueAt(l, 0).toString()); //ajout a T(x)
						 ps.setString(u,tab_crit.getValueAt(l, 1).toString()); // ajout a C(x)
						 ps.setString(v,(String) tab_crit.getValueAt(l, 2)); // ajout a Min(x)
						 ps.setString(w,(String) tab_crit.getValueAt(l, 3)); // ajout a Max(x)
						 t=t+4;
						 u=u+4;
						 v=v+4;
						 w=w+4;
					}
				
				// pour completer BDD non utilisé
			   int somme = tab_crit.getRowCount()*4+12; //calcul du nombre de ligne bdd utilisé
				
				if (t<68) {
				  	for (t=somme; t<72; t=t+4) {
				  		
				  		ps.setString(t,"" );
				  		ps.setString(u,"");
				  		ps.setString(v,null);
				  		ps.setString(w,null);
				  		u=u+4;
				  		v=v+4;
				  		w=w+4;
				  	}
				}
				ps.executeUpdate();
				ps.close();
				
				JOptionPane.showMessageDialog(null,"Nouvelle Trame pour la matiere : "+tnom.getText()+" ajoutée");
				fen_TMP.dispose();
				
			   } catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			   }
	}}

public class PDFTRAME implements ActionListener   {
	public void actionPerformed(ActionEvent ev) {
		
		Document doc = new Document();
		chemin = chemin+"Trame_MP_"+tnom.getText()+".pdf";
		
		
		 try {
		    PdfWriter.getInstance(doc, new FileOutputStream(chemin));
		    doc.open();
		    
	  //creation du contenu
		    Paragraph p1 = new Paragraph("TECHNIQUE BETON",FontFactory.getFont(FontFactory.HELVETICA,6));
		    Paragraph p2 = new Paragraph("CONTROLE DE MP - TRAME "+ctrame.getSelectedItem(),FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
            Paragraph p3 = new Paragraph("VALIDATION DE LA TRAME - VERSION "+tver.getText(),FontFactory.getFont(FontFactory.HELVETICA,10));
		    Paragraph block = new Paragraph("  ",FontFactory.getFont(FontFactory.HELVETICA,6));
		    p2.setAlignment(Element.ALIGN_CENTER); // centrer text
		    p3.setAlignment(Element.ALIGN_CENTER); 
		    
		    
           PdfPTable tableValidation = new PdfPTable(1); 
           PdfPCell cell = new PdfPCell();
           cell.addElement(new Phrase("PAR REPONSABLE LABORATOIRE",FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD,BaseColor.BLACK)));
           cell.addElement(new Phrase("LE : ",FontFactory.getFont(FontFactory.HELVETICA,6)));
           cell.addElement(block);
           cell.addElement(new Phrase("VISA :",FontFactory.getFont(FontFactory.HELVETICA,6)));
           cell.addElement(block);
           cell.setBackgroundColor(new BaseColor(226, 226, 226));
           tableValidation.addCell(cell);
           
      
           PdfPTable TableInfo = new PdfPTable(2);
           PdfPCell cellInfo1 = new PdfPCell(new Paragraph(lfreq.getText(),FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
           PdfPCell cellInfo2 = new PdfPCell(new Paragraph(tfreq.getText(),FontFactory.getFont(FontFactory.HELVETICA,8)));
           PdfPCell cellInfo3 = new PdfPCell(new Paragraph(lqtitep.getText(),FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
           
           PdfPCell cellInfo4 = new PdfPCell(new Paragraph(tqtitep.getText(),FontFactory.getFont(FontFactory.HELVETICA,8)));
           PdfPCell cellInfo5 = new PdfPCell(new Paragraph(lmod.getText(),FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
           PdfPCell cellInfo6 = new PdfPCell(new Paragraph(tmod.getText(),FontFactory.getFont(FontFactory.HELVETICA,8)));
           PdfPCell cellInfo7 = new PdfPCell(new Paragraph(lprep.getText(),FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
           PdfPCell cellInfo8 = new PdfPCell(new Paragraph(tprep.getText(),FontFactory.getFont(FontFactory.HELVETICA,8)));
           PdfPCell cellInfo9 = new PdfPCell(new Paragraph(lpart.getText(),FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
           PdfPCell cellInfo10 = new PdfPCell(new Paragraph(tpart.getText(),FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
           cellInfo1.setBackgroundColor(new BaseColor(255, 255, 107));
           cellInfo2.setBackgroundColor(new BaseColor(255, 255, 107));
           cellInfo3.setBackgroundColor(new BaseColor(255, 255, 107));
           cellInfo4.setBackgroundColor(new BaseColor(255, 255, 107));
           cellInfo5.setBackgroundColor(new BaseColor(255, 255, 107));
           cellInfo6.setBackgroundColor(new BaseColor(255, 255, 107));
           cellInfo7.setBackgroundColor(new BaseColor(255, 255, 107));
           cellInfo8.setBackgroundColor(new BaseColor(255, 255, 107));
           cellInfo9.setBackgroundColor(new BaseColor(255, 255, 107));
           cellInfo10.setBackgroundColor(new BaseColor(255, 255, 107));
           TableInfo.addCell(cellInfo1);
           TableInfo.addCell(cellInfo2);
           TableInfo.addCell(cellInfo3);
           TableInfo.addCell(cellInfo4);
           TableInfo.addCell(cellInfo5);
           TableInfo.addCell(cellInfo6);
           TableInfo.addCell(cellInfo7);
           TableInfo.addCell(cellInfo8);
           TableInfo.addCell(cellInfo9);
           TableInfo.addCell(cellInfo10);
           TableInfo.setWidths(new int[] {20,120});
           
           PdfPTable TableStat = new PdfPTable(7);
           TableStat.addCell(new Phrase("CUMLS SUR 1 AN",FontFactory.getFont(FontFactory.HELVETICA,7)));
           TableStat.addCell(new Phrase("TONNAGE",FontFactory.getFont(FontFactory.HELVETICA,7)));
           TableStat.addCell(new Phrase("  "));
           TableStat.addCell(new Phrase("NC",FontFactory.getFont(FontFactory.HELVETICA,7)));
           TableStat.addCell(new Phrase("  "));
           TableStat.addCell(new Phrase("DEROG",FontFactory.getFont(FontFactory.HELVETICA,7)));
           TableStat.addCell(new Phrase("   "));
           
           PdfPTable TableNom = new PdfPTable(2);
           PdfPCell cellnom1 = new PdfPCell(new Paragraph("MP",FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD)));
           PdfPCell cellnom2 = new PdfPCell(new Paragraph(tnom.getText(),FontFactory.getFont(FontFactory.HELVETICA,14,BaseColor.RED)));
           cellnom2.setHorizontalAlignment(Element.ALIGN_CENTER);
           TableNom.addCell(cellnom1);
           TableNom.addCell(cellnom2);
           TableNom.setWidths(new int[] {20,120});
          
           PdfPTable TableTracabililte = new PdfPTable(8);
           TableTracabililte.addCell(new Phrase("CONFORMITE",FontFactory.getFont(FontFactory.HELVETICA,7)));
           TableTracabililte.addCell(new Phrase("  ",FontFactory.getFont(FontFactory.HELVETICA,7)));
           TableTracabililte.addCell(new Phrase("N° DE LOT",FontFactory.getFont(FontFactory.HELVETICA,7)));
           TableTracabililte.addCell(new Phrase("  ",FontFactory.getFont(FontFactory.HELVETICA,6)));
           TableTracabililte.addCell(new Phrase("N° FICHE NC",FontFactory.getFont(FontFactory.HELVETICA,7)));
           TableTracabililte.addCell(new Phrase("  ",FontFactory.getFont(FontFactory.HELVETICA,6)));
           TableTracabililte.addCell(new Phrase("DEROG Oui/Vide",FontFactory.getFont(FontFactory.HELVETICA,7)));
           TableTracabililte.addCell(new Phrase("   ",FontFactory.getFont(FontFactory.HELVETICA,6)));
           TableTracabililte.addCell(new Phrase("RESP DES ESSAIS",FontFactory.getFont(FontFactory.HELVETICA,6)));
           TableTracabililte.addCell(new Phrase("  ",FontFactory.getFont(FontFactory.HELVETICA,6)));
           TableTracabililte.addCell(new Phrase("RESERVATION LOT",FontFactory.getFont(FontFactory.HELVETICA,6)));
           TableTracabililte.addCell(new Phrase("   ",FontFactory.getFont(FontFactory.HELVETICA,6)));
           TableTracabililte.addCell(new Phrase("OBSERVATION",FontFactory.getFont(FontFactory.HELVETICA,6)));
           TableTracabililte.addCell(new Phrase("  ",FontFactory.getFont(FontFactory.HELVETICA,6)));
           TableTracabililte.addCell(new Phrase("DATE CONTROLE",FontFactory.getFont(FontFactory.HELVETICA,6)));
           TableTracabililte.addCell(new Phrase("   ",FontFactory.getFont(FontFactory.HELVETICA,7)));
           TableTracabililte.setWidths(new int[] {30,25,25,25,25,25,25,25});
           
           PdfPTable TableCritere = new PdfPTable(4);
           TableCritere.addCell(new Phrase((String)cqtite.getSelectedItem(),FontFactory.getFont(FontFactory.HELVETICA,6)));
           TableCritere.addCell(new Phrase("CRITERES",FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
           TableCritere.addCell(new Phrase("MIN",FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
           TableCritere.addCell(new Phrase("MAX",FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
           
           for (int rows = 0; rows < tab_crit.getRowCount();rows++) {
        	   for( int cols = 0; cols < tab_crit.getColumnCount(); cols++) {
        		   TableCritere.addCell(new Phrase(tab_crit.getModel().getValueAt(rows, cols).toString(),FontFactory.getFont(FontFactory.HELVETICA,6)));
        	   }
           }
           TableCritere.setWidths(new int[] {25,100,25,25});
		     
           
  //ajout contenu au doc
		    doc.add(p1);
		    doc.add(p2);
		    doc.add(p3);
		    doc.add(block);
		    doc.add(tableValidation);
		    doc.add(block);
		    doc.add(TableInfo);
		    doc.add(block);
		    doc.add(TableStat);
		    doc.add(block);
		    doc.add(TableNom);
		    doc.add(block);
		    doc.add(TableTracabililte);
		    doc.add(block);
		    doc.add(TableCritere);
		    
		    } catch (DocumentException de) {
		    	de.printStackTrace();
		   } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		 doc.close();
		 
		 try {
			Desktop.getDesktop().open(new File(chemin));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 chemin=""; 
	}}


}

