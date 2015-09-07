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
@Table(name = "keyword")
public class Keyword implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk_keyword_id", nullable = false, insertable = true, updatable = false)
	protected long id;

	/** M001 - systemID (xs:string) */
	@Column(name = "system_id")
	protected String systemId;

	/** M022 - noekkelord (xs:string) */
	@Column(name = "keyword")
	protected String keyword;

	// Links to Class
	@ManyToMany(mappedBy = "referenceKeyword")
	protected Set<Class> referenceClass = new HashSet<Class>();

	// Links to File
	@ManyToMany(mappedBy = "referenceKeyword")
	protected Set<File> referenceFile = new HashSet<File>();

	// Links to BasicRecord
	@ManyToMany(mappedBy = "referenceKeyword")
	protected Set<BasicRecord> referenceBasicRecord = new HashSet<BasicRecord>();

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Set<Class> getReferenceClass() {
		return referenceClass;
	}

	public void setReferenceClass(Set<Class> referenceClass) {
		this.referenceClass = referenceClass;
	}

	public Set<File> getReferenceFile() {
		return referenceFile;
	}

	public void setReferenceFile(Set<File> referenceFile) {
		this.referenceFile = referenceFile;
	}

	public Set<BasicRecord> getReferenceBasicRecord() {
		return referenceBasicRecord;
	}

	public void setReferenceBasicRecord(Set<BasicRecord> referenceBasicRecord) {
		this.referenceBasicRecord = referenceBasicRecord;
	}

	@Override
	public String toString() {
		return "Keyword [id=" + id + ", systemId=" + systemId + ", keyword="
				+ keyword + ", referenceClass=" + referenceClass
				+ ", referenceFile=" + referenceFile
				+ ", referenceBasicRecord=" + referenceBasicRecord + "]";
	}

}
