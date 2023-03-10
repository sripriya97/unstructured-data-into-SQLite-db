package amazon.data.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import amazon.data.model.AmazonSearchData;
import amazon.data.model.AsinProductNameMapper;
import amazon.data.model.MarketPlaceDepartment;

public class DataPersistence {

	private Connection conn = null;

	private Connection connect() throws SQLException {
//		String url = "jdbc:sqlite:/mnt/disks/db/amazon/amazon.db";
		String url = "jdbc:sqlite:C:/Users/Sripriya Srinivasan/Desktop/db/amazon.db";
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		conn.setAutoCommit(false);
		return this.conn;
	}

	private void closeConnection() {
		try {
			if (conn != null) {
				conn.setAutoCommit(true);
				conn.close();
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void addData(List<AmazonSearchData> dataObjList) {

		String sql = "INSERT INTO AMAZON_SEARCH (MARKET_PLACE,YEAR ,MONTH ,DEPARTMENT_CODE ,SEARCH_TERM , SEARCH_FREQ_RANK , ASIN_1 , CLICK_SHARE_1 ,CONVERSION_SHARE_1 ,\r\n"
				+ "	ASIN_2,CLICK_SHARE_2 ,CONVERSION_SHARE_2 ,ASIN_3 ,CLICK_SHARE_3 ,CONVERSION_SHARE_3 , ASIN_4 ,CLICK_SHARE_4,CONVERSION_SHARE_4,\r\n"
				+ "    ASIN_5 ,CLICK_SHARE_5,CONVERSION_SHARE_5,ASIN_6,CLICK_SHARE_6,CONVERSION_SHARE_6,ASIN_7,CLICK_SHARE_7,CONVERSION_SHARE_7,\r\n"
				+ "    ASIN_8,CLICK_SHARE_8,CONVERSION_SHARE_8,ASIN_9,CLICK_SHARE_9,CONVERSION_SHARE_9,ASIN_10 ,CLICK_SHARE_10,CONVERSION_SHARE_10)\r\n"
				+ "	VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (AmazonSearchData data : dataObjList) {
				pstmt.setInt(1, data.getMarket_place());
				pstmt.setLong(2, data.getYear());
				pstmt.setInt(3, data.getMonth());
				pstmt.setInt(4, data.getDepartment());
				pstmt.setString(5, data.getSearchTerm());
				pstmt.setLong(6, data.getSearchFreqRank());
				pstmt.setString(7, data.getAsin1());
				pstmt.setDouble(8, data.getClickShare1());
				pstmt.setDouble(9, data.getConversionShare1());
				pstmt.setString(10, data.getAsin2());
				pstmt.setDouble(11, data.getClickShare2());
				pstmt.setDouble(12, data.getConversionShare2());
				pstmt.setString(13, data.getAsin3());
				pstmt.setDouble(14, data.getClickShare3());
				pstmt.setDouble(15, data.getConversionShare3());
				pstmt.setString(16, data.getAsin4());
				pstmt.setDouble(17, data.getClickShare4());
				pstmt.setDouble(18, data.getConversionShare4());
				pstmt.setString(19, data.getAsin5());
				pstmt.setDouble(20, data.getClickShare5());
				pstmt.setDouble(21, data.getConversionShare5());
				pstmt.setString(22, data.getAsin6());
				pstmt.setDouble(23, data.getClickShare6());
				pstmt.setDouble(24, data.getConversionShare6());
				pstmt.setString(25, data.getAsin7());
				pstmt.setDouble(26, data.getClickShare7());
				pstmt.setDouble(27, data.getConversionShare7());
				pstmt.setString(28, data.getAsin8());
				pstmt.setDouble(29, data.getClickShare8());
				pstmt.setDouble(30, data.getConversionShare8());
				pstmt.setString(31, data.getAsin9());
				pstmt.setDouble(32, data.getClickShare9());
				pstmt.setDouble(33, data.getConversionShare9());
				pstmt.setString(34, data.getAsin10());
				pstmt.setDouble(35, data.getClickShare10());
				pstmt.setDouble(36, data.getConversionShare10());

				pstmt.addBatch();
			}

			pstmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			closeConnection();
		}

	}

	public void addAsinProductMapping(List<AsinProductNameMapper> asinProdList) {
		String sql = "REPLACE INTO ASIN_TO_PRODUCTNAME (ASIN, ITEM_NAME) VALUES(?,?)";
		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (AsinProductNameMapper asinProd : asinProdList) {
				pstmt.setString(1, asinProd.getAsin());
				pstmt.setString(2, asinProd.getItemName());

				pstmt.addBatch();
			}

			pstmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			closeConnection();
		}

	}

	public List<MarketPlaceDepartment> getAllMarketPlaceMappings() {
		String sql = "SELECT DEPARTMENT_VALUE, DEPARTMENT_CODE, MARKET_PLACE_CODE, MARKET_PLACE_VALUE FROM MARKET_PLACE_MAPPER";
		List<MarketPlaceDepartment> marketplaceList = new LinkedList<MarketPlaceDepartment>();
		try {
			connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				MarketPlaceDepartment mrkt = new MarketPlaceDepartment(rs.getInt("DEPARTMENT_CODE"),
						rs.getString("DEPARTMENT_VALUE"), rs.getInt("MARKET_PLACE_CODE"),
						rs.getString("MARKET_PLACE_VALUE"));
				marketplaceList.add(mrkt);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnection();
		}
		return marketplaceList;

	}

	public int addMarketPlace(MarketPlaceDepartment marketPlaceDeptData) {
		String sql = "INSERT INTO MARKET_PLACE_MAPPER (DEPARTMENT_VALUE,MARKET_PLACE_CODE,MARKET_PLACE_VALUE) VALUES(?,?,?)";
		int deptcode = 0;
		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, marketPlaceDeptData.getDepartmentValue());
			pstmt.setInt(2, marketPlaceDeptData.getMarketplaceCode());
			pstmt.setString(3, marketPlaceDeptData.getMarketPlaceValue());

			// pstmt.executeBatch();
			pstmt.executeUpdate();
			ResultSet res = pstmt.getGeneratedKeys();
			while (res.next()) {
				deptcode = res.getInt(1);
			}
			conn.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			closeConnection();
			return deptcode;
		}
	}

}
