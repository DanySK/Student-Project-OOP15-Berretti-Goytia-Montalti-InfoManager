package it.unibo.infomanager.infomng.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.controller.DataBaseSearch;
import it.unibo.infomanager.infomng.controller.TableRow;

public interface modelPurchasesI {
	/***
	 * ottiene l'id del record corrente
	 * @return
	 * un integer che contiene l'id
	 */
	Integer getID();
	/***
	 * ottiene il numero della ricevuta dell'acquisto
	 * @return
	 * un int con il numero della ricevuta
	 */
	int getNumberPaymentReceipt();
	/***
	 * ottiene l'id del fornitore di cui si � acquistto
	 * @return
	 * un integer che contiene l'ide del fornitore
	 */
	@Deprecated
	Integer getIDProvider();
	/***
	 * ottiene l'iva dell'acquisto
	 * @return
	 * un float contenente l'iva dell'acquisto
	 */
	float getIva();
	/***
	 * ottiene la data dell'acquisto
	 * @return
	 * una object sql.date che contiene la data dell'acquisto
	 */
	Date getDate();
	/***
	 * ottiene lo sconto per l'acquisto
	 * @return
	 * un float con lo sconto dell'acquisto
	 */
	float getDiscount();
	/***
	 * ottiene una lista con tutti i prodotti acquistati
	 * @return
	 * una lista di tipo transactionsProducts 
	 */
	List<transactionsProducts> purchasedProducts();
	/***
	 * ottiene il totale speso per questo acquisto
	 * @return
	 * un double con il totale speso
	 */
	Double getTotalSpent();
	
	/***
	 * eliminazione dell'accquisto corrente
	 * @return
	 * true se e andato a buon fine
	 */
	boolean deletePurchase();
	
	/***
	 * elenco di tutti gli acquisti realizzati
	 * @return
	 * una lista contenente tutti gli acquisti fatti
	 */
	public static List<modelPurchasesI> purchasesList(){
		DataBaseSearch query = DataBaseSearch.queryDaTabella("Acquisti");
		try {
			return query.find().stream()
					.map(e -> new modelPurchases(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			return new ArrayList<modelPurchasesI>();
		}
	}
	/***
	 * salvataggio di un nuovo acquisto
	 * @param IDFornitore
	 * id del fornitore del quale si e acquistato
	 * @param nRicevuta
	 * numero della ricevuta
	 * @param iva
	 * iva dell'acquisto
	 * @param sconto
	 * sconto dell'acquisto
	 * @param data
	 * data dell'acquisto
	 * @param prodotti
	 * una lista di tipo transactionsProducts contenente tutti i prodotti acquistati
	 * @return
	 * true se e andato a buon fine
	 */
	public static boolean newPurchase(Integer IDFornitore, int nRicevuta, float iva, float sconto, Date data, List<transactionsProductsI> prodotti){
	
		modelPurchases temp = new modelPurchases(TableRow.oggettoDaTabella("Acquisti"));
		temp.setProvider(IDFornitore);
		temp.setDiscount(sconto);
		temp.setDate(data);
		temp.setIVA(iva);
		temp.setNumberPaymentReceipt(nRicevuta);
		if(temp.builderProductsPurchases(nRicevuta, prodotti))
			return temp.oggetto.salva();
		else 
			return false;
	}
	/***
	 * ottiene il report degli acquisti
	 * @return
	 * una lista contenente tutti gli acquisti arodinati in maniera decrescente di tutti gli acquisti
	 */
	public static List<modelPurchasesI> reportPurchases(){
		
		Comparator<modelPurchasesI> sort = (primo, secondo) -> Double.compare(primo.getTotalSpent(), secondo.getTotalSpent());
		
		return modelPurchasesI.purchasesList().stream()
				.sorted(sort)
				.collect(Collectors.toList());
	}
	
	
}