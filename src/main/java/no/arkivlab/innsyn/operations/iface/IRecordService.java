package no.arkivlab.innsyn.operations.iface;

import no.arkivlab.innsyn.models.n5.Record;

public interface IRecordService {
	public Iterable<Record> findAll();
	public Iterable <Record> findAll(Integer pageNumber, Integer pageSize);	
	public Record findBySystemId(String systemId);	
	public Iterable <Record> findByCreatedDate(String createdDate);
	public Iterable <Record> findByCreatedBy(String createdBy);
}
