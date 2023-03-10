package amazon.data.model;

import java.io.Serializable;

public class SalesRankData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String country;
	private String asin;
	private long category;
	private long time;
	private long salesRank;

	public SalesRankData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalesRankData(String country, String asin, long category, long time, long salesRank) {
		super();
		this.country = country;
		this.asin = asin;
		this.category = category;
		this.time = time;
		this.salesRank = salesRank;
	}

	public SalesRankData(String country, String asin, long category, long time) {
		super();
		this.country = country;
		this.asin = asin;
		this.category = category;
		this.time = time;
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

	public long getCategory() {
		return category;
	}

	public void setCategory(long category) {
		this.category = category;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getSalesRank() {
		return salesRank;
	}

	public void setSalesRank(long salesRank) {
		this.salesRank = salesRank;
	}

}
