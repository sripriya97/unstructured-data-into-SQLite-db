package amazon.data.model;

import java.io.Serializable;

public class MarketPlaceDepartment implements Serializable {

	private static final long serialVersionUID = 1L;

	private int deptcode;
	private String departmentValue;
	private int marketplaceCode;
	private String marketPlaceValue;

	public MarketPlaceDepartment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MarketPlaceDepartment(String departmentValue, int marketplaceCode, String marketPlaceValue) {
		super();
		this.departmentValue = departmentValue;
		this.marketplaceCode = marketplaceCode;
		this.marketPlaceValue = marketPlaceValue;
	}

	public MarketPlaceDepartment(int deptcode, String departmentValue, int marketplaceCode,
			String marketPlaceValue) {
		super();
		this.deptcode = deptcode;
		this.departmentValue = departmentValue;
		this.marketplaceCode = marketplaceCode;
		this.marketPlaceValue = marketPlaceValue;
	}

	public int getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(int deptcode) {
		this.deptcode = deptcode;
	}

	public String getDepartmentValue() {
		return departmentValue;
	}

	public void setDepartmentValue(String departmentValue) {
		this.departmentValue = departmentValue;
	}

	public int getMarketplaceCode() {
		return marketplaceCode;
	}

	public void setMarketplaceCode(int marketplaceCode) {
		this.marketplaceCode = marketplaceCode;
	}

	public String getMarketPlaceValue() {
		return marketPlaceValue;
	}

	public void setMarketPlaceValue(String marketPlaceValue) {
		this.marketPlaceValue = marketPlaceValue;
	}

}
