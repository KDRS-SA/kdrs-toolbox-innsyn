package no.arkivlab.innsyn.operations.iface;

import no.arkivlab.innsyn.models.n5.Class;

public interface IClassService {
	public Iterable<Class> findAll();
	public Iterable <Class> findAll(Integer pageNumber, Integer pageSize);	
	public Class findBySystemId(String systemId);	
	public Iterable <Class> findByCreatedDate(String createdDate);
	public Iterable <Class> findByCreatedBy(String createdBy);
}
