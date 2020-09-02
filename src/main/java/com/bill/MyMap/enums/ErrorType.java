package com.bill.MyMap.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ErrorType {

	SUCCESS("0000"),  //執行成功
	
	MISSIG_REQUIRED_PARAMETERS("1001"),  //缺少必要參數
	
	SYSTEM_CONNECT_ERROR("2001"),  	//系統連線異常
	DATABASE_ERROR("2002"),  		//資料庫錯誤
	
	UNKNOW_ERROR("9999");  //未知錯誤
	
	private String code;
	
	private static final Map<String, ErrorType> lookup = new HashMap<String, ErrorType>();
	 
    static {
        for(ErrorType e : EnumSet.allOf(ErrorType.class)){
            lookup.put(e.code, e);
        }
    }
	
	@SuppressWarnings("unused")
	private String message;
	
	ErrorType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	//用代碼反查錯誤
	public static ErrorType find(String code, ErrorType defaultValue){
		ErrorType value = lookup.get(code);
        if(value == null){
            return defaultValue;
        }
        return value;
    }
}
