package no.arkivlab.innsyn.operations.iface;

import no.arkivlab.innsyn.models.n5.ClassificationSystem;

public interface IClassificationSystemService {
	public Iterable<ClassificationSystem> findAll();
	public Iterable <ClassificationSystem> findAll(Integer pageNumber, Integer pageSize);	
	public ClassificationSystem findBySystemId(String systemId);	
	public Iterable <ClassificationSystem> findByCreatedDate(String createdDate);
	public Iterable <ClassificationSystem> findByCreatedBy(String createdBy);
}
