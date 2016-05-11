package com.infoMng;

import java.awt.EventQueue;
import java.sql.SQLException;

import com.mattiaberretti.database.GestioneDB;

import view.Login;

public class infoManager {

	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					try {
						GestioneDB.generaDB();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new view.Login().setStatus(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}

}
