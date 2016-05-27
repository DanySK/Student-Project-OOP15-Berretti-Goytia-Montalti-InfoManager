package com.goytia.models.DB;

import com.infoMng.controller.MBOggetto;

import java.sql.Date;
import java.util.List;

import com.goytia.classiAusiliari.prodottoVenduto;
public class modelAcquisti {
	
	MBOggetto oggetto;
	
	private modelAcquisti(MBOggetto temp){
		this.oggetto = temp;
	}
	
	public modelAcquisti(){
		this.oggetto = MBOggetto.oggettoDaTabella("Acquisti");
	}
	
	private void setRicevuta(int nRicevuta){
		this.oggetto.setObjectValue("nRicevuta", nRicevuta);
	}
	
	private void setIVA(float iva){
		this.oggetto.setObjectValue("IVA", iva);
	}
	
	private void setFornitore(Integer IDFornitore){
		this.oggetto.setObjectValue("IDFornitore", IDFornitore);
	}
	
	private void setData(Date data){
		this.oggetto.setObjectValue("Data", data);
	}
	
	private void setSconto(float sconto){
		this.oggetto.setObjectValue("Sconto", sconto);
	}
	
	public int getRicevuta(){
		return (int)this.oggetto.getObject("nRicevuta");
	}
	
	public Integer getCliente(){
		return (Integer)this.oggetto.getObject("IDFornitore");
	}
	
	public float getIva(){
		return (float)this.oggetto.getObject("IVA");
	}
	
	public Date getData(){
		return (Date)this.oggetto.getObject("Data");
	}
	
	public float getSconto(){
		return (float)this.oggetto.getObject("Sconto");
	}
	
	public static boolean nuovoAcquisto(Integer IDFornitore, int nRicevuta, float iva, float sconto, Date data, List<prodottoVenduto> prodotti){
		
		modelAcquisti temp = new modelAcquisti(MBOggetto.oggettoDaTabella("Acquisti"));
		temp.setFornitore(IDFornitore);
		temp.setSconto(sconto);
		temp.setData(data);
		temp.setIVA(iva);
		temp.setRicevuta(nRicevuta);
		if(modelAcquisti.builderElementiAcquisiti(nRicevuta, prodotti))
			return temp.oggetto.salva();
		else 
			return false;
	}
	
	private static boolean builderElementiAcquisiti(int nRicevuta, List<prodottoVenduto> lista){
		
		return modelProdottiAcquistati.prodottiAcquistati(nRicevuta, lista);
	}
	
}
