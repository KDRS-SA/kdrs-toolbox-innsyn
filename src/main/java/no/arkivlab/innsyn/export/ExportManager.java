package no.arkivlab.innsyn.export;

import no.arkivlab.innsyn.models.n5.Fonds;

import org.springframework.beans.factory.annotation.Autowired;


public class ExportManager implements IExportManager{
	
	@Autowired 
	private ArkivstrukturHandler arkivstrukturHandler;
	
	//private static final Logger logger = Logger.getLogger(ExportManager.class);
	
	public ExportManager() {
		
	}
	
	@Override
	public void exportArkivstrukturFromFonds(Fonds fonds){
		arkivstrukturHandler.startExportFromFonds(fonds);
	}
	
}
