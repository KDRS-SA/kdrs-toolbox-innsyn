package no.arkivlab.innsyn.models.n5;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "basic_record")
@Inheritance(strategy = InheritanceType.JOINED)
public class BasicRecord extends Record implements Serializable {

	private static final long serialVersionUID = 1L;

	/** M004 - registreringsID (xs:string) */
	@Column(name = "record_id")
	protected String recordId;

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

	// Link to StorageLocation
	@ManyToOne
	@JoinColumn(name = "basic_record_storage_location_id", referencedColumnName = "pk_storage_location_id")
	@JsonBackReference
	protected StorageLocation referenceStorageLocation;

	// Links to Keywords
	@ManyToMany
	@JoinTable(name = "basic_record_keyword", joinColumns = @JoinColumn(name = "f_pk_record_id", referencedColumnName = "pk_record_id"), inverseJoinColumns = @JoinColumn(name = "f_pk_keyword_id", referencedColumnName = "pk_keyword_id"))
	@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
	@JsonIgnore
	protected Set<Keyword> referenceKeyword = new HashSet<Keyword>();

	// Links to Authors
	@ManyToMany
	@JoinTable(name = "basic_record_author", joinColumns = @JoinColumn(name = "f_pk_record_id", referencedColumnName = "pk_record_id"), inverseJoinColumns = @JoinColumn(name = "f_pk_author_id", referencedColumnName = "pk_author_id"))
	@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
	@JsonIgnore
	protected Set<Author> referenceAuthor = new HashSet<Author>();

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
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

	public Set<Author> getReferenceAuthor() {
		return referenceAuthor;
	}

	public void setReferenceAuthor(Set<Author> referenceAuthor) {
		this.referenceAuthor = referenceAuthor;
	}

	@Override
	public String toString() {
		return "BasicRecord [recordId=" + recordId + ", title=" + title
				+ ", officialTitle=" + officialTitle + ", description="
				+ description + ", documentMedium=" + documentMedium
				+ ", referenceStorageLocation=" + referenceStorageLocation
				+ ", referenceKeyword=" + referenceKeyword
				+ ", referenceAuthor=" + referenceAuthor + "]";
	}

}
