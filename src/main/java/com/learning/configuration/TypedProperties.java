package com.learning.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.learning.data.Constants;

/**
 * This class has methods to read the values from .properties file
 */

public class TypedProperties {

	Properties page = new Properties();
	Properties allPages = new Properties();
	File fileObj;
	String filePath = null;

	/**
	 * Method for reading data from .properties files
	 */
	public Properties getObjectRepository(String fileName, String Filepath) throws IOException {
		String locatorsFileName = fileName;
		final InputStream stream = new FileInputStream(new File(Filepath + locatorsFileName));
		page.load(stream);
		return page;
	}

	/**
	 * This method will read all the .properties files in one shot
	 * 
	 * You do not need to give the file name every time
	 */
	public Properties loadAllFiles() throws IOException {
		fileObj = new File(Constants.PAGEPATH);
		System.out.println(Constants.PAGEPATH);
		filePath = Constants.PAGEPATH;
		File[] files = fileObj.listFiles();
		for (int file = 0; file < files.length; file++) {
			String FileName = files[file].getName();
			allPages = getObjectRepository(FileName, filePath);
		}
		return allPages;
	}

}
