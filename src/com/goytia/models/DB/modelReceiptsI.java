package com.goytia.models.DB;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public interface modelReceiptsI {

	int getNumberReceipt();

	Integer getIDClient();

	float getIVA();

	Date getDate();

	/***
	 * metodo alternativo che ritorno il cliente con tutti i dati filtrando con l'idCliente
	 * @return
	 * lo specifico cliente a qui va rivolta lo scontrino
	 */
	modelClientsI client();

	/***
	 * meotodo che ritorna tutti i prodotti da insierire nello scontrino mappato con la classe prodttoVenduto
	 * @return
	 * una lista ocn tutti i prodotti sottoforma di prodottoVenduto
	 */
	List<transactionsProducts> soldProducts();

	/***
	 * elimnazione dello scontrino corrente
	 * @return
	 * true o false a seconda del esito
	 */
	boolean deleteReceipt();
	
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
	 * true se è stato creato e salvato lo scrontrino altrimenti false
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
		
		return modelReceiptsI.receiptsList().stream()
				.filter(s -> s.getNumberReceipt() == nScontrino)
				.findFirst()
				.get();
	}
	
}