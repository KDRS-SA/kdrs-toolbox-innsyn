package no.arkivlab.innsyn.models.n5;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "document_description")
public class DocumentDescription implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk_document_description_id", nullable = false, insertable = true, updatable = false)
	protected long id;

	/** M001 - systemID (xs:string) */
	@Column(name = "system_id")
	protected String systemId;

	/** M083 - dokumenttype (xs:string) */
	@Column(name = "document_type")
	protected String documentType;

	/** M054 - dokumentstatus (xs:string) */
	@Column(name = "document_status")
	protected String documentStatus;

	/** M020 - tittel (xs:string) */
	@Column(name = "title")
	protected String title;

	/** M021 - beskrivelse (xs:string) */
	@Column(name = "description")
	protected String description;

		/** M600 - opprettetDato (xs:dateTime) */
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;

	/** M601 - opprettetAv (xs:string) */
	@Column(name = "created_by")
	protected String createdBy;

	/** M300 - dokumentmedium (xs:string) */
	@Column(name = "document_medium")
	protected String documentMedium;

	/** M217 - tilknyttetRegistreringSom (xs:string) */
	@Column(name = "associated_with_record_as")
	protected String associatedWithRecordAs;

	/** M007 - dokumentnummer (xs:integer) */
	@Column(name = "document_number")
	protected Integer documentNumber;

	/** M620 - tilknyttetDato (xs:date) */
	@Column(name = "association_date")
	@Temporal(TemporalType.DATE)
	protected Date associationDate;

	/** M621 - tilknyttetAv (xs:string) */
	@Column(name = "associated_by")
	protected String associatedBy;

	// Links to Records
	@ManyToMany(mappedBy = "referenceDocumentDescription")
	protected Set<Record> referenceRecord = new HashSet<Record>();

	// Links to DocumentObjects
	@OneToMany(mappedBy = "referenceDocumentDescription")
	protected Set<DocumentObject> referenceDocumentObject = new HashSet<DocumentObject>();

	// Links to Authors
	@ManyToMany
	@JoinTable(name = "document_description_author", joinColumns = @JoinColumn(name = "f_pk_record_id", referencedColumnName = "pk_document_description_id"), inverseJoinColumns = @JoinColumn(name = "f_pk_author_id", referencedColumnName = "pk_author_id"))
	@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
	@JsonIgnore
	protected Set<Author> referenceAuthor = new HashSet<Author>();

	// Link to StorageLocation
	@ManyToOne
	@JoinColumn(name = "document_description_storage_location_id", referencedColumnName = "pk_storage_location_id")
	@JsonBackReference
	protected StorageLocation referenceStorageLocation;
		
	@Embedded
	protected Screening screening;
	
	public Long getId() {		
		return id;
	}
	
	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(String documentStatus) {
		this.documentStatus = documentStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDocumentMedium() {
		return documentMedium;
	}

	public void setDocumentMedium(String documentMedium) {
		this.documentMedium = documentMedium;
	}

	public String getAssociatedWithRecordAs() {
		return associatedWithRecordAs;
	}

	public void setAssociatedWithRecordAs(String associatedWithRecordAs) {
		this.associatedWithRecordAs = associatedWithRecordAs;
	}

	public Integer getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(Integer documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Date getAssociationDate() {
		return associationDate;
	}

	public void setAssociationDate(Date associationDate) {
		this.associationDate = associationDate;
	}

	public String getAssociatedBy() {
		return associatedBy;
	}

	public void setAssociatedBy(String associatedBy) {
		this.associatedBy = associatedBy;
	}

	public Set<Record> getReferenceRecord() {
		return referenceRecord;
	}

	public void setReferenceRecord(Set<Record> referenceRecord) {
		this.referenceRecord = referenceRecord;
	}

	public Set<DocumentObject> getReferenceDocumentObject() {
		return referenceDocumentObject;
	}

	public Set<Author> getReferenceAuthor() {
		return referenceAuthor;
	}

	public void setReferenceAuthor(Set<Author> referenceAuthor) {
		this.referenceAuthor = referenceAuthor;
	}

	public void setReferenceDocumentObject(
			Set<DocumentObject> referenceDocumentObject) {
		this.referenceDocumentObject = referenceDocumentObject;
	}

	public StorageLocation getReferenceStorageLocation() {
		return referenceStorageLocation;
	}

	public void setReferenceStorageLocation(StorageLocation referenceStorageLocation) {
		this.referenceStorageLocation = referenceStorageLocation;
	}

	public Screening getScreening() {
		return screening;
	}

	public void setScreening(Screening screening) {
		this.screening = screening;
	}

	@Override
	public String toString() {
		return "DocumentDescription [id=" + id + ", systemId=" + systemId
				+ ", documentType=" + documentType + ", documentStatus="
				+ documentStatus + ", title=" + title + ", description="
				+ description + ", createdDate=" + createdDate + ", createdBy="
				+ createdBy + ", documentMedium=" + documentMedium
				+ ", associatedWithRecordAs=" + associatedWithRecordAs
				+ ", documentNumber=" + documentNumber + ", associationDate="
				+ associationDate + ", associatedBy=" + associatedBy
				+ ", referenceRecord=" + referenceRecord
				+ ", referenceDocumentObject=" + referenceDocumentObject + "]";
	}

}
