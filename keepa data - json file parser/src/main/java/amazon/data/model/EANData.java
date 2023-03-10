package amazon.data.model;

import java.io.Serializable;

public class EANData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String country;
	private String asin;
	private long ean;

	public EANData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EANData(String country, String asin, long ean) {
		super();
		this.country = country;
		this.asin = asin;
		this.ean = ean;
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

	public long getEan() {
		return ean;
	}

	public void setEan(long ean) {
		this.ean = ean;
	}

}
