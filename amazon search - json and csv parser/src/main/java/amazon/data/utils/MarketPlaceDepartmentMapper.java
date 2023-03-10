package amazon.data.utils;

import java.util.HashMap;
import java.util.Map;

import amazon.data.model.MarketPlaceDepartment;
import amazon.data.persistence.DataPersistence;

public class MarketPlaceDepartmentMapper {

	private DataPersistence persistence;
	private Map<Integer, Map<String, Integer>> marketPlaceDeptMap;
	private Map<String, Integer> marketPlaceMap;

	public MarketPlaceDepartmentMapper(DataPersistence persistence) {
		super();
		this.persistence = persistence;
		this.marketPlaceMap = new HashMap<String, Integer>();
		this.marketPlaceDeptMap = new HashMap<Integer, Map<String, Integer>>();
		// US = 1, UK = 2, DE = 3
		marketPlaceMap.put("ATVPDKIKX0DER", 1);
		marketPlaceMap.put("NA_US", 1);
		marketPlaceMap.put("A1F83G8C2ARO7P", 2);
		marketPlaceMap.put("EU_UK", 2);
		marketPlaceMap.put("A1PA6795UKMFR9", 3);
		marketPlaceMap.put("EU_DE", 3);
		generateMap();
	}

	private void generateMap() {
		for (MarketPlaceDepartment marketPlaceDept : persistence.getAllMarketPlaceMappings()) {
			Map<String, Integer> deptMap = marketPlaceDeptMap.get(marketPlaceDept.getMarketplaceCode()) == null
					? new HashMap<String, Integer>()
					: marketPlaceDeptMap.get(marketPlaceDept.getMarketplaceCode());
			deptMap.put(marketPlaceDept.getDepartmentValue(), marketPlaceDept.getDeptcode());
			marketPlaceDeptMap.put(marketPlaceDept.getMarketplaceCode(), deptMap);
		}
	}

	public int departmentMapper(int marketplaceCode, String marketplaceValue, String deptValue) {
		if (marketPlaceDeptMap.get(marketplaceCode) == null
				|| !marketPlaceDeptMap.get(marketplaceCode).containsKey(deptValue)) {
			Map<String, Integer> deptMap = marketPlaceDeptMap.get(marketplaceCode) == null
					? new HashMap<String, Integer>()
					: marketPlaceDeptMap.get(marketplaceCode);

			int deptCode = persistence
					.addMarketPlace(new MarketPlaceDepartment(deptValue, marketplaceCode, marketplaceValue));
			deptMap.put(deptValue, deptCode);
			marketPlaceDeptMap.put(marketplaceCode, deptMap);
		}
		return marketPlaceDeptMap.get(marketplaceCode).get(deptValue);
	}

	public int marketPlaceMapper(String marketPlaceValue) {
		return marketPlaceMap.get(marketPlaceValue);
	}

}
