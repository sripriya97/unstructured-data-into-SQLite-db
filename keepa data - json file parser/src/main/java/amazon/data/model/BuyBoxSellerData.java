package amazon.data.model;

import java.io.Serializable;

public class BuyBoxSellerData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String country;
	private String asin;
	private long time;
	private String seller;

	public BuyBoxSellerData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BuyBoxSellerData(String country, String asin, long time, String seller) {
		super();
		this.country = country;
		this.asin = asin;
		this.time = time;
		this.seller = seller;
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

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

}
