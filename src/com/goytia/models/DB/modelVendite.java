package com.goytia.models.DB;

import com.infoMng.controller.MBOggetto;
import com.goytia.classiAusiliari.prodottoVenduto;
import java.sql.Date;
import java.util.List;

public class modelVendite{
	
	MBOggetto oggetto;
	List<prodottoVenduto> prodottiVenduti;
	
	private modelVendite(MBOggetto temp){
		this.oggetto = temp;
	}
	
	public modelVendite(){
		this.oggetto = MBOggetto.oggettoDaTabella("Vendite");
	}
	
	private void setRicevuta(int nRicevuta){
		this.oggetto.setObjectValue("nRicevuta", nRicevuta);
	}
	
	private void setIVA(float iva){
		this.oggetto.setObjectValue("IVA", iva);
	}
	
	private void setCliente(Integer IDCliente){
		this.oggetto.setObjectValue("IDCliente", IDCliente);
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
		return (Integer)this.oggetto.getObject("IDCliente");
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
	
	public static boolean nuovaVendita(Integer IDCliente, int nRicevuta, float iva, float sconto, Date data, List<prodottoVenduto> prodotti){
		
		modelVendite temp = new modelVendite(MBOggetto.oggettoDaTabella("Vendite"));
		temp.setCliente(IDCliente);
		temp.setSconto(sconto);
		temp.setData(data);
		temp.setIVA(iva);
		temp.setRicevuta(nRicevuta);
		if(modelVendite.builderElementiVenduti(nRicevuta, prodotti))
			return temp.oggetto.salva();
		else 
			return false;
	}
	
	private static boolean builderElementiVenduti(int nRicevuta, List<prodottoVenduto> lista){
		
		return modelMovimenti.prodottiNelMovimento(nRicevuta, lista, true);
	}
}
