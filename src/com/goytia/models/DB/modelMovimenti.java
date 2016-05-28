package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.goytia.classiAusiliari.prodottoVenduto;
import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelMovimenti {
	
MBOggetto oggetto;
	
	private modelMovimenti(MBOggetto temp){
		this.oggetto = temp;
	}
	
	public modelMovimenti(){
		this.oggetto = MBOggetto.oggettoDaTabella("Movimenti");
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
	
	public static List<modelMovimenti> elenco(){
		
		MBQuery query = MBQuery.queryDaTabella("Clienti");
		try {
			return query.find().stream()
					.map(e -> new modelMovimenti(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean prodottiNelMovimento(int nRicevuta, List<prodottoVenduto> lista, boolean ctrlVendita){
		
		boolean ctrl= true;
		for(prodottoVenduto p : lista){
			modelMovimenti temp = new modelMovimenti(MBOggetto.oggettoDaTabella("Movimenti"));
			temp.oggetto.setObjectValue("nRicevuta", nRicevuta );
			temp.oggetto.setObjectValue("IDProdotto", p.getIDProdottoV());
			if(ctrlVendita)
				temp.oggetto.setObjectValue("Quantita", -1 * p.getQuantita());
			else
				temp.oggetto.setObjectValue("Quantita", p.getQuantita());
			temp.oggetto.setObjectValue("Prezzo", p.getPrezzoUnitario());
			ctrl=temp.oggetto.salva();
		}
		return ctrl;
	}

}
