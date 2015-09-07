package no.arkivlab.innsyn.models.n5;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Screening {
	
	/** M500 - tilgangsrestriksjon n4 (JP.TGKODE) */ 
    @Column(name = "access_restriction")
    protected String accessRestriction;

    /** M501 - skjermingshjemmel n4 (JP.UOFF) */
    @Column(name = "screening_authority")
    protected String screeningAuthority;

    /** M502 - skjermingMetadata should be 1-M */
    @Column(name = "screened_metadata")
    protected String screenedMetadata;

    /** M503 - skjermingDokument */
    @Column(name = "screened_document")
    protected String screenedDocument;

    /** M505 - skjermingOpphoererDato n4(JP.AGDATO)*/
    @Column(name = "screening_expires")
    protected Date screenedExpires;
    
    /** M504 - skjermingsvarighet */
    @Column(name = "screening_duration")
    protected String screenedDuration;
    
    public Screening() {
    }
    
    public String getAccessRestriction() {
		return accessRestriction;
	}

	public void setAccessRestriction(String accessRestriction) {
		this.accessRestriction = accessRestriction;
	}

	public String getScreeningAuthority() {
		return screeningAuthority;
	}

	public void setScreeningAuthority(String screeningAuthority) {
		this.screeningAuthority = screeningAuthority;
	}

	public String getScreenedMetadata() {
		return screenedMetadata;
	}

	public void setScreenedMetadata(String screenedMetadata) {
		this.screenedMetadata = screenedMetadata;
	}

	public String getScreenedDocument() {
		return screenedDocument;
	}

	public void setScreenedDocument(String screenedDocument) {
		this.screenedDocument = screenedDocument;
	}

	public Date getScreenedExpires() {
		return screenedExpires;
	}

	public void setScreenedExpires(Date screenedExpires) {
		this.screenedExpires = screenedExpires;
	}

	public String getScreenedDuration() {
		return screenedDuration;
	}

	public void setScreenedDuration(String screenedDuration) {
		this.screenedDuration = screenedDuration;
	}

	@Override
	public String toString() {
		return "Screening [accessRestriction=" + accessRestriction
				+ ", screeningAuthority=" + screeningAuthority
				+ ", screenedMetadata=" + screenedMetadata
				+ ", screenedDocument=" + screenedDocument + "]";
	}
}
