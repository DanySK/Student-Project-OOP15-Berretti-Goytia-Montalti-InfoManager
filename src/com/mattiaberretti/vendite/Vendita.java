package com.mattiaberretti.vendite;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mattiaberretti.clienti.ICliente;
import com.mattiaberretti.prodotti.IProdotto;
@Deprecated
class Vendita implements IVendita {
	private Integer IDVendita;
	private Integer IVA;
	private Date data;
	private ICliente cliente;
	
	private Map<IProdotto, PairVendita> elementi;
	
	protected Vendita(Integer IDVendita, Integer IVA, Date data, ICliente cliente, Map<IProdotto, PairVendita> elementi) {
		this.IDVendita = IDVendita;
		this.IVA = IVA;
		this.data = data;
		this.cliente = cliente;
		this.elementi = elementi;
	}
	
	
	@Override
	public List<IProdotto> getProdotti(){
		return this.elementi.keySet().stream().collect(Collectors.toList());
	}
	
	@Override
	public Integer getQuantitaPerProdotto(IProdotto prodotto){
		PairVendita dati = this.elementi.get(prodotto);
		if(dati == null){
			return 0;
		}
		else{
			return dati.getQuantita();
		}
	}
	
	@Override
	public Double getPrezzoPerProdotto(IProdotto prodotto){
		PairVendita dati = this.elementi.get(prodotto);
		if(dati == null){
			return prodotto.getPrezzoVendita();
		}
		else{
			return dati.getPrezzo();
		}
	}

	@Override
	public void aggiungiProdotto(IProdotto prodotto, Integer quantita, Double prezzo){
		quantita = this.getQuantitaPerProdotto(prodotto) + quantita;
		PairVendita dati = this.elementi.get(prodotto);
		if(dati == null){
			dati = new PairVendita(quantita, prezzo);
			this.elementi.put(prodotto, dati);
		}
		else{
			dati.setQuantita(quantita);
			dati.setPrezzo(prezzo);
		}
	}

	@Override
	public void rimuoviProdotto(IProdotto prodotto){
		this.elementi.remove(prodotto);
	}
	
	@Override
	public Integer getIDVendita() {
		return IDVendita;
	}

	@Override
	public Integer getIVA() {
		return IVA;
	}

	@Override
	public Date getData() {
		return data;
	}

	@Override
	public ICliente getCliente() {
		return cliente;
	}

	@Override
	public Map<IProdotto, PairVendita> getElementi() {
		return elementi;
	}

	@Override
	public void setIDVendita(Integer iDVendita) {
		IDVendita = iDVendita;
	}

	@Override
	public void setIVA(Integer iVA) {
		IVA = iVA;
	}

	@Override
	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public void setCliente(ICliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public void setElementi(Map<IProdotto, PairVendita> elementi) {
		this.elementi = elementi;
	}

	@Override
	public void salva() throws SQLException, ClassNotFoundException{
		//TODO: modifica della ricevuta e di tutti i suoi elementi compresi i movimenti
	}
	
	@Override
	public void elimina() throws SQLException, ClassNotFoundException{
		//TODO: completare eliminazione della ricevuta e di tutti i suoi movimenti
	}
}
