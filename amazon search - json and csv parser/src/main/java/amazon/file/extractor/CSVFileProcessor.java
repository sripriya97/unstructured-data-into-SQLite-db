package amazon.file.extractor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import amazon.data.model.AmazonSearchData;
import amazon.data.persistence.DataPersistence;
import amazon.data.utils.MarketPlaceDepartmentMapper;

public class CSVFileProcessor {

	private List<AmazonSearchData> amazonDataList = new ArrayList<>();
	private DataPersistence persistence = new DataPersistence();
	private MarketPlaceDepartmentMapper marketPlaceDeptMapper = new MarketPlaceDepartmentMapper(persistence);

	public void readCSVFile(String fileName) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			int i = 1;
			boolean firstRow = true;
			while ((line = br.readLine()) != null) {
				if (firstRow) {
					firstRow = false;
					continue;
				}
				i++;
				String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				amazonDataList.add(modelStringArray(values));
				if (amazonDataList.size() == 3000)
					persist();

			}
			System.out.println(i + "rows");
			if (amazonDataList.size() != 0)
				persist();
		}
	}

	private AmazonSearchData modelStringArray(String[] csvRow) {
		long year = csvRow[1].isEmpty() ? null : Long.parseLong(csvRow[1]);
		int month = csvRow[2].isEmpty() ? null : Integer.parseInt(csvRow[2]);
		long sfr = csvRow[4].isEmpty() ? null : Long.parseLong(csvRow[4]);
		AmazonSearchData data = new AmazonSearchData(marketPlaceDeptMapper.marketPlaceMapper(csvRow[0]), year, month,
				csvRow[3], sfr);
		switch (csvRow.length) {
		case 6: {
			if (!csvRow[5].isEmpty())
				data.setAsin1(csvRow[5]);
			return data;
		}
		case 7: {
			if (!csvRow[5].isEmpty())
				data.setAsin1(csvRow[5]);
			if (!csvRow[6].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[6]));
			return data;
		}
		case 8: {
			if (!csvRow[5].isEmpty())
				data.setAsin1(csvRow[5]);
			if (!csvRow[6].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[6]));
			if (!csvRow[7].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[7]));
			return data;
		}
		case 9: {
			if (!csvRow[5].isEmpty())
				data.setAsin1(csvRow[5]);
			if (!csvRow[6].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[6]));
			if (!csvRow[7].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[7]));
			if (!csvRow[8].isEmpty())
				data.setAsin1(csvRow[8]);
			return data;
		}
		case 10: {
			if (!csvRow[5].isEmpty())
				data.setAsin1(csvRow[5]);
			if (!csvRow[6].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[6]));
			if (!csvRow[7].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[7]));
			if (!csvRow[8].isEmpty())
				data.setAsin1(csvRow[8]);
			if (!csvRow[9].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[9]));
			return data;
		}
		case 11: {
			if (!csvRow[5].isEmpty())
				data.setAsin1(csvRow[5]);
			if (!csvRow[6].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[6]));
			if (!csvRow[7].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[7]));
			if (!csvRow[8].isEmpty())
				data.setAsin1(csvRow[8]);
			if (!csvRow[9].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[9]));
			if (!csvRow[10].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[10]));
			return data;
		}
		case 12: {
			if (!csvRow[5].isEmpty())
				data.setAsin1(csvRow[5]);
			if (!csvRow[6].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[6]));
			if (!csvRow[7].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[7]));
			if (!csvRow[8].isEmpty())
				data.setAsin1(csvRow[8]);
			if (!csvRow[9].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[9]));
			if (!csvRow[10].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[10]));
			if (!csvRow[11].isEmpty())
				data.setAsin1(csvRow[11]);
			return data;
		}
		case 13: {
			if (!csvRow[5].isEmpty())
				data.setAsin1(csvRow[5]);
			if (!csvRow[6].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[6]));
			if (!csvRow[7].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[7]));
			if (!csvRow[8].isEmpty())
				data.setAsin1(csvRow[8]);
			if (!csvRow[9].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[9]));
			if (!csvRow[10].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[10]));
			if (!csvRow[11].isEmpty())
				data.setAsin1(csvRow[11]);
			if (!csvRow[12].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[12]));
			return data;
		}
		case 14: {
			if (!csvRow[5].isEmpty())
				data.setAsin1(csvRow[5]);
			if (!csvRow[6].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[6]));
			if (!csvRow[7].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[7]));
			if (!csvRow[8].isEmpty())
				data.setAsin1(csvRow[8]);
			if (!csvRow[9].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[9]));
			if (!csvRow[10].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[10]));
			if (!csvRow[11].isEmpty())
				data.setAsin1(csvRow[11]);
			if (!csvRow[12].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[12]));
			if (!csvRow[13].isEmpty())
				data.setClickShare1(Double.parseDouble(csvRow[13]));
			return data;
		}
		default: {
			return data;
		}
		}
	}

	private void persist() {
		persistence.addData(amazonDataList);
		amazonDataList.clear();
	}
}
