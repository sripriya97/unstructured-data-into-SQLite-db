package amazon.data.model;

import java.io.Serializable;

public class FrequentlyBoughtTogetherData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String country;
	private String asin;
	private String boughtTogetherAsin;

	public FrequentlyBoughtTogetherData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FrequentlyBoughtTogetherData(String country, String asin, String boughtTogetherAsin) {
		super();
		this.country = country;
		this.asin = asin;
		this.boughtTogetherAsin = boughtTogetherAsin;
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

	public String getBoughtTogetherAsin() {
		return boughtTogetherAsin;
	}

	public void setBoughtTogetherAsin(String boughtTogetherAsin) {
		this.boughtTogetherAsin = boughtTogetherAsin;
	}

}
