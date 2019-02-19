
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Spot extends DomainEntity {

	private String	line;
	private String	col;
	private Request	request;


	public Spot() {
		super();
	}

	public String getLine() {
		return this.line;
	}
	public void setLine(final String line) {
		this.line = line;
	}

	public String getCol() {
		return this.col;
	}
	public void setCol(final String col) {
		this.col = col;
	}

	@OneToOne(optional = false)
	public Request getRequest() {
		return this.request;
	}
	public void setRequest(final Request request) {
		this.request = request;
	}

}
