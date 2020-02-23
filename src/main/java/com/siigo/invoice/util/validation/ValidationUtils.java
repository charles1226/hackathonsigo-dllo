package com.siigo.invoice.util.validation;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.NotImplementedException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartException;

public final class ValidationUtils {
	
	public static final String XLS_DOCUMENT = "application/vnd.ms-excel";
	public static final String XLSX_DOCUMENT = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	private ValidationUtils() {
        throw new NotImplementedException("Utility classes cannot be instantiated");
    }

    public static void assertNotBlank(String paramStr, String message) {
        if (StringUtils.isEmpty(paramStr) || paramStr.trim().length() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertMinimumLength(String username, int length, String message) {
        if (username.length() < length) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void assertExcelFileType(String fileType, String message) {
		if(!fileType.equals(XLSX_DOCUMENT) && !fileType.equals(XLS_DOCUMENT))
			throw new MultipartException(message);
    }
    
    public static void assertPattern(String param, Pattern regExp, String message) {
    	Matcher matcher = regExp.matcher(param);
        if (!matcher.find()) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void assertNotEquals(String paramStr, String paramToCompare, String message) {
        if (!paramStr.equals(paramToCompare)) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void assertJSONObjectFormat(String jsonString, String message) {
        try {
        	new JSONObject(jsonString);
		} catch (Exception e) {
			throw new IllegalArgumentException(message);
		}
    }

    public static void assertMatches(String paramStr, Pattern regex, String message) {
        if (!regex.matcher(paramStr).matches()) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static <T> void assertNotNull(T object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static <T> void assertNotEmpty(List<T> list, String message) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
