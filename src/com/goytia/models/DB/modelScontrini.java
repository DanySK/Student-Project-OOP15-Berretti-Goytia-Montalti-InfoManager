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
	
	public modelScontrini(){
		this.oggetto = MBOggetto.oggettoDaTabella("Scontrini");
	}
	
}


