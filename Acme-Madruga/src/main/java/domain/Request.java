
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Request {

	private String		status;
	private String		rejectedReason;
	private Member		member;
	private Procession	procession;
	private Spot		spot;


	public Request() {
		super();
	}

	@NotNull
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

	@OneToOne(optional = false)
	public Member getMember() {
		return this.member;
	}

	public void setMember(final Member member) {
		this.member = member;
	}

	@OneToOne(optional = false)
	public Procession getProcession() {
		return this.procession;
	}

	public void setProcession(final Procession procession) {
		this.procession = procession;
	}

	@OneToOne(optional = true)
	public Spot getSpot() {
		return this.spot;
	}

	public void setSpot(final Spot spot) {
		this.spot = spot;
	}

}
