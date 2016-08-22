package com.nuaa.ec.utils;

import java.util.HashMap;
import java.util.Map;

public class StoreData {

	private static Map<String, Object> teachertranslate = new HashMap<String, Object>();

	public static Map<String, Object> getTeachertranslate() {
		return teachertranslate;
	}

	public static void setTeachertranslate(Map<String, Object> teachertranslate) {
		StoreData.teachertranslate = teachertranslate;
	}
	
	
}
