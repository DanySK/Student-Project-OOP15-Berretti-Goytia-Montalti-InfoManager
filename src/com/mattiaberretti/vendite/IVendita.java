package com.mattiaberretti.vendite;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mattiaberretti.clienti.ICliente;
import com.mattiaberretti.prodotti.IProdotto;

public interface IVendita {

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
		
		public IVendita build(){
			Map<String, Object> valori = new HashMap<>();
			valori.put("IDCliente", this.cliente.getIDCliente());
			valori.put("IVA", this.IVA);
			valori.put("Data", this.data.toString());
			
			//TODO: salvataggio della nuova vendita
			//TODO: lettura dell'id della vendita
			Integer idVendita = -1;
			
			this.elementi.forEach((e,v) -> {
				Map<String, Object> valoriElemento = new HashMap<>();
				valoriElemento.put("IProdotto", e.getIDProdotto());
				valoriElemento.put("Quantita", v.getQuantita());
				valoriElemento.put("Prezzo", v.getPrezzo());
				valoriElemento.put("IDVendita", idVendita);
				
				//TODO: salvataggio dell'elemento
			});
			
			return new Vendita(idVendita, this.IVA, this.data, this.cliente, this.elementi);
		}
		
	}
	
}