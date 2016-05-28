package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.sql.Date;
import com.goytia.classiAusiliari.*;
import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelScontrini {
	
	MBOggetto oggetto;
	
	private modelScontrini(MBOggetto temp){
		this.oggetto=temp;
	}
	/***
	 * Ottengo un record nuovo dalla tabella Scontrini
	 */
	public modelScontrini(){
		this.oggetto = MBOggetto.oggettoDaTabella("Scontrini");
	}
	
	private void setScontrino(int nScontrino){
		this.oggetto.setObjectValue("nScontrino", nScontrino);
	}
	private void setCliente(Integer idCliente){
		this.oggetto.setObjectValue("IDCliente", idCliente);
	}
	
	private void setData(Date data){
		this.oggetto.setObjectValue("Data", data);
	}
	
	private void setIva(float iva){
		this.oggetto.setObjectValue("IVA", iva);
	}
	
	private void setRicevuta(int nRicevuta){
		this.oggetto.setObjectValue("nRicevuta", nRicevuta);
	}
	
	private int getRicevuta(){
		return (int)this.oggetto.getObject("nRicevuta");
	}
	
	public int getNscontrino(){
		return (int)this.oggetto.getObject("nScontrino");
	}
	
	public Integer getIDCliente(){
		return (Integer)this.oggetto.getObject("IDCliente");
	}
	
	public float getIVA(){
		return (float) this.oggetto.getObject("IVA");
	}
	
	public Date getData(){
		return (Date)this.oggetto.getObject("Data");
	}
	/***
	 * metodo alternativo che ritorno il cliente con tutti i dati filtrando con l'idCliente
	 * @return
	 * lo specifico cliente a qui va rivolta lo scontrino
	 */
	public modelClienti cliente(){
		return modelClienti.elenco().stream()
				.filter(c -> c.getID().equals(this.getIDCliente()))
				.findFirst()
				.get();
	}
	/***
	 * meotodo che ritorna tutti i prodotti da insierire nello scontrino mappato con la classe prodttoVenduto
	 * @return
	 * una lista ocn tutti i prodotti sottoforma di prodottoVenduto
	 */
	public List<prodottoVenduto> prodottiVenduti(){
		return modelMovimenti.elenco().stream()
				.filter( m -> m.getNricevuta() == this.getRicevuta())
				.map( p -> {
					prodottoVenduto prod = new prodottoVenduto(p.getIDProdotto(), Math.abs(p.getQuantita()), p.getPrezzo());
					return prod;
				})
				.collect(Collectors.toList());
				
	}
	/***
	 * metodo che ritorna elenco di tutti gli scontrini
	 * @return
	 * un lista cont utti gli scontrini esistenti fino a quel momento
	 */
	public static List<modelScontrini> elenco(){
		MBQuery query = MBQuery.queryDaTabella("Scontrini");
		try {
			return query.find().stream()
					.map(e -> new modelScontrini(e))
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
	public boolean creaScontrino(int nScontrino, int nRicevuta, Integer idCliente, Date data, float iva){
		
		modelScontrini nuovo = new modelScontrini(MBOggetto.oggettoDaTabella("Scontrini"));
		nuovo.setCliente(idCliente);
		nuovo.setData(data);
		nuovo.setIva(iva);
		nuovo.setRicevuta(nRicevuta);
		nuovo.setScontrino(nScontrino);
		return nuovo.oggetto.salva();
		
	}
	
	/***
	 * ricerca di uno specifico scontrino
	 * @param nScontrino
	 * numero dello scontrino a ricercare
	 * @return
	 * lo scontrino 
	 */
	public modelScontrini cercaScontrinoPerNumero(int nScontrino){
		
		return modelScontrini.elenco().stream()
				.filter(s -> s.getNscontrino() == nScontrino)
				.findFirst()
				.get();
	}
}


