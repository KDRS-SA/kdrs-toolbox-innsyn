package no.arkivlab.innsyn.operations.iface;


import no.arkivlab.innsyn.models.n5.Series;

public interface ISeriesService {
	public Iterable <Series> findAll(Integer pageNumber, Integer pageSize);	
	public Iterable <Series> findAll();
	public Series findBySystemId(String systemId);	
	public Iterable <Series> findByCreatedDate(String createdDate);
	public Iterable <Series> findByCreatedBy(String createdBy);
}
