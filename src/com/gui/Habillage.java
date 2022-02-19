package com.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


import javax.swing.JPanel;

//Cette Class défini les images de fond des fenetre de l'application
//chaque fenetre fait appel a cette classe pour afficher son theme de fond

// Une class et un methode sont utiles pour afficher un fond sur une fenetre

public class Habillage {

	// CLASS POUR LES FONDS ECRAN POUR L ENSEMBLE DES CLASS
	
	private Image  img1, img_base,img_BMP1, img_BPF1,img_titre,img_Archive,img_control,img_BEM,img_TMP,img_TPF,img_Modifier_TMP,img_Modifier_TPF;
	private Image img_sW,img_cl4;
	
	public Habillage() {
		Prime();
		imgBaseMP();
		imgBasePF();
		
		imgTitreTrame();
		imgTitreArchive();
		img_Control();
		imgBaseEM();
		imgTMP();
		imgTPF();
		imgModifer_TMP();
		imgModifer_TPF();
		smallWindow();
		paintCL4();
		imgBase();
	}
	
	// ***********************************methode d'affichage
	private void Prime() {
		//affichage image de fond
		 Toolkit kit = Toolkit.getDefaultToolkit();
		 img1 = kit.getImage(getClass().getResource(definition.URL_FONDPRIME));
		 img1 = img1.getScaledInstance(1593, -1, Image.SCALE_SMOOTH);
}
	//creer trame mp
	private void imgBaseMP() {
		//affichage image de fond
		 Toolkit kit = Toolkit.getDefaultToolkit();
		 img_BMP1 = kit.getImage(getClass().getResource(definition.URL_FONDTMP));
		 img_BMP1 = img_BMP1.getScaledInstance(1000, -1, Image.SCALE_SMOOTH); 
	 }
	//class creer trame pf
	private void imgBasePF() {
		//affichage image de fond
		 Toolkit kit = Toolkit.getDefaultToolkit();
		 img_BPF1 = kit.getImage(getClass().getResource(definition.URL_FONDTPF));
		 img_BPF1 = img_BPF1.getScaledInstance(1000, -1, Image.SCALE_SMOOTH); 
	 }
	
	private void imgBase() {
		//affichage image de fond
		 Toolkit kit = Toolkit.getDefaultToolkit();
		 img_base = kit.getImage(getClass().getResource(definition.URL_FONDBASE));
		 img_base = img_base.getScaledInstance(998, -1, Image.SCALE_SMOOTH); 
	 }
	
	
	
	private void imgTitreTrame() {
		//affichage image de fond
		 Toolkit kit = Toolkit.getDefaultToolkit();
		 img_titre = kit.getImage(getClass().getResource(definition.URL_TITRETRAME));
		 img_titre = img_titre.getScaledInstance(1000, -1, Image.SCALE_SMOOTH); 
	 }
	
	private void imgTitreArchive() {
		//affichage image de fond
		 Toolkit kit = Toolkit.getDefaultToolkit();
		 img_Archive = kit.getImage(getClass().getResource(definition.URL_TITREARCHIVE));
		 img_Archive = img_Archive.getScaledInstance(1000, -1, Image.SCALE_SMOOTH); 
	 }
	
	
	private void img_Control() {
		//affichage image de fond
		 Toolkit kit = Toolkit.getDefaultToolkit();
		 img_control = kit.getImage(getClass().getResource(definition.URL_FOND));
		 img_control = img_control.getScaledInstance(998, -1, Image.SCALE_SMOOTH); 
	 }
	
	private void imgBaseEM() {
		//affichage image de fond
		 Toolkit kit = Toolkit.getDefaultToolkit();
		 img_BEM = kit.getImage(getClass().getResource(definition.URL_FOND_EM));
		 img_BEM = img_BEM.getScaledInstance(1200, -1, Image.SCALE_SMOOTH); 
	 }
	
	
	private void imgTMP() {
		//affichage image de fond
		 Toolkit kit = Toolkit.getDefaultToolkit();
		 img_TMP = kit.getImage(getClass().getResource(definition.URL_FONDTRAMEMP));
		 img_TMP = img_TMP.getScaledInstance(1000, -1, Image.SCALE_SMOOTH); 
	 }
	
	private void imgTPF() {
		//affichage image de fond
		 Toolkit kit = Toolkit.getDefaultToolkit();
		 img_TPF = kit.getImage(getClass().getResource(definition.URL_FONDTRAMEPF));
		 img_TPF = img_TPF.getScaledInstance(1000, -1, Image.SCALE_SMOOTH); 
	 }
	
	private void imgModifer_TMP() {
		//affichage image de fond
		 Toolkit kit = Toolkit.getDefaultToolkit();
		 img_Modifier_TMP = kit.getImage(getClass().getResource(definition.URL_FONDTRAMEMP));
		 img_Modifier_TMP = img_Modifier_TMP.getScaledInstance(1000, -1, Image.SCALE_SMOOTH); 
	 }
	
	private void imgModifer_TPF() {
		//affichage image de fond
		 Toolkit kit = Toolkit.getDefaultToolkit();
		 img_Modifier_TPF = kit.getImage(getClass().getResource(definition.URL_FONDTRAMEPF));
		 img_Modifier_TPF = img_Modifier_TPF.getScaledInstance(1000, -1, Image.SCALE_SMOOTH); 
	 }
	
	 private void smallWindow() {
			//affichage image de fond
			 Toolkit kit = Toolkit.getDefaultToolkit();
			 img_sW = kit.getImage(getClass().getResource(definition.URL_FONDLISTE));
			 img_sW = img_sW.getScaledInstance(600, -1, Image.SCALE_SMOOTH); 
		 }
	 
	 private void paintCL4() {
			//affichage image de fond
			 Toolkit kit = Toolkit.getDefaultToolkit();
			 img_cl4 = kit.getImage(getClass().getResource(definition.URL_FONDPRIME4));
			 img_cl4 = img_cl4.getScaledInstance(1362, -1, Image.SCALE_SMOOTH); 
		 }
	 
	 

	
	//***********Class Panel ********************************************************
	 public class PrimePanel extends JPanel {
			private static final long serialVersionUID = 1L;
			
			
			public void paintComponent(Graphics g)
		        {
		            //  **** don't forget to call the super method first
		            super.paintComponent(g);
		            
		            g.drawImage(img1,0,0,this);
		        
		        }} 
	//class creer trame
	 public class BaseMPPanel extends JPanel {

			private static final long serialVersionUID = 1L;
            
				public void paintComponent(Graphics g)
		        {
		            //  **** don't forget to call the super method first
		            super.paintComponent(g);
		            g.drawImage(img_BMP1,0,0,this);
		        } } 
	 
	 
	 
	 
	 //classe creer trame
	 public class BasePFPanel extends JPanel {
			private static final long serialVersionUID = 1L;
				public void paintComponent(Graphics g)
		        {
		            //  **** don't forget to call the super method first
		            super.paintComponent(g);
		            
		            g.drawImage(img_BPF1,0,0,this);
		        
		        }} 
	 //class base MP & PF + trame + archive trame 
	 public class BasePanel extends JPanel {
			private static final long serialVersionUID = 1L;
				public void paintComponent(Graphics g)
		        {
		            //  **** don't forget to call the super method first
		            super.paintComponent(g);
		            
		            g.drawImage(img_base,0,0,this);
		        
		        }} 
	 //CLASS BASE TRAME
	 public class TitreTramePanel extends JPanel {
			private static final long serialVersionUID = 1L;
				public void paintComponent(Graphics g)
		        {
		            //  **** don't forget to call the super method first
		            super.paintComponent(g);
		            
		            g.drawImage(img_titre,0,0,this);
		        
		        }} 
	 // CLASS BASEARCHIVETRAME
	 public class TitreArchivePanel extends JPanel {
			private static final long serialVersionUID = 1L;
				public void paintComponent(Graphics g)
		        {
		            //  **** don't forget to call the super method first
		            super.paintComponent(g);
		            
		            g.drawImage(img_Archive,0,0,this);
		        
		        }} 
	 
	 //class AJOUTMP & AJOUTPF
	 public class ControlPanel extends JPanel {

			private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g)
		        {
		            //  **** don't forget to call the super method first
		            super.paintComponent(g);
		            g.drawImage(img_control,0,0,this);
		        } }
	 
	 //class BASE_EM
	 public class BaseEMPanel extends JPanel {

			private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g)
		        {
		            //  **** don't forget to call the super method first
		            super.paintComponent(g);
		            g.drawImage(img_BEM,0,0,this);
		        } } 
	 

	 //class CREER_TMP
	 public class CreerTMP_Panel extends JPanel {

			private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g)
		        {
		            //  **** don't forget to call the super method first
		            super.paintComponent(g);
		            g.drawImage(img_TMP,0,0,this);
		        } } 
	 //class CREER_TPF
	 public class CreerTPF_Panel extends JPanel {

			private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g)
		        {
		            //  **** don't forget to call the super method first
		            super.paintComponent(g);
		            g.drawImage(img_TPF,0,0,this);
		        } } 
	 //CLASS MODIFIER MP
	 public class ModifierMP_Panel extends JPanel {

			private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g)
		        {
		            //  **** don't forget to call the super method first
		            super.paintComponent(g);
		            g.drawImage(img_Modifier_TMP,0,0,this);
		        } } 
	 
	 public class ModifierPF_Panel extends JPanel {

			private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g)
		        {
		            //  **** don't forget to call the super method first
		            super.paintComponent(g);
		            g.drawImage(img_Modifier_TPF,0,0,this);
		        } } 
	 
	 //class Liste (avlot, sslot, cap
	 public class SmallWindow extends JPanel {

			private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g)
		    {
		        //  **** don't forget to call the super method first
		        super.paintComponent(g);
		        g.drawImage(img_sW,0,0,this);
		    } } 
	 
	 public class Panel_CL4 extends JPanel {

			private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g)
		    {
		        //  **** don't forget to call the super method first
		        super.paintComponent(g);
		        g.drawImage(img_cl4,0,0,this);
		    } } 
	 
}