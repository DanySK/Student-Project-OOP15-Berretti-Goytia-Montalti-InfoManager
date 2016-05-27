package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.sql.Date;
import com.goytia.classiAusiliari.*;
import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelScontrini {
	
	MBOggetto oggetto;
	
	private modelScontrini(MBOggetto temp){
		this.oggetto=temp;
	}
	
	public modelScontrini(){
		this.oggetto = MBOggetto.oggettoDaTabella("Scontrini");
	}
	
	private void setData(Date data){
		this.oggetto.setObjectValue("Data", data);
	}
	private void setSconto(float sconto) {
		// TODO Auto-generated method stub
		this.oggetto.setObjectValue("Sconti", sconto);
	}

	private void setIva(float iva) {
		// TODO Auto-generated method stub
		this.oggetto.setObjectValue("IVA", iva);
	}

	private void setPrezzoUnitario(double prezzoUnitario) {
		// TODO Auto-generated method stub
		this.oggetto.setObjectValue("PrezzoUnitario", prezzoUnitario);
	}
	
	private void setProdotto(Integer idProdotto) {	
		this.oggetto.setObjectValue("IDProdotto", idProdotto);
	}
	
	private void setQuantita(int quantita){
		this.oggetto.setObjectValue("Quantita", quantita);
	}

	private void setNumScontrino(int nScontrino) {
		// TODO Auto-generated method stub
		this.oggetto.setObjectValue("NumScontrino", nScontrino);
	}
	
	/*
	private void setPrezzoFinale(double prezzoUnitario, int quantita){
		this.oggetto.setObjectValue("PrezzoFinale", prezzoUnitario * quantita);
	}*/
	
	public Integer getID(){
		return this.oggetto.objectId();
	}
	
	public Integer getIDProdotto(){
		return (Integer) this.oggetto.getObject("IDProdotto");
	}
	
	public int getNumScontrino(){
		return (int)this.oggetto.getObject("NumScontrino");
	}
	
	public Date getData(){
		return (Date)this.oggetto.getObject("Data");
	}
	
	public String getNomeProdotto(){
		return modelMagazzino.elenco().stream()
				.filter( a -> a.getID() == this.getIDProdotto())
				.map(a -> a.getNome())
				.toString();
	}
	
	public int getQuantita(){
		return (int)this.oggetto.getObject("Quantita");
	}
	
	public float getIva(){
		return (float) this.oggetto.getObject("IVA");
	}
	
	public float getSconto(){
		return (float) this.oggetto.getObject("Sconti");
	}
	
	public int prezzoUnitario(){
		return (int)this.oggetto.getObject("PrezzoUnitario");
	}
	/*
	public double prezzoFinale(){
		return (double)this.oggetto.getObject("PrezzoFinale");
	}*/
	
	public static List<modelScontrini> elenco(){
		MBQuery query = MBQuery.queryDaTabella("Scontrini");
		try {
			return query.find().stream()
					.map(e -> new modelScontrini(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/***
	 * 
	 * @param nScontrino
	 * @param p
	 * lista di tipo "prodottoVenduto"(vedi classi ausiliari)
	 * @param prezzoUnitario
	 * @param iva
	 * @param sconto
	 * @return
	 */
	public boolean nuovoScontrino(int nScontrino, Date data, List<prodottoVenduto> prodottiVenduti){
		
		modelScontrini _temp; boolean ctrl=true;
		for(prodottoVenduto p : prodottiVenduti){
			_temp = new modelScontrini(MBOggetto.oggettoDaTabella("Scontrini"));
			_temp.setNumScontrino(nScontrino);
			_temp.setData(data);
			_temp.setProdotto(p.getIDProdottoV());
			_temp.setQuantita(p.getQuantita());
			_temp.setPrezzoUnitario(p.getPrezzoUnitario());
			_temp.setIva(p.getIVA());
			_temp.setSconto(p.getSconto());
			//_temp.setPrezzoFinale(prodottiVenduti.get(i).getPrezzoUnitario(), prodottiVenduti.get(i).getQuantita());
		    ctrl = _temp.oggetto.salva();
		}
		return ctrl;
	}
	
	public static List<modelScontrini> getProdottiScontrino(int nScontrino){
		return elenco().stream()
				.filter(e-> e.getNumScontrino() == nScontrino)
				.collect(Collectors.toList());
	}
	
	
}


