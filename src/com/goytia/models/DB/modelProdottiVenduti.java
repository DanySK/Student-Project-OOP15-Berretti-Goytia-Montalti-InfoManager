package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.goytia.classiAusiliari.prodottoVenduto;
import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelProdottiVenduti {

	MBOggetto oggetto;
	
	private modelProdottiVenduti(MBOggetto temp){
		this.oggetto = temp;
	}
	
	public modelProdottiVenduti(){
		this.oggetto = MBOggetto.oggettoDaTabella("ProdottiVenduti");
	}
	
	public Integer getIDProdotto(){
		return (Integer) this.oggetto.getObject("IDProdotto");
	}
	
	public int getNricevuta(){
		return (int)this.oggetto.getObject("nRicevuta");
	}
	
	public int getQuantita(){
		return (int)this.oggetto.getObject("Quantita");
	}
	
	public double getPrezzo(){
		return (double)this.oggetto.getObject("Prezzo");
	}
	
	public static List<modelProdottiVenduti> elenco(){
		
		MBQuery query = MBQuery.queryDaTabella("Clienti");
		try {
			return query.find().stream()
					.map(e -> new modelProdottiVenduti(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean prodottiVenduti(int nRicevuta, List<prodottoVenduto> lista){
		
		boolean ctrl= true;
		for(prodottoVenduto p : lista){
			modelProdottiVenduti temp = new modelProdottiVenduti(MBOggetto.oggettoDaTabella("ProdottiVenduti"));
			temp.oggetto.setObjectValue("nRicevuta", nRicevuta );
			temp.oggetto.setObjectValue("IDProdotto", p.getIDProdottoV());
			temp.oggetto.setObjectValue("Quantita", p.getQuantita());
			temp.oggetto.setObjectValue("Prezzo", p.getPrezzoUnitario());
			ctrl=temp.oggetto.salva();
		}
		return ctrl;
		
	}
}
