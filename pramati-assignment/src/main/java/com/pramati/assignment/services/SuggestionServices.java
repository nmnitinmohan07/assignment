package com.pramati.assignment.services;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.pramati.assignment.constant.IConstant;
import com.pramati.assignment.dto.FormDTO;
import com.pramati.assignment.dto.FormDtoList;
import com.pramati.assignment.sort.SortByCity;
import com.pramati.assignment.utility.FileFolderUtil;
/**
 * @author nmnitinmohan07
 * Service class to return City suggestion
 */
@Component
public class SuggestionServices {
	public static final Logger LOGGER = LoggerFactory.getLogger(SuggestionServices.class);
	private String cityRegexBeign = "(?i)(";
	private String cityRegexEnd = ").*";

	public List<FormDTO> getCitySuggestion(String arg) throws IOException {
		LOGGER.info("Entry SuggestionServices");
		CSVReader csvReader ;
		String[] nextRecord;
		FormDtoList formDtoList = FormDtoList.getInstance(); // gets singleton based FormDtoList object.
		List<FormDTO> cityList;
		if (formDtoList.getFormList() == null) {			 // For first time , it reads the data from CSV file  & stores the data in Singleton Object
			File[] jsonFiles = FileFolderUtil.filterFiles(IConstant.METERING, IConstant.CITY_WISE_DATA_CSV);
			FileReader filereader = new FileReader(jsonFiles[0].getPath());
			csvReader = new CSVReader(filereader);
			nextRecord = csvReader.readNext();
			cityList = new ArrayList<FormDTO>();
			formDtoList.setFormList(cityList);
			while ((nextRecord = csvReader.readNext()) != null) {
				FormDTO form = new FormDTO();
				form.setCity(nextRecord[1]);				// setting city data in formDto object
				form.setState(nextRecord[2]);				// setting state data in formDto object
				cityList.add(form);
			}
			csvReader.close();
		} else
			cityList = formDtoList.getFormList();			// From 1st time onwards , it need not read .csv file again. It uses existing object to get data.
		
		List<FormDTO> listClone = new ArrayList<FormDTO>();

		for (FormDTO city : cityList) {						// Regular expression pattern to get matching city data.
			if (city.getCity().matches(cityRegexBeign + arg + cityRegexEnd)) {
				listClone.add(city);
			}
		}
		Collections.sort(listClone, new SortByCity());		// sorting by city in alphabetical order.
		LOGGER.info("Exit SuggestionServices");
		return listClone;

	}
}
