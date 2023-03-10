package amazon.data.model;

import java.io.Serializable;

public class Categories implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String country;
	private String asin;
	private long categories;

	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categories(String country, String asin, long categories) {
		super();
		this.country = country;
		this.asin = asin;
		this.categories = categories;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public long getCategories() {
		return categories;
	}

	public void setCategories(long categories) {
		this.categories = categories;
	}

}
