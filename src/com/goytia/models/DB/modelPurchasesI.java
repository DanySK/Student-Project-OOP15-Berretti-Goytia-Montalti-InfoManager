package com.goytia.models.DB;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public interface modelPurchasesI {

	Integer getID();

	int getNumberPaymentReceipt();

	Integer getIDProvider();

	float getIva();

	Date getDate();

	float getDiscount();

	List<transactionsProducts> purchasedProducts();

	/***
	 * eliminazione dell'accquisto corrente
	 * @return
	 * true o false a seconda del esito
	 */
	boolean deletePurchase();
	
	/***
	 * elenco di tutti gli acquisti realizzati
	 * @return
	 * una lista contenente tutti gli acquisti fatti
	 */
	public static List<modelPurchases> purchasesList(){
		MBQuery query = MBQuery.queryDaTabella("Acquisti");
		try {
			return query.find().stream()
					.map(e -> new modelPurchases(e))
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
	public static boolean newPurchase(Integer IDFornitore, int nRicevuta, float iva, float sconto, Date data, List<transactionsProductsI> prodotti){
	
		modelPurchases temp = new modelPurchases(MBOggetto.oggettoDaTabella("Acquisti"));
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
	
	/*public static List<modelPurchases> reportPurchases(){
		return modelPurchasesI.purchasesList().stream()
				.sorted().
	}*/
}