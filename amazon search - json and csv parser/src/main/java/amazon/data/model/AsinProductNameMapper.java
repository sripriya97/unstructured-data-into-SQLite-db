package amazon.data.model;

import java.io.Serializable;

public class AsinProductNameMapper implements Serializable {

	private static final long serialVersionUID = 1L;

	private String asin;
	private String itemName;

	public AsinProductNameMapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AsinProductNameMapper(String asin, String itemName) {
		super();
		this.asin = asin;
		this.itemName = itemName;
	}

	public AsinProductNameMapper(String asin) {
		super();
		this.asin = asin;
	}

	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
