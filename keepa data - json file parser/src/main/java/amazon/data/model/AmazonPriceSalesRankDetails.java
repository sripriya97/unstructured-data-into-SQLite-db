package amazon.data.model;

public class AmazonPriceSalesRankDetails {

	private Long average;
	private int nonMissingCount;
	private int totalCount;
	private Long category;

	public AmazonPriceSalesRankDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AmazonPriceSalesRankDetails(Long average, int nonMissingCount, int totalCount, Long category) {
		super();
		this.average = average;
		this.nonMissingCount = nonMissingCount;
		this.totalCount = totalCount;
		this.category = category;
	}

	public AmazonPriceSalesRankDetails(Long average, int nonMissingCount, int totalCount) {
		super();
		this.average = average;
		this.nonMissingCount = nonMissingCount;
		this.totalCount = totalCount;
	}

	public Long getAverage() {
		return average;
	}

	public void setAverage(Long average) {
		this.average = average;
	}

	public int getNonMissingCount() {
		return nonMissingCount;
	}

	public void setNonMissingCount(int nonMissingCount) {
		this.nonMissingCount = nonMissingCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

}
