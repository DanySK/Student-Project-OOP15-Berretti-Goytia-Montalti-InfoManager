package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.sql.Date;
import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelReceipts {
	
	MBOggetto oggetto;
	
	private modelReceipts(MBOggetto temp){
		this.oggetto=temp;
	}
	/***
	 * Ottengo un record nuovo dalla tabella Scontrini
	 */
	public modelReceipts(){
		this.oggetto = MBOggetto.oggettoDaTabella("Scontrini");
	}
	
	private void setReceipt(int nScontrino){
		this.oggetto.setObjectValue("nScontrino", nScontrino);
	}
	private void setClient(Integer idCliente){
		this.oggetto.setObjectValue("IDCliente", idCliente);
	}
	
	private void setDate(Date data){
		this.oggetto.setObjectValue("Data", data);
	}
	
	private void setIva(float iva){
		this.oggetto.setObjectValue("IVA", iva);
	}
	
	private void setNumberPaymentReceipt(int nRicevuta){
		this.oggetto.setObjectValue("nRicevuta", nRicevuta);
	}
	
	private int getNumberPaymentReceipt(){
		return (int)this.oggetto.getObject("nRicevuta");
	}
	
	public int getNumberReceipt(){
		return (int)this.oggetto.getObject("nScontrino");
	}
	
	public Integer getIDClient(){
		return (Integer)this.oggetto.getObject("IDCliente");
	}
	
	public float getIVA(){
		return (float) this.oggetto.getObject("IVA");
	}
	
	public Date getDate(){
		return (Date)this.oggetto.getObject("Data");
	}
	/***
	 * metodo alternativo che ritorno il cliente con tutti i dati filtrando con l'idCliente
	 * @return
	 * lo specifico cliente a qui va rivolta lo scontrino
	 */
	public modelClients client(){
		return modelClients.clientsList().stream()
				.filter(c -> c.getID().equals(this.getIDClient()))
				.findFirst()
				.get();
	}
	/***
	 * meotodo che ritorna tutti i prodotti da insierire nello scontrino mappato con la classe prodttoVenduto
	 * @return
	 * una lista ocn tutti i prodotti sottoforma di prodottoVenduto
	 */
	public List<transactionsProducts> soldProducts(){
		return modelTransactions.transactionsList().stream()
				.filter( m -> m.getNumberPaymentRicevuta() == this.getNumberPaymentReceipt())
				.map( p -> {
					transactionsProducts prod = new transactionsProducts(p.getIDProduct(), Math.abs(p.getQuantity()), p.getPrice());
					return prod;
				})
				.collect(Collectors.toList());
				
	}
	/***
	 * metodo che ritorna elenco di tutti gli scontrini
	 * @return
	 * un lista cont utti gli scontrini esistenti fino a quel momento
	 */
	public static List<modelReceipts> receiptsList(){
		MBQuery query = MBQuery.queryDaTabella("Scontrini");
		try {
			return query.find().stream()
					.map(e -> new modelReceipts(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/***
	 * metodo per la crezione di un nuovo scontrino
	 * @param nScontrino
	 * numero dello scontrino 
	 * @param nRicevuta
	 * numero della ricevuta per la quale si deve realizzare lo scontrino
	 * @param idCliente
	 * id del cliente
	 * @param data
	 * data de lo scontrino
	 * @param iva
	 * iva da applicare
	 * @return
	 * true se � stato creato e salvato lo scrontrino altrimenti false
	 */
	public static boolean newReceipt(int nScontrino, int nRicevuta, Integer idCliente, Date data, float iva){
		
		modelReceipts nuovo = new modelReceipts(MBOggetto.oggettoDaTabella("Scontrini"));
		nuovo.setClient(idCliente);
		nuovo.setDate(data);
		nuovo.setIva(iva);
		nuovo.setNumberPaymentReceipt(nRicevuta);
		nuovo.setReceipt(nScontrino);
		return nuovo.oggetto.salva();
		
	}
	
	/***
	 * ricerca di uno specifico scontrino
	 * @param nScontrino
	 * numero dello scontrino a ricercare
	 * @return
	 * lo scontrino 
	 */
	public static modelReceipts searchReceiptByNumber(int nScontrino){
		
		return modelReceipts.receiptsList().stream()
				.filter(s -> s.getNumberReceipt() == nScontrino)
				.findFirst()
				.get();
	}
	/***
	 * elimnazione dello scontrino corrente
	 * @return
	 * true o false a seconda del esito
	 */
	public boolean deleteReceipt(){
		return this.oggetto.elimina();
	}
}


