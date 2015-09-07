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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "fonds")
@XmlRootElement(name = "arkiv")
//@XmlType(propOrder = {"name", "age", "role", "gender"})
public class Fonds implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk_fonds_id", nullable = false, insertable = true, updatable = false)
	@XmlTransient
	protected Long id;

	/** M001 - systemID (xs:string) */
	@XmlElement(name = "systemID")
	@Column(name = "system_id")
	protected String systemId;

	/** M020 - tittel (xs:string) */
	@XmlElement(name = "tittel")
	@Column(name = "title")
	protected String title;

	/** M021 - beskrivelse (xs:string) */
	@XmlElement(name = "beskrivelse")
	@Column(name = "description")
	protected String description;

	/** M050 - arkivstatus (xs:string) */
	@XmlElement(name = "arkivstatus")
	@Column(name = "fonds_status")
	protected String fondsStatus;

	/** M300 - dokumentmedium (xs:string) */
	@XmlElement(name = "dokumentmedium")
	@Column(name = "document_medium")
	protected String documentMedium;

	/** M600 - opprettetDato (xs:dateTime) */
	@XmlElement(name = "opprettetDato")
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;

	/** M601 - opprettetAv (xs:string) */
	@XmlElement(name = "opprettetAv")
	@Column(name = "created_by")
	protected String createdBy;

	/** M602 - avsluttetDato (xs:dateTime) */
	@Column(name = "finalised_date")
	@Temporal(TemporalType.TIMESTAMP)
	@XmlElement(name = "avsluttetDato")
	protected Date finalisedDate;

	/** M603 - avsluttetAv (xs:string) */
	@Column(name = "finalised_by")
	@XmlElement(name = "avsluttetAv")
	protected String finalisedBy;

	// Links to Series
	@OneToMany(mappedBy = "referenceFonds")
	@LazyCollection(LazyCollectionOption.EXTRA)
	@JsonIgnore	
	protected Set<Series> referenceSeries = new HashSet<Series>();

	// Link to parent Fonds
	@ManyToOne
	protected Fonds referenceParentFonds;

	// Links to child Fonds
	@OneToMany(mappedBy = "referenceParentFonds")
	@LazyCollection(LazyCollectionOption.EXTRA)
	@JsonManagedReference
	@JsonIgnore
	protected Set<Fonds> referenceChildFonds = new HashSet<Fonds>();

	// Links to StorageLocations
	@ManyToMany
	@JoinTable(name = "fonds_storage_location", joinColumns = @JoinColumn(name = "f_pk_fonds_id", referencedColumnName = "pk_fonds_id"), inverseJoinColumns = @JoinColumn(name = "f_pk_storage_location_id", referencedColumnName = "pk_storage_location_id"))
	@LazyCollection(LazyCollectionOption.EXTRA)
	@JsonIgnore
	protected Set<StorageLocation> referenceStorageLocation = new HashSet<StorageLocation>();

	// Links to FondsCreators
	@ManyToMany
	@JoinTable(name = "fonds_fonds_creator", joinColumns = @JoinColumn(name = "f_pk_fonds_id", referencedColumnName = "pk_fonds_id"), inverseJoinColumns = @JoinColumn(name = "f_pk_fonds_creator_id", referencedColumnName = "pk_fonds_creator_id"))
	@LazyCollection(LazyCollectionOption.EXTRA)
	@JsonIgnore
	protected Set<FondsCreator> referenceFondsCreator = new HashSet<FondsCreator>();

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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

	public String getFondsStatus() {
		return fondsStatus;
	}

	public void setFondsStatus(String fondsStatus) {
		this.fondsStatus = fondsStatus;
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

	public Set<Series> getReferenceSeries() {
		return referenceSeries;
	}

	public void setReferenceSeries(Set<Series> referenceSeries) {
		this.referenceSeries = referenceSeries;
	}

	public Fonds getReferenceParentFonds() {
		return referenceParentFonds;
	}

	public void setReferenceParentFonds(Fonds referenceParentFonds) {
		this.referenceParentFonds = referenceParentFonds;
	}

	public Set<Fonds> getReferenceChildFonds() {
		return referenceChildFonds;
	}

	public void setReferenceChildFonds(Set<Fonds> referenceChildFonds) {
		this.referenceChildFonds = referenceChildFonds;
	}

	public Set<StorageLocation> getReferenceStorageLocation() {
		return referenceStorageLocation;
	}

	public void setReferenceStorageLocation(
			Set<StorageLocation> referenceStorageLocation) {
		this.referenceStorageLocation = referenceStorageLocation;
	}

	public Set<FondsCreator> getReferenceFondsCreator() {
		return referenceFondsCreator;
	}

	public void setReferenceFondsCreator(Set<FondsCreator> referenceFondsCreator) {
		this.referenceFondsCreator = referenceFondsCreator;
	}

	@Override
	public String toString() {
		return "Fonds [id=" + id + ", systemId=" + systemId + ", title="
				+ title + ", description=" + description + ", fondsStatus="
				+ fondsStatus + ", documentMedium=" + documentMedium
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", finalisedDate=" + finalisedDate + ", finalisedBy="
				+ finalisedBy;
	}


}
