package com.mattiaberretti.prodotti;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mattiaberretti.database.GestioneDB;
import com.mattiaberretti.utenti.IUtente;

public interface IProdotto {

	/***
	 * ricava l'elenco dei prodotti assegnati ad un determinato utente
	 * @return
	 * una lista di prodotti
	 * @throws ClassNotFoundException
	 * libreria di sqlite non trovata
	 * @throws SQLException
	 * errore durante l'interrogazione del database
	 */
	static List<IProdotto> elencoProdotti() throws ClassNotFoundException, SQLException{
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();
		Integer idUtente = IUtente.utenteCorrente.getUtenteCorrente().get().getIDUtente();
		List<Map<String, Object>> valori = db.eseguiLettura(new String[]{"IDProdotto", "Nome", "PrezzoAcquisto", "PrezzoVendita", "IDUtente"}, "Prodotti").stream()
				.filter(w -> w.get("IDUtente").equals(idUtente))
				.collect(Collectors.toList());
		db.disconnetti();
		
		List<IProdotto> ritorno = new ArrayList<>();
		
		for (Map<String, Object> val : valori) {
			ritorno.add(new Prodotto((Integer)val.get("IDProdotto"),
					(String)val.get("Nome"),
					(Double)val.get("PrezzoAcquisto"),
					(Double)val.get("PrezzoVendita")));
		}
		
		return ritorno;
	}
	
	Integer getIDProdotto();

	String getNome();

	Double getPrezzoAcquisto();

	Double getPrezzoVendita();

	void setNome(String nome);

	void setPrezzoAcquisto(Double prezzoAcquisto);

	void setPrezzoVendita(Double prezzoVendita);

	/**
	 * ottiene la quantita di un prodotto mediante l'elaborazione dei movimenti
	 * @return
	 * la quantita attuale di un prodotto
	 * @throws ClassNotFoundException
	 * impossibile caricare la libreria di sqlite
	 * @throws SQLException
	 * errore durante l'interrogazione del database
	 */
	Integer quantita() throws ClassNotFoundException, SQLException;

	/**
	 * aggiorna il record di un prodotto
	 * @throws ClassNotFoundException
	 * impossibile caricare la libreria di sqlite
	 * @throws SQLException
	 * errore durante l'utilizzo del database
	 */
	void salva() throws ClassNotFoundException, SQLException;
	
	
	/**
	 * Builder per un nuovo prodotto
	 * @author mattiaberretti
	 *
	 */
	public class Builder{
		private String nome;
		private Double prezzoAcquisto;
		private Double prezzoVendita;
		private Integer quantitaIniziale;
		
		public Builder(String nome){
			this.nome = nome;
			this.prezzoAcquisto = 0.0;
			this.prezzoVendita = 0.0;
			this.quantitaIniziale = 0;
		}
		
		public IProdotto.Builder setNome(String nome){
			this.nome = nome;
			return this;
		}
		
		public IProdotto.Builder setPrezzoAcquisto(Double prezzo){
			this.prezzoAcquisto = prezzo;
			return this;
		}
		
		public IProdotto.Builder setPrezzoVendita(Double prezzo){
			this.prezzoVendita = prezzo;
			return this;
		}
		
		public IProdotto.Builder setQuantitaIniziale(Integer quantita) {
			this.quantitaIniziale = quantita;
			return this;
		}
		
		/**
		 * costruisce un nuovo prodotto e lo salva all'interno del database.
		 * se impostata una quantita iniziale la salva come un nuovo movimento privo di riferimento ad acquisti o vendite
		 * @return
		 * il nuovo prodotto
		 * @throws ClassNotFoundException
		 * impossibile trovare la libreria di sqlite
		 * @throws SQLException
		 * impossibile collegarsi al database
		 */
		public IProdotto build() throws ClassNotFoundException, SQLException{
			GestioneDB db = GestioneDB.generaControllore();
			db.connetti();
			Map<String, Object> valori = new HashMap<>();
			valori.put("Nome", this.nome);
			valori.put("PrezzoAcquisto", this.prezzoAcquisto);
			valori.put("PrezzoVendita", this.prezzoVendita);
			valori.put("IDUtente", IUtente.utenteCorrente.getUtenteCorrente().get().getIDUtente());
			
			db.inserisciRecord("Prodotti", valori);
			
			Integer idProdotto = db.eseguiLettura(new String[] {"IDProdotto"}, "Prodotti").stream()
					.mapToInt(w -> (Integer)w.get("IDProdotto"))
					.max().getAsInt();
			
			IProdotto ritorno = new Prodotto(idProdotto, this.nome, this.prezzoAcquisto, this.prezzoVendita);
			
			valori = new HashMap<>();
			valori.put("IDProdotto", idProdotto);
			valori.put("Quantita", this.quantitaIniziale);
			valori.put("Descrizione", "Quantita iniziale");
			db.inserisciRecord("Movimenti", valori);
			
			db.disconnetti();
			return ritorno;
		}
	}

}