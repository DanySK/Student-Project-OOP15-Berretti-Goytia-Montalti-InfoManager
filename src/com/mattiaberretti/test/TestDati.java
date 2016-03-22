package com.mattiaberretti.test;

import static org.junit.Assert.*;

import java.util.List;
import java.sql.SQLException;
import java.util.Optional;

import org.junit.Test;

import com.mattiaberretti.clienti.ICliente;
import com.mattiaberretti.database.GestioneDB;
import com.mattiaberretti.utenti.IUtente;

public class TestDati {

	@Test
	public void testCreaDb() {
		try {
			GestioneDB.generaDB();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			fail("Errore durante la creazione del db");
		}
	}
	
	@Test
	public void testUtenti(){
		try {
			GestioneDB db = GestioneDB.generaControllore();
			db.connetti();
			db.eliminaRecord("Utenti", Optional.empty(), Optional.empty());
			db.disconnetti();
		} catch (ClassNotFoundException | SQLException e) {
			fail("Impossibile pulire il db utenti");
			e.printStackTrace();
		}
		
		try {
			assertFalse("Utente gia in memoria?", IUtente.utenteCorrente.getUtenteCorrente().isPresent());
			Optional<IUtente> utente = IUtente.accedi("Prova", "123");
			assertFalse("Non doveva accedere (il db è vuoto)", utente.isPresent());
			
			utente = IUtente.registrati("Nome", "Cognome", "Prova", "123");
			assertTrue("Non è stato registrato", utente.isPresent());
			
			assertTrue("Utente corrente non salvato in registrazione", IUtente.utenteCorrente.getUtenteCorrente().isPresent());
			
			utente = IUtente.accedi("Prova", "123");
			assertTrue("Non ha eseguito accesso", utente.isPresent());
			
			assertTrue("Utente corrente non salvato in registrazione", IUtente.utenteCorrente.getUtenteCorrente().isPresent());
			
		} catch (ClassNotFoundException | SQLException e) {
			fail("Errore uso db");
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testClienti(){
		GestioneDB db = GestioneDB.generaControllore();
		try {
			db.connetti();
			db.eliminaRecord("Utenti", Optional.empty(), Optional.empty());
			db.eliminaRecord("Clienti", Optional.empty(), Optional.empty());
			db.disconnetti();
			
			assertTrue("Errore durante l'accesso", IUtente.registrati("Nome", "Cognome", "Prova", "123").isPresent());
			
			Optional<ICliente> nuovo = new ICliente.Builder("Nome", "Cognome")
					.setMail("Mail")
					.setTelefono("Telefono")
					.build();
			
			List<ICliente> elenco = ICliente.elencoClienti();
			assertFalse("Cliente non aggiunto", elenco.size() == 0);
			
			nuovo = new ICliente.Builder("Nome", "Cognome")
					.setMail("Mail")
					.setTelefono("Telefono")
					.build();
			
			assertFalse("L'utente non doveva essere salvato", nuovo.isPresent());
			
			assertTrue("Lista clienti troppo lunga doveva essere 1", ICliente.elencoClienti().size() == 1);
			
			
			

			IUtente.utenteCorrente.setUtenteCorrente(null);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			fail("Errore uso db");
		}
	}

}
