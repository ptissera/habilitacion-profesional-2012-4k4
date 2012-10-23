package com.coming.cellprojectmanager.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	private static DateFormat backendDateFormat;
	private static DateFormat frontendDateFormat;

	static {
		backendDateFormat = new SimpleDateFormat("ddMMyyyy");
		frontendDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
	}

	public static String fechaToFrontendString(Date date) {
		String fechaAsString = "";
		if(date != null) {
			fechaAsString = frontendDateFormat.format(date);
		}
		return fechaAsString;
	}
	
	public static String fechaToBackendString(Date date) {
		String fechaAsString = "";
		if(date != null) {
			fechaAsString = backendDateFormat.format(date);
		}
		return fechaAsString;
	}

	public static Date fechaFromBackendStringToDate(String fecha) {
		if(fecha == null || fecha.isEmpty()) {
			return null;
		}
		Date date = null;
		try {
			date = backendDateFormat.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}	
}
