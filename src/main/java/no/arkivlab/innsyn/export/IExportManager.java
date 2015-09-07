package no.arkivlab.innsyn.export;

import no.arkivlab.innsyn.models.n5.Fonds;

public interface IExportManager {
	public void exportArkivstrukturFromFonds(Fonds fonds);
}
