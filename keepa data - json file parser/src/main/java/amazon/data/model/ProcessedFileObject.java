package amazon.data.model;

import java.util.List;

public class ProcessedFileObject {

	private Product product;
	private List<Prices> prices_amz;
	private List<Prices> prices_new;
	private List<Prices> prices_used;
	private List<SalesRankData> salesRanks;
	private List<BuyBoxSellerData> buyboxs;
	private List<UPCData> upcs;
	private List<EANData> eans;
	private List<Categories> categorieslist;
	private List<FrequentlyBoughtTogetherData> freqs;
	private List<VariationsData> vars;

	public ProcessedFileObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Prices> getPrices_amz() {
		return prices_amz;
	}

	public void setPrices_amz(List<Prices> prices_amz) {
		this.prices_amz = prices_amz;
	}

	public List<Prices> getPrices_new() {
		return prices_new;
	}

	public void setPrices_new(List<Prices> prices_new) {
		this.prices_new = prices_new;
	}

	public List<Prices> getPrices_used() {
		return prices_used;
	}

	public void setPrices_used(List<Prices> prices_used) {
		this.prices_used = prices_used;
	}

	public List<SalesRankData> getSalesRanks() {
		return salesRanks;
	}

	public void setSalesRanks(List<SalesRankData> salesRanks) {
		this.salesRanks = salesRanks;
	}

	public List<BuyBoxSellerData> getBuyboxs() {
		return buyboxs;
	}

	public void setBuyboxs(List<BuyBoxSellerData> buyboxs) {
		this.buyboxs = buyboxs;
	}

	public List<UPCData> getUpcs() {
		return upcs;
	}

	public void setUpcs(List<UPCData> upcs) {
		this.upcs = upcs;
	}

	public List<EANData> getEans() {
		return eans;
	}

	public void setEans(List<EANData> eans) {
		this.eans = eans;
	}

	public List<Categories> getCategorieslist() {
		return categorieslist;
	}

	public void setCategorieslist(List<Categories> categorieslist) {
		this.categorieslist = categorieslist;
	}

	public List<FrequentlyBoughtTogetherData> getFreqs() {
		return freqs;
	}

	public void setFreqs(List<FrequentlyBoughtTogetherData> freqs) {
		this.freqs = freqs;
	}

	public List<VariationsData> getVars() {
		return vars;
	}

	public void setVars(List<VariationsData> vars) {
		this.vars = vars;
	}

}