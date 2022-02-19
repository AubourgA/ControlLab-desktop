package com.gui;

import java.io.File;

public class definition {

	//fentre principale 
	static int WLarge = 1600;
	 static int WHaut = 900;
	 
	 
	 //fenetre secondaire
	 static int SLarge = 1005;
	 static int SHaut = 660;
	 
	 //fenetre tertaire
	 static int BEM_Large = 1200;
     static int BEM_Haut =  700;
	 
   //fenetre petite
   	 static int SS_Large = 400;
        static int SS_Haut =  300;
        
        //fenetre tresPetite
        static int TP_Large = 400;
        static int TP_Haut =  150;
        
        
        
	 //redimension image
	 static int RPLarge = WLarge;
	 static int RSLarge = SLarge;
	 
	 //dimension des jscrollpane
	 static int s_Large = 900;  //BaseMP  onglet 1 table 1
	 static int s_Haut = 450;
	 
	 
	 static int scp2_Large = 720;  //BaseMP  onglet 2 table 1
	 static int scp2_Haut = 325;
	 
	 static int scp3_Large = 250; //base MP onglet 2table 2
	 static int scp3_Haut = 325;
	 
	 
	 static int scp4_Large = 550; //prime - CARD2
	 static int scp4_Haut = 600;
	 
	 static int scp7_Large = 400; //prime - CARD4
	 static int scp7_Haut = 400;
	 
	 static int scp_10_Large = 1025; //Base_EM - tableau essai
	 static int scp_10_Haut = 425;
	 
	 static int scp_jour_Large = 400; // prime CARD 5
	 static int scp_jour_Haut = 300;
	 
	 
	 static int scp_Large = 720; //AjoutPF
	 static int scp_Haut = 250;
	 
	 static int tablesp_Large = 300; //creertmp
	 static int tablesp_Haut = 150;
	 
	 
	 //panel
	 static int S1_Large = 600; // viewtab_AjoutPF
	 static int S1_Haut = 300;
	 
	 static int E_Large = 300; // viewEssai_AjoutPF
	 static int E_Haut = 0;
	 
	 static int O_Large = 400; // viewProduct_AjoutPF
	 static int O_Haut = 0;
	 
	 static int S2_Large = 0; // viewValid_AjoutPF
	 static int S2_Haut = 200;
	 
	 static int   MS_Large = 0;//mainsouth_prime
     static int MS_Haut = 75;	 
     
     
     
     //dimension composant
     // ****** bouton
     static int butP_Large = 45; //menu rapide_prime
     static int butEM_Large = 160; //bouton Essai Mecanique _BASE EM
     static int butEM_Haut = 40; //bouton Essai Mecanique _BASE EM
     static int butAJ_Large = 180; // bouton classe AJOUT
     static int butAJ_Haut = 35; // bouton class AJOUT
     
     //adresse image fond
     static String URL_FONDPRIME ="/img/fondprime.png";
     static String URL_FOND="/img/fond_C.png";
     static String URL_FOND_EM="/img/FondEM.png";
     static String URL_Logo="/img/logo.png";
     static String URL_CARD="img/CARD4.png";
     static String URL_FONDTRAMEMP ="/img/FondTrameMP.png";
     static String URL_FONDTRAMEPF ="/img/FondTramePF.png";
     static String URL_FONDLISTE ="/img/fond_liste.png";
     static String URL_FONDTPF ="/img/Titre_basePF.png";
     static String URL_FONDTMP ="/img/Titre_baseMP.png";
     static String URL_FONDBASE ="/img/FondBase.png";
     static String URL_TITRETRAME ="/img/titre_trame.png";
     static String URL_TITREARCHIVE ="/img/titre_archive.png";
     static String URL_FONDPRIME4 ="/img/prime_CL4.png";
     
     //image bouton
     static String URL_ANNULER ="/img/annuler.png";
     static String URL_VALIDER ="/img/valider.png";
     static String URL_VALIDER_P ="/img/valider_16x16.png";
     static String URL_AJOUTER ="/img/ajouter.png";
     static String URL_AJOUTER_P ="/img/ajout_16x16.png";
     static String URL_SUPPRIMER ="/img/supprimer.png";
     static String URL_EDITER ="/img/editer.png";
     static String URL_MODIFIER ="/img/modifier.png";
     static String URL_RECHERCHER ="/img/rechercher.png";
     static String URL_RECHERCHER_P ="/img/rechercher_16x16.png";
     static String URL_PRINT ="/img/print.png";
     static String URL_XLS ="/img/icon_xls.png";
     static String URL_PRINT_P ="/img/print_16x16.png"; 
     static String URL_TRANSFERT ="/img/transferer.png";
     static String URL_HISTORIQUE ="/img/historique.png";
     static String URL_CALCULER ="/img/calculer.png";
     static String URL_ARCHIVER ="/img/archive.png";
     static String URL_GRAPH_P ="/img/graph_16x16.png"; //
     
     static String URL_CONTROLEMP ="/img/newMP.png";
     static String URL_CONTROLEPF ="/img/newPF.png";
     static String URL_BASEMP ="/img/base_MP.png";
     static String URL_BASEPF ="/img/base_PF.png";
     static String URL_BASEEM ="/img/base_EM.png";
     static String URL_RAFRAICHIR ="/img/ud.png";
     static String URL_RAFRAICHIR_P ="/img/ud_16x16.png"; 
     
     static String URL_RECUPDATA ="/img/recup_data.png";
     static String URL_RECUPDATA_P ="/img/recupdatap_p.png";
     static String URL_CONTROLE ="/img/check.png";
     static String URL_MISEENATTENTE ="/img/mea.png";

     //gneration pdf
     static String getPathDesktop =System.getProperty("user.home")+File.separator+"Desktop";
    
}
