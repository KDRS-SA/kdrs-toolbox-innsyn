package no.arkivlab.innsyn.models.n5;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "document_object")
public class DocumentObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk_document_object_id", nullable = false, insertable = true, updatable = false)
	protected long id;

	/** M001 - systemID (xs:string) */
	@Column(name = "system_id")
	protected String systemId;

	/** M005 - versjonsnummer (xs:integer) **/
	@Column(name = "version_number")
	protected Integer versionNumber;

	/** M700 - variantformat (xs:string) */
	@Column(name = "variant_format")
	protected String variantFormat;

	/** M701 - format (xs:string) */
	@Column(name = "format")
	protected String format;

	/** M702 - formatDetaljer (xs:string) */
	@Column(name = "format_details")
	protected String formatDetails;

	/** M600 - opprettetDato (xs:dateTime) */
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;

	/** M601 - opprettetAv (xs:string) */
	@Column(name = "created_by")
	protected String createdBy;

	/** M218 - referanseDokumentfil (xs:string) */
	@Column(name = "reference_document_file")
	protected String referenceDocumentFile;

	/** M705 - sjekksum (xs:string) */
	@Column(name = "checksum")
	protected String checksum;

	/** M706 - sjekksumAlgoritme (xs:string) */
	@Column(name = "checksum_algorithm")
	protected String checksumAlgorithm;

	/** M707 - filstoerrelse (xs:string) */
	@Column(name = "file_size")
	protected Long fileSize;

	// Link to DocumentDescription
	@ManyToOne
	@JoinColumn(name = "document_object_document_description_id", referencedColumnName = "pk_document_description_id")
	protected DocumentDescription referenceDocumentDescription;

	// Link to Record
	@ManyToOne
	@JoinColumn(name = "document_object_record_id", referencedColumnName = "pk_record_id")
	protected Record referenceRecord;

	public Long getId() {		
		return id;
	}
	
	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public Integer getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Integer versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getVariantFormat() {
		return variantFormat;
	}

	public void setVariantFormat(String variantFormat) {
		this.variantFormat = variantFormat;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getFormatDetails() {
		return formatDetails;
	}

	public void setFormatDetails(String formatDetails) {
		this.formatDetails = formatDetails;
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

	public String getReferenceDocumentFile() {
		return referenceDocumentFile;
	}

	public void setReferenceDocumentFile(String referenceDocumentFile) {
		this.referenceDocumentFile = referenceDocumentFile;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getChecksumAlgorithm() {
		return checksumAlgorithm;
	}

	public void setChecksumAlgorithm(String checksumAlgorithm) {
		this.checksumAlgorithm = checksumAlgorithm;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public DocumentDescription getReferenceDocumentDescription() {
		return referenceDocumentDescription;
	}

	public void setReferenceDocumentDescription(
			DocumentDescription referenceDocumentDescription) {
		this.referenceDocumentDescription = referenceDocumentDescription;
	}

	public Record getReferenceRecord() {
		return referenceRecord;
	}

	public void setReferenceRecord(Record referenceRecord) {
		this.referenceRecord = referenceRecord;
	}

	@Override
	public String toString() {
		return "DocumentObject [id=" + id + ", systemId=" + systemId
				+ ", versionNumber=" + versionNumber + ", variantFormat="
				+ variantFormat + ", format=" + format + ", formatDetails="
				+ formatDetails + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + ", referenceDocumentFile="
				+ referenceDocumentFile + ", checksum=" + checksum
				+ ", checksumAlgorithm=" + checksumAlgorithm + ", fileSize="
				+ fileSize + ", referenceDocumentDescription="
				+ referenceDocumentDescription + ", referenceRecord="
				+ referenceRecord + "]";
	}

}
