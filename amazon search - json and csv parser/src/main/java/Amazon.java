import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import amazon.file.extractor.CSVFileProcessor;
import amazon.file.extractor.GSONStreamer;

public class Amazon {

	public static void main(String[] args) throws ParseException, IOException {

		GSONStreamer gsonstreamer = new GSONStreamer();
		CSVFileProcessor csvProcessor = new CSVFileProcessor();
		String folderpath = args[0];
		final File folder = new File(folderpath);

		// File folder = new File("C:\\Users\\Sripriya Srinivasan\\Downloads\\usdata");

		for (final File fileEntry : folder.listFiles()) {
			String filename = fileEntry.getName();
			System.out.println(fileEntry.getName() + "----file started ");
			if (fileEntry.isFile() && filename.contains("json")) {
				gsonstreamer.streamer(fileEntry.getAbsolutePath());
			}
			if (fileEntry.isFile() && filename.contains("csv")) {
				csvProcessor.readCSVFile(fileEntry.getAbsolutePath());
			}
			System.out.println(fileEntry.getName() + "----file done ");
		}

	}

}
