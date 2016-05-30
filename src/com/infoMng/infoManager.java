package com.infoMng;

import javax.swing.UIManager;
import com.infoMng.controller.ObserverInterfaceImpl;
import view.ViewInterfaceImpl;
import view.interfaces.ObserverInterface;
import view.interfaces.ViewInterface;

public class infoManager {

	public static void main(String[] args){

	    	
	    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e){}
			
		    ViewInterface view = new ViewInterfaceImpl();
			ObserverInterface controller = new ObserverInterfaceImpl(view);
			controller.Start();
	}

}
