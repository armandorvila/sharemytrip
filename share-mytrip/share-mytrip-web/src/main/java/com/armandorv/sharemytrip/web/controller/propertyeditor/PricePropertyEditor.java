package com.armandorv.sharemytrip.web.controller.propertyeditor;

import java.beans.PropertyEditorSupport;

import com.armandorv.sharemytrip.business.model.Price;

public class PricePropertyEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String text) {
		
		Price price = new Price();
		price.setPrice(Double.parseDouble(text));
		setValue(price);

	}

	public boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}

}
