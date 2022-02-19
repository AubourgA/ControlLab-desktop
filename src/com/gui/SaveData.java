package com.gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import connexion.BD.connexion;

//Cette class sert a : 
// 1) Tansferer les donnees des talbe SQL dans un fichier Excel
// 2) Effacer les donnes des tables SQL 

public class SaveData {

	private static Connection connect; //creer varaible connect
	
	public SaveData() {
		//DataTransfert();
		//ResetBase();
	}
	
	
	
	protected void DataTransfert() {
	
	 String[] liste_transfert = {"Base Matieres Premieres","Base Produits Finis", "Base Essais Mecaniques"};
	 String Results =(String) JOptionPane.showInputDialog(null,"Quel Type de Contrôle A Transferer ?","Transfert de Base",JOptionPane.QUESTION_MESSAGE,null, liste_transfert,liste_transfert[0]);
     
        if (Results.equals(liste_transfert[0])) {  //choix du type de base
       //requete acces base de donnee
   	     try {
    	    	 connect= connexion.getConnection(); //initialisation connection
    		     String sql1 = "SELECT * FROM TABLE_MP ORDER BY NOM"; // selecton type de donne de la base
				      PreparedStatement pState1 = (PreparedStatement) connect.prepareStatement(sql1);
				       ResultSet rs = pState1.executeQuery();
				       		
				@SuppressWarnings("resource") //creation fichier excel
				XSSFWorkbook wb = new XSSFWorkbook();
				     XSSFSheet spreadsheet = wb.createSheet("base MP");
				
    		    XSSFRow row = spreadsheet.createRow(0);
    		    XSSFCell cell;
    		    
  //1ere ligne avec nom des colonnes (recup nom via metadonne)
    		    for(int c=1; c<123;c++) {
    		    	cell = row.createCell(c);cell.setCellValue(rs.getMetaData().getColumnName(c+1));
    		    }
   //insertion suite de la table dans le fichier xls
    		 int i= 1; //pour la 2em ligne et suite             		 
    		 while(rs.next()) {
    			 row = spreadsheet.createRow(i);  //creeer la ligne
    			 for(int j=1;j<123;j++) { //boucle pour toute la ligne
    				cell = row.createCell(j); cell.setCellValue(rs.getString(j+1));    
    			 }
    			 i++;          		
    		 }         		 
    		 
    		 //choisir lieu de stockage
    		  JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());		  
    		  j.setDialogTitle("Destination du fichier de Backup" );
    		    //pour accepter uniquement fichier excel
    		  FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier Excel .xlsx",".xlsx");
    		  j.addChoosableFileFilter(filter);
    		  j.setAcceptAllFileFilterUsed(false);
    		  j.setFileFilter(filter);
    		  
    		     int r = j.showSaveDialog(null);
    		      if ( r == JFileChooser.APPROVE_OPTION) {
    			 FileOutputStream out = new FileOutputStream(new File(j.getSelectedFile().getAbsolutePath().toString() ));
                 wb.write(out);
                  JOptionPane.showMessageDialog(null, "Transfert Complete. Fichier Crée");
    		      } else {
    		    	  JOptionPane.showMessageDialog(null, "Aucune donnée n'a été transférée"); }
    		              		  
    		 rs.close();
    		 pState1.close();
          } catch(Exception e1) {
		JOptionPane.showMessageDialog(null, e1);
	     }} //fin du 2em if
     
        else if  (Results.equals(liste_transfert[1])) {
      	  try {
     	    	 connect= connexion.getConnection(); //initialisation connection
     		     String sql2 = "SELECT * FROM TABLE_PF ORDER BY NOM "; // selecton type de donne de la base
				      PreparedStatement pState2 = (PreparedStatement) connect.prepareStatement(sql2);
				       ResultSet rs2 = pState2.executeQuery();
				       		
				@SuppressWarnings("resource") //creation fichier excel
				XSSFWorkbook wb2 = new XSSFWorkbook();
				     XSSFSheet spreadsheet2 = wb2.createSheet("base PF");
				
     		    XSSFRow row2 = spreadsheet2.createRow(0);
     		    XSSFCell cell2;
     		    
   //1ere ligne avec nom des colonnes (recup nom via metadonne)
     		    for(int c=1; c<195;c++) {
     		    	cell2 = row2.createCell(c);cell2.setCellValue(rs2.getMetaData().getColumnName(c+1));
     		    }
    //insertion suite de la table dans le fichier xls
     		 int i= 1; //pour la 2em ligne et suite             		 
     		 while(rs2.next()) {
     			 row2 = spreadsheet2.createRow(i);  //creeer la ligne
     			 for(int j=1;j<195;j++) { //boucle pour toute la ligne
     				cell2 = row2.createCell(j); cell2.setCellValue(rs2.getString(j+1));    
     			 }
     			 i++;          		
     		 }         		 
     		 
     		 //choisir lieu de stockage
     		  JFileChooser j2 = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());		  
     		  j2.setDialogTitle("Destination du fichier de Backup" );
     		    //pour accepter uniquement fichier excel
     		  FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier Excel .xlsx",".xlsx");
     		  j2.addChoosableFileFilter(filter);
     		  j2.setAcceptAllFileFilterUsed(false);
     		  j2.setFileFilter(filter);
     		  
     		     int r2 = j2.showSaveDialog(null);
     		      if ( r2 == JFileChooser.APPROVE_OPTION) {
     			 FileOutputStream out = new FileOutputStream(new File(j2.getSelectedFile().getAbsolutePath().toString() ));
                  wb2.write(out);
                   JOptionPane.showMessageDialog(null, "Transfert Complete. Fichier Créé");
     		      } else {
     		    	  JOptionPane.showMessageDialog(null, "Aucune donnée n'a été transférée"); }
     		              		  
     		 rs2.close();
     		 pState2.close();
           } catch(Exception e2) {
		JOptionPane.showMessageDialog(null, e2);
	     }         	
        } // fin du else if
        else if  (Results.equals(liste_transfert[2])) {
      	  try {
      	    	 connect= connexion.getConnection(); //initialisation connection
      		     String sql3 = "SELECT * FROM TABLE_EM ORDER BY PF "; // selecton type de donne de la base
				      PreparedStatement pState3 = (PreparedStatement) connect.prepareStatement(sql3);
				       ResultSet rs3 = pState3.executeQuery();
				       		
				@SuppressWarnings("resource") //creation fichier excel
				XSSFWorkbook wb3 = new XSSFWorkbook();
				     XSSFSheet spreadsheet3 = wb3.createSheet("base EM");
				
      		    XSSFRow row3 = spreadsheet3.createRow(0);
      		    XSSFCell cell3;
      		    
    //1ere ligne avec nom des colonnes (recup nom via metadonne)
      		    for(int c=1; c<40;c++) {
      		    	cell3 = row3.createCell(c);cell3.setCellValue(rs3.getMetaData().getColumnName(c+1));
      		    }
     //insertion suite de la table dans le fichier xls
      		 int i= 1; //pour la 2em ligne et suite             		 
      		 while(rs3.next()) {
      			 row3 = spreadsheet3.createRow(i);  //creeer la ligne
      			 for(int j=1;j<40;j++) { //boucle pour toute la ligne
      				cell3 = row3.createCell(j); cell3.setCellValue(rs3.getString(j+1));    
      			 }
      			 i++;          		
      		 }         		 
      		 
      		 //choisir lieu de stockage
      		  JFileChooser j3 = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());		  
      		  j3.setDialogTitle("Destination du fichier de Backup" );
      		    //pour accepter uniquement fichier excel
      		  FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier Excel .xlsx",".xlsx");
      		  j3.addChoosableFileFilter(filter);
      		  j3.setAcceptAllFileFilterUsed(false);
      		  j3.setFileFilter(filter);
      		  
      		     int r3 = j3.showSaveDialog(null);
      		      if ( r3 == JFileChooser.APPROVE_OPTION) {
      			 FileOutputStream out = new FileOutputStream(new File(j3.getSelectedFile().getAbsolutePath().toString() ));
                   wb3.write(out);
                    JOptionPane.showMessageDialog(null, "Transfert Complete. Fichier de la BASE_EM Créé");
      		      } else {
      		    	  JOptionPane.showMessageDialog(null, "Aucune donnée n'a été transférée"); }
      		              		  
      		 rs3.close();
      		 pState3.close();
            } catch(Exception e3) {
		JOptionPane.showMessageDialog(null, e3);
	     }      }}
	
protected void ResetBase() {
	 //liste des messages
	   String[] liste_RAZ = {"Base Matieres Premieres","Base Produits Finis", "Base Essais Mecaniques"};
       String Result2 =(String) JOptionPane.showInputDialog(null,"Quel Type de Base A Remetre a Zero ?","Réinitialisation des Bases de Donnees",JOptionPane.QUESTION_MESSAGE,null, liste_RAZ,liste_RAZ[0]);
    
if(Result2!=null) { //evite erreur lorsque 'annuler' est selectionné
  	       
   if (Result2.equals(liste_RAZ[0])) { //choix liste
	 int reponse =  JOptionPane.showConfirmDialog(null,"ATTENTION : Vous etes sur le point d effacer toutes les donnees. Etes vous sur ?","MESSAGE DE CONFIRMATION",JOptionPane.WARNING_MESSAGE);	
    		 if (reponse == 0) {   //reponse 
    	  	        	
    	           try {	 
    		          connect= connexion.getConnection();
    		          String sql4 = "TRUNCATE  TABLE_MP ";
    		          PreparedStatement pState4 = (PreparedStatement) connect.prepareStatement(sql4);
		              pState4.executeUpdate();
    		          pState4.close();
    		          JOptionPane.showMessageDialog(null, "Table des MP Remise a Zero");
    	                } catch (Exception e4) {
    		              JOptionPane.showMessageDialog(null, e4);}
    	 
    	          }
    		    else if (reponse == 2) {
    			JOptionPane.showMessageDialog(null, "Action Annulée"); }
    		
     } else if (Result2.equals(liste_RAZ[1])) {
       int reponse =  JOptionPane.showConfirmDialog(null,"ATTENTION : Vous etes sur le point d effacer toutes les donnees. Etes vous sur ?","MESSAGE DE CONFIRMATION", JOptionPane.WARNING_MESSAGE);
    	 if (reponse == 0) {   //reponse 
    	
    	           try {
    		          connect= connexion.getConnection();
    		          String sql5 = "DELETE FROM TABLE_PF ";
    		          PreparedStatement pState5 = (PreparedStatement) connect.prepareStatement(sql5);
		              pState5.executeUpdate();pState5.close();
    		 		 JOptionPane.showMessageDialog(null, "Table des PF Remise a Zeo");
    	                } catch (Exception e5) {
    		               JOptionPane.showMessageDialog(null, e5);} 
    	              }
    	  else if (reponse == 2) {
  			JOptionPane.showMessageDialog(null, "Action Annulée"); }
    	   
     } else if (Result2.equals(liste_RAZ[2])) {
    	 int reponse =  JOptionPane.showConfirmDialog(null,"ATTENTION : Vous etes sur le point d effacer toutes les donnees. Etes vous sur ?","MESSAGE DE CONFIRMATION", JOptionPane.WARNING_MESSAGE);
    	 if (reponse == 0) {   //reponse 
    	 
    	            try {
    	                connect= connexion.getConnection();
		                String sql6 = "DELETE FROM table_em WHERE STATUT = 'TERMINE' AND ANNE = YEAR(NOW())-1 OR ANNE = YEAR(NOW())-2";
		                PreparedStatement pState6 = (PreparedStatement) connect.prepareStatement(sql6);			    
		                pState6.executeUpdate(); pState6.close();
		                 JOptionPane.showMessageDialog(null, "Les Essais Mécaniques de l'Annee sont supprimés");
	                 } catch (Exception e6) {
		                     JOptionPane.showMessageDialog(null, e6);}
    	             
		      } 
    	else if (reponse == 2) {
			JOptionPane.showMessageDialog(null, "Action Annulée"); }
	  	    	 
}} //fin if principale  
}

public void TableToExcel(JTable table, File file, String Name) {
        try {
        	TableModel model = table.getModel();
        	FileWriter out = new FileWriter(file);
        	for (int i=0; i<model.getColumnCount(); i++) {
        		out.write(model.getColumnName(i)+"\t");
        	}
        	out.write("\n");
        	
        	
        	for (int i=0; i<model.getRowCount();i++) {
        		for (int j=0;j<model.getColumnCount();j++) {
        			out.write(model.getValueAt(i, j)+"\t");
        		}
        		out.write("\n");
        	}
        	out.close();
        	JOptionPane.showMessageDialog(null, Name);
        } catch (Exception err) {
        	err.printStackTrace();
        }
	
}
	
}
