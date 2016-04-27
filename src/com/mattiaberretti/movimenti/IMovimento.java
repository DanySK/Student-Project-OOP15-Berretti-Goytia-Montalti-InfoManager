package com.mattiaberretti.movimenti;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mattiaberretti.database.GestioneDB;
import com.mattiaberretti.prodotti.IProdotto;

public interface IMovimento {

	static List<IMovimento> movimentiGlobali() throws ClassNotFoundException, SQLException{
		List<IMovimento> ritorno = new ArrayList<>();
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();
		
		String[] campi = {"IDMovimento", "IDProdotto", "Data", "IDRicevuta", "Prezzo", "IVA", "Descrizione", "Quantita" };
		db.eseguiLettura(campi, "Movimenti").stream()
		.forEach(d -> {
			Integer idMovimento = (Integer)d.get("IDMovimento");
			Integer idProdotto = (Integer)d.get("IDProdotto");
			Date data = Date.valueOf((String)d.get("Data"));
			Integer idRicevuta = (Integer)d.get("IDRicevuta");
			Double prezzo = (Double)d.get("Prezzo");
			Integer iva = (Integer)d.get("IVA");
			String descrizione = (String)d.get("Descrizione");
			Integer quantita = (Integer)d.get("Quantita");
			
			if(quantita > 0){
				ritorno.add(new Acquisto(idMovimento, quantita, descrizione, idProdotto, data, prezzo, iva, idRicevuta));
			}
			else{
				ritorno.add(new Vendita(idMovimento, -quantita, descrizione, idProdotto, data, prezzo, iva, idRicevuta));
			}
		});
		
		db.disconnetti();
		
		return ritorno;
	}
	
	static List<IMovimento> movimentiFromProdotto(IProdotto prodotto) throws ClassNotFoundException, SQLException{
		/*
		 *togliere il commento se per caso non funziona lo stream sotto 
		List<IMovimento> ritorno = new ArrayList<>();
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();
		
		String[] campi = {"IDMovimento", "IDProdotto", "Data", "IDRicevuta", "Prezzo", "IVA", "Descrizione", "Quantita" };
		db.eseguiLettura(campi, "Movimenti").stream()
		.filter(w -> w.get("IDProdotto").equals(prodotto.getIDProdotto()))
		.forEach(d -> {
			Integer idMovimento = (Integer)d.get("IDMovimento");
			Integer idProdotto = (Integer)d.get("IDProdotto");
			Date data = Date.valueOf((String)d.get("Data"));
			Integer idRicevuta = (Integer)d.get("IDRicevuta");
			Double prezzo = (Double)d.get("Prezzo");
			Integer iva = (Integer)d.get("IVA");
			String descrizione = (String)d.get("Descrizione");
			Integer quantita = (Integer)d.get("Quantita");
			
			if(quantita > 0){
				ritorno.add(new Acquisto(idMovimento, quantita, descrizione, idProdotto, data, prezzo, iva, idRicevuta));
			}
			else{
				ritorno.add(new Vendita(idMovimento, -quantita, descrizione, idProdotto, data, prezzo, iva, idRicevuta));
			}
		});
		
		db.disconnetti();
		
		return ritorno;*/
		return movimentiGlobali().stream()
				.filter(m -> m.getIdProdotto().equals(prodotto.getIDProdotto()))
				.collect(Collectors.toList());
	}
	
	Integer getIdMovimento();

	Integer getQuantita();

	String getDescrizione();

	Integer getIdProdotto();

	void setQuantita(Integer quantita);

	void setDescrizione(String descrizione);

	void setIdProdotto(Integer idProdotto);

	boolean isAcquisto();

	Date getData();

	Double getPrezzo();

	Integer getIVA();

	Integer getIdRicevuta();

	void setData(Date data);

	void setPrezzo(Double prezzo);

	void setIVA(Integer iVA);

	void setIdRicevuta(Integer idRicevuta);

	//TODO: modificare il tipo per il ritorno di una ricevuta
	void getRicevuta() throws SQLException, ClassNotFoundException;

	void salva() throws SQLException, ClassNotFoundException;

	void elimina() throws SQLException, ClassNotFoundException;
	
	public class Builder{
		private IProdotto prodotto;
		private boolean isVendita;
		private Integer quantita;
		private Integer IVA;
		private Date data;
		private Double prezzo;
		private Integer idRicevuta;
		private String descrizione;
		
		public Builder(IProdotto prodotto, boolean isVendita, Integer idRicevuta){
			this.prodotto = prodotto;
			this.isVendita = isVendita;
			this.IVA = 20;
			this.prezzo = isVendita ? prodotto.getPrezzoVendita() : prodotto.getPrezzoAcquisto();
			this.idRicevuta = idRicevuta;
			this.quantita = 1;
			Calendar cal = Calendar.getInstance();
			this.data = new Date(cal.getTimeInMillis()); //non penso che funzioni dovrebbe tornare la data corrente
			this.descrizione = "";
		}

		public Builder setQuantita(Integer quantita) {
			this.quantita = quantita;
			return this;
		}

		public Builder setIVA(Integer iVA) {
			IVA = iVA;
			return this;
		}

		public Builder setData(Date data) {
			this.data = data;
			return this;
		}

		public Builder setPrezzo(Double prezzo) {
			this.prezzo = prezzo;
			return this;
		}
		
		public Builder setDescrizione(String descrizione){
			this.descrizione = descrizione;
			return this;
		}
		
		public IMovimento build() throws ClassNotFoundException, SQLException{
			Map<String, Object> valori = new HashMap<>();
			valori.put("IDProdotto", this.prodotto.getIDProdotto());
			valori.put("Data", this.data);
			valori.put("IDRicevuta", this.idRicevuta);
			valori.put("Prezzo", this.prezzo);
			valori.put("IVA", this.IVA);
			valori.put("Descrizione", this.descrizione);
			valori.put("Quantita", this.quantita);
			GestioneDB db = GestioneDB.generaControllore();
			db.connetti();
			db.inserisciRecord("Movimenti", valori);
			
			Integer idMovimento;
			try{
			idMovimento = db.eseguiLettura(new String[]{"IDMovimento"}, "Movimenti").stream()
					.mapToInt(w -> (Integer)w.get("IDMovimento"))
					.max()
					.getAsInt();
			}
			catch(NullPointerException e){
				idMovimento = 1;
			}
			
			db.disconnetti();
			IMovimento ritorno;
			if(this.isVendita){
				ritorno = new Vendita(idMovimento, this.quantita, this.descrizione, this.prodotto.getIDProdotto(), this.data, this.prezzo, this.IVA, this.idRicevuta);
			}
			else{
				ritorno = new Acquisto(idMovimento, quantita, descrizione, this.prodotto.getIDProdotto(), data, prezzo, IVA, idRicevuta);
			}
			return ritorno;
		}
		
		
	}

}