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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "series")
public class Series implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk_series_id", nullable = false, insertable = true, updatable = false)
	protected long id;

	/** M001 - systemID (xs:string) */
	@Column(name = "system_id")
	protected String systemId;

	/** M020 - tittel (xs:string) */
	@Column(name = "title")
	protected String title;

	/** M021 - beskrivelse (xs:string) */
	@Column(name = "description")
	protected String description;

	/** M051 - arkivdelstatus (xs:string) */
	@Column(name = "series_status")
	protected String seriesStatus;

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

	/** M602 - avsluttetDato (xs:dateTime) */
	@Column(name = "finalised_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date finalisedDate;

	/** M603 - avsluttetAv (xs:string) */
	@Column(name = "finalised_by")
	protected String finalisedBy;

	/** M107 - arkivperiodeStartDato (xs:date) */
	@Column(name = "series_start_date")
	@Temporal(TemporalType.DATE)
	protected Date seriesStartDate;

	/** M108 - arkivperiodeSluttDato (xs:date) */
	@Column(name = "series_end_date")
	@Temporal(TemporalType.DATE)
	protected Date seriesEndDate;

	// Links to StorageLocations
	@ManyToMany
	@JoinTable(name = "series_storage_location", joinColumns = @JoinColumn(name = "f_pk_series_id", referencedColumnName = "pk_series_id"), inverseJoinColumns = @JoinColumn(name = "f_pk_storage_location_id", referencedColumnName = "pk_storage_location_id"))
	@JsonIgnore
	protected Set<StorageLocation> referenceStorageLocation = new HashSet<StorageLocation>();

	// Link to Fonds
	@ManyToOne
	@JoinColumn(name = "series_fonds_id", referencedColumnName = "pk_fonds_id")
	@JsonBackReference
	protected Fonds referenceFonds;

	// Link to precursor Series
	@OneToOne
	protected Series referencePrecursor;

	// Link to successor Series
	@OneToOne(mappedBy = "referencePrecursor")
	protected Series referenceSuccessor;

	// Link to ClassificationSystem
	@ManyToOne
	@JoinColumn(name = "series_classification_system_id", referencedColumnName = "pk_classification_system_id")
	@JsonBackReference
	protected ClassificationSystem referenceClassificationSystem;

	// Links to Files
	@OneToMany(mappedBy = "referenceSeries")
	@LazyCollection(LazyCollectionOption.EXTRA)
	@JsonManagedReference
	@JsonIgnore
	protected Set<File> referenceFile = new HashSet<File>();

	// Links to Records
	@OneToMany(mappedBy = "referenceSeries")
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

	public String getSeriesStatus() {
		return seriesStatus;
	}

	public void setSeriesStatus(String seriesStatus) {
		this.seriesStatus = seriesStatus;
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

	public Date getSeriesStartDate() {
		return seriesStartDate;
	}

	public void setSeriesStartDate(Date seriesStartDate) {
		this.seriesStartDate = seriesStartDate;
	}

	public Date getSeriesEndDate() {
		return seriesEndDate;
	}

	public void setSeriesEndDate(Date seriesEndDate) {
		this.seriesEndDate = seriesEndDate;
	}

	public Set<StorageLocation> getReferenceStorageLocation() {
		return referenceStorageLocation;
	}

	public void setReferenceStorageLocation(
			Set<StorageLocation> referenceStorageLocation) {
		this.referenceStorageLocation = referenceStorageLocation;
	}

	public Fonds getReferenceFonds() {
		return referenceFonds;
	}

	public void setReferenceFonds(Fonds referenceFonds) {
		this.referenceFonds = referenceFonds;
	}

	public Series getReferencePrecursor() {
		return referencePrecursor;
	}

	public void setReferencePrecursor(Series referencePrecursor) {
		this.referencePrecursor = referencePrecursor;
	}

	public Series getReferenceSuccessor() {
		return referenceSuccessor;
	}

	public void setReferenceSuccessor(Series referenceSuccessor) {
		this.referenceSuccessor = referenceSuccessor;
	}

	public ClassificationSystem getReferenceClassificationSystem() {
		return referenceClassificationSystem;
	}

	public void setReferenceClassificationSystem(
			ClassificationSystem referenceClassificationSystem) {
		this.referenceClassificationSystem = referenceClassificationSystem;
	}

	public Set<File> getReferenceFile() {
		return referenceFile;
	}

	public void setReferenceFile(Set<File> referenceFile) {
		this.referenceFile = referenceFile;
	}

	public Set<Record> getReferenceRecord() {
		return referenceRecord;
	}

	public void setReferenceRecord(Set<Record> referenceRecord) {
		this.referenceRecord = referenceRecord;
	}

	@Override
	public String toString() {
		return "Series [id=" + id + ", systemId=" + systemId + ", title="
				+ title + ", description=" + description + ", seriesStatus="
				+ seriesStatus + ", documentMedium=" + documentMedium
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", finalisedDate=" + finalisedDate + ", finalisedBy="
				+ finalisedBy + ", seriesStartDate=" + seriesStartDate
				+ ", seriesEndDate=" + seriesEndDate;
	}
}
