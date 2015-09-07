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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "record")
@Inheritance(strategy = InheritanceType.JOINED)
public class Record implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk_record_id", nullable = false, insertable = true, updatable = false)
	protected long id;

	/** M001 - systemID (xs:string) */
	@Column(name = "system_id")
	protected String systemId;

	/** M600 - opprettetDato (xs:dateTime) */
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;

	/** M601 - opprettetAv (xs:string) */
	@Column(name = "created_by")
	protected String createdBy;

	/** Not Noark5, added to help keep integrity from n4 to n5 */
	@Column(name = "created_by_id")
	protected Integer createdById;
	
	/** M604 - arkivertDato (xs:dateTime) */
	@Column(name = "archived_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date archivedDate;

	/** M605 - arkivertAv (xs:string) */
	@Column(name = "archived_by")
	protected String archivedBy;

	// Link to File
	@ManyToOne
	@JoinColumn(name = "record_file_id", referencedColumnName = "pk_file_id")
	@JsonBackReference
	protected File referenceFile;

	// Link to Series
	@ManyToOne
	@JoinColumn(name = "record_series_id", referencedColumnName = "pk_series_id")
	@JsonBackReference
	protected Series referenceSeries;

	// Link to Class
	@ManyToOne
	@JoinColumn(name = "record_class_id", referencedColumnName = "pk_class_id")
	@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
	protected Class referenceClass;

	// Links to DocumentDescriptions
	@ManyToMany
	@JoinTable(name = "record_document_description", joinColumns = @JoinColumn(name = "f_pk_record_id", referencedColumnName = "pk_record_id"), inverseJoinColumns = @JoinColumn(name = "f_pk_document_description_id", referencedColumnName = "pk_document_description_id"))
	@JsonIgnore
	protected Set<DocumentDescription> referenceDocumentDescription = new HashSet<DocumentDescription>();

	// Links to DocumentObjects
	@OneToMany(mappedBy = "referenceRecord")
	@JsonManagedReference	
	@JsonIgnore
	protected Set<DocumentObject> referenceDocumentObject = new HashSet<DocumentObject>();
	
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

	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public Date getArchivedDate() {
		return archivedDate;
	}

	public void setArchivedDate(Date archivedDate) {
		this.archivedDate = archivedDate;
	}

	public String getArchivedBy() {
		return archivedBy;
	}

	public void setArchivedBy(String archivedBy) {
		this.archivedBy = archivedBy;
	}

	public File getReferenceFile() {
		return referenceFile;
	}

	public void setReferenceFile(File referenceFile) {
		this.referenceFile = referenceFile;
	}

	public Series getReferenceSeries() {
		return referenceSeries;
	}

	public void setReferenceSeries(Series referenceSeries) {
		this.referenceSeries = referenceSeries;
	}

	public Class getReferenceClass() {
		return referenceClass;
	}

	public void setReferenceClass(Class referenceClass) {
		this.referenceClass = referenceClass;
	}

	public Set<DocumentDescription> getReferenceDocumentDescription() {
		return referenceDocumentDescription;
	}

	public void setReferenceDocumentDescription(
			Set<DocumentDescription> referenceDocumentDescription) {
		this.referenceDocumentDescription = referenceDocumentDescription;
	}

	public Set<DocumentObject> getReferenceDocumentObject() {
		return referenceDocumentObject;
	}

	public void setReferenceDocumentObject(
			Set<DocumentObject> referenceDocumentObject) {
		this.referenceDocumentObject = referenceDocumentObject;
	}

	public Screening getScreening() {
		return screening;
	}

	public void setScreening(Screening screening) {
		this.screening = screening;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", systemId=" + systemId + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", archivedDate="
				+ archivedDate + ", archivedBy=" + archivedBy
				+ ", referenceFile=" + referenceFile + ", referenceSeries="
				+ referenceSeries + ", referenceClass=" + referenceClass
				+ ", referenceDocumentDescription="
				+ referenceDocumentDescription + ", referenceDocumentObject="
				+ referenceDocumentObject + "]";
	}

}
