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

@Entity
@Table(name = "comment")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk_comment_id", nullable = false, insertable = true, updatable = false)
	protected long id;
	
	/** M310 - merknadstekst */
	@Column(name = "comment_text", length = 2000)
	protected String commentText; 
	
	/** M084 - merknadstype */ 
	@Column(name = "comment_type")
	protected String commentType;
	
	/** M611 - merknadsdato */
	@Column(name = "comment_time")
	protected Date commentTime;
	
	/** M612 - merknadRegistrertAv */
	@Column(name = "comment_registered_by")
	protected String commentRegisteredBy;

	// Link to File
	@ManyToOne
	@JoinColumn(name = "comment_file_id", referencedColumnName = "pk_file_id")
	protected File referenceFile;

	// Link to BasicRecord
	@ManyToOne
	@JoinColumn(name = "comment_basic_record_id", referencedColumnName = "pk_record_id")
	protected BasicRecord referenceBasicRecord;

	// Link to DocumentDescription
	@ManyToOne
	@JoinColumn(name = "comment_document_description_id", referencedColumnName = "pk_document_description_id")
	protected DocumentDescription referenceDocumentDescription;

	public Comment() {
		
	}
	
	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public String getCommentRegisteredBy() {
		return commentRegisteredBy;
	}

	public void setCommentRegisteredBy(String commentRegisteredBy) {
		this.commentRegisteredBy = commentRegisteredBy;
	}

	public File getReferenceFile() {
		return referenceFile;
	}

	public void setReferenceFile(File referenceFile) {
		this.referenceFile = referenceFile;
	}

	public BasicRecord getReferenceBasicRecord() {
		return referenceBasicRecord;
	}

	public void setReferenceBasicRecord(BasicRecord referenceBasicRecord) {
		this.referenceBasicRecord = referenceBasicRecord;
	}

	public DocumentDescription getReferenceDocumentDescription() {
		return referenceDocumentDescription;
	}

	public void setReferenceDocumentDescription(
			DocumentDescription referenceDocumentDescription) {
		this.referenceDocumentDescription = referenceDocumentDescription;
	}

	@Override
	public String toString() {
		return "Comment [commentText=" + commentText + ", commentType="
				+ commentType + ", commentTime=" + commentTime
				+ ", commentRegisteredBy=" + commentRegisteredBy
				+ ", referenceFile=" + referenceFile
				+ ", referenceBasicRecord=" + referenceBasicRecord
				+ ", referenceDocumentDescription="
				+ referenceDocumentDescription + "]";
	}
}
