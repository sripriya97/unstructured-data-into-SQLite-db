package amazon.data.model;

import java.io.Serializable;

public class UPCData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String country;
	private String asin;
	private long upc;

	public UPCData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UPCData(String country, String asin, long upc) {
		super();
		this.country = country;
		this.asin = asin;
		this.upc = upc;
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

	public long getUpc() {
		return upc;
	}

	public void setUpc(long upc) {
		this.upc = upc;
	}

}
