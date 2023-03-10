package amazon.data.model;

import java.io.Serializable;

public class Prices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String asin;
	private long time;
	private long price;
	private String country;

	public Prices() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Prices(String asin, long time, String country) {
		super();
		this.asin = asin;
		this.time = time;
		this.country = country;
	}

	public Prices(String asin, long time, long price, String country) {
		super();
		this.asin = asin;
		this.time = time;
		this.price = price;
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

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
