package com.siigo.invoice.util.validation;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.NotImplementedException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

public final class FormsValidationUtils {

	public static final String XLS_DOCUMENT = "application/vnd.ms-excel";
	public static final String XLSX_DOCUMENT = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	
	private FormsValidationUtils() {
        throw new NotImplementedException("Utility classes cannot be instantiated");
    }

    public static void assertNotBlank(JSONObject errors, String field, String fieldValue, String message) {
        if (StringUtils.isEmpty(fieldValue) || fieldValue.trim().length() == 0) {
            errors.put(field, message);
        }
    }

    public static void assertMinimumLength(JSONObject errors, String field, String fieldValue, int length, String message) {
        if (fieldValue.length() < length) {
        	errors.put(field, message);
        }
    }
    
    public static void assertExcelFileType(JSONObject errors, String field, String fileType, String message) {
		if(!fileType.equals(XLSX_DOCUMENT) && !fileType.equals(XLS_DOCUMENT))
			errors.put(field, message);
    }
    
    public static void assertPattern(JSONObject errors, String field, String fieldValue, Pattern regExp, String message) {
    	Matcher matcher = regExp.matcher(fieldValue);
        if (!matcher.find()) {
        	errors.put(field, message);
        }
    }
    
    public static void assertNotEquals(JSONObject errors, String field, String fieldValue, String paramToCompare, String message) {
        if (!fieldValue.equals(paramToCompare)) {
        	errors.put(field, message);
        }
    }
    
    public static void assertJSONObjectFormat(JSONObject errors, String field, String jsonString, String message) {
        try {
        	new JSONObject(jsonString);
		} catch (Exception e) {
			errors.put(field, message);
		}
    }

    public static void assertMatches(JSONObject errors, String field, String fieldValue, Pattern regex, String message) {
        if (!regex.matcher(fieldValue).matches()) {
        	errors.put(field, message);
        }
    }
    
    public static <T> void assertNotNull(JSONObject errors, String field, T object, String message) {
        if (object == null) {
        	errors.put(field, message);
        }
    }
    
    public static <T> void assertNotEmpty(JSONObject errors, String field, List<T> list, String message) {
        if (list == null || list.isEmpty()) {
        	errors.put(field, message);
        }
    }
}
