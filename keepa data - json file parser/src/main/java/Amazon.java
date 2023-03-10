import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import amazon.file.extractor.ASINDataFileProcessor;

public class Amazon {

	public static void main(String[] args) throws ParseException, IOException {

		 String folderpath = args[0];
		//final File folder = new File("D:\\RA\\Keepa Remaining\\Sripriya");
		final File folder = new File(folderpath);
		new ASINDataFileProcessor().processFilesForFolder(folder);

	}

}
