package com.mattiaberretti.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface GestioneDB {

	static GestioneDB generaControllore(){
		return new DataBase();
	}
	
	static void generaDB() throws ClassNotFoundException, SQLException{
		DataBase db = new DataBase();
		db.connetti();
		Colonna nome = new Colonna("Nome", tipoColonna.testo);
		Colonna cognome = new Colonna("Cognome", tipoColonna.testo);
		Colonna usernameUtente = new Colonna("Username", tipoColonna.testo);
		Colonna passwordUtente = new Colonna("Password", tipoColonna.testo);
		Colonna idUtentePrimaria = new Colonna("IDUtente", tipoColonna.intero, new attributiColonna[]{ attributiColonna.primaryKey, attributiColonna.autoincrement});
		db.creaTabella(new Colonna[]{idUtentePrimaria, nome, cognome, usernameUtente, passwordUtente}, "Utenti");
		
		Colonna mail = new Colonna("Mail", tipoColonna.testo);
		Colonna telefono = new Colonna("Telefono", tipoColonna.testo);
		Colonna IDClientePrimaria = new Colonna("IDCliente", tipoColonna.intero, new attributiColonna[]{attributiColonna.primaryKey, attributiColonna.autoincrement});
		Colonna IDUtente = new Colonna("IDUtente", tipoColonna.intero);
		
		db.creaTabella(new Colonna[]{IDClientePrimaria, nome, cognome, mail, telefono, IDUtente}, "Clienti");
		
		Colonna idProdottoPrimario = new Colonna("IDProdotto", tipoColonna.intero, new attributiColonna[]{attributiColonna.primaryKey, attributiColonna.autoincrement});
		Colonna prezzoAcquisto = new Colonna("PrezzoAcquisto", tipoColonna.decimal);
		Colonna prezzoVendita = new Colonna("PrezzoVendita", tipoColonna.decimal);
		
		db.creaTabella(new Colonna[]{idProdottoPrimario, nome, cognome, prezzoAcquisto, prezzoVendita, IDUtente}, "Prodotti");
		
		Colonna idMovimentoPrimario = new Colonna("IDMovimento", tipoColonna.intero, new attributiColonna[]{attributiColonna.primaryKey, attributiColonna.autoincrement});
		Colonna idProdotto = new Colonna("IDProdotto", tipoColonna.intero);
		Colonna data = new Colonna("Data", tipoColonna.data);
		Colonna idVendita = new Colonna("IDVendita", tipoColonna.intero);
		Colonna idAcquisto = new Colonna("IDAcquisto", tipoColonna.intero);
		Colonna prezzo = new Colonna("Prezzo", tipoColonna.decimal);
		Colonna iva = new Colonna("IVA", tipoColonna.intero);
		Colonna descrizione = new Colonna("Descrizione", tipoColonna.testo);
		
		db.creaTabella(new Colonna[]{idMovimentoPrimario, idProdotto, data, idVendita, idAcquisto, prezzo, iva, descrizione}, "Movimenti");
		db.disconnetti();
	}
	
	public void connetti() throws SQLException, ClassNotFoundException;

	public void disconnetti() throws SQLException;

	public List<Map<String, Object>> eseguiLettura(String[] campi, String tabella) throws SQLException;
	
	public void creaTabella(GestioneDB.Colonna[] colonne, String nomeTabella) throws SQLException;
	
	void inserisciRecord(String nomeTabella, Map<String, Object> colonnaValore) throws SQLException;
	
	void aggiornaTabella(String nomeTabella, Map<String, Object> colonnaValore, String nomeChiave, Object[] valoriConfronto) throws SQLException;
	
	public enum tipoColonna{
		testo("TEXT"),
		decimal("DOUBLE"),
		intero("INTEGER"),
		data("DATE"),
		datetime("DATETIME");
		
		private final String tipo;
		
		tipoColonna(String tipo){
			this.tipo = tipo;
		}
		
		@Override
		public String toString() {
			return this.tipo;
		}
	}
	
	public enum attributiColonna{
		primaryKey("PRIMARY KEY"),
		autoincrement("AUTOINCREMENT");
		
		private final String testo;
		
		private attributiColonna(String testo) {
			this.testo = testo;
		}
		
		@Override
		public String toString() {
			return this.testo;
		}
	}
	
	public static class Colonna{
		private String nome;
		private tipoColonna tipo;
		private List<attributiColonna> attributi;
		
		public Colonna(String nome, tipoColonna tipo){
			this.nome = nome;
			this.tipo = tipo;
			this.attributi = new ArrayList<>();
		}
		
		public Colonna(String nome, tipoColonna tipo, attributiColonna[] attributi){
			this(nome, tipo);
			for(attributiColonna tmp : attributi){
				this.attributi.add(tmp);
			}
		}
		
		public String getNome(){
			return this.nome;
		}
		
		public tipoColonna getTipo(){
			return this.tipo;
		}
		
		public void addAttribute(attributiColonna nuovo){
			if (!this.attributi.contains(nuovo))
			{
				this.attributi.add(nuovo);
			}
		}
		
		public void rimuoviAttributo(attributiColonna cancellare){
			this.attributi.remove(cancellare);
		}
		
		@Override
		public String toString() {
			String ritorno = String.format("%s %s", this.nome, this.tipo.toString());
			for(attributiColonna tmp : this.attributi){
				ritorno = String.format("%s %s", ritorno, tmp.toString());
			}
			return ritorno;
		}
	}
}