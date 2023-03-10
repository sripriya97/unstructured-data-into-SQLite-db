package amazon.data.model;

import java.io.Serializable;

public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String asin;
	private String parent_asin;
	private String images;
	private int product_type;
	private String country;
	private String upcs;
	private String eans;
	private String categories;
	private long tracking_since;
	private String manufacturer;
	private long root_category;
	private long last_update;
	private String brand;
	private String frequently_bought_together;
	private String product_group;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String asin, String country) {
		super();
		this.asin = asin;
		this.country = country;
	}

	public Product(String title, String asin, String parent_asin, String images, int product_type, String country,
			String upcs, String eans, String categories, long tracking_since, String manufacturer, long root_category,
			long last_update, String brand, String frequently_bought_together, String product_group) {
		super();
		this.title = title;
		this.asin = asin;
		this.parent_asin = parent_asin;
		this.images = images;
		this.product_type = product_type;
		this.country = country;
		this.upcs = upcs;
		this.eans = eans;
		this.categories = categories;
		this.tracking_since = tracking_since;
		this.manufacturer = manufacturer;
		this.root_category = root_category;
		this.last_update = last_update;
		this.brand = brand;
		this.frequently_bought_together = frequently_bought_together;
		this.product_group = product_group;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public String getParent_asin() {
		return parent_asin;
	}

	public void setParent_asin(String parent_asin) {
		this.parent_asin = parent_asin;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getProduct_type() {
		return product_type;
	}

	public void setProduct_type(int product_type) {
		this.product_type = product_type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUpcs() {
		return upcs;
	}

	public void setUpcs(String upcs) {
		this.upcs = upcs;
	}

	public String getEans() {
		return eans;
	}

	public void setEans(String eans) {
		this.eans = eans;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public long getTracking_since() {
		return tracking_since;
	}

	public void setTracking_since(long tracking_since) {
		this.tracking_since = tracking_since;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public long getRoot_category() {
		return root_category;
	}

	public void setRoot_category(long root_category) {
		this.root_category = root_category;
	}

	public long getLast_update() {
		return last_update;
	}

	public void setLast_update(long last_update) {
		this.last_update = last_update;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getFrequently_bought_together() {
		return frequently_bought_together;
	}

	public void setFrequently_bought_together(String frequently_bought_together) {
		this.frequently_bought_together = frequently_bought_together;
	}

	public String getProduct_group() {
		return product_group;
	}

	public void setProduct_group(String product_group) {
		this.product_group = product_group;
	}

}
