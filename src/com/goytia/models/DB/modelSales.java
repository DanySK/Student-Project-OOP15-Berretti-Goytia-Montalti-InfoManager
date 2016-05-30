package com.goytia.models.DB;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class modelSales {
	
	MBOggetto oggetto;
	//List<transactionsProducts> prodottiVenduti;
	
	private modelSales(MBOggetto temp){
		this.oggetto = temp;
	}
	
	public modelSales(){
		this.oggetto = MBOggetto.oggettoDaTabella("Vendite");
	}
	
	private void setNumberPaymentReceipt(int nRicevuta){
		this.oggetto.setObjectValue("nRicevuta", nRicevuta);
	}
	
	private void setIVA(float iva){
		this.oggetto.setObjectValue("IVA", iva);
	}
	
	private void setIDClient(Integer IDCliente){
		this.oggetto.setObjectValue("IDCliente", IDCliente);
	}
	
	private void setDate(Date data){
		this.oggetto.setObjectValue("Data", data);
	}
	
	private void setDiscount(float sconto){
		this.oggetto.setObjectValue("Sconto", sconto);
	}
	
	public Integer getID(){
		return this.oggetto.objectId();
	}
	
	public int getNumberPaymentReceipt(){
		return (int)this.oggetto.getObject("nRicevuta");
	}
	
	public Integer getIDClient(){
		return (Integer)this.oggetto.getObject("IDCliente");
	}
	
	public float getIva(){
		return (float)this.oggetto.getObject("IVA");
	}
	
	public Date getDate(){
		return (Date)this.oggetto.getObject("Data");
	}
	
	public float getDiscount(){
		return (float)this.oggetto.getObject("Sconto");
	}
	
	/***
	 * elenco di tutte le vendite realizzate anche quelle che non possiedono uno scrontrino
	 * @return
	 * una lista contenente tutte le vendite
	 */
	public static List<modelSales> salesList(){
		MBQuery query = MBQuery.queryDaTabella("Acquisti");
		try {
			return query.find().stream()
					.map(e -> new modelSales(e))
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
	public static boolean newSale(Integer IDCliente, int nRicevuta, float iva, float sconto, Date data, List<transactionsProducts> prodotti){
		
		modelSales temp = new modelSales(MBOggetto.oggettoDaTabella("Vendite"));
		temp.setIDClient(IDCliente);
		temp.setDiscount(sconto);
		temp.setDate(data);
		temp.setIVA(iva);
		temp.setNumberPaymentReceipt(nRicevuta);
		if(modelSales.builderProductsSale(nRicevuta, prodotti))
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
	private static boolean builderProductsSale(int nRicevuta, List<transactionsProducts> lista){
		
		return modelTransactions.transactionsProducts(nRicevuta, lista, true);
	}
	/***
	 * metodo che elimina la vednita corrente e i suoi relativi prodotti
	 * @return
	 * true o false a seconda del esito
	 */
	public boolean deleteSale(){

		if(modelTransactions.deleteTransactionsProducts(this.getNumberPaymentReceipt(), true))
			return this.oggetto.elimina();
		else 
			return false;
	}
	
	public List<transactionsProducts> soldProducts(){
		return modelTransactions.transactionsList().stream()
				.filter( m -> m.getNumberPaymentRicevuta() == this.getNumberPaymentReceipt())
				.map( p -> {
					transactionsProducts prod = new transactionsProducts(p.getIDProduct(), Math.abs(p.getQuantity()), p.getPrice());
					return prod;
				})
				.collect(Collectors.toList());
	}
	
	public boolean renameSale(Integer newIDCliente, int newNRicevuta, float newIva, float newSconto, Date newData){
			if(!newIDCliente.equals(null))this.setNumberPaymentReceipt(newNRicevuta);
			if(newNRicevuta != -1)this.setNumberPaymentReceipt(newNRicevuta);
			if(newIva != -1)this.setIVA(newIva);
			if(newSconto != -1)this.setDiscount(newSconto);
			if(newData != null)this.setDate(newData);
			return this.oggetto.salva();
	}
}
