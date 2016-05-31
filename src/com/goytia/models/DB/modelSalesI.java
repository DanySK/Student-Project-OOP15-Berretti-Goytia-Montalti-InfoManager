package com.goytia.models.DB;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.DataBaseSearch;
import com.infoMng.controller.TableRow;

public interface modelSalesI {

	Integer getID();

	int getNumberPaymentReceipt();

	Integer getIDClient();

	float getIva();

	Date getDate();

	float getDiscount();
	
	double getTotalColleactions();
	
	/***
	 * metodo che elimina la vednita corrente e i suoi relativi prodotti
	 * @return
	 * true o false a seconda del esito
	 */
	boolean deleteSale();

	List<transactionsProductsI> soldProducts();

	boolean renameSale(Integer newIDCliente, int newNRicevuta, float newIva, float newSconto, Date newData);
	
	/***
	 * elenco di tutte le vendite realizzate anche quelle che non possiedono uno scrontrino
	 * @return
	 * una lista contenente tutte le vendite
	 */
	public static List<modelSalesI> salesList(){
		DataBaseSearch query = DataBaseSearch.queryDaTabella("Acquisti");
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
	public static boolean newSale(Integer IDCliente, int nRicevuta, float iva, float sconto, Date data, List<transactionsProductsI> prodotti){
		
		modelSales temp = new modelSales(TableRow.oggettoDaTabella("Vendite"));
		temp.setIDClient(IDCliente);
		temp.setDiscount(sconto);
		temp.setDate(data);
		temp.setIVA(iva);
		temp.setNumberPaymentReceipt(nRicevuta);
		if(temp.builderProductsSale(nRicevuta, prodotti))
			return temp.oggetto.salva();
		else 
			return false;
	}
	
	public static List<modelSalesI> reportSales(){
		
	Comparator<modelSalesI> sort = (primo, secondo) -> Double.compare(primo.getTotalColleactions(), secondo.getTotalColleactions());
			
			return modelSalesI.salesList().stream()
					.sorted(sort)
					.collect(Collectors.toList());
	}

}