package amazon.file.extractor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class GZFileExtractor {

	public static void main(String[] args) throws IOException {

		gzUnzip(); // UNZIP .gz file

	}

	public static void gzUnzip() {

		byte[] buffer = new byte[1024];

		try {

			GZIPInputStream gzis = new GZIPInputStream(
					new FileInputStream("C:\\Users\\Sripriya Srinivasan\\Downloads\\RA\\usB084WZQ65S.json.gz"));
			FileOutputStream out = new FileOutputStream(
					"C:\\Users\\Sripriya Srinivasan\\Downloads\\RA\\usB084WZQ65S.json");

			int len;
			while ((len = gzis.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}

			gzis.close();
			out.close();

			System.out.println("Extracted ");

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}
