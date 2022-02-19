package com.gui;


import javax.swing.SwingUtilities;

import connexion.BD.connexion;


public class execute {

	
	public  prime p;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
          SwingUtilities.invokeLater(new Runnable() {
        	  public void run() {
        		  new connexion();
        			prime p =new prime();
        			p.fen.setVisible(true);
        			Alerte.PrimeAlerte();
        	           }
          });
          }
}
		 
		
		
	


