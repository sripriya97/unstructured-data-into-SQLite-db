package amazon.file.extractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import amazon.data.model.BuyBoxSellerData;
import amazon.data.model.Categories;
import amazon.data.model.EANData;
import amazon.data.model.FrequentlyBoughtTogetherData;
import amazon.data.model.Prices;
import amazon.data.model.ProcessedFileObject;
import amazon.data.model.Product;
import amazon.data.model.SalesRankData;
import amazon.data.model.UPCData;
import amazon.data.model.VariationsData;
import amazon.data.persistence.DataPersistence;

public class ASINDataFileProcessor {

	private DataPersistence persistence = new DataPersistence();
	private List<String> asinList = new ArrayList<String>();

	private Object lock = new Object();
	private Object lock2 = new Object();

	private List<Product> productslist = new LinkedList<>();
	private List<Prices> prices_amz = new LinkedList<>();
	private List<Prices> prices_new = new LinkedList<>();
	private List<Prices> prices_used = new LinkedList<>();
	private List<SalesRankData> salesRanks = new LinkedList<>();
	private List<BuyBoxSellerData> buyboxs = new LinkedList<>();
	private List<UPCData> upcs = new LinkedList<>();
	private List<EANData> eans = new LinkedList<>();
	private List<Categories> categorieslist = new LinkedList<>();
	private List<FrequentlyBoughtTogetherData> freqs = new LinkedList<>();
	private List<VariationsData> vars = new LinkedList<>();

	public ASINDataFileProcessor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void processFilesForFolder(final File folder) throws IOException {
		asinList = persistence.getAsinList();

		Consumer<File> fileConsumer = (fileEntry) -> {
			System.out.println(fileEntry.getName() + "----file started ");
			String fileName = fileEntry.getName().substring(2, fileEntry.getName().length() - 8);
			ProcessedFileObject processedFileObj = new ProcessedFileObject();

			try {
				List<Product> tempproduct = null;
				List<Prices> tempprice_amz = null;
				List<Prices> tempprice_new = null;
				List<Prices> tempprice_used = null;
				List<SalesRankData> tempsalesRank = null;
				List<BuyBoxSellerData> tempbuybox = null;
				List<UPCData> tempupc = null;
				List<EANData> tempean = null;
				List<Categories> tempcategories = null;
				List<FrequentlyBoughtTogetherData> tempfreq = null;
				List<VariationsData> tempvar = null;

				if (fileEntry.isFile() && !asinList.contains(fileName)) {
					processedFileObj = fileReader(fileEntry.getAbsolutePath(), fileEntry.getName());
				}

				synchronized (lock) {
					if (processedFileObj.getProduct() != null)
						productslist.add(processedFileObj.getProduct());
					if (processedFileObj.getPrices_amz() != null && !processedFileObj.getPrices_amz().isEmpty())
						prices_amz.addAll(processedFileObj.getPrices_amz());
					if (processedFileObj.getPrices_new() != null && !processedFileObj.getPrices_new().isEmpty())
						prices_new.addAll(processedFileObj.getPrices_new());
					if (processedFileObj.getPrices_used() != null && !processedFileObj.getPrices_used().isEmpty())
						prices_used.addAll(processedFileObj.getPrices_used());
					if (processedFileObj.getSalesRanks() != null && !processedFileObj.getSalesRanks().isEmpty())
						salesRanks.addAll(processedFileObj.getSalesRanks());
					if (processedFileObj.getBuyboxs() != null && !processedFileObj.getBuyboxs().isEmpty())
						buyboxs.addAll(processedFileObj.getBuyboxs());
					if (processedFileObj.getUpcs() != null && !processedFileObj.getUpcs().isEmpty())
						upcs.addAll(processedFileObj.getUpcs());
					if (processedFileObj.getEans() != null && !processedFileObj.getEans().isEmpty())
						eans.addAll(processedFileObj.getEans());
					if (processedFileObj.getCategorieslist() != null && !processedFileObj.getCategorieslist().isEmpty())
						categorieslist.addAll(processedFileObj.getCategorieslist());
					if (processedFileObj.getFreqs() != null && !processedFileObj.getFreqs().isEmpty())
						freqs.addAll(processedFileObj.getFreqs());
					if (processedFileObj.getVars() != null && !processedFileObj.getVars().isEmpty())
						vars.addAll(processedFileObj.getVars());

					if (productslist.size() >= 100) {
						tempproduct = new LinkedList<>(productslist);
						tempprice_amz = new LinkedList<>(prices_amz);
						tempprice_new = new LinkedList<>(prices_new);
						tempprice_used = new LinkedList<>(prices_used);
						tempsalesRank = new LinkedList<>(salesRanks);
						tempbuybox = new LinkedList<>(buyboxs);
						tempupc = new LinkedList<>(upcs);
						tempean = new LinkedList<>(eans);
						tempcategories = new LinkedList<>(categorieslist);
						tempfreq = new LinkedList<>(freqs);
						tempvar = new LinkedList<>(vars);

						clearLists();
					}

				}

				if (tempproduct != null) {
					synchronized (lock2) {
						// push products to DB
						if (!tempproduct.isEmpty())
							persistence.addProductList(tempproduct);
						if (!tempprice_amz.isEmpty())
							persistence.addPriceData(tempprice_amz, "prices_amazon");
						if (!tempprice_new.isEmpty())
							persistence.addPriceData(tempprice_new, "prices_new");
						if (!tempprice_used.isEmpty())
							persistence.addPriceData(tempprice_used, "prices_used");
						if (!tempsalesRank.isEmpty())
							persistence.addSalesRankData(tempsalesRank);
						if (!tempbuybox.isEmpty())
							persistence.addBuyboxSellerData(tempbuybox);
						if (!tempupc.isEmpty())
							persistence.addUpcsData(tempupc);
						if (!tempean.isEmpty())
							persistence.addEansData(tempean);
						if (!tempcategories.isEmpty())
							persistence.addCategoriesData(tempcategories);
						if (!tempfreq.isEmpty())
							persistence.addfreqBoughtData(tempfreq);
						if (!tempvar.isEmpty())
							persistence.addVariationData(tempvar);

					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(fileEntry.getName() + "----file done ");
		};
		try (Stream<File> fileStream = Arrays.stream(folder.listFiles()).parallel()) {
			fileStream.forEach(fileConsumer);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 

	}

	public ProcessedFileObject fileReader(String filegz, String fileName) throws IOException {
		InputStream fis = new FileInputStream(filegz);
		ProcessedFileObject processedFileObj = new ProcessedFileObject();

		try (GZIPInputStream gzip = new GZIPInputStream(fis)) {
			String jsonData = new String(gzip.readAllBytes());
			JSONObject json = new JSONObject(jsonData);
			if (!json.getJSONArray("products").isNull(0)) {
				JSONObject products = (JSONObject) json.getJSONArray("products").get(0);
				JSONArray csv = products.getJSONArray("csv");
				String country = fileName.substring(0, 2);
				String asin = products.getString("asin");

				processedFileObj.setProduct(productDataBuilder(products, country, asin));
				if (csv.length() >= 1 && !csv.isNull(0))
					processedFileObj.setPrices_amz(priceListBuilder(csv.getJSONArray(0), country, asin));
				if (csv.length() >= 2 && !csv.isNull(1))
					processedFileObj.setPrices_new(priceListBuilder(csv.getJSONArray(1), country, asin));
				if (csv.length() >= 3 && !csv.isNull(2))
					processedFileObj.setPrices_used(priceListBuilder(csv.getJSONArray(2), country, asin));
				if (csv.length() >= 4 && !csv.isNull(3))
					processedFileObj.setSalesRanks(salesRankListBuilder(csv.getJSONArray(3), products, country, asin));
				if (products.has("buyBoxSellerIdHistory"))
					processedFileObj.setBuyboxs(buyboxSellerListBuilder(products, country, asin));
				if (products.has("upcList"))
					processedFileObj.setUpcs(upcsBuilder(products, country, asin));
				if (products.has("eanList"))
					processedFileObj.setEans(eansBuilder(products, country, asin));
				if (products.has("categories"))
					processedFileObj.setCategorieslist(categoryBuilder(products, country, asin));
				if (products.has("frequentlyBoughtTogether"))
					processedFileObj.setFreqs(freqBoughtListBuilder(products, country, asin));
				if (products.has("variations"))
					processedFileObj.setVars(variationsListBuilder(products, country, asin));

				asinList.add(asin);

			}
		}
		return processedFileObj;

	}

	private void clearLists() {
		productslist.clear();
		prices_amz.clear();
		prices_new.clear();
		prices_used.clear();
		salesRanks.clear();
		buyboxs.clear();
		upcs.clear();
		eans.clear();
		categorieslist.clear();
		freqs.clear();
		vars.clear();
	}

	private Product productDataBuilder(JSONObject products, String country, String asin) {
		Product productData = new Product(asin, country);

		if (products.has("title"))
			productData.setTitle(products.getString("title"));
		if (products.has("productType"))
			productData.setProduct_type(products.getInt("productType"));
		if (products.has("trackingSince"))
			productData.setTracking_since(keepaTimeConverter(products.getLong("trackingSince")));
		if (products.has("manufacturer"))
			productData.setManufacturer(products.getString("manufacturer"));
		if (products.has("rootCategory"))
			productData.setRoot_category(products.getLong("rootCategory"));
		if (products.has("brand"))
			productData.setBrand(products.getString("brand"));
		if (products.has("imagesCSV"))
			productData.setImages(products.getString("imagesCSV"));
		if (products.has("parentAsin"))
			productData.setParent_asin(products.getString("parentAsin"));
		if (products.has("productGroup"))
			productData.setProduct_group(products.getString("productGroup"));
		if (products.has("lastUpdate"))
			productData.setLast_update(keepaTimeConverter(products.getLong("lastUpdate")));
		if (products.has("frequentlyBoughtTogether"))
			productData.setFrequently_bought_together(arrayConcat((products.getJSONArray("frequentlyBoughtTogether"))));
		if (products.has("categories"))
			productData.setCategories(arrayConcat((products.getJSONArray("categories"))));
		if (products.has("eanList"))
			productData.setEans(arrayConcat((products.getJSONArray("eanList"))));
		if (products.has("upcList"))
			productData.setUpcs(arrayConcat((products.getJSONArray("upcList"))));
		return productData;
	}

	private List<Prices> priceListBuilder(JSONArray jsonArray, String country, String asin) {
		if (jsonArray.length() == 0)
			return null;
		List<Prices> priceList = new LinkedList<>();
		for (int i = 0; i < jsonArray.length(); i += 2) {
			priceList.add(new Prices(asin, keepaTimeConverter((long) jsonArray.getLong(i)), jsonArray.getLong(i + 1),
					country));
		}
		return priceList;
	}

	private List<SalesRankData> salesRankListBuilder(JSONArray csv, JSONObject products, String country, String asin) {
		List<SalesRankData> salesRankList = new LinkedList<>();
		long category = -1000L;
		try {
			for (int i = 0; i < csv.length(); i += 2) {
				category = products.getLong("salesRankReference");
				salesRankList.add(new SalesRankData(country, asin, category, keepaTimeConverter((long) csv.getLong(i)),
						csv.getLong(i + 1)));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("no sales rank info in csv part");
		}
		try {
			JSONObject sales = products.getJSONObject("salesRanks");
			Iterator<String> itSales = sales.keys();
			while (itSales.hasNext()) {
				String categoryString = itSales.next();
				if (category != -1000L && Long.parseLong(categoryString) == category)
					continue; // skip that category key as it has already been processed from csv[3]
				JSONArray salesArr = sales.getJSONArray(categoryString);
				for (int i = 0; i < salesArr.length(); i += 2) {
					salesRankList.add(new SalesRankData(country, asin, Long.parseLong(categoryString),
							keepaTimeConverter((long) salesArr.getLong(i)), salesArr.getLong(i + 1)));
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("no sales rank info in salesRank part");
		}
		if (salesRankList.size() == 0)
			return null;
		return salesRankList;
	}

	private List<BuyBoxSellerData> buyboxSellerListBuilder(JSONObject products, String country, String asin) {
		JSONArray sellerArr = products.getJSONArray("buyBoxSellerIdHistory");
		if (sellerArr.length() == 0)
			return null;
		List<BuyBoxSellerData> sellerDataList = new LinkedList<>();

		for (int i = 0; i < sellerArr.length(); i += 2) {
			sellerDataList.add(new BuyBoxSellerData(country, asin,
					keepaTimeConverter(Long.parseLong(sellerArr.getString(i))), sellerArr.getString(i + 1)));
		}
		return sellerDataList;
	}

	private List<UPCData> upcsBuilder(JSONObject products, String country, String asin) {
		JSONArray upcsArr = products.getJSONArray("upcList");
		if (upcsArr.length() == 0)
			return null;

		List<UPCData> upcs = new LinkedList<>();
		for (int i = 0; i < upcsArr.length(); i++) {
			upcs.add(new UPCData(country, asin, upcsArr.getLong(i)));
		}
		return upcs;
	}

	private List<EANData> eansBuilder(JSONObject products, String country, String asin) {
		JSONArray eansArr = products.getJSONArray("eanList");
		if (eansArr.length() == 0)
			return null;
		List<EANData> eans = new LinkedList<>();

		for (int i = 0; i < eansArr.length(); i++) {
			eans.add(new EANData(country, asin, eansArr.getLong(i)));
		}
		return eans;
	}

	private List<Categories> categoryBuilder(JSONObject products, String country, String asin) {
		JSONArray categoriesArr = products.getJSONArray("categories");
		if (categoriesArr.length() == 0)
			return null;
		List<Categories> categories = new LinkedList<>();

		for (int i = 0; i < categoriesArr.length(); i++) {
			categories.add(new Categories(country, asin, categoriesArr.getLong(i)));
		}
		return categories;
	}

	private List<FrequentlyBoughtTogetherData> freqBoughtListBuilder(JSONObject products, String country, String asin) {
		JSONArray freqBoughtArr = products.getJSONArray("frequentlyBoughtTogether");
		if (freqBoughtArr.length() == 0)
			return null;
		List<FrequentlyBoughtTogetherData> freqBoughtList = new LinkedList<>();

		for (int i = 0; i < freqBoughtArr.length(); i++) {
			freqBoughtList.add(new FrequentlyBoughtTogetherData(country, asin, freqBoughtArr.getString(i)));
		}
		return freqBoughtList;
	}

	private List<VariationsData> variationsListBuilder(JSONObject products, String country, String asin) {
		JSONArray variations = products.getJSONArray("variations");
		if (variations.length() == 0)
			return null;
		List<VariationsData> variationsList = new LinkedList<>();

		for (int i = 0; i < variations.length(); i++) {
			JSONObject variationJSON = variations.getJSONObject(i);
			String variationAsin = variationJSON.getString("asin");
			String dimension = variationJSON.getJSONArray("attributes").getJSONObject(0).getString("dimension");
			String value = variationJSON.getJSONArray("attributes").getJSONObject(0).getString("value");
			variationsList.add(new VariationsData(country, asin, variationAsin, dimension, value));
		}
		return variationsList;
	}

	private long keepaTimeConverter(long keepaTime) {
		return (keepaTime + 21564000) * 60000;
	}

	private String arrayConcat(JSONArray jsonArray) {
		StringBuffer concat = new StringBuffer();

		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.length(); i++) {
				if (i != 0)
					concat.append("|");
				concat.append(jsonArray.get(i).toString());
			}
		}
		return concat.toString();
	}
}
