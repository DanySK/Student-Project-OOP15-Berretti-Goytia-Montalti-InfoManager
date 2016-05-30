package com.infoMng.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import com.goytia.models.DB.modelClients;
import com.goytia.models.DB.modelProviders;
import com.goytia.models.DB.modelStore;
import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;


public class Fattura implements IFattura {
	MBOggetto collegamento;
	
	protected Fattura(Integer idFattura) throws SQLException{
		MBQuery query = MBQuery.queryDaTabella("Fatture");
		query.whereEqualTo("objectId", idFattura);
		this.collegamento = query.getFirst();
	}

	@Override
	public List<prodottoFattura> getProdotti() {
		MBQuery query = MBQuery.queryDaTabella("ProdottoVendita");
		query.whereEqualTo("IDFattura", this.collegamento.objectId());
		
		
		try {
			return query.find().stream()
					.map(co -> {
						prodottoFattura nuovo = new prodottoFattura();
						nuovo.quantita = (Integer)co.getObject("Quantita");
						nuovo.prezzo = (Double)co.getObject("Prezzo");
						Integer idProdotto = (Integer)co.getObject("IDProdotto");
						
						modelStore prodotto = modelStore.elenco().stream().filter(p -> p.getID().equals(idProdotto)).findFirst().get();
						nuovo.prodotto = prodotto;
						return nuovo;
						
					})
					.collect(Collectors.toList());
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Date getData() {
		return (Date) this.collegamento.getObject("Data");
	}

	@Override
	public Integer getNumeroOrdine() {
		return (Integer) this.collegamento.getObject("Numero");
	}

	@Override
	public modelClients getCliente() {
		Integer idCliente = (Integer) this.collegamento.getObject("IDCliente");
		
		if(idCliente != null){
			return modelClients.clientsList().stream().filter(c -> c.getID().equals(idCliente)).findFirst().get();
		}
		return null;
		
	}

	@Override
	public modelProviders getFornitore() {
		Integer idCliente = (Integer) this.collegamento.getObject("IDFornitore");
		
		if(idCliente != null){
			return modelProviders.providersList().stream().filter(f -> f.getID().equals(idCliente)).findFirst().get();
		}
		return null;
	}

	@Override
	public Date[] getConsegna() {
		Date da = (Date) this.collegamento.getObject("da");
		Date al = (Date) this.collegamento.getObject("al");
		return new Date[]{da, al};
	}

	@Override
	public String getTipoOrdine() {
		return (String) this.collegamento.getObject("TipoOrdine");
	}

	@Override
	public String getNomeNegozio() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTipoPagamento() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBanca() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNote() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getSconto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getIVA() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
