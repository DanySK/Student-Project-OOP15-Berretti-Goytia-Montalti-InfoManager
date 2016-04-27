package com.mattiaberretti.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GestioneDB {

	/***
	 * genera un'instanza del controllore del database
	 * @return
	 */
	static GestioneDB generaControllore(){
		return new DataBase();
	}
	
	/***
	 * genera il database sqlite per la conservazione dei dati
	 * @throws ClassNotFoundException
	 * impossibile trovare la libreria di sqlite
	 * @throws SQLException
	 * errore durante la creazione delle tabelle
	 */
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
		
		db.creaTabella(new Colonna[]{idProdottoPrimario, nome, prezzoAcquisto, prezzoVendita, IDUtente}, "Prodotti");
		
		Colonna idMovimentoPrimario = new Colonna("IDMovimento", tipoColonna.intero, new attributiColonna[]{attributiColonna.primaryKey, attributiColonna.autoincrement});
		Colonna idProdotto = new Colonna("IDProdotto", tipoColonna.intero);
		Colonna data = new Colonna("Data", tipoColonna.data);
		Colonna idRicevuta = new Colonna("IDRicevuta", tipoColonna.intero);
		Colonna prezzo = new Colonna("Prezzo", tipoColonna.decimal);
		Colonna iva = new Colonna("IVA", tipoColonna.intero);
		Colonna descrizione = new Colonna("Descrizione", tipoColonna.testo);
		Colonna quantita = new Colonna("Quantita", tipoColonna.intero);
		
		db.creaTabella(new Colonna[]{idMovimentoPrimario, idProdotto, data, idRicevuta, prezzo, iva, descrizione, quantita}, "Movimenti");
		db.disconnetti();
	}
	
	/***
	 * connette il controllore al database
	 * @throws SQLException
	 * errore durante la connessione al database
	 * @throws ClassNotFoundException
	 * impossibile trovare la libreria di sqlite
	 */
	public void connetti() throws SQLException, ClassNotFoundException;

	/***
	 * disconnette il controllore dal database
	 * @throws SQLException
	 * errore durante la disconnessione
	 */
	public void disconnetti() throws SQLException;

	/***
	 * legge un'intera tabella di un database
	 * @param campi
	 * elenco dei campi da leggere
	 * @param tabella
	 * nome della tabella da interrogare
	 * @return
	 * una lista di mappe con chiave delle stringe e con valori degli Object
	 * @throws SQLException
	 * errore durante la lettura della tabella.
	 * Controllare i nomi dei campi o il nome della tabella
	 */
	public List<Map<String, Object>> eseguiLettura(String[] campi, String tabella) throws SQLException;
	
	/***
	 * crea una tabella all'interno del database
	 * @param colonne
	 * elenco delle colonne da creare
	 * @param nomeTabella
	 * nome della tabella
	 * @throws SQLException
	 * erorre durante la creazione della tabella
	 */
	public void creaTabella(GestioneDB.Colonna[] colonne, String nomeTabella) throws SQLException;
	
	/***
	 * inserisce un nuovo record all'interno della tabella
	 * @param nomeTabella
	 * nome della tabella che conterrà il record
	 * @param colonnaValore
	 * una mappa di String e Object 
	 * conterra come chiavi i nomi delle colonne e come valori il valore da inserire all'interno di tale colonna
	 * @throws SQLException
	 * errore durante l'aggiunta dei dati
	 */
	void inserisciRecord(String nomeTabella, Map<String, Object> colonnaValore) throws SQLException;
	
	/***
	 * aggiorna un record di una tabella
	 * @param nomeTabella
	 * nome della tabella
	 * @param colonnaValore
	 * mappa contenente i nomi delle colonne come chiavi e come valori il nuovo contenuto di tali colonne
	 * @param nomeChiave
	 * nome della colonna che sarà usata per selezionare la colonna
	 * @param valoriConfronto
	 * i valori che può assumere la colonna chiave per essere selezionata (viene usata la logica OR)
	 * @throws SQLException
	 * errore durante l'aggiornamento
	 */
	void aggiornaTabella(String nomeTabella, Map<String, Object> colonnaValore, String nomeChiave, Object[] valoriConfronto) throws SQLException;
	
	/***
	 * elimina un record dalla tabella
	 * in caso di chiave e valore nulli la tabella viene svuotata
	 * @param nomeTabella
	 * nome della tabella
	 * @param nomeChiave
	 * chiave usata per l'eliminazione del record
	 * @param valoreConfronto
	 * valore per selezionare la chiave
	 * @throws SQLException
	 * errore durante l'eliminazione
	 */
	void eliminaRecord(String nomeTabella, Optional<String> nomeChiave, Optional<Object> valoreConfronto) throws SQLException;
	
	/***
	 * tipi che una colonna può assumere
	 * @author mattiaberretti
	 *
	 */
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
	
	/***
	 * attributi assegnabili ad una colonna
	 * @author mattiaberretti
	 *
	 */
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
	
	/***
	 * classe colonna,
	 * essa contiene tutti i dati necessari per creare una colonna
	 * @author mattiaberretti
	 *
	 */
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