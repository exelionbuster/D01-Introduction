package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity 
@Access(AccessType.PROPERTY)
public class Spot {

	private int row;
	private int column;
	
	public Spot(){
		super();
	}

	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
	
}
