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
@Table(name = "fonds_creator")
public class FondsCreator implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk_fonds_creator_id", nullable = false, insertable = true, updatable = false)
	protected long id;

	/** M001 - systemID (xs:string) */
	@Column(name = "system_id")
	protected String systemId;

	/** M006 - arkivskaperID (xs:string) */
	@Column(name = "fonds_creator_id")
	protected String fondsCreatorId;

	/** M023 - arkivskaperNavn (xs:string) */
	@Column(name = "fonds_creator_name")
	protected String fondsCreatorName;

	/** M021 - beskrivelse (xs:string) */
	@Column(name = "description")
	protected String description;

	// Links to Fonds
	@ManyToMany(mappedBy = "referenceFondsCreator")
	protected Set<Fonds> referenceFonds = new HashSet<Fonds>();

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getFondsCreatorId() {
		return fondsCreatorId;
	}

	public void setFondsCreatorId(String fondsCreatorId) {
		this.fondsCreatorId = fondsCreatorId;
	}

	public String getFondsCreatorName() {
		return fondsCreatorName;
	}

	public void setFondsCreatorName(String fondsCreatorName) {
		this.fondsCreatorName = fondsCreatorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Fonds> getReferenceFonds() {
		return referenceFonds;
	}

	public void setReferenceFonds(Set<Fonds> referenceFonds) {
		this.referenceFonds = referenceFonds;
	}

	@Override
	public String toString() {
		return "FondsCreator [id=" + id + ", systemId=" + systemId
				+ ", fondsCreatorId=" + fondsCreatorId + ", fondsCreatorName="
				+ fondsCreatorName + ", description=" + description
				+ ", referenceFonds=" + referenceFonds + "]";
	}

}
