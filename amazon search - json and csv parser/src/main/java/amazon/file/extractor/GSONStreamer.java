package amazon.file.extractor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.GZIPInputStream;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import amazon.data.model.AmazonSearchData;
import amazon.data.model.AsinProductNameMapper;
import amazon.data.persistence.DataPersistence;
import amazon.data.utils.MarketPlaceDepartmentMapper;

public class GSONStreamer {
	private DataPersistence persistence = new DataPersistence();
	private MarketPlaceDepartmentMapper marketPlaceDeptMapper = new MarketPlaceDepartmentMapper(persistence);

	private List<AmazonSearchData> dataList = new ArrayList<AmazonSearchData>();
	private AmazonSearchData amazonData = null;
	private List<Object> asinData = new ArrayList<Object>();
	private List<AsinProductNameMapper> asinProductMapperList = new ArrayList<AsinProductNameMapper>();

	private long year;
	private int month;
	private int clickShareRank = 0;
	private int marketplaceCode;
	private String marketplaceValue;
	private int department;
	private String searchTerm;
	private long sfr;
	private String asin;
	private boolean firstEntry = true;

	public GSONStreamer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void streamer(String filePath) throws ParseException {
		int i = 0;
		try {
			String n = null;
			InputStream fis = new FileInputStream(filePath);
			InputStream gis = new GZIPInputStream(fis);
			Reader r = new InputStreamReader(gis);

			JsonReader jsonReader = new JsonReader(r);

			jsonReader.beginObject();

			while (jsonReader.peek() != JsonToken.END_DOCUMENT) {

				String name = jsonReader.nextName();
				if (name.equals("reportSpecification")) {
					jsonReader.beginObject();
					while (jsonReader.peek() != JsonToken.END_OBJECT) {
						n = jsonReader.nextName();
						if (n.equals("dataStartTime")) {
							String date = jsonReader.nextString();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
							Date dateObj = formatter.parse(date);
							month = dateObj.getMonth() + 1;
							year = dateObj.getYear() + 1900;
							// createDataObject();
							continue;
						} else if (n.equals("marketplaceIds")) {
							jsonReader.beginArray();
							marketplaceValue = jsonReader.nextString();
							marketplaceCode = marketPlaceDeptMapper.marketPlaceMapper(marketplaceValue);
							if(jsonReader.peek()!=JsonToken.END_ARRAY)
								System.out.println("This file has more than one MarketplaceID");
							createDataObject();
							// continue;
						} else {
							jsonReader.skipValue();
						}
						if (jsonReader.peek() == JsonToken.END_ARRAY) {
							jsonReader.endArray();
							// break;
						}
						if (jsonReader.peek() == JsonToken.END_OBJECT) {
							jsonReader.endObject();
							break;
						}
					}
				} else if (name.equals("dataByDepartmentAndSearchTerm")) {
					jsonReader.beginArray();
					jsonReader.beginObject();
					while (jsonReader.peek() != JsonToken.END_ARRAY) {
						n = jsonReader.nextName();
						if (n.equals("departmentName")) {
							department = marketPlaceDeptMapper.departmentMapper(marketplaceCode, marketplaceValue,
									jsonReader.nextString());
							if (department != amazonData.getDepartment() && !firstEntry) {
								newObjectDetected();
							}
							amazonData.setDepartment(department); // set new department id
						} else if (n.equals("searchTerm")) {
							searchTerm = jsonReader.nextString();
							// check if search term is different and new object not already created
							if (!searchTerm.equals(amazonData.getSearchTerm()) && amazonData.getSearchTerm() != null
									&& !firstEntry) {
								newObjectDetected();
								amazonData.setDepartment(department);
							}
							amazonData.setSearchTerm(searchTerm);
						} else if (n.equals("searchFrequencyRank")) {
							sfr = jsonReader.nextLong();
							if (sfr != amazonData.getSearchFreqRank() && amazonData.getSearchFreqRank() != 0L
									&& !firstEntry) {
								newObjectDetected();
								amazonData.setDepartment(department);
								amazonData.setSearchTerm(searchTerm);
							}
							amazonData.setSearchFreqRank(sfr);
						} else if (n.equals("clickedAsin")) {
							i++;
							asin = jsonReader.nextString();
							asinData.add(asin);
						} else if (n.equals("clickedItemName")) { // clickedItemName
							asinProductMapperList.add(new AsinProductNameMapper(asin, jsonReader.nextString()));
						} else if (jsonReader.peek() != JsonToken.NULL && n.equals("clickShare")) {
							asinData.add(jsonReader.nextDouble());
						} else if (jsonReader.peek() != JsonToken.NULL && n.equals("conversionShare")) {
							asinData.add(jsonReader.nextDouble());
							firstEntry = false;
							clickShareRank++;
						} else {
							// use this when you are not sure about all the contents in the JSON file
							jsonReader.skipValue();
						}
						if (jsonReader.peek() == JsonToken.END_OBJECT) {
							jsonReader.endObject();
							if (dataList.size() == 3000) {
								persist();
							}

						}
						if (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
							jsonReader.beginObject();
						}
					}
					jsonReader.endArray();
					newObjectDetected();
					if (dataList.size() != 0)
						persist(); // to persist any remaining objects in the list
					jsonReader.endObject();
				} else {
					jsonReader.skipValue();
				}
			}
			jsonReader.close();
			System.out.println(i+"asins");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createDataObject() {
		this.amazonData = new AmazonSearchData(this.year, this.month, this.marketplaceCode);
	}

	private void newObjectDetected() {
		for (int i = 1; i <= this.clickShareRank; i++) {
			setAsinData(i);
		}
		asinData.clear();
		dataList.add(amazonData);
		createDataObject();
		clickShareRank = 0;
	}

	private void persist() {
		persistence.addData(dataList);
		persistence.addAsinProductMapping(asinProductMapperList);
		dataList.clear();
		asinProductMapperList.clear();
	}

	private void setAsinData(int asinIndex) {
		switch (asinIndex) {
		case 1: {
			amazonData.setAsin1((String) asinData.get(0));
			amazonData.setClickShare1((double) asinData.get(1));
			amazonData.setConversionShare1((double) asinData.get(2));
			break;
		}
		case 2: {
			amazonData.setAsin2((String) asinData.get(3));
			amazonData.setClickShare2((double) asinData.get(4));
			amazonData.setConversionShare2((double) asinData.get(5));
			break;
		}
		case 3: {
			amazonData.setAsin3((String) asinData.get(6));
			amazonData.setClickShare3((double) asinData.get(7));
			amazonData.setConversionShare3((double) asinData.get(8));
			break;
		}
		case 4: {
			amazonData.setAsin4((String) asinData.get(9));
			amazonData.setClickShare4((double) asinData.get(10));
			amazonData.setConversionShare4((double) asinData.get(11));
			break;
		}
		case 5: {
			amazonData.setAsin5((String) asinData.get(12));
			amazonData.setClickShare5((double) asinData.get(13));
			amazonData.setConversionShare5((double) asinData.get(14));
			break;
		}
		case 6: {
			amazonData.setAsin6((String) asinData.get(15));
			amazonData.setClickShare6((double) asinData.get(16));
			amazonData.setConversionShare6((double) asinData.get(17));
			break;
		}
		case 7: {
			amazonData.setAsin7((String) asinData.get(18));
			amazonData.setClickShare7((double) asinData.get(19));
			amazonData.setConversionShare7((double) asinData.get(20));
			break;
		}
		case 8: {
			amazonData.setAsin8((String) asinData.get(21));
			amazonData.setClickShare8((double) asinData.get(22));
			amazonData.setConversionShare8((double) asinData.get(23));
			break;
		}
		case 9: {
			amazonData.setAsin9((String) asinData.get(24));
			amazonData.setClickShare9((double) asinData.get(25));
			amazonData.setConversionShare9((double) asinData.get(26));
			break;
		}
		case 10: {
			amazonData.setAsin10((String) asinData.get(27));
			amazonData.setClickShare10((double) asinData.get(28));
			amazonData.setConversionShare10((double) asinData.get(29));
			break;
		}

		default: {
			// throw error
		}
		}
	}
}
