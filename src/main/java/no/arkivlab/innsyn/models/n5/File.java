package no.arkivlab.innsyn.models.n5;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "file")
@Inheritance(strategy = InheritanceType.JOINED)
public class File implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk_file_id", nullable = false, insertable = true, updatable = false)
	protected Long id;

	/** M001 - systemID (xs:string) */
	@Column(name = "system_id")
	protected String systemId;

	/** M003 - mappeID (xs:string) */
	@Column(name = "file_id")
	protected String fileId;

	/** M020 - tittel (xs:string) */
	@Column(name = "title")
	protected String title;

	/** M025 - offentligTittel (xs:string) */
	@Column(name = "official_title")
	protected String officialTitle;

	/** M021 - beskrivelse (xs:string) */
	@Column(name = "description")
	protected String description;

	/** M300 - dokumentmedium (xs:string) */
	@Column(name = "document_medium")
	protected String documentMedium;

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
	
	/** M602 - avsluttetDato (xs:dateTime) */
	@Column(name = "finalised_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date finalisedDate;

	/** M603 - avsluttetAv (xs:string) */
	@Column(name = "finalised_by")
	protected String finalisedBy;

	// Link to StorageLocation
	@ManyToOne
	@JoinColumn(name = "file_storage_location_id", referencedColumnName = "pk_storage_location_id")
	@JsonBackReference
	protected StorageLocation referenceStorageLocation;

	// Links to Keywords
	@ManyToMany
	@JoinTable(name = "file_keyword", joinColumns = @JoinColumn(name = "f_pk_file_id", referencedColumnName = "pk_file_id"), inverseJoinColumns = @JoinColumn(name = "f_pk_keyword_id", referencedColumnName = "pk_keyword_id"))
	@JsonBackReference
	protected Set<Keyword> referenceKeyword = new HashSet<Keyword>();

	// Link to parent File
	@ManyToOne
	@JsonBackReference
	protected File referenceParentFile;

	// Links to child Files
	@OneToMany(mappedBy = "referenceParentFile")
	@LazyCollection(LazyCollectionOption.EXTRA)
	@JsonManagedReference
	@JsonIgnore
	protected Set<File> referenceChildFile = new HashSet<File>();

	// Link to Series
	@ManyToOne
	@JoinColumn(name = "file_series_id", referencedColumnName = "pk_series_id")
	@JsonBackReference
	protected Series referenceSeries;

	// Link to Class
	@ManyToOne
	@JoinColumn(name = "file_class_id", referencedColumnName = "pk_class_id")
	@JsonBackReference
	protected Class referenceClass;

	// Links to Records
	@OneToMany(mappedBy = "referenceFile")
	@LazyCollection(LazyCollectionOption.EXTRA)
	@JsonManagedReference	
	@JsonIgnore
	protected Set<Record> referenceRecord = new HashSet<Record>();

	public Long getId() {
		return id;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOfficialTitle() {
		return officialTitle;
	}

	public void setOfficialTitle(String officialTitle) {
		this.officialTitle = officialTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDocumentMedium() {
		return documentMedium;
	}

	public void setDocumentMedium(String documentMedium) {
		this.documentMedium = documentMedium;
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

	public Date getFinalisedDate() {
		return finalisedDate;
	}

	public void setFinalisedDate(Date finalisedDate) {
		this.finalisedDate = finalisedDate;
	}

	public String getFinalisedBy() {
		return finalisedBy;
	}

	public void setFinalisedBy(String finalisedBy) {
		this.finalisedBy = finalisedBy;
	}

	public StorageLocation getReferenceStorageLocation() {
		return referenceStorageLocation;
	}

	public void setReferenceStorageLocation(
			StorageLocation referenceStorageLocation) {
		this.referenceStorageLocation = referenceStorageLocation;
	}

	public Set<Keyword> getReferenceKeyword() {
		return referenceKeyword;
	}

	public void setReferenceKeyword(Set<Keyword> referenceKeyword) {
		this.referenceKeyword = referenceKeyword;
	}

	public File getReferenceParentFile() {
		return referenceParentFile;
	}

	public void setReferenceParentFile(File referenceParentFile) {
		this.referenceParentFile = referenceParentFile;
	}

	public Set<File> getReferenceChildFile() {
		return referenceChildFile;
	}

	public void setReferenceChildFile(Set<File> referenceChildFile) {
		this.referenceChildFile = referenceChildFile;
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

	public Set<Record> getReferenceRecord() {
		return referenceRecord;
	}

	public void setReferenceRecord(Set<Record> referenceRecord) {
		this.referenceRecord = referenceRecord;
	}

	@Override
	public String toString() {
		return "File [id=" + id + ", systemId=" + systemId + ", fileId="
				+ fileId + ", title=" + title + ", officialTitle="
				+ officialTitle + ", description=" + description
				+ ", documentMedium=" + documentMedium + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", finalisedDate="
				+ finalisedDate + ", finalisedBy=" + finalisedBy;
	}

}
