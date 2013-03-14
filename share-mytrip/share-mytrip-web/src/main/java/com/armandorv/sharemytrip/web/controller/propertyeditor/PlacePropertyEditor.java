package com.armandorv.sharemytrip.web.controller.propertyeditor;

import java.beans.PropertyEditorSupport;

import com.armandorv.sharemytrip.business.model.Place;

/**
 * TODO: Substitute java beans property editors for Spring conversion service. 
 */
public class PlacePropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		setValue(new Place(text));
	}

}
