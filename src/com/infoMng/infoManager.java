package com.infoMng;

import java.sql.SQLException;

import com.mattiaberretti.database.GestioneDB;

public class infoManager {

	public static void main(String[] args) {
		try {
			GestioneDB.generaDB();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
