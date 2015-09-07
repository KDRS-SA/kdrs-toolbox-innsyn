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
@Table(name = "case_party")
public class CaseParty implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_case_party_id", nullable = false, insertable = true, updatable = false)
    protected Long id;

	/** M010 - sakspartID (xs:string) */
	@Column(name = "case_party_id")
	protected String casePartyId; 

	/** M302 sakspartNavn (xs:string) */
	@Column(name = "case_party_name")	
	protected String casePartyName;
	
	/** M303 sakspartRolle  (xs:string) */
	@Column(name = "case_party_role")	
	protected String casePartyRole; 
	
	/** M406 postadresse  (xs:string) */
	@Column(name = "postal_address")	
	protected String postalAddress;  
	
	/** M407 postnummer (xs:string) */
	@Column(name = "post_code")
	protected String postCode; 
	
	/** M408 poststed  (xs:string) */
	@Column(name = "postal_town")
	protected String postalTown;  
	
	/** M409 - land (xs:string) */
	@Column(name = "country")
	protected String country;
	
	/** M410 - epostadresse  (xs:string) */
	@Column(name = "email_address")
	protected String emailAddress; 
	
	/** M411 - telefonnummer  (xs:string) */
	@Column(name = "telephone_number")
	protected String telephoneNumber; 
	
	/** M412 - kontaktperson (xs:string) */
	@Column(name = "contact_person")
	protected String contactPerson;
	
	// Links to CaseFiles
	@ManyToMany(mappedBy = "referenceCaseParty")
	protected Set<CaseFile> referenceCaseFile = new HashSet<CaseFile>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCasePartyId() {
		return casePartyId;
	}

	public void setCasePartyId(String casePartyId) {
		this.casePartyId = casePartyId;
	}

	public String getCasePartyName() {
		return casePartyName;
	}

	public void setCasePartyName(String casePartyName) {
		this.casePartyName = casePartyName;
	}

	public String getCasePartyRole() {
		return casePartyRole;
	}

	public void setCasePartyRole(String casePartyRole) {
		this.casePartyRole = casePartyRole;
	}

	public String getPostal_adresse() {
		return postalAddress;
	}

	public void setPostal_adresse(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPoststed() {
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

	public Set<CaseFile> getReferenceCaseFile() {
		return referenceCaseFile;
	}

	public void setReferenceCaseFile(Set<CaseFile> referenceCaseFile) {
		this.referenceCaseFile = referenceCaseFile;
	}
	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getPostalTown() {
		return postalTown;
	}

	
}
