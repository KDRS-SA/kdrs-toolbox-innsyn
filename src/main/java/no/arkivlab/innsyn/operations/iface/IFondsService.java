package no.arkivlab.innsyn.operations.iface;

import java.util.List;

import no.arkivlab.innsyn.models.n5.Fonds;

public interface IFondsService {
	public List<Fonds> getAllFonds();		
    public Fonds getFondsBySystemId(String systemId);
    public List<Fonds> getFondsByCreatedDate(String createdDate);
	public Fonds getFondsById(Long id);
}

