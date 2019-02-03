package com.pramati.assignment.dto;

import java.util.List;
/**
 * 
 * @author nmnitinmohan07
 * Singleton Class to store FormDto data in formList using Double Checked Locking
 */
public class FormDtoList {

	private volatile static FormDtoList obj;
	private List<FormDTO> formList = null;

	private FormDtoList() {
	}

	public static FormDtoList getInstance() {
		if (obj == null)
			synchronized (FormDtoList.class) {
				if(obj == null)
					obj = new FormDtoList();
			}
		return obj;
	}

	public List<FormDTO> getFormList() {
		return formList;
	}

	public void setFormList(List<FormDTO> formList) {
		this.formList = formList;
	}

}
