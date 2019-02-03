package com.pramati.assignment.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pramati.assignment.dto.FormDTO;
import com.pramati.assignment.services.SuggestionServices;
/**
 * 
 * @author nmnitinmohan07
 * Rest Controller to return city auto completion 
 */
@RestController
public class SuggestionController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(SuggestionController.class);
	
	@Autowired
	SuggestionServices suggestionServices;
	
	@RequestMapping(path="suggest_cities",produces="text/plain",method = RequestMethod.GET)
	public StringBuilder getCitiesSuggestion(@RequestParam("start") String start,@RequestParam("atmost") int atmost) throws IOException {
		
		LOGGER.info("Entry SuggestionController");
		StringBuilder strBuilder = new StringBuilder();
		
		List<FormDTO> strList = suggestionServices.getCitySuggestion(start);
		for(FormDTO str: strList ) {
			if(atmost-- > 0) { // checks for to return only utmost count to client , e.g. 5 , 2 as requested by client
				strBuilder.append(str.getCity());
				strBuilder.append('\n');				
			}
		}
		LOGGER.info("Exit SuggestionController");
		return strBuilder;
	}
}
