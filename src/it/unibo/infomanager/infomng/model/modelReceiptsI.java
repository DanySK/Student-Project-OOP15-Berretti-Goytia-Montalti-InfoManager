package it.unibo.infomanager.infomng.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.controller.DataBaseSearch;
import it.unibo.infomanager.infomng.controller.TableRow;

public interface modelReceiptsI {
	
	/***
	 * ottiene l'ID del record
	 * @return
	 */
	Integer getID();
	
	/***
	 * ottiene il numero dello scontrino
	 * @return
	 * un int con il numero dello scontrino
	 */
	int getNumberReceipt();
	
	/***
	 * ottiene l'id del cliente a cui � stato fatto lo scontrino
	 * @return
	 * un Integer con il numero dello scontrino
	 */
	@Deprecated
	Integer getIDClient();

	/***
	 * ottiene l'iva dello scontrino
	 * @return
	 * un float contenente l'IVA
	 */
	float getIVA();
	
	/***
	 *ottiene la data di emissione dello scontrino 
	 * @return
	 * un object sql.Date con la data
	 */
	Date getDate();

	/***
	 * ottieni il cliente sottoforma di modelClientsI contentnte tutti i dati del cliente a cui � stato emmesso lo scontrino
	 * @return
	 * un obtject tipo modelCLientsI
	 */
	modelClientsI client();

	/***
	 * ottiene la lista di tutti i prodotti venduti nello sconrino
	 * @return
	 * una lista tipo transactionsProductsI 
	 */
	List<transactionsProductsI> soldProducts();

	/***
	 * elimnazione dello scontrino corrente
	 * @return
	 * true se e andato  a buon fine
	 */
	boolean deleteReceipt();
	
	/***
	 * ottiene una lista con tutti gli scontrini emmessi
	 * @return
	 * un lista di tipo modelReceiptsI
	 */
	public static List<modelReceiptsI> receiptsList(){
		DataBaseSearch query = DataBaseSearch.queryDaTabella("Scontrini");
		try {
			return query.find().stream()
					.map(e -> new modelReceipts(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			return new ArrayList<modelReceiptsI>();
		}
	}
	
	/***
	 * salvataggio di un nuovo scontrino
	 * @param nScontrino
	 * numero dello scontrino
	 * @param nRicevuta
	 * numero della ricevuta per la quale si deve realizzare lo scontrino
	 * @param idCliente
	 * id del cliente a chi va emmesso lo scontrino
	 * @param data
	 * data dello scontrino
	 * @param iva
	 * iva da applicare
	 * @return
	 * true se e andato a buon fine altrimenti false
	 */
	@Deprecated
	public static boolean newReceipt(int nScontrino, int nRicevuta, Integer idCliente, Date data, float iva){
		
		modelReceipts nuovo = new modelReceipts(TableRow.oggettoDaTabella("Scontrini"));
		nuovo.setClient(idCliente);
		nuovo.setDate(data);
		nuovo.setIva(iva);
		nuovo.setNumberPaymentReceipt(nRicevuta);
		nuovo.setReceipt(nScontrino);
		return nuovo.oggetto.salva();
		
	}
	
	public static boolean newReceipt(Optional<Integer> numeroScontrino, modelClientsI cliente, Date data, Double iva, List<transactionsProductsI> prodotti){
		
		return false;
	}
	
	/***
	 * ricerca di uno specifico scontrino
	 * @param nScontrino
	 * numero dello scontrino da ricercare
	 * @return
	 * lo scontrino 
	 */
	public static modelReceiptsI searchReceiptByNumber(int nScontrino){
		
		return modelReceiptsI.receiptsList().stream()
				.filter(s -> s.getNumberReceipt() == nScontrino)
				.findFirst()
				.get();
	}
	
}