package com.pramati.assignment.sort;

import java.util.Comparator;

import com.pramati.assignment.dto.FormDTO;
/**
 * 
 * @author nmnitinmohan07
 * Sorting Class to sort FormDto based on city
 *
 */
public class SortByCity implements Comparator<FormDTO>{

	@Override
	public int compare(FormDTO o1, FormDTO o2) {
		// TODO Auto-generated method stub
		return o1.getCity().compareTo(o2.getCity());
	}
	
}

