package amazon.data.model;

import java.io.Serializable;

public class AmazonSearchData implements Serializable {

	private static final long serialVersionUID = 1L;
	private int dataId;
	private int market_place;
	private long year;
	private int month;
	private int department;
	private String searchTerm;
	private long searchFreqRank;
	private String asin1;
	private double clickShare1;
	private double conversionShare1;
	private String asin2;
	private double clickShare2;
	private double conversionShare2;
	private String asin3;
	private double clickShare3;
	private double conversionShare3;
	private String asin4;
	private double clickShare4;
	private double conversionShare4;
	private String asin5;
	private double clickShare5;
	private double conversionShare5;
	private String asin6;
	private double clickShare6;
	private double conversionShare6;
	private String asin7;
	private double clickShare7;
	private double conversionShare7;
	private String asin8;
	private double clickShare8;
	private double conversionShare8;
	private String asin9;
	private double clickShare9;
	private double conversionShare9;
	private String asin10;
	private double clickShare10;
	private double conversionShare10;

	public AmazonSearchData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AmazonSearchData(long year, int month, int marketplaceCode) {
		super();
		this.year = year;
		this.month = month;
		this.market_place = marketplaceCode;
	}

	public AmazonSearchData(int market_place, long year, int month, String searchTerm, long searchFreqRank) {
		super();
		this.market_place = market_place;
		this.year = year;
		this.month = month;
		this.searchTerm = searchTerm;
		this.searchFreqRank = searchFreqRank;
	}

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	public int getMarket_place() {
		return market_place;
	}

	public void setMarket_place(int market_place) {
		this.market_place = market_place;
	}

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}
	
	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public long getSearchFreqRank() {
		return searchFreqRank;
	}

	public void setSearchFreqRank(long searchFreqRank) {
		this.searchFreqRank = searchFreqRank;
	}

	public String getAsin1() {
		return asin1;
	}

	public void setAsin1(String asin1) {
		this.asin1 = asin1;
	}

	public double getClickShare1() {
		return clickShare1;
	}

	public void setClickShare1(double clickShare1) {
		this.clickShare1 = clickShare1;
	}

	public double getConversionShare1() {
		return conversionShare1;
	}

	public void setConversionShare1(double conversionShare1) {
		this.conversionShare1 = conversionShare1;
	}

	public String getAsin2() {
		return asin2;
	}

	public void setAsin2(String asin2) {
		this.asin2 = asin2;
	}

	public double getClickShare2() {
		return clickShare2;
	}

	public void setClickShare2(double clickShare2) {
		this.clickShare2 = clickShare2;
	}

	public double getConversionShare2() {
		return conversionShare2;
	}

	public void setConversionShare2(double conversionShare2) {
		this.conversionShare2 = conversionShare2;
	}

	public String getAsin3() {
		return asin3;
	}

	public void setAsin3(String asin3) {
		this.asin3 = asin3;
	}

	public double getClickShare3() {
		return clickShare3;
	}

	public void setClickShare3(double clickShare3) {
		this.clickShare3 = clickShare3;
	}

	public double getConversionShare3() {
		return conversionShare3;
	}

	public void setConversionShare3(double conversionShare3) {
		this.conversionShare3 = conversionShare3;
	}

	public String getAsin4() {
		return asin4;
	}

	public void setAsin4(String asin4) {
		this.asin4 = asin4;
	}

	public double getClickShare4() {
		return clickShare4;
	}

	public void setClickShare4(double clickShare4) {
		this.clickShare4 = clickShare4;
	}

	public double getConversionShare4() {
		return conversionShare4;
	}

	public void setConversionShare4(double conversionShare4) {
		this.conversionShare4 = conversionShare4;
	}

	public String getAsin5() {
		return asin5;
	}

	public void setAsin5(String asin5) {
		this.asin5 = asin5;
	}

	public double getClickShare5() {
		return clickShare5;
	}

	public void setClickShare5(double clickShare5) {
		this.clickShare5 = clickShare5;
	}

	public double getConversionShare5() {
		return conversionShare5;
	}

	public void setConversionShare5(double conversionShare5) {
		this.conversionShare5 = conversionShare5;
	}

	public String getAsin6() {
		return asin6;
	}

	public void setAsin6(String asin6) {
		this.asin6 = asin6;
	}

	public double getClickShare6() {
		return clickShare6;
	}

	public void setClickShare6(double clickShare6) {
		this.clickShare6 = clickShare6;
	}

	public double getConversionShare6() {
		return conversionShare6;
	}

	public void setConversionShare6(double conversionShare6) {
		this.conversionShare6 = conversionShare6;
	}

	public String getAsin7() {
		return asin7;
	}

	public void setAsin7(String asin7) {
		this.asin7 = asin7;
	}

	public double getClickShare7() {
		return clickShare7;
	}

	public void setClickShare7(double clickShare7) {
		this.clickShare7 = clickShare7;
	}

	public double getConversionShare7() {
		return conversionShare7;
	}

	public void setConversionShare7(double conversionShare7) {
		this.conversionShare7 = conversionShare7;
	}

	public String getAsin8() {
		return asin8;
	}

	public void setAsin8(String asin8) {
		this.asin8 = asin8;
	}

	public double getClickShare8() {
		return clickShare8;
	}

	public void setClickShare8(double clickShare8) {
		this.clickShare8 = clickShare8;
	}

	public double getConversionShare8() {
		return conversionShare8;
	}

	public void setConversionShare8(double conversionShare8) {
		this.conversionShare8 = conversionShare8;
	}

	public String getAsin9() {
		return asin9;
	}

	public void setAsin9(String asin9) {
		this.asin9 = asin9;
	}

	public double getClickShare9() {
		return clickShare9;
	}

	public void setClickShare9(double clickShare9) {
		this.clickShare9 = clickShare9;
	}

	public double getConversionShare9() {
		return conversionShare9;
	}

	public void setConversionShare9(double conversionShare9) {
		this.conversionShare9 = conversionShare9;
	}

	public String getAsin10() {
		return asin10;
	}

	public void setAsin10(String asin10) {
		this.asin10 = asin10;
	}

	public double getClickShare10() {
		return clickShare10;
	}

	public void setClickShare10(double clickShare10) {
		this.clickShare10 = clickShare10;
	}

	public double getConversionShare10() {
		return conversionShare10;
	}

	public void setConversionShare10(double conversionShare10) {
		this.conversionShare10 = conversionShare10;
	}

}
