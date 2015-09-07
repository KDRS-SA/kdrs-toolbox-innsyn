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
import javax.persistence.ManyToMany;
import javax.persistence.Table;






/**
 * 
 * Right now it's a little unclear as avskrivnig is in both JP and it's own avskriving
 * I think all JP must be avskrevet, but maybe the related avskriving is only applicable
 *  to one side. You need to think about this!!
 * 
 *
 */
@Entity
@Table(name = "write_off")
public class WriteOff implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk_write_off_id", nullable = false, insertable = true, updatable = false)
	protected long id;
	
	/** M617 - avskrivningsdato */ 
	@Column(name = "write_off_date")
	protected Date writeOffDate;
	
	/** M618 - avskrevetAv */
	@Column(name = "write_off_name")
	protected String writeOffBy;
	
	/** M619 - avskrivningsmaate */
	@Column(name = "write_off_method")
	protected String writeOffMethod;

	// Links to Records
	@ManyToMany(mappedBy = "referenceWriteOff")
	protected Set<RegistryEntry> referenceRecord = new HashSet<RegistryEntry>();

	/** M215 referanseAvskrivesAvJournalpost */
	// TODO: Implement this
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getWriteOffDate() {
		return writeOffDate;
	}

	public void setWriteOffDate(Date writeOffDate) {
		this.writeOffDate = writeOffDate;
	}

	public String getWriteOffBy() {
		return writeOffBy;
	}

	public void setWriteOffBy(String writeOffBy) {
		this.writeOffBy = writeOffBy;
	}

	public String getWriteOffMethod() {
		return writeOffMethod;
	}

	public void setWriteOffMethod(String writeOffMethod) {
		this.writeOffMethod = writeOffMethod;
	}

	public Set<RegistryEntry> getReferenceRecord() {
		return referenceRecord;
	}

	public void setReferenceRecord(Set<RegistryEntry> referenceRecord) {
		this.referenceRecord = referenceRecord;
	}

	@Override
	public String toString() {
		return "WriteOff [id=" + id + ", writeOffDate=" + writeOffDate
				+ ", writeOffBy=" + writeOffBy + ", writeOffMethod="
				+ writeOffMethod + ", referenceRecord=" + referenceRecord + "]";
	}
	
	
	
}
