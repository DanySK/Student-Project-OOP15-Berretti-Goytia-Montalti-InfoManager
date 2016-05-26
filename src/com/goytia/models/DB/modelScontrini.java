package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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
	
	private void setSconto(float sconto) {
		// TODO Auto-generated method stub
		this.oggetto.setObjectValue("Sconti", sconto);
	}

	private void setIva(float iva) {
		// TODO Auto-generated method stub
		this.oggetto.setObjectValue("IVA", iva);
	}

	private void setQuantita(int quantita) {
		// TODO Auto-generated method stub
		this.oggetto.setObjectValue("Quantita", quantita);
	}

	private void setPrezzoUnitario(double prezzoUnitario) {
		// TODO Auto-generated method stub
		this.oggetto.setObjectValue("PrezzoUnitario", prezzoUnitario);
	}

	private void setProdotto(String prodotto) {
		// TODO Auto-generated method stub
		this.oggetto.setObjectValue("Prodotto", prodotto);
	}

	private void setNumScontrino(int nScontrino) {
		// TODO Auto-generated method stub
		this.oggetto.setObjectValue("NumScontrino", nScontrino);
	}
	
	public Integer getID(){
		return this.oggetto.objectId();
	}
	
	public int getNumScontrino(){
		return (int)this.oggetto.getObject("NumScontrino");
	}
	
	public String getProdotto(){
		return (String)this.oggetto.getObject("Prodotto");
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
	
	public boolean nuovoScontrino(int nScontrino, String prodotto, int quantita, double prezzoUnitario, float iva, float sconto){
		modelScontrini _temp = new modelScontrini(MBOggetto.oggettoDaTabella("Scontrini"));
		_temp.setNumScontrino(nScontrino);
		_temp.setProdotto(prodotto);
		_temp.setQuantita(quantita);
		_temp.setPrezzoUnitario(prezzoUnitario);
		_temp.setIva(iva);
		_temp.setSconto(sconto);
		return _temp.oggetto.salva();
	}
	
	public static modelScontrini cercaScontrino(int nScontrino){
		return elenco().stream()
				.filter(e-> e.getNumScontrino() == nScontrino)
				.findFirst()
				.get();
	}
	
	
}
