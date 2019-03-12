
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Request extends DomainEntity {

	private String		status;
	private String		rejectedReason;
	private Member		member;
	private Procession	procession;
	private int			processionRow;
	private int			processionColumn;


	public Request() {
		super();
	}

	@NotBlank
	@Pattern(regexp = "^PENDING|APPROVED|REJECTED$")
	public String getStatus() {
		return this.status;
	}
	public void setStatus(final String status) {
		this.status = status;
	}

	public String getRejectedReason() {
		return this.rejectedReason;
	}
	public void setRejectedReason(final String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}

	//RELATIONSHIPS

	@Valid
	@ManyToOne(optional = false)
	public Member getMember() {
		return this.member;
	}

	public void setMember(final Member member) {
		this.member = member;
	}

	@Valid
	@ManyToOne(optional = false)
	public Procession getProcession() {
		return this.procession;
	}

	public void setProcession(final Procession procession) {
		this.procession = procession;
	}

	public int getProcessionRow() {
		return this.processionRow;
	}

	public void setProcessionRow(final int processionRow) {
		this.processionRow = processionRow;
	}

	public int getProcessionColumn() {
		return this.processionColumn;
	}

	public void setProcessionColumn(final int processionColumn) {
		this.processionColumn = processionColumn;
	}

}
