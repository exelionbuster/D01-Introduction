
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Spot {

	private int		row;
	private int		column;
	private Request	request;


	public Spot() {
		super();
	}

	public int getRow() {
		return this.row;
	}
	public void setRow(final int row) {
		this.row = row;
	}

	public int getColumn() {
		return this.column;
	}
	public void setColumn(final int column) {
		this.column = column;
	}

	//RELATIONSHIPS

	@OneToOne(optional = false)
	public Request getRequest() {
		return this.request;
	}

	public void setRequest(final Request request) {
		this.request = request;
	}

}
