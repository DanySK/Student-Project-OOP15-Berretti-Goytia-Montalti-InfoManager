package com.goytia.models.DB;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;
import com.goytia.classiAusiliari.prodottoVenduto;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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
	
	/***
	 * elenco di tutte le vendite realizzate anche quelle che non possiedono uno scrontrino
	 * @return
	 * una lista contenente tutte le vendite
	 */
	public static List<modelVendite> elenco(){
		MBQuery query = MBQuery.queryDaTabella("Acquisti");
		try {
			return query.find().stream()
					.map(e -> new modelVendite(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/***
	 * nuova vendita
	 * @param IDCliente
	 * id del cliente a chi è stato effettuata la vendita
	 * @param nRicevuta
	 * @param iva
	 * @param sconto
	 * @param data
	 * @param prodotti
	 * una lista dipo prodottoVenduto che contiene tutti i prodotti venduti
	 * @return
	 * true o false a seconda dell'esito
	 */
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
	/***
	 * metodo per il slvataggio dei produtti venduti
	 * @param nRicevuta
	 * @param lista
	 * @return
	 * true o false a seconda dell'esito
	 */
	private static boolean builderElementiVenduti(int nRicevuta, List<prodottoVenduto> lista){
		
		return modelMovimenti.prodottiNelMovimento(nRicevuta, lista, true);
	}
	/***
	 * metodo che elimina la vednita corrente e i suoi relativi prodotti
	 * @return
	 * true o false a seconda del esito
	 */
	public boolean eliminaVendita(){

		if(modelMovimenti.elimnaProdottiDellMovimento(this.getRicevuta(), true))
			return this.oggetto.elimina();
		else 
			return false;
	}
}
