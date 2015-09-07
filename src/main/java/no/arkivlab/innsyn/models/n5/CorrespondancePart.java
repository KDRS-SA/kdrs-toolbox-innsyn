package no.arkivlab.innsyn.models.n5;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "correspondance_part")
@Inheritance(strategy = InheritanceType.JOINED)

public class CorrespondancePart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk_correspondance_part_id", nullable = false, insertable = true, updatable = false)
	protected Long id;

	/** M400 - korrespondansepartNavn (xs:string) */
	@Column(name = "correspondance_part_name")
	protected String correspondancePartName;

	/** M303 - sakspartRolle (xs:string) */
	@Column(name = "case_party_role")
	protected String casePartyRole;

	/** M406 - postadresse (xs:string) */
	@Column(name = "postal_address")
	protected String postalAddress;

	/** M407 - postnummer (xs:string) */
	@Column(name = "post_code")
	protected String postCode;

	/** M408 - poststed (xs:string) */
	@Column(name = "postal_town")
	protected String postalTown;

	/** M409 - land (xs:string) */
	@Column(name = "country")
	protected String country;

	/** M410 - epostadresse (xs:string) */
	@Column(name = "email_address")
	protected String emailAddress;

	/** M411 - telefonnummer (xs:string) */
	@Column(name = "telephone_number")
	protected String telephoneNumber;

	/** M412 - kontaktperson (xs:string) */
	@Column(name = "contact_person")
	protected String contactPerson;

	/** M087 - korrespondanseparttype */
	@Column(name = "correspondance_part_type")
	protected String correspondancePartType;

	/** M305 - administrativEnhet (xs:string) */
	@Column(name = "administrative_unit")
	protected String administrativeUnit;

	/** M307 - saksbehandler */
	@Column(name = "case_handler")
	protected String caseHandler;

	// Links to Records
	@ManyToMany(mappedBy = "referenceCorrespondancePart")
	protected Set<RegistryEntry> referenceRecord = new HashSet<RegistryEntry>();

	public CorrespondancePart() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorrespondancePartName() {
		return correspondancePartName;
	}

	public void setCorrespondancePartName(String correspondancePartName) {
		this.correspondancePartName = correspondancePartName;
	}

	public String getCasePartyRole() {
		return casePartyRole;
	}

	public void setCasePartyRole(String casePartyRole) {
		this.casePartyRole = casePartyRole;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPostalTown() {
		return postalTown;
	}

	public void setPostalTown(String postalTown) {
		this.postalTown = postalTown;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getCorrespondancePartType() {
		return correspondancePartType;
	}

	public void setCorrespondancePartType(String correspondancePartType) {
		this.correspondancePartType = correspondancePartType;
	}

	public String getAdministrativeUnit() {
		return administrativeUnit;
	}

	public void setAdministrativeUnit(String administrativeUnit) {
		this.administrativeUnit = administrativeUnit;
	}

	public String getCaseHandler() {
		return caseHandler;
	}

	public void setCaseHandler(String caseHandler) {
		this.caseHandler = caseHandler;
	}

	public Set<RegistryEntry> getReferenceRecord() {
		return referenceRecord;
	}

	public void setReferenceRecord(Set<RegistryEntry> referenceRecord) {
		this.referenceRecord = referenceRecord;
	}

}
