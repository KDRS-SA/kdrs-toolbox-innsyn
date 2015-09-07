package no.arkivlab.innsyn.operations.iface;

import no.arkivlab.innsyn.models.n5.File;

public interface IFileService {
	public Iterable <File> findAll(Integer pageNumber, Integer pageSize);	
	public File findBySystemId(String systemId);	
	public Iterable <File> findByCreatedDate(String createdDate);
	public Iterable <File> findByCreatedBy(String createdBy);
}
