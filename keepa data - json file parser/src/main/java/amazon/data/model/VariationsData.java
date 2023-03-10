package amazon.data.model;

import java.io.Serializable;

public class VariationsData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String country;
	private String asin;
	private String variationAsin;
	private String dimension;
	private String value;

	public VariationsData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VariationsData(String country, String asin, String variationAsin, String dimension, String value) {
		super();
		this.country = country;
		this.asin = asin;
		this.variationAsin = variationAsin;
		this.dimension = dimension;
		this.value = value;
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

	public String getVariationAsin() {
		return variationAsin;
	}

	public void setVariationAsin(String variationAsin) {
		this.variationAsin = variationAsin;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
