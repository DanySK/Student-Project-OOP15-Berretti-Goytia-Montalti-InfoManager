package com.mattiaberretti.vendite;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mattiaberretti.clienti.ICliente;
import com.mattiaberretti.database.GestioneDB;
import com.mattiaberretti.prodotti.IProdotto;
import com.mattiaberretti.utenti.IUtente;

public interface IVendita {

	static List<IVendita> elencoVendite() throws ClassNotFoundException, SQLException{
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();
		Integer idUtente = IUtente.utenteCorrente.getUtenteCorrente().get().getIDUtente();
		List<IVendita> ritorno = new LinkedList<>();
		
		List<Map<String, Object>> tabellaRicevute = db.eseguiLettura(new String[]{"IDRicevuta", "Data", "IDCliente", "IVA", "IDUtente"}, "Vendite").stream()
		.filter(e -> e.get("IDUtente").equals(idUtente))
		.map(e -> {
			e.remove("IDUtente");
			return e;
		})
		.collect(Collectors.toList());
		
		for(Map<String, Object> tmp : tabellaRicevute){
			 ICliente cliente = ICliente.elencoClienti().stream()
					 .filter(c -> c.getIDCliente().equals(tmp.get("IDCliente")))
					 .findFirst().get();
			 
			 Date data = (Date)tmp.get("Data");
			 Integer IDRicevuta = (Integer)tmp.get("IDRicevuta");
			 Integer IVA = (Integer)tmp.get("IVA");
			 
			 
			 //ottengo i dettagli
			 
			 List<Map<String, Object>> tabellaDettagli = db.eseguiLettura(new String[]{"Quantita", "Prezzo", "IDVendita", "IDProdotto"}, "DettagliVendita").stream()
					 .filter(d -> d.get("IDVendita").equals(IDRicevuta))
					 .map(r -> {
						 r.remove("IDVendita");
						 return r;
					 })
					 .collect(Collectors.toList());
			 
			 Map<IProdotto, PairVendita> dettagli = new HashMap<>();
			 for(Map<String, Object> dettaglio : tabellaDettagli){
				 Integer quantita = (Integer)dettaglio.get("Quantita");
				 Double prezzo = (Double)dettaglio.get("Prezzo");
				 
				 IProdotto prodotto = IProdotto.elencoProdotti().stream()
						 .filter(p -> p.getIDProdotto().equals(dettaglio.get("IDProdotto")))
						 .findFirst().get();
				 
				 dettagli.put(prodotto, new PairVendita(quantita, prezzo));
			 }
			 
			 IVendita nuovo = new Vendita(IDRicevuta, IVA, data, cliente, dettagli);
			 ritorno.add(nuovo);
		}
		
		
		db.disconnetti();
		return ritorno;
	}
	
	


	List<IProdotto> getProdotti();

	Integer getQuantitaPerProdotto(IProdotto prodotto);

	Double getPrezzoPerProdotto(IProdotto prodotto);

	void aggiungiProdotto(IProdotto prodotto, Integer quantita, Double prezzo);

	void rimuoviProdotto(IProdotto prodotto);

	Integer getIDVendita();

	Integer getIVA();

	Date getData();

	ICliente getCliente();

	Map<IProdotto, PairVendita> getElementi();

	void setIDVendita(Integer iDVendita);

	void setIVA(Integer iVA);

	void setData(Date data);

	void setCliente(ICliente cliente);

	void setElementi(Map<IProdotto, PairVendita> elementi);

	void salva() throws SQLException, ClassNotFoundException;

	void elimina() throws SQLException, ClassNotFoundException;

	public class Builder{
		private ICliente cliente;
		private Date data;
		private Integer IVA;
		
		private Map<IProdotto, PairVendita> elementi;
		
		public Builder(){
			this.elementi = new HashMap<>();
		}
		
		public Builder aggiungiProdotto(IProdotto prodotto, Integer quantita){
			return this.aggiungiProdotto(prodotto, quantita, prodotto.getPrezzoVendita());
		}
		
		public Builder aggiungiProdotto(IProdotto prodotto, Integer quantita, Double prezzo){
			PairVendita dati = elementi.get(prodotto);
			if(dati == null){
				dati = new PairVendita(quantita, prezzo);
			}
			else{
				quantita = dati.getQuantita() + quantita;
				dati.setQuantita(quantita);
				dati.setPrezzo(prezzo);
			}
			this.elementi.put(prodotto, dati);
			return this;
		}
		
		public Builder rimuoviProdotto(IProdotto prodotto){
			this.elementi.remove(prodotto);
			return this;
		}
		
		public Builder setCliente(ICliente cliente){
			this.cliente = cliente;
			return this;
		}
		
		public Builder setData(Date data){
			this.data = data;
			return this;
		}
		
		public Builder setIva(Integer iva){
			this.IVA = iva;
			return this;
		}
		
		public IVendita build() throws ClassNotFoundException, SQLException{
			Integer idUtente = IUtente.utenteCorrente.getUtenteCorrente().get().getIDUtente();
			Map<String, Object> valori = new HashMap<>();
			valori.put("IDCliente", this.cliente.getIDCliente());
			valori.put("IVA", this.IVA);
			valori.put("Data", this.data.toString());
			valori.put("IDUtente", idUtente);
			
			GestioneDB db = GestioneDB.generaControllore();
			db.connetti();
			db.inserisciRecord("Vendite", valori);
			
			Integer idVendita = db.eseguiLettura(new String[]{"IDRicevuta", "IDUtente"}, "Vendite").stream()
					.filter(e -> e.get("IDUtente").equals(idUtente))
					.mapToInt(i -> (Integer)i.get("IDUtente"))
					.max().getAsInt();
			
			for (java.util.Map.Entry<IProdotto, PairVendita> tmp: this.elementi.entrySet()){
				IProdotto e = tmp.getKey();
				PairVendita v = tmp.getValue();
				Map<String, Object> valoriElemento = new HashMap<>();
				valoriElemento.put("IDProdotto", e.getIDProdotto());
				valoriElemento.put("Quantita", v.getQuantita());
				valoriElemento.put("Prezzo", v.getPrezzo());
				valoriElemento.put("IDVendita", idVendita);
				
				db.inserisciRecord("DettagliVendita", valoriElemento);
				
				
				Integer idDettagli = db.eseguiLettura(new String[]{"IDDettagliVendita"}, "DettagliVendita").stream()
						.mapToInt(i -> (Integer)i.get("IDDettagliVendita"))
						.max().getAsInt();
				
				//inserisco il movimento del prodotto
				valoriElemento = new HashMap<>();
				valoriElemento.put("IDProdotto", e.getIDProdotto());
				valoriElemento.put("IDVendita", idDettagli);
				valoriElemento.put("Descrizione", "Ricevuta di vendita");
				valoriElemento.put("Quantita", v.getQuantita());
				valoriElemento.put("Data", this.data);
				
				db.inserisciRecord("Movimento", valoriElemento);
			
				
			}
			
			db.disconnetti();
			
			return new Vendita(idVendita, this.IVA, this.data, this.cliente, this.elementi);
		}
		
	}
	
}