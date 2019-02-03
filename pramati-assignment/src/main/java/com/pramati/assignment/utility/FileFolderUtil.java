package com.pramati.assignment.utility;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pramati.assignment.services.SuggestionServices;

/**
 * 
 * @author nmnitinmohan07
 * Utility class to get files from specific folder & file name
 */
public class FileFolderUtil {
	public static final Logger LOGGER = LoggerFactory.getLogger(FileFolderUtil.class);
	
	public static File getResource(String resourceName) {
		File file = null;
		ClassLoader classLoader = SuggestionServices.class.getClassLoader();
		if(null != classLoader){
			URL url = classLoader.getResource(resourceName);
			try {
				file = new File(url.toURI());
			} catch (URISyntaxException e) {
				LOGGER.error("URISyntaxException Exception "+e.getMessage());
			}
		}
		return file;
	}
	public static File[] filterFiles(String folderName, String fileName) {
		File dir = getResource(folderName);
		File[] listFiles = null;
		if (dir != null) {
			listFiles = dir.listFiles();
		}else{
			LOGGER.info("Directory is Empty");
		}
		return listFiles;
	}
}
