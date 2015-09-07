package no.arkivlab.innsyn.models.n5;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "storage_location")
public class StorageLocation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk_storage_location_id", nullable = false, insertable = true, updatable = false)
	private long id;

	/** M001 - systemID (xs:string) */
	@Column(name = "system_id")
	protected String systemId;

	/** M301 - oppbevaringssted (xs:string) */
	@Column(name = "storage_location")
	protected String storageLocation;

	// Links to Fonds
	@ManyToMany(mappedBy = "referenceStorageLocation")
	protected Set<Fonds> referenceFonds = new HashSet<Fonds>();

	// Links to Series
	@ManyToMany(mappedBy = "referenceStorageLocation")
	protected Set<Series> referenceSeries = new HashSet<Series>();

	// Links to Files
	@OneToMany(mappedBy = "referenceStorageLocation")
	protected Set<File> referenceFile = new HashSet<File>();

	// Links to BasicRecords
	@OneToMany(mappedBy = "referenceStorageLocation")
	protected Set<BasicRecord> referenceRecord = new HashSet<BasicRecord>();
	
	// Links to DocumentDescription
	@OneToMany(mappedBy = "referenceStorageLocation")
	protected Set<DocumentDescription> referenceDocumentDescription = new HashSet<DocumentDescription>();

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public Set<Fonds> getReferenceFonds() {
		return referenceFonds;
	}

	public void setReferenceFonds(Set<Fonds> referenceFonds) {
		this.referenceFonds = referenceFonds;
	}

	public Set<Series> getReferenceSeries() {
		return referenceSeries;
	}

	public void setReferenceSeries(Set<Series> referenceSeries) {
		this.referenceSeries = referenceSeries;
	}

	public Set<File> getReferenceFile() {
		return referenceFile;
	}

	public void setReferenceFile(Set<File> referenceFile) {
		this.referenceFile = referenceFile;
	}

	public Set<BasicRecord> getReferenceRecord() {
		return referenceRecord;
	}

	public void setReferenceRecord(Set<BasicRecord> referenceRecord) {
		this.referenceRecord = referenceRecord;
	}

	public Set<DocumentDescription> getReferenceDocumentDescription() {
		return referenceDocumentDescription;
	}

	public void setReferenceDocumentDescription(
			Set<DocumentDescription> referenceDocumentDescription) {
		this.referenceDocumentDescription = referenceDocumentDescription;
	}

	@Override
	public String toString() {
		return "StorageLocation [id=" + id + ", systemId=" + systemId
				+ ", storageLocation=" + storageLocation + ", referenceFonds="
				+ referenceFonds + ", referenceSeries=" + referenceSeries
				+ ", referenceFile=" + referenceFile + ", referenceRecord="
				+ referenceRecord + "]";
	}

}
