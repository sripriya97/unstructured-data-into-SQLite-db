package amazon.data.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import amazon.data.model.BuyBoxSellerData;
import amazon.data.model.Categories;
import amazon.data.model.EANData;
import amazon.data.model.FrequentlyBoughtTogetherData;
import amazon.data.model.Prices;
import amazon.data.model.Product;
import amazon.data.model.SalesRankData;
import amazon.data.model.UPCData;
import amazon.data.model.VariationsData;

public class DataPersistence {

	private Connection conn = null;

	private Connection connect() throws SQLException {
		String url = "jdbc:sqlite:/mnt/disks/db/amazon/amazon.db";
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

	public void addProductList(List<Product> productData) {
		String sql = "INSERT INTO products (country,asin,title,parent_asin,images,product_type,upcs,eans,categories, tracking_since, last_update,manufacturer,root_category,brand,frequently_bought_together,product_group) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (Product product : productData) {
				pstmt.setString(1, product.getCountry());
				pstmt.setString(2, product.getAsin());
				pstmt.setString(3, product.getTitle());
				pstmt.setString(4, product.getParent_asin());
				pstmt.setString(5, product.getImages());
				pstmt.setInt(6, product.getProduct_type());
				pstmt.setString(7, product.getUpcs());
				pstmt.setString(8, product.getEans());
				pstmt.setString(9, product.getCategories());
				pstmt.setLong(10, product.getTracking_since());
				pstmt.setLong(11, product.getLast_update());
				pstmt.setString(12, product.getManufacturer());
				pstmt.setLong(13, product.getRoot_category());
				pstmt.setString(14, product.getBrand());
				pstmt.setString(15, product.getFrequently_bought_together());
				pstmt.setString(16, product.getProduct_group());

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

	public void addPriceData(List<Prices> priceList, String tableName) {

		String sql = "INSERT INTO " + tableName + " (country, asin, time,price) VALUES (?,?,?,?)";

		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (Prices price : priceList) {
				pstmt.setString(1, price.getCountry());
				pstmt.setString(2, price.getAsin());
				pstmt.setLong(3, price.getTime());
				pstmt.setLong(4, price.getPrice());

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

	public void addSalesRankData(List<SalesRankData> salesRankDataList) {

		String sql = "INSERT INTO SALES_RANKS (country, asin, category,time,sales_rank) VALUES (?,?,?,?,?)";

		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (SalesRankData sales : salesRankDataList) {
				pstmt.setString(1, sales.getCountry());
				pstmt.setString(2, sales.getAsin());
				pstmt.setLong(3, sales.getCategory());
				pstmt.setLong(4, sales.getTime());
				pstmt.setLong(5, sales.getSalesRank());

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

	public void addBuyboxSellerData(List<BuyBoxSellerData> sellerDataList) {

		String sql = "INSERT INTO buybox_sellers (country, asin, time,seller) VALUES (?,?,?,?)";

		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (BuyBoxSellerData seller : sellerDataList) {
				pstmt.setString(1, seller.getCountry());
				pstmt.setString(2, seller.getAsin());
				pstmt.setLong(3, seller.getTime());
				pstmt.setString(4, seller.getSeller());

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

	public void addUpcsData(List<UPCData> upcs) {

		String sql = "INSERT INTO upcs (country, asin, upc) VALUES (?,?,?)";

		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (UPCData upc : upcs) {
				pstmt.setString(1, upc.getCountry());
				pstmt.setString(2, upc.getAsin());
				pstmt.setLong(3, upc.getUpc());

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

	public void addEansData(List<EANData> eans) {

		String sql = "INSERT INTO eans (country, asin, ean) VALUES (?,?,?)";

		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (EANData ean : eans) {
				pstmt.setString(1, ean.getCountry());
				pstmt.setString(2, ean.getAsin());
				pstmt.setLong(3, ean.getEan());

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

	public void addCategoriesData(List<Categories> categories) {

		String sql = "INSERT INTO categories (country, asin, categories) VALUES (?,?,?)";

		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (Categories category : categories) {
				pstmt.setString(1, category.getCountry());
				pstmt.setString(2, category.getAsin());
				pstmt.setLong(3, category.getCategories());

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

	public void addfreqBoughtData(List<FrequentlyBoughtTogetherData> freqBoughtList) {

		String sql = "INSERT INTO frequently_bought_together (country, asin, bought_together_asin) VALUES (?,?,?)";

		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (FrequentlyBoughtTogetherData freq : freqBoughtList) {
				pstmt.setString(1, freq.getCountry());
				pstmt.setString(2, freq.getAsin());
				pstmt.setString(3, freq.getBoughtTogetherAsin());

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

	public void addVariationData(List<VariationsData> variationsList) {

		String sql = "INSERT INTO variations (country, asin, variation_asin, dimension,value) VALUES (?,?,?,?,?)";

		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (VariationsData var : variationsList) {
				pstmt.setString(1, var.getCountry());
				pstmt.setString(2, var.getAsin());
				pstmt.setString(3, var.getVariationAsin());
				pstmt.setString(4, var.getDimension());
				pstmt.setString(5, var.getValue());

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

	public List<String> getAsinList() {
		String sql = "SELECT ASIN FROM PRODUCTS";
		List<String> asinList = new LinkedList<String>();
		try {
			connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				asinList.add(rs.getString("asin"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnection();
		}
		return asinList;

	}

}
