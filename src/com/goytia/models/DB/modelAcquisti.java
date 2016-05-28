package com.goytia.models.DB;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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
	/***
	 * elenco di tutti gli acquisti realizzati
	 * @return
	 * una lista contenente tutti gli acquisti fatti
	 */
	public static List<modelAcquisti> elenco(){
		MBQuery query = MBQuery.queryDaTabella("Acquisti");
		try {
			return query.find().stream()
					.map(e -> new modelAcquisti(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/***
	 * Aggiunta di un nuovo acquisto
	 * @param IDFornitore
	 * @param nRicevuta
	 * @param iva
	 * @param sconto
	 * @param data
	 * @param prodotti
	 * @return
	 * true se è stato aggiunt nel db altrimenti false
	 */
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
	//salvataggio degli elementi acquistati
	private static boolean builderElementiAcquisiti(int nRicevuta, List<prodottoVenduto> lista){
		return modelMovimenti.prodottiNelMovimento(nRicevuta, lista, false);
	}
	/***
	 * eliminazione dell'accquisto corrente
	 * @return
	 * true o false a seconda del esito
	 */
	public boolean eliminaAquisto(){
		return this.oggetto.elimina();
	}
}
