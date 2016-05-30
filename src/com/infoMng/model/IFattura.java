package com.infoMng.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goytia.models.DB.modelClients;
import com.goytia.models.DB.modelProviders;
import com.goytia.models.DB.modelStore;
import com.infoMng.controller.MBOggetto;

public interface IFattura {
	
	List<prodottoFattura> getProdotti();
	
	Date getData();
	Integer getNumeroOrdine();
	
	modelClients getCliente();
	modelProviders getFornitore();
	
	Date[] getConsegna();
	
	String getTipoOrdine();
	
	String getNomeNegozio();
	
	String getTipoPagamento();
	
	String getBanca();
	
	String getNote();
	
	Integer getSconto();
	Integer getIVA();
	
	
	public static class FatturaBuilder{
		List<prodottoFattura> prodotti;
		
		Date data;
		Integer numeroOrdine;
		
		modelClients cliente;
		modelProviders fornitore;
		
		Date[] consegna;
		
		String tipoOrdine;
		
		String nomeNegozio;
		
		String tipoPagamento;
		
		String banca;
		
		String note;
		
		Integer sconto;
		Integer IVA;
	
		public FatturaBuilder(){
			this.prodotti = new ArrayList<>();
			consegna = new Date[2];
			
		}
		
		public IFattura salva(){
			MBOggetto padre = MBOggetto.oggettoDaTabella("Fatture");
			padre.setObjectValue("Numero", numeroOrdine);
			if(this.cliente != null){
				padre.setObjectValue("IDCliente", this.cliente.getID());
			}
			else {
				padre.setObjectValue("IDFornitore", this.fornitore.getID());
			}
			
			padre.setObjectValue("Data", this.data);
			
			padre.setObjectValue("da", this.consegna[0]);
			padre.setObjectValue("al", this.consegna[1]);
			padre.setObjectValue("TipoOrdine", this.tipoOrdine);
			padre.setObjectValue("NomeNegozio", this.nomeNegozio);
			padre.setObjectValue("TipoPagamento", this.tipoPagamento);
			padre.setObjectValue("Banca", this.banca);
			padre.setObjectValue("Sconto", this.sconto);
			padre.setObjectValue("IVA", this.IVA);
			
			if(padre.salva()){
				this.prodotti.stream()
				.map(p -> {
					MBOggetto figlio = MBOggetto.oggettoDaTabella("ProdottoVendita");
					figlio.setObjectValue("IDProdotto", p.prodotto.getID());
					figlio.setObjectValue("Quantita", p.quantita);
					figlio.setObjectValue("Prezzo", p.prezzo);
					figlio.setObjectValue("IDFattura", padre.objectId());
					return figlio;
				})
				.forEach(c -> c.salva());
			}
			
			try {
				return new Fattura(padre.objectId());
			} catch (SQLException e) {
				return null;
			}
		}
		
	}
	
	public static class prodottoFattura{
		modelStore prodotto;
		Integer quantita;
		Double prezzo;
	}
}
