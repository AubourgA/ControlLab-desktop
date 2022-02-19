package com.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class myMouseListener implements MouseListener {
int i=0;
	
	 public void mouseClicked(MouseEvent event)
	   {  
	     i++;
	    
		 System.out.println("Point n° :"+i+"\n"
	                        + "x = " + event.getX() + "\n"+"y = " + event.getY());      
	   }

	   public void mouseEntered(MouseEvent event)
	   {  //System.out.println("Mouse entered. x = " 
	        //                  + event.getX() + " y = " + event.getY());
	   }

	   public void mouseExited(MouseEvent event)
	   { // System.out.println("Mouse exited. x = " 
	       //                   + event.getX() + " y = " + event.getY());
	   }

	   public void mousePressed(MouseEvent event)
	   {  //System.out.println("Mouse pressed. x = " 
	        //                  + event.getX() + " y = " + event.getY());
	   }

	   public void mouseReleased(MouseEvent event)
	   {//  System.out.println("Mouse released. x = " 
	      //                    + event.getX() + " y = " + event.getY());
	   }
	 }


