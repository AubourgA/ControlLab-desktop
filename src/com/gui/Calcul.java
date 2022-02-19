package com.gui;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Calcul {

	//***********Calcul de la densité pour les eprouvette  ************
	 public static String Densite(String valeur) {
		 Float val = Float.parseFloat(valeur);
		 
		
		
		 Float Produit = 1000 * val /  (160*40*40);
		 NumberFormat d_number = NumberFormat.getNumberInstance(Locale.ENGLISH); //permet d'obtenir nombre a 2 chiffr apres virgul
		  d_number.setMaximumFractionDigits(2); 
		 String densite_Format = d_number.format(Produit);
		 
		 return densite_Format;
	 }
	
	 //*********** Calcul de la densité Moyenne des eprouvette **************
	 
	 public static String DensiteMoyenne(String v1, String v2, String v3) {
		 Float dm1 = Float.parseFloat(v1);
		 Float dm2 = Float.parseFloat(v2);
		 Float dm3 = Float.parseFloat(v3);
		 
		 Float dm = (dm1+dm2+dm3)/3;
		 
		 NumberFormat d_number = NumberFormat.getNumberInstance(Locale.ENGLISH); //permet d'obtenir nombre a 2 chiffr apres virgul
		  d_number.setMaximumFractionDigits(2); 
		 String dM_Format = d_number.format(dm);
		 
		 
		 return dM_Format;
	 }
	 
	 // ************ Calcul de la Resistance Flex Moyenne sur 3ep ************
	 public static String ResFlexMoy(String v1, String v2, String v3) {
		 Float rf1 = Float.parseFloat(v1);
		 Float rf2 = Float.parseFloat(v2);
		 Float rf3 = Float.parseFloat(v3);
		 
		 Float refm = (rf1+rf2+rf3)/3;
		 
		 NumberFormat d_number = NumberFormat.getNumberInstance(Locale.ENGLISH); //permet d'obtenir nombre a 2 chiffr apres virgul
		  d_number.setMaximumFractionDigits(2); 
		 String FM_Format = d_number.format(refm);
		 return FM_Format;
	 }
	 
	 // ************ Calcul de la Resistance Comp Moyenne sur 6 valeurs ************
	 public static String ResComMoy(String v1, String v2, String v3, String v4, String v5, String v6) {
		 Float r11 = Float.parseFloat(v1);
		 Float r12 = Float.parseFloat(v2);
		 Float r21 = Float.parseFloat(v3);
		 Float r22 = Float.parseFloat(v4);
		 Float r31 = Float.parseFloat(v5);
		 Float r32 = Float.parseFloat(v6);
		 
		 
		 Float Rcmoy = (r11+r12+r21+r22+r31+r32)/6;
		 
		 NumberFormat d_number = NumberFormat.getNumberInstance(Locale.ENGLISH); //permet d'obtenir nombre a 2 chiffr apres virgul
		  d_number.setMaximumFractionDigits(2); 
		 String Rc_Format = d_number.format(Rcmoy);
		 
		 
		 return Rc_Format;
	 }
	 
	 
	 //****************calcul de l ecart type ****************
	    // ecart type 3 valeurs
	 public static String EcartType(String val1, String val2, String val3) {
		Float fval1 = Float.parseFloat(val1);
		Float fval2 = Float.parseFloat(val2);
		Float fval3 = Float.parseFloat(val3);
		   
		 Float val_moy = (fval1+fval2+fval3)/3;
		 double abs_moy1 =  Math.pow((Math.abs(fval1-val_moy)),2);
		 double abs_moy2 =  Math.pow((Math.abs(fval2-val_moy)),2);
		 double abs_moy3 =  Math.pow((Math.abs(fval3-val_moy)),2);
		 double sum = abs_moy1+abs_moy2+abs_moy3;
		 double sum_div_2 = sum/2;
		 double racine = Math.sqrt(sum_div_2);
		 
		 NumberFormat d_number = NumberFormat.getNumberInstance(Locale.ENGLISH); //permet d'obtenir nombre a 2 chiffr apres virgul
		  d_number.setMaximumFractionDigits(2);
		  
		 String ec = d_number.format(racine);
		 
		 return ec;
	 }
	 
	   // ecart type 6 valeur
   public static String EcartType(String val1, String val2, String val3, String val4, String val5, String val6) {
		 Float fval1 = Float.parseFloat(val1);
		Float fval2 = Float.parseFloat(val2);
		Float fval3 = Float.parseFloat(val3);
		Float fval4 = Float.parseFloat(val4);
		Float fval5 = Float.parseFloat(val5);
		Float fval6 = Float.parseFloat(val6);
		
		   
		 Float val_moy = (fval1+fval2+fval3+fval4+fval5+fval6)/6;
		 double abs_moy1 =  Math.pow((Math.abs(fval1-val_moy)),2);
		 double abs_moy2 =  Math.pow((Math.abs(fval2-val_moy)),2);
		 double abs_moy3 =  Math.pow((Math.abs(fval3-val_moy)),2);
		 double abs_moy4 =  Math.pow((Math.abs(fval4-val_moy)),2);
		 double abs_moy5 =  Math.pow((Math.abs(fval5-val_moy)),2);
		 double abs_moy6 =  Math.pow((Math.abs(fval6-val_moy)),2);
		 
		 double sum = abs_moy1+abs_moy2+abs_moy3+abs_moy4+abs_moy5+abs_moy6;
		 double sum_div_2 = sum/5;
		 double racine = Math.sqrt(sum_div_2);
		 
		 NumberFormat d_number = NumberFormat.getNumberInstance(Locale.ENGLISH); //permet d'obtenir nombre a 2 chiffr apres virgul
		 d_number.setMaximumFractionDigits(2);
		  
		 String ec = d_number.format(racine);
		 
		 return ec;
	 }  
   
  //**************** calul poucentage fiche de controle complete ************************

public static String Complete(DefaultTableModel DTM, JTable table, JTextField jtxt1, JTextField jtxt2, JTextField jtxt3) {
	 
	// nombre de donnée a remplir dans la table
	Double compteur =0.0;
	for(int li=0; li<table.getRowCount(); li++) { 	
		 String statut = String.valueOf(DTM.getValueAt(li,5)); //colonne statut
		 if (statut.equals("donnée a saisir") || statut.equals("CONFORME") || statut.equals("NC"))   {
			 compteur++;
		 }
	}
	
	for(int li=0; li<table.getRowCount(); li++) { 	
		 String statut = String.valueOf(DTM.getValueAt(li,7)); //colonne statut
		 if (statut.equals("donnée a saisir") || statut.equals("CONFORME") || statut.equals("NC"))   {
			 compteur++;
		 }
	}
	
	//nombre de donnée rempli dans la table
	Double compteur2=0.0;
	for(int li=0; li<table.getRowCount(); li++) { 	
		 String valeur = String.valueOf(DTM.getValueAt(li,2)); //colonne resultat
		 if (valeur=="null" || valeur.isEmpty())   {
		 } else {
			 compteur2++;
		 }
	}
	//pour savoir si il y a une NC
	int NCcount=0;
	for(int li2=0; li2<table.getRowCount(); li2++) { 	
		
		String statut = String.valueOf(DTM.getValueAt(li2,5)); //colonne resultat
		 if (statut.equals("NC"))   {
			NCcount++;
			}
		 } 

	//pour compter le nombre de valeur inscrite dans le 2nd controle
	if (NCcount>0) {
		for(int li=0; li<table.getRowCount(); li++) { 	
			 String valeur = String.valueOf(DTM.getValueAt(li,6)); //colonne statut
			 if (valeur=="null" || valeur.isEmpty())   {
			 } else {
				  compteur2++;
			 }
		}
	}
	
	//nombre de donnée rempli
	Double compteur3=0.0;
	if (jtxt1.getText().length()>0) {
		compteur3++;
	}
	if (jtxt2.getText().length()>0) {
		compteur3++;
	}
	if (jtxt3.getText().length()>0) {
		compteur3++;
	}
	
	
	//formule
	Double Pourcentage =  (compteur2+compteur3)/(compteur+3);
	//format affichage
	NumberFormat formatter = new DecimalFormat("#0.0 %");
	
	
	return formatter.format(Pourcentage);
 }
   
}
	 

