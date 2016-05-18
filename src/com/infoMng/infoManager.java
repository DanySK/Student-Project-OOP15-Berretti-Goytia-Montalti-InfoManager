package com.infoMng;

import java.awt.EventQueue;
import java.sql.SQLException;

import com.mattiaberretti.database.GestioneDB;

public class infoManager {

	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					try {
						GestioneDB.generaDB();//TODO: da rimuovere per le prossime versioni
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new view.LoginGUI(null).setStatus(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}

}
