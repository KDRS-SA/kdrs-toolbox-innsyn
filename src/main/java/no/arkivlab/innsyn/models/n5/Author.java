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
import javax.persistence.Table;

@Entity
@Table(name = "author")
public class Author implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk_author_id", nullable = false, insertable = true, updatable = false)
	protected long id;

	/** M001 - systemID (xs:string) */
	@Column(name = "system_id")
	protected String systemId;

	/** M024 - forfatter (xs:string) */
	@Column(name = "author")
	protected String author;

	// Links to BasicRecords
	@ManyToMany(mappedBy = "referenceAuthor")
	protected Set<BasicRecord> referenceBasicRecord = new HashSet<BasicRecord>();

	// Links to DocumentDescription
	@ManyToMany(mappedBy = "referenceAuthor")
	protected Set<DocumentDescription> referenceDocumentDescription = new HashSet<DocumentDescription>();

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Set<BasicRecord> getReferenceBasicRecord() {
		return referenceBasicRecord;
	}

	public void setReferenceBasicRecord(Set<BasicRecord> referenceBasicRecord) {
		this.referenceBasicRecord = referenceBasicRecord;
	}

	public Set<DocumentDescription> getReferenceDocumentDescription() {
		return referenceDocumentDescription;
	}

	public void setReferenceDocumentDescription(Set<DocumentDescription> referenceDocumentDescription) {
		this.referenceDocumentDescription = referenceDocumentDescription;
	}
	
	@Override
	public String toString() {
		return "Author [id=" + id + ", systemId=" + systemId + ", author="
				+ author + ", referenceBasicRecord=" + referenceBasicRecord
				+ "]";
	}

}
