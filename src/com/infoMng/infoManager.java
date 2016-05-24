package com.infoMng;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.UIManager;

import com.infoMng.controller.ObserverInterfaceImpl;
import com.mattiaberretti.database.GestioneDB;

import view.ViewInterfaceImpl;
import view.interfaces.ObserverInterface;
import view.interfaces.ViewInterface;

public class infoManager {

	public static void main(String[] args){

	    	System.out.println("Cavolo juan la memorizzazione dell'utente corrente doveva essere la prima cosa");
			try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e){}
			
		    ViewInterface view = new ViewInterfaceImpl();
			ObserverInterface controller = new ObserverInterfaceImpl(view);
			controller.Start();
	}

}
